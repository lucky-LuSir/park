package com.kfwy.park.task.attract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.task.attract.dao.ITodayTaskDbDao;
import com.kfwy.park.task.attract.entity.TaskEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @Auth xiongzhan
 * @Date 2018/8/3 9:57
 * @Version 1.0
 */
@Repository
public class TodayTaskDbDaoImpl extends AbstractMyBatisDao<TaskEntity,Long> implements ITodayTaskDbDao {

    /**
     * @Description 首页招商人员统计数据查询
     * @author wangchuang
     * @Date 2018/8/31 15:11
     * @Version 1.0
     * @Param [param]
     * @return java.util.List<com.kfwy.park.task.attract.entity.TaskEntity>
     **/
    @Override
    public List<TaskEntity> selectGroupByEmpCode(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_GROUP_BY_EMP_CODE, param);
    }

    /**
     * @Description 查询招商人员，本月日常任务
     * @Auth luming
     * @Date 2018/12/4 8:45
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public TaskEntity selectTask(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("empCode",empCode);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        params.put("isDeleted", false);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectTask", params);
    }
}
