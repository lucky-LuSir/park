package com.kfwy.park.act.activiti.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.act.activiti.entity.ApprovalBaseEntity;
import com.kfwy.park.exp.contract.entity.LandlordContractEntity;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;
import java.util.Map;

/**
 * Created by gengtao on 2019/4/19.
 */
public interface ILandlordRentApplyManager extends IManager<LandlordContractEntity> {
    /**
     *@Author gengtao
     *@Date 2019-04-19 11:48:59
     *@Description 创建合同
     *@Return
     **/
    void createApply(LandlordContractEntity entity, RepositoryService repositoryService, TaskService taskService, RuntimeService runtimeService) throws IllegalAccessException;

    /**
     *@Author gengtao
     *@Date 2019-04-19 11:49:15
     *@Description 查询带我审批
     *@Return
     **/
    void selectWaitApprovalList(List<Task> taskList, List<LandlordContractEntity> entities, TaskService taskService);

    /**
     *@Author gengtao
     *@Date 2019-04-19 13:38:57
     *@Description 办理任务
     *@Return
     **/
    void verify(LandlordContractEntity entity, String approveReason, Boolean verify,  TaskService taskService, RuntimeService runtimeService);

    /**
     *@Author gengtao
     *@Date 2019-04-19 13:55:38
     *@Description 取消任务
     *@Return
     **/
    void cancelApply(LandlordContractEntity throwLeasePayApplyEntity,RuntimeService runtimeService);

    /**
     *@Author gengtao
     *@Date 2019-04-19 15:45:06
     *@Description 调整申请
     *@Return
     **/
    void adjustApply(LandlordContractEntity landlordContractEntity,TaskService taskService,RuntimeService runtimeService);

    /**
     *@Author gengtao
     *@Date 2019-04-25 11:06:00
     *@Description 获取审批信息
     *@Return
     **/
    ApprovalBaseEntity getApprovalInfo(LandlordContractEntity landlordContractEntity);

    /**
     *@Author gengtao
     *@Date 2019-04-23 14:04:31
     *@Description 获取部门
     *@Return
     **/
    List<LandlordContractEntity> selectDept(Map<String,Object> map);
/**
 *@Author gengtao
 *@Date 2019-04-25 11:12:06
 *@Description 批量新增租金信息
 *@Return
 **/
    void createRntInfo(LandlordContractEntity entity);
}
