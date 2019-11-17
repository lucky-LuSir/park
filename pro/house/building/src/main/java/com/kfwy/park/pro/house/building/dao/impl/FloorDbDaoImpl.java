package com.kfwy.park.pro.house.building.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.house.building.dao.IFloorDbDao;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/5/30 9:15
 */
@Repository
public class FloorDbDaoImpl extends AbstractMyBatisDao<FloorEntity,Long> implements IFloorDbDao {

    /**
     * @Description 修改:根据pkCode修改pkName
     * @Auth wangchuang
     * @Date 2018/7/14 9:32
     * @Version 1.0
     * @Param [floorEntity]
     * @return int
     **/
    @Override
    public int updateBypkCode(FloorEntity floorEntity) {
        return this.getSqlSession().update(this.mapperNamespace + UPDATE_BY_PK_CODE, floorEntity);
    }

    /**
     * @Description 查询当前楼栋最大值
     * @Auth luming
     * @Date 2018/11/26 9:37
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int selectMaxFloor(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_MAX_FLOOR, map);
    }

    @Override
    /**
     *@Author gengtao
     *@Date 2019-05-31 14:28:44
     *@Description 根据园区、大区查询
     *@Return
     **/
    public List<FloorEntity> selectWithPark(Map<String, Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_WITH_PARK, map);
    }
}
