package com.kfwy.park.sys.auth.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.RestBusinessException;
import com.kfwy.park.sys.auth.business.IRoleManager;
import com.kfwy.park.sys.auth.dao.IRoleDbDao;
import com.kfwy.park.sys.auth.dao.IRoleMenuDbDao;
import com.kfwy.park.sys.auth.entity.MenuEntity;
import com.kfwy.park.sys.auth.entity.RoleEntity;
import com.kfwy.park.sys.auth.entity.RoleMenuEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by davidcun on 2018/5/22.
 */
@Service
public class RoleManagerImpl extends AbstractManager<RoleEntity> implements IRoleManager {

    @Autowired
    private IRoleDbDao roleDbDao;

    @Autowired
    private IRoleMenuDbDao roleMenuDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return roleDbDao;
    }

    @Override
    protected void beforeCreate(RoleEntity roleEntity) {
        roleEntity.setRoleCode(CodeGenUtils.generate());
    }

    @Override
    protected void beforeUpdate(RoleEntity roleEntity) {

        if(StringUtils.isEmpty(roleEntity.getRoleCode())
                && roleEntity.getId()==null){
            throw new RestBusinessException("更新角色，ID或者角色编码不能为空");
        }

        
    }

    @Transactional
    public int update(RoleEntity role,List<String> menus){

        if (menus!=null && menus.size()>0){
            roleMenuDbDao.deleteByRoleCode(role.getRoleCode());
        }

        int i = roleDbDao.update(role);

        createRoleMenu(role.getRoleCode(),menus);

        return i;
    }



    private void createRoleMenu(String roleCode,List<String> menus){
        List<RoleMenuEntity> list = new ArrayList<RoleMenuEntity>();

        for (String menu : menus){
            RoleMenuEntity pme = new RoleMenuEntity();
            pme.setRoleCode(roleCode);
            pme.setMenuCode(menu);
            //pme.setCpyCode(CurrentContext.getCpyCode());

            list.add(pme);
        }

        roleMenuDbDao.batchInsert(list);
    }



    @Override
    public List<RoleEntity> findRoleListByUser(String userCode) {
        if (StringUtils.isEmpty(userCode)){
            throw new RestBusinessException("查询角色列表，用户编码userCode不能为空");
        }

        List<RoleEntity> roles = roleDbDao.selectRoleListByUser(userCode);

        return roles;
    }


}
