package com.kfwy.park.ati.customer.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.customer.enums.FollowupType;

/**
 * @Description 渠道跟进
 * @Auth luming
 * @Date 2018/10/24 9:49
 * @Version 1.0
 * @Param
 * @return
 */
public class CustomerChannelFollowupEntity extends BaseEntity{

    /**
     * 渠道跟进code
     */
    private String followupCode;

    /**
     * 渠道编码，关联渠道主表channelCode
     */
    private String channelCode;

    /**
     * 渠道公司code
     */
    private String companyCode;

    /**
     * 跟进类型
     * @see FollowupType
     */
    private String followupType;

    /**
     * 跟进类型名字
     */
    private String followupTypeName;

    /**
     * 跟进结果
     */
    private String followupResult;

    /**
     * 封装跟进实体
     */
    private CustomerChannelEntity channelEntity;

    /**
     * 封装跟进单位实体
     */
    private CustomerChannelCpyEntity channelCpyEntity;


    /**********************************【封装渠道、渠道单位字段】**********************************/
//    /**
//     * 渠道类型
//     * @see ChannelStatus
//     */
//    private String channelStatus;
//
//    /**
//     * 跟进渠道类型
//     */
//    private String channelStatusName;
//
//    /**
//     * 联系人姓名
//     */
//    private String userName;
//
//    /**
//     * 联系人电话
//     */
//    private String phone;
//
//    /**
//     * 性别
//     */
//    private String sex;
//
//    /**
//     * 职位
//     */
//    private String post;
//
//    /**
//     * 渠道单位名
//     */
//    private String channelCpyName;
//
//    /**
//     * 渠道单位描述
//     */
//    private String channelCpyRemark;
//
//    /**
//     * 渠道单位地址
//     */
//    private String channelCpyAddress;


    public String getFollowupCode() {
        return followupCode;
    }

    public void setFollowupCode(String followupCode) {
        this.followupCode = followupCode;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getFollowupType() {
        return followupType;
    }

    public void setFollowupType(String followupType) {
        this.followupType = followupType;
    }

    public String getFollowupTypeName() {
        return DictionaryStorage.get(FollowupType.class.getName(), this.getFollowupType()).getName();
    }

    public void setFollowupTypeName(String followupTypeName) {
        this.followupTypeName = followupTypeName;
    }

    public String getFollowupResult() {
        return followupResult;
    }

    public void setFollowupResult(String followupResult) {
        this.followupResult = followupResult;
    }

    public CustomerChannelEntity getChannelEntity() {
        return channelEntity;
    }

    public void setChannelEntity(CustomerChannelEntity channelEntity) {
        this.channelEntity = channelEntity;
    }

    public CustomerChannelCpyEntity getChannelCpyEntity() {
        return channelCpyEntity;
    }

    public void setChannelCpyEntity(CustomerChannelCpyEntity channelCpyEntity) {
        this.channelCpyEntity = channelCpyEntity;
    }
}
