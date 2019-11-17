package com.kfwy.park.ati.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.customer.dao.ICustomerUpDownDbDao;
import com.kfwy.park.ati.customer.entity.CustomerUpDownEntity;
import org.springframework.stereotype.Repository;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Repository
public class CustomerUpDownDbDaoImpl extends AbstractMyBatisDao<CustomerUpDownEntity, Long> implements ICustomerUpDownDbDao {
}
