package com.kfwy.park.act.activiti.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.act.activiti.business.IApprovalBaseManager;
import com.kfwy.park.act.activiti.business.IThrowLeaseApplyManager;
import com.kfwy.park.act.activiti.business.IThrowLeasePayApplyManager;
import com.kfwy.park.act.activiti.dao.IThrowLeasePayApplyDbDao;
import com.kfwy.park.act.activiti.entity.ApprovalBaseEntity;
import com.kfwy.park.act.activiti.entity.ThrowLeaseApplyEntity;
import com.kfwy.park.act.activiti.entity.ThrowLeasePayApplyEntity;
import com.kfwy.park.ati.contract.business.IContractManager;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.common.utils.ActivitiConstant;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.hrm.org.dept.business.IDeptManager;
import com.kfwy.park.sys.auth.business.IRoleManager;
import com.kfwy.park.sys.auth.entity.RoleEntity;
import com.kfwy.park.sys.user.business.IUserRoleManager;
import com.kfwy.park.sys.user.entity.UserRoleEntity;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kfwy_it_004 on 2019/3/22.
 */
@Service
@Transactional
public class ThrowLeasePayApplyManagerImpl extends AbstractManager<ThrowLeasePayApplyEntity> implements IThrowLeasePayApplyManager {
    @Autowired
    private IThrowLeasePayApplyDbDao throwLeasePayApplyDbDao;

    @Autowired
    private IContractManager contractManager;

    @Autowired
    private IRoleManager roleManager;
    @Autowired
    private IUserRoleManager userRoleManager;
    @Autowired
    private IThrowLeaseApplyManager throwLeaseApplyManager;
    @Autowired
    private IApprovalBaseManager approvalBaseManager;
    @Autowired
    private IDeptManager deptManager;

    @Override
    protected IMyBatisBaseDao<ThrowLeasePayApplyEntity, Long> getMyBatisDao() {
        return throwLeasePayApplyDbDao;
    }

    @Override
    public void checkParams(ThrowLeasePayApplyEntity throwLeasePayApplyEntity) throws IllegalAccessException {
        List<String> param = new ArrayList<>();
        param.add("throwLeaseApplyCode");
        param.add("payMoney");
        param.add("appointPayTime");
        param.add("isContract");
        param.add("isInvoice");
        param.add("payCompany");
        param.add("payee");
        param.add("outPicture");

        ParamUtil<ThrowLeasePayApplyEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(throwLeasePayApplyEntity, param);
    }

    @Override
    protected void beforeCreate(ThrowLeasePayApplyEntity entity){
        entity.setApprovalStatus("1");
        entity.setApplyCode(CodeGenUtils.generate());
    }

    @Override
    public void selectWaitApprovalList(List<Task> taskList, List<ThrowLeasePayApplyEntity> throwLeasePayApplyEntitiesList, TaskService taskService){
        for (Task task : taskList) {
            String taskId = task.getId();

            String processDefinitionKey = (String) taskService.getVariable(taskId, "processDefinitionKey");

            String applyCode = (String) taskService.getVariable(taskId, "applyCode");
            Map<String, Object> params = new HashMap<>();
            params.put("applyCode", applyCode);
            params.put("isDeleted", false);
            ThrowLeasePayApplyEntity throwLeasePayApplyEntity = throwLeasePayApplyDbDao.selectUniqueByMap(params);

            throwLeasePayApplyEntity.setTaskId(taskId);
            throwLeasePayApplyEntity.setAssignee(task.getAssignee());
            throwLeasePayApplyEntity.setApproveName(task.getName());
            throwLeasePayApplyEntity.setExecutionId(task.getExecutionId());
            throwLeasePayApplyEntity.setProcessInstanceId(task.getProcessInstanceId());
            throwLeasePayApplyEntity.setProcessDefinitionId(task.getProcessDefinitionId());
            throwLeasePayApplyEntity.setTaskCreateTime(task.getCreateTime());
            throwLeasePayApplyEntity.setProcessDefinitionKey(processDefinitionKey);

            throwLeasePayApplyEntitiesList.add(throwLeasePayApplyEntity);
        }
    }

