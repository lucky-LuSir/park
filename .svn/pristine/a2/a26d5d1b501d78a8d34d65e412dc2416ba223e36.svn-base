package com.kfwy.park.ati.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.contract.dao.IWaterMeterDbDao;
import com.kfwy.park.ati.contract.entity.WaterMeterEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Description 水表管理
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Repository
public class WaterMeterDbDaoImpl extends AbstractMyBatisDao<WaterMeterEntity, Long> implements IWaterMeterDbDao {

    /**
     * @Description 水表关联水表抄表记录
     * @Auth luming
     * @Date 2019/6/6 10:58
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<WaterMeterEntity> waterMeterUnionRecord(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + WATER_METER_UNION_RECORD, map);
    }

    /**
     * @Description 水表是否开启
     * @author wangchuang
     * @Date 2019/8/13 11:27
     * @Version 1.0
     * @Param [map]
     * @return int
     **/
    @Override
    public int changeWaterIsOpen(Map<String, Object> map) {
        return this.getSqlSession().update(this.mapperNamespace + CHANGE_WATER_IS_OPEN, map);
    }
}
