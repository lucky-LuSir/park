package com.kfwy.park.act.activiti.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.entity.BaseEntity;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.act.activiti.business.ILandlordRentApplyManager;
import com.kfwy.park.act.activiti.dao.IApprovalBaseDbDao;
import com.kfwy.park.act.activiti.entity.ApprovalBaseEntity;
import com.kfwy.park.common.utils.ActivitiConstant;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.exp.contract.dao.ILandlordContractDbDao;
import com.kfwy.park.exp.contract.dao.ILandlordRentDao;
import com.kfwy.park.exp.contract.entity.LandlordContractEntity;
import com.kfwy.park.exp.contract.entity.LandlordRentEntity;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
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

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gengtao on 2019/4/19.
 */
@Transactional
@Service
public class LandlordRentApplyManagerImpl extends AbstractManager<LandlordContractEntity> implements ILandlordRentApplyManager {
    @Autowired
    private ILandlordContractDbDao landlordContractDbDao;
    @Autowired
    private ILandlordRentDao landlordRentDao;
    @Autowired
    private IApprovalBaseDbDao approvalBaseDbDao;
    @Autowired
    private IRoleManager roleManager;
    @Autowired
    private IUserRoleManager userRoleManager;
    @Autowired
    private IParkManager parkManager;

    @Override
    protected IMyBatisBaseDao<LandlordContractEntity, Long> getMyBatisDao() {
        return this.landlordContractDbDao;
    }

    /**
     * @Author gengtao
     * @Date 2019-04-19 11:50:20
     * @Description 创建申请
     * @Return
     **/
    @Override
    public void createApply(LandlordContractEntity entity, RepositoryService repositoryService, TaskService taskService, RuntimeService runtimeService) throws IllegalAccessException {
        //创建流程
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
        processDefinitionQuery.processDefinitionKey(ActivitiConstant.KFWY_LANDLORD_APPLY_KEY).latestVersion();
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult(); //获取最新版本的流程
        if (processDefinition == null) {
            throw new BusinessException("该流程定义不存在！");
        }

        String attractInvestment = approvesRoleList(ActivitiConstant.KFWY_PARK_APPROVAL); //职能招商
        String accountancy = approvesRoleList(ActivitiConstant.KFWY_FINANCE_APPROVAL); //财务
        Map<String, Object> variables = new HashMap<>();
        variables.put("applicantCode", CurrentContext.getUserCode()); //申请人
        variables.put("attractInvestment", attractInvestment); //职能招商审批
        variables.put("accountancy", accountancy); //财务审批

        // 启动流程实例
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(ActivitiConstant.KFWY_LANDLORD_APPLY_KEY, variables);

        String ldCode = CodeGenUtils.generate();// 大房东合同code

        //办理第一个任务(提交申请)
        TaskQuery taskQuery = taskService.createTaskQuery().taskAssignee(CurrentContext.getUserCode()).processInstanceId(processInstance.getId());
        Task task = taskQuery.singleResult();
        String id = task.getId();
        Map<String, Object> taskVariables = new HashMap<>();
        taskVariables.put("processDefinitionKey", processInstance.getProcessDefinitionKey());
        taskVariables.put("ldCode", ldCode);
        taskService.complete(id, taskVariables);

        if(null != entity.getId()){ //id不为null，表示为重新申请
            this.updateStatus(entity.getId(),entity.getLdCode(),"9");
        }else{  //首次申请新建idCtCode,重新申请时使用原ldCtCode
            String ldCtName = autoCtName(entity);
            entity.setLdCtName(ldCtName); //注释掉，表示使用原先的ldCtName
        }

        entity.setLdCode(ldCode);
        entity.setProcessDefinitionId(processDefinition.getId());
        entity.setProcessInstanceId(processInstance.getProcessInstanceId());
        entity.setLdCtStatus("2");
        clearBaseEntity(entity);
        this.create(entity);




        // 批量新增合同租金
        createRntInfo(entity);
    }

    protected void updateStatus(Long id, String ldCode, String status){
        LandlordContractEntity entity = new LandlordContractEntity();
        entity.setId(id);
        entity.setLdCode(ldCode);
        entity.setLdCtStatus(status);
        landlordContractDbDao.update(entity);
    }


