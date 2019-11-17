package com.kfwy.park.sys.board.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.sys.board.entity.BoardEntity;

/**
 * Created by liuzhengyang on 2018/7/23.
 */
public interface IBoardManager extends IManager<BoardEntity> {
    void delete(Long id);

    //批量插入
    void batchCreate(String pgCodes, String createCodes);
}
