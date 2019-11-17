package com.kfwy.park.controller.act.activiti.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.act.activiti.entity.ApprovalBaseEntity;

/**
 * Created by kfwy_it_004 on 2019/3/25.
 */
public class ApprovalBaseServiceRequest extends AbstractServiceRequest<ApprovalBaseEntity> {
    /*
    退租申请单编号
     */
    private String throwLeaseApplyCode;

    public String getThrowLeaseApplyCode() {
        return throwLeaseApplyCode;
    }

    public void setThrowLeaseApplyCode(String throwLeaseApplyCode) {
        this.throwLeaseApplyCode = throwLeaseApplyCode;
    }
}
