package com.kfwy.park.ati.customer.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.customer.enums.FlStatus;

import java.math.BigDecimal;

/**
 * @author wangchuang
 * @Descirption 客户意向房源实体
 * @Date 2018/6/5 8:41
 */
public class CustomerIntentParkEntity extends BaseEntity {

    /**客户跟进编码*/
    private String followupCode;
    /**园区编码*/
    private String pkCode;
    /**
     * 园区省/市/区编码
     */
    private String provinceCode;
    private String cityCode;
    private String regionCode;
    /**
     *园区省/市/区名称
     */
    private String provinceName;
    private String cityName;
    private String regionName;
    /**园区地址*/
    private String pkAddress;
    /**建筑编码*/
    private String bdCode;
    /**楼层编码*/
    private String flCode;
    /**可租用面积*/
    private BigDecimal rentableArea;
    /**
     * 楼层状态
     * @see FlStatus
     */
    private String flStatus;
    /**
     * 楼层状态名称
     */
    private String flStatusName;
    /**客户评价*/
    private String cusAppraisal;

    public String getFollowupCode() {
        return followupCode;
    }

    public void setFollowupCode(String followupCode) {
        this.followupCode = followupCode;
    }

    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
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

    public String getPkAddress() {
        return pkAddress;
    }

    public void setPkAddress(String pkAddress) {
        this.pkAddress = pkAddress;
    }

    public String getBdCode() {
        return bdCode;
    }

    public void setBdCode(String bdCode) {
        this.bdCode = bdCode;
    }

    public String getFlCode() {
        return flCode;
    }

    public void setFlCode(String flCode) {
        this.flCode = flCode;
    }

    public BigDecimal getRentableArea() {
        return rentableArea;
    }

    public void setRentableArea(BigDecimal rentableArea) {
        this.rentableArea = rentableArea;
    }

    public String getFlStatus() {
        return flStatus;
    }

    public void setFlStatus(String flStatus) {
        this.flStatus = flStatus;
    }

    public String getFlStatusName() {
        return DictionaryStorage.get(FlStatus.class.getName(),this.getFlStatus()).getName();
    }

    public void setFlStatusName(String flStatusName) {
        this.flStatusName = flStatusName;
    }

    public String getCusAppraisal() {
        return cusAppraisal;
    }

    public void setCusAppraisal(String cusAppraisal) {
        this.cusAppraisal = cusAppraisal;
    }
}
