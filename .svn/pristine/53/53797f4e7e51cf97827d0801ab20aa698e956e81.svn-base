package com.kfwy.park.ati.customer.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.entity.CustomerFollowupEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/5/30 19:10
 */
public interface ICustomerFollowupDbDao extends IMyBatisBaseDao<CustomerFollowupEntity,Long> {

    public static final String SELECT_CUSTOMER_FOLLOWUP = "selectFollowup";

    public static final String SELECT_PK = "selectPk";

    public static final String SELECT_PK_CUS_COUNT= "selectPkByCusCount";

    /**
     * 查询一条跟进的详细信息
     * @param
     * @return
     */
    /*public List<CustomerFollowupEntity> selectOneFollowup(String followupCode);*/

    /**
     * @Description 看板：我的数据，查询本月意向客户
     * @Auth luming
     * @Date 2018/8/14 15:44
     * @Version 1.0
     * @Param
     * @return
     */
    int queryThisMonthFollowup(Map<String, Object> map);

    /**
     * @Description 查询招商人员的跟进数量
     * @Auth luming
     * @Date 2018/12/5 14:55
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectFollowup(String empCode, Date startDate, Date endDate);

    /**
     ** @Description 查询跟进的园区
     * @Auth gengtao
     * @Date 2019/3/14 11:29
     */
    List<CustomerFollowupEntity> selectPk(Map<String,Object>map);

    /**
     ** @Description 查询每个园区的每个人跟进次数
     * @Auth gengtao
     * @Date 2019/3/14 11:30
     */
    List<CustomerFollowupEntity> selectPkByCusCount(Map<String,Object>map);
}
