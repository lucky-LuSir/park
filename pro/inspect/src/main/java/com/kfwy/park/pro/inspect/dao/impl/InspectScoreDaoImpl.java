package com.kfwy.park.pro.inspect.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.inspect.dao.IInspectScoreDao;
import com.kfwy.park.pro.inspect.entity.InspectScoreEntity;
import org.springframework.stereotype.Repository;

@Repository
public class InspectScoreDaoImpl extends AbstractMyBatisDao<InspectScoreEntity, Long> implements IInspectScoreDao {
}
