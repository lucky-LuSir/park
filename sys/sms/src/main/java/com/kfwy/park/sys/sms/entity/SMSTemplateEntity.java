package com.kfwy.park.sys.sms.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.sys.sms.enums.TemplateType;

/**
 * @Description 短信模板实体
 * @Auth luming
 * @Date 2019/7/10 17:39
 * @Version 1.0
 * @Param
 * @return
 */
public class SMSTemplateEntity extends BaseEntity {
    /**
     * 短信模板code
     */
    private String smsCode;

    /**
     * 短信模板ID
     */
    private String templateId;

    /**
     * 模板签名
     */
    private String templateSignName;

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
     * 短信模板内容
     */
    private String templateContent;

    /*---------------------------------------------------------------*/

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getTemplateSignName() {
        return templateSignName;
    }

    public void setTemplateSignName(String templateSignName) {
        this.templateSignName = templateSignName;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
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

    public String getTemplateContent() {
        return templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }
}
