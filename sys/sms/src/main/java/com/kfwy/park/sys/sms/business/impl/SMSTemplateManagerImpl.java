package com.kfwy.park.sys.sms.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.sys.sms.business.ISMSTemplateManager;
import com.kfwy.park.sys.sms.dao.ISMSTemplateDbDao;
import com.kfwy.park.sys.sms.entity.SMSTemplateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Description 短信模板
 * @Auth luming
 * @Date 2019/7/11 10:28
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class SMSTemplateManagerImpl extends AbstractManager<SMSTemplateEntity> implements ISMSTemplateManager {
    @Autowired
    private ISMSTemplateDbDao smsTemplateDbDao;

    @Override
    protected IMyBatisBaseDao<SMSTemplateEntity, Long> getMyBatisDao() {
        return this.smsTemplateDbDao;
    }
}
