package com.kfwy.park.act.activiti.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.act.activiti.business.IThrowLeaseApplyManager;
import com.kfwy.park.act.activiti.business.IThrowLeasePayApplyManager;
import com.kfwy.park.act.activiti.dao.IApprovalBaseDbDao;
import com.kfwy.park.act.activiti.dao.IThrowLeaseApplyDbDao;
import com.kfwy.park.act.activiti.entity.ApprovalBaseEntity;
import com.kfwy.park.act.activiti.entity.ThrowLeaseApplyEntity;
import com.kfwy.park.ati.contract.dao.IContractDbDao;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luming on 2019/3/13.
 * 退租申请工作流
 */
@Transactional
@Service
public class ThrowLeaseApplyManagerImpl extends AbstractManager<ThrowLeaseApplyEntity> implements IThrowLeaseApplyManager {
    @Autowired
    private IThrowLeaseApplyDbDao throwLeaseApplyDbDao;

    @Autowired
    private IApprovalBaseDbDao approvalBaseDbDao;

    @Autowired
    private IRoleManager roleManager;

    @Autowired
    private IUserRoleManager userRoleManager;

    @Autowired
    private IDeptManager deptManager;

    @Autowired
    private IThrowLeasePayApplyManager throwLeasePayApplyManager;

    @Autowired
    private IContractDbDao contractDbDao;



    @Override
    protected IMyBatisBaseDao<ThrowLeaseApplyEntity, Long> getMyBatisDao() {
        return throwLeaseApplyDbDao;
    }

    @Override
    protected void beforeCreate(ThrowLeaseApplyEntity entity) {

        entity.setApplyCode(CodeGenUtils.generate());

        // 提交的新流程状态置为1==>审核中
        entity.setApprovalStatus("1");
    }

    /**
     * @Description 校验必填字段
     * @author luming
     * @Date 2019/3/20 16:35
     * @Version 1.0
     * @Param [throwLeaseApplyEntity]
     * @return void
     */
    @Override
    public void checkParams(ThrowLeaseApplyEntity throwLeaseApplyEntity) throws IllegalAccessException {
        List<String> param = new ArrayList<>();
        param.add("partyA");
        param.add("partyB");
        param.add("throwLeaseDate");
        param.add("ctName");
        param.add("leasedArea");
        param.add("throwLeaseArea");
        param.add("monthlyRent");
        param.add("throwLeaseRemark");
        param.add("refundBank");
        param.add("refundAccountName");
        param.add("refundAccountNumber");
        param.add("partyAAddress");
        param.add("partyALegalRepresentative");
        param.add("partyAPhone");
        param.add("partyBAddress");
        param.add("partyBLegalRepresentative");
        param.add("partyBPhone");

        ParamUtil<ThrowLeaseApplyEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(throwLeaseApplyEntity, param);
    }

    /**
     * @Description 查询待我审批的任务，包含个人任务和公共任务
     * @author luming
     * @Date 2019/3/22 9:12
     * @Version 1.0
     * @Param [publicTaskList]
     * @return java.util.List<com.kfwy.park.act.activiti.entity.ThrowLeaseApplyEntity>
     */
    @Override
    public void selectWaitApprovalList(List<Task> taskList, List<ThrowLeaseApplyEntity> throwLeaseApplyEntityList, TaskService taskService) {
        for (Task task : taskList) {
            String taskId = task.getId();

            String processDefinitionKey = (String) taskService.getVariable(taskId, "processDefinitionKey");

            String applyCode = (String) taskService.getVariable(taskId, "applyCode");
            Map<String, Object> params = new HashMap<>();
            params.put("applyCode", applyCode);
            params.put("isDeleted", false);
            ThrowLeaseApplyEntity throwLeaseApplyEntity = throwLeaseApplyDbDao.selectUniqueByMap(params);

            if(throwLeaseApplyEntity == null) {
                throw new BusinessException("查询申请退租协议为空");
            }

            throwLeaseApplyEntity.setTaskId(taskId);
            throwLeaseApplyEntity.setAssignee(task.getAssignee());
            throwLeaseApplyEntity.setApproveName(task.getName());
            throwLeaseApplyEntity.setExecutionId(task.getExecutionId());
            throwLeaseApplyEntity.setProcessInstanceId(task.getProcessInstanceId());
            throwLeaseApplyEntity.setProcessDefinitionId(task.getProcessDefinitionId());
            throwLeaseApplyEntity.setTaskCreateTime(task.getCreateTime());
            throwLeaseApplyEntity.setProcessDefinitionKey(processDefinitionKey);

            throwLeaseApplyEntityList.add(throwLeaseApplyEntity);
        }
    }

