package com.kfwy.park.ati.customer.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.customer.enums.ChannelStatus;

/**
 * Created by liuzhengyang on 2018/9/20.
 * 客户渠道
 */
public class CustomerChannelEntity extends BaseEntity{

    /**
     * 渠道编码
     * channel_code
     */
    private String channelCode;
    /**
     * 渠道类型
     * @see ChannelStatus
     * channel_status
     */
    private String channelStatus;
    /**
     * 渠道类型名称
     */
    private String channelStatusName;
    /**
     * 联系人姓名
     * user_name
     */
    private String userName;
    /**
     * 联系人电话
     * phone
     */
    private String phone;
    /**
     * 联系人性别
     * sex
     */
    private String sex;
    /**
     * 联系人职务
     * post
     */
    private String post;
    /**
     * 单位编码
     * company_code
     */
    private String companyCode;

    /**
     * 关联渠道单位信息
     */
    private CustomerChannelCpyEntity channelCpyEntity;


    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

    public String getChannelStatusName() {
        return DictionaryStorage.get(ChannelStatus.class.getName(),this.getChannelStatus()).getName();
    }

    public void setChannelStatusName(String channelStatusName) {
        this.channelStatusName = channelStatusName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPost() {
        return post;
    }

    public void setPost(String post) {
        this.post = post;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public CustomerChannelCpyEntity getChannelCpyEntity() {
        return channelCpyEntity;
    }

    public void setChannelCpyEntity(CustomerChannelCpyEntity channelCpyEntity) {
        this.channelCpyEntity = channelCpyEntity;
    }
}
