package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IParkingReceiptManager;
import com.kfwy.park.ati.contract.business.IRentReceiptPayManager;
import com.kfwy.park.ati.contract.dao.IContractDbDao;
import com.kfwy.park.ati.contract.dao.IParkingReceiptDbDao;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.ati.contract.entity.ParkingReceiptEntity;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/6/8 10:41
 */
@Service
public class ParkingReceiptManagerImpl extends AbstractManager<ParkingReceiptEntity> implements IParkingReceiptManager {

    @Autowired
    private IParkingReceiptDbDao parkingReceiptDbDao;

    @Autowired
    private IContractDbDao contractDbDao;

    @Autowired
    private IRentReceiptPayManager rentReceiptPayManager;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.parkingReceiptDbDao;
    }

    @Override
    protected void beforeCreate(ParkingReceiptEntity parkingEntity) {
        parkingEntity.setParkingCode(CodeGenUtils.generate());
        //本期抄表收款结束时间
        Date receivingDate = new Date(parkingEntity.getEndTime().getTime()+1);
        Calendar a = Calendar.getInstance();
        a.setTime(receivingDate);
        int day = a.get(Calendar.DAY_OF_MONTH);
        if (day <= 15){
            a.set(Calendar.DAY_OF_MONTH,15);
        } else {
            a.add(Calendar.MONTH,1);
            a.set(Calendar.DAY_OF_MONTH,15);
        }
        parkingEntity.setReceivingDate(a.getTime());
        /*
         *实收停车费
         */
        parkingEntity.setRealPrice(new BigDecimal(0));
    }

    @Override
    protected void afterCreate(ParkingReceiptEntity parkingReceiptEntity) {
        ContractEntity contractEntity=new ContractEntity();
        contractEntity.setParkingSpace(parkingReceiptEntity.getParkingSpace());
        contractEntity.setCtCode(parkingReceiptEntity.getCtCode());
        contractDbDao.update(contractEntity);
    }

    /**
     * @Description 停车费缴费
     * @Auth luming
     * @Date 2018/9/10 18:16
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    @Transactional
    public void pay(ParkingReceiptEntity entity) {
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("parkingCode",entity.getParkingCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        ParkingReceiptEntity pkingEntity = this.findOne(param);

        RentReceiptPayEntity payEntity = new RentReceiptPayEntity();
        payEntity.setPayCode(CodeGenUtils.generate());
        payEntity.setCode(entity.getParkingCode());
        payEntity.setCtCode(entity.getCtCode());
        //停车费缴费置为4
        payEntity.setCostType("4");
        payEntity.setCostStartTime(pkingEntity.getStartTime());// 计费开始日期
        payEntity.setCostEndTime(pkingEntity.getEndTime());// 计费结束日期
        payEntity.setReceivableRent(pkingEntity.getReceivablePrice());// 应收停车费
        payEntity.setRealRent(entity.getRealPrice());// 实收停车费
        payEntity.setPaidPrice(entity.getPaidPrice());// 已付金额
        payEntity.setPayDate(entity.getPayDate());// 支付时间
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
        entity.setHasTax(pkingEntity.getHasTax());
        entity.setTaxPoint(pkingEntity.getTaxPoint());

        this.update(entity);
        rentReceiptPayManager.create(payEntity);
    }
}
