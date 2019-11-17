package com.kfwy.park.pro.inspect.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.pro.inspect.business.IInspectObjectRuleManager;
import com.kfwy.park.pro.inspect.business.IInspectTemplateManager;
import com.kfwy.park.pro.inspect.dao.IInspectTemplateDao;
import com.kfwy.park.pro.inspect.entity.InspectObjectRuleEntity;
import com.kfwy.park.pro.inspect.entity.InspectTemplateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class InspectTemplateManagerImpl extends AbstractManager<InspectTemplateEntity> implements IInspectTemplateManager {
    @Autowired
    private IInspectTemplateDao inspectTemplateDao;
    @Autowired
    private IInspectObjectRuleManager inspectObjectRuleManager;

    @Override
    protected IMyBatisBaseDao<InspectTemplateEntity, Long> getMyBatisDao() {
        return inspectTemplateDao;
    }

    @Override
    public void createTemplate(InspectTemplateEntity templateEntity) {
        templateEntity.setTemplateCode(UUID.randomUUID().toString().replaceAll("-",""));
        this.create(templateEntity);

        //创建模板后，在事项规则表中添加一个该模板的根节点，节点名称为模板名称
        String templateName = templateEntity.getTemplateName();
        InspectObjectRuleEntity objectRuleEntity = new InspectObjectRuleEntity();
        objectRuleEntity.setObjRlCode(UUID.randomUUID().toString().replaceAll("-",""));
        objectRuleEntity.setObjRlName(templateName);
        objectRuleEntity.setDescribe(templateName);
        objectRuleEntity.setParentCode("0");
        objectRuleEntity.setType("1");
        objectRuleEntity.setTemplateCode(templateEntity.getTemplateCode());
        inspectObjectRuleManager.create(objectRuleEntity);
    }

    @Override
    public void deleteTemplate(InspectTemplateEntity templateEntity) {
        templateEntity.setIsDeleted(true);
        this.update(templateEntity);

        //删除该模板的所有任务

        //虚拟删除该模板对应的检查事项
//        Map<String, Object> map = new HashMap<>();
//        map.put("isDeleted",true);
//        map.put("templateCode",templateEntity.getTemplateCode());
//        inspectObjectRuleManager.deleteByTemplateCode(map);
    }
}
