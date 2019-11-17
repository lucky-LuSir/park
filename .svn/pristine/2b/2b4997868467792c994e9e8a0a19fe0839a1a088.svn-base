package com.kfwy.park.sys.area.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.sys.area.business.ICompanyAreaManager;
import com.kfwy.park.sys.area.dao.ICompanyAreaDbDao;
import com.kfwy.park.sys.area.entity.CompanyAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/7/3 09:32
 */
@Service
public class CompanyAreaManagerImpl extends AbstractManager<CompanyAreaEntity> implements ICompanyAreaManager {


    @Autowired
    private ICompanyAreaDbDao companyAreaDbDao;


    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.companyAreaDbDao;
    }

    @Override
    public CompanyAreaEntity findAreaByCode(String areaCode) {
        return companyAreaDbDao.selectUniqueByProp("areaCode",areaCode);
    }
}
