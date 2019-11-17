package com.kfwy.park.ati.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.contract.dao.IElectricityMeterDbDao;
import com.kfwy.park.ati.contract.entity.ElectricityMeterEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Description 电表管理
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Repository
public class ElectricityMeterDbDaoImpl extends AbstractMyBatisDao<ElectricityMeterEntity,Long> implements IElectricityMeterDbDao {

    /**
     * @Description 关联查询电表和电表记录表，电表记录表取最新的一条数据
     * @author luming
     * @Date 2019/5/30 10:53
     * @Version 1.0
     * @Param [map]
     * @return java.util.List<com.kfwy.park.ati.contract.entity.ElectricityMeterEntity>
     */
    @Override
    public List<ElectricityMeterEntity> eleMeterUnionRecord(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + ELE_METER_UNION_RECORD, map);
    }

    /**
     * @Description 电表启用禁用
     * @author wangchuang
     * @Date 2019/8/9 14:31
     * @Version 1.0
     * @Param [map]
     * @return int
     **/
    @Override
    public int changeEleIsOpen(Map<String, Object> map) {
        return this.getSqlSession().update(this.mapperNamespace + CHANGE_ELE_IS_OPEN, map);
    }
}
