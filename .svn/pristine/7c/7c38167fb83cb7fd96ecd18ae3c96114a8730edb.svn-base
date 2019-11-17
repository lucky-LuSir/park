package com.kfwy.park.bi.report.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.bi.report.dao.ILandlordReceivableBiDao;
import com.kfwy.park.bi.report.entity.LandlordReceivableBiEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by gengtao on 2019/5/9.
 */
@Repository
public class LandlordReceivableBiDaoImpl extends AbstractMyBatisDao<LandlordReceivableBiEntity, Long> implements ILandlordReceivableBiDao{
    @Override
    public List<LandlordReceivableBiEntity> landlordReceivableInfo(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "landlordReceivableInfo", map);
    }
}
