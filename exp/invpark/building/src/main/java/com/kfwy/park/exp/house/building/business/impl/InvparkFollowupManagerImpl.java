package com.kfwy.park.exp.house.building.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.exp.house.building.business.IInvparkFollowupManager;
import com.kfwy.park.exp.house.building.dao.IInvparkDbDao;
import com.kfwy.park.exp.house.building.dao.IInvparkFollowupDbDao;
import com.kfwy.park.exp.house.building.entity.InvparkEntity;
import com.kfwy.park.exp.house.building.entity.InvparkFollowupEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 拓展跟进
 * @Auth luming
 * @Date 2018/11/7 10:33
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class InvparkFollowupManagerImpl extends AbstractManager<InvparkFollowupEntity> implements IInvparkFollowupManager {

    @Autowired
    private IInvparkFollowupDbDao invparkFollowupDbDao;

    @Autowired
    private IInvparkDbDao invparkDbDao;

    @Override
    protected IMyBatisBaseDao<InvparkFollowupEntity, Long> getMyBatisDao() {
        return invparkFollowupDbDao;
    }

    @Override
    protected void afterCreate(InvparkFollowupEntity invparkFollowupEntity) {
        String expStatus = invparkFollowupEntity.getExpStatus();
        String itemCode = invparkFollowupEntity.getItemCode();

        InvparkEntity invparkEntity = new InvparkEntity();
        invparkEntity.setExpStatus(expStatus);
        invparkEntity.setItemCode(itemCode);

        invparkDbDao.update(invparkEntity);
    }
}
