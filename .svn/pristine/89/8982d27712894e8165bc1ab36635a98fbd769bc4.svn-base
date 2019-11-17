package com.kfwy.park.task.attract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.task.attract.dao.ITaskTargetDbDao;
import com.kfwy.park.task.attract.entity.TaskTargetEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 招商任务指标
 * @Auth luming
 * @Date 2019/1/21 13:56
 * @Version 1.0
 * @Param
 * @return
 */
@Repository
public class TaskTargetDbDaoImpl extends AbstractMyBatisDao<TaskTargetEntity, Long> implements ITaskTargetDbDao {

    /**
     * @Description 查询渠道跟进
     * @Auth luming
     * @Date 2019/1/21 17:42
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectChannelFollowupCount(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode", empCode);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectChannelFollowupCount", params);
    }
}
