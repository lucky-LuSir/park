package com.kfwy.park.pro.house.building.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.pro.house.building.entity.FloorEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description
 * @Auth wangchuang
 * @Date 2018/6/12 15:41
 * @Version 1.0
 * @Param
 * @return
 **/
public interface IFloorManager extends IManager<FloorEntity> {

    /**
     * @Description 查询一条详情
     * @Auth wangchuang
     * @Date 2018/6/22 15:39
     * @Version 1.0
     * @Param [param]
     * @return com.kfwy.park.pro.house.building.entity.FloorEntity
     **/
    public FloorEntity findDetails(Map<String,Object> param) throws Exception;

    /**
     * @Description 创建楼层时验证本栋的所有楼层面积是否大于楼栋面积
     * @Auth luming
     * @Date 2018/8/25 14:36
     * @Version 1.0
     * @Param
     * @return
     */
    public Boolean acreage(FloorEntity floorEntity) throws Exception;

    /**
     *@Author gengtao
     *@Date 2019-06-27 18:04:32
     *@Description 根据园区查找floor信息
     *@Return
     **/
    List<FloorEntity> selectWithPark(Map<String, Object> map);
}
