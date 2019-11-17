package com.kfwy.park.sys.auth.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.sys.auth.dao.IRoleDbDao;
import com.kfwy.park.sys.auth.entity.RoleEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 */
@Repository
public class RoleDbDaoImpl extends AbstractMyBatisDao<RoleEntity,Long> implements IRoleDbDao {

    public static String selectRoleListByUser = "selectRoleListByUser";

    @Override
    public List<RoleEntity> selectRoleListByUser(String userCode) {
        Map<String,Object> param = new HashMap<String,Object>();

        param.put("userCode",userCode);
        param.put("isDeleted",Boolean.FALSE);

        return this.selectByStatement(selectRoleListByUser,param);
    }
}
