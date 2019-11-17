package com.kfwy.park.ati.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.customer.dao.ICustomerDbDao;
import com.kfwy.park.ati.customer.entity.CustomerEntity;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashMap;
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
@Repository
public class CustomerDbDaoImpl extends AbstractMyBatisDao<CustomerEntity,Long> implements ICustomerDbDao {

    /**
     * @Description 查询客户渠道来源报表
     * @Auth luming
     * @Date 2018/10/31 15:49
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<CustomerEntity> queryDitchCount(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectDitchCount", map);
    }

    /**
     * @Description 查询每个职员招到的客户数
     * @Auth gengtao
     * @Date 2019/3/11 14:12
     * @Version 1.0
     * @param map
     * @return
     */
    @Override
    public List<CustomerEntity> selectEmp(Map<String,Object> map){
        return  this.getSqlSession().selectList(this.mapperNamespace + "selectEmp",map);
    }



    /**
     * @Description 主动获客，排除ditch值为7和10
     * @Auth luming
     * @Date 2018/12/5 15:22
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectCusCount(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode",empCode);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        params.put("isDeleted", false);
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_CUS_COUNT, params);
    }

    /**
     * @Description 查询中介渠道的获客数
     * @Auth luming
     * @Date 2018/12/5 15:23
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Integer selectAgent(String empCode, Date startDate, Date endDate) {
        Map<String,Object> params = new HashMap<>();
        params.put("empCode",empCode);
        params.put("startDate",startDate);
        params.put("endDate",endDate);
        params.put("isDeleted", false);
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_AGENT, params);
    }

    /**
     * @Description 查询库房无忧的获客数
     * @Auth luming
     * @Date 2018/12/5 15:24
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
        params.put("isDeleted", false);
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_KFWY, params);
    }

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:52:26
     *@Description 统计各园区客户数
     *@Return
     **/
    @Override
    public List<CustomerEntity> analysisByPk(Map<String, Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace + ANALYSIS_BY_PK, map);
    }

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:52:26
     *@Description 统计行业客户数
     *@Return
     **/
    @Override
    public List<CustomerEntity> analysisByTrade(Map<String, Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace + ANALYSIS_BY_TRADE, map);
    }

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:53:06
     *@Description 获取状态
     *@Return
     **/
    @Override
    public List<CustomerEntity> selectStatus(Map<String, Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_STATUS, map);
    }
}
