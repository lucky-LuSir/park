package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IElectricityReceiptManager;
import com.kfwy.park.ati.contract.business.IRentReceiptPayManager;
import com.kfwy.park.ati.contract.dao.IElectricityReceiptDbDao;
import com.kfwy.park.ati.contract.entity.ElectricityReceiptEntity;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/6/7 20:39
 */
@Service
public class ElectricityReceiptManagerImpl extends AbstractManager<ElectricityReceiptEntity> implements IElectricityReceiptManager {

    @Autowired
    private IElectricityReceiptDbDao electricityReceiptDbDao;

    @Autowired
    private IRentReceiptPayManager rentReceiptPayManager;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.electricityReceiptDbDao;
    }

    @Override
    protected void beforeCreate(ElectricityReceiptEntity eleEntity) {

        //1、计算每月基础电费  2、计算用电费用总额  3、应收电费
//        compute(eleEntity);

    }

//    public void compute(ElectricityReceiptEntity eleEntity) {
//
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("ctCode",eleEntity.getCtCode());
//        map.put("isDeleted",false);
//        String eleCode = CodeGenUtils.generate();
//        eleEntity.setEleCode(eleCode);
//
//        ContractEntity contractEntity = contractDbDao.selectUniqueByMap(map);
//
//        if (contractEntity != null){
//            //合同表需要的字段
//
//            //总用电量
//            BigDecimal totalElectricity = contractEntity.getTotalElectricity();
//            //基础电费单价
//            BigDecimal basicElectricityUnitPrice = contractEntity.getBasicElectricityUnitPrice();
//            //电费波峰单价
//            BigDecimal electricityPeakPrice = contractEntity.getElectricityPeakPrice();
//            //电费平值单价
//            BigDecimal electricityFlatPrice = contractEntity.getElectricityFlatPrice();
//            //电费波谷单价
//            BigDecimal electricityTroughPrice = contractEntity.getElectricityTroughPrice();
//            //电费均价单价
//            BigDecimal electricityAveragePrice = contractEntity.getElectricityAveragePrice();
//            //加收损耗(%)
//            BigDecimal addLoss = contractEntity.getAddLoss();
//            addLoss = addLoss.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(1));
//            //倍率
//            BigDecimal multiple = contractEntity.getMultiple();
//
//            Date lastBillingTime = eleEntity.getLastBillingTime();
//            Date currentBillingTime = eleEntity.getCurrentBillingTime();
//
//
//             //1.计算每月基础电费
//
//            //1.1 得到本期的天数
//            BigDecimal betweenDays = DateFormatUtil.daysBetween(lastBillingTime, currentBillingTime);
//            //1.2 基础电费单价*总用电量*天数
//            BigDecimal bm = basicElectricityUnitPrice.multiply(totalElectricity).multiply(betweenDays);
//            eleEntity.setBasicElectricityPriceByMonth(bm);
//
//
//             //2.计算用电费用总额
//
//            String eleType = eleEntity.getElectricityBillingType();
//
//            //用电费用
//            BigDecimal elePrice = new BigDecimal(0);
//            if ("2".equals(eleType)) {//峰.平.谷计费方式
//                //1.波峰.平.谷电量*单价
//                BigDecimal peak = eleEntity.getPeakDegree().multiply(electricityPeakPrice);
//                BigDecimal flat = eleEntity.getFlatDegree().multiply(electricityFlatPrice);
//                BigDecimal trough = eleEntity.getTroughDegree().multiply(electricityTroughPrice);
//
//                //用电费用
//                elePrice = (peak.add(flat).add(trough)).multiply(multiple).multiply(addLoss);
//
//                //得到用到的总用电量
//                BigDecimal rDegree = eleEntity.getPeakDegree().add(eleEntity.getFlatDegree().add(eleEntity.getTroughDegree()));
//                eleEntity.setRealDegree(rDegree);
//            } else if("1".equals(eleType)) {//均价计费方式
//                elePrice = eleEntity.getRealDegree().multiply(electricityAveragePrice).multiply(multiple).multiply(addLoss);
//            }
//
//            eleEntity.setElectricityPrice(elePrice);
//
//
//             // 3.应收电费
//
//            eleEntity.setReceivablePrice(eleEntity.getBasicElectricityPriceByMonth().add(eleEntity.getElectricityPrice()));
//
//             // 4.实收电费
//
//            eleEntity.setRealPrice(new BigDecimal(0));
//        }
//    }


    /**
     * @Description
     * 1、计算每月基础电费  2、计算用电费用总额  3、应收电费
     * @author wangchuang
     * @Date 2018/7/25 17:29
     * @Version 1.0
     * @Param [eleEntity]
     * @return void
     **/
    /*public void compute(ElectricityReceiptEntity eleEntity) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ctCode",eleEntity.getCtCode());
        map.put("isDeleted",false);

        ContractEntity contractEntity = contractDbDao.selectUniqueByMap(map);

        if (contractEntity != null){
            //合同表需要的字段

            //总用电量
            BigDecimal totalElectricity = contractEntity.getTotalElectricity();
            //基础电费单价
            BigDecimal basicElectricityUnitPrice = contractEntity.getBasicElectricityUnitPrice();
            //电费波峰单价
            BigDecimal electricityPeakPrice = contractEntity.getElectricityPeakPrice();
            //电费平值单价
            BigDecimal electricityFlatPrice = contractEntity.getElectricityFlatPrice();
            //电费波谷单价
            BigDecimal electricityTroughPrice = contractEntity.getElectricityTroughPrice();
            //电费均价单价
            BigDecimal electricityAveragePrice = contractEntity.getElectricityAveragePrice();
            //加收损耗(%)
            BigDecimal addLoss = contractEntity.getAddLoss();
            addLoss = addLoss.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).add(new BigDecimal(1));
            //倍率
            BigDecimal multiple = contractEntity.getMultiple();

            Date lastBillingTime = eleEntity.getLastBillingTime();
            Date currentBillingTime = eleEntity.getCurrentBillingTime();

            *//*
             * 1.计算每月基础电费
             *//*
            //1.1 得到本期的天数
            BigDecimal betweenDays = DateFormatUtil.daysBetween(lastBillingTime, currentBillingTime);
            //1.2 基础电费单价*总用电量*天数
            BigDecimal bm = basicElectricityUnitPrice.multiply(totalElectricity).multiply(betweenDays);
            eleEntity.setBasicElectricityPriceByMonth(bm);

            *//*
             * 2.计算用电费用总额
             *//*
            String eleType = eleEntity.getElectricityBillingType();

            //用电费用
            BigDecimal elePrice = new BigDecimal(0);
            if ("2".equals(eleType)) {//峰.平.谷计费方式
                //1.波峰.平.谷电量*单价
                BigDecimal peak = eleEntity.getPeakDegree().multiply(electricityPeakPrice);
                BigDecimal flat = eleEntity.getFlatDegree().multiply(electricityFlatPrice);
                BigDecimal trough = eleEntity.getTroughDegree().multiply(electricityTroughPrice);

                //用电费用
                elePrice = (peak.add(flat).add(trough)).multiply(multiple).multiply(addLoss);

                //得到用到的总用电量
                BigDecimal rDegree = eleEntity.getPeakDegree().add(eleEntity.getFlatDegree().add(eleEntity.getTroughDegree()));
                eleEntity.setRealDegree(rDegree);
            } else if("1".equals(eleType)) {//均价计费方式
                elePrice = eleEntity.getRealDegree().multiply(electricityAveragePrice).multiply(multiple).multiply(addLoss);
            }

            eleEntity.setElectricityPrice(elePrice);

            *//*
             * 3.应收电费
             *//*
            eleEntity.setReceivablePrice(eleEntity.getBasicElectricityPriceByMonth().add(eleEntity.getElectricityPrice()));
            *//*
             * 4.实收电费
             *//*
            eleEntity.setRealPrice(new BigDecimal(0));
        }
    }*/


    /**
     * @return
     * @Description 电费缴费
     * @Auth luming
     * @Date 2018/9/10 17:10
     * @Version 1.0
     * @Param
     */
    @Override
    @Transactional
    public void pay(ElectricityReceiptEntity entity) {
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("eleCode", entity.getEleCode());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        ElectricityReceiptEntity eleEntity = this.findOne(param);

        // 支付
        RentReceiptPayEntity payEntity = new RentReceiptPayEntity();
        payEntity.setPayCode(CodeGenUtils.generate());
        payEntity.setCode(entity.getEleCode());
        payEntity.setCtCode(entity.getCtCode());
        //电费缴费置为2
        payEntity.setCostType("2");
        payEntity.setCostStartTime(eleEntity.getLastBillingTime());
        payEntity.setCostEndTime(eleEntity.getCurrentBillingTime());
        payEntity.setPayDate(entity.getPayDate());
        payEntity.setReceivableRent(eleEntity.getReceivablePrice());// 应收金额
        payEntity.setRealRent(entity.getRealPrice());// 实收金额
        payEntity.setPaidPrice(entity.getPaidPrice());// 已收金额
        payEntity.setPayStatus(entity.getPayStatus());// 支付状态

        // 税
        payEntity.setHasTax(entity.getHasTax());// 租金是否含税
        if(entity.getHasTax()) {
            payEntity.setTaxPoint(entity.getTaxPoint());// 税点
        } else {
            payEntity.setTaxPoint("");// 税点
        }

        payEntity.setRemark(entity.getRemark());

        //税信息还原，不更改原合同税信息，记录缴费中每笔税信息
        entity.setHasTax(eleEntity.getHasTax());
        entity.setTaxPoint(eleEntity.getTaxPoint());

        this.update(entity);
        rentReceiptPayManager.create(payEntity);
    }
}
