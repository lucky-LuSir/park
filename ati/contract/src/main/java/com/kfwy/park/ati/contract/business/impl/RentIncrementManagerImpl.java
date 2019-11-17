package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IRentIncrementManager;
import com.kfwy.park.ati.contract.dao.IRentIncrementDbDao;
import com.kfwy.park.ati.contract.entity.RentIncrementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Service
public class RentIncrementManagerImpl extends AbstractManager<RentIncrementEntity> implements IRentIncrementManager {

    @Autowired
    private IRentIncrementDbDao rentIncrementDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.rentIncrementDbDao;
    }

    @Override
    protected void beforeCreate(RentIncrementEntity rentIncEntity) {

    }

    /**
     * @Description 查询某合同在某月的递增信息
     * @author wangchuang
     * @Date 2018/10/31 17:01
     * @Version 1.0
     * @Param [param]
     * @return com.kfwy.park.ati.contract.entity.RentIncrementEntity
     **/
    @Override
    public List<RentIncrementEntity> selectOneIncDel(Map<String, Object> param) {
        return rentIncrementDbDao.selectOneIncDel(param);
    }

}
