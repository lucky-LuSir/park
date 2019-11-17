package com.kfwy.park.controller.act.activiti;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.act.activiti.business.IThrowLeaseApplyManager;
import com.kfwy.park.act.activiti.entity.ThrowLeaseApplyEntity;
import com.kfwy.park.common.exportword.WordGenerator;
import com.kfwy.park.common.utils.ActivitiConstant;
import com.kfwy.park.common.utils.ExportFileUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.act.activiti.vo.ThrowLeaseApplyServiceRequest;
import com.kfwy.park.controller.act.activiti.vo.ThrowLeaseApplyServiceResponse;
import com.kfwy.park.sys.file.business.IFileManager;
import com.kfwy.park.sys.file.entity.FileEntity;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by luming on 2019/3/13.
 * 退租申请工作流
 */
@Controller
@RequestMapping("/throwLeaseApplyActiviti")
public class ThrowLeaseApplyService extends SpringRestService {

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private IThrowLeaseApplyManager throwLeaseApplyManager;

    @Autowired
    private IFileManager fileManager;

    @Value("${file.path.head}")
    private String filePathHead;

    /**
     * @Description 新增退租申请单
     * @author luming
     * @Date 2019/3/14 16:58
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/createApply"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createApply(@RequestBody ThrowLeaseApplyServiceRequest request) throws IllegalAccessException {
        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        ThrowLeaseApplyEntity throwLeaseApplyEntity = request.getEntity();

        throwLeaseApplyManager.createApply(throwLeaseApplyEntity, repositoryService, taskService, runtimeService);

        response.setResult(throwLeaseApplyEntity.getApplyCode());
        return this.success(response);
    }

    /**
     * @Description 查询我的申请记录
     * @author luming
     * @Date 2019/3/18 9:09
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/myApplyList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> myApplyList(@RequestBody ThrowLeaseApplyServiceRequest request) {
        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("createCode", CurrentContext.getUserCode());
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted", false);
        PageResult<ThrowLeaseApplyEntity> throwLeaseApplyEntityList = throwLeaseApplyManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(throwLeaseApplyEntityList);
        return this.success(response);
    }

    /**
     * @Description 查询待我审批
     * @author luming
     * @Date 2019/3/18 9:25
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/waitApprovalList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> waitApprovalList(@RequestBody ThrowLeaseApplyServiceRequest request) {

        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();

        List<ThrowLeaseApplyEntity> list = new ArrayList<>();

        /**
         * 查询公共任务列表
         */
        TaskQuery publicTaskQuery = taskService.createTaskQuery();
        publicTaskQuery.taskCandidateUser(CurrentContext.getUserCode()).processDefinitionKey(ActivitiConstant.KFWY_THROW_LEASE_APPLY_KEY);// 查询候选人待代办的
        publicTaskQuery.orderByTaskCreateTime().desc();
        List<Task> publicTaskList = publicTaskQuery.list();
        throwLeaseApplyManager.selectWaitApprovalList(publicTaskList, list, taskService);

        /**
         * 查询个人任务列表
         */
        TaskQuery myTaskQuery = taskService.createTaskQuery();
        myTaskQuery.taskAssignee(CurrentContext.getUserCode()).processDefinitionKey(ActivitiConstant.KFWY_THROW_LEASE_APPLY_KEY);// 查询个人待办的
        myTaskQuery.orderByTaskCreateTime().desc();
        List<Task> myTaskList = myTaskQuery.list();
        throwLeaseApplyManager.selectWaitApprovalList(myTaskList, list, taskService);

        Collections.sort(list, new Comparator<ThrowLeaseApplyEntity>() {
            @Override
            public int compare(ThrowLeaseApplyEntity o1, ThrowLeaseApplyEntity o2) {
                if (o1.getTaskCreateTime().after(o2.getTaskCreateTime())) {
                    return -1;
                }
                return 1;
            }
        });

