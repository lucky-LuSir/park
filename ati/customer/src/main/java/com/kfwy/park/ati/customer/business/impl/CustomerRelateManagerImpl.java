package com.kfwy.park.ati.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.business.ICustomerRelateManager;
import com.kfwy.park.ati.customer.dao.ICustomerRelateDbDao;
import com.kfwy.park.ati.customer.entity.CustomerRelateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luming on 2018/5/30.
 */
@Service
public class CustomerRelateManagerImpl extends AbstractManager<CustomerRelateEntity> implements ICustomerRelateManager {

    @Autowired
    private ICustomerRelateDbDao customerRelateDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.customerRelateDbDao;
    }

    @Override
    protected void beforeCreate(CustomerRelateEntity customerRelateEntity) {
        customerRelateEntity.setRelateCode(CodeGenUtils.generate());
    }
}
