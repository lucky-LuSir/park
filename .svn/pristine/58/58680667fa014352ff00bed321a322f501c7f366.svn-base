package com.kfwy.park.hrm.org.bi.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.hrm.org.bi.dao.IEmpBiDbDao;
import com.kfwy.park.hrm.org.bi.entity.EmpBiEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description TODO
 * @Auth xpp
 * @Date 2018/8/3
 * @Version 1.0
 */
@Repository
public class EmpBiDbDaoImpl extends AbstractMyBatisDao<EmpBiEntity,Long> implements IEmpBiDbDao {

    /*@Override
    public int selectCusReceiveCount() {
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_CITY);
    }*/

    /**
     * @Description 获取一个招商人员,谈判中的客户,不含时间，查询所有
     * @Auth xpp
     * @Date 2018/10/19 8:50
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int selectCusNegotiateCount(String empCode) {
        EmpBiEntity one = new EmpBiEntity();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("empCode",empCode);
//        param.put("weekOne",weekOne);
//        param.put("weekSunday",weekSunday);
        param.put("isDeleted",Boolean.FALSE);
        int oneInt = 0;
        oneInt  = this.getSqlSession().selectOne(this.mapperNamespace + "selectCusNegotiateCount",param);
        return oneInt;
    }

    /**
     * @Description 获取一个招商人员名字,电话
     * @Auth xpp
     * @Date 2018/10/19 8:51
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public EmpBiEntity selectEmployee(String empCode) {
        EmpBiEntity one = new EmpBiEntity();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("empCode",empCode);
        param.put("isDeleted",Boolean.FALSE);
        one  = this.getSqlSession().selectOne(this.mapperNamespace + "selectEmployee",param);
        return one;
    }

    @Override
    public int selectCusReceiveCount(String empCode, Date weekOne, Date weekSunday) throws ParseException {

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("empCode",empCode);
        param.put("weekOne",weekOne);
        param.put("weekSunday",weekSunday);
        param.put("isDeleted",Boolean.FALSE);
        int countInt = 0;
        countInt = this.getSqlSession().selectOne(this.mapperNamespace + "selectCusReceiveCount",param);
        return countInt;
    }

    /**
     * @Description 获取一个招商人员,本月入驻客户
     * @Auth xpp
     * @Date 2018/10/19 8:52
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int selectCusEntryCount(String empCode, Date weekOneDate, Date weekSundayDate) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("empCode",empCode);
        param.put("weekOne",weekOneDate);
        param.put("weekSunday",weekSundayDate);
        param.put("isDeleted",Boolean.FALSE);
        int countInt = 0;
        countInt = this.getSqlSession().selectOne(this.mapperNamespace + "selectCusEntryCount",param);
        return countInt;
    }

    /**
     * @Description 查询一个员工,本月去化面积
     * @Auth xpp
     * @Date 2018/10/19 8:53
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public BigDecimal selectSignAreaMonth(String empCode, Date weekOneDate, Date weekSundayDate) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("empCode",empCode);
        param.put("weekOne",weekOneDate);
        param.put("weekSunday",weekSundayDate);
        param.put("isDeleted",Boolean.FALSE);
        BigDecimal countInt = new BigDecimal("0.1");
        countInt = this.getSqlSession().selectOne(this.mapperNamespace + "selectSignAreaMonth",param);
        if(countInt==null){countInt = new BigDecimal("0.0");}
        return countInt;
    }

    /**
     * @Description 查询本月 获客数
     * @Auth luming
     * @Date 2018/10/18 19:00
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int getCustCount(String empCode, Date firstDate, Date endDate) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("empCode",empCode);
        param.put("weekOne",firstDate);
        param.put("weekSunday",endDate);
        param.put("isDeleted",Boolean.FALSE);
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectCustomerCount", param);
    }
}
