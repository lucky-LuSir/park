package com.kfwy.park.controller.sys.sms.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.sys.sms.entity.SmsSendHistoryEntity;

import java.util.Date;

/**
 * @Description 短信发送历史
 * @Auth luming
 * @Date 2019/7/27 14:16
 * @Version 1.0
 * @Param
 * @return
 */
public class SmsSendHistoryServiceRequest extends AbstractServiceRequest<SmsSendHistoryEntity> {
    /**
     * 短信发送开始时间
     */
    private Date sendDateStart;

    /**
     * 短信发送结束时间
     */
    private Date sendDateEnd;

    /*-------------------------------------------------------------*/

    public Date getSendDateStart() {
        return sendDateStart;
    }

    public void setSendDateStart(Date sendDateStart) {
        this.sendDateStart = sendDateStart;
    }

    public Date getSendDateEnd() {
        return sendDateEnd;
    }

    public void setSendDateEnd(Date sendDateEnd) {
        this.sendDateEnd = sendDateEnd;
    }
}
