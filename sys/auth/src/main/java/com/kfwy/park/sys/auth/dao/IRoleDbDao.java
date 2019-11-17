package com.kfwy.park.sys.auth.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.sys.auth.entity.RoleEntity;

import java.util.List;

/**
 * Created by davidcun on 2018/5/22.
 */
public interface IRoleDbDao extends IMyBatisBaseDao<RoleEntity,Long> {
    public List<RoleEntity> selectRoleListByUser(String userCode);
}