    /**
     * @Description 新增审批信息
     * @author luming
     * @Date 2019/3/25 18:04
     * @Version 1.0
     * @Param [throwLeaseApplyEntity, firstVerify, approveReason]
     * @return void
     */
    @Override
    public ApprovalBaseEntity getApprovalInfo(ThrowLeaseApplyEntity throwLeaseApplyEntity) {
        if(throwLeaseApplyEntity == null) {
            throw new BusinessException("新增审批信息时传入退租协议信息为空");
        }
        // 封装数据到审批表，记录当前流程每个审批节点
        ApprovalBaseEntity approvalBaseEntity = new ApprovalBaseEntity();
        approvalBaseEntity.setApprovalCode(CodeGenUtils.generate());
        approvalBaseEntity.setApplyCode(throwLeaseApplyEntity.getApplyCode());
        approvalBaseEntity.setApplicantCode(throwLeaseApplyEntity.getCreateCode());
        approvalBaseEntity.setApplicantName(throwLeaseApplyEntity.getCreateName());
        approvalBaseEntity.setTaskId(throwLeaseApplyEntity.getTaskId());
        approvalBaseEntity.setExecutionId(throwLeaseApplyEntity.getExecutionId());
        approvalBaseEntity.setProcessInstanceId(throwLeaseApplyEntity.getProcessInstanceId());
        approvalBaseEntity.setProcessDefinitionId(throwLeaseApplyEntity.getProcessDefinitionId());
        approvalBaseEntity.setProcessDefinitionKey(throwLeaseApplyEntity.getProcessDefinitionKey());
        OperateFillUtils.fill(approvalBaseEntity);
        return approvalBaseEntity;
    }

    /**
     * @Description 查询拥有审批权限的人员code集合
     * @author luming
     * @Date 2019/3/27 14:17
     * @Version 1.0
     * @Param [roleName]
     * @return java.lang.String
     */
    @Override
    public String approvesRoleList(String roleName) {
        List<UserRoleEntity> approvesUserRoleList = null;// 有退租单审核的人员集合

        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);
        params.put("cpyCode", CurrentContext.getUserInfo().getCpyCode());
        List<RoleEntity> roleList = roleManager.findByMap(params);

        for (RoleEntity list : roleList) {
            if (roleName.equals(list.getRoleName())) {
                Map<String, Object> map = new HashMap<>();
                map.put("roleCode", list.getRoleCode());
                map.put("isDeleted", false);
                map.put("cpyCode", CurrentContext.getUserInfo().getCpyCode());
                approvesUserRoleList = userRoleManager.findByMap(map);// 查询角色下关联的人员
                break;
            }
        }

