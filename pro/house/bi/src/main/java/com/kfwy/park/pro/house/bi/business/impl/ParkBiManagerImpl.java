package com.kfwy.park.pro.house.bi.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.house.bi.business.IParkBiManager;
import com.kfwy.park.pro.house.bi.dao.IParkBiDbDao;
import com.kfwy.park.pro.house.bi.entity.ParkBiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/*
 * @Description TODO
 * @Auth liuzhengyang
 * @Date 2018/6/26 16:03
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class ParkBiManagerImpl extends AbstractManager<ParkBiEntity> implements IParkBiManager {

    @Autowired
    private IParkBiDbDao parkDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.parkDbDao;
    }

    /**
     * @Description 统计园区所在城市
     * @Auth wangchuang
     * @Date 2018/7/9 14:51
     * @Version 1.0
     * @Param []
     * @return java.util.List<com.kfwy.park.pro.house.bi.entity.ParkBiEntity>
     **/
    @Override
    public List<ParkBiEntity> queryCity(Map<String,Object> param) throws Exception {
        return parkDbDao.queryCity(param);
    }

    /**
     * @Description 统计园区在某城市下所有的区
     * @Auth wangchuang
     * @Date 2018/7/9 15:24
     * @Version 1.0
     * @Param [cityName]
     * @return java.util.List<com.kfwy.park.pro.house.bi.entity.ParkBiEntity>
     **/
    @Override
    public List<ParkBiEntity> queryRegion(Map<String,Object> param) throws Exception {
        return parkDbDao.queryRegion(param);
    }
}
