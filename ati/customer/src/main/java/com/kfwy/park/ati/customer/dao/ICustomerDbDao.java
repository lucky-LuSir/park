package com.kfwy.park.ati.customer.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.entity.CustomerEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Description 客户管理
 * @Auth luming
 * @Date 2018/6/14 21:19
 * @Version 1.0
 * @Param
 * @return
 */
public interface ICustomerDbDao extends IMyBatisBaseDao<CustomerEntity,Long> {

    public static final String SELECT_CUS_COUNT = "selectCusCount";
    public static final String SELECT_AGENT = "selectAgent";
    public static final String SELECT_KFWY = "selectKfwy";
    public static final String ANALYSIS_BY_PK = "analysisByPk";
    public static final String ANALYSIS_BY_TRADE = "analysisByTrade";
    public static final String SELECT_STATUS = "selectStatus";

    /**
     * @Description 查询客户渠道来源报表
     * @Auth luming
     * @Date 2018/10/31 15:49
     * @Version 1.0
     * @Param
     * @return
     */
    List<CustomerEntity> queryDitchCount(Map<String, Object> map);

    /**
     * @Description 查询每个职员招到的客户数
     * @Auth gengtao
     * @Date 2019/3/11 14:12
     * @Version 1.0
     * @param map
     * @return
     */
    List<CustomerEntity> selectEmp(Map<String,Object> map);


    /**
     * @Description 主动获客，排除ditch值为7和10
     * @Auth luming
     * @Date 2018/12/5 15:22
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectCusCount(String empCode, Date startDate, Date endDate);

    /**
     * @Description 查询中介渠道的获客数
     * @Auth luming
     * @Date 2018/12/5 15:23
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectAgent(String empCode, Date startDate, Date endDate);

    /**
     * @Description 查询库房无忧的获客数
     * @Auth luming
     * @Date 2018/12/5 15:24
     * @Version 1.0
     * @Param
     * @return
     */
    Integer selectKfwy(String empCode, Date startDate, Date endDate);

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:52:26
     *@Description 统计各园区客户数
     *@Return
     **/
    List<CustomerEntity> analysisByPk(Map<String, Object> map);

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:52:26
     *@Description 统计各行业客户数
     *@Return
     **/
    List<CustomerEntity> analysisByTrade(Map<String, Object> map);

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:53:06
     *@Description 获取状态
     *@Return
     **/
    List<CustomerEntity> selectStatus(Map<String, Object> map);
}
