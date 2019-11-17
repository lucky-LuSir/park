package com.kfwy.park.pro.house.building.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.house.building.dao.IRoomDbDao;
import com.kfwy.park.pro.house.building.entity.RoomEntity;
import org.springframework.stereotype.Repository;

/**
 * @author xiongzhan
 * @Descirption ${DESCRIPTION}
 * @Date 2018/8/14 14:20
 */
@Repository
public class RoomDbDaoImpl extends AbstractMyBatisDao<RoomEntity,Long> implements IRoomDbDao {
    /**
     * @Description 根据房间code删除房间
     * @Auth luming
     * @Date 2018/10/30 15:21
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public void realDelete(String roomCode) {
        this.getSqlSession().delete(this.mapperNamespace + "realDeleteByRoomCode", roomCode);
    }
}
