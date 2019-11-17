package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by liuzhengyang on 2018/5/29.
 * 合同:租金应收列表:明细列表
 */
public class RentReceiptDetailEntity extends BaseEntity {

    /**
     *租金明细列表编号
     */
    private String rrdCode;
    /**
     *当期租金编号
     */
    private String rentCode;
    /**
     *租房编号
     */
    private String rentHosCode;
    /**
     *出租承租面积
     */
    private BigDecimal rentArea;
    /**
     *出租招商面积（含公摊）
     */
    private BigDecimal investArea;
    /**
     *公摊比例
     */
    private String publicOfficeProportion;
    /**
     *递增前租金单价
     */
    private BigDecimal IncBeforePrice;
    /**
     *递增后租金单价
     */
    private BigDecimal IncAfterPrice;
    /**
     *租金单位
     */
    private String unit;
    /**
     *月租金总额
     */
    private BigDecimal monthPrice;
    /**
     *应收租金
     */
    private BigDecimal receivableRent;

    /*===============================20180529=============================*/
    public String getRrdCode() {
        return rrdCode;
    }

    public void setRrdCode(String rrdCode) {
        this.rrdCode = rrdCode;
    }

    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
    }

    public String getRentHosCode() {
        return rentHosCode;
    }

    public void setRentHosCode(String rentHosCode) {
        this.rentHosCode = rentHosCode;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public String getPublicOfficeProportion() {
        return publicOfficeProportion;
    }

    public void setPublicOfficeProportion(String publicOfficeProportion) {
        this.publicOfficeProportion = publicOfficeProportion;
    }

    public BigDecimal getIncBeforePrice() {
        return IncBeforePrice;
    }

    public void setIncBeforePrice(BigDecimal incBeforePrice) {
        IncBeforePrice = incBeforePrice;
    }

    public BigDecimal getIncAfterPrice() {
        return IncAfterPrice;
    }

    public void setIncAfterPrice(BigDecimal incAfterPrice) {
        IncAfterPrice = incAfterPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getMonthPrice() {
        return monthPrice;
    }

    public void setMonthPrice(BigDecimal monthPrice) {
        this.monthPrice = monthPrice;
    }

    public BigDecimal getReceivableRent() {
        return receivableRent;
    }

    public void setReceivableRent(BigDecimal receivableRent) {
        this.receivableRent = receivableRent;
    }

    public BigDecimal getInvestArea() {
        return investArea;
    }

    public void setInvestArea(BigDecimal investArea) {
        this.investArea = investArea;
    }
}
