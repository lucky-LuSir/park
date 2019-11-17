package com.kfwy.park.hrm.org.employee.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.hrm.org.employee.business.IPostMenuManager;
import com.kfwy.park.hrm.org.employee.dao.IPostMenuDbDao;
import com.kfwy.park.hrm.org.employee.entity.PostMenuEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/6/29 15:43
 */
@Service
public class PostMenuManagerImpl extends AbstractManager<PostMenuEntity> implements IPostMenuManager {

    @Autowired
    private IPostMenuDbDao postMenuDbDao;


    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.postMenuDbDao;
    }
}
