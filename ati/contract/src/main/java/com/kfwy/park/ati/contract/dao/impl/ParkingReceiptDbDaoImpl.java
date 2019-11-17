package com.kfwy.park.ati.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.contract.dao.IParkingReceiptDbDao;
import com.kfwy.park.ati.contract.entity.ParkingReceiptEntity;
import org.springframework.stereotype.Repository;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/6/8 10:38
 */
@Repository
public class ParkingReceiptDbDaoImpl extends AbstractMyBatisDao<ParkingReceiptEntity,Long> implements IParkingReceiptDbDao {
}
