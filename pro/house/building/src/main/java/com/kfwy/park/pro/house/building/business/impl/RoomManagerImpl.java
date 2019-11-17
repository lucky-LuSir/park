package com.kfwy.park.pro.house.building.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.house.building.business.IRoomManager;
import com.kfwy.park.pro.house.building.dao.IFloorDbDao;
import com.kfwy.park.pro.house.building.dao.IRoomDbDao;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import com.kfwy.park.pro.house.building.entity.RoomEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiongzhan
 * @Descirption ${DESCRIPTION}
 * @Date 2018/8/14 14:20
 */
@Service
@Transactional
public class RoomManagerImpl extends AbstractManager<RoomEntity> implements IRoomManager {
    @Autowired
    private IRoomDbDao roomDbDao;

    @Autowired
    private IFloorDbDao floorDbDao;

    @Override
    protected void beforeCreate(RoomEntity roomEntity) {
        String roomCode = CodeGenUtils.generate();
        roomEntity.setRoomCode(roomCode);

        //新增房间状态为热阻中
        roomEntity.setRoomStatus("1");
    }

    @Override
    protected void afterCreate(RoomEntity roomEntity) {
        super.afterCreate(roomEntity);
    }

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.roomDbDao;
    }

    /**
     * @return
     * @Description 创建房间时校验房间面积是否大于楼层
     * @Auth luming
     * @Date 2018/8/27 9:06
     * @Version 1.0
     * @Param
     */
    @Override
    public Boolean acreage(RoomEntity roomEntity) throws Exception {
        Boolean flag = true;

        Map<String, Object> map = new HashMap<>();
        map.put("flCode", roomEntity.getFlCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        // 查询楼层面积
        FloorEntity floorEntity = floorDbDao.selectUniqueByMap(map);
        int flInvestArea = floorEntity.getInvestArea().intValue();

        // 前端输入的面积
        int rmInvestArea = roomEntity.getInvestArea().intValue();

        if (rmInvestArea > flInvestArea) {
            flag = false;
        } else {
            List<RoomEntity> roomEntityList = roomDbDao.selectByMap(map);

            int roomInvestAreaSum = 0;

            // 新增房间
            if (StringUtils.isEmpty(roomEntity.getRoomCode())) {
                for (int i = 0; i < roomEntityList.size(); i++) {
                    int intRmInvestArea = roomEntityList.get(i).getInvestArea().intValue();
                    roomInvestAreaSum += intRmInvestArea;
                }

                if ((roomInvestAreaSum + rmInvestArea) > flInvestArea) {
                    flag = false;
                }
            } else {    // 修改房间
                for (int i = 0; i < roomEntityList.size(); i++) {
                    String roomCode = roomEntity.getRoomCode();
                    String roomCode1 = roomEntityList.get(i).getRoomCode();

                    if (!roomCode.equals(roomCode1)) {
                        int intRmInvestArea = roomEntityList.get(i).getInvestArea().intValue();
                        roomInvestAreaSum += intRmInvestArea;
                    }
                }

                if ((roomInvestAreaSum + rmInvestArea) > flInvestArea) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     * @Description 根据房间code删除房间
     * @Auth luming
     * @Date 2018/10/30 15:54
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public void realDelete(String roomCode) {
        roomDbDao.realDelete(roomCode);
    }
}
