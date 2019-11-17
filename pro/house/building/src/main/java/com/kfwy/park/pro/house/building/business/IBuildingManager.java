package com.kfwy.park.pro.house.building.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.pro.house.building.entity.BuildingEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by luming on 2018/5/29.
 */
public interface IBuildingManager extends IManager<BuildingEntity>{

    /**
     * @Description 查询建筑详情并关联楼层信息
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    public BuildingEntity details(Map<String,Object> map) throws Exception;

    /***
     * @Description 查询园区所有楼栋
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    public List<BuildingEntity> parkAllBuilding(Map<String,Object> map);

    /**
     * @Description 创建楼栋时校验楼栋面积是否大于园区面积
     * @Auth luming
     * @Date 2018/8/25 14:36
     * @Version 1.0
     * @Param
     * @return
     */
    public Boolean acreage(BuildingEntity buildingEntity) throws Exception;
}
