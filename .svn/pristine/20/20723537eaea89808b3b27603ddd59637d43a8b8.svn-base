package com.kfwy.park.pro.house.building.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.house.building.entity.ParkEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/16.
 */
public interface IParkDbDao extends IMyBatisBaseDao<ParkEntity,Long> {
    /**
     * @Description 查询所有园区总面积，可租用面积之和
     * @Auth luming
     * @Date 2018/9/25 15:13
     * @Version 1.0
     * @Param
     * @return
     */
    ParkEntity getAllParkSum(Map<String, Object> map);

    /**
     * @Description 查询园区所属城市
     * @Auth luming
     * @Date 2018/10/17 11:11
     * @Version 1.0
     * @Param
     * @return
     */
    List<ParkEntity> selectParkCity(Map<String, Object> map);

    /**
     * 查询所有大区
     * @param map
     * @return
     */
    List<ParkEntity> selectAllTerritory(Map<String,Object> map);

}
