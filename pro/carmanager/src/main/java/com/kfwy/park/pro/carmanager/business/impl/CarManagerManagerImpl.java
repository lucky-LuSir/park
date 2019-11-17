package com.kfwy.park.pro.carmanager.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.pro.carmanager.business.ICarManagerManager;
import com.kfwy.park.pro.carmanager.dao.ICarManagerDao;
import com.kfwy.park.pro.carmanager.entity.CarManagerEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gtaotao
 * @since 2019-07-23 16:37:38
 */
@Service
public class CarManagerManagerImpl extends AbstractManager<CarManagerEntity> implements ICarManagerManager {
    @Autowired
    private ICarManagerDao carManagerDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.carManagerDao;
    }

    @Override
    public void checkParam(CarManagerEntity carManagerEntity) throws IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("licenseNum");
        list.add("pkCode");
        list.add("carOwnerName");
        list.add("carOwnerPhone");
        list.add("carOwnerIdCard");
        list.add("cusCode");
        ParamUtil<CarManagerEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(carManagerEntity, list);
    }
}