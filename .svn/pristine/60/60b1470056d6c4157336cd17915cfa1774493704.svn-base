package com.kfwy.park.pro.inspect.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 物业检查得分信息表
 * @author gengtao
 * @since 2019-07-09 13:42:56
 */
public class InspectScoreEntity extends BaseEntity {
    /**
    *检查编号
    */
    private String inspectCode;

    /**
     *事项编号
     */
    private String objRlCode;

    /**
     *事项名称
     */
    private String objRlName;
    
    /**
    *事项评分
    */
    private BigDecimal grade;
    
    /**
    *事项得分
    */
    private BigDecimal score;
    
    /**
    *类型
    */
    private String type;
    
    /**
    *父事项编号
    */
    private String parentCode;
    
    /**
    *事项描述
    */
    private String describe;
    
    /**
    *文件编号
    */
    private String fileCode;

    /**
     *评分备注
     */
    private String remark;

    /**
     *包含的子节点
     */
    private List<InspectScoreEntity> children;

    
    public String getInspectCode() {
        return inspectCode;
    }

    public void setInspectCode(String inspectCode) {
        this.inspectCode = inspectCode;
    }
    
    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }
    
    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
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
    
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getFileCode() {
        return fileCode;
    }

    public void setFileCode(String fileCode) {
        this.fileCode = fileCode;
    }

    public String getObjRlName() {
        return objRlName;
    }

    public void setObjRlName(String objRlName) {
        this.objRlName = objRlName;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<InspectScoreEntity> getChildren() {
        return children;
    }

    public void setChildren(List<InspectScoreEntity> children) {
        this.children = children;
    }

    public String getObjRlCode() {
        return objRlCode;
    }

    public void setObjRlCode(String objRlCode) {
        this.objRlCode = objRlCode;
    }
}