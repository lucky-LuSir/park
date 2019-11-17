package com.kfwy.park.pro.house.building.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.pro.house.building.entity.RoomEntity;

/**
 * @author xiongzhan
 * @Descirption ${DESCRIPTION}
 * @Date 2018/8/14 14:20
 */
public interface IRoomManager extends IManager<RoomEntity> {
    /**
     * @Description 创建房间时校验房间面积是否大于楼层
     * @Auth luming
     * @Date 2018/8/27 9:05
     * @Version 1.0
     * @Param
     * @return
     */
    Boolean acreage(RoomEntity roomEntity) throws Exception;

    /**
     * @Description 根据房间code删除房间
     * @Auth luming
     * @Date 2018/10/30 15:54
     * @Version 1.0
     * @Param
     * @return
     */
    void realDelete(String roomCode);
}
