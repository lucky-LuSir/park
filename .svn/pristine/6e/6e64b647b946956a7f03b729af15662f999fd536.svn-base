package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.PayStatus;
import com.kfwy.park.ati.contract.enums.ReceivingChannel;
import com.kfwy.park.ati.contract.enums.TaxPoint;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuzhengyang on 2018/5/29.
 * 合同:水费应收列表
 */
public class WaterReceiptEntity extends BaseEntity{

    /**
     *水费应收列表编号
     */
    private String waterCode;
    /**
     *合同编号
     */
    private String ctCode;
    /**
     *客户编号
     */
    private String cusCode;
    /**
     *公司名称
     */
    private String companyName;
    /**
     *上一次抄表时间
     */
    private Date lastBillingTime;
    /**
     *本次抄表时间
     */
    private Date currentBillingTime;
    /**
     *应收水费
     * 实用量*水费单价
     */
    private BigDecimal receivablePrice;
    /**
     *实收水费（本次付款）
     */
    private BigDecimal realPrice;
    /**
     *已收款(总共付款钱数)
     */
    private BigDecimal paidPrice;
    /**
     *收款渠道
     * @see ReceivingChannel
     */
    private String receivingChannel;
    /**
     *收款渠道名称
     */
    private String receivingChannelName;
    /**
     *收款渠道单号
     */
    private String receivingChannelCode;
    /**
     *收款时间
     */
    private Date receivingDate;
    /**
     *状态
     * @see PayStatus
     */
    private String payStatus;
    /**
     *状态名称
     */
    private String payStatusName;

    /**
     * 支付时间
     * 加该字段原因：前端缴费功能，需向pay表写入交费日期
     */
    private Date payDate;

    /**
     *是否含税
     */
    private Boolean hasTax;
    /**
     *发票税点
     * @see TaxPoint
     */
    private String taxPoint;
    /**
     *发票税点名称
     */
    private String taxPointName;
    /**
     *税金
     */
    private BigDecimal taxPrice;


    /*=============================20180530=========================*/

    public String getWaterCode() {
        return waterCode;
    }

    public void setWaterCode(String waterCode) {
        this.waterCode = waterCode;
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Date getLastBillingTime() {
        return lastBillingTime;
    }

    public void setLastBillingTime(Date lastBillingTime) {
        this.lastBillingTime = lastBillingTime;
    }

    public Date getCurrentBillingTime() {
        return currentBillingTime;
    }

    public void setCurrentBillingTime(Date currentBillingTime) {
        this.currentBillingTime = currentBillingTime;
    }

    public BigDecimal getReceivablePrice() {
        return receivablePrice;
    }

    public void setReceivablePrice(BigDecimal receivablePrice) {
        this.receivablePrice = receivablePrice;
    }

    public BigDecimal getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
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

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
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

}
