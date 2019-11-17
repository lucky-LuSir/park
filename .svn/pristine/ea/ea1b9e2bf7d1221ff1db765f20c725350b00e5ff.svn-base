package com.kfwy.park.bi.report.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.CtStatus;
import com.kfwy.park.ati.contract.enums.PayStatus;
import com.kfwy.park.ati.contract.enums.TaxPoint;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 水费应收报表
 * @Auth luming
 * @Date 2019/1/7 14:22
 * @Version 1.0
 * @Param
 * @return
 */
public class WaterReceivableBiEntity extends BaseEntity {

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
     * 上次抄表时间
     */
    private Date lastBillingTime;

    /**
     * 本次抄表时间
     */
    private Date currentBillingTime;

    /**
     * 缴费截至日期
     */
    private Date receivingDate;

    /**
     * 应收水费
     */
    private BigDecimal receivablePrice;

    /**
     * 是否含税
     */
    private Boolean hasTax;

    /**
     * 税点
     * @see TaxPoint
     */
    private String taxPoint;

    /**
     * 租金税点名称
     */
    private String taxPointName;

    /**
     * 税金
     */
    private BigDecimal taxPrice;

    /**
     * 本期应收
     */
    private BigDecimal totalPrice;

    /**
     * 支付状态
     */
    private String payStatus;

    /**
     * 支付状态名称
     * @see PayStatus
     */
    private String payStatusName;

    /*----------------------------------- 封装字段，用于前端查询传入数据 -----------------------------------*/

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /*----------------------------------- get set -----------------------------------*/

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

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    public BigDecimal getReceivablePrice() {
        return receivablePrice;
    }

    public void setReceivablePrice(BigDecimal receivablePrice) {
        this.receivablePrice = receivablePrice;
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
        return DictionaryStorage.get(TaxPoint.class.getName(), this.getTaxPoint()).getName();
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
