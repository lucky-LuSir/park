package com.kfwy.park.act.activiti.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.act.activiti.entity.ApprovalBaseEntity;
import com.kfwy.park.act.activiti.entity.ThrowLeaseApplyEntity;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * Created by luming on 2019/3/13.
 * 退租申请工作流
 */
public interface IThrowLeaseApplyManager extends IManager<ThrowLeaseApplyEntity> {

    /**
     * @Description 校验必填字段
     * @author luming
     * @Date 2019/3/20 16:35
     * @Version 1.0
     * @Param [throwLeaseApplyEntity]
     * @return void
     */
    void checkParams(ThrowLeaseApplyEntity throwLeaseApplyEntity) throws IllegalAccessException;

    /**
     * @Description 查询待我审批的任务，包含个人任务和公共任务
     * @author luming
     * @Date 2019/3/22 9:11
     * @Version 1.0
     * @Param []
     * @return java.util.List<com.kfwy.park.act.activiti.entity.ThrowLeaseApplyEntity>
     */
    void selectWaitApprovalList(List<Task> taskList, List<ThrowLeaseApplyEntity> throwLeaseApplyEntityList, TaskService taskService);

    /**
     * @Description 新增审批信息
     * @author luming
     * @Date 2019/4/1 18:26
     * @Version 1.0
     * @Param [throwLeaseApplyEntity]
     * @return com.kfwy.park.act.activiti.entity.ApprovalBaseEntity
     */
    ApprovalBaseEntity getApprovalInfo(ThrowLeaseApplyEntity throwLeaseApplyEntity);

    /**
     * @Description 查询拥有审批权限的人员code集合
     * @author luming
     * @Date 2019/3/27 14:17
     * @Version 1.0
     * @Param [roleName]
     * @return java.lang.String
     */
    String approvesRoleList(String roleName);

    /**
     * @Description 创建退租表申请单
     * @author luming
     * @Date 2019/4/1 18:25
     * @Version 1.0
     * @Param [throwLeaseApplyEntity, repositoryService, taskService, runtimeService]
     * @return void
     */
    void createApply(ThrowLeaseApplyEntity throwLeaseApplyEntity, RepositoryService repositoryService, TaskService taskService, RuntimeService runtimeService) throws IllegalAccessException;

    /**
     * @Description 拾取公共任务
     * @author luming
     * @Date 2019/4/2 9:04
     * @Version 1.0
     * @Param [throwLeaseApplyEntity, taskService]
     * @return void
     */
    void getPublicTask(ThrowLeaseApplyEntity throwLeaseApplyEntity, TaskService taskService);

    /**
     * @Description 取消拾取公共任务
     * @author luming
     * @Date 2019/4/2 9:05
     * @Version 1.0
     * @Param [throwLeaseApplyEntity, taskService]
     * @return void
     */
    void backPublicTask(ThrowLeaseApplyEntity throwLeaseApplyEntity, TaskService taskService);

    /**
     * @Description 审核人员办理任务
     * @author luming
     * @Date 2019/4/2 8:34
     * @Version 1.0
     * @Param [throwLeaseApplyEntity, approveReason, reapply, taskService, runtimeService]
     * @return void
     */
    void verify(ThrowLeaseApplyEntity throwLeaseApplyEntity, String approveReason, Boolean reapply,  TaskService taskService, RuntimeService runtimeService);

    /**
     * @Description 重新提交
     * @author luming
     * @Date 2019/4/2 8:43
     * @Version 1.0
     * @Param [throwLeaseApplyEntity, approveReason, reapply, taskService, runtimeService]
     * @return void
     */
    void applicantResubmit(ThrowLeaseApplyEntity throwLeaseApplyEntity, String approveReason, Boolean reapply, TaskService taskService, RuntimeService runtimeService) throws IllegalAccessException;

    /**
     *@Author gengtao
     *@Date 2019-04-15 14:16:59
     *@Description 获取部门
     *@Return
     **/
    List<ThrowLeaseApplyEntity> selectDept(Map<String,Object> map);

    /**
     *@author gtaotao
     *@Date 2019-08-15 17:06:56
     *@Description 获取6个月营收,根据合同时间获取退租信息
     *@param
     *@return
     **/
    List<ThrowLeaseApplyEntity> selectByContractTime(Map<String, Object>map);
}
