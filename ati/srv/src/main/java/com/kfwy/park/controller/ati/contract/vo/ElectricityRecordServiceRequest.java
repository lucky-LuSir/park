package com.kfwy.park.controller.ati.contract.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.ati.contract.entity.ElectricityRecordEntity;

import java.util.Date;
import java.util.List;

/**
 * @author wangchuang
 * @Description
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class ElectricityRecordServiceRequest extends AbstractServiceRequest<ElectricityRecordEntity> {

    /**
     * 抄表信息集合
     */
    private List<ElectricityRecordEntity> recordEntityList;

    /**
     * 抄表日期开始日和结束日集合
     */
    private List<Date> eleTime;

    /**
     * 合同集合
     */
    private List<ContractEntity> contractList;

    /**
     * 收款截止时间
     * 一键生成电费应收账单，选择收款截止时间
     */
    private Date receivingDate;

    /*--------------------------------------------------------------------*/

    public List<Date> getEleTime() {
        return eleTime;
    }

    public void setEleTime(List<Date> eleTime) {
        this.eleTime = eleTime;
    }

    public List<ElectricityRecordEntity> getRecordEntityList() {
        return recordEntityList;
    }

    public void setRecordEntityList(List<ElectricityRecordEntity> recordEntityList) {
        this.recordEntityList = recordEntityList;
    }

    public List<ContractEntity> getContractList() {
        return contractList;
    }

    public void setContractList(List<ContractEntity> contractList) {
        this.contractList = contractList;
    }

    public Date getReceivingDate() {
        return receivingDate;
    }

    public void setReceivingDate(Date receivingDate) {
        this.receivingDate = receivingDate;
    }
}
