package com.kfwy.park.ati.contract.business;

import com.gniuu.framework.common.business.IManager;
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
public interface IWaterMeterManager extends IManager<WaterMeterEntity> {

    //执行删除操作
    int delete(Long id);

    /**
     * @Description 水表关联水表抄表记录
     * @Auth luming
     * @Date 2019/6/6 10:54
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
