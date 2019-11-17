package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IRentReceiptLogManager;
import com.kfwy.park.ati.contract.dao.IRentReceiptLogDbDao;
import com.kfwy.park.ati.contract.entity.RentReceiptLogEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by liuzhengyang on 2018/8/2.
 */
@Service
@Transactional
public class RentReceiptLogManagerImpl extends AbstractManager<RentReceiptLogEntity> implements IRentReceiptLogManager {

    @Autowired
    private IRentReceiptLogDbDao rentReceiptLogDbDao;
    @Override
    protected IMyBatisBaseDao<RentReceiptLogEntity, Long> getMyBatisDao() {
        return this.rentReceiptLogDbDao;
    }
}
