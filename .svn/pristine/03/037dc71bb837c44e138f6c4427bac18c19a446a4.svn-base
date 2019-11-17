package com.kfwy.park.sys.auth.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.sys.auth.entity.MenuEntity;

import java.util.List;

/**
 * @description TODO
 * @auth davidCun
 * @date 2018/6/29 10:55
 * @version 1.0
 * @return
 */
public interface IMenuDbDao extends IMyBatisBaseDao<MenuEntity,Long>{


    /**
     * 根据角色关系查询用户菜单
     * @param userCode
     * @return
     */
    public List<MenuEntity> selectUserMenuByRoleRelation(String userCode);

    /**
     * 根据岗位关联关系查询用户菜单
     * @param userCode
     * @return
     */
    public List<MenuEntity> selectUserMenuByPostRelation(String userCode);



    /**
    * @Description 描述:根据岗位编码,查询对应菜单列表
    * @Auth xpp
    * @Date 2018/7/27 17:32
    * @Version 1.0
    * @Param [postCode]
    * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
    **/
    public List<MenuEntity> selectMenuListByPost(String postCode);

    /**
    * @Description 描述:根据角色编码,查询对应菜单列表
    * @Auth xpp
    * @Date 2018/8/16 14:48
    * @Version 1.0
    * @Param [roleCode]
    * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
    **/
    public List<MenuEntity> selectMenuListByRole(String roleCode);
}
