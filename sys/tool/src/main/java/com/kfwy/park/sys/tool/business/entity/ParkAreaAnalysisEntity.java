package com.kfwy.park.sys.tool.business.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;

/**
 * Created by gengtao on 2019/5/30.
 */
public class ParkAreaAnalysisEntity extends BaseEntity{
    /**
     *园区编号
     **/
    private String pkCode;

    /**
     *园区名称
     **/
    private String pkName;

    /**
     *楼栋编号
    **/
    private String bdCode;
    /**
     *楼栋名称
     **/
    private String bdName;

    /**
     *楼层编号
     **/
    private String flCode;
    /**
     *楼层名称
     **/
    private String thisFloor;

    /**
     *楼层总面积
     **/
    private BigDecimal investArea;

    /**
     *楼层可租面积
     **/
    private BigDecimal rentableArea;

    /**
     *楼层已租面积
     **/
    private BigDecimal rentedArea;

    /**
     *该楼层合同已租面积
     **/
    private BigDecimal contractRentedArea;

    /**
     *异常或正常
     **/
    private String checked;


    public ParkAreaAnalysisEntity() {
    }

    public ParkAreaAnalysisEntity(String pkCode, String pkName, String bdCode, String bdName, String flCode, String thisFloor, BigDecimal investArea, BigDecimal rentableArea, BigDecimal rentedArea, BigDecimal contractRentedArea, String checked) {
        this.pkCode = pkCode;
        this.pkName = pkName;
        this.bdCode = bdCode;
        this.bdName = bdName;
        this.flCode = flCode;
        this.thisFloor = thisFloor;
        this.investArea = investArea;
        this.rentableArea = rentableArea;
        this.rentedArea = rentedArea;
        this.contractRentedArea = contractRentedArea;
        this.checked = checked;
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

    public String getThisFloor() {
        return thisFloor;
    }

    public void setThisFloor(String thisFloor) {
        this.thisFloor = thisFloor;
    }

    public BigDecimal getInvestArea() {
        return investArea;
    }

    public void setInvestArea(BigDecimal investArea) {
        this.investArea = investArea;
    }

    public BigDecimal getRentableArea() {
        return rentableArea;
    }

    public void setRentableArea(BigDecimal rentableArea) {
        this.rentableArea = rentableArea;
    }

    public BigDecimal getRentedArea() {
        return rentedArea;
    }

    public void setRentedArea(BigDecimal rentedArea) {
        this.rentedArea = rentedArea;
    }

    public BigDecimal getContractRentedArea() {
        return contractRentedArea;
    }

    public void setContractRentedArea(BigDecimal contractRentedArea) {
        this.contractRentedArea = contractRentedArea;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }
}
