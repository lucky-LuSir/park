package com.kfwy.park.pro.house.building.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.house.building.dao.IFloorPriceHistoryDbDao;
import com.kfwy.park.pro.house.building.entity.FloorPriceHistoryEntity;
import org.springframework.stereotype.Repository;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/5/30 13:52
 */
@Repository
public class FloorPriceHistoryImpl extends AbstractMyBatisDao<FloorPriceHistoryEntity,Long> implements IFloorPriceHistoryDbDao {
}
