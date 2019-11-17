package com.kfwy.park.ati.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.customer.dao.ICustomerNeedAreaDbDao;
import com.kfwy.park.ati.customer.entity.CustomerNeedAreaEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by luming on 2018/6/6.
 */
@Repository
public class CustomerNeedAreaDbDaoImpl extends AbstractMyBatisDao<CustomerNeedAreaEntity,Long> implements ICustomerNeedAreaDbDao {
}
