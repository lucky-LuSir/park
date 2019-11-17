package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IPremiumManager;
import com.kfwy.park.ati.contract.business.IRentReceiptPayManager;
import com.kfwy.park.ati.contract.dao.IPremiumDbDao;
import com.kfwy.park.ati.contract.entity.PremiumEntity;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 保险费
 * @Auth luming
 * @Date 2018/11/17 11:55
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class PremiumManagerImpl extends AbstractManager<PremiumEntity> implements IPremiumManager {

    @Autowired
    private IPremiumDbDao premiumDbDao;

    @Autowired
    private IRentReceiptPayManager rentReceiptPayManager;

    @Override
    protected IMyBatisBaseDao<PremiumEntity, Long> getMyBatisDao() {
        return this.premiumDbDao;
    }

    @Override
    protected void beforeCreate(PremiumEntity premiumEntity) {
        premiumEntity.setPayStatus("1");
        premiumEntity.setPaidPrice(new BigDecimal(0));
        premiumEntity.setThePayment(new BigDecimal(0));

        Date startDate = DateFormatUtil.dayBegin(premiumEntity.getPremiumStartDate());
        Date endDate = DateFormatUtil.dayEnd(premiumEntity.getPremiumEndDate());
        Date deadline = DateFormatUtil.dayEnd(premiumEntity.getDeadline());

        premiumEntity.setPremiumStartDate(startDate);
        premiumEntity.setPremiumEndDate(endDate);
        premiumEntity.setDeadline(deadline);
    }

    /**
     * @Description 支付
     * @Auth luming
     * @Date 2018/11/19 17:01
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    @Transactional
    public void pay(PremiumEntity premiumEntity) {
        Map<String,Object> map = new HashMap<>();
        map.put("premiumCode", premiumEntity.getPremiumCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        PremiumEntity preEntity = this.findOne(map);

        RentReceiptPayEntity payEntity = new RentReceiptPayEntity();
        payEntity.setPayCode(CodeGenUtils.generate());
        payEntity.setCode(premiumEntity.getPremiumCode());
        payEntity.setCtCode(premiumEntity.getCtCode());
        payEntity.setCostType("5");// 5表示保险缴费
        payEntity.setCostStartTime(preEntity.getPremiumStartDate());// 租金开始日期
        payEntity.setCostEndTime(preEntity.getPremiumEndDate());// 租金结束日期
        payEntity.setPayDate(premiumEntity.getPayDate());// 支付时间
        payEntity.setReceivableRent(preEntity.getReceivablePremium());// 应收金额
        payEntity.setRealRent(premiumEntity.getThePayment());// 本次付款金额
        payEntity.setPaidPrice(premiumEntity.getPaidPrice());// 已收金额
        payEntity.setPayStatus(premiumEntity.getPayStatus());// 支付状态

        // 税
//        payEntity.setHasTax(premiumEntity.getHasTax());// 租金是否含税
//        if(premiumEntity.getHasTax()) {
//            payEntity.setTaxPoint(premiumEntity.getTaxPoint());// 税点
//        } else {
//            payEntity.setTaxPoint("");// 税点
//        }
        payEntity.setRemark(premiumEntity.getRemark()); //备注

        //税信息还原，不更改原合同税信息，记录缴费中每笔税信息
        premiumEntity.setHasTax(preEntity.getHasTax());
        premiumEntity.setTaxPoint(preEntity.getTaxPoint());

        this.update(premiumEntity);
        rentReceiptPayManager.create(payEntity);
    }
}
