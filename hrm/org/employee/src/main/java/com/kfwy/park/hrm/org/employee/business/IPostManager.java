package com.kfwy.park.hrm.org.employee.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.hrm.org.employee.entity.PostEntity;

import java.util.List;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/12 22:51
 * @Version 1.0
 */
public interface IPostManager extends IManager<PostEntity> {


    /**
     * 创建岗位的同时分配菜单权限
     * @param postEntity
     * @param menus
     * @return
     */
    int create(PostEntity postEntity, List<String> menus);


    /**
     * 更新菜单，有可能是跟新菜单权限
     * @param post
     * @param menus
     * @return
     */
    int update(PostEntity post,List<String> menus);
}
