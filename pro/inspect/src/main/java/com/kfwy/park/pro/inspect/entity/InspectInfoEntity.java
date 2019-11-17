package com.kfwy.park.pro.inspect.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.pro.inspect.enums.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 物业检查信息记录表
 * @author gengtao
 * @since 2019-07-05 16:47:31
 */
public class InspectInfoEntity extends BaseEntity {
    /**
    *检查编号
    */
    private String inspectCode;

    /**
     *模板编号
     */
    private String templateCode;

    /**
     *任务编号
     */
    private String taskCode;
    
    /**
    *检查日期
    */
    private Date inspectTime;
    
    /**
    *责任人编号
    */
    private String beInspectedEmpCode;
    
    /**
    *责任人姓名
    */
    private String beInspectedEmpName;
    
    /**
    *检查人编号
    */
    private String inspectEmpCode;
    
    /**
    *检查人姓名
    */
    private String inspectEmpName;
    
    /**
    *园区编号
    */
    private String pkCode;
    
    /**
    *园区名称
    */
    private String pkName;

    /**
     *总评分
     */
    private BigDecimal totalGrade;

    /**
     *总得分
     */
    private BigDecimal totalScore;

    /**
     *检查类型
     */
    private String inspectType;

    /**
     *检查类型名称
     * @see InspectType
     */
    private String inspectTypeName;

    /**
     *包含的所有检查项目
     */
    List<InspectScoreEntity> scoreEntityList;

    /**
     *包含的检查项目转为树形
     */
    InspectScoreEntity scoreEntityTree;


    public String getInspectCode() {
        return inspectCode;
    }

    public void setInspectCode(String inspectCode) {
        this.inspectCode = inspectCode;
    }

    public String getTemplateCode() {
        return templateCode;
    }

    public void setTemplateCode(String templateCode) {
        this.templateCode = templateCode;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public Date getInspectTime() {
        return inspectTime;
    }

    public void setInspectTime(Date inspectTime) {
        this.inspectTime = inspectTime;
    }
    
    public String getBeInspectedEmpCode() {
        return beInspectedEmpCode;
    }

    public void setBeInspectedEmpCode(String beInspectedEmpCode) {
        this.beInspectedEmpCode = beInspectedEmpCode;
    }
    
    public String getBeInspectedEmpName() {
        return beInspectedEmpName;
    }

    public void setBeInspectedEmpName(String beInspectedEmpName) {
        this.beInspectedEmpName = beInspectedEmpName;
    }
    
    public String getInspectEmpCode() {
        return inspectEmpCode;
    }

    public void setInspectEmpCode(String inspectEmpCode) {
        this.inspectEmpCode = inspectEmpCode;
    }
    
    public String getInspectEmpName() {
        return inspectEmpName;
    }

    public void setInspectEmpName(String inspectEmpName) {
        this.inspectEmpName = inspectEmpName;
    }
    
    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
    }
    
    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public BigDecimal getTotalGrade() {
        return totalGrade;
    }

    public void setTotalGrade(BigDecimal totalGrade) {
        this.totalGrade = totalGrade;
    }

    public BigDecimal getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(BigDecimal totalScore) {
        this.totalScore = totalScore;
    }

    public List<InspectScoreEntity> getScoreEntityList() {
        return scoreEntityList;
    }

    public void setScoreEntityList(List<InspectScoreEntity> scoreEntityList) {
        this.scoreEntityList = scoreEntityList;
    }

    public InspectScoreEntity getScoreEntityTree() {
        return scoreEntityTree;
    }

    public void setScoreEntityTree(InspectScoreEntity scoreEntityTree) {
        this.scoreEntityTree = scoreEntityTree;
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