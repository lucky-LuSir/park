package com.kfwy.park.pro.feedback.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.feedback.business.IFeedbackReplyManager;
import com.kfwy.park.pro.feedback.dao.IFeedbackReplyDbDao;
import com.kfwy.park.pro.feedback.entity.FeedbackReplyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Service
public class FeedbackReplyManagerImpl extends AbstractManager<FeedbackReplyEntity> implements IFeedbackReplyManager {

    @Autowired
    private IFeedbackReplyDbDao feedbackReplyDbDao;


    @Override
    protected IMyBatisBaseDao<FeedbackReplyEntity, Long> getMyBatisDao() {
        return this.feedbackReplyDbDao;
    }

    @Override
    protected void beforeCreate(FeedbackReplyEntity feedbackReplyEntity) {
        //生成问题反馈编码
        feedbackReplyEntity.setReplyCode(CodeGenUtils.generate());

    }

}
