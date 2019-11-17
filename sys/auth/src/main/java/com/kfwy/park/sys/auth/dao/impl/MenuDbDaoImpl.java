package com.kfwy.park.sys.auth.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.sys.auth.dao.IMenuDbDao;
import com.kfwy.park.sys.auth.entity.MenuEntity;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description TODO
 * @auth davidCun
 * @date 2018/6/29 11:53
 * @version 1.0
 * @return
 */
@Repository
public class MenuDbDaoImpl extends AbstractMyBatisDao<MenuEntity,Long> implements IMenuDbDao {

    public static String selectUserMenuByRoleRelation = "selectUserMenuByRoleRelation";

    public static String selectUserMenuByPostRelation = "selectUserMenuByPostRelation";

    public static String selectUserMenuByEstaff = "selectUserMenuByEstaff";

    public static String selectMenuListByPost = "selectMenuListByPost";

    public static String selectMenuListByRole = "selectMenuListByRole";
    @Override
    public List<MenuEntity> selectUserMenuByRoleRelation(String userCode) {

        Map<String,Object> param = new HashMap<String,Object>();

        param.put("userCode",userCode);
        param.put("isDeleted",Boolean.FALSE);

        return this.selectByStatement(selectUserMenuByRoleRelation,param);
    }



    @Override
    public List<MenuEntity> selectUserMenuByPostRelation(String userCode) {

        Map<String,Object> param = new HashMap<String,Object>();

        param.put("userCode",userCode);
        param.put("isDeleted",Boolean.FALSE);

        return this.selectByStatement(selectUserMenuByPostRelation,param);
    }



    @Override
    public List<MenuEntity> selectMenuListByPost(String postCode) {
        Map<String,Object> param = new HashMap<String,Object>();

        param.put("postCode",postCode);
        param.put("isDeleted",Boolean.FALSE);

        return this.selectByStatement(selectMenuListByPost,param);
    }

    @Override
    public List<MenuEntity> selectMenuListByRole(String roleCode) {
        Map<String,Object> param = new HashMap<String,Object>();

        param.put("roleCode",roleCode);
        param.put("isDeleted",Boolean.FALSE);

        return this.selectByStatement(selectMenuListByRole,param);
    }
}
