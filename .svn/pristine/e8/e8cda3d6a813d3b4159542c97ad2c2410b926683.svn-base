package com.kfwy.park.pro.inspect.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.inspect.business.IInspectTaskManager;
import com.kfwy.park.pro.inspect.dao.IInspectTaskDao;
import com.kfwy.park.pro.inspect.entity.InspectTaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InspectTaskManagerImpl extends AbstractManager<InspectTaskEntity> implements IInspectTaskManager {
    @Autowired
    private IInspectTaskDao inspectTaskDao;

    @Override
    protected IMyBatisBaseDao<InspectTaskEntity, Long> getMyBatisDao() {
        return inspectTaskDao;
    }
}