        response.setResult(list);
        return this.success(response);
    }

    /**
     * @Description 拾取公共任务
     * @author luming
     * @Date 2019/3/19 17:21
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/getPublicTask"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getPublicTask(@RequestBody ThrowLeaseApplyServiceRequest request) {

        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        ThrowLeaseApplyEntity throwLeaseApplyEntity = request.getEntity();

        throwLeaseApplyManager.getPublicTask(throwLeaseApplyEntity, taskService);

        return this.success(response);
    }

    /**
     * @Description 已拾取的任务退回到公共任务中
     * @author luming
     * @Date 2019/3/19 17:39
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/backPublicTask"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> backPublicTask(@RequestBody ThrowLeaseApplyServiceRequest request) {

        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        ThrowLeaseApplyEntity throwLeaseApplyEntity = request.getEntity();

        throwLeaseApplyManager.backPublicTask(throwLeaseApplyEntity, taskService);

        return this.success(response);
    }

    /**
     * @Description 退租单审核人员，办理任务
     * @author luming
     * @Date 2019/3/19 18:06
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/verify"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> firstVerify(@RequestBody ThrowLeaseApplyServiceRequest request) {
        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        ThrowLeaseApplyEntity throwLeaseApplyEntity = request.getEntity();

        Boolean verify = request.getVerify();
        String approveReason = request.getApproveReason();// 审批结果原因

        throwLeaseApplyManager.verify(throwLeaseApplyEntity, approveReason, verify, taskService, runtimeService);

        return this.success(response);
    }

    /**
     * @Description 申请人重新提交
     * @author luming
     * @Date 2019/3/21 15:23
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/applicantResubmit"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> applicantResubmit(@RequestBody ThrowLeaseApplyServiceRequest request) throws IllegalAccessException {
        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        ThrowLeaseApplyEntity throwLeaseApplyEntity = request.getEntity();

        String approveReason = request.getApproveReason();// 该申请通过或驳回的理由
        Boolean reapply = request.getReapply();// 是否重新提交退租单

        throwLeaseApplyManager.applicantResubmit(throwLeaseApplyEntity, approveReason, reapply, taskService, runtimeService);

        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-04-15 11:13:31
     *@Description 获取退租记录
     *@Return
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ThrowLeaseApplyServiceRequest request) throws IllegalAccessException {
        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        ThrowLeaseApplyEntity entity = request.getEntity();
        ParamUtil<ThrowLeaseApplyEntity> paramUtil = new ParamUtil<>();
        Map<String,Object> map = new HashMap<>();
        map = paramUtil.objectToMap(entity, map);
        map.put("isDeleted",false);
        map.put("cpyCode",CurrentContext.getCpyCode());
        if(!StringUtils.isEmpty(entity.getCreateDeptCode())){
            map.put("createDeptCode",entity.getCreateDeptCode());
        }
        if(!StringUtils.isEmpty(request.getKeyword())){
            map.put("keyword",request.getKeyword());
        }
        PageResult<ThrowLeaseApplyEntity> pageResult = throwLeaseApplyManager.findByMap(map,request.getStart(),request.getPageSize(),"create_time",false);
        response.setResult(pageResult);
        return this.success(response);
    }

    @RequestMapping(path = "/getDept"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getDept(@RequestBody ThrowLeaseApplyServiceRequest request) throws IllegalAccessException {
        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<ThrowLeaseApplyEntity> throwLeaseApplyEntities = throwLeaseApplyManager.selectDept(map);
        response.setResult(throwLeaseApplyEntities);
        return this.success(response);
    }

    /**
     * @Description 导出退租单
     * @author wangchuang
     * @Date 2019/3/29 8:46
     * @Version 1.0
     * @Param [request]
     * @return void
     **/
    @RequestMapping(path = "/exportThrowLeaseWord"
            , method = RequestMethod.GET)
    public void exportThrowLeaseWord(HttpServletRequest request, HttpServletResponse response) {

        String applyCode = request.getParameter("applyCode");

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("applyCode", applyCode);
        param.put("isDeleted", false);
        param.put("cpyCode",CurrentContext.getCpyCode());

        ThrowLeaseApplyEntity throwLeaseApplyEntity = throwLeaseApplyManager.findOne(param);

        //用于导出文件名显示月份
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String throwLeaseDate = sdf.format(throwLeaseApplyEntity.getThrowLeaseDate());


        Map<String, Object> map = new HashMap<String, Object>();

        map.put("partyA",throwLeaseApplyEntity.getPartyA());
        map.put("partyB",throwLeaseApplyEntity.getPartyB());
        map.put("throwLeaseDate",throwLeaseDate);
        map.put("leasedArea",throwLeaseApplyEntity.getLeasedArea());
        map.put("throwLeaseArea",throwLeaseApplyEntity.getThrowLeaseArea());
        map.put("monthlyRent",throwLeaseApplyEntity.getMonthlyRent());
        map.put("throwLeaseRemark",throwLeaseApplyEntity.getThrowLeaseRemark());
        map.put("refundBank",throwLeaseApplyEntity.getRefundBank());
        map.put("refundAccountName",throwLeaseApplyEntity.getRefundAccountName());
        map.put("refundAccountNumber",throwLeaseApplyEntity.getRefundAccountNumber());
        map.put("partyAAddress",throwLeaseApplyEntity.getPartyALegalRepresentative());
        map.put("partyALegalRepresentative",throwLeaseApplyEntity.getPartyALegalRepresentative());
        map.put("partyAPhone",throwLeaseApplyEntity.getPartyAPhone());
        map.put("partyBAddress",throwLeaseApplyEntity.getPartyBAddress());
        map.put("partyBLegalRepresentative",throwLeaseApplyEntity.getPartyBLegalRepresentative());
        map.put("partyBPhone",throwLeaseApplyEntity.getPartyBPhone());

        //上传文件名
        String fileName = throwLeaseApplyEntity.getPartyB()+"退租协议.doc";
        String fileTemplate = "退租协议.ftl";

        String filePathHeadStr = filePathHead;

        ExportFileUtil.exportThrowLeaseWord(response, map, ThrowLeaseApplyService.class, fileName, fileTemplate, filePathHeadStr);
    }

    @RequestMapping(path = "/exportThrowLeaseWordWithImg"
            , method = RequestMethod.GET)
    public void exportThrowLeaseWordWithImg(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String applyCode = request.getParameter("applyCode");

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("applyCode", applyCode);
        param.put("isDeleted", false);
        param.put("cpyCode",CurrentContext.getCpyCode());

        ThrowLeaseApplyEntity throwLeaseApplyEntity = throwLeaseApplyManager.findOne(param);
        String throwLeaseRemark = throwLeaseApplyEntity.getThrowLeaseRemark();
        List<String> fileCode = WordGenerator.getFileCode(throwLeaseRemark);
        Map<String, Object> imgPathMap = new HashMap<>();
        for (String code : fileCode){
            FileEntity file = fileManager.findByCode(code);
            String filePath = file.getFilePath();
            imgPathMap.put(code, filePath);
        }
        //用于导出文件名显示月份
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String throwLeaseDate = sdf.format(throwLeaseApplyEntity.getThrowLeaseDate());


        Map<String, Object> map = new HashMap<String, Object>();

        map.put("partyA",throwLeaseApplyEntity.getPartyA());
        map.put("partyB",throwLeaseApplyEntity.getPartyB());
        map.put("throwLeaseDate",throwLeaseDate);
        map.put("leasedArea",throwLeaseApplyEntity.getLeasedArea());
        map.put("throwLeaseArea",throwLeaseApplyEntity.getThrowLeaseArea());
        map.put("monthlyRent",throwLeaseApplyEntity.getMonthlyRent());
        map.put("throwLeaseRemark",throwLeaseApplyEntity.getThrowLeaseRemark());
        map.put("refundBank",throwLeaseApplyEntity.getRefundBank());
        map.put("refundAccountName",throwLeaseApplyEntity.getRefundAccountName());
        map.put("refundAccountNumber",throwLeaseApplyEntity.getRefundAccountNumber());
        map.put("partyAAddress",throwLeaseApplyEntity.getPartyALegalRepresentative());
        map.put("partyALegalRepresentative",throwLeaseApplyEntity.getPartyALegalRepresentative());
        map.put("partyAPhone",throwLeaseApplyEntity.getPartyAPhone());
        map.put("partyBAddress",throwLeaseApplyEntity.getPartyBAddress());
        map.put("partyBLegalRepresentative",throwLeaseApplyEntity.getPartyBLegalRepresentative());
        map.put("partyBPhone",throwLeaseApplyEntity.getPartyBPhone());

        String fileName = throwLeaseApplyEntity.getPartyB()+"退租协议.doc";
        WordGenerator.export(response
                , map
                , ThrowLeaseApplyService.class
                ,"/throwLeaseFile"
                ,imgPathMap
                ,"throwLeaseRemark"
                ,"tuizu.ftl"
                , fileName
                , "file:///C:/D17C33B5"
                , "tuizu.files"
                , "01D54C64.25321F20");
    }
}
