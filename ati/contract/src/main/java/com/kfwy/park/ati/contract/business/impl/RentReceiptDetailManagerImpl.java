package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IRentReceiptDetailManager;
import com.kfwy.park.ati.contract.dao.IRentReceiptDetailDbDao;
import com.kfwy.park.ati.contract.entity.RentReceiptDetailEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by xiongzhan on 2018/6/4.
 */
@Service
public class RentReceiptDetailManagerImpl extends AbstractManager<RentReceiptDetailEntity> implements IRentReceiptDetailManager {

    @Autowired
    private IRentReceiptDetailDbDao rentReceiptDetailDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.rentReceiptDetailDbDao;
    }
}