        StringBuffer sb = new StringBuffer();
        for (UserRoleEntity list : approvesUserRoleList) {
            sb.append(list.getUserCode());
            sb.append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    /**
     * @Description 创建退租表申请单
     * @author luming
     * @Date 2019/4/1 18:25
     * @Version 1.0
     * @Param [throwLeaseApplyEntity, repositoryService, taskService, runtimeService]
     * @return void
     */
    @Override
    public void createApply(ThrowLeaseApplyEntity throwLeaseApplyEntity, RepositoryService repositoryService, TaskService taskService, RuntimeService runtimeService) throws IllegalAccessException {
        /**
         * 查询流程定义
         */
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        processDefinitionQuery.processDefinitionKey(ActivitiConstant.KFWY_THROW_LEASE_APPLY_KEY);
        processDefinitionQuery.latestVersion();// 最新版本过滤
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();
        if (processDefinition == null) {
            throw new BusinessException("该流程定义不存在！");
        }

        String attractInvestment = approvesRoleList(ActivitiConstant.KFWY_PARK_APPROVAL);///职能招商
        String accountancy = approvesRoleList(ActivitiConstant.KFWY_FINANCE_APPROVAL); //财务

        Map<String, Object> variables = new HashMap<>();
        variables.put("applicantCode", CurrentContext.getUserCode());// 设置变量，当前登陆的用户可发起申请
        List<String> regionManageList = deptManager.selectByUserCode(CurrentContext.getUserCode()); //大区主管
        StringBuffer se = new StringBuffer();
        for(String list : regionManageList){
            se.append(list);
            se.append(",");
        }
        se.deleteCharAt(se.length() - 1);

        variables.put("regionManage", se.toString()); //大区总审批
        variables.put("attractInvestment", attractInvestment); //职能招商审批
        variables.put("accountancy",accountancy); //财务审批

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ActivitiConstant.KFWY_THROW_LEASE_APPLY_KEY, variables);// 根据流程定义Key启动流程，并获取流程实例

        checkParams(throwLeaseApplyEntity);// 校验必填字段

        throwLeaseApplyEntity.setPdId(processDefinition.getId());
        throwLeaseApplyEntity.setProcessInstanceId(processInstance.getProcessInstanceId());
        this.create(throwLeaseApplyEntity);// 填写流程申请表

        // 根据当前登陆用户和流程实例ID查询任务并办理第一个任务（提交申请任务）
        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee(CurrentContext.getUserCode()).processInstanceId(processInstance.getId());
        Task task = taskQuery.singleResult();
        String id = task.getId();

        // 将流程定义的Key存入流程变量中
        Map<String, Object> taskVariables = new HashMap<>();
        taskVariables.put("processDefinitionKey", processInstance.getProcessDefinitionKey());
        taskVariables.put("applyCode", throwLeaseApplyEntity.getApplyCode());
        taskService.complete(id, taskVariables);

        //将合同状态改为退租中
        changeConStatus(throwLeaseApplyEntity.getCtCode(), "9");

    }

    /**
     * @Description 拾取公共任务
     * @author luming
     * @Date 2019/4/2 9:04
     * @Version 1.0
     * @Param [throwLeaseApplyEntity, taskService]
     * @return void
     */
    @Override
    public void getPublicTask(ThrowLeaseApplyEntity throwLeaseApplyEntity, TaskService taskService) {
        String taskId = throwLeaseApplyEntity.getTaskId();
        String userCode = CurrentContext.getUserCode();

        TaskQuery taskQuery = taskService.createTaskQuery().taskId(taskId);
        Task task = taskQuery.singleResult();
        String assignee = task.getAssignee();

        if (!StringUtils.isEmpty(assignee)) {
            throw new BusinessException("该任务已经被处理！");
        } else {
            taskService.claim(taskId, userCode);
        }
    }

    /**
     * @Description 取消拾取公共任务
     * @author luming
     * @Date 2019/4/2 9:06
     * @Version 1.0
     * @Param [throwLeaseApplyEntity, taskService]
     * @return void
     */
    @Override
    public void backPublicTask(ThrowLeaseApplyEntity throwLeaseApplyEntity, TaskService taskService) {
        String taskId = throwLeaseApplyEntity.getTaskId();
        taskService.setAssignee(taskId, null);
    }

    /**
     * @Description 审核人员办理任务
     * @author luming
     * @Date 2019/4/2 8:35
     * @Version 1.0
     * @Param [throwLeaseApplyEntity, approveReason, verify, taskService, runtimeService]
     * @return void
     */
    @Override
    public void verify(ThrowLeaseApplyEntity throwLeaseApplyEntity, String approveReason, Boolean verify,  TaskService taskService, RuntimeService runtimeService) {
        String taskId = throwLeaseApplyEntity.getTaskId();
        String processInstanceId = throwLeaseApplyEntity.getProcessInstanceId();// 获得流程实例ID

        // 办理任务
        Map<String, Object> variables = new HashMap<>();
        variables.put("verify", verify);
        taskService.complete(taskId, variables);

        ApprovalBaseEntity approvalBaseEntity = getApprovalInfo(throwLeaseApplyEntity);
        approvalBaseEntity.setApprovalStatus((verify == false) ? "4" : "5");
        approvalBaseEntity.setApproveComment(approveReason);
        approvalBaseDbDao.insert(approvalBaseEntity); // 插入办理记录

        // 查询当前流程实例是否存在
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        if (verify) {
            if (processInstance == null) {
                throwLeaseApplyEntity.setApprovalStatus("3");// 审批表中状态改为已通过
                this.update(throwLeaseApplyEntity);
                //申请通过后，还原园区面积
                ThrowLeaseApplyEntity entity = this.findOne("applyCode",throwLeaseApplyEntity.getApplyCode());
                throwLeasePayApplyManager.recoverContract(entity);
            }
        } else {
            if (processInstance != null) {
                throwLeaseApplyEntity.setApprovalStatus("4");// 审批表中状态改为驳回
                this.update(throwLeaseApplyEntity);
            }
        }
    }

