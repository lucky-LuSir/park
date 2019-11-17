package com.kfwy.park.pro.inspect.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.inspect.dao.IInspectTaskDao;
import com.kfwy.park.pro.inspect.entity.InspectTaskEntity;
import org.springframework.stereotype.Repository;

@Repository
public class InspectTaskDaoImpl extends AbstractMyBatisDao<InspectTaskEntity, Long> implements IInspectTaskDao {
}
