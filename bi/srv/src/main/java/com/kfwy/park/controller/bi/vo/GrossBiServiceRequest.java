package com.kfwy.park.controller.bi.vo;

import com.gniuu.framework.service.AbstractServiceRequest;

import java.util.Date;

/**
 * Created by liuzhengyang on 2018/9/14.
 */
public class GrossBiServiceRequest extends AbstractServiceRequest<GrossBiEntity> {

    private String pkCode;

    private String cityCode;

    private String flCode;

    /**
     * 大区编号
     */
    private String territoryType;

    //查询月份
    private Date selectMonth;

    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public Date getSelectMonth() {
        return selectMonth;
    }

    public void setSelectMonth(Date selectMonth) {
        this.selectMonth = selectMonth;
    }

    public String getTerritoryType() {
        return territoryType;
    }

    public void setTerritoryType(String territoryType) {
        this.territoryType = territoryType;
    }

    public String getFlCode() {
        return flCode;
    }

    public void setFlCode(String flCode) {
        this.flCode = flCode;
    }
}
