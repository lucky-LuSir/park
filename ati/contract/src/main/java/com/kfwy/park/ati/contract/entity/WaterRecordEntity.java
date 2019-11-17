package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.WaterBillingType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class WaterRecordEntity extends BaseEntity {

    /**
     * 水表抄表编号
     */
    private String waterRecordCode;

    /**
     * 水表编号
     */
    private String waterMeterCode;

    /**
     * 水表名称
     */
    private String waterMeterName;

    /**
     * 抄表费用编号
     */
    private String waterCode;

    /**
     * 合同编号
     */
    private String ctCode;

    /**
     * 计费方式
     * @see WaterBillingType
     */
    private String waterBillingType;

    /**
     * 计费方式名称
     */
    private String waterBillingTypeName;

    /**
     * 水费金额（均摊计费）
     */
    private BigDecimal waterAmount;

    /**
     * 上一次抄表吨位（抄表计费）
     */
    private BigDecimal lastBillingWater;

    /**
     * 本次抄表吨位（抄表计费
     */
    private BigDecimal currentBillingWater;

    /**
     * 水费单价（抄表计费）
     */
    private BigDecimal waterUnitPrice;

    /**
     * 实用量 单位为吨（抄表计费）
     */
    private BigDecimal realWater;

    /**
     * 上次抄表日期
     */
    private Date lastMeterRecordDate;

    /**
     * 当前抄表日期
     */
    private Date currentMeterRecordDate;

    /*-----------------------------------------------------------------------------*/

    /**
     * 下期开始日期(拓展字段,不存入数据库,用作查询返回拓展字段)
     */
    private Date nextBillingTime;

    /*-----------------------------------------------------------------------------*/

    /**
     * 合同状态
     */
    private String ctStatus;

    /**
     * 合同编号
     */
    private String ctName;

    /**
     * 园区code
     */
    private String pkCode;

    /**
     * 园区名
     */
    private String pkName;

    /**
     * 公司名
     */
    private String companyName;

    /**
     * 客户名
     */
    private String relateName;

    /*-----------------------------------------------------------------------------*/

    /**
     * 是否生成账单
     */
    private Boolean hasBill;

    /**
     * 抄表月份
     */
    private Date recordMonth;

    /*-----------------------------------------------------------------------------*/

    public String getWaterRecordCode() {
        return waterRecordCode;
    }

    public void setWaterRecordCode(String waterRecordCode) {
        this.waterRecordCode = waterRecordCode;
    }

    public String getWaterMeterCode() {
        return waterMeterCode;
    }

    public void setWaterMeterCode(String waterMeterCode) {
        this.waterMeterCode = waterMeterCode;
    }

    public String getWaterMeterName() {
        return waterMeterName;
    }

    public void setWaterMeterName(String waterMeterName) {
        this.waterMeterName = waterMeterName;
    }

    public String getWaterCode() {
        return waterCode;
    }

    public void setWaterCode(String waterCode) {
        this.waterCode = waterCode;
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public String getWaterBillingType() {
        return waterBillingType;
    }

    public void setWaterBillingType(String waterBillingType) {
        this.waterBillingType = waterBillingType;
    }

    public String getWaterBillingTypeName() {
        return DictionaryStorage.get(WaterBillingType.class.getName(), this.getWaterBillingType()).getName();
    }

    public void setWaterBillingTypeName(String waterBillingTypeName) {
        this.waterBillingTypeName = waterBillingTypeName;
    }

    public BigDecimal getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(BigDecimal waterAmount) {
        this.waterAmount = waterAmount;
    }

    public BigDecimal getLastBillingWater() {
        return lastBillingWater;
    }

    public void setLastBillingWater(BigDecimal lastBillingWater) {
        this.lastBillingWater = lastBillingWater;
    }

    public BigDecimal getCurrentBillingWater() {
        return currentBillingWater;
    }

    public void setCurrentBillingWater(BigDecimal currentBillingWater) {
        this.currentBillingWater = currentBillingWater;
    }

    public BigDecimal getWaterUnitPrice() {
        return waterUnitPrice;
    }

    public void setWaterUnitPrice(BigDecimal waterUnitPrice) {
        this.waterUnitPrice = waterUnitPrice;
    }

    public BigDecimal getRealWater() {
        return realWater;
    }

    public void setRealWater(BigDecimal realWater) {
        this.realWater = realWater;
    }

    public Date getNextBillingTime() {
        return nextBillingTime;
    }

    public void setNextBillingTime(Date nextBillingTime) {
        this.nextBillingTime = nextBillingTime;
    }

    public Date getLastMeterRecordDate() {
        return lastMeterRecordDate;
    }

    public void setLastMeterRecordDate(Date lastMeterRecordDate) {
        this.lastMeterRecordDate = lastMeterRecordDate;
    }

    public Date getCurrentMeterRecordDate() {
        return currentMeterRecordDate;
    }

    public void setCurrentMeterRecordDate(Date currentMeterRecordDate) {
        this.currentMeterRecordDate = currentMeterRecordDate;
    }

    public String getCtStatus() {
        return ctStatus;
    }

    public void setCtStatus(String ctStatus) {
        this.ctStatus = ctStatus;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
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

    public Boolean getHasBill() {
        return hasBill;
    }

    public void setHasBill(Boolean hasBill) {
        this.hasBill = hasBill;
    }

    public Date getRecordMonth() {
        return recordMonth;
    }

    public void setRecordMonth(Date recordMonth) {
        this.recordMonth = recordMonth;
    }
}
