package com.kfwy.park.ati.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.contract.dao.IRentReceiptPayDbDao;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by liuzhengyang on 2018/8/6.
 */
@Repository
public class RentReceiptPayDbDaoImpl extends AbstractMyBatisDao<RentReceiptPayEntity,Long> implements IRentReceiptPayDbDao {
}
