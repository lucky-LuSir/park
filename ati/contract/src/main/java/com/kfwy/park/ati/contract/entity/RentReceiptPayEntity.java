package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.CostType;
import com.kfwy.park.ati.contract.enums.PayStatus;
import com.kfwy.park.ati.contract.enums.ReceivingChannel;
import com.kfwy.park.ati.contract.enums.TaxPoint;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by liuzhengyang on 2018/5/29.
 * 合同:租金应收支付记录
 */
public class RentReceiptPayEntity extends BaseEntity {

    /**
     *支付记录编号
     */
    private String payCode;
    /**
     *费用缴费编号
     */
    private String code;
    /**
     * 合同编号
     */
    private String ctCode;
    /**
     *费用缴费类型(1:租金,2:电费,3:水费,4:停车费,5:保险费,6:退租费,7: 大房东合同缴费)
     * @see CostType
     */
    private String costType;
    /**
     * 缴费类型名称
     */
    private String costTypeName;
    /**
     *租金开始日期
     */
    private Date costStartTime;
    /**
     *租金结束日期
     */
    private Date costEndTime;
    /**
     *应收租金
     */
    private BigDecimal receivableRent;
    /**
     *实收租金
     */
    private BigDecimal realRent;
    /**
     *已收款(不含税金)
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
     *最后收款时间
     */
    private Date payDate;
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
     *发票税点名称
     */
    private String proTaxPointName;

    /*==================================20180529============================*/

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getCostTypeName() {
        return DictionaryStorage.get(CostType.class.getName(), this.getCostType()).getName();
    }

    public void setCostTypeName(String costTypeName) {
        this.costTypeName = costTypeName;
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
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
        return receivingChannelName;
    }

    public void setReceivingChannelName(String receivingChannelName) {
        this.receivingChannelName = receivingChannelName;
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

    public String getPayCode() {
        return payCode;
    }

    public void setPayCode(String payCode) {
        this.payCode = payCode;
    }

    public String getTaxPointName() {
        return DictionaryStorage.get(TaxPoint.class.getName(),this.getTaxPoint()).getName();
    }

    public void setTaxPointName(String taxPointName) {
        this.taxPointName = taxPointName;
    }

    public BigDecimal getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(BigDecimal paidPrice) {
        this.paidPrice = paidPrice;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public BigDecimal getTaxPrice() {
        return taxPrice;
    }

    public void setTaxPrice(BigDecimal taxPrice) {
        this.taxPrice = taxPrice;
    }
}
