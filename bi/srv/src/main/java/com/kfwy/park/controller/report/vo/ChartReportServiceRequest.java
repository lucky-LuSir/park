package com.kfwy.park.controller.report.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.pro.house.building.entity.ParkEntity;

import java.util.Date;

/**
 * Created by kfwy_it_004 on 2019/3/15.
 */
public class ChartReportServiceRequest extends AbstractServiceRequest<ParkEntity>{
    /**
     * 开始日期
     */
    private Date startDate;

    /**
     * 结束日期
     */
    private Date endDate;

    private Date selectMonth;

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getSelectMonth() {
        return selectMonth;
    }

    public void setSelectMonth(Date selectMonth) {
        this.selectMonth = selectMonth;
    }
}