    /**
     * @Description 重新提交
     * @author luming
     * @Date 2019/4/2 8:43
     * @Version 1.0
     * @Param [throwLeaseApplyEntity, approveReason, reapply, taskService, runtimeService]
     * @return void
     */
    @Override
    public void applicantResubmit(ThrowLeaseApplyEntity throwLeaseApplyEntity, String approveReason, Boolean reapply, TaskService taskService, RuntimeService runtimeService) throws IllegalAccessException {
        String taskId = throwLeaseApplyEntity.getTaskId();
        String processInstanceId = throwLeaseApplyEntity.getProcessInstanceId();// 获得流程实例ID

        Map<String, Object> variables = new HashMap<>();
        variables.put("verify", reapply);

        if (reapply) {
            // 重新提交申请
            checkParams(throwLeaseApplyEntity);// 校验必填字段
            throwLeaseApplyEntity.setApprovalStatus("1");// 状态设为审批中
            //更新退租申请审批状态
            this.update(throwLeaseApplyEntity);
            //执行流程
            taskService.complete(taskId, variables);

        } else {
            // 不重新提交
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            if (processInstance != null) {
                throwLeaseApplyEntity.setApprovalStatus("2");// 状态改为未通过
                this.update(throwLeaseApplyEntity);
                // 在操作表插入一条取消申请操作
                ApprovalBaseEntity approvalBaseEntity = getApprovalInfo(throwLeaseApplyEntity);
                approvalBaseEntity.setApprovalStatus("2");
                approvalBaseDbDao.insert(approvalBaseEntity);

                runtimeService.deleteProcessInstance(processInstanceId, approveReason);
            }

            //取消申请后将申请的合同状态置为二审通过
            changeConStatus(throwLeaseApplyEntity.getCtCode(), "4");

        }
    }

    /**
     *@Author gengtao
     *@Date 2019-04-15 14:18:16
     *@Description 获取部门
     *@Return
     **/
    @Override
    public List<ThrowLeaseApplyEntity> selectDept(Map<String,Object> map){
        return throwLeaseApplyDbDao.selectDept(map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-15 17:06:56
     *@Description 获取6个月营收,根据合同时间获取退租信息
     *@param
     *@return
     **/
    @Override
    public List<ThrowLeaseApplyEntity> selectByContractTime(Map<String, Object> map) {
        return throwLeaseApplyDbDao.selectByContractTime(map);
    }

    /**
     * @Description 把传入ctCode合同的状态改为传入的ctStatus
     * @author wangchuang
     * @Date 2019/6/13 17:17
     * @Version 1.0
     * @Param [ctCode, ctStatus]
     * @return void
     **/
    public void changeConStatus(String ctCode, String ctStatus) {
        if(StringUtils.isEmpty(ctCode) || StringUtils.isEmpty(ctStatus)) {
            throw new BusinessException("退租工作流中修改合同状态传入参数为空");
        }
        Map<String, Object> param = new HashMap<>();
        param.put("ctCode", ctCode);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        ContractEntity contractEntity = contractDbDao.selectUniqueByMap(param);
        if(contractEntity == null || contractEntity.getId() == null) {
            throw new BusinessException("查询要操作的合同数据异常");
        }

        //更改合同状态
        Map<String, Object> map = new HashMap<>();
        map.put("id", contractEntity.getId());
        map.put("ctCode", contractEntity.getCtCode());
        map.put("ctStatus", ctStatus);
        map.put("cpyCode", CurrentContext.getCpyCode());

        contractDbDao.contractVerify(map);
    }
}
