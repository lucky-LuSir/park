package com.kfwy.park.sys.auth.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.sys.auth.dao.IRoleMenuDbDao;
import com.kfwy.park.sys.auth.entity.RoleMenuEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by davidcun on 2018/5/22.
 */
@Repository
public class RoleMenuDbDaoImpl extends AbstractMyBatisDao<RoleMenuEntity,Long> implements IRoleMenuDbDao {

    @Override
    public int deleteByRoleCode(String roleCode) {
        return this.getSqlSession().delete(this.mapperNamespace+"deleteByRoleCode",roleCode);
    }
}