    /**
     * @Author gengtao
     * @Date 2019-04-19 11:49:56
     * @Description 待我审批
     * @Return
     **/
    @Override
    public void selectWaitApprovalList(List<Task> taskList, List<LandlordContractEntity> entities, TaskService taskService) {
        for (Task task : taskList) {
            String taskId = task.getId();

            String processDefinitionKey = (String) taskService.getVariable(taskId, "processDefinitionKey");

            //获取合同信息
            String ldCode = (String) taskService.getVariable(taskId, "ldCode");
            Map<String, Object> params = new HashMap<>();
            params.put("ldCode", ldCode);
            params.put("isDeleted", false);
            params.put("cpyCode",CurrentContext.getCpyCode());
            LandlordContractEntity entity = landlordContractDbDao.selectUniqueByMap(params);
            if (entity == null) {
                throw new BusinessException("查询大房东合同为空");
            }

            //获取租金信息
            List<LandlordRentEntity> landlordRentEntities = landlordRentDao.selectByMap(params);

            entity.setTaskId(taskId);
            entity.setAssignee(task.getAssignee());
            entity.setApproveName(task.getName());
            entity.setExecutionId(task.getExecutionId());
            entity.setProcessInstanceId(task.getProcessInstanceId());
            entity.setProcessDefinitionId(task.getProcessDefinitionId());
            entity.setTaskCreateTime(task.getCreateTime());
            entity.setProcessDefinitionKey(processDefinitionKey);
            entity.setLandlordRentEntityList(landlordRentEntities);

            entities.add(entity);
        }

    }

    /**
     * @Author gengtao
     * @Date 2019-04-19 13:56:07
     * @Description 办理任务
     * @Return
     **/
    @Override
    public void verify(LandlordContractEntity landlordContractEntity, String approveReason, Boolean verify, TaskService taskService, RuntimeService runtimeService) {
        String taskId = landlordContractEntity.getTaskId();
        String processInstanceId = landlordContractEntity.getProcessInstanceId();// 获得流程实例ID

        // 办理任务
        Map<String, Object> variables = new HashMap<>();
        variables.put("verify", verify);
        taskService.complete(taskId, variables);

        //插入办理记录
        ApprovalBaseEntity approvalBaseEntity = getApprovalInfo(landlordContractEntity);
        approvalBaseEntity.setApprovalStatus((verify == false) ? "4" : "5");
        approvalBaseEntity.setApproveComment(approveReason);
        approvalBaseDbDao.insert(approvalBaseEntity);

        // 查询当前流程实例是否存在
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

        if (verify) {
            if (processInstance == null) {
                landlordContractEntity.setLdCtStatus("4");// 二审通过
            }else{
                landlordContractEntity.setLdCtStatus("3"); //一审通过
            }

        } else {
            landlordContractEntity.setLdCtStatus("5");// 合同中状态改为驳回
        }
        this.update(landlordContractEntity);
    }

    /**
     * @Author gengtao
     * @Date 2019-04-19 13:56:18
     * @Description 取消任务
     * @Return
     **/
    @Override
    public void cancelApply(LandlordContractEntity landlordContractEntity, RuntimeService runtimeService) {
        //获取流程
        String processInstanceId = landlordContractEntity.getProcessInstanceId();// 获得流程实例ID
        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
        if (null != processInstance) {
            landlordContractEntity.setLdCtStatus("6");
            this.update(landlordContractEntity);

            //添加取消申请操作
            ApprovalBaseEntity approvalBaseEntity = getApprovalInfo(landlordContractEntity);
            approvalBaseEntity.setApprovalStatus("2");
            approvalBaseEntity.setTaskId("");
            approvalBaseDbDao.insert(approvalBaseEntity);
            runtimeService.deleteProcessInstance(processInstanceId, "取消申请");
        }
    }

    /**
     *@Author gengtao
     *@Date 2019-04-25 11:01:18
     *@Description 调整申请
     *@Return
     **/
    @Override
    public void adjustApply(LandlordContractEntity landlordContractEntity,  TaskService taskService, RuntimeService runtimeService) {
        //执行任务
        Map<String, Object> variables = new HashMap<>();
        variables.put("verify", true);
        String taskId = landlordContractEntity.getTaskId();
        taskService.complete(taskId, variables);
        landlordContractEntity.setLdCtStatus("2");
        this.update(landlordContractEntity);

        //删除旧的租金信息
        Map<String,Object> rentMap = new HashMap<>();
        rentMap.put("isDeleted",false);
        rentMap.put("ldCode",landlordContractEntity.getLdCode());
        rentMap.put("cpyCode",CurrentContext.getCpyCode());
        List<LandlordRentEntity> landlordRentEntities = landlordRentDao.selectByMap(rentMap);
        if(landlordRentEntities.size()>0){
            List<LandlordRentEntity> rents = new ArrayList<>();
            for(LandlordRentEntity rent : landlordRentEntities){
                rent.setIsDeleted(true);
                rents.add(rent);
            }
            landlordRentDao.batchUpdate(rents);
        }
        //新增租金信息
        createRntInfo(landlordContractEntity);
    }


