package com.kfwy.park.bi.report.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.report.business.ILandlordReceivableManager;
import com.kfwy.park.bi.report.dao.ILandlordReceivableBiDao;
import com.kfwy.park.bi.report.entity.LandlordReceivableBiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by gengtao on 2019/5/9.
 */
@Service
public class LandlordReceivableManagerImpl extends AbstractManager<LandlordReceivableBiEntity> implements ILandlordReceivableManager{
    @Autowired
    private ILandlordReceivableBiDao landlordReceivableBiDao;

    @Override
    protected IMyBatisBaseDao<LandlordReceivableBiEntity, Long> getMyBatisDao() {
        return landlordReceivableBiDao;
    }

    @Override
    public List<LandlordReceivableBiEntity> landlordReceivableInfo(Map<String, Object> map) {
        return landlordReceivableBiDao.landlordReceivableInfo(map);
    }
}
