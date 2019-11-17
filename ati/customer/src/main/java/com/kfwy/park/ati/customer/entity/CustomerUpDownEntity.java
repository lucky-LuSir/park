package com.kfwy.park.ati.customer.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.customer.enums.CusDownType;
import com.kfwy.park.ati.customer.enums.CusUpdownType;

import java.util.Date;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class CustomerUpDownEntity extends BaseEntity {

    /**
     * 客户编码
     */
    private String cusCode;
    /**
     * 下架类型
     */
    private String cusDownType;
    /**
     * 下架类型名称
     * @see CusDownType
     */
    private String cusDownTypeName;
    /**
     * 类型（上架,下架）
     */
    private String cusUpdownType;
    /**
     * 类型名称
     * @see CusUpdownType
     */
    private String cusUpdownTypeName;
    /**
     * 下架原因
     */
    private String cusDownReason;
    /**
     * 成交区域
     */
    private String provinceCode;
    private String cityCode;
    private String regionCode;
    private String provinceName;
    private String cityName;
    private String regionName;
    /**
     * 被谁成交
     */
    private String dealPeople;
    /**
     * 上下架员工编号
     */
    private String empCode;
    /**
     * 上下架员工姓名
     */
    private String empName;
    /**
     * 客户所属人编号
     */
    private String cusEmpCode;
    /**
     * 客户所属人姓名
     */
    private String cusEmpName;

    /**
     * 合同签订日期
     */
    private Date contractStartTime;

    /**
     * 合同结束日期
     */
    private Date contractEndTime;

    /*******************字段分割**************************/

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getCusDownType() {
        return cusDownType;
    }

    public void setCusDownType(String cusDownType) {
        this.cusDownType = cusDownType;
    }

    public String getCusDownTypeName() {
        return DictionaryStorage.get(CusDownType.class.getName(),this.getCusDownType()).getName();
    }

    public void setCusDownTypeName(String cusDownTypeName) {
        this.cusDownTypeName = cusDownTypeName;
    }

    public String getCusUpdownType() {
        return cusUpdownType;
    }

    public void setCusUpdownType(String cusUpdownType) {
        this.cusUpdownType = cusUpdownType;
    }

    public String getCusUpdownTypeName() {
        return DictionaryStorage.get(CusUpdownType.class.getName(),this.getCusUpdownType()).getName();
    }

    public void setCusUpdownTypeName(String cusUpdownTypeName) {
        this.cusUpdownTypeName = cusUpdownTypeName;
    }

    public String getCusDownReason() {
        return cusDownReason;
    }

    public void setCusDownReason(String cusDownReason) {
        this.cusDownReason = cusDownReason;
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

    public String getDealPeople() {
        return dealPeople;
    }

    public void setDealPeople(String dealPeople) {
        this.dealPeople = dealPeople;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getCusEmpCode() {
        return cusEmpCode;
    }

    public void setCusEmpCode(String cusEmpCode) {
        this.cusEmpCode = cusEmpCode;
    }

    public String getCusEmpName() {
        return cusEmpName;
    }

    public void setCusEmpName(String cusEmpName) {
        this.cusEmpName = cusEmpName;
    }

    public Date getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }
}
