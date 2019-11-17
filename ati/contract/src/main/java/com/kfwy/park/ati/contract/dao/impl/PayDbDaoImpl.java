package com.kfwy.park.ati.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.contract.dao.IPayDbDao;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import org.springframework.stereotype.Repository;

/**
 * @Description 租金、电费、水费、停车费，支付表
 * @Auth luming
 * @Date 2018/9/19 18:43
 * @Version 1.0
 * @Param
 * @return
 */
@Repository
public class PayDbDaoImpl extends AbstractMyBatisDao<RentReceiptPayEntity,Long> implements IPayDbDao {

}
