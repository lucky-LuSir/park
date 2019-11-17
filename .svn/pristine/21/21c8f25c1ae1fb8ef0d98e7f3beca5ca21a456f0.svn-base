package com.kfwy.park.bi.report.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.PayStatus;
import com.kfwy.park.exp.contract.enums.LdCtStatus;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gengtao on 2019/5/9.
 * 大房东应收报表实体
 */
public class LandlordReceivableBiEntity extends BaseEntity{
    /**
     * 合同编码
     */
    private String ldCode;

    /**
     * 租金编码
     */
    private String  rentCode;

    /**
     * 合同编号（自动生成的）
     */
    private String ldCtName;

    /**
     * 合同状态
     * @see LdCtStatus
     */
    private String ldCtStatus;

    /**
     * 合同状态名称
     */
    private String ldCtStatusName;

    /**
     * 园区名称
     */
    private String pkName;

    /**
     * 房东名字
     */
    private String landlordName;

    /**
     * 合同开始日期
     */
    private Date contractStartDate;

    /**
     * 合同结束日期
     */
    private Date contractEndDate;


    /**
     * 费用开始日期
     */
    private Date costStartTime;

    /**
     * 费用结束日期
     */
    private Date costEndTime;

    /**
     * 付款截止日期
     */
    private Date deadline;

    /**
     *@see PayStatus
     *  付款状态
     */
    private String payStatus;

    /**
     * 支付状态名称
     */
    private String payStatusName;

    /**
     * 总应付金额
     */
    private BigDecimal totalPrice;

    /**
     * 已付总金额
     */
    private BigDecimal paidPrice;

    /**
     * 优惠金额
     */
    private BigDecimal discountsPrice;

 /**----------------------------   get and set-------------------------------------**/

    public String getLdCode() {
        return ldCode;
    }

    public void setLdCode(String ldCode) {
        this.ldCode = ldCode;
    }

    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
    }

    public String getLdCtName() {
        return ldCtName;
    }

    public void setLdCtName(String ldCtName) {
        this.ldCtName = ldCtName;
    }

    public String getLdCtStatus() {
        return ldCtStatus;
    }

    public void setLdCtStatus(String ldCtStatus) {
        this.ldCtStatus = ldCtStatus;
    }

    public String getLdCtStatusName() {
        return DictionaryStorage.get(LdCtStatus.class.getName(), this.getLdCtStatus()).getName();
    }

    public void setLdCtStatusName(String ldCtStatusName) {
        this.ldCtStatusName = ldCtStatusName;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getLandlordName() {
        return landlordName;
    }

    public void setLandlordName(String landlordName) {
        this.landlordName = landlordName;
    }

    public Date getCostStartTime() {
        return costStartTime;
    }

    public void setCostStartTime(Date costStartTime) {
        this.costStartTime = costStartTime;
    }

    public Date getCostEndTime() {
        return costEndTime;
    }

    public void setCostEndTime(Date costEndTime) {
        this.costEndTime = costEndTime;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayStatusName() {
        return DictionaryStorage.get(PayStatus.class.getName(), this.getPayStatus()).getName();
    }

    public void setPayStatusName(String payStatusName) {
        this.payStatusName = payStatusName;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
    }

    public BigDecimal getDiscountsPrice() {
        return discountsPrice;
    }

    public void setDiscountsPrice(BigDecimal discountsPrice) {
        this.discountsPrice = discountsPrice;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }
}
