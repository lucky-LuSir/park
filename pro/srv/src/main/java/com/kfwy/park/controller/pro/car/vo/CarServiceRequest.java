package com.kfwy.park.controller.pro.car.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.pro.car.entity.CarEntity;

import java.util.Date;

/**
 * Created by gengtao on 2019/6/11.
 */
public class CarServiceRequest extends AbstractServiceRequest<CarEntity> {
    private Date outDateStart;

    private Date outDateEnd;

    private String keyword;

    public Date getOutDateStart() {
        return outDateStart;
    }

    public void setOutDateStart(Date outDateStart) {
        this.outDateStart = outDateStart;
    }

    public Date getOutDateEnd() {
        return outDateEnd;
    }

    public void setOutDateEnd(Date outDateEnd) {
        this.outDateEnd = outDateEnd;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
