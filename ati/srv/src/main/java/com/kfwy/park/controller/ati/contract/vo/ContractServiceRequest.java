package com.kfwy.park.controller.ati.contract.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.ati.contract.entity.ContractEntity;

import java.util.Date;

/**
 * Created by davidcun on 2018/5/22.
 */
public class ContractServiceRequest extends AbstractServiceRequest<ContractEntity> {
    /**
     * 关键字
     */
    private String keyword;

    /**
     * 是否退租（用于园区详情页入驻客户显示，过滤掉已经退租的客户）
     */
    private Boolean ctStatusFlag;

    /**
     * 签约日期开始日
     */
    private Date contractDateStart;

    /**
     * 签约日期结束日
     */
    private Date contractDateEnd;

    /**
     * 查询的是否是园区负责人个人的信息
     */
    private String ifMe;

    /**
     * 楼栋编号
     */
    private String bdCode;

    /**
     * 年份和月份
     */
    private String mmAndYY;

    /**
     * 月份，用于电表抄表监控，根据月份查询
     */
    private Date recordMonth;

    /*--------------------------------------------------------------------*/

    public String getIfMe() {
        return ifMe;
    }

    public void setIfMe(String ifMe) {
        this.ifMe = ifMe;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getCtStatusFlag() {
        return ctStatusFlag;
    }

    public void setCtStatusFlag(Boolean ctStatusFlag) {
        this.ctStatusFlag = ctStatusFlag;
    }

    public Date getContractDateStart() {
        return contractDateStart;
    }

    public void setContractDateStart(Date contractDateStart) {
        this.contractDateStart = contractDateStart;
    }

    public Date getContractDateEnd() {
        return contractDateEnd;
    }

    public void setContractDateEnd(Date contractDateEnd) {
        this.contractDateEnd = contractDateEnd;
    }

    public String getBdCode() {
        return bdCode;
    }

    public void setBdCode(String bdCode) {
        this.bdCode = bdCode;
    }

    public String getMmAndYY() {
        return mmAndYY;
    }

    public void setMmAndYY(String mmAndYY) {
        this.mmAndYY = mmAndYY;
    }

    public Date getRecordMonth() {
        return recordMonth;
    }

    public void setRecordMonth(Date recordMonth) {
        this.recordMonth = recordMonth;
    }
}
