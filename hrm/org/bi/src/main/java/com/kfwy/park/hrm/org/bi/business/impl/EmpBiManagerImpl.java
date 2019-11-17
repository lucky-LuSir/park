package com.kfwy.park.hrm.org.bi.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.hrm.org.bi.business.IEmpBiManager;
import com.kfwy.park.hrm.org.bi.dao.IEmpBiDbDao;
import com.kfwy.park.hrm.org.bi.entity.EmpBiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Date;

/**
 * @Description TODO
 * @Auth xpp
 * @Date 2018/8/3
 * @Version 1.0
 */
@Service
public class EmpBiManagerImpl extends AbstractManager<EmpBiEntity> implements IEmpBiManager {

    @Autowired
    private IEmpBiDbDao empBiDbDao;


    @Override
    protected IMyBatisBaseDao<EmpBiEntity, Long> getMyBatisDao() {
        return this.empBiDbDao;
    }


    @Override
    public EmpBiEntity queryOne(String empCode, Date firstDate, Date endDate) throws ParseException {

        EmpBiEntity empBiEntity = new EmpBiEntity();

        // 获取一个招商人员名字,电话
        empBiEntity = empBiDbDao.selectEmployee(empCode);

        // 查询本月 获客数
        int custCount = empBiDbDao.getCustCount(empCode, firstDate, endDate);
        empBiEntity.setCusCount(custCount);

        // 获取一个招商人员,谈判中的客户
        int cusNegotiateCount = empBiDbDao.selectCusNegotiateCount(empCode);
        empBiEntity.setCusNegotiateCount(cusNegotiateCount);

        // 获取一个招商人员,本月入驻客户
        int cusEntryCount = empBiDbDao.selectCusEntryCount(empCode, firstDate, endDate);
        empBiEntity.setCusEntryCount(cusEntryCount);

        // 获取一个招商人员,本月去化面积
        BigDecimal signAreaMonth = empBiDbDao.selectSignAreaMonth(empCode, firstDate, endDate);
        empBiEntity.setSignAreaMonth(signAreaMonth);

        return empBiEntity;
    }
}
