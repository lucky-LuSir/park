package com.kfwy.park.pro.house.bi.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;

/*
 * @Description 园区统计表
 * @Auth liuzhengyang
 * @Date 2018/6/26 16:05
 * @Version 1.0
 * @Param 
 * @return  
 */
public class ParkBiEntity extends BaseEntity {

    /**
     * 园区编码
     */
    private String pkCode;
    /**
     * 园区名
     */
    private String pkName;
    /**
     * 园区地址
     */
    private String address;
    /**
     * 所属大区（部门编号）
     */
    private String territoryType;
    /**
     * 所属大区名称
     */
    private String territoryTypeName;



    /*----------------------------------当前显示栏字段start----------------------------------*/

    /**
     * 园区承租面积
     */
    private BigDecimal acreage;
    /**
     * 园区公摊比
     */
    private BigDecimal areaRate;
    /**
     * 出租计租面积(合同中该园区出租含公摊的面积和)
     */
    private BigDecimal rentInvestArea;
    /**
     * 当前空置面积
     */
    private BigDecimal rentableArea;
    /**
     * 空置比例
     */
    private BigDecimal areaProp;
    /**
     * 15天内应缴租金户
     */
    private Integer rentWarningCount;
    /**
     * 楼栋总数
     */
    private BigDecimal buildingTotal;
    /**
     * 2个月内合同到期户
     */
    private Integer ctWarningCount;
    /**
     * 费用逾期数
     */
    private Integer overdueCount;
    /**
     * 剩余电量
     */
    private BigDecimal useElecSurplus;
    /**
     * 抄表日期
     */
    private String meterReading;



    /*----------------------------------当前显示栏字段end----------------------------------*/


    /**
     * 意向客户数
     */
    private Integer intentCusCount;
    /**
     * 入住客户总数
     */
    private Integer cusCount;

    /**
     * 本月营收
     */
    private BigDecimal monthRevenue;
    /**
     * 费用支出
     */
    private BigDecimal expenditure;
    /**
     * 利润增长
     */
    private BigDecimal profitGrowth;
    /**
     * 每月抄表日期
     */
    private String meterData;
    /**
     * 15日内应收房租和物业费
     */
    private BigDecimal totalCost;

    /**
     * 房源省市区街道名称
     */
    private String provinceName;
    private String cityName;
    private String regionName;
    private String streetName;

    /**
     * 毛利率
     */
    private BigDecimal projectGross; //项目毛利率
    private BigDecimal investGross; //出租毛利率
    /*=================================字段分割线====================================*/

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getIntentCusCount() {
        return intentCusCount;
    }

    public void setIntentCusCount(Integer intentCusCount) {
        this.intentCusCount = intentCusCount;
    }

    public Integer getCusCount() {
        return cusCount;
    }

    public void setCusCount(Integer cusCount) {
        this.cusCount = cusCount;
    }

    public Integer getCtWarningCount() {
        return ctWarningCount;
    }

    public void setCtWarningCount(Integer ctWarningCount) {
        this.ctWarningCount = ctWarningCount;
    }

    public Integer getOverdueCount() {
        return overdueCount;
    }

    public void setOverdueCount(Integer overdueCount) {
        this.overdueCount = overdueCount;
    }

    public BigDecimal getRentableArea() {
        return rentableArea;
    }

    public void setRentableArea(BigDecimal rentableArea) {
        this.rentableArea = rentableArea;
    }

    public BigDecimal getAreaProp() {
        return areaProp;
    }

    public void setAreaProp(BigDecimal areaProp) {
        this.areaProp = areaProp;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public BigDecimal getAreaRate() {
        return areaRate;
    }

    public void setAreaRate(BigDecimal areaRate) {
        this.areaRate = areaRate;
    }

    public BigDecimal getRentInvestArea() {
        return rentInvestArea;
    }

    public void setRentInvestArea(BigDecimal rentInvestArea) {
        this.rentInvestArea = rentInvestArea;
    }

    public BigDecimal getMonthRevenue() {
        return monthRevenue;
    }

    public void setMonthRevenue(BigDecimal monthRevenue) {
        this.monthRevenue = monthRevenue;
    }

    public BigDecimal getExpenditure() {
        return expenditure;
    }

    public void setExpenditure(BigDecimal expenditure) {
        this.expenditure = expenditure;
    }

    public BigDecimal getProfitGrowth() {
        return profitGrowth;
    }

    public void setProfitGrowth(BigDecimal profitGrowth) {
        this.profitGrowth = profitGrowth;
    }

    public String getMeterData() {
        return meterData;
    }

    public void setMeterData(String meterData) {
        this.meterData = meterData;
    }

    public BigDecimal getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(BigDecimal totalCost) {
        this.totalCost = totalCost;
    }

    public Integer getRentWarningCount() {
        return rentWarningCount;
    }

    public void setRentWarningCount(Integer rentWarningCount) {
        this.rentWarningCount = rentWarningCount;
    }

    public BigDecimal getBuildingTotal() {
        return buildingTotal;
    }

    public void setBuildingTotal(BigDecimal buildingTotal) {
        this.buildingTotal = buildingTotal;
    }

    public BigDecimal getUseElecSurplus() {
        return useElecSurplus;
    }

    public void setUseElecSurplus(BigDecimal useElecSurplus) {
        this.useElecSurplus = useElecSurplus;
    }

    public String getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(String meterReading) {
        this.meterReading = meterReading;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTerritoryType() {
        return territoryType;
    }

    public void setTerritoryType(String territoryType) {
        this.territoryType = territoryType;
    }

    public String getTerritoryTypeName() {
        return territoryTypeName;
    }

    public void setTerritoryTypeName(String territoryTypeName) {
        this.territoryTypeName = territoryTypeName;
    }

    public BigDecimal getProjectGross() {
        return projectGross;
    }

    public void setProjectGross(BigDecimal projectGross) {
        this.projectGross = projectGross;
    }

    public BigDecimal getInvestGross() {
        return investGross;
    }

    public void setInvestGross(BigDecimal investGross) {
        this.investGross = investGross;
    }
}
