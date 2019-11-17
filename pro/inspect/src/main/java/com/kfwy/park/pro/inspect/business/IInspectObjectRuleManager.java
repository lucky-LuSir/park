package com.kfwy.park.pro.inspect.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.common.entity.Cascader;
import com.kfwy.park.pro.inspect.entity.InspectObjectRuleEntity;

import java.util.List;
import java.util.Map;

public interface IInspectObjectRuleManager extends IManager<InspectObjectRuleEntity> {
    /**
     *@Author gtaotao
     *@Date 2019-07-08 15:21:10
     *@Description 将模板中的事项规则以cascader树形返回
     *@Return
     **/
    Cascader ObjRuleCascader(List<InspectObjectRuleEntity> inspectObjectRuleEntity);

    /**
     *@Author gtaotao
     *@Date 2019-07-10 14:36:37
     *@Description 通过taskCode获取任务对应的检查事项
     *@Return
     **/
    List<InspectObjectRuleEntity> findByTaskCode(Map<String, Object> map);

    /**
     *@Author gtaotao
     *@Date 2019-07-10 14:42:43
     *@Description 删除检查事项
     *@Return
     **/
    void deleteObj(InspectObjectRuleEntity entity);

    /**
     *@Author gtaotao
     *@Date 2019-07-10 14:43:11
     *@Description 根据templateCode 删除检查事项
     *@Return
     **/
    void deleteByTemplateCode(Map<String, Object> map);

    /**
     *@Author gtaotao
     *@Date 2019-07-10 14:43:38
     *@Description 根据taskCode获取树形检查事项
     *@Return
     **/
    InspectObjectRuleEntity objWithTreeByTaskCode(Map<String, Object>map);

    /**
     *@Author gtaotao
     *@Date 2019-07-10 15:08:42
     *@Description 将检查事项以树形返回
     *@Return
     **/
    InspectObjectRuleEntity objToTree(InspectObjectRuleEntity rootEntity, List<InspectObjectRuleEntity> objectRuleEntityList);
}
