package com.kfwy.park.ati.contract.dao.impl;


import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.contract.dao.IRentReceiptLogDbDao;
import com.kfwy.park.ati.contract.entity.RentReceiptLogEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by liuzhengyang on 2018/8/2.
 */
@Repository
public class RentReceiptLogDbDaoImpl extends AbstractMyBatisDao<RentReceiptLogEntity,Long> implements IRentReceiptLogDbDao {
}
