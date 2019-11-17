package com.kfwy.park.controller.act.activiti;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.act.activiti.business.IApprovalBaseManager;
import com.kfwy.park.act.activiti.entity.ApprovalBaseEntity;
import com.kfwy.park.common.entity.TaskBaseEntity;
import com.kfwy.park.common.utils.ActivitiConstant;
import com.kfwy.park.common.utils.InputStreamToString;
import com.kfwy.park.controller.act.activiti.vo.*;
import com.kfwy.park.sys.user.business.IUserManager;
import com.kfwy.park.sys.user.entity.UserEntity;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.history.HistoricVariableInstance;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.activiti.image.ProcessDiagramGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;


/**
 * Created by luming on 2019/3/13.
 * 工作流公共方法
 */
@Controller
@RequestMapping("/activitiManage")
public class ActivitiManageService extends SpringRestService {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private IApprovalBaseManager approvalBaseManager;

    @Autowired
    private IUserManager userManager;



    /**
     * @Description 流程部署
     * @author luming
     * @Date 2019/3/13 17:25
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/deployment"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> deployment(@RequestBody ActivitiManageServiceRequest request) throws FileNotFoundException {

        ActivitiManageServiceResponse response = new ActivitiManageServiceResponse();

        String filePath = request.getFilePath();
        File file = new File(filePath);

        DeploymentBuilder deploymentBuilder = repositoryService.createDeployment();
        ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(file));

        deploymentBuilder.addZipInputStream(zipInputStream);
        deploymentBuilder.deploy();

        return this.success(response);
    }

    /**
     * @Description 查询最新版本的流程定义列表
     * @author luming
     * @Date 2019/3/14 8:47
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/procdefList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> procdefList(@RequestBody ActivitiManageServiceRequest request) throws FileNotFoundException {

        ActivitiManageServiceResponse response = new ActivitiManageServiceResponse();

        List<Map<String, Object>> pd = new ArrayList<>();

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        processDefinitionQuery.latestVersion();// 最新版本过滤
        List<ProcessDefinition> list = processDefinitionQuery.list();

        for (ProcessDefinition processDefinition : list) {
            Map<String, Object> values = new HashMap<>();
            values.put("id", processDefinition.getId());
            values.put("name", processDefinition.getName());
            values.put("key", processDefinition.getKey());
            values.put("version", processDefinition.getVersion());
            values.put("deploymentId", processDefinition.getDeploymentId());
            pd.add(values);
        }

        response.setResult(pd);
        return this.success(response);
    }

    /**
     * @Description 删除工作流
     * @author luming
     * @Date 2019/3/14 16:13
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/deleteProcess"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> deleteProcess(@RequestBody ActivitiManageServiceRequest request) throws FileNotFoundException {

        ActivitiManageServiceResponse response = new ActivitiManageServiceResponse();
        TaskBaseEntity taskBaseEntity = request.getEntity();

        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        String key = taskBaseEntity.getProcessDefinitionKey();
        processDefinitionQuery.processDefinitionKey(key);
        List<ProcessDefinition> list = processDefinitionQuery.list();

        for (ProcessDefinition pd : list) {
            String deploymentId = pd.getDeploymentId();
            repositoryService.deleteDeployment(deploymentId, true);
        }

        return this.success(response);
    }

    /**
     * @Description 显示流程图
     * @author luming
     * @Date 2019/3/28 10:27
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/showActivitiPicture"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> showActivitiPicture(@RequestBody ActivitiManageServiceRequest request) throws IllegalAccessException, IOException {

        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        TaskBaseEntity taskBaseEntity = request.getEntity();

        InputStream inputStream = repositoryService.getProcessDiagram(taskBaseEntity.getProcessDefinitionId());

        String encode = InputStreamToString.showPng(inputStream);

        response.setResult(encode);
        return this.success(response);
    }

    /**
     * @Description 显示当前节点
     * @author luming
     * @Date 2019/3/29 10:00
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/currentNode"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> currentNode(@RequestBody ActivitiManageServiceRequest request) throws IllegalAccessException, IOException {

        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        TaskBaseEntity taskBaseEntity = request.getEntity();

        String processInstanceId = taskBaseEntity.getProcessInstanceId();// 获取流程实例ID

        Task task = taskService.createTaskQuery().processInstanceId(processInstanceId).singleResult();

        // 流程定义
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());

        // 正在活动节点
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(task.getExecutionId());

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        ProcessDiagramGenerator pdg = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator();

        InputStream inputStream = pdg.generateDiagram(bpmnModel, "PNG",activeActivityIds, activeActivityIds,
                processEngine.getProcessEngineConfiguration().getActivityFontName(),
                processEngine.getProcessEngineConfiguration().getLabelFontName(),
                processEngine.getProcessEngineConfiguration().getAnnotationFontName(),
                processEngine.getProcessEngineConfiguration().getProcessEngineConfiguration().getClassLoader(), 1.0);

        String encode = InputStreamToString.showPng(inputStream);

        response.setResult(encode);
        return this.success(response);
    }

    /**
     * @Description 审批详情
     * @author luming
     * @Date 2019/3/29 11:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/approvalDetail"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> approvalDetail(@RequestBody ApprovalBaseServiceRequest request) throws IllegalAccessException, IOException {
        ThrowLeaseApplyServiceResponse response = new ThrowLeaseApplyServiceResponse();
        ApprovalBaseEntity approvalBaseEntity = request.getEntity();

        String processInstanceId = approvalBaseEntity.getProcessInstanceId();
        List<ApprovalBaseEntity> approvalList = new ArrayList<>();

        //获取流程取消节点
        Map<String,Object> endParams = new HashMap<>();
        endParams.put("processInstanceId",processInstanceId);
        endParams.put("isDeleted",false);
        endParams.put("approvalStatus","2"); //流程取消节点
        List<ApprovalBaseEntity> endApproval = approvalBaseManager.findByMap(endParams);
        if(endApproval.size()>0){
            if(StringUtils.isEmpty(endApproval.get(0).getTaskId())){
                ApprovalBaseEntity endEntity = endApproval.get(0);
                endEntity.setActName("取消申请");
                endEntity.setApprovalStatus(null);
                approvalList.add(endApproval.get(0));
            }
        }

        //获取流程各个userTask节点
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery().processInstanceId(processInstanceId).orderByTaskCreateTime().desc();//倒叙
        List<HistoricTaskInstance> historyTaskInstanceList = query.list();
        for (HistoricTaskInstance hl : historyTaskInstanceList) {
            ApprovalBaseEntity approvalEntity = null;

            Map<String, Object> map = new HashMap<>();
            map.put("taskId", hl.getId());
            map.put("isDeleted", false);
            approvalEntity = approvalBaseManager.findOne(map);

            if(null == approvalEntity){
                approvalEntity = new ApprovalBaseEntity();
                approvalEntity.setCreateTime(hl.getStartTime());
                if(null !=hl.getAssignee()){  //拾取人不为空时,一种情况是在调整申请，一种是审核人已经拾取
                    List<HistoricVariableInstance> application = historyService.createHistoricVariableInstanceQuery().processInstanceId(hl.getProcessInstanceId()).variableName("applicantCode").list();
                    String applicantCode = (String) application.get(0).getValue();//申请人code
                    if(hl.getAssignee().equals(applicantCode)){ //情况一

                    }else{ //情况二
                        approvalEntity.setApprovalStatus("1");
                    }
                    UserEntity userEntity = userManager.findOne("userCode",hl.getAssignee());
                    approvalEntity.setCreateName(userEntity.getUserName());

                }else { //拾取人为空时，从流程变量里获取第一审批人或第二审批人
                    String verifyUserCode = null;
                    if(hl.getTaskDefinitionKey().equals("regionManage")){
                        List<HistoricVariableInstance> application = historyService.createHistoricVariableInstanceQuery().processInstanceId(hl.getProcessInstanceId()).variableName("regionManage").list();
                        verifyUserCode = (String) application.get(0).getValue();
                    }else if(hl.getTaskDefinitionKey().equals("attractInvestment")){
                        List<HistoricVariableInstance> application = historyService.createHistoricVariableInstanceQuery().processInstanceId(hl.getProcessInstanceId()).variableName("attractInvestment").list();

                        verifyUserCode = (String) application.get(0).getValue();
                    }else if(hl.getTaskDefinitionKey().equals("accountancy")){
                        List<HistoricVariableInstance> application = historyService.createHistoricVariableInstanceQuery().processInstanceId(hl.getProcessInstanceId()).variableName("accountancy").list();

                        verifyUserCode = (String) application.get(0).getValue();
                    }
                    String[] userCodeArr = verifyUserCode.split(",");
                    StringBuffer sb = new StringBuffer();
                    for(String userCode : userCodeArr){  //获取审批人姓名
                        UserEntity userEntity = userManager.findOne("userCode",userCode);
                        sb.append(userEntity.getUserName());
                        sb.append(",");
                    }
                    sb.deleteCharAt(sb.length()-1);
                    approvalEntity.setCreateName(sb.toString());
                    approvalEntity.setApprovalStatus("1");
                }
            }/*else{
                if(hl.getTaskDefinitionKey().equals("adjustApply")){
                    approvalEntity.setApprovalStatus(null);
                    approvalBaseEntity.setApprovalStatusName("取消申请");
                }
            }*/

