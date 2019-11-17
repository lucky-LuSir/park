package com.kfwy.park.task.attract.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.task.attract.entity.TaskEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 招商日常任务
 * @Auth xiongzhan
 * @Date 2018/8/3 22:50
 * @Version 1.0
 */
public interface ITodayTaskDbDao extends IMyBatisBaseDao<TaskEntity,Long>{

    //首页招商人员统计数据查询
    public static final String SELECT_GROUP_BY_EMP_CODE = "selectGroupByEmpCode";

    /**
     * @Description 首页招商人员统计数据查询
     * @author wangchuang
     * @Date 2018/8/31 15:10
     * @Version 1.0
     * @Param [param]
     * @return java.util.List<com.kfwy.park.task.attract.entity.TaskEntity>
     **/
    List<TaskEntity> selectGroupByEmpCode(Map<String,Object> param);

    /**
     * @Description 查询招商人员，本月日常任务
     * @Auth luming
     * @Date 2018/12/3 17:05
     * @Version 1.0
     * @Param
     * @return
     */
    TaskEntity selectTask(String empCode, Date startDate, Date endDate);
}





