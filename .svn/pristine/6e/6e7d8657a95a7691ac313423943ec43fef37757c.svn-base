package com.kfwy.park.ati.customer.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.customer.enums.*;

import java.util.Date;
import java.util.List;

/**
 * @author wangchuang
 * @Descirption 客户跟进实体
 * @Date 2018/5/30 19:06
 */
public class CustomerFollowupEntity extends BaseEntity {

    /**
     * 客户跟进编码
     */
    private String followupCode;
    /**
     * 客户编码
     */
    private String cusCode;
    /**
     * 客户姓名
     */
    private String cusName;
    /**
     * 客户联系方式
     */
    private String cusPhone;

    /**
     * 客户类型（字典）
     * @see CustomerType
     */
    private String customerType;

    /**
     * 客户类型名称（字典）
     */
    private String customerTypeName;
    /**
     * 园区编码
     */
    private String pkCode;
    /**
     * 园区名称
     */
    private String pkName;
    /**
     * 客户意向房源
     */
    private String cusIntentPark;

    /**
     * 客户带看类型
     * @see ReceptionType
     */
    private String receptionType;

    /**
     * 客户带看类型名称
     */
    private String receptionTypeName;

    /**
     * 客户跟进(来访)类型
     * @see FollowupType
     */
    private String followupType;
    /**
     * 跟进(来访)类型名称
     */
    private String followupTypeName;
    /**
     * 渠道来源
     * @see DitchType
     */
    private String cusChannel;

    /**
     * 渠道来源名称
     */
    private String cusChannelName;

    /**
     * 跟进客户状态
     * @see CusStatus
     */
    private String cusStatus;
    /**
     * 跟进客户状态名称
     */
    private String cusStatusName;
    /**
     * 不合适原因
     * @see ImproperReason
     */
    private String improperReason;
    /**
     * 不合适原因名称
     */
    private String improperReasonName;
    /**
     * 备注不合适原因
     */
    private String improperReasonNote;
    /**
     * 来访结果
     */
    private String followupRes;
    /**
     * 看房时间
     */
    private Date followupTime;
    /**
     * 接待人编码
     */
    private String empCode;
    /**
     * 接待人姓名
     */
    private String empName;
    /**
     * 接待人电话
     */
    private String empPhone;


    /*----------------------中介信息-----------------------*/

    /**
     * 中介公司编码
     */
    private String midCpyCode;
    /**
     * 中介公司名称
     */
    private String midCpyName;
    /**
     * 中介联系人编码
     */
    private String midCode;
    /**
     * 中介联系人姓名
     */
    private String midName;
    /**
     * 中介联系人电话
     */
    private String midPhone;
    /**
     * 中介联系人职务
     */
    private String midPosition;
    /**
     * 客户意向房源列表
     */
    private List<CustomerIntentParkEntity> cusIntentParkList;

    /*----------------------统计字段-----------------------*/
    /*
    *每个园区每个人的跟进次数
     */
    private Integer pkByCusCount;

    /*---------------------------------------------*/
    /*
    *附件
     */
    private String attachment;


    /*=====================================字段分割线===========================================*/

    public String getFollowupCode() {
        return followupCode;
    }

    public void setFollowupCode(String followupCode) {
        this.followupCode = followupCode;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
    }

    public String getCusIntentPark() {
        return cusIntentPark;
    }

    public void setCusIntentPark(String cusIntentPark) {
        this.cusIntentPark = cusIntentPark;
    }

    public String getReceptionType() {
        return receptionType;
    }

    public void setReceptionType(String receptionType) {
        this.receptionType = receptionType;
    }

    public String getReceptionTypeName() {
        return DictionaryStorage.get(ReceptionType.class.getName(),this.getReceptionType()).getName();
    }

    public void setReceptionTypeName(String receptionTypeName) {
        this.receptionTypeName = receptionTypeName;
    }

    public String getFollowupType() {
        return followupType;
    }

    public void setFollowupType(String followupType) {
        this.followupType = followupType;
    }

    public String getFollowupTypeName() {
        return DictionaryStorage.get(FollowupType.class.getName(),this.getFollowupType()).getName();
    }

    public void setFollowupTypeName(String followupTypeName) {
        this.followupTypeName = followupTypeName;
    }

    public String getCusChannel() {
        return cusChannel;
    }

    public void setCusChannel(String cusChannel) {
        this.cusChannel = cusChannel;
    }

    public String getCusStatus() {
        return cusStatus;
    }

    public void setCusStatus(String cusStatus) {
        this.cusStatus = cusStatus;
    }

    public String getCusChannelName() {
        return DictionaryStorage.get(DitchType.class.getName(), this.getCusChannel()).getName();
    }

    public void setCusChannelName(String cusChannelName) {
        this.cusChannelName = cusChannelName;
    }

    public String getCusStatusName() {
        return DictionaryStorage.get(CusStatus.class.getName(),this.getCusStatus()).getName();
    }

    public void setCusStatusName(String cusStatusName) {
        this.cusStatusName = cusStatusName;
    }

    public String getImproperReason() {
        return improperReason;
    }

    public void setImproperReason(String improperReason) {
        this.improperReason = improperReason;
    }

    public String getImproperReasonName() {
        return DictionaryStorage.get(ImproperReason.class.getName(),this.getImproperReason()).getName();
    }

    public void setImproperReasonName(String improperReasonName) {
        this.improperReasonName = improperReasonName;
    }

    public String getImproperReasonNote() {
        return improperReasonNote;
    }

    public void setImproperReasonNote(String improperReasonNote) {
        this.improperReasonNote = improperReasonNote;
    }

    public String getFollowupRes() {
        return followupRes;
    }

    public void setFollowupRes(String followupRes) {
        this.followupRes = followupRes;
    }

    public Date getFollowupTime() {
        return followupTime;
    }

    public void setFollowupTime(Date followupTime) {
        this.followupTime = followupTime;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerTypeName() {
        return DictionaryStorage.get(CustomerType.class.getName(),this.getCustomerType()).getName();
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public String getMidCpyCode() {
        return midCpyCode;
    }

    public void setMidCpyCode(String midCpyCode) {
        this.midCpyCode = midCpyCode;
    }

    public String getMidCpyName() {
        return midCpyName;
    }

    public void setMidCpyName(String midCpyName) {
        this.midCpyName = midCpyName;
    }

    public String getMidCode() {
        return midCode;
    }

    public void setMidCode(String midCode) {
        this.midCode = midCode;
    }

    public String getMidName() {
        return midName;
    }

    public void setMidName(String midName) {
        this.midName = midName;
    }

    public String getMidPhone() {
        return midPhone;
    }

    public void setMidPhone(String midPhone) {
        this.midPhone = midPhone;
    }

    public String getMidPosition() {
        return midPosition;
    }

    public void setMidPosition(String midPosition) {
        this.midPosition = midPosition;
    }

    public List<CustomerIntentParkEntity> getCusIntentParkList() {
        return cusIntentParkList;
    }

    public void setCusIntentParkList(List<CustomerIntentParkEntity> cusIntentParkList) {
        this.cusIntentParkList = cusIntentParkList;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public Integer getPkByCusCount() {
        return pkByCusCount;
    }

    public void setPkByCusCount(Integer pkByCusCount) {
        this.pkByCusCount = pkByCusCount;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
