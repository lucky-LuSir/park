package com.kfwy.park.pro.house.building.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.house.building.dao.IBuildingDbDao;
import com.kfwy.park.pro.house.building.entity.BuildingEntity;
import org.springframework.stereotype.Repository;


/**
 * Created by luming on 2018/5/29.
 */
@Repository
public class BuildingDbDaoImpl extends AbstractMyBatisDao<BuildingEntity,Long> implements IBuildingDbDao {

    /**
     * @Description 修改根据pkCode修改pkName
     * @Auth wangchuang
     * @Date 2018/7/13 18:09
     * @Version 1.0
     * @Param [param]
     * @return int
     **/
    @Override
    public int updateBypkCode(BuildingEntity param) {
        return this.getSqlSession().update(this.mapperNamespace + UPDATE_BY_PK_CODE, param);
    }
}
