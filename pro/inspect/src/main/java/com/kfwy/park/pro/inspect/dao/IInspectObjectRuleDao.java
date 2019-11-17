package com.kfwy.park.pro.inspect.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.inspect.entity.InspectObjectRuleEntity;

import java.util.List;
import java.util.Map;

public interface IInspectObjectRuleDao extends IMyBatisBaseDao<InspectObjectRuleEntity, Long> {
    public static final String SELECT_BY_TASK_CODE = "selectByTaskCode";
    public static final String DELETE_BY_TEMPLATE_CODE = "deleteByTemplateCode";

    List<InspectObjectRuleEntity> selectByTaskCode(Map<String, Object> map);

    void deleteByTemplateCode(Map<String, Object> map);
}
