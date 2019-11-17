package com.kfwy.park.sys.board.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.sys.board.dao.IBoardDbDao;
import com.kfwy.park.sys.board.entity.BoardEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by liuzhengyang on 2018/7/23.
 */
@Repository
public class BoardDbDao extends AbstractMyBatisDao<BoardEntity,Long> implements IBoardDbDao {
}
