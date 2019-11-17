package com.kfwy.park.controller.report.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.bi.report.entity.ElectricityReceivableBiEntity;

/**
 * @Description 电费应收报表
 * @Auth luming
 * @Date 2019/1/7 15:14
 * @Version 1.0
 * @Param 
 * @return 
 */
public class ElectricityReceivableBiServiceRequest extends AbstractServiceRequest<ElectricityReceivableBiEntity> {
    /**
     *签单人的部门编码
     */
    private String contractorDeptCode;

    /**
     *园区编码
     */
    private String pkCode;

    public String getContractorDeptCode() {
        return contractorDeptCode;
    }

    public void setContractorDeptCode(String contractorDeptCode) {
        this.contractorDeptCode = contractorDeptCode;
    }

    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
    }
}
