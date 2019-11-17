package com.kfwy.park.bi.report.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.bi.report.dao.IMerchantsBiDbDao;
import com.kfwy.park.bi.report.entity.MerchantsBiEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 招商人员任务、去化数据
 * @Auth luming
 * @Date 2018/12/3 16:07
 * @Version 1.0
 * @Param
 * @return
 */
@Repository
public class MerchantsBiDbDaoImpl extends AbstractMyBatisDao<MerchantsBiEntity, Long> implements IMerchantsBiDbDao {

    /*--------------------------------------------- 查询跟进数据 ---------------------------------------------*/
    /**
     * @Description 查询跟进
     * @Auth luming
     * @Date 2018/12/3 18:55
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectPhoneFollowup(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode",empCode);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectPhoneFollowup", params);
    }

    /**
     * @Description 查询客户来访
     * @Auth luming
     * @Date 2019/1/15 13:40
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectVisityFollowup(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode",empCode);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectVisityFollowup", params);
    }


    /*--------------------------------------------- 查询渠道获客数据 ---------------------------------------------*/
    /**
     * @Description 查询主动获客
     * @Auth luming
     * @Date 2018/12/3 17:57
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectCusCount(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode", empCode);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectCusCount", params);
    }

    /**
     * @Description 查询中介数据
     * @Auth luming
     * @Date 2018/12/3 18:51
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectAgent(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("empCode",empCode);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectAgent", params);
    }

    /**
     * @Description 查询库房无忧数据
     * @Auth luming
     * @Date 2018/12/3 18:52
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectKfwy(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode",empCode);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectKfwy", params);
    }

    /**
     * @Description 查询本月 获客数
     * @Auth luming
     * @Date 2019/1/15 15:25
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer getCustCount(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode", empCode);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectCustomerCount", params);
    }



    /*--------------------------------------------- 谈判、入驻、去化面积 ---------------------------------------------*/
    /**
     * @Description 获取一个招商人员，谈判中的客户，查询所有
     * @Auth luming
     * @Date 2019/1/15 15:40
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectCusNegotiateCount(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode", empCode);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectCusNegotiateCount", params);
    }

    /**
     * @Description 获取一个招商人员,本月入驻客户
     * @Auth luming
     * @Date 2019/1/15 15:41
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectCusEntryCount(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode", empCode);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectCusEntryCount", params);
    }

    /**
     * @Description 查询一个员工,本月去化面积
     * @Auth luming
     * @Date 2019/1/15 15:41
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public BigDecimal selectSignAreaMonth(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode", empCode);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectSignAreaMonth", params);
    }


    /**
     * @Description 查询招商人员，本月横幅数量
     * @Auth luming
     * @Date 2018/12/3 17:05
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectTaskBanner(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode", empCode);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectTaskBanner", params);
    }

    /**
     * @Description 查询招商人员，本月宣传单数量
     * @Auth luming
     * @Date 2019/1/22 8:45
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectTaskLeaflets(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode", empCode);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectTaskLeaflets", params);
    }

    /**
     * @Description 查询招商人员，本月朋友圈数量
     * @Auth luming
     * @Date 2019/1/22 8:45
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectTaskFriendShare(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode", empCode);
        params.put("startDate", startDate);
        params.put("endDate", endDate);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectTaskFriendShare", params);
    }
}
