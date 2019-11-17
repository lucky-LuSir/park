package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.ElectricityBillingType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author wangchuang
 * @Description 合同:电费:电费记录表
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class ElectricityRecordEntity extends BaseEntity {
    /**
     * 电表编号
     */
    private String eleRecordCode;

    /**
     * 电表编号
     */
    private String eleMeterCode;

    /**
     * 合同编号
     */
    private String ctCode;

    /**
     * 电费缴收编号
     */
    private String eleCode;

    /**
     * 电表名称
     */
    private String eleMeterName;

    /**
     * 计费方式
     *
     * @see ElectricityBillingType
     */
    private String electricityBillingType;

    /**
     * 计费方式名称
     */
    private String electricityBillingTypeName;

    /**
     * 上次均价电表度数
     */
    private BigDecimal lastEleAverageDegree;

    /**
     * 本次均价电表度数
     */
    private BigDecimal currentEleAverageDegree;

    /**
     * 上次峰值抄表度数
     */
    private BigDecimal lastPeakDegree;

    /**
     * 本次峰值抄表度数
     */
    private BigDecimal currentPeakDegree;

    /**
     * 上次谷值抄表度数
     */
    private BigDecimal lastTroughDegree;

    /**
     * 本次谷值抄表度数
     */
    private BigDecimal currentTroughDegree;

    /**
     * 上次平值抄表度数
     */
    private BigDecimal lastFlatDegree;

    /**
     * 本次平值抄表度数
     */
    private BigDecimal currentFlatDegree;

    /**
     * 高峰实用度数
     */
    private BigDecimal peakDegree;

    /**
     * 低峰实用度数
     */
    private BigDecimal troughDegree;

    /**
     * 平峰实用度数
     */
    private BigDecimal flatDegree;

    /**
     * 实用度数
     */
    private BigDecimal realDegree;

    /*------------------------------- 抄表时间 -------------------------------*/

    /**
     * 上次抄表日期
     */
    private Date lastMeterRecordDate;

    /**
     * 当前抄表日期
     */
    private Date currentMeterRecordDate;

    /**
     * 是否生成账单
     */
    private Boolean hasBill;

    /**
     * 抄表月份
     */
    private Date recordMonth;

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

    public String getEleRecordCode() {
        return eleRecordCode;
    }

    public void setEleRecordCode(String eleRecordCode) {
        this.eleRecordCode = eleRecordCode;
    }

    public String getEleMeterCode() {
        return eleMeterCode;
    }

    public void setEleMeterCode(String eleMeterCode) {
        this.eleMeterCode = eleMeterCode;
    }

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

    public String getEleMeterName() {
        return eleMeterName;
    }

    public void setEleMeterName(String eleMeterName) {
        this.eleMeterName = eleMeterName;
    }

    public String getElectricityBillingType() {
        return electricityBillingType;
    }

    public void setElectricityBillingType(String electricityBillingType) {
        this.electricityBillingType = electricityBillingType;
    }

    public String getElectricityBillingTypeName() {
        return DictionaryStorage.get(ElectricityBillingType.class.getName(), this.getElectricityBillingType()).getName();
    }

    public void setElectricityBillingTypeName(String electricityBillingTypeName) {
        this.electricityBillingTypeName = electricityBillingTypeName;
    }

    public BigDecimal getLastEleAverageDegree() {
        return lastEleAverageDegree;
    }

    public void setLastEleAverageDegree(BigDecimal lastEleAverageDegree) {
        this.lastEleAverageDegree = lastEleAverageDegree;
    }

    public BigDecimal getCurrentEleAverageDegree() {
        return currentEleAverageDegree;
    }

    public void setCurrentEleAverageDegree(BigDecimal currentEleAverageDegree) {
        this.currentEleAverageDegree = currentEleAverageDegree;
    }

    public BigDecimal getLastPeakDegree() {
        return lastPeakDegree;
    }

    public void setLastPeakDegree(BigDecimal lastPeakDegree) {
        this.lastPeakDegree = lastPeakDegree;
    }

    public BigDecimal getCurrentPeakDegree() {
        return currentPeakDegree;
    }

    public void setCurrentPeakDegree(BigDecimal currentPeakDegree) {
        this.currentPeakDegree = currentPeakDegree;
    }

    public BigDecimal getLastTroughDegree() {
        return lastTroughDegree;
    }

    public void setLastTroughDegree(BigDecimal lastTroughDegree) {
        this.lastTroughDegree = lastTroughDegree;
    }

    public BigDecimal getCurrentTroughDegree() {
        return currentTroughDegree;
    }

    public void setCurrentTroughDegree(BigDecimal currentTroughDegree) {
        this.currentTroughDegree = currentTroughDegree;
    }

    public BigDecimal getLastFlatDegree() {
        return lastFlatDegree;
    }

    public void setLastFlatDegree(BigDecimal lastFlatDegree) {
        this.lastFlatDegree = lastFlatDegree;
    }

    public BigDecimal getCurrentFlatDegree() {
        return currentFlatDegree;
    }

    public void setCurrentFlatDegree(BigDecimal currentFlatDegree) {
        this.currentFlatDegree = currentFlatDegree;
    }

    public BigDecimal getPeakDegree() {
        return peakDegree;
    }

    public void setPeakDegree(BigDecimal peakDegree) {
        this.peakDegree = peakDegree;
    }

    public BigDecimal getTroughDegree() {
        return troughDegree;
    }

    public void setTroughDegree(BigDecimal troughDegree) {
        this.troughDegree = troughDegree;
    }

    public BigDecimal getFlatDegree() {
        return flatDegree;
    }

    public void setFlatDegree(BigDecimal flatDegree) {
        this.flatDegree = flatDegree;
    }

    public BigDecimal getRealDegree() {
        return realDegree;
    }

    public void setRealDegree(BigDecimal realDegree) {
        this.realDegree = realDegree;
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
