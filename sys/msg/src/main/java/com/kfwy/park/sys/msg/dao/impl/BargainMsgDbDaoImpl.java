package com.kfwy.park.sys.msg.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.sys.msg.dao.IBargainMsgDbDao;
import com.kfwy.park.sys.msg.entity.BargainMsgEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by luming on 2019/6/21.
 */
@Repository
public class BargainMsgDbDaoImpl extends AbstractMyBatisDao<BargainMsgEntity, Long> implements IBargainMsgDbDao {
}
