package com.kfwy.park.pro.house.building.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.pro.house.building.entity.ParkButtJointEntity;

import java.util.List;
import java.util.Map;

/**
 * @author xiongzhan
 * @Descirption ${DESCRIPTION}
 * @Date 2018/6/26 15:12
 */
public interface IParkButtJointManager extends IManager<ParkButtJointEntity> {

    /**
     * @Description 根据pkCode查询对接人员
     * @Auth xiongzhan
     * @Date 2018/8/6 8:43
     * @Version 1.0
     * @Param
     * @return
     */
    public List<ParkButtJointEntity> selectByPkCode(Map pbjEntity);

    /**
     * @Description 根据empCode查询对接人员负责的园区
     * @Auth xiongzhan
     * @Date 2018/8/9 9:54
     * @Version 1.0
     * @Param
     * @return
     */
    public List<String> selectByEmpCode(String empCode);

    /**
     * @Description 在招商管理页面绑定园区
     * @author wangchuang
     * @Date 2018/9/19 18:56
     * @Version 1.0
     * @Param [entity]
     * @return int
     **/
    public int createByPkCode(ParkButtJointEntity entity);
}