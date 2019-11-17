package com.kfwy.park.ati.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.business.ICustomerUpDownManager;
import com.kfwy.park.ati.customer.dao.ICustomerUpDownDbDao;
import com.kfwy.park.ati.customer.entity.CustomerUpDownEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Service
public class CustomerUpDownManagerImpl extends AbstractManager<CustomerUpDownEntity> implements ICustomerUpDownManager {

    @Autowired
    private ICustomerUpDownDbDao customerUpDownDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.customerUpDownDbDao;
    }

    @Override
    protected void beforeCreate(CustomerUpDownEntity customerUpDownEntity) {

    }

    @Override
    protected void afterCreate(CustomerUpDownEntity customerUpDownEntity) {

    }
}
