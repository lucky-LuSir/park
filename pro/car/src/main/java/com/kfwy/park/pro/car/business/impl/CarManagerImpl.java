package com.kfwy.park.pro.car.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.pro.car.business.ICarManager;
import com.kfwy.park.pro.car.dao.ICarEntityDao;
import com.kfwy.park.pro.car.entity.CarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gengtao on 2019/6/11.
 */
@Service
public class CarManagerImpl extends AbstractManager<CarEntity> implements ICarManager {
    @Autowired
    private ICarEntityDao carEntityDao;

    @Override
    protected IMyBatisBaseDao<CarEntity, Long> getMyBatisDao() {
        return carEntityDao;
    }

    @Override
    public void updataAll(CarEntity entity) {
        carEntityDao.updateAll(entity);
    }

    @Override
    public void checkParam(CarEntity entity) throws IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("pkCode");
        list.add("pkName");
        list.add("visitor");
        list.add("inTime");
        list.add("visitorPhone");
        list.add("visitorNum");
        list.add("beVisitorName");
        list.add("beVisitorPhone");
        list.add("visitorIdCard");
        list.add("inOutType");
        ParamUtil<CarEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(entity,list);
    }
}
