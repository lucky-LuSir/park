package com.kfwy.park.pro.inspect.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.inspect.dao.IInspectTaskObjDao;
import com.kfwy.park.pro.inspect.entity.InspectTaskObjEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class InspectTaskObjDaoImpl extends AbstractMyBatisDao<InspectTaskObjEntity, Long>  implements IInspectTaskObjDao {
    @Override
    public void deleteByTaskCode(Map<String, Object> map) {
        this.getSqlSession().delete(this.mapperNamespace + DELETE_BY_TASK_CODE, map);
    }
}
