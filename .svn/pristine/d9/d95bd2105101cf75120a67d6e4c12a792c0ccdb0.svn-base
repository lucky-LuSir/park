package com.kfwy.park.pro.house.building.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.house.building.entity.FloorEntity;

import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/5/30 9:13
 */
public interface IFloorDbDao extends IMyBatisBaseDao<FloorEntity,Long> {

    public static final String  UPDATE_BY_PK_CODE = "updateBypkCode";
    public static final String SELECT_MAX_FLOOR = "selectMaxFloor";
    public static final String SELECT_WITH_PARK= "selectWithPark";
    /**
     * @Description 修改:根据pkCode修改pkName
     * @Auth wangchuang
     * @Date 2018/7/14 9:31
     * @Version 1.0
     * @Param [floorEntity]
     * @return int
     **/
    int updateBypkCode(FloorEntity floorEntity);

    /**
     * @Description 查询当前楼栋最大值
     * @Auth luming
     * @Date 2018/11/26 9:37
     * @Version 1.0
     * @Param
     * @return
     */
    int selectMaxFloor(Map<String, Object> map);

    /**
     *@Author gengtao
     *@Date 2019-05-31 14:28:44
     *@Description 根据园区、大区查询
     *@Return
     **/
    List<FloorEntity> selectWithPark(Map<String, Object> map);
}
