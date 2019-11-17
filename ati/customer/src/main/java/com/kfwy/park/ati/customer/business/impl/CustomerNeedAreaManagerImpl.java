package com.kfwy.park.ati.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.business.ICustomerNeedAreaManager;
import com.kfwy.park.ati.customer.dao.ICustomerNeedAreaDbDao;
import com.kfwy.park.ati.customer.entity.CustomerNeedAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by luming on 2018/6/6.
 */
@Service
public class CustomerNeedAreaManagerImpl extends AbstractManager<CustomerNeedAreaEntity> implements ICustomerNeedAreaManager {

    @Autowired
    private ICustomerNeedAreaDbDao customerNeedAreaDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.customerNeedAreaDbDao;
    }


    @Override
    protected void beforeCreate(CustomerNeedAreaEntity customerNeedAreaEntity) {
        customerNeedAreaEntity.setCusAreaCode(CodeGenUtils.generate());
    }
}
