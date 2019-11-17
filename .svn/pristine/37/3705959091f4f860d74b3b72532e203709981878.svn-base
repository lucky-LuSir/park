package com.kfwy.park.pro.house.building.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.pro.house.building.dao.IParkButttJointDbDao;
import com.kfwy.park.pro.house.building.entity.ParkButtJointEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * @author xiongzhan
 * @Descirption ${DESCRIPTION}
 * @Date 2018/6/26 15:12
 */
@Repository
public class ParkButttJointImpl extends AbstractMyBatisDao<ParkButtJointEntity,Long> implements IParkButttJointDbDao {

    /**
     * @Description 根据pkCode查询对接人员
     * @Auth xiongzhan
     * @Date 2018/8/6 19:54
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<ParkButtJointEntity> selectByPkCode(Map pbjEntity){
        return this.getSqlSession().selectList(this.mapperNamespace + "selectByPkCode",pbjEntity);
    }

    /**
     * @Description 根据empCode查询对接人员负责的园区
     * @Auth xiongzhan
     * @Date 2018/8/9 9:54
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<String> selectByEmpCode(String empCode) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectByEmpCode",empCode);
    }
}