package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IRentReceiptPayManager;
import com.kfwy.park.ati.contract.business.IWaterReceiptManager;
import com.kfwy.park.ati.contract.dao.IWaterReceiptDbDao;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import com.kfwy.park.ati.contract.entity.WaterReceiptEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xiongzhan on 2018/6/6.
 */
@Service
public class WaterReceiptManagerImpl extends AbstractManager<WaterReceiptEntity> implements IWaterReceiptManager {

    @Autowired
    private IWaterReceiptDbDao waterReceiptDbDao;

    @Autowired
    private IRentReceiptPayManager rentReceiptPayManager;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.waterReceiptDbDao;
    }

    @Override
    protected void beforeCreate(WaterReceiptEntity waterEntity) {

        //处理结束时间为23:59:59.999
        Date dateCurrent = DateFormatUtil.dayEnd(waterEntity.getCurrentBillingTime());
        waterEntity.setCurrentBillingTime(dateCurrent);

        //本期抄表收款结束时间
        Date receivingDate = new Date(dateCurrent.getTime()+1);
        Calendar a = Calendar.getInstance();
        a.setTime(receivingDate);
        int day = a.get(Calendar.DAY_OF_MONTH);
        if (day <= 15){
            a.set(Calendar.DAY_OF_MONTH,15);
        } else {
            a.add(Calendar.MONTH,1);
            a.set(Calendar.DAY_OF_MONTH,15);
        }
        waterEntity.setReceivingDate(a.getTime());

        /**
         * 应收水费 实用量*水费单价
         */
//        if ("1".equals(waterEntity.getWaterBillingType())) {
//            waterEntity.setReceivablePrice(waterEntity.getWaterAmount());
//        }
//        if ("2".equals(waterEntity.getWaterBillingType())) {
//            waterEntity.setReceivablePrice(waterEntity.getRealWater().multiply(waterEntity.getUnitPrice()));
//        }


        //实收为0
        waterEntity.setRealPrice(new BigDecimal(0));
    }

    /**
     * @Description 水费缴费
     * @Auth luming
     * @Date 2018/9/10 17:51
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    @Transactional
    public void pay(WaterReceiptEntity entity) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("waterCode",entity.getWaterCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        WaterReceiptEntity waterReceiptEntity = this.findOne(param);

        RentReceiptPayEntity payEntity = new RentReceiptPayEntity();
        payEntity.setPayCode(CodeGenUtils.generate());
        payEntity.setCode(entity.getWaterCode());
        payEntity.setCtCode(entity.getCtCode());
        // 水费缴费置为3
        payEntity.setCostType("3");
        payEntity.setCostStartTime(waterReceiptEntity.getLastBillingTime());// 上次抄表时间
        payEntity.setCostEndTime(waterReceiptEntity.getCurrentBillingTime());// 本次抄表时间
        payEntity.setReceivableRent(waterReceiptEntity.getReceivablePrice());// 应收水费
        payEntity.setRealRent(entity.getRealPrice());// 实收水费
        payEntity.setPaidPrice(entity.getPaidPrice());// 已收水费
        payEntity.setPayStatus(entity.getPayStatus());// 支付状态
        payEntity.setPayDate(entity.getPayDate());// 支付时间

        // 税
        payEntity.setHasTax(entity.getHasTax());// 租金是否含税
        if(entity.getHasTax()) {
            payEntity.setTaxPoint(entity.getTaxPoint());// 税点
        } else {
            payEntity.setTaxPoint("");// 税点
        }

        payEntity.setRemark(entity.getRemark());
        //税信息还原，不更改原合同税信息，记录缴费中每笔税信息
        entity.setHasTax(waterReceiptEntity.getHasTax());
        entity.setTaxPoint(waterReceiptEntity.getTaxPoint());

        this.update(entity);
        rentReceiptPayManager.create(payEntity);
    }
}
