package com.kfwy.park.pro.house.bi.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.house.bi.dao.IParkBiDbDao;
import com.kfwy.park.pro.house.bi.entity.ParkBiEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/*
 * @Description TODO
 * @Auth liuzhengyang
 * @Date 2018/6/26 16:01
 * @Version 1.0
 * @Param 
 * @return  
 */
@Repository
public class ParkBiDbDaoImpl extends AbstractMyBatisDao<ParkBiEntity,Long> implements IParkBiDbDao {
    
    /**
     * @Description 统计园区所在城市
     * @Auth wangchuang
     * @Date 2018/7/9 14:53
     * @Version 1.0
     * @Param []
     * @return java.util.List<com.kfwy.park.pro.house.bi.entity.ParkBiEntity>
     **/
    @Override
    public List<ParkBiEntity> queryCity(Map<String,Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_CITY, param);
    }

    /**
     * @Description 统计园区在某城市下所有的区
     * @Auth wangchuang
     * @Date 2018/7/9 15:21
     * @Version 1.0
     * @Param []
     * @return java.util.List<com.kfwy.park.pro.house.bi.entity.ParkBiEntity>
     **/
    @Override
    public List<ParkBiEntity> queryRegion(Map<String,Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_REGION, param);
    }

    /**
     * @Description 修改根据pkCode修改pkName
     * @Auth wangchuang
     * @Date 2018/7/13 18:52
     * @Version 1.0
     * @Param [param]
     * @return int
     **/
    @Override
    public int updateBypkCode(ParkBiEntity param) {
        return this.getSqlSession().update(this.mapperNamespace + UPDATE_BY_PK_CODE, param);
    }
}
