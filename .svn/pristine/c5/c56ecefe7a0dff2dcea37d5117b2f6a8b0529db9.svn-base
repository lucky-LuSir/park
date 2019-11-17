package com.kfwy.park.pro.car.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.car.dao.ICarEntityDao;
import com.kfwy.park.pro.car.entity.CarEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by gengtao on 2019/6/11.
 */
@Repository
public class CarEntityDaoImpl extends AbstractMyBatisDao<CarEntity,Long> implements ICarEntityDao {
    @Override
    public void updateAll(CarEntity entity) {
        this.getSqlSession().update(this.mapperNamespace + UPDATA_ALL, entity);
    }
}
