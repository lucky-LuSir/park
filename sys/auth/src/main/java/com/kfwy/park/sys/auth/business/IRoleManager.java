package com.kfwy.park.sys.auth.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.sys.auth.entity.RoleEntity;

import java.util.List;

/**
 * Created by davidcun on 2018/5/22.
 */
public interface IRoleManager extends IManager<RoleEntity> {

    /**
    * @Description 描述:更新菜单，有可能是跟新菜单权限
    * @Auth xpp
    * @Date 2018/8/16 15:34
    * @Version 1.0
    * @Param [post, menus]
    * @return int
    **/
    int update(RoleEntity post,List<String> menus);

    /**
    * @Description 描述:根据userCode查询出对应的角色role
    * @Auth xpp
    * @Date 2018/8/18 14:04
    * @Version 1.0
    * @Param [userCode]
    * @return java.util.List<com.kfwy.park.sys.auth.entity.RoleEntity>
    **/
    public List<RoleEntity> findRoleListByUser(String userCode);
}
