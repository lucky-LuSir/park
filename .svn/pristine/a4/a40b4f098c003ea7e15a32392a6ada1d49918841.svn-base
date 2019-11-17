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
 * 合同:租金应收列表
 */
public class RentReceiptEntity extends BaseEntity {

    /**
     *租金编号
     */
    private String rentCode;
    /**
     *合同编号
     */
    private String ctCode;
    /**
     *园区编号
     */
    private String pkCode;
    /**
     *租金开始日期
     */
    private Date rentStartTime;
    /**
     *租金结束日期
     */
    private Date rentEndTime;
    /**
     *合计应收金额
     */
    private BigDecimal totalPrice;
    /**
     *自动计算租金
     */
    private BigDecimal autoRent;
    /**
     *应收租金
     */
    private BigDecimal receivableRent;
    /**
     *实收租金（本次付款）
     */
    private BigDecimal realRent;
    /**
     *已收款(总共付款钱数)
     * paid_price
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
     *支付时间
     */
    private Date payDate;
    /**
     *最后收款时间(根据提前交租时间计算得到日期)
     */
    private Date receivingDate;
    /**
     *租金状态
     * @see PayStatus
     */
    private String payStatus;
    /**
     *租金状态名称
     */
    private String payStatusName;
    /**
     *备注
     */
    private String remark;
    /**
     *租金是否含税
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
     * tax_price
     */
    private BigDecimal taxPrice;
    /**
     * 物业费
     */
    private  BigDecimal propertyPrice;
    /**
     *物业费是否含税
     */
    private Boolean proHasTax;
    /**
     *物业费发票税点
     * @see TaxPoint
     */
    private String proTaxPoint;
    /**
     *物业费发票税点名称
     */
    private String proTaxPointName;
    /**
     *逾期天数
     */
    private Integer overdueDays;
    /**
     *滞纳金
     */
    private BigDecimal latePayment;
    /**
     *优惠金额
     */
    private BigDecimal discountsPrice;
    /**
     *明细列表
     */
    private List<RentReceiptDetailEntity> rentReceiptList;

    /*==================================20180529============================*/

    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
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

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    public BigDecimal getReceivableRent() {
        return receivableRent;
    }

    public void setReceivableRent(BigDecimal receivableRent) {
        this.receivableRent = receivableRent;
    }

    public BigDecimal getRealRent() {
        return realRent;
    }

    public void setRealRent(BigDecimal realRent) {
        this.realRent = realRent;
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

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
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

    public BigDecimal getPropertyPrice() {
        return propertyPrice;
    }

    public void setPropertyPrice(BigDecimal propertyPrice) {
        this.propertyPrice = propertyPrice;
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
        return DictionaryStorage.get(TaxPoint.class.getName(),this.getProTaxPoint()).getName();
    }

    public void setProTaxPointName(String proTaxPointName) {
        this.proTaxPointName = proTaxPointName;
    }

    public List<RentReceiptDetailEntity> getRentReceiptList() {
        return rentReceiptList;
    }

    public void setRentReceiptList(List<RentReceiptDetailEntity> rentReceiptList) {
        this.rentReceiptList = rentReceiptList;
    }

    public BigDecimal getAutoRent() {
        return autoRent;
    }

    public void setAutoRent(BigDecimal autoRent) {
        this.autoRent = autoRent;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
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

    public BigDecimal getDiscountsPrice() {
        return discountsPrice;
    }

    public void setDiscountsPrice(BigDecimal discountsPrice) {
        this.discountsPrice = discountsPrice;
    }
}
