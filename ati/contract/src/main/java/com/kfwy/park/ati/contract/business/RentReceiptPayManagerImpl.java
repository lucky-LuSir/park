package com.kfwy.park.ati.contract.business;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.dao.IRentReceiptPayDbDao;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuzhengyang on 2018/8/6.
 */
@Service
public class RentReceiptPayManagerImpl extends AbstractManager<RentReceiptPayEntity> implements IRentReceiptPayManager {

    @Autowired
    private IRentReceiptPayDbDao rentReceiptPayDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.rentReceiptPayDbDao;
    }

}
