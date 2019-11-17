package com.kfwy.park.controller.report.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.bi.report.entity.AccountReceivableBiEntity;

/**
 * @Description 招商人员任务、去化数据
 * @Auth luming
 * @Date 2018/12/3 9:34
 * @Version 1.0
 * @Param
 * @return
 */
public class AccountReceivableBiServiceRequest extends AbstractServiceRequest<AccountReceivableBiEntity> {
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
