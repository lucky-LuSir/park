package com.kfwy.park.task.attract.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.task.attract.entity.TaskEntity;

import java.util.Date;
import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth xiongzhan
 * @date 2018/8/3 9:43
 */
public interface ITodayTaskManager extends IManager<TaskEntity> {

    void insert(TaskEntity taskEntity);

    List<TaskEntity> select(TaskEntity entity) throws IllegalAccessException;

    /**
     * @Description 查询招商人员，本月日常任务
     * @Auth luming
     * @Date 2018/12/4 8:48
     * @Version 1.0
     * @Param
     * @return
     */
    TaskEntity selectTask(String empCode, Date startDate, Date endDate);
}
