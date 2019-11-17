package com.kfwy.park.controller.sys.user.vo;

import com.kfwy.park.sys.auth.entity.MenuEntity;
import com.kfwy.park.sys.auth.entity.RoleEntity;
import com.kfwy.park.sys.user.entity.UserEntity;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/7/4 10:15
 */
public class UserDto extends UserEntity {

    private List<RoleEntity> roles;

    public UserDto(){}

    public UserDto(UserEntity user){
        BeanUtils.copyProperties(user,this);
    }


    public List<RoleEntity> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleEntity> roles) {
        this.roles = roles;
    }
}
