package com.kfwy.park.exp.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IRentReceiptPayManager;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import com.kfwy.park.exp.contract.business.ILandlordRentManager;
import com.kfwy.park.exp.contract.dao.ILandlordRentDao;
import com.kfwy.park.exp.contract.entity.LandlordContractEntity;
import com.kfwy.park.exp.contract.entity.LandlordRentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by gengtao on 2019/4/18.
 */
@Service
public class LandlordRentManagerImpl extends AbstractManager<LandlordRentEntity> implements ILandlordRentManager {
    @Autowired
    private ILandlordRentDao landlordRentDao;
    @Autowired
    private IRentReceiptPayManager rentReceiptPayManager;
    @Override
    protected IMyBatisBaseDao<LandlordRentEntity, Long> getMyBatisDao() {
        return this.landlordRentDao;
    }

    @Override
    public void pay(LandlordRentEntity landlordRentEntity) {
        this.update(landlordRentEntity);

        RentReceiptPayEntity payEntity = landlordRentEntity.getPayEntityList().get(0);
        payEntity.setPayCode(CodeGenUtils.generate());
        payEntity.setCode(landlordRentEntity.getRentCode());
        payEntity.setCtCode(landlordRentEntity.getLdCode());
        payEntity.setCostStartTime(landlordRentEntity.getCostStartTime());
        payEntity.setCostEndTime(landlordRentEntity.getCostEndTime());
        payEntity.setPayStatus(landlordRentEntity.getPayStatus());
        rentReceiptPayManager.create(payEntity);
    }
}