            approvalEntity.setActName(hl.getName());
            approvalList.add(approvalEntity);
        }

        response.setResult(approvalList);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-04-12 16:35:47
     *@Description 获取当前已经到达的审核进度
     *@Return
     **/
    @RequestMapping(path = "approvalProgress"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> approvalProgress(@RequestBody ApprovalBaseServiceRequest request){
        ApprovalBaseServiceResponse response = new ApprovalBaseServiceResponse();
        ApprovalBaseEntity approvalBaseEntity = request.getEntity();
        String processInstanceId = approvalBaseEntity.getProcessInstanceId();
        Map<String,Object> map = new HashMap<>();
        map.put("processInstanceId",processInstanceId);
        map.put("isDeleted",false);
        List<ApprovalBaseEntity> approvalBaseEntities = approvalBaseManager.findByMap(map,"create_time",false);
        String status = "0";
        String tdkey = null;
        if(approvalBaseEntities.size()>0){
            String taskId = approvalBaseEntities.get(0).getTaskId();
            HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery().taskId(taskId);
            List<HistoricTaskInstance> historyTaskInstanceList = query.list();
            status = approvalBaseEntities.get(0).getApprovalStatus();
            tdkey = historyTaskInstanceList.get(0).getTaskDefinitionKey();
        }

        Map<String,Object> result = new HashMap<>();
        result.put("status",status);
        result.put("tdkey",tdkey);
        response.setResult(result);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-04-19 13:49:23
     *@Description 拾取任务
     *@Return
     **/
    @RequestMapping(path = "/getPublicTask"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getPublicTask(@RequestBody ActivitiManageServiceRequest request) {

        ActivitiManageServiceResponse response = new ActivitiManageServiceResponse();
        TaskBaseEntity taskBaseEntity = request.getEntity();

        String taskId = taskBaseEntity.getTaskId();
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
     *@Author gengtao
     *@Date 2019-04-19 13:51:24
     *@Description 退回已拾取的任务
     *@Return
     **/
    @RequestMapping(path = "/backPublicTask"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> backPublicTask(@RequestBody ActivitiManageServiceRequest request) {
        ActivitiManageServiceResponse response = new ActivitiManageServiceResponse();
        TaskBaseEntity taskBaseEntity = request.getEntity();
        String taskId = taskBaseEntity.getTaskId();
        taskService.setAssignee(taskId, null);
        return this.success(response);
    }

    /**
     * @Description 查询待办的任务数
     * @Auth luming
     * @Date 2019/4/26 10:02
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/waitApprovalCount"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> waitApprovalCount(@RequestBody ActivitiManageServiceRequest request) {
        ActivitiManageServiceResponse response = new ActivitiManageServiceResponse();

        Long landlordCount = waitApprovalCount(ActivitiConstant.KFWY_LANDLORD_APPLY_KEY);
        Long throwLeaseApply = waitApprovalCount(ActivitiConstant.KFWY_THROW_LEASE_APPLY_KEY);
        Long throwLeasePayApply = waitApprovalCount(ActivitiConstant.KFWY_THROW_LEASE_PAY_APPLY_KEY);

        Map<String, Object> map = new HashMap<>();
        map.put("landlordCount", landlordCount);
        map.put("throwLeaseApply", throwLeaseApply);
        map.put("throwLeasePayApply", throwLeasePayApply);

        response.setResult(map);
        return this.success(response);
    }

    Long waitApprovalCount(String processDefinitionKey) {
        // 查询没有被拾取的任务数
        TaskQuery publicTaskQuery = taskService.createTaskQuery();
        publicTaskQuery.taskCandidateUser(CurrentContext.getUserCode()).processDefinitionKey(processDefinitionKey);// 查询候选人待代办的
        long publicTaskCount = publicTaskQuery.count();

        // 查询个人任务数
        TaskQuery myTaskQuery = taskService.createTaskQuery();
        myTaskQuery.taskAssignee(CurrentContext.getUserCode()).processDefinitionKey(processDefinitionKey);// 查询个人待办的
        long myTaskCount = myTaskQuery.count();

        long taskCount = myTaskCount + publicTaskCount;
        return taskCount;
    }
}
