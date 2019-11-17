package com.kfwy.park.act.activiti.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.act.activiti.entity.ApprovalBaseEntity;
import com.kfwy.park.act.activiti.entity.ThrowLeaseApplyEntity;
import com.kfwy.park.act.activiti.entity.ThrowLeasePayApplyEntity;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * Created by kfwy_it_004 on 2019/3/22.
 */
public interface IThrowLeasePayApplyManager extends IManager<ThrowLeasePayApplyEntity> {
    void checkParams(ThrowLeasePayApplyEntity throwLeasePayApplyEntity) throws IllegalAccessException;
    /**
     * @Description 查询待我审批的任务，包含个人任务和公共任务
     * @author gengtao
     * @Date 2019/3/25 14:39
     * @Version 1.0
     * @Param []
     * @return java.util.List<com.kfwy.park.act.activiti.entity.ThrowLeasePayApplyEntity>
     */
    void selectWaitApprovalList(List<Task> taskList, List<ThrowLeasePayApplyEntity> throwLeasePayApplyEntities, TaskService taskService);


    /**
     * @Description 退租申请通过后，还原园区面积
     * @author luming
     * @Date 2019/3/26 13:39
     * @Version 1.0
     * @Param [throwLeaseApplyEntity]
     * @return void
     */
    void recoverContract(ThrowLeaseApplyEntity throwLeaseApplyEntity);

    /**
     *@Author gengtao
     *@Date 2019-03-29 12:21:13
     *@Description 获取付款申请一审二审人员
     *@Return
     **/
    Map<String,String> getApprovalUser();

    /**
     *@Author gengtao
     *@Date 2019-03-29 13:37:39
     *@Description 办理申请
     *@Return
     **/
    String verifyApply(ApprovalBaseEntity approvalBaseEntity, String throwLeaseApplyCode, TaskService taskService, RuntimeService runtimeService);

    /**
     *@Author gengtao
     *@Date 2019-04-01 10:52:31
     *@Description 检测是否重复提交退款申请
     *@Return
     **/
    boolean checkPayApply(String applyCode);

    /**
     *@Author gengtao
     *@Date 2019-04-01 16:47:27
     *@Description 从ThrowLeasePayApplyEntity获取审批信息
     *@Return
     **/
    ApprovalBaseEntity getApprovalInfo(ThrowLeasePayApplyEntity entity);

    /**
     *@Author gengtao
     *@Date 2019-04-02 08:53:19
     *@Description 创建申请
     *@Return
     **/
    String createApply(ThrowLeasePayApplyEntity throwLeasePayApplyEntity,
                       RepositoryService repositoryService,
                       RuntimeService runtimeService,
                       TaskService taskService);


    /**
     *@Author gengtao
     *@Date 2019-04-02 11:54:29
     *@Description 调整申请
     *@Return
     **/
    String adjustApply(ThrowLeasePayApplyEntity throwLeasePayApplyEntity,
                       boolean reapply,
                       String taskId,
                       String processInstanceId,
                       TaskService taskService,
                       RuntimeService runtimeService);

    /**
     *@Author gengtao
     *@Date 2019-04-02 14:42:54
     *@Description 取消申请
     *@Return
     **/
    String cancelApply(ThrowLeasePayApplyEntity throwLeasePayApplyEntity,RuntimeService runtimeService);

    ApprovalBaseEntity removeBase(ApprovalBaseEntity approvalBaseEntity);

    /**
     *@Author gengtao
     *@Date 2019-04-15 14:16:59
     *@Description 获取部门
     *@Return
     **/
    List<ThrowLeasePayApplyEntity> selectDept(Map<String,Object> map);
}
