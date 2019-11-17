package com.kfwy.park.pro.inspect.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.pro.inspect.enums.InspectType;

import java.util.List;

/**
 * 物业检查模板表
 * @author gengtao
 * @since 2019-07-05 16:48:09
 */
public class InspectTemplateEntity extends BaseEntity {
    /**
    *模板编号
    */
    private String templateCode;
    
    /**
    *模板名称
    */
    private String templateName;
    
    /**
    *描述
    */
    private String describe;

    /**
     *检查类型
     */
    private String inspectType;

    /**
     *检查类型名称
     * @see InspectType
     */
    private String inspectTypeName;

    /**------------------------------关联检查事项---------------------*/
    private List<InspectObjectRuleEntity> objectRuleEntityList;


    /**-----------------------------getter setter--------------------*/
    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
    
    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }
    
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public List<InspectObjectRuleEntity> getObjectRuleEntityList() {
        return objectRuleEntityList;
    }

    public void setObjectRuleEntityList(List<InspectObjectRuleEntity> objectRuleEntityList) {
        this.objectRuleEntityList = objectRuleEntityList;
    }

    public String getInspectType() {
        return inspectType;
    }

    public void setInspectType(String inspectType) {
        this.inspectType = inspectType;
    }

    public String getInspectTypeName() {
        return DictionaryStorage.get(InspectType.class.getName(), this.getInspectType()).getName();
    }

    public void setInspectTypeName(String inspectTypeName) {
        this.inspectTypeName = inspectTypeName;
    }
}