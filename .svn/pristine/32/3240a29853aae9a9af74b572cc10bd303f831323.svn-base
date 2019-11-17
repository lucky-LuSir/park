package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IConOperateHisManager;
import com.kfwy.park.ati.contract.dao.IConOperateHisDbDao;
import com.kfwy.park.ati.contract.entity.ConOperateHisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Service
public class ConOperateHisManagerImpl extends AbstractManager<ConOperateHisEntity> implements IConOperateHisManager {

    @Autowired
    private IConOperateHisDbDao conOperateHisDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.conOperateHisDbDao;
    }

    @Override
    protected void beforeCreate(ConOperateHisEntity conOperateHisEntity) {

    }
}
