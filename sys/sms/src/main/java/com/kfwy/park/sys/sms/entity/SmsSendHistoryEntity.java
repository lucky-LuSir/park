package com.kfwy.park.sys.sms.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.sys.sms.enums.TemplateType;

/**
 * @Description 短信发送历史记录
 * @Auth luming
 * @Date 2019/7/23 15:26
 * @Version 1.0
 * @Param
 * @return
 */
public class SmsSendHistoryEntity extends BaseEntity {
    /**
     * 发送短信Code
     */
    private String sendCode;

    /**
     * 短信模板code
     */
    private String smsCode;

    /**
     * 短信模板名称
     */
    private String templateName;

    /**
     * 短信模板类型
     * @see TemplateType
     */
    private String templateType;

    /**
     * 短信模板类型名称
     */
    private String templateTypeName;



    /**
     * 接收短信的手机号码
     */
    private String phoneNum;

    /**
     * 短信发送日期和时间
     */
    private String sendDate;

    /**
     * 短信接收日期和时间
     */
    private String receiveDate;

    /**
     * 是否接受成功
     */
    private Boolean success;

    /**
     * 状态报告编码
     */
    private String errCode;

    /**
     * 状态报告说明
     */
    private String errMsg;

    /**
     * 短信长度
     */
    private String smsSize;

    /**
     * 发送序列号
     */
    private String bizId;

    /**
     * 短信内容
     */
    private String content;

    /**
     * 短信模板ID（阿里云生成）
     */
    private String templateCode;

    /**
     * 模板签名（阿里云生成）
     */
    private String signName;

    /*---------------------------------------------------------------*/

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

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

    public String getTemplateType() {
        return templateType;
    }

    public void setTemplateType(String templateType) {
        this.templateType = templateType;
    }

    public String getTemplateTypeName() {
        return DictionaryStorage.get(TemplateType.class.getName(), this.getTemplateType()).getName();
    }

    public void setTemplateTypeName(String templateTypeName) {
        this.templateTypeName = templateTypeName;
    }

    public String getSendCode() {
        return sendCode;
    }

    public void setSendCode(String sendCode) {
        this.sendCode = sendCode;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(String receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public String getSmsSize() {
        return smsSize;
    }

    public void setSmsSize(String smsSize) {
        this.smsSize = smsSize;
    }

    public String getBizId() {
        return bizId;
    }

    public void setBizId(String bizId) {
        this.bizId = bizId;
    }

    @Override
    public String toString() {
        return "SmsSendHistoryEntity{" +
                "sendCode='" + sendCode + '\'' +
                ", smsCode='" + smsCode + '\'' +
                ", templateName='" + templateName + '\'' +
                ", templateType='" + templateType + '\'' +
                ", templateTypeName='" + templateTypeName + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", sendDate='" + sendDate + '\'' +
                ", receiveDate='" + receiveDate + '\'' +
                ", success=" + success +
                ", errCode='" + errCode + '\'' +
                ", errMsg='" + errMsg + '\'' +
                ", smsSize='" + smsSize + '\'' +
                ", bizId='" + bizId + '\'' +
                ", content='" + content + '\'' +
                ", templateCode='" + templateCode + '\'' +
                ", signName='" + signName + '\'' +
                ", id=" + id +
                ", cpyCode='" + cpyCode + '\'' +
                ", cpyName='" + cpyName + '\'' +
                ", ownerCode='" + ownerCode + '\'' +
                ", ownerName='" + ownerName + '\'' +
                ", ownerDeptCode='" + ownerDeptCode + '\'' +
                ", ownerDeptName='" + ownerDeptName + '\'' +
                ", createCode='" + createCode + '\'' +
                ", createName='" + createName + '\'' +
                ", createDeptCode='" + createDeptCode + '\'' +
                ", createDeptName='" + createDeptName + '\'' +
                ", lastUpdateCode='" + lastUpdateCode + '\'' +
                ", lastUpdateName='" + lastUpdateName + '\'' +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", isDeleted=" + isDeleted +
                ", remark='" + remark + '\'' +
                '}';
    }
}
