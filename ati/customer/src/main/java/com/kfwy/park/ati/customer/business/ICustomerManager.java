package com.kfwy.park.ati.customer.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.park.ati.customer.entity.CustomerEntity;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 * Update by luming on 2018/06/06
 */
public interface ICustomerManager extends IManager<CustomerEntity>{
    public static final String QUERY_EMP_COUNT = "selectEmp";


    /**
     * @Description 根据cusCode查询客户详情
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    CustomerEntity details(Map<String,Object> map) throws Exception;


    /**
    * @Description 描述:根据phone查询客户是否存在
    * @Auth xpp
    * @Date 2018/8/25 14:40
    * @Version 1.0
    * @Param [mainContactPhone]
    * @return int
    **/
    int findOneByPhone(String mainContactPhone);

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
    PageResult<CustomerEntity> queryEmpCount(Map<String, Object> map,int start, int pageSize);

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
     *@Date 2019-05-20 09:14:13
     *@Description 根据园区统计客户数量
     *@Return
     **/
    List<CustomerEntity> analysisByPk(Map<String, Object> map);

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:14:13
     *@Description 根据行业统计客户数量
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
