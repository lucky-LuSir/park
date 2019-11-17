package com.kfwy.park.hrm.org.bi.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.hrm.org.bi.entity.EmpBiEntity;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * @Description TODO
 * @Auth xpp
 * @Date 2018/8/3
 * @Version 1.0
 */
public interface IEmpBiDbDao extends IMyBatisBaseDao<EmpBiEntity,Long> {


    /**
     * @Description 获取一个招商人员,谈判中的客户,不含时间，查询所有
     * @Auth xpp
     * @Date 2018/10/19 8:50
     * @Version 1.0
     * @Param
     * @return
     */
    int selectCusNegotiateCount(String empCode);

    /**
     * @Description 获取一个招商人员名字,电话
     * @Auth xpp
     * @Date 2018/10/19 8:51
     * @Version 1.0
     * @Param
     * @return
     */
    EmpBiEntity selectEmployee(String empCode);

    int selectCusReceiveCount(String empCode, Date weekOne, Date weekSunday) throws ParseException;

    /**
     * @Description 获取一个招商人员,本月入驻客户
     * @Auth xpp
     * @Date 2018/10/19 8:52
     * @Version 1.0
     * @Param
     * @return
     */
    int selectCusEntryCount(String empCode, Date weekOneDate, Date weekSundayDate);

    /**
     * @Description 查询一个员工,本月去化面积
     * @Auth xpp
     * @Date 2018/10/19 8:53
     * @Version 1.0
     * @Param
     * @return
     */
    BigDecimal selectSignAreaMonth(String empCode, Date weekOneDate, Date weekSundayDate);

    /**
     * @Description 查询本月 获客数
     * @Auth luming
     * @Date 2018/10/18 19:00
     * @Version 1.0
     * @Param
     * @return
     */
    public int getCustCount(String empCode, Date firstDate, Date endDate);
}
