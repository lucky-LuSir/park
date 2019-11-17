package com.kfwy.park.pro.car.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.car.entity.CarEntity;

/**
 * 车辆进出管理Dao
 * <pre>
 * Author	kfwy_it_004
 * Version 	1.0
 * Date    2019年06月11日
 * </pre>
 */
public interface ICarEntityDao extends IMyBatisBaseDao<CarEntity, Long> {
    public static final String UPDATA_ALL = "updateAll";

    void updateAll(CarEntity entity);

}
