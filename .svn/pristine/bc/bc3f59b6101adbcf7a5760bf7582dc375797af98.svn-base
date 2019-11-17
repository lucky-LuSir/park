package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.pro.house.building.enums.PriceUnit;

import java.math.BigDecimal;

/**
 * Created by liuzhengyang on 2018/5/29.
 * 合同:房源列表
 */
public class ContractHouseEntity extends BaseEntity {

    /**
     *合同编号
     */
    private String ctCode;
    /**
     *租房编号
     */
    private String rentHosCode;
    /**
     *园区编码
     */
    private String pkCode;
    /**
     *园区名称
     */
    private String pkName;
    /**
     *建筑编码
     */
    private String bdCode;
    /**
     *建筑名称
     */
    private String bdName;
    /**
     *楼层编码
     */
    private String flCode;
    /**
     *楼层数
     */
    private Integer flCount;
    /**
     *房间号
     */
    private String roomNumber;

    /**
     * 房间主键code
     */
    private String roomCode;
    /**
     *出租承租面积
     */
    private BigDecimal rentArea;
    /**
     *出租计租面积（含公摊）
     */
    private BigDecimal investArea;
    /**
     *公摊比例
     */
    private String areaRate;
    /**
     *租金单价
     */
    private BigDecimal unitPrice;
    /**
     * 价格单位（字典） 新
     * @see PriceUnit
     */
    private String unit;
    /**
     * 价格单位名称（字典） 新
     */
    private String unitName;
    /**
     *月租金总额
     */
    private BigDecimal monthlyRent;
    /**
     * 物业费单价  7/11+
     */
    private BigDecimal propertyUnitPrice;


    /*----------------------------------- 封装字段 -----------------------------------*/
    /**
     * 合同编号
     */
    private String ctName;

    /**
     * 入驻的公司名
     */
    private String companyName;

    /**
     * 客户名字
     */
    private String relateName;

    /*===================================20180529字段===========================*/


    /*==========================20180730 暂时没用字段  start=============================*/

//    /**
//     *房源省市区街道编码
//     */
//    private String provinceCode;
//    private String cityCode;
//    private String regionCode;
//    private String streetCode;
//    /**
//     *房源省市区街道名称
//     */
//    private String provinceName;
//    private String cityName;
//    private String regionName;
//    private String streetName;

    /*==========================20180730 暂时没用字段  end=============================*/


    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public String getRentHosCode() {
        return rentHosCode;
    }

    public void setRentHosCode(String rentHosCode) {
        this.rentHosCode = rentHosCode;
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

    public String getBdCode() {
        return bdCode;
    }

    public void setBdCode(String bdCode) {
        this.bdCode = bdCode;
    }

    public String getBdName() {
        return bdName;
    }

    public void setBdName(String bdName) {
        this.bdName = bdName;
    }

    public String getFlCode() {
        return flCode;
    }

    public void setFlCode(String flCode) {
        this.flCode = flCode;
    }

    public Integer getFlCount() {
        return flCount;
    }

    public void setFlCount(Integer flCount) {
        this.flCount = flCount;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public BigDecimal getRentArea() {
        return rentArea;
    }

    public void setRentArea(BigDecimal rentArea) {
        this.rentArea = rentArea;
    }

    public BigDecimal getInvestArea() {
        return investArea;
    }

    public void setInvestArea(BigDecimal investArea) {
        this.investArea = investArea;
    }

    public String getAreaRate() {
        return areaRate;
    }

    public void setAreaRate(String areaRate) {
        this.areaRate = areaRate;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitName() {
        return DictionaryStorage.get(PriceUnit.class.getName(),this.getUnit()).getName();
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public BigDecimal getPropertyUnitPrice() {
        return propertyUnitPrice;
    }

    public void setPropertyUnitPrice(BigDecimal propertyUnitPrice) {
        this.propertyUnitPrice = propertyUnitPrice;
    }

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
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
}
