package com.kfwy.park.ati.contract.business;

import com.gniuu.framework.common.business.IManager;
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
public interface IElectricityMeterManager extends IManager<ElectricityMeterEntity> {

    /**
     * @Description 物理删除
     * @author wangchuang
     * @Date 2018/8/20 18:09
     * @Version 1.0
     * @Param [id]
     * @return int
     **/
     int delete(Long id);

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
      * @Date 2019/8/9 14:33
      * @Version 1.0
      * @Param [map]
      * @return int
      **/
     int changeEleIsOpen(Map<String, Object> map);
}
