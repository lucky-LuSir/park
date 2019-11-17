package com.kfwy.park.pro.inspect.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.inspect.dao.IInspectInfoDao;
import com.kfwy.park.pro.inspect.entity.InspectInfoEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class InspectInfoDaoImpl extends AbstractMyBatisDao<InspectInfoEntity, Long> implements IInspectInfoDao {
}
