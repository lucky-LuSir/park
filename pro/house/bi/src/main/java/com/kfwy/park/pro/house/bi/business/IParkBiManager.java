package com.kfwy.park.pro.house.bi.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.pro.house.bi.entity.ParkBiEntity;

import java.util.List;
import java.util.Map;

/*
 * @Description 园区统计表manager
 * @Auth liuzhengyang
 * @Date 2018/6/26 16:03
 * @Version 1.0
 * @Param 
 * @return  
 */
public interface IParkBiManager extends IManager<ParkBiEntity> {

    /**
     * @Description 统计园区所在城市
     * @Auth wangchuang
     * @Date 2018/7/9 14:51
     * @Version 1.0
     * @Param []
     * @return java.util.List<com.kfwy.park.pro.house.bi.entity.ParkBiEntity>
     **/
    public List<ParkBiEntity> queryCity(Map<String,Object> param) throws Exception;

    /**
     * @Description 统计园区在某城市下所有的区
     * @Auth wangchuang
     * @Date 2018/7/9 15:24
     * @Version 1.0
     * @Param [cityName]
     * @return java.util.List<com.kfwy.park.pro.house.bi.entity.ParkBiEntity>
     **/
    public List<ParkBiEntity> queryRegion(Map<String,Object> param) throws Exception;
}
