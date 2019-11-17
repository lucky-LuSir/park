package com.kfwy.park.pro.feedback.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.feedback.dao.IFeedbackDbDao;
import com.kfwy.park.pro.feedback.entity.FeedbackEntity;
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
public class FeedbackDbDaoImpl extends AbstractMyBatisDao<FeedbackEntity,Long> implements IFeedbackDbDao {
}
