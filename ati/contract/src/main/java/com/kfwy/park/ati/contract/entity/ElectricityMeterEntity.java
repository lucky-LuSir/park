package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.ElectricityBillingType;

import java.math.BigDecimal;

/**
 * @author wangchuang
 * @Description 电表信息表
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class ElectricityMeterEntity extends BaseEntity {

    /**
     * 电表编号
     */
    private String eleMeterCode;

    /**
     * 电表名称
     */
    private String eleMeterName;

    /**
     *合同编号
     */
    private String ctCode;

    /**
     * 倍率
     */
    private BigDecimal multiple;

    /**
     * 计费方式
     * @see ElectricityBillingType
     */
    private String electricityBillingType;

    /**
     *计费方式名称
     */
    private String electricityBillingTypeName;

    /*--------------------------------------- 峰平谷计费 ---------------------------------------*/

    /**
     * 初始峰值
     */
    private BigDecimal electricityPeak;

    /**
     * 初始平值
     */
    private BigDecimal electricityFlat;

    /**
     * 初始谷值
     */
    private  BigDecimal electricityTrough;

    /**
     *电费波峰单价
     */
    private BigDecimal electricityPeakPrice;

    /**
     *电费平峰单价
     */
    private BigDecimal electricityFlatPrice;

    /**
     *电费波谷单价
     */
    private BigDecimal electricityTroughPrice;

    /*--------------------------------------- 均价计费 ---------------------------------------*/

    /**
     * 均价电价
     */
    private BigDecimal electricityAveragePrice;

    /**
     * 均价初始电表
     */
    private BigDecimal initElectricity;

    /**
     * 是否启用电表
     * true：启用
     * false：禁用
     */
    private Boolean eleIsOpen;

    /*--------------------------------------- 数据封装 ---------------------------------------*/

    /**
     * 封装最新一条的抄表记录
     */
    private ElectricityRecordEntity electricityRecordEntity;

    /*--------------------------------------- 字段分割 ---------------------------------------*/

    public String getEleMeterCode() {
        return eleMeterCode;
    }

    public void setEleMeterCode(String eleMeterCode) {
        this.eleMeterCode = eleMeterCode;
    }

    public String getEleMeterName() {
        return eleMeterName;
    }

    public void setEleMeterName(String eleMeterName) {
        this.eleMeterName = eleMeterName;
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public BigDecimal getMultiple() {
        return multiple;
    }

    public void setMultiple(BigDecimal multiple) {
        this.multiple = multiple;
    }

    public String getElectricityBillingType() {
        return electricityBillingType;
    }

    public void setElectricityBillingType(String electricityBillingType) {
        this.electricityBillingType = electricityBillingType;
    }

    public String getElectricityBillingTypeName() {
        return DictionaryStorage.get(ElectricityBillingType.class.getName(),this.getElectricityBillingType()).getName();
    }

    public void setElectricityBillingTypeName(String electricityBillingTypeName) {
        this.electricityBillingTypeName = electricityBillingTypeName;
    }

    public BigDecimal getElectricityPeak() {
        return electricityPeak;
    }

    public void setElectricityPeak(BigDecimal electricityPeak) {
        this.electricityPeak = electricityPeak;
    }

    public BigDecimal getElectricityFlat() {
        return electricityFlat;
    }

    public void setElectricityFlat(BigDecimal electricityFlat) {
        this.electricityFlat = electricityFlat;
    }

    public BigDecimal getElectricityTrough() {
        return electricityTrough;
    }

    public void setElectricityTrough(BigDecimal electricityTrough) {
        this.electricityTrough = electricityTrough;
    }

    public BigDecimal getElectricityPeakPrice() {
        return electricityPeakPrice;
    }

    public void setElectricityPeakPrice(BigDecimal electricityPeakPrice) {
        this.electricityPeakPrice = electricityPeakPrice;
    }

    public BigDecimal getElectricityFlatPrice() {
        return electricityFlatPrice;
    }

    public void setElectricityFlatPrice(BigDecimal electricityFlatPrice) {
        this.electricityFlatPrice = electricityFlatPrice;
    }

    public BigDecimal getElectricityTroughPrice() {
        return electricityTroughPrice;
    }

    public void setElectricityTroughPrice(BigDecimal electricityTroughPrice) {
        this.electricityTroughPrice = electricityTroughPrice;
    }

    public BigDecimal getElectricityAveragePrice() {
        return electricityAveragePrice;
    }

    public void setElectricityAveragePrice(BigDecimal electricityAveragePrice) {
        this.electricityAveragePrice = electricityAveragePrice;
    }

    public BigDecimal getInitElectricity() {
        return initElectricity;
    }

    public void setInitElectricity(BigDecimal initElectricity) {
        this.initElectricity = initElectricity;
    }

    public Boolean getEleIsOpen() {
        return eleIsOpen;
    }

    public void setEleIsOpen(Boolean eleIsOpen) {
        this.eleIsOpen = eleIsOpen;
    }

    public ElectricityRecordEntity getElectricityRecordEntity() {
        return electricityRecordEntity;
    }

    public void setElectricityRecordEntity(ElectricityRecordEntity electricityRecordEntity) {
        this.electricityRecordEntity = electricityRecordEntity;
    }
}
