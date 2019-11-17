package com.kfwy.park.controller.pro.inspect.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.pro.inspect.entity.InspectObjectRuleEntity;

public class InspectObjectRuleServiceRequest extends AbstractServiceRequest<InspectObjectRuleEntity> {
    private String taskCode;

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }
}
