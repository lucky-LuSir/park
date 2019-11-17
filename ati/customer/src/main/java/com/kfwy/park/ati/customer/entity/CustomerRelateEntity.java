package com.kfwy.park.ati.customer.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.customer.enums.ContactJob;
import com.kfwy.park.ati.customer.enums.ContactSexName;

/**
 * com.kfwy.park.ati.customer.entity
 * Created by kfwy_it_008+xpp on 2018/5/30 14:12.
 * 客户更多联系人列表(关联表)
 * 客户对联系人一对多
 */
public class CustomerRelateEntity extends BaseEntity {

    /**
     * 主键唯一标识
     */
    private String relateCode;

    /**
     * 关联外键(客户编码)
     */
    private String cusCode;

    /**
     * 联系人姓名
     */
    private String contactName;

    /**
     * 联系人性别
     */
    private String contactSex;

    /**
     * 联系人性别(字典)
     *@see com.kfwy.park.ati.customer.enums.ContactSexName
     */
    private String contactSexName;

    /**
     * 联系人电话
     */
    private String contactPhone;

    /**
     * 联系人职务(对应前端决策人)字典
     * @see ContactJob
     */
    private String contactJob;

    /**
     * 联系人职务名称
     */
    private String contactJobName;

    /**
     * 是否是主联系人
     */
    private boolean hasMainContact;


    /*----------字段分割线-----------*/


    public String getRelateCode() {
        return relateCode;
    }

    public void setRelateCode(String relateCode) {
        this.relateCode = relateCode;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactSex() {
        return contactSex;
    }

    public void setContactSex(String contactSex) {
        this.contactSex = contactSex;
    }

    public String getContactJob() {
        return contactJob;
    }

    public void setContactJob(String contactJob) {
        this.contactJob = contactJob;
    }

    public String getContactJobName() {
        return DictionaryStorage.get(ContactJob.class.getName(),this.getContactJob()).getName();
    }

    public void setContactJobName(String contactJobName) {
        this.contactJobName = contactJobName;
    }

    public String getContactSexName() {
        return DictionaryStorage.get(ContactSexName.class.getName(),this.getContactSex()).getName();
    }

    public void setContactSexName(String contactSexName) {
        this.contactSexName = contactSexName;
    }

    public boolean getHasMainContact() {
        return hasMainContact;
    }

    public void setHasMainContact(boolean hasMainContact) {
        this.hasMainContact = hasMainContact;
    }

}
