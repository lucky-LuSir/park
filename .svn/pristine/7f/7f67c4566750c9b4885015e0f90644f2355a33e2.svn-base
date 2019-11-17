package com.kfwy.park.bi.report.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.CtStatus;
import com.kfwy.park.ati.contract.enums.PayStatus;
import com.kfwy.park.ati.contract.enums.TaxPoint;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description 首笔款报表
 * @Auth luming
 * @Date 2019/1/8 15:49
 * @Version 1.0
 * @Param
 * @return
 */
public class FirstReceivableBiEntity extends BaseEntity {

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
     * 本期费用编码
     */
    private String rentCode;

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
     * 电费押金
     */
    private BigDecimal eleDeposit;

    /**
     * 水费押金
     */
    private BigDecimal waterDeposit;

    /**
     * 首期支付保险费
     */
    private BigDecimal fpayInsurancePrice;

    /**
     * 首期支付工程押金
     */
    private BigDecimal fpayProjectDeposit;

    /**
     * 首期支付保证金
     */
    private BigDecimal fpayBond;

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
     *签单人的部门编码
     */
    private String contractorDeptCode;

    /**
     *园区编码
     */
    private String pkCode;

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

    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
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

    public BigDecimal getEleDeposit() {
        return eleDeposit;
    }

    public void setEleDeposit(BigDecimal eleDeposit) {
        this.eleDeposit = eleDeposit;
    }

    public BigDecimal getWaterDeposit() {
        return waterDeposit;
    }

    public void setWaterDeposit(BigDecimal waterDeposit) {
        this.waterDeposit = waterDeposit;
    }

    public BigDecimal getFpayInsurancePrice() {
        return fpayInsurancePrice;
    }

    public void setFpayInsurancePrice(BigDecimal fpayInsurancePrice) {
        this.fpayInsurancePrice = fpayInsurancePrice;
    }

    public BigDecimal getFpayProjectDeposit() {
        return fpayProjectDeposit;
    }

    public void setFpayProjectDeposit(BigDecimal fpayProjectDeposit) {
        this.fpayProjectDeposit = fpayProjectDeposit;
    }

    public BigDecimal getFpayBond() {
        return fpayBond;
    }

    public void setFpayBond(BigDecimal fpayBond) {
        this.fpayBond = fpayBond;
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

    public String getContractorDeptCode() {
        return contractorDeptCode;
    }

    public void setContractorDeptCode(String contractorDeptCode) {
        this.contractorDeptCode = contractorDeptCode;
    }

    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
    }
}
