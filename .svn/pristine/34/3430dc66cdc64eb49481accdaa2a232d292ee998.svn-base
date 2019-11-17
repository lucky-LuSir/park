package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IElectricityMeterManager;
import com.kfwy.park.ati.contract.dao.IElectricityMeterDbDao;
import com.kfwy.park.ati.contract.entity.ElectricityMeterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class ElectricityMeterManagerImpl extends AbstractManager<ElectricityMeterEntity> implements IElectricityMeterManager {

    @Autowired
    private IElectricityMeterDbDao electricityMeterDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.electricityMeterDbDao;
    }

    @Override
    protected void beforeCreate(ElectricityMeterEntity meterEntity) {
        meterEntity.setEleMeterCode(CodeGenUtils.generate());

        //新增电表默认开启状态(true：启用，false：禁用)
        meterEntity.setEleIsOpen(true);
    }

    @Override
    public int delete(Long id) {
        return electricityMeterDbDao.deleteById(id);
    }

    /**
     * @Description 关联查询电表和电表记录表，电表记录表取最新的一条数据
     * @author luming
     * @Date 2019/5/30 10:50
     * @Version 1.0
     * @Param [map]
     * @return java.util.List<com.kfwy.park.ati.contract.entity.ElectricityMeterEntity>
     */
    @Override
    public List<ElectricityMeterEntity> eleMeterUnionRecord(Map<String, Object> map) {
        return electricityMeterDbDao.eleMeterUnionRecord(map);
    }

    /**
     * @Description 电表启用禁用
     * @author wangchuang
     * @Date 2019/8/9 14:46
     * @Version 1.0
     * @Param [map]
     * @return int
     **/
    @Override
    public int changeEleIsOpen(Map<String, Object> map) {
        return electricityMeterDbDao.changeEleIsOpen(map);
    }
}
