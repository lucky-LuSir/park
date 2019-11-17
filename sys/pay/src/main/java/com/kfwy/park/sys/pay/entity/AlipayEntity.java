package com.kfwy.park.sys.pay.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * @Author gengtao
 * @Date 2019/7/25 11:12
 **/
public class AlipayEntity extends BaseEntity {
    /**
     *商家订单号
     **/
    private String widOutTradeNo;

    /**
     *支付宝交易号
     **/
    private String trade_no;

    /**
     *订单金额
     **/
    private String widTotalAmount;

    /**
     *订单名称
     **/
    private String widSubject;

    /**
     *商品描述
     **/
    private String widBody;

    /**
     *支付状态
     **/
    private String tradeStatus;
    /**
     *买家在支付宝的用户id
     **/
    private String buyerUserId;

    /**
     *买家支付宝账号
     **/
    private String buyerLogonId;


    public String getWidOutTradeNo() {
        return widOutTradeNo;
    }

    public void setWidOutTradeNo(String widOutTradeNo) {
        this.widOutTradeNo = widOutTradeNo;
    }

    public String getWidTotalAmount() {
        return widTotalAmount;
    }

    public void setWidTotalAmount(String widTotalAmount) {
        this.widTotalAmount = widTotalAmount;
    }

    public String getWidSubject() {
        return widSubject;
    }

    public void setWidSubject(String widSubject) {
        this.widSubject = widSubject;
    }

    public String getWidBody() {
        return widBody;
    }

    public void setWidBody(String widBody) {
        this.widBody = widBody;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getBuyerUserId() {
        return buyerUserId;
    }

    public void setBuyerUserId(String buyerUserId) {
        this.buyerUserId = buyerUserId;
    }

    public String getBuyerLogonId() {
        return buyerLogonId;
    }

    public void setBuyerLogonId(String buyerLogonId) {
        this.buyerLogonId = buyerLogonId;
    }
}