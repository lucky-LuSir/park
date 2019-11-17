package com.kfwy.park.sys.sms.business;

import com.aliyuncs.exceptions.ClientException;
import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.sys.sms.entity.SmsSendHistoryEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description 短信发送记录
 * @Auth luming
 * @Date 2019/7/23 17:35
 * @Version 1.0
 * @Param
 * @return
 */
public interface ISmsSendHistoryManager extends IManager<SmsSendHistoryEntity> {

    /**
     * @Description 新增短信发送历史记录
     * @Auth luming
     * @Date 2019/7/25 17:25
     * @Version 1.0
     * @Param
     * @return
     */
    int insertSmsSendHistory(Map<String, Object> map) throws Exception;
}
