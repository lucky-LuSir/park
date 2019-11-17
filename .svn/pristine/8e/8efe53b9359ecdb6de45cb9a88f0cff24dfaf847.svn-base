package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IWaterMeterManager;
import com.kfwy.park.ati.contract.dao.IWaterMeterDbDao;
import com.kfwy.park.ati.contract.entity.WaterMeterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class WaterMeterManagerImpl extends AbstractManager<WaterMeterEntity> implements IWaterMeterManager {

    @Autowired
    private IWaterMeterDbDao waterMeterDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.waterMeterDbDao;
    }

    @Override
    protected void beforeCreate(WaterMeterEntity waterMeterEntity) {
        waterMeterEntity.setWaterMeterCode(CodeGenUtils.generate());
        //新增水表默认开启状态
        waterMeterEntity.setWaterIsOpen(true);
    }

    @Override
    public int delete(Long id) {
        return waterMeterDbDao.deleteById(id);
    }

    /**
     * @Description 水表关联水表抄表记录
     * @Auth luming
     * @Date 2019/6/6 10:55
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<WaterMeterEntity> waterMeterUnionRecord(Map<String, Object> map) {
        return waterMeterDbDao.waterMeterUnionRecord(map);
    }

    /**
     * @Description 水表是否开启
     * @author wangchuang
     * @Date 2019/8/13 11:29
     * @Version 1.0
     * @Param [map]
     * @return int
     **/
    @Override
    public int changeWaterIsOpen(Map<String, Object> map) {
        return waterMeterDbDao.changeWaterIsOpen(map);
    }
}
