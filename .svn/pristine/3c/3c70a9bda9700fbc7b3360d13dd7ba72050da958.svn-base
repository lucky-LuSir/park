package com.kfwy.park.pro.house.building.business.impl;


import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.house.building.business.IParkButtJointManager;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.dao.IParkButttJointDbDao;
import com.kfwy.park.pro.house.building.entity.ParkButtJointEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author xiongzhan
 * @Descirption ${DESCRIPTION}
 * @Date 2018/6/26 15:12
 */
@Service
public class ParkButtJointManagerImpl extends AbstractManager<ParkButtJointEntity> implements IParkButtJointManager {

    @Autowired
    private IParkButttJointDbDao parkButttJointDbDao;

    @Autowired
    private IParkManager parkManager;


    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.parkButttJointDbDao;
    }

    @Override
    public List<ParkButtJointEntity> selectByPkCode(Map pbjEntity) {
        return parkButttJointDbDao.selectByPkCode(pbjEntity);
    }

    @Override
    public List<String> selectByEmpCode(String empCode) {
        return parkButttJointDbDao.selectByEmpCode(empCode);
    }

    /**
     * @Description 在招商管理页面绑定园区
     * @author wangchuang
     * @Date 2018/9/19 18:57
     * @Version 1.0
     * @Param [entity]
     * @return int
     **/
    @Override
    public int createByPkCode(ParkButtJointEntity entity) {

        entity.setPbjCode(CodeGenUtils.generate());

        //查询园区信息
        String pkCode = entity.getPkCode();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pkCode",pkCode);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        ParkEntity parkEntity = parkManager.findOne(param);
        String address = parkEntity.getProvinceName() + parkEntity.getCityName() + parkEntity.getRegionName() + parkEntity.getStreetName() + parkEntity.getAddress();
        entity.setPkAddress(address);

        return this.create(entity);
    }
}