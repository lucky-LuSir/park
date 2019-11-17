package com.kfwy.park.ati.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.business.ICustomerChannelFollowupManager;
import com.kfwy.park.ati.customer.dao.ICustomerChannelFollowupDbDao;
import com.kfwy.park.ati.customer.entity.CustomerChannelFollowupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 渠道跟进
 * @Auth luming
 * @Date 2018/10/24 11:34
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class CustomerChannelFollowupManagerImpl extends AbstractManager<CustomerChannelFollowupEntity> implements ICustomerChannelFollowupManager {

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.customerChannelFollowupDbDao;
    }

    @Autowired
    private ICustomerChannelFollowupDbDao customerChannelFollowupDbDao;


}
