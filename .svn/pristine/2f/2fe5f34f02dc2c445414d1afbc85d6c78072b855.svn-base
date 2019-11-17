package com.kfwy.park.sys.user.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.sys.user.dao.IUserRoleDbDao;
import com.kfwy.park.sys.user.entity.UserRoleEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by davidcun on 2018/5/22.
 */
@Repository
public class UserRoleDbDaoImpl extends AbstractMyBatisDao<UserRoleEntity,Long> implements IUserRoleDbDao {



    @Override
    public int deleteByUserCode(String userCode) {
        return this.getSqlSession().delete(this.mapperNamespace+"deleteByUserCode",userCode);
    }
}