    /**
     * @Description 退租申请通过后，还原园区面积
     * @author luming
     * @Date 2019/3/26 10:24
     * @Version 1.0
     * @Param [throwLeaseApplyEntity]
     * @return void
     */
    @Override
    public void recoverContract(ThrowLeaseApplyEntity throwLeaseApplyEntity) {
        Map <String, Object> map = new HashMap<>();
        map.put("ctName", throwLeaseApplyEntity.getCtName());
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted", false);
        ContractEntity contractEntity = contractManager.findOne(map);

        if (contractEntity != null) {
            contractManager.recoverContract(contractEntity,"3");

            contractEntity.setCtStatus("7");// 合同状态设置为已退租
            contractManager.update(contractEntity);
        } else {
            throw new BusinessException("合同编号："+ throwLeaseApplyEntity.getCtName() +"不存在！");
        }
    }

    /**
     * 获取付款申请审批人员
     * @return
     */
    @Override
    public Map<String,String> getApprovalUser(){
        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);
        params.put("cpyCode", CurrentContext.getUserInfo().getCpyCode());
        List<RoleEntity> roleList = roleManager.findByMap(params);

        List<String> regionManageList = deptManager.selectByUserCode(CurrentContext.getUserCode()); //大区主管
        List<UserRoleEntity> attractInvestmentList = null; //职能招商人员集合
        List<UserRoleEntity> accountancyList = null; //财务人员人员集合
        for (RoleEntity list : roleList) {
            Map<String, Object> map = new HashMap<>();
            map.put("roleCode", list.getRoleCode());
            map.put("isDeleted", false);
            map.put("cpyCode", CurrentContext.getCpyCode());
            if (ActivitiConstant.KFWY_PARK_APPROVAL.equals(list.getRoleName())) {
                attractInvestmentList = userRoleManager.findByMap(map);// 查询角色下关联的人员
            }else if(ActivitiConstant.KFWY_FINANCE_APPROVAL.equals(list.getRoleName())){
                accountancyList = userRoleManager.findByMap(map);
            }
        }

        //大区主管集合
        StringBuffer se = new StringBuffer();
        for(String list : regionManageList){
            se.append(list);
            se.append(",");
        }
        se.deleteCharAt(se.length() - 1);

        // 职能招商集合
        StringBuffer sb = new StringBuffer();
        for (UserRoleEntity list : attractInvestmentList) {
            sb.append(list.getUserCode());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);

        //财务集合
        StringBuffer sd = new StringBuffer();
        for(UserRoleEntity list : accountancyList){
            sd.append(list.getUserCode());
            sd.append(",");
        }
        sd.deleteCharAt(sd.length() -1);

        Map<String,String> map = new HashMap<>();
        map.put("regionManage",se.toString()); //大区主管
        map.put("attractInvestment",sb.toString()); //职能招商
        map.put("accountancy",sd.toString());//财务

