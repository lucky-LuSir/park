package com.kfwy.park.sys.auth.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.park.sys.auth.entity.MenuEntity;

import java.util.List;

/**
 * Created by davidcun on 2018/5/22.
 * 用户菜单权限管理
 */
public interface IMenuManager extends IManager<MenuEntity> {

    /**
     * 通过用户编码查询用户的权限，通过角色及岗位关联关系
     * @param userCode
     * @return
     */
    public List<MenuEntity> findUserMenuToTreeByUserCode(String userCode);

    /**
    * @Description 描述:通过岗位编码查询用户的权限，通过岗位关联权限关系
    * @Auth xpp
    * @Date 2018/7/12 11:21
    * @Version 1.0
    * @Param [postCode]
    * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
    **/
    public List<MenuEntity> findUserMenuToListByPostRelation(String postCode);




    /**
     * @Description 描述:把List<MenuEntity>转成(树)
     * @Auth xpp
     * @Date 2018/7/25 16:01
     * @Version 1.0
     * @Param [menuList]
     * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
     **/
    public List<MenuEntity> menuListToTree(List<MenuEntity> menuList);

    /**
     * 返回所有菜单树形结构
     * @return
     */
    public List<MenuEntity> findAllMenuTree();

    /**
    * @Description 描述:岗位分配页调用,仅仅包含岗位和菜单的关系
    * @Auth xpp
    * @Date 2018/7/27 17:25
    * @Version 1.0
    * @Param [postCode]
    * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
    **/
    public List<MenuEntity> findMenuListByPost(String postCode);

    /**
    * @Description 描述:根据角色查菜单列表(目前不用转化为树)
    * @Auth xpp
    * @Date 2018/8/16 14:48
    * @Version 1.0
    * @Param [roleCode]
    * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
    **/
    public List<MenuEntity> findMenuListByRole(String roleCode);

    /**
    * @Description 描述:根据userCode获取用户下所有角色对应所有Menu
    * @Auth xpp
    * @Date 2018/8/20 14:17
    * @Version 1.0
    * @Param [upostCode]
    * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
    **/
    public List<MenuEntity> findUserMenuToListByRoleRelation(String userCode);

    /**
     * @Description 描述:获取用户所有菜单(包含岗位,角色中的菜单)
     * @Auth xpp
     * @Date 2018/8/31 9:43
     * @Version 1.0
     * @Param [userCode]
     * @return java.util.List<com.kfwy.park.sys.auth.entity.MenuEntity>
     **/
    public List<MenuEntity> getMenuResult (String userCode,String empCode);
}
