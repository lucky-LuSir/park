package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.PayStatus;
import com.kfwy.park.ati.contract.enums.ReceivingChannel;
import com.kfwy.park.ati.contract.enums.TaxPoint;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by liuzhengyang on 2018/5/29.
 * 合同:电费应收列表
 */
public class ElectricityReceiptEntity extends BaseEntity {

    /**
     *合同编号
     */
    private String ctCode;
    /**
     * 电费缴收编号
     */
    private String eleCode;
    /**
     *客户编号
     */
    private String cusCode;
    /**
     *公司名称
     */
    private String companyName;
    /**
     *上次结算时间
     */
    private Date lastBillingTime;
    /**
     *本次结算时间
     */
    private Date currentBillingTime;
    /**
     *每月基础电费(基础电费单价*总用电量*天数)
     */
    private BigDecimal basicElectricityPriceByMonth;
    /**
     *用电费用
     * (波峰度数*电费波峰单价 + 波平度数*电费波平单价 + 波谷度数*电费波谷单价)*倍数*(1+电损)
     * 或(均价度数*均价单价)*倍数*(1+电损)
     */
    private BigDecimal electricityPrice;
    /**
     *应收电费
     * 用电费用+ 基础电费总额
     */
    private BigDecimal receivablePrice;
    /**
     *实收电费（本次付款）
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
     *支付时间
     */
    private Date payDate;
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

    /**
     * 电表记录列表
     */
    private List<ElectricityRecordEntity> eleRecordList;


    /*==========================20180530=============================*/


    /*==========================20180730 暂时没用字段  start=============================*/

//    /**
//     *总用电量
//     */
//    private BigDecimal totalElectricity;
//    /**
//     *基础电费单价
//     */
//    private BigDecimal basicElectricityUnitPrice;
//    /**
//     *基础电费总额(每月基础电费*该次收款月份数)
//     */
//    private BigDecimal basicElectricityPrice;
//    /**
//     *电费波峰单价
//     */
//    private BigDecimal electricityPeakPrice;
//    /**
//     *电费波谷单价
//     */
//    private BigDecimal electricityTroughPrice;
//    /**
//     *电费平值单价
//     */
//    private BigDecimal electricityFlatPrice;
//    /**
//     *加收损耗(5%)
//     */
//    private BigDecimal addLoss;
//    /**
//     *电费波峰单价
//     */
//    private BigDecimal electricityPeakPrice;
//    /**
//     *电费平峰单价
//     */
//    private BigDecimal electricityFlatPrice;
//    /**
//     *电费波谷单价
//     */
//    private BigDecimal electricityTroughPrice;
//    /**
//     * 初始峰值
//     */
//    private BigDecimal electricityPeak;
//    /**
//     * 初始平值
//     */
//    private BigDecimal electricityFlat;
//    /**
//     * 初始谷值
//     */
//    private  BigDecimal electricityTrough;
//    /**
//     * 平均电价
//     */
//    private BigDecimal electricityAveragePrice;
//    /**
//     * 初始电表
//     */
//    private BigDecimal initElectricity;

    /*==========================20180730 暂时没用字段  end=============================*/

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public String getEleCode() {
        return eleCode;
    }

    public void setEleCode(String eleCode) {
        this.eleCode = eleCode;
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

    public BigDecimal getBasicElectricityPriceByMonth() {
        return basicElectricityPriceByMonth;
    }

    public void setBasicElectricityPriceByMonth(BigDecimal basicElectricityPriceByMonth) {
        this.basicElectricityPriceByMonth = basicElectricityPriceByMonth;
    }

    public BigDecimal getElectricityPrice() {
        return electricityPrice;
    }

    public void setElectricityPrice(BigDecimal electricityPrice) {
        this.electricityPrice = electricityPrice;
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

    public List<ElectricityRecordEntity> getEleRecordList() {
        return eleRecordList;
    }

    public void setEleRecordList(List<ElectricityRecordEntity> eleRecordList) {
        this.eleRecordList = eleRecordList;
    }
}
