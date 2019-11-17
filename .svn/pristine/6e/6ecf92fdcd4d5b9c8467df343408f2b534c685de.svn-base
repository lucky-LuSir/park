package com.kfwy.park.pro.car.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.pro.car.enums.InOutType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gengtao on 2019/6/11.
 */
public class CarEntity extends BaseEntity {
    /**
     * 编号
     */
    private String cmCode;
    /**
     * 园区编码
     */
    private String pkCode;
    /**
     * 园区名
     */
    private String pkName;
    /**
     * 车牌号
     */
    private String licenseNum;
    /**
     * 进入时间
     */
    private Date inTime;
    /**
     * 出去时间
     */
    private Date outTime;
    /**
     * 访问人
     */
    private String visitor;
    /**
     * 访问人手机号
     */
    private String visitorPhone;
    /**
     * 被访客户编号
     */
    private String cusCode;
    /**
     * 被访客户公司名
     */
    private String company;
    /**
     * 其他被访客户名
     */
    private String otherCompany;

    /**
     * 来访人数
     */
    private BigDecimal visitorNum;
    /**
     * 来访目的
     */
    private String visitPurpose;

    /**
     * 被访人姓名
     */
    private String beVisitorName;

    /**
     * 被访人手机号
     */
    private String beVisitorPhone;

    /**
     * 来访人身份证号
     */
    private String visitorIdCard;

    /**
     * 进出类型
     */
    private String inOutType;

    /**
     * 进出类型名称
     * @see InOutType
     */
    private String inOutTypeName;


    /***************************get set***************************/

    public String getCmCode() {
        return cmCode;
    }

    public void setCmCode(String cmCode) {
        this.cmCode = cmCode;
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

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public Date getInTime() {
        return inTime;
    }

    public void setInTime(Date inTime) {
        this.inTime = inTime;
    }

    public Date getOutTime() {
        return outTime;
    }

    public void setOutTime(Date outTime) {
        this.outTime = outTime;
    }

    public String getVisitor() {
        return visitor;
    }

    public void setVisitor(String visitor) {
        this.visitor = visitor;
    }

    public String getVisitorPhone() {
        return visitorPhone;
    }

    public void setVisitorPhone(String visitorPhone) {
        this.visitorPhone = visitorPhone;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getOtherCompany() {
        return otherCompany;
    }

    public void setOtherCompany(String otherCompany) {
        this.otherCompany = otherCompany;
    }

    public BigDecimal getVisitorNum() {
        return visitorNum;
    }

    public void setVisitorNum(BigDecimal visitorNum) {
        this.visitorNum = visitorNum;
    }

    public String getVisitPurpose() {
        return visitPurpose;
    }

    public void setVisitPurpose(String visitPurpose) {
        this.visitPurpose = visitPurpose;
    }

    public String getBeVisitorName() {
        return beVisitorName;
    }

    public void setBeVisitorName(String beVisitorName) {
        this.beVisitorName = beVisitorName;
    }

    public String getBeVisitorPhone() {
        return beVisitorPhone;
    }

    public void setBeVisitorPhone(String beVisitorPhone) {
        this.beVisitorPhone = beVisitorPhone;
    }

    public String getVisitorIdCard() {
        return visitorIdCard;
    }

    public void setVisitorIdCard(String visitorIdCard) {
        this.visitorIdCard = visitorIdCard;
    }

    public String getInOutType() {
        return inOutType;
    }

    public void setInOutType(String inOutType) {
        this.inOutType = inOutType;
    }

    public String getInOutTypeName() {
        return DictionaryStorage.get(InOutType.class.getName(),this.getInOutType()).getName();
    }

    public void setInOutTypeName(String inOutTypeName) {
        this.inOutTypeName = inOutTypeName;
    }
}
