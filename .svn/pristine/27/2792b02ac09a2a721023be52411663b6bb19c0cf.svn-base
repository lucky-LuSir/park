package com.kfwy.park.controller.ati.contract.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.ati.contract.entity.WaterMeterEntity;

import java.util.List;

/**
 * @author wangchuang
 * @Description 水表
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class WaterMeterServiceRequest extends AbstractServiceRequest<WaterMeterEntity> {

    /**
     * 合同集合
     * 电表批量抄表，前端传入合同信息集合，便于后端获取集合
     */
    private List<ContractEntity> contractList;

    /**
     * 水表code
     */
    private String waterMeterCode;

    /*-------------------------------------------------------*/

    public List<ContractEntity> getContractList() {
        return contractList;
    }

    public void setContractList(List<ContractEntity> contractList) {
        this.contractList = contractList;
    }

    public String getWaterMeterCode() {
        return waterMeterCode;
    }

    public void setWaterMeterCode(String waterMeterCode) {
        this.waterMeterCode = waterMeterCode;
    }
}
