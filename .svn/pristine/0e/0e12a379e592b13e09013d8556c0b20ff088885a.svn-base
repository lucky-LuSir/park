package com.kfwy.park.controller.ati.contract.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.ati.contract.entity.WaterRecordEntity;

import java.util.Date;
import java.util.List;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class WaterRecordServiceRequest extends AbstractServiceRequest<WaterRecordEntity> {

    /**
     * 抄表信息集合
     */
    private List<WaterRecordEntity> waterRecordList;

    /**
     * 抄表日期开始日和结束日集合
     */
    private List<Date> waterTime;

    /**
     * 合同集合
     */
    private List<ContractEntity> contractList;

    /**
     * 收款截止时间
     * 一键生成电费应收账单，选择收款截止时间
     */
    private Date receivingDate;

    /*-------------------------------------------------------------------------------------*/

    public List<WaterRecordEntity> getWaterRecordList() {
        return waterRecordList;
    }

    public void setWaterRecordList(List<WaterRecordEntity> waterRecordList) {
        this.waterRecordList = waterRecordList;
    }

    public List<Date> getWaterTime() {
        return waterTime;
    }

    public void setWaterTime(List<Date> waterTime) {
        this.waterTime = waterTime;
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
