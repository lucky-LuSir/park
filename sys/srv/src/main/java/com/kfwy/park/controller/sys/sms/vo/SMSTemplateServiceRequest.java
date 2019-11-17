package com.kfwy.park.controller.sys.sms.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.sys.sms.entity.SMSTemplateEntity;

/**
 * @Description 短信模板
 * @Auth luming
 * @Date 2019/7/11 11:35
 * @Version 1.0
 * @Param
 * @return
 */
public class SMSTemplateServiceRequest extends AbstractServiceRequest<SMSTemplateEntity> {
    /**
     * 接收短信的手机号码
     */
    private String phoneNumbers;

    /**
     * 短信模板参数
     */
    private String templateParam;

    /**
     * 封装参数，短信发送成功后，封装响应结果
     */
    private String data;

    /*---------------------------------- get set ----------------------------------*/

    public String getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(String phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getTemplateParam() {
        return templateParam;
    }

    public void setTemplateParam(String templateParam) {
        this.templateParam = templateParam;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
