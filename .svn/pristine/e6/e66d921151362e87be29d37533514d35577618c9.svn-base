package com.kfwy.park.pro.inspect.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.inspect.business.IInspectTaskObjManager;
import com.kfwy.park.pro.inspect.dao.IInspectTaskObjDao;
import com.kfwy.park.pro.inspect.entity.InspectTaskObjEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InspectTaskObjManagerImpl extends AbstractManager<InspectTaskObjEntity> implements IInspectTaskObjManager {
    @Autowired
    private IInspectTaskObjDao inspectTaskObjDao;

    @Override
    protected IMyBatisBaseDao<InspectTaskObjEntity, Long> getMyBatisDao() {
        return inspectTaskObjDao;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(String taskCode, List<InspectTaskObjEntity> entities) {
        Map<String, Object> map = new HashMap<>();
        map.put("taskCode",taskCode);
        //先删除后添加
        inspectTaskObjDao.deleteByTaskCode(map);
        inspectTaskObjDao.batchInsert(entities);
    }
}
