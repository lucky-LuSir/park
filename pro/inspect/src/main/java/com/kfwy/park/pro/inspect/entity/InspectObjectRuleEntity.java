package com.kfwy.park.pro.inspect.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 物业检查事项规则
 * @author gengtao
 * @since 2019-07-05 16:47:31
 */
public class InspectObjectRuleEntity extends BaseEntity {
    /**
    *规则编号
    */
    private String objRlCode;
    
    /**
    *规则名称
    */
    private String objRlName;
    
    /**
    *规则所占分数
    */
    private BigDecimal grade;
    
    /**
    *规则类型 1:节点 2:具体规则
    */
    private String type;
    
    /**
    *上级节点编号
    */
    private String parentCode;
    
    /**
    *所属模板编号
    */
    private String templateCode;
    
    /**
    *规则描述
    */
    private String describe;

    /**-------------------------------封装得分 和 文件-----------------------*/
    /**
     *得分
     */
    private BigDecimal score;
    /**
     *文件地址
     */
    private String fileCode;
    /**
     *包含子事项
     */
    private List<InspectObjectRuleEntity> children;
    

    
    public String getObjRlCode() {
        return objRlCode;
    }

    public void setObjRlCode(String objRlCode) {
        this.objRlCode = objRlCode;
    }
    
    public String getObjRlName() {
        return objRlName;
    }

    public void setObjRlName(String objRlName) {
        this.objRlName = objRlName;
    }
    
    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
        this.parentCode = parentCode;
    }
    
    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }
    
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public List<InspectObjectRuleEntity> getChildren() {
        return children;
    }

    public void setChildren(List<InspectObjectRuleEntity> children) {
        this.children = children;
    }
}