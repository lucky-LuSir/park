package com.kfwy.park.bi.report.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.CtStatus;
import com.kfwy.park.ati.contract.enums.PayStatus;
import com.kfwy.park.ati.contract.enums.TaxPoint;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 应收账款报表
 * @Auth luming
 * @Date 2018/12/14 8:56
 * @Version 1.0
 * @Param
 * @return
 */
public class AccountReceivableBiEntity extends BaseEntity {

    /**
     * 合同编码
     */
    private String ctCode;

    /**
     * 租金编码
     */
    private String rentCode;

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
     * 本期费用开始日期
     */
    private Date rentStartTime;

    /**
     * 本期费用结束日期
     */
    private Date rentEndTime;

    /**
     * 缴费截至日期
     */
    private Date receivingDate;

    /**
     * 租金
     */
    private BigDecimal receivableRent;

    /**
     * 租金是否含税
     */
    private Boolean hasTax;

    /**
     * 租金税点
     * @see TaxPoint
     */
    private String taxPoint;

    /**
     * 租金税点名称
     */
    private String taxPointName;
    /**
     * 物业费
     */
    private BigDecimal propertyPrice;

    /**
     * 物业费是否含税
     */
    private Boolean proHasTax;

    /**
     * 物业费税点
     * @see TaxPoint
     */
    private String proTaxPoint;

    /**
     * 物业费税点名称
     */
    private String proTaxPointName;

    /**
     * 已收租金
     */
    private BigDecimal paidPrice;

    /**
     * 本期应收
     */
    private BigDecimal totalPrice;

    /**
     * 租金状态
     */
    private String payStatus;

    /**
     * 租金状态
     * @see PayStatus
     */
    private String payStatusName;
    /**
     *逾期天数
     */
    private Integer overdueDays;
    /**
     *滞纳金
     */
    private BigDecimal latePayment;

    /*----------------------------------- 封装字段，用于前端查询传入数据 -----------------------------------*/

    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    /**
     * 月份选择
     */
    private Date selectMonth;

    /*----------------------------------- get set -----------------------------------*/

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
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

    public String getRelateName() {
        return relateName;
    }

    public void setRelateName(String relateName) {
        this.relateName = relateName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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

    public Date getRentStartTime() {
        return rentStartTime;
    }

    public void setRentStartTime(Date rentStartTime) {
        this.rentStartTime = rentStartTime;
    }

    public Date getRentEndTime() {
        return rentEndTime;
    }

    public void setRentEndTime(Date rentEndTime) {
        this.rentEndTime = rentEndTime;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }

    public BigDecimal getReceivableRent() {
        return receivableRent;
    }

    public void setReceivableRent(BigDecimal receivableRent) {
        this.receivableRent = receivableRent;
    }

    public BigDecimal getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(BigDecimal propertyPrice) {
        this.propertyPrice = propertyPrice;
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

    public Boolean getProHasTax() {
        return proHasTax;
    }

    public void setProHasTax(Boolean proHasTax) {
        this.proHasTax = proHasTax;
    }

    public String getProTaxPoint() {
        return proTaxPoint;
    }

    public void setProTaxPoint(String proTaxPoint) {
        this.proTaxPoint = proTaxPoint;
    }

    public String getProTaxPointName() {
        return DictionaryStorage.get(TaxPoint.class.getName(), this.getProTaxPoint()).getName();
    }

    public void setProTaxPointName(String proTaxPointName) {
        this.proTaxPointName = proTaxPointName;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
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
        return DictionaryStorage.get(PayStatus.class.getName(), this.getPayStatus()).getName();
    }

    public void setPayStatusName(String payStatusName) {
        this.payStatusName = payStatusName;
    }

    public Integer getOverdueDays() {
        return overdueDays;
    }

    public void setOverdueDays(Integer overdueDays) {
        this.overdueDays = overdueDays;
    }

    public BigDecimal getLatePayment() {
        return latePayment;
    }

    public void setLatePayment(BigDecimal latePayment) {
        this.latePayment = latePayment;
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

    public Date getSelectMonth() {
        return selectMonth;
    }

    public void setSelectMonth(Date selectMonth) {
        this.selectMonth = selectMonth;
    }
}
