package com.kfwy.park.ati.customer.dao.impl;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.customer.dao.ICustomerFollowupDbDao;
import com.kfwy.park.ati.customer.entity.CustomerFollowupEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/5/30 19:12
 */
@Repository
public class CustomerFollowupDbDaoImpl extends AbstractMyBatisDao<CustomerFollowupEntity,Long> implements ICustomerFollowupDbDao {

    /**
     * 查询一条跟进的详细信息
     * @param followupCode
     * @return
     */
    /*@Override
    public List<CustomerFollowupEntity> selectOneFollowup(String followupCode) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectOneFollowup",followupCode);
    }*/

    /**
     * @Description 看板：我的数据，查询本月意向客户
     * @Auth luming
     * @Date 2018/8/14 15:45
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int queryThisMonthFollowup(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "thisMonthFollowup", map);
    }

    /**
     * @Description 查询招商人员的跟进数量
     * @Auth luming
     * @Date 2018/12/5 14:57
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectFollowup(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode",empCode);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        params.put("isDeleted", false);
        params.put("cpyCode", CurrentContext.getCpyCode());
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_CUSTOMER_FOLLOWUP, params);
    }

    /**
     ** @Description 查询跟进的园区
     * @Auth gengtao
     * @Date 2019/3/14 11:29
     */
    @Override
    public List<CustomerFollowupEntity> selectPk(Map<String,Object>map){
        return this.getSqlSession().selectList(this.mapperNamespace+SELECT_PK,map);
    }

    /**
     ** @Description 查询每个园区的每个人跟进次数
     * @Auth gengtao
     * @Date 2019/3/14 11:30
     */
    @Override
    public List<CustomerFollowupEntity> selectPkByCusCount(Map<String,Object>map){
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_PK_CUS_COUNT, map);
    }
}
