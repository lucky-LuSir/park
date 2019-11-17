package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangchuang
 * @Description 租金递增详情
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class RentIncrementEntity extends BaseEntity {

    /**
     * 租金递增编号
     */
    private String incCode;

    /**
     * 合同编号
     */
    private String ctCode;

    /**
     * 递增开始时间
     */
    private Date incStartTime;

    /**
     * 递增结束时间
     */
    private Date incEndTime;

    /**
     * 每月租金(本合同所有房屋总和)
     */
    private BigDecimal rentTotal;

    /**
     * 单价(元/平米/月)
     * 多个房屋则算出平均单价存入
     */
    private BigDecimal avgUnitPrice;

    /**
     * 出租含公摊比面积
     */
    private BigDecimal rentInvestArea;

    /**
     * 递增比
     */
    private BigDecimal rentIncreaseProportion;

    /***********************************字段分割******************************************/

    public String getIncCode() {
        return incCode;
    }

    public void setIncCode(String incCode) {
        this.incCode = incCode;
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public Date getIncStartTime() {
        return incStartTime;
    }

    public void setIncStartTime(Date incStartTime) {
        this.incStartTime = incStartTime;
    }

    public Date getIncEndTime() {
        return incEndTime;
    }

    public void setIncEndTime(Date incEndTime) {
        this.incEndTime = incEndTime;
    }

    public BigDecimal getRentTotal() {
        return rentTotal;
    }

    public void setRentTotal(BigDecimal rentTotal) {
        this.rentTotal = rentTotal;
    }

    public BigDecimal getAvgUnitPrice() {
        return avgUnitPrice;
    }

    public void setAvgUnitPrice(BigDecimal avgUnitPrice) {
        this.avgUnitPrice = avgUnitPrice;
    }

    public BigDecimal getRentInvestArea() {
        return rentInvestArea;
    }

    public void setRentInvestArea(BigDecimal rentInvestArea) {
        this.rentInvestArea = rentInvestArea;
    }

    public BigDecimal getRentIncreaseProportion() {
        return rentIncreaseProportion;
    }

    public void setRentIncreaseProportion(BigDecimal rentIncreaseProportion) {
        this.rentIncreaseProportion = rentIncreaseProportion;
    }
}
