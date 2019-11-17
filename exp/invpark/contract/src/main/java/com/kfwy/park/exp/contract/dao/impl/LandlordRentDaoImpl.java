package com.kfwy.park.exp.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.exp.contract.dao.ILandlordRentDao;
import com.kfwy.park.exp.contract.entity.LandlordRentEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by gengtao on 2019/4/18.
 */
@Repository
public class LandlordRentDaoImpl extends AbstractMyBatisDao<LandlordRentEntity, Long>  implements ILandlordRentDao {
}
