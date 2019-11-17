package com.kfwy.park.sys.sms.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.sys.sms.dao.ISmsSendHistoryDbDao;
import com.kfwy.park.sys.sms.entity.SmsSendHistoryEntity;
import org.springframework.stereotype.Repository;

/**
 * @Description 短信发送记录
 * @Auth luming
 * @Date 2019/7/23 17:35
 * @Version 1.0
 * @Param
 * @return
 */
@Repository
public class SmsSendHistoryDbDaoImpl extends AbstractMyBatisDao<SmsSendHistoryEntity, Long> implements ISmsSendHistoryDbDao {
}
