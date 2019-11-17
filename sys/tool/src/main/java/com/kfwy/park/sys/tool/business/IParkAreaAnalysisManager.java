package com.kfwy.park.sys.tool.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import com.kfwy.park.sys.tool.business.entity.ParkAreaAnalysisEntity;

import java.util.List;

/**
 * Created by gengtao on 2019/5/30.
 */
public interface IParkAreaAnalysisManager extends IManager<ParkEntity> {
    List<ParkAreaAnalysisEntity> parkDatials(ParkEntity entity) throws Exception;
}
