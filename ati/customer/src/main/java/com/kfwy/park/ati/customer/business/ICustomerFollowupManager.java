package com.kfwy.park.ati.customer.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.ati.customer.entity.CustomerFollowupEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/5/30 19:16
 */
public interface ICustomerFollowupManager extends IManager<CustomerFollowupEntity> {

    /**
     * 非物理删除跟进信息
     * @param entity
     * @return
     */
    int followupDelete(CustomerFollowupEntity entity) throws Exception;

    /**
     * 修改跟进信息
     * @param entity
     * @return
     */
    int followupUpdate(CustomerFollowupEntity entity) throws Exception;

    /**
     * 查询一条跟进的详细信息
     * @param followupCode
     * @return
     */
    /*List<CustomerFollowupEntity> selectOneFollowup(String followupCode);*/


    /**
     * @Description 看板：我的数据，查询本月意向客户
     * @Auth luming
     * @Date 2018/8/14 15:44
     * @Version 1.0
     * @Param
     * @return
     */
    int queryThisMonthFollowup(Map<String, Object> map) throws Exception;

    /**
     * @Description 查询招商人员的跟进数量
     * @Auth luming
     * @Date 2018/12/5 15:08
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectFollowup(String empCode, Date startDate, Date endDate);

    /**
     ** @Description 查询每个园区的每个人跟进次数统计
     * @Auth gengtao
     * @Date 2019/3/14 11:30
     */
    List<Map<String,Object>> selectPkByCusFolCount(Map<String,Object>ma,Date startDate, Date endDatep);
}
