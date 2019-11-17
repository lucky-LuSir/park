package com.kfwy.park.pro.house.building.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.house.building.dao.IParkDbDao;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by xpp on 2018/5/16.
 */
@Repository
public class ParkDbDaoImpl extends AbstractMyBatisDao<ParkEntity,Long> implements IParkDbDao {
    /**
     * @Description 查询所有园区总面积，可租用面积之和
     * @Auth luming
     * @Date 2018/9/25 15:13
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public ParkEntity getAllParkSum(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectParkAcreageSum", map);
    }

    /**
     * @Description 查询园区所属城市
     * @Auth luming
     * @Date 2018/10/17 11:12
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<ParkEntity> selectParkCity(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectAllCity", map);
    }

    /**
     * 查询所有大区
     * @param map
     * @return
     */
    @Override
    public List<ParkEntity> selectAllTerritory(Map<String,Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace+"selectAllTerritory",map);
    }
}
