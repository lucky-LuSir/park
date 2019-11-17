package com.kfwy.park.ati.contract.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.entity.WaterMeterEntity;

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
public interface IWaterMeterDbDao extends IMyBatisBaseDao<WaterMeterEntity, Long> {

    public static final String WATER_METER_UNION_RECORD = "waterMeterUnionRecord";
    public static final String CHANGE_WATER_IS_OPEN = "changeWaterIsOpen";


    /**
     * @Description 水表关联水表抄表记录
     * @Auth luming
     * @Date 2019/6/6 10:57
     * @Version 1.0
     * @Param
     * @return
     */
    List<WaterMeterEntity> waterMeterUnionRecord(Map<String, Object> map);

    /**
     * @Description 水表是否开启
     * @author wangchuang
     * @Date 2019/8/13 11:23
     * @Version 1.0
     * @Param []
     * @return int
     **/
    int changeWaterIsOpen(Map<String, Object> map);
}
