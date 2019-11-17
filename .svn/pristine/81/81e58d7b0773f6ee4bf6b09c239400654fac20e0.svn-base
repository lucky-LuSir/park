package com.kfwy.park.ati.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.business.ICustomerChannelCpyManager;
import com.kfwy.park.ati.customer.dao.ICustomerChannelCpyDbDao;
import com.kfwy.park.ati.customer.entity.CustomerChannelCpyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuzhengyang on 2018/9/21.
 */
@Service
public class CustomerChannelCpyManagerImpl extends AbstractManager<CustomerChannelCpyEntity> implements ICustomerChannelCpyManager {

    @Autowired
    private ICustomerChannelCpyDbDao customerChannelCpyDbDao;

    @Override
    protected IMyBatisBaseDao<CustomerChannelCpyEntity, Long> getMyBatisDao() {
        return customerChannelCpyDbDao;
    }
}
