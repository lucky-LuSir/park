package com.kfwy.park.controller.hrm.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.hrm.org.bi.entity.EmpBiEntity;

/**
 * Created by xpp on 2018/8/6.
 */
public class EmpBiServiceRequest extends AbstractServiceRequest<EmpBiEntity> {

    private String deptCode;

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
}
