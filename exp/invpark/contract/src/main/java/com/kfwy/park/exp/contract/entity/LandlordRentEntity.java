package com.kfwy.park.exp.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import com.kfwy.park.ati.contract.enums.PayStatus;
import com.kfwy.park.ati.contract.enums.TaxPoint;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by gengtao on 2019/4/18.
 */
public class LandlordRentEntity extends BaseEntity {
    /**
     * 编号
     */
    private String rentCode;

    /**
     * 大房东合同编号
     */
    private String ldCode;

    /**
     * 付款开始日期
     */
    private Date costStartTime;

    /**
     * 付款结束日期
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
     * 本期租金
     */
    private BigDecimal rentPrice;

    /**
     * 租金应付税额
     */
    private BigDecimal rentTaxPrice;

    /**
     * 租金票面金额
     */
    private BigDecimal rentOnInvoice;

    /**
     * 租金实际税点
     */
    private String rentRealTaxPoint;

    /**
     * 租金票面税点
     */
    private String rentInvoiceTaxPoint;

    /**
     * 本期物业费
     */
    private BigDecimal propertyPrice;

    /**
     *物业应收税额
     */
    private BigDecimal propertyTaxPrice;

    /**
     * 物业票面金额
     */
    private BigDecimal propertyOnInvoice;

    /**
     * 物业实际税点
     */
    private String propertyRealTaxPoint;

    /**
     * 物业票面税点
     */
    private String propertyInvoiceTaxPoint;

    /**
     * 其他应付
     */
    private BigDecimal otherPrice;

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

    /*************************************************************封装字段******************************/
    /**
     * 付款明细
     */
    private List<RentReceiptPayEntity> payEntityList;


    /*************************************************************get,set******************************/
    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
    }

    public String getLdCode() {
        return ldCode;
    }

    public void setLdCode(String ldCode) {
        this.ldCode = ldCode;
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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public BigDecimal getRentTaxPrice() {
        return rentTaxPrice;
    }

    public void setRentTaxPrice(BigDecimal rentTaxPrice) {
        this.rentTaxPrice = rentTaxPrice;
    }

    public BigDecimal getRentOnInvoice() {
        return rentOnInvoice;
    }

    public void setRentOnInvoice(BigDecimal rentOnInvoice) {
        this.rentOnInvoice = rentOnInvoice;
    }

    public String getRentRealTaxPoint() {
        return rentRealTaxPoint;
    }

    public void setRentRealTaxPoint(String rentRealTaxPoint) {
        this.rentRealTaxPoint = rentRealTaxPoint;
    }

    public String getRentInvoiceTaxPoint() {
        return rentInvoiceTaxPoint;
    }

    public void setRentInvoiceTaxPoint(String rentInvoiceTaxPoint) {
        this.rentInvoiceTaxPoint = rentInvoiceTaxPoint;
    }

    public BigDecimal getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(BigDecimal propertyPrice) {
        this.propertyPrice = propertyPrice;
    }

    public BigDecimal getPropertyTaxPrice() {
        return propertyTaxPrice;
    }

    public void setPropertyTaxPrice(BigDecimal propertyTaxPrice) {
        this.propertyTaxPrice = propertyTaxPrice;
    }

    public BigDecimal getPropertyOnInvoice() {
        return propertyOnInvoice;
    }

    public void setPropertyOnInvoice(BigDecimal propertyOnInvoice) {
        this.propertyOnInvoice = propertyOnInvoice;
    }

    public String getPropertyRealTaxPoint() {
        return propertyRealTaxPoint;
    }

    public void setPropertyRealTaxPoint(String propertyRealTaxPoint) {
        this.propertyRealTaxPoint = propertyRealTaxPoint;
    }

    public String getPropertyInvoiceTaxPoint() {
        return propertyInvoiceTaxPoint;
    }

    public void setPropertyInvoiceTaxPoint(String propertyInvoiceTaxPoint) {
        this.propertyInvoiceTaxPoint = propertyInvoiceTaxPoint;
    }

    public BigDecimal getOtherPrice() {
        return otherPrice;
    }

    public void setOtherPrice(BigDecimal otherPrice) {
        this.otherPrice = otherPrice;
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

    public String getPayStatusName() {
        return DictionaryStorage.get(PayStatus.class.getName(), this.getPayStatus()).getName();
    }

    public void setPayStatusName(String payStatusName) {
        this.payStatusName = payStatusName;
    }

    public String getRentRealTaxPointName() {
        return DictionaryStorage.get(TaxPoint.class.getName(), this.getRentRealTaxPoint()).getName();
    }

    public String getRentInvoiceTaxPointName() {
        return DictionaryStorage.get(TaxPoint.class.getName(), this.getRentInvoiceTaxPoint()).getName();
    }

    public List<RentReceiptPayEntity> getPayEntityList() {
        return payEntityList;
    }

    public void setPayEntityList(List<RentReceiptPayEntity> payEntityList) {
        this.payEntityList = payEntityList;
    }
}

