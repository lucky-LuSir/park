package com.kfwy.park.bi.report.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.bi.report.dao.PremiumReceivableBiDao;
import com.kfwy.park.bi.report.entity.PremiumReceivableBiEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by gengtao on 2019/4/17.
 */
@Repository
public class PremiumReceivableBiDaoImpl extends AbstractMyBatisDao<PremiumReceivableBiEntity, Long> implements PremiumReceivableBiDao {
    @Override
    public List<PremiumReceivableBiEntity> selectReceivable(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace+SELECT_RECEIVABLE, map);
    }
}
