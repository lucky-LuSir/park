package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IRentReceiptManager;
import com.kfwy.park.ati.contract.business.IRentReceiptPayManager;
import com.kfwy.park.ati.contract.dao.IRentReceiptDbDao;
import com.kfwy.park.ati.contract.entity.RentReceiptEntity;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiongzhan on 2018/6/6.
 */
@Service
public class RentReceiptManagerImpl extends AbstractManager<RentReceiptEntity> implements IRentReceiptManager {

    @Autowired
    private IRentReceiptDbDao rentReceiptDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.rentReceiptDbDao;
    }

    @Override
    protected void beforeCreate(RentReceiptEntity rentReceiptEntity) {

        rentReceiptEntity.setRentCode(CodeGenUtils.generate());
        rentReceiptEntity.setPayStatus("1");
        rentReceiptEntity.setRealRent(new BigDecimal(0));
        rentReceiptEntity.setPaidPrice(new BigDecimal(0));
        //逾期天数默认为0
        rentReceiptEntity.setOverdueDays(0);
        //滞纳金默认为0
        rentReceiptEntity.setLatePayment(new BigDecimal(0));
        //优惠金额默认为0
        rentReceiptEntity.setDiscountsPrice(new BigDecimal(0));

        Date rentStartTime = DateFormatUtil.dayBegin(rentReceiptEntity.getRentStartTime());
        Date rentEndTime = DateFormatUtil.dayEnd(rentReceiptEntity.getRentEndTime());
        Date receivingDate = DateFormatUtil.dayEnd(rentReceiptEntity.getReceivingDate());
        rentReceiptEntity.setRentStartTime(rentStartTime);
        rentReceiptEntity.setRentEndTime(rentEndTime);
        rentReceiptEntity.setReceivingDate(receivingDate);

    }

    @Autowired
    private IRentReceiptPayManager rentReceiptPayManager;

    /**
     * @Description 查询15天内应缴租金户
     * @author wangchuang
     * @Date 2018/7/19 15:01
     * @Version 1.0
     * @Param []
     * @return int
     **/
    @Override
    public int rentMaturityTime(Map<String, Object> map) {
        return rentReceiptDbDao.countByMap(map);
    }

    /**
     * @Description 查询费用逾期数
     * @author wangchuang
     * @Date 2018/7/19 20:23
     * @Version 1.0
     * @Param [pkCode]
     * @return int
     **/
    @Override
    public int selectOverdueCount(String pkCode) {
        return rentReceiptDbDao.selectOverdueCount(pkCode);
    }

    /*
     * @Description 租金应收缴费接口
     * @Auth liuzhengyang
     * @Date 2018/8/6 13:47
     * @Version 1.0
     * @Param [entity]
     * @return void
     */
    @Override
    @Transactional
    public void pay(RentReceiptEntity entity) {

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("rentCode",entity.getRentCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        RentReceiptEntity rentRes = this.findOne(param);

        RentReceiptPayEntity payEntity = new RentReceiptPayEntity();
        payEntity.setPayCode(CodeGenUtils.generate());
        payEntity.setCode(entity.getRentCode());
        payEntity.setCtCode(entity.getCtCode());
        //租金缴费置为1
        payEntity.setCostType("1");
        payEntity.setCostStartTime(rentRes.getRentStartTime());// 租金开始日期
        payEntity.setCostEndTime(rentRes.getRentEndTime());// 租金结束日期
        payEntity.setPayDate(entity.getPayDate());// 支付时间
        payEntity.setReceivableRent(rentRes.getReceivableRent());// 应收租金金额
        payEntity.setRealRent(entity.getRealRent());// 本次实收金额
        payEntity.setPropertyPrice(entity.getPropertyPrice());// 物业费
        payEntity.setPayStatus(entity.getPayStatus());// 支付状态
        // 税
        payEntity.setHasTax(entity.getHasTax());// 租金是否含税
        if(entity.getHasTax()) {
            payEntity.setTaxPoint(entity.getTaxPoint());// 税点
        } else {
            payEntity.setTaxPoint("");// 税点
        }

        payEntity.setProHasTax(entity.getProHasTax());//物业费是否含税
        if(entity.getProHasTax()) {
            payEntity.setProTaxPoint(entity.getProTaxPoint());//物业费税点
        } else {
            payEntity.setProTaxPoint("");//物业费税点
        }

        payEntity.setRemark(entity.getRemark());
        //税信息还原，不更改原合同税信息，记录缴费中每笔税信息
        entity.setHasTax(rentRes.getHasTax());
        entity.setTaxPoint(rentRes.getTaxPoint());
        entity.setProHasTax(rentRes.getProHasTax());
        entity.setProTaxPoint(rentRes.getProTaxPoint());

        //每笔优惠金额累加起来
        entity.setDiscountsPrice(rentRes.getDiscountsPrice().add(entity.getDiscountsPrice()));

        this.update(entity);
        rentReceiptPayManager.create(payEntity);

    }

    @Override
    public List<RentReceiptEntity> findByPark(Map<String, Object> map) {
        return rentReceiptDbDao.selectByPark(map);
    }


}
