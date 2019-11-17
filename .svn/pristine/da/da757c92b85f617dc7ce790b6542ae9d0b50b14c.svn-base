package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.WaterBillingType;

import java.math.BigDecimal;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class WaterMeterEntity extends BaseEntity {

    /**
     * 水表编号
     */
    private String waterMeterCode;

    /**
     * 合同编号
     */
    private String ctCode;

    /**
     * 水表名称
     */
    private String waterMeterName;

    /**
     * 计费方式
     */
    private String waterBillingType;

    /**
     * 计费方式名称
     * @see WaterBillingType
     */
    private String waterBillingTypeName;

    /**
     * 水费单价（抄表计费）
     */
    private BigDecimal waterUnitPrice;

    /**
     * 初始电表（抄表计费）
     */
    private BigDecimal initWater;

    /**
     * 水费金额（均摊计费）
     */
    private BigDecimal waterAmount;

    /**
     * 封装水表抄表记录
     */
    private WaterRecordEntity waterRecordEntity;

    /**
     * 是否启用水表
     * true：启用
     * false：禁用
     */
    private Boolean waterIsOpen;

    /***************************************分割线*******************************************/

    public String getWaterMeterCode() {
        return waterMeterCode;
    }

    public void setWaterMeterCode(String waterMeterCode) {
        this.waterMeterCode = waterMeterCode;
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public String getWaterMeterName() {
        return waterMeterName;
    }

    public void setWaterMeterName(String waterMeterName) {
        this.waterMeterName = waterMeterName;
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

    public BigDecimal getWaterUnitPrice() {
        return waterUnitPrice;
    }

    public void setWaterUnitPrice(BigDecimal waterUnitPrice) {
        this.waterUnitPrice = waterUnitPrice;
    }

    public BigDecimal getInitWater() {
        return initWater;
    }

    public void setInitWater(BigDecimal initWater) {
        this.initWater = initWater;
    }

    public BigDecimal getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(BigDecimal waterAmount) {
        this.waterAmount = waterAmount;
    }

    public WaterRecordEntity getWaterRecordEntity() {
        return waterRecordEntity;
    }

    public void setWaterRecordEntity(WaterRecordEntity waterRecordEntity) {
        this.waterRecordEntity = waterRecordEntity;
    }

    public Boolean getWaterIsOpen() {
        return waterIsOpen;
    }

    public void setWaterIsOpen(Boolean waterIsOpen) {
        this.waterIsOpen = waterIsOpen;
    }
}
