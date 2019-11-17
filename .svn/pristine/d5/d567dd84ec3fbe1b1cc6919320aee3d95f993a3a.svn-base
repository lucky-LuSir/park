package com.kfwy.park.controller.hrm.employee.vo;

import com.kfwy.park.hrm.org.employee.entity.PostEntity;
import com.kfwy.park.sys.auth.entity.MenuEntity;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/7/4 10:15
 */
public class PostDto extends PostEntity {

    private List<MenuEntity> menus;

    public PostDto(){}

    public PostDto(PostEntity post){
        BeanUtils.copyProperties(post,this);
    }


    public List<MenuEntity> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuEntity> menus) {
        this.menus = menus;
    }


}
