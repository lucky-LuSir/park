package com.kfwy.park.controller.act.activiti;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.act.activiti.business.ILandlordRentApplyManager;
import com.kfwy.park.common.utils.ActivitiConstant;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.act.activiti.vo.LandlordRentApplyServiceRequest;
import com.kfwy.park.controller.act.activiti.vo.LandlordRentApplyServiceResponse;
import com.kfwy.park.exp.contract.business.ILandlordContractManager;
import com.kfwy.park.exp.contract.business.ILandlordRentManager;
import com.kfwy.park.exp.contract.entity.LandlordContractEntity;
import com.kfwy.park.exp.contract.entity.LandlordRentEntity;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;
import java.util.List;

/**
 * Created by gengtao on 2019/4/19.
 */
@Controller
@RequestMapping("/landlordRentApply")
public class LandlordRentApplyService extends SpringRestService {
    @Autowired
    private ILandlordRentApplyManager landlordRentApplyManager;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private ILandlordContractManager landlordContractManager;

    @Autowired
    private ILandlordRentManager landlordRentManager;

    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody LandlordRentApplyServiceRequest request) throws IllegalAccessException {
        LandlordRentApplyServiceResponse response = new LandlordRentApplyServiceResponse();
        LandlordContractEntity landlordContractEntity = request.getEntity();
        landlordContractManager.checkParams(landlordContractEntity);
        landlordRentApplyManager.createApply(landlordContractEntity,repositoryService,taskService,runtimeService);
        return this.success(response);
    }

    /**
     * 查询我的申请
     */
    @RequestMapping(path = "/myApplyList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> myApplyList(@RequestBody LandlordRentApplyServiceRequest request) {
        LandlordRentApplyServiceResponse response = new LandlordRentApplyServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("createCode", CurrentContext.getUserCode());
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted", false);
        PageResult<LandlordContractEntity> result = landlordRentApplyManager.findByMap(map, request.getStart(), request.getPageSize(), "create_time", false);
        if(result.getData() != null ){
            for(LandlordContractEntity entity : result.getData()){
                Map<String, Object> rentMap = new HashMap<>();
                rentMap.put("cpyCode",CurrentContext.getCpyCode());
                rentMap.put("ldCode",entity.getLdCode());
                rentMap.put("isDeleted",false);
                List<LandlordRentEntity> rentEntities = landlordRentManager.findByMap(rentMap);
                entity.setLandlordRentEntityList(rentEntities);
            }
        }
        response.setResult(result);
        return this.success(response);
    }

    /**
     * 查询待我审批
     */
    @RequestMapping(path = "/waitApprovalList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> waitApprovalList(@RequestBody LandlordRentApplyServiceRequest request) {
        LandlordRentApplyServiceResponse response = new LandlordRentApplyServiceResponse();
        java.util.List<LandlordContractEntity> list = new ArrayList<>();

        /**
         * 查询公共任务列表
         */
        TaskQuery publicTaskQuery = taskService.createTaskQuery();
        publicTaskQuery.taskCandidateUser(CurrentContext.getUserCode()).processDefinitionKey(ActivitiConstant.KFWY_LANDLORD_APPLY_KEY);// 查询候选人待代办的付款申请
        publicTaskQuery.orderByTaskCreateTime().desc();
        java.util.List<Task> publicTaskList = publicTaskQuery.list();
        landlordRentApplyManager.selectWaitApprovalList(publicTaskList, list, taskService);

        /**
         * 查询个人任务列表
         */
        TaskQuery myTaskQuery = taskService.createTaskQuery();
        myTaskQuery.taskAssignee(CurrentContext.getUserCode()).processDefinitionKey(ActivitiConstant.KFWY_LANDLORD_APPLY_KEY);// 查询个人待办的
        myTaskQuery.orderByTaskCreateTime().desc();
        java.util.List<Task> myTaskList = myTaskQuery.list();
        landlordRentApplyManager.selectWaitApprovalList(myTaskList, list, taskService);

        Collections.sort(list, new Comparator<LandlordContractEntity>() {
            @Override
            public int compare(LandlordContractEntity o1, LandlordContractEntity o2) {
                if (o1.getCreateTime().after(o2.getCreateTime())) {
                    return -1;
                }
                return 1;
            }
        });
        response.setResult(list);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-04-19 13:53:29
     *@Description 办理任务
     *@Return
     **/
    @RequestMapping(path = "/verify"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> firstVerify(@RequestBody LandlordRentApplyServiceRequest request) {
        LandlordRentApplyServiceResponse response = new LandlordRentApplyServiceResponse();
        LandlordContractEntity throwLeaseApplyEntity = request.getEntity();

        Boolean verify = request.getVerify();
        String approveReason = request.getApproveReason();// 审批结果原因

        landlordRentApplyManager.verify(throwLeaseApplyEntity, approveReason, verify, taskService, runtimeService);

        return this.success(response);
    }

    /**
     * 关闭流程实例
     * @param request
     * @return
     */
    @RequestMapping(path = "/cancelApply"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cancelApply(@RequestBody LandlordRentApplyServiceRequest request) {
        LandlordRentApplyServiceResponse response = new LandlordRentApplyServiceResponse();
        LandlordContractEntity landlordContractEntity = request.getEntity();

        landlordRentApplyManager.cancelApply(landlordContractEntity,runtimeService);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-04-19 15:45:30
     *@Description 调整申请
     *@Return
     **/
    @RequestMapping(path = "/applicantResubmit"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> applicantResubmit(@RequestBody LandlordRentApplyServiceRequest request) throws IllegalAccessException {
        LandlordRentApplyServiceResponse response = new LandlordRentApplyServiceResponse();
        LandlordContractEntity landlordContractEntity = request.getEntity();
        landlordContractManager.checkParams(landlordContractEntity);
        landlordRentApplyManager.adjustApply(landlordContractEntity,taskService,runtimeService);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-04-15 11:13:31
     *@Description 获取退款记录
     *@Return
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody LandlordRentApplyServiceRequest request) throws IllegalAccessException {
        LandlordRentApplyServiceResponse response = new LandlordRentApplyServiceResponse();
        LandlordContractEntity entity = request.getEntity();
        ParamUtil<LandlordContractEntity> paramUtil = new ParamUtil<>();
        Map<String,Object> map = new HashMap<>();
        paramUtil.objectToMap(entity, map);
        map.put("isDeleted",false);
        map.put("cpyCode",CurrentContext.getCpyCode());
        if(!StringUtils.isEmpty(entity.getCreateDeptCode())){
            map.put("createDeptCode",entity.getCreateDeptCode());
        }
        if(!StringUtils.isEmpty(request.getKeyword())){
            map.put("keyword",request.getKeyword());
        }
        PageResult<LandlordContractEntity> pageResult = landlordContractManager.findByMap(map,request.getStart(),request.getPageSize(),"create_time",false);
        response.setResult(pageResult);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-04-15 14:28:07
     *@Description 获取部门
     *@Return
     **/
    @RequestMapping(path = "/getDept"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getDept(@RequestBody LandlordRentApplyServiceRequest request) throws IllegalAccessException {
        LandlordRentApplyServiceResponse response = new LandlordRentApplyServiceResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<LandlordContractEntity> landlordContractEntities = landlordRentApplyManager.selectDept(map);
        response.setResult(landlordContractEntities);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-04-25 09:54:40
     *@Description 获取合同详情
     *@Return
     **/
    @RequestMapping(path = "/ContractInfo"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> ContractInfo(@RequestBody LandlordRentApplyServiceRequest request) throws IllegalAccessException {
        LandlordRentApplyServiceResponse response = new LandlordRentApplyServiceResponse();
        LandlordContractEntity entity = request.getEntity();
        String ldCode = entity.getLdCode();
        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("ldCode",ldCode);
        LandlordContractEntity landlordContractEntity = landlordContractManager.findOne(map);
        List<LandlordRentEntity> landlordRentEntityList = landlordRentManager.findByMap(map);
        landlordContractEntity.setLandlordRentEntityList(landlordRentEntityList);
        response.setResult(landlordContractEntity);
        return this.success(response);
    }
}
