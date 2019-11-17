package com.kfwy.park.controller.pro.inspect.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.pro.inspect.entity.InspectInfoEntity;

import java.util.Date;

public class InspectInfoServiceRequest extends AbstractServiceRequest<InspectInfoEntity> {
    private String keyword;

    private Date inspectStartTime;

    private Date inspectEndTime;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Date getInspectStartTime() {
        return inspectStartTime;
    }

    public void setInspectStartTime(Date inspectStartTime) {
        this.inspectStartTime = inspectStartTime;
    }

    public Date getInspectEndTime() {
        return inspectEndTime;
    }

    public void setInspectEndTime(Date inspectEndTime) {
        this.inspectEndTime = inspectEndTime;
    }
}
