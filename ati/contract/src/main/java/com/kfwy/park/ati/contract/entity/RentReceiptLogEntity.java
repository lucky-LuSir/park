package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * Created by liuzhengyang on 2018/8/2.
 */
public class RentReceiptLogEntity extends BaseEntity {

    /**
     *租金编号
     */
    private String rentCode;
    /**
     *ip
     */
    private String ip;
    /**
     *方法
     */
    private String method;




    public String getRentCode() {
        return rentCode;
    }

    public void setRentCode(String rentCode) {
        this.rentCode = rentCode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
