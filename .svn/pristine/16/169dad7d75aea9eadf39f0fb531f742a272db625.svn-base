package com.kfwy.park.bi.report.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.report.entity.MerchantsBiEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 招商人员任务、去化数据
 * @Auth luming
 * @Date 2018/12/3 16:06
 * @Version 1.0
 * @Param
 * @return
 */
public interface IMerchantsBiDbDao extends IMyBatisBaseDao<MerchantsBiEntity, Long> {

    /**
     * @Description 查询主动获客
     * @Auth luming
     * @Date 2018/12/3 17:57
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectCusCount(String empCode, Date startDate, Date endDate);

    /**
     * @Description 查询中介
     * @Auth luming
     * @Date 2018/12/3 17:58
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectAgent(String empCode, Date startDate, Date endDate);

    /**
     * @Description 查询库房无忧
     * @Auth luming
     * @Date 2018/12/3 17:59
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectKfwy(String empCode, Date startDate, Date endDate);

    /**
     * @Description 查询本月 获客数
     * @Auth luming
     * @Date 2019/1/15 15:24
     * @Version 1.0
     * @Param
     * @return
     */
    Integer getCustCount(String empCode, Date firstDate, Date endDate);



    /*--------------------------------------------- 查询跟进数据 ---------------------------------------------*/
    /**
     * @Description 查询电话跟进
     * @Auth luming
     * @Date 2018/12/3 18:55
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectPhoneFollowup(String empCode, Date startDate, Date endDate);

    /**
     * @Description 查询客户来访
     * @Auth luming
     * @Date 2019/1/15 13:39
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectVisityFollowup(String empCode, Date startDate, Date endDate);



    /*--------------------------------------------- 谈判、入驻、去化面积 ---------------------------------------------*/
    /**
     * @Description 获取一个招商人员，谈判中的客户，查询所有
     * @Auth luming
     * @Date 2019/1/15 15:40
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectCusNegotiateCount(String empCode, Date startDate, Date endDate);

    /**
     * @Description 获取一个招商人员,本月入驻客户
     * @Auth luming
     * @Date 2019/1/15 15:41
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectCusEntryCount(String empCode, Date startDate, Date endDate);

    /**
     * @Description 查询一个员工,本月去化面积
     * @Auth luming
     * @Date 2019/1/15 15:41
     * @Version 1.0
     * @Param
     * @return
     */
    BigDecimal selectSignAreaMonth(String empCode, Date startDate, Date endDate);

    /**
     * @Description 查询招商人员，本月横幅数量
     * @Auth luming
     * @Date 2018/12/3 17:05
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectTaskBanner(String empCode, Date startDate, Date endDate);

    /**
     * @Description 查询招商人员，本月宣传单数量
     * @Auth luming
     * @Date 2019/1/22 8:45
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectTaskLeaflets(String empCode, Date startDate, Date endDate);

    /**
     * @Description 查询招商人员，本月朋友圈数量
     * @Auth luming
     * @Date 2019/1/22 8:45
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectTaskFriendShare(String empCode, Date startDate, Date endDate);
}
