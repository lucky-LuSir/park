package com.kfwy.park.ati.contract.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.entity.ElectricityMeterEntity;

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
public interface IElectricityMeterDbDao extends IMyBatisBaseDao<ElectricityMeterEntity,Long> {

    public static final String ELE_METER_UNION_RECORD = "eleMeterUnionRecord";
    public static final String CHANGE_ELE_IS_OPEN = "changeEleIsOpen";

    /**
     * @Description 关联查询电表和电表记录表，电表记录表取最新的一条数据
     * @author luming
     * @Date 2019/5/30 10:50
     * @Version 1.0
     * @Param [map]
     * @return java.util.List<com.kfwy.park.ati.contract.entity.ElectricityMeterEntity>
     */
    List<ElectricityMeterEntity> eleMeterUnionRecord(Map<String, Object> map);

    /**
     * @Description 电表启用禁用
     * @author wangchuang
     * @Date 2019/8/9 14:32
     * @Version 1.0
     * @Param [map]
     * @return int
     **/
    int changeEleIsOpen(Map<String, Object> map);
}
