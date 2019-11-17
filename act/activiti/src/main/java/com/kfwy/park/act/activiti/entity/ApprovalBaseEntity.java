package com.kfwy.park.act.activiti.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.act.activiti.emums.ApprovalStatus;

import java.util.Date;

/**
 * Created by luming on 2019/3/22.
 * 审批列表
 */
public class ApprovalBaseEntity extends BaseEntity {

    /**
     * 审批编码
     */
    private String approvalCode;

    /**
     * 申请编码
     */
    private String applyCode;

    /**
     * 申请人编码
     */
    private String applicantCode;

    /**
     * 申请人名字
     */
    private String applicantName;

    /**
     * 申请结果
     * @see ApprovalStatus
     */
    private String approvalStatus;

    /**
     * 审批结果名称
     */
    private String approvalStatusName;

    /**
     * 审批结果理由
     */
    private String approveComment;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 流程实例ID
     */
    private String executionId;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 流程定义ID
     */
    private String processDefinitionId;

    /**
     * 流程定义Key
     */
    private String processDefinitionKey;

    /*----------------------------------------非表内字段----------------------------------------------------*/
    /**
     * 节点名称
     */
    private String actName;

    /*--------------------------------------- get set 方法 ---------------------------------------*/

    public String getApprovalCode() {
        return approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public String getApplicantCode() {
        return applicantCode;
    }

    public void setApplicantCode(String applicantCode) {
        this.applicantCode = applicantCode;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalStatusName() {
        return DictionaryStorage.get(ApprovalStatus.class.getName(), this.getApprovalStatus()).getName();
    }

    public void setApprovalStatusName(String approvalStatusName) {
        this.approvalStatusName = approvalStatusName;
    }

    public String getApproveComment() {
        return approveComment;
    }

    public void setApproveComment(String approveComment) {
        this.approveComment = approveComment;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getExecutionId() {
        return executionId;
    }

    public void setExecutionId(String executionId) {
        this.executionId = executionId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }
}
