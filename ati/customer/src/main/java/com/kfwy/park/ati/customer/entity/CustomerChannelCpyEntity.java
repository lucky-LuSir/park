package com.kfwy.park.ati.customer.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * Created by liuzhengyang on 2018/9/20.
 * 客户渠道单位
 */
public class CustomerChannelCpyEntity extends BaseEntity{

    /**
     * 单位编码
     * company_code
     */
    private String companyCode;
    /**
     * 单位名称
     * channel_cpy_name
     */
    private String channelCpyName;
    /**
     * 单位描述
     * channel_cpy_remark
     */
    private String channelCpyRemark;
    /**
     * 单位地址
     * channel_cpy_address
     */
    private String channelCpyAddress;


    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getChannelCpyName() {
        return channelCpyName;
    }

    public void setChannelCpyName(String channelCpyName) {
        this.channelCpyName = channelCpyName;
    }

    public String getChannelCpyRemark() {
        return channelCpyRemark;
    }

    public void setChannelCpyRemark(String channelCpyRemark) {
        this.channelCpyRemark = channelCpyRemark;
    }

    public String getChannelCpyAddress() {
        return channelCpyAddress;
    }

    public void setChannelCpyAddress(String channelCpyAddress) {
        this.channelCpyAddress = channelCpyAddress;
    }
}
