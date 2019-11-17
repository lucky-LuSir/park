package com.kfwy.park.pro.house.building.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.house.building.business.IFloorPriceHistoryManager;
import com.kfwy.park.pro.house.building.dao.IFloorPriceHistoryDbDao;
import com.kfwy.park.pro.house.building.entity.FloorPriceHistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/5/30 14:05
 */
@Service
public class FloorPriceHistoryManagerImpl extends AbstractManager<FloorPriceHistoryEntity> implements IFloorPriceHistoryManager {

    @Autowired
    private IFloorPriceHistoryDbDao floorPriceHistoryDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.floorPriceHistoryDbDao;
    }

}
