package com.kfwy.park.pro.feedback.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.feedback.dao.IFeedbackReplyDbDao;
import com.kfwy.park.pro.feedback.entity.FeedbackReplyEntity;
import org.springframework.stereotype.Repository;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Repository
public class FeedbackReplyDbDaoImpl extends AbstractMyBatisDao<FeedbackReplyEntity, Long> implements IFeedbackReplyDbDao {
}
