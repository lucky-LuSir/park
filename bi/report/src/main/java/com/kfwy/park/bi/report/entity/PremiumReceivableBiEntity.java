package com.kfwy.park.bi.report.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.CtStatus;
import com.kfwy.park.ati.contract.enums.PayStatus;
import com.kfwy.park.ati.contract.enums.TaxPoint;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by gengtao on 2019/4/17.
 */
public class PremiumReceivableBiEntity extends BaseEntity {
    /**
     * 合同编码
     */
    private String ctCode;

    /**
     * 合同编号（自动生成的）
     */
    private String ctName;

    /**
     * 合同状态
     * @see CtStatus
     */
    private String ctStatus;

    /**
     * 合同状态名称
     */
    private String ctStatusName;

    /**
     * 项目名
     */
    private String pkName;

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 联系人姓名
     */
    private String relateName;

    /**
     * 合同开始时间
     */
    private Date contractStartTime;

    /**
     * 合同结束时间
     */
    private Date contractEndTime;

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
     * 保险费
     */
    private BigDecimal receivablePremium;


    /**
     * 合计应收金额
     */
    private BigDecimal totalPrice;

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
     * 已收保险费
     */
    private BigDecimal paidPrice;

    /**
     * 状态（字典）
     * @see PayStatus
     */
    private String payStatus;

    /**
     * 状态名称（字典）
     */
    private String payStatusName;

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getCtStatus() {
        return ctStatus;
    }

    public void setCtStatus(String ctStatus) {
        this.ctStatus = ctStatus;
    }

    public String getCtStatusName() {
        return DictionaryStorage.get(CtStatus.class.getName(), this.getCtStatus()).getName();
    }

    public void setCtStatusName(String ctStatusName) {
        this.ctStatusName = ctStatusName;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRelateName() {
        return relateName;
    }

    public void setRelateName(String relateName) {
        this.relateName = relateName;
    }

    public Date getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
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

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getPayStatusName() {
        return DictionaryStorage.get(PayStatus.class.getName(),this.getPayStatus()).getName();
    }

    public BigDecimal getReceivablePremium() {
        return receivablePremium;
    }

    public void setReceivablePremium(BigDecimal receivablePremium) {
        this.receivablePremium = receivablePremium;
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
        return taxPointName;
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

    public void setPayStatusName(String payStatusName) {
        this.payStatusName = payStatusName;
    }
}