    @Override
    public ApprovalBaseEntity getApprovalInfo(LandlordContractEntity landlordContractEntity) {
        if (landlordContractEntity == null) {
            throw new BusinessException("新增审批信息时传入退租协议信息为空");
        }
        // 封装数据到审批表，记录当前流程每个审批节点
        ApprovalBaseEntity approvalBaseEntity = new ApprovalBaseEntity();
        approvalBaseEntity.setApprovalCode(CodeGenUtils.generate());
        approvalBaseEntity.setApplyCode(landlordContractEntity.getLdCode());
        approvalBaseEntity.setApplicantCode(landlordContractEntity.getCreateCode());
        approvalBaseEntity.setApplicantName(landlordContractEntity.getCreateName());
        approvalBaseEntity.setTaskId(landlordContractEntity.getTaskId());
        approvalBaseEntity.setExecutionId(landlordContractEntity.getExecutionId());
        approvalBaseEntity.setProcessInstanceId(landlordContractEntity.getProcessInstanceId());
        approvalBaseEntity.setProcessDefinitionId(landlordContractEntity.getProcessDefinitionId());
        approvalBaseEntity.setProcessDefinitionKey(landlordContractEntity.getProcessDefinitionKey());
        OperateFillUtils.fill(approvalBaseEntity);
        return approvalBaseEntity;
    }

    @Override
    public List<LandlordContractEntity> selectDept(Map<String, Object> map) {
        return landlordContractDbDao.selectDept(map);
    }

    public  String approvesRoleList(String roleName) {
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
     *@Author gengtao
     *@Date 2019-04-25 11:11:31
     *@Description 批量新增合同租金
     *@Return
     **/
    public void createRntInfo(LandlordContractEntity entity){
        List<LandlordRentEntity> landlordRentEntityList = entity.getLandlordRentEntityList();
        if (landlordRentEntityList != null) {
            for (LandlordRentEntity landlordRentEntity : landlordRentEntityList) {
                clearBaseEntity(landlordRentEntity);
                OperateFillUtils.fill(landlordRentEntity);
                landlordRentEntity.setRentCode(CodeGenUtils.generate());
                landlordRentEntity.setLdCode(entity.getLdCode());
                landlordRentEntity.setPayStatus("1");
                landlordRentEntity.setDiscountsPrice(new BigDecimal("0"));
                landlordRentEntity.setPaidPrice(new BigDecimal("0"));
            }
            landlordRentDao.batchInsert(entity.getLandlordRentEntityList());
        }
    }

    /**
     * @return void
     * @Description 自动生成合同编号
     * @author wangchuang
     * @Date 2018/9/26 18:40
     * @Version 1.0
     * @Param [contractEntity]
     **/
    private String  autoCtName(LandlordContractEntity contractEntity) {
        //生成编号例：0001(园区id)201809(合同开始年月)0001(当月自增长编号)  0001 201809 0001

        //1、根据合同开始日期查询该日期下有多少合同
        Date contractStartTime = contractEntity.getContractStartDate();

        Map<String, Object> param = new HashMap<String, Object>();
        Date monthBegin = DateFormatUtil.monthBegin(contractStartTime);
        Date monthEnd = DateFormatUtil.monthEnd(contractStartTime);

        param.put("contractStartDateStart", monthBegin);
        param.put("contractStartDateEnd", monthEnd);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<LandlordContractEntity> resList = this.findByMap(param);
        int ctNum = resList.size() + 1;

        //2、第3部分---自增编号
        String lastNum = String.format("%04d", ctNum);

        //3、第2部分---合同开始时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String conStartTimeStr = sdf.format(contractStartTime);

        //4、第1部分---园区id
        Map<String, Object> parkParam = new HashMap<String, Object>();
        parkParam.put("pkCode", contractEntity.getPkCode());
        parkParam.put("isDeleted", false);

        ParkEntity parkEntity = parkManager.findOne(parkParam);
        long parkId = parkEntity.getId();

        String firstId = String.format("%04d", parkId);

        //5、拼接一起
        String ldCtName = "L"+firstId + conStartTimeStr + lastNum;

        return ldCtName;

    }

    private BaseEntity clearBaseEntity(BaseEntity entity){
        entity.setCreateCode(null);
        entity.setCreateName(null);
        entity.setCreateDeptCode(null);
        entity.setCreateDeptName(null);
        entity.setCpyCode(null);
        entity.setCpyName(null);
        entity.setOwnerCode(null);
        entity.setOwnerName(null);
        entity.setOwnerDeptCode(null);
        entity.setOwnerDeptName(null);
        entity.setLastUpdateCode(null);
        entity.setLastUpdateName(null);
        entity.setCreateTime(null);
        entity.setLastUpdateTime(null);
        return entity;
    }

}
