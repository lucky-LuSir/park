package com.kfwy.park.bi.report.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.report.business.PremiumReceivableBiManager;
import com.kfwy.park.bi.report.dao.PremiumReceivableBiDao;
import com.kfwy.park.bi.report.entity.PremiumReceivableBiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by gengtao on 2019/4/17.
 */
@Service
public class PremiumReceivableBiManagerImpl extends AbstractManager<PremiumReceivableBiEntity> implements PremiumReceivableBiManager{
    @Autowired
    private PremiumReceivableBiDao premiumReceivableBiDao;

    @Override
    protected IMyBatisBaseDao<PremiumReceivableBiEntity, Long> getMyBatisDao() {
        return this.premiumReceivableBiDao;
    }

    @Override
    public List<PremiumReceivableBiEntity> selectReceivable(Map<String, Object> map) {
        return premiumReceivableBiDao.selectReceivable(map);
    }


}
