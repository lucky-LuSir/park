package com.kfwy.park.pro.inspect.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.inspect.dao.IInspectObjectRuleDao;
import com.kfwy.park.pro.inspect.entity.InspectObjectRuleEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class InspectObjectRuleDaoImpl extends AbstractMyBatisDao<InspectObjectRuleEntity, Long> implements IInspectObjectRuleDao {
    @Override
    public List<InspectObjectRuleEntity> selectByTaskCode(Map<String, Object> map) {
       return this.getSqlSession().selectList(this.mapperNamespace + SELECT_BY_TASK_CODE, map);
    }

    @Override
    public void deleteByTemplateCode(Map<String, Object> map) {
        this.getSqlSession().update(this.mapperNamespace + DELETE_BY_TEMPLATE_CODE, map);
    }
}
