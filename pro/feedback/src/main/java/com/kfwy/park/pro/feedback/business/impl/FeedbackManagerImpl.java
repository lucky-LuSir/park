package com.kfwy.park.pro.feedback.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.feedback.business.IFeedbackManager;
import com.kfwy.park.pro.feedback.dao.IFeedbackDbDao;
import com.kfwy.park.pro.feedback.entity.FeedbackEntity;
import org.apache.commons.lang3.StringUtils;
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
public class FeedbackManagerImpl extends AbstractManager<FeedbackEntity> implements IFeedbackManager {

    @Autowired
    private IFeedbackDbDao feedbackDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.feedbackDbDao;
    }


    @Override
    protected void beforeCreate(FeedbackEntity feedbackEntity) {
        //生成问题反馈编码
        feedbackEntity.setFbCode(CodeGenUtils.generate());

        if(StringUtils.isEmpty(feedbackEntity.getDealQuestionCode())) {
            //无处理人的话，这则问题反馈状态为 "1",待处理
            feedbackEntity.setFbStatus("1");
        } else {
            //有处理人的话，这则问题反馈状态为 "2",已受理
            feedbackEntity.setFbStatus("2");
        }


    }

}
