package com.kfwy.park.pro.house.bi.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.house.bi.entity.ParkBiEntity;

import java.util.List;
import java.util.Map;

/*
 * @Description TODO
 * @Auth liuzhengyang
 * @Date 2018/6/26 16:02
 * @Version 1.0
 * @Param 
 * @return  
 */
public interface IParkBiDbDao extends IMyBatisBaseDao<ParkBiEntity,Long> {

    public static final String SELECT_CITY = "selectCity";
    public static final String SELECT_REGION = "selectRegion";
    public static final String  UPDATE_BY_PK_CODE = "updateBypkCode";

    /**
     * @Description 统计园区所在城市
     * @Auth wangchuang
     * @Date 2018/7/9 14:53
     * @Version 1.0
     * @Param []
     * @return java.util.List<com.kfwy.park.pro.house.bi.entity.ParkBiEntity>
     **/
    public List<ParkBiEntity> queryCity(Map<String,Object> param);

    /**
     * @Description 统计园区在某城市下所有的区
     * @Auth wangchuang
     * @Date 2018/7/9 15:20
     * @Version 1.0
     * @Param []
     * @return java.util.List<com.kfwy.park.pro.house.bi.entity.ParkBiEntity>
     **/
    public List<ParkBiEntity> queryRegion(Map<String,Object> param);

    /**
     * @Description 修改根据pkCode修改pkName
     * @Auth wangchuang
     * @Date 2018/7/13 18:51
     * @Version 1.0
     * @Param [param]
     * @return int
     **/
    public int updateBypkCode(ParkBiEntity param);
}
