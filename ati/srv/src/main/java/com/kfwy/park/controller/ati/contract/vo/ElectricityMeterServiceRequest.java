package com.kfwy.park.controller.ati.contract.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.ati.contract.entity.ElectricityMeterEntity;
import com.kfwy.park.controller.ati.contract.ContractService;

import java.util.List;

/**
 * @author wangchuang
 * @Description
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class ElectricityMeterServiceRequest extends AbstractServiceRequest<ElectricityMeterEntity> {

    /**
     * 电表集合
     */
    private List<ElectricityMeterEntity> meterList;

    /**
     * 合同集合
     * 电表批量抄表，前端传入合同信息集合，便于后端获取集合
     */
    private List<ContractEntity> contractList;

    /**
     * 电表code
     */
    private String eleMeterCode;

    /*----------------------------------------------------------*/

    public List<ElectricityMeterEntity> getMeterList() {
        return meterList;
    }

    public void setMeterList(List<ElectricityMeterEntity> meterList) {
        this.meterList = meterList;
    }

    public List<ContractEntity> getContractList() {
        return contractList;
    }

    public void setContractList(List<ContractEntity> contractList) {
        this.contractList = contractList;
    }

    public String getEleMeterCode() {
        return eleMeterCode;
    }

    public void setEleMeterCode(String eleMeterCode) {
        this.eleMeterCode = eleMeterCode;
    }
}
