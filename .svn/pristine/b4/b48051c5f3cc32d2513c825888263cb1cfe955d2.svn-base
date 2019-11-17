package com.kfwy.park.controller.sys.auth.vo;

import com.kfwy.park.sys.auth.entity.MenuEntity;
import com.kfwy.park.sys.auth.entity.RoleEntity;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
* @Description 描述:包装类,继承roleEntity,增加List<MenuEntity>
* @Auth xpp
* @Date 2018/8/16 13:55
* @Version 1.0
* @Param 
* @return 
**/
public class RoleDto extends RoleEntity {

    private List<MenuEntity> menus;

    public RoleDto(){}

    public RoleDto(RoleEntity role){
        BeanUtils.copyProperties(role,this);
    }


    public List<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuEntity> menus) {
        this.menus = menus;
    }


}
