package com.kfwy.park.ati.agent.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.agent.business.IMiddlemanManager;
import com.kfwy.park.ati.agent.dao.IMiddlemanDbDao;
import com.kfwy.park.ati.agent.entity.MiddlemanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * Created by xiongzhan on 2018/6/2.
 */
@Service
public class MiddlemanManagerImpl extends AbstractManager<MiddlemanEntity> implements IMiddlemanManager {

    @Autowired
    private IMiddlemanDbDao middlemanDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.middlemanDbDao;
    }

}
