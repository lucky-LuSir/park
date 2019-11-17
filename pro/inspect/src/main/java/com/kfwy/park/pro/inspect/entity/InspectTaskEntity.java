package com.kfwy.park.pro.inspect.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.util.List;

/**
 * 物业检查任务表
 * @author gengtao
 * @since 2019-07-05 16:47:31
 */
public class InspectTaskEntity extends BaseEntity {
    /**
    *任务编号
    */
    private String taskCode;
    
    /**
    *任务名称
    */
    private String taskName;
    
    /**
    *描述
    */
    private String describe;

    /**
     *所属模板编号
     */
    private String templateCode;

    /**
     *任务所包含的检查事项
     */
    List<InspectObjectRuleEntity> objectRuleEntityList;


    /**
     *检查事项转为树形结构
     */
    private InspectObjectRuleEntity ObjectRuleTree;
    

    
    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }
    
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public List<InspectObjectRuleEntity> getObjectRuleEntityList() {
        return objectRuleEntityList;
    }

    public void setObjectRuleEntityList(List<InspectObjectRuleEntity> objectRuleEntityList) {
        this.objectRuleEntityList = objectRuleEntityList;
    }

    public InspectObjectRuleEntity getObjectRuleTree() {
        return ObjectRuleTree;
    }

    public void setObjectRuleTree(InspectObjectRuleEntity objectRuleTree) {
        ObjectRuleTree = objectRuleTree;
    }
}