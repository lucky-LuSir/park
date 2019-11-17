package com.kfwy.park.pro.house.building.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.house.building.entity.RoomEntity;

/**
 * @author xiongzhan
 * @Descirption ${DESCRIPTION}
 * @Date 2018/8/14 14:20
 */
public interface IRoomDbDao extends IMyBatisBaseDao<RoomEntity,Long> {

    /**
     * @Description 根据房间code删除房间
     * @Auth luming
     * @Date 2018/10/30 15:20
     * @Version 1.0
     * @Param
     * @return
     */
    void realDelete(String roomCode);
}
