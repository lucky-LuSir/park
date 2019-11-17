package com.kfwy.park.controller.act.activiti;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.act.activiti.business.IThrowLeasePayApplyManager;
import com.kfwy.park.act.activiti.entity.ApprovalBaseEntity;
import com.kfwy.park.act.activiti.entity.ThrowLeasePayApplyEntity;
import com.kfwy.park.ati.contract.business.IContractManager;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.common.utils.ActivitiConstant;
import com.kfwy.park.common.utils.ExportFileUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.act.activiti.vo.*;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.ibatis.builder.BuilderException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by kfwy_it_004 on 2019/3/22.
 */
@RestController
@RequestMapping("/throwLeasePayApplyActiviti")
public class ThrowLeasePayApplyService extends SpringRestService {
    @Autowired
    private IThrowLeasePayApplyManager throwLeasePayApplyManager;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private IContractManager contractManager;

    private final static Logger logger = LoggerFactory.getLogger("ThrowLeasePayApplyService.class");

    @Value("${file.path.head}")
    private String filePathHead;


    /**
     * 新建付款申请 gtaotao
     * @param request
     * @return
     * @throws IllegalAccessException
     */
    @RequestMapping(path = "/createApply"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createApply(@RequestBody ThrowLeasePayApplyServiceRequest request) throws IllegalAccessException {
        ThrowLeasePayApplyServiceResponse response = new ThrowLeasePayApplyServiceResponse();
        ThrowLeasePayApplyEntity throwLeasePayApplyEntity = request.getEntity();

        //检验是否重复提交
        boolean checkReApply = throwLeasePayApplyManager.checkPayApply(throwLeasePayApplyEntity.getThrowLeaseApplyCode());
        if(!checkReApply){
            throw new BuilderException("重复提交退款申请！");
        }

        throwLeasePayApplyManager.checkParams(throwLeasePayApplyEntity);

        //判断合同是否存在
        String ctName = throwLeasePayApplyEntity.getCtName();
        Map<String,Object> contractParam = new HashMap<>();
        contractParam.put("ctName",ctName);
        contractParam.put("isDeleted",false);
        contractParam.put("CtStatus","4");
        ContractEntity contractEntity = contractManager.findOne(contractParam);
        if(null == contractEntity){
            throw new BusinessException("未查到该合同信息!");
        }else{
            throwLeasePayApplyEntity.setCtCode(contractEntity.getCtCode());
        }

        //新建申请
        String applyCode = throwLeasePayApplyManager.createApply(throwLeasePayApplyEntity,repositoryService,runtimeService,taskService);
        response.setResult(applyCode);
        return this.success(response);
    }

    /**
     * 修改申请
     */
    @RequestMapping(path = "/editApply"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> editApply(@RequestBody ThrowLeasePayApplyServiceRequest request) throws IllegalAccessException {
        ThrowLeasePayApplyServiceResponse response = new ThrowLeasePayApplyServiceResponse();
        ThrowLeasePayApplyEntity throwLeasePayApplyEntity = request.getEntity();
        throwLeasePayApplyManager.checkParams(throwLeasePayApplyEntity);
        throwLeasePayApplyManager.update(throwLeasePayApplyEntity);
        response.setResult(throwLeasePayApplyEntity.getApplyCode());
        return this.success(response);
    }

    /**
     * 查询我的申请
     */
    @RequestMapping(path = "/myApplyList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> updateApply(@RequestBody ThrowLeasePayApplyServiceRequest request){
        ThrowLeasePayApplyServiceResponse response = new ThrowLeasePayApplyServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("createCode", CurrentContext.getUserCode());
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted", false);
        PageResult<ThrowLeasePayApplyEntity> throwLeaseApplyEntityList = throwLeasePayApplyManager.findByMap(map,request.getStart(),request.getPageSize(),"create_time",false);

        response.setResult(throwLeaseApplyEntityList);
        return this.success(response);
    }

    /**
     * 查询待我审批
     */
    @RequestMapping(path = "/waitApprovalList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> waitApprovalList(@RequestBody ThrowLeasePayApplyServiceRequest request){
        ThrowLeasePayApplyServiceResponse response = new ThrowLeasePayApplyServiceResponse();
        List<ThrowLeasePayApplyEntity> list = new ArrayList<>();

        /**
         * 查询公共任务列表
         */
        TaskQuery publicTaskQuery = taskService.createTaskQuery();
        publicTaskQuery.taskCandidateUser(CurrentContext.getUserCode()).processDefinitionKey(ActivitiConstant.KFWY_THROW_LEASE_PAY_APPLY_KEY);// 查询候选人待代办的付款申请
        publicTaskQuery.orderByTaskCreateTime().desc();
        List<Task> publicTaskList = publicTaskQuery.list();
        throwLeasePayApplyManager.selectWaitApprovalList(publicTaskList,list,taskService);

        /**
         * 查询个人任务列表
         */
        TaskQuery myTaskQuery = taskService.createTaskQuery();
        myTaskQuery.taskAssignee(CurrentContext.getUserCode()).processDefinitionKey(ActivitiConstant.KFWY_THROW_LEASE_PAY_APPLY_KEY);// 查询个人待办的
        myTaskQuery.orderByTaskCreateTime().desc();
        List<Task> myTaskList = myTaskQuery.list();
        throwLeasePayApplyManager.selectWaitApprovalList(myTaskList,list,taskService);

        //根据日期排序
        list = list.stream().sorted((v1, v2) -> v1.getCreateTime().after(v2.getCreateTime()) ? -1 : 1)
                .collect(Collectors.toList());

        response.setResult(list);
        return this.success(response);
    }

    /**
     * 审核人员办理任务
     */
    @RequestMapping(path = "/verify"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> verify(@RequestBody ApprovalBaseServiceRequest request){

        ApprovalBaseServiceResponse response = new ApprovalBaseServiceResponse();
        ApprovalBaseEntity approvalBaseEntity = request.getEntity();
        String throwLeaseApplyCode = request.getThrowLeaseApplyCode(); //退租申请单编号

        ApprovalBaseEntity entity = throwLeasePayApplyManager.removeBase(approvalBaseEntity);
        String payApplyCode = throwLeasePayApplyManager.verifyApply(entity, throwLeaseApplyCode,taskService,runtimeService);

        response.setResult(payApplyCode);
        return this.success(response);
    }

    /**
     * 是否重新提交申请
     */
    @RequestMapping(path = "/applicantResubmit"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> applicantResubmit(@RequestBody ThrowLeasePayApplyServiceRequest request){
        ThrowLeasePayApplyServiceResponse response = new ThrowLeasePayApplyServiceResponse();
        ThrowLeasePayApplyEntity throwLeasePayApplyEntity = request.getEntity();
        String taskId = throwLeasePayApplyEntity.getTaskId();
        Boolean reapply = request.getReapply();// 是否重新提交退租单
        String rejectReason = throwLeasePayApplyEntity.getRejectReason();// 该申请通过或驳回的理由
        String processInstanceId = throwLeasePayApplyEntity.getProcessInstanceId();// 获得流程实例ID

        String applyCode = throwLeasePayApplyManager.adjustApply(throwLeasePayApplyEntity,reapply,taskId,processInstanceId,taskService,runtimeService);
        response.setResult(applyCode);
        return this.success(response);
    }

    /**
     * 拾取任务
     */
    @RequestMapping(path = "/getPublicTask"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getPublicTask(@RequestBody ThrowLeasePayApplyServiceRequest request) {

        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        ThrowLeasePayApplyEntity throwLeasePayApplyEntity = request.getEntity();

        String taskId = throwLeasePayApplyEntity.getTaskId();
        String userCode = CurrentContext.getUserCode();

        TaskQuery taskQuery = taskService.createTaskQuery().taskId(taskId);
        Task task = taskQuery.singleResult();
        String assignee = task.getAssignee();

        if (!StringUtils.isEmpty(assignee)) {
            throw new BusinessException("该任务已经被处理！");
        } else {
            taskService.claim(taskId, userCode);
        }

        return this.success(response);
    }

    /**
     * 退回已拾取的任务
     */
    @RequestMapping(path = "/backPublicTask"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> backPublicTask(@RequestBody ThrowLeasePayApplyServiceRequest request) {

        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        ThrowLeasePayApplyEntity throwLeasePayApplyEntity = request.getEntity();

        String taskId = throwLeasePayApplyEntity.getTaskId();

        taskService.setAssignee(taskId, null);

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
    public ResponseEntity<IServiceResponse> cancelApply(@RequestBody ThrowLeasePayApplyServiceRequest request) {
        ThrowLeasePayApplyServiceResponse response = new ThrowLeasePayApplyServiceResponse();
        ThrowLeasePayApplyEntity throwLeasePayApplyEntity = request.getEntity();

        String applyCode = throwLeasePayApplyManager.cancelApply(throwLeasePayApplyEntity,runtimeService);
        response.setResult(applyCode);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-04-01 15:08:27
     *@Description 检验是否重复提交退款申请
     *@Return
     **/
    @RequestMapping(path = "/checkApply"
            , method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> checkApply(@RequestBody ThrowLeasePayApplyServiceRequest request){
        ThrowLeasePayApplyServiceResponse response = new ThrowLeasePayApplyServiceResponse();

        ThrowLeasePayApplyEntity throwLeasePayApplyEntity = request.getEntity();
        String throwLeaseApplyCode = throwLeasePayApplyEntity.getThrowLeaseApplyCode();
        boolean result = throwLeasePayApplyManager.checkPayApply(throwLeaseApplyCode);
        response.setResult(result);
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
    public ResponseEntity<IServiceResponse> query(@RequestBody ThrowLeasePayApplyServiceRequest request) throws IllegalAccessException {
        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        ThrowLeasePayApplyEntity entity = request.getEntity();
        ParamUtil<ThrowLeasePayApplyEntity> paramUtil = new ParamUtil<>();
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
        PageResult<ThrowLeasePayApplyEntity> pageResult = throwLeasePayApplyManager.findByMap(map,request.getStart(),request.getPageSize(),"create_time",false);
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
    public ResponseEntity<IServiceResponse> getDept(@RequestBody ThrowLeaseApplyServiceRequest request) throws IllegalAccessException {
        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<ThrowLeasePayApplyEntity> throwLeaseApplyEntities = throwLeasePayApplyManager.selectDept(map);
        response.setResult(throwLeaseApplyEntities);
        return this.success(response);
    }


    /**
     *@Author gengtao
     *@Date 2019-04-03 15:27:47
     *@Description 下载退款申请单
     *@Return
     **/
    @RequestMapping(path = "/exportThrowLeasePayWord"
            , method = RequestMethod.GET)
    public void exportThrowLeasePayWord(HttpServletRequest request, HttpServletResponse response) {
        String appplyCode = request.getParameter("applyCode");
        ThrowLeasePayApplyEntity throwLeasePayApplyEntity = throwLeasePayApplyManager.findOne("applyCode",appplyCode);
        Map<String,Object> map = new HashMap<>();

        if(!throwLeasePayApplyEntity.getApprovalStatus().equals("3")){
            throw new BuilderException("该申请未通过，下载付款申请单失败！");
        }

        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String invoiceInTime = "";
        if(!StringUtils.isEmpty(throwLeasePayApplyEntity.getInvoiceInTime())){
             invoiceInTime = sf.format(throwLeasePayApplyEntity.getInvoiceInTime());
        }
        String appointPayTime = sf.format(throwLeasePayApplyEntity.getAppointPayTime());
        String createTime = sf.format(throwLeasePayApplyEntity.getCreateTime());

        map.put("createName",throwLeasePayApplyEntity.getCreateName());
        map.put("createDeptName",throwLeasePayApplyEntity.getCreateDeptName());
        map.put("payMoney",throwLeasePayApplyEntity.getPayMoney());
        map.put("appointPayTime",appointPayTime);
        map.put("isContract",throwLeasePayApplyEntity.getIsContract().equals("1") ? "是" : "否");
        map.put("isInvoice",throwLeasePayApplyEntity.getIsInvoice().equals("1")? "是" : "否");
        map.put("invoiceInTime",invoiceInTime);
        map.put("payCompany",throwLeasePayApplyEntity.getPayCompany());
        map.put("payee",throwLeasePayApplyEntity.getPayee());
        map.put("createTime",createTime);

        //上传文件名
        String payeeName = throwLeasePayApplyEntity.getPayee().split("\n")[0];
        String fileName = payeeName +"打款申请.doc";
        String fileTemplate = "打款申请.ftl";

        String filePathHeadStr = filePathHead;

        ExportFileUtil.exportThrowLeaseWord(response, map, ThrowLeaseApplyService.class, fileName, fileTemplate, filePathHeadStr);
    }
}
