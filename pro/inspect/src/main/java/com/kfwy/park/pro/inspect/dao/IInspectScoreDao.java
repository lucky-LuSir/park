package com.kfwy.park.pro.inspect.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.inspect.entity.InspectScoreEntity;
import org.springframework.stereotype.Repository;

/**
 * 物业检查得分信息表 表数据库访问层
 *
 * @author gengtao
 * @since 2019-07-09 13:49:25
 */
public interface IInspectScoreDao extends IMyBatisBaseDao<InspectScoreEntity, Long>{
}