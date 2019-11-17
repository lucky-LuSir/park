package com.kfwy.park.controller.sys.pay.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.sys.pay.entity.AlipayEntity;

/**
 * @Author gengtao
 * @Date 2019/7/25 11:21
 **/
public class AliPayServiceRequest extends AbstractServiceRequest<AlipayEntity> {
    private String billDate;

    private String billType;

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }
}