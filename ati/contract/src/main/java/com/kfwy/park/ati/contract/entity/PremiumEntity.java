package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.PayStatus;
import com.kfwy.park.ati.contract.enums.ReceivingChannel;
import com.kfwy.park.ati.contract.enums.TaxPoint;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 保险费缴费
 * @Auth luming
 * @Date 2018/11/15 19:17
 * @Version 1.0
 * @Param
 * @return
 */
public class PremiumEntity extends BaseEntity{

    /**
     * 保险缴费编号
     */
    private String premiumCode;

    /**
     * 合同编号
     */
    private String ctCode;

    /**
     * 保险费开始日期
     */
    private Date premiumStartDate;

    /**
     * 保险费结束日期
     */
    private Date premiumEndDate;

    /**
     * 截至日期
     */
    private Date deadline;

    /**
     * 投保金额
     */
    private BigDecimal premiumAmount;

    /**
     * 应收保险费
     */
    private BigDecimal receivablePremium;

    /**
     * 已收保险费
     */
    private BigDecimal paidPrice;

    /**
     * 本次付款
     */
    private BigDecimal thePayment;

    /**
     * 是否含税
     */
    private Boolean hasTax;

    /**
     * 发票税点（字典）
     * @see TaxPoint
     */
    private String taxPoint;

    /**
     * 发票税点名称（字典）
     */
    private String taxPointName;

    /**
     * 税金
     */
    private BigDecimal taxPrice;

    /**
     * 合计应收金额
     */
    private BigDecimal totalPrice;

    /**
     * 收款渠道（字典）
     * @see ReceivingChannel
     */
    private String receivingChannel;

    /**
     * 收款渠道名称（字典）
     */
    private String receivingChannelName;

    /**
     * 收款渠道单号
     */
    private String receivingChannelCode;

    /**
     * 收款时间
     */
    private Date receivingDate;

    /**
     * 支付时间
     */
    private Date payDate;

    /**
     * 状态（字典）
     * @see PayStatus
     */
    private String payStatus;

    /**
     * 状态名称（字典）
     */
    private String payStatusName;

    /***************************************************************
     * get,set 方法
     **************************************************************/

    public String getPremiumCode() {
        return premiumCode;
    }

    public void setPremiumCode(String premiumCode) {
        this.premiumCode = premiumCode;
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public Date getPremiumStartDate() {
        return premiumStartDate;
    }

    public void setPremiumStartDate(Date premiumStartDate) {
        this.premiumStartDate = premiumStartDate;
    }

    public Date getPremiumEndDate() {
        return premiumEndDate;
    }

    public void setPremiumEndDate(Date premiumEndDate) {
        this.premiumEndDate = premiumEndDate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public BigDecimal getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(BigDecimal premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public BigDecimal getReceivablePremium() {
        return receivablePremium;
    }

    public void setReceivablePremium(BigDecimal receivablePremium) {
        this.receivablePremium = receivablePremium;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
    }

    public BigDecimal getThePayment() {
        return thePayment;
    }

    public void setThePayment(BigDecimal thePayment) {
        this.thePayment = thePayment;
    }

    public Boolean getHasTax() {
        return hasTax;
    }

    public void setHasTax(Boolean hasTax) {
        this.hasTax = hasTax;
    }

    public String getTaxPoint() {
        return taxPoint;
    }

    public void setTaxPoint(String taxPoint) {
        this.taxPoint = taxPoint;
    }

    public String getTaxPointName() {
        return DictionaryStorage.get(TaxPoint.class.getName(),this.getTaxPoint()).getName();
    }

    public void setTaxPointName(String taxPointName) {
        this.taxPointName = taxPointName;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getReceivingChannel() {
        return receivingChannel;
    }

    public void setReceivingChannel(String receivingChannel) {
        this.receivingChannel = receivingChannel;
    }

    public String getReceivingChannelName() {
        return DictionaryStorage.get(ReceivingChannel.class.getName(),this.getReceivingChannel()).getName();
    }

    public void setReceivingChannelName(String receivingChannelName) {
        this.receivingChannelName = receivingChannelName;
    }

    public String getReceivingChannelCode() {
        return receivingChannelCode;
    }

    public void setReceivingChannelCode(String receivingChannelCode) {
        this.receivingChannelCode = receivingChannelCode;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayStatusName() {
        return DictionaryStorage.get(PayStatus.class.getName(),this.getPayStatus()).getName();
    }

    public void setPayStatusName(String payStatusName) {
        this.payStatusName = payStatusName;
    }
}