        return map;
    }

    /**
     *@Author gengtao
     *@Date 2019-03-29 14:19:14
     *@Description 办理任务
     *@Return
     **/
    @Override
    public String verifyApply(ApprovalBaseEntity approvalBaseEntity,String throwLeaseApplyCode,TaskService taskService,RuntimeService runtimeService){
        String taskId = approvalBaseEntity.getTaskId();

        String approvalStatus = approvalBaseEntity.getApprovalStatus();// 是否通过
        boolean Verify = false;
        if(approvalStatus.equals("5")){
            Verify =true;
        }
        String processInstanceId = approvalBaseEntity.getProcessInstanceId();// 获得流程实例ID

        // 办理任务
        Map<String, Object> variables = new HashMap<>();
        variables.put("verify", Verify);

        //  taskService.addComment(taskId,processInstanceId,"描述");
        taskService.complete(taskId, variables);

        //获取流程实例
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        ThrowLeasePayApplyEntity throwLeasePayApplyEntity = new ThrowLeasePayApplyEntity();
        long id = approvalBaseEntity.getId();
        String applyCode = approvalBaseEntity.getApplyCode();
        throwLeasePayApplyEntity.setId(id);
        throwLeasePayApplyEntity.setApplyCode(applyCode);
        if(Verify){
            if(null != processInstance){
                throwLeasePayApplyEntity.setApprovalStatus("1");// 审批表中状态办理中
            }else{
                throwLeasePayApplyEntity.setApprovalStatus("3");// 审批表中状态为通过
                /*//申请通过后，还原园区面积
                ThrowLeaseApplyEntity throwLeaseApplyEntity = throwLeaseApplyManager.findOne("applyCode",throwLeaseApplyCode);
                recoverContract(throwLeaseApplyEntity);*/
            }
        }else{
            throwLeasePayApplyEntity.setApprovalStatus("4");// 审批表中状态为驳回
            throwLeasePayApplyEntity.setRejectReason(approvalBaseEntity.getApproveComment());
        }
        //修改申请状态
        this.update(throwLeasePayApplyEntity);

        //保存审批
        approvalBaseEntity.setApprovalCode(CodeGenUtils.generate());
        approvalBaseEntity.setApplicantCode(CurrentContext.getUserCode());
        approvalBaseEntity.setApplicantName(CurrentContext.getUserInfo().getUserName());
        approvalBaseEntity.setId(null);
        approvalBaseManager.create(approvalBaseEntity);
      //  int i = 1/0;
        return throwLeasePayApplyEntity.getApplyCode();
    }

    /**
     *@Author gengtao
     *@Date 2019-04-01 10:53:12
     *@Description 检测是否重复提交退款申请
     *@Return
     **/
    @Override
    public boolean checkPayApply(String applyCode){
        Map<String,Object> payMap = new HashMap<>();
        payMap.put("throwLeaseApplyCode",applyCode);
        payMap.put("isDeleted",false);
        List<ThrowLeasePayApplyEntity> payList = this.findByMap(payMap);
        if(payList.size()>0){
            for(ThrowLeasePayApplyEntity entity : payList){
                if(!entity.getApprovalStatus().equals("2")){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     *@Author gengtao
     *@Date 2019-04-01 16:47:27
     *@Description 从ThrowLeasePayApplyEntity获取审批信息
     *@Return
     **/
    @Override
    public ApprovalBaseEntity getApprovalInfo(ThrowLeasePayApplyEntity entity){
        ApprovalBaseEntity result = new ApprovalBaseEntity();
        result.setApprovalCode(CodeGenUtils.generate());
        result.setApplyCode(entity.getApplyCode());
        result.setApplicantCode(entity.getCreateCode());
        result.setApplicantName(entity.getCreateName());
      //  result.setApprovalStatus((verify == false) ? "4" : "5");
     //   result.setApproveComment(approveReason);
        result.setTaskId(entity.getTaskId());
        result.setExecutionId(entity.getExecutionId());
        result.setProcessInstanceId(entity.getProcessInstanceId());
        result.setProcessDefinitionId(entity.getProcessDefinitionId());
        result.setProcessDefinitionKey(entity.getProcessDefinitionKey());
        OperateFillUtils.fill(result);
        return result;
    }

    /**
     *@Author gengtao
     *@Date 2019-04-02 11:54:08
     *@Description 新建打款申请
     *@Return
     **/
    @Override
    public String createApply(ThrowLeasePayApplyEntity throwLeasePayApplyEntity,
                              RepositoryService repositoryService,
                              RuntimeService runtimeService,
                              TaskService taskService){

        //新建申请
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        processDefinitionQuery.processDefinitionKey(ActivitiConstant.KFWY_THROW_LEASE_PAY_APPLY_KEY);
        processDefinitionQuery.latestVersion();// 最新版本过滤
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();

        throwLeasePayApplyEntity.setPdId(processDefinition.getId());
        //   throwLeasePayApplyManager.create(throwLeasePayApplyEntity);// 填写流程申请表

        Map<String,String> approvalUser = getApprovalUser();
        String regionManage = approvalUser.get("regionManage"); //大区主管
        String attractInvestment = approvalUser.get("attractInvestment"); //职能招商
        String accountancy = approvalUser.get("accountancy"); //财务

        String ProcessInstanceKey = ActivitiConstant.KFWY_THROW_LEASE_PAY_APPLY_KEY;// 流程的key
        Map<String, Object> variables = new HashMap<>();
        variables.put("applicantCode", CurrentContext.getUserCode());// 设置变量，当前登陆的用户可发起申请
        variables.put("regionManage", regionManage);
        variables.put("attractInvestment", attractInvestment);
        variables.put("accountancy",accountancy);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ProcessInstanceKey, variables);// 根据流程的key启动流程，并获取流程实例

        throwLeasePayApplyEntity.setProcessInstanceId(processInstance.getProcessInstanceId());
        this.create(throwLeasePayApplyEntity);// 填写流程申请表

        // 根据当前登陆用户和流程实例ID查询任务并办理第一个任务（提交申请任务）
        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee(CurrentContext.getUserCode()).processInstanceId(processInstance.getId());
        Task task = taskQuery.singleResult();
        String id = task.getId();

        // 将流程定义的Key存入流程变量中
        Map<String, Object> taskVariables = new HashMap<>();
        taskVariables.put("processDefinitionKey", processInstance.getProcessDefinitionKey());
        taskVariables.put("applyCode", throwLeasePayApplyEntity.getApplyCode());
        taskService.complete(id, taskVariables);
        return throwLeasePayApplyEntity.getApplyCode();
    }

    /**
     *@Author gengtao
     *@Date 2019-04-02 11:54:29
     *@Description 调整申请
     *@Return
     **/
    @Override
    public String adjustApply(ThrowLeasePayApplyEntity throwLeasePayApplyEntity,
                              boolean reapply,
                              String taskId,
                              String processInstanceId,
                              TaskService taskService,
                              RuntimeService runtimeService){
        // 办理任务
        Map<String, Object> variables = new HashMap<>();
        variables.put("verify", reapply);
        try{
            taskService.complete(taskId, variables);
        }catch (Exception e){
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //手动回滚事物
            throw new BuilderException("办理重新提交付款申请失败！");
        }


        // 查询当前流程实例是否存在
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        // 一审被拒后，是否重新提交审核
        if (reapply) {
            if (processInstance != null) {
                throwLeasePayApplyEntity.setApprovalStatus("1");// 审批表中状态改为审批中
            }else{
                throwLeasePayApplyEntity.setApprovalStatus("3");// 已通过
            }
        } else {
            throwLeasePayApplyEntity.setApprovalStatus("2");  //未通过

            //在操作表插入一条取消申请操作
            ApprovalBaseEntity approvalBaseEntity = getApprovalInfo(throwLeasePayApplyEntity);
            approvalBaseEntity.setApprovalStatus("2");
            approvalBaseManager.create(approvalBaseEntity);

            if (processInstance != null) {
                runtimeService.deleteProcessInstance(processInstanceId, null);
            }
        }
        this.update(throwLeasePayApplyEntity);
        return throwLeasePayApplyEntity.getApplyCode();
    }

    /**
     *@Author gengtao
     *@Date 2019-04-02 14:42:21
     *@Description 取消申请
     *@Return
     **/
    @Override
    public String cancelApply(ThrowLeasePayApplyEntity throwLeasePayApplyEntity,RuntimeService runtimeService){
        String processInstanceId = throwLeasePayApplyEntity.getProcessInstanceId();// 获得流程实例ID
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if(null != processInstance){
            throwLeasePayApplyEntity.setApprovalStatus("2"); //未通过
            this.update(throwLeasePayApplyEntity);
            //在操作表里插入取消申请操作
            ApprovalBaseEntity approvalBaseEntity = getApprovalInfo(throwLeasePayApplyEntity);
            approvalBaseEntity.setApprovalStatus("2");
            approvalBaseManager.create(approvalBaseEntity);
            runtimeService.deleteProcessInstance(processInstanceId,"取消申请");
        }
        return throwLeasePayApplyEntity.getApplyCode();
    }

    public ApprovalBaseEntity removeBase(ApprovalBaseEntity approvalBaseEntity){
        ApprovalBaseEntity result = new ApprovalBaseEntity();
        result.setApplyCode(approvalBaseEntity.getApplyCode());
        result.setTaskId(approvalBaseEntity.getTaskId());
        result.setExecutionId(approvalBaseEntity.getExecutionId());
        result.setProcessInstanceId(approvalBaseEntity.getProcessInstanceId());
        result.setProcessDefinitionId(approvalBaseEntity.getProcessDefinitionId());
        result.setProcessDefinitionKey(approvalBaseEntity.getProcessDefinitionKey());
        result.setApprovalStatus(approvalBaseEntity.getApprovalStatus());
        result.setApproveComment(approvalBaseEntity.getApproveComment());
        result.setId(approvalBaseEntity.getId());
        return result;
    }

    /**
     *@Author gengtao
     *@Date 2019-04-15 14:26:52
     *@Description 获取部门
     *@Return
     **/
    public List<ThrowLeasePayApplyEntity> selectDept(Map<String,Object> map){
        return throwLeasePayApplyDbDao.selectDept(map);
    }
}
