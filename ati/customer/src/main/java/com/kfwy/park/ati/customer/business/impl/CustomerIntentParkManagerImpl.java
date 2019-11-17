package com.kfwy.park.ati.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.business.ICustomerIntentParkManager;
import com.kfwy.park.ati.customer.dao.ICustomerIntentParkDbDao;
import com.kfwy.park.ati.customer.entity.CustomerIntentParkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/6/5 16:33
 */
@Service
@Transactional
public class CustomerIntentParkManagerImpl extends AbstractManager<CustomerIntentParkEntity> implements ICustomerIntentParkManager {

    @Autowired
    private ICustomerIntentParkDbDao iCustomerIntentParkDbDao;


    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.iCustomerIntentParkDbDao;
    }

}
