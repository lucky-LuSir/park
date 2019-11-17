package com.kfwy.park.ati.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.contract.dao.IElectricityReceiptDbDao;
import com.kfwy.park.ati.contract.entity.ElectricityReceiptEntity;
import org.springframework.stereotype.Repository;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/6/7 20:36
 */
@Repository
public class ElectricityReceiptDbDaoImpl extends AbstractMyBatisDao<ElectricityReceiptEntity,Long> implements IElectricityReceiptDbDao {
}
