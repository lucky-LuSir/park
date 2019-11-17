package com.kfwy.park.controller.sys.auth.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.sys.auth.entity.MenuEntity;
import com.kfwy.park.sys.auth.entity.RoleEntity;

import java.util.List;

/**
 * Created by davidcun on 2018/5/24.
 */
public class RoleServiceRequest extends AbstractServiceRequest<RoleEntity> {

    private List<String> menuCodes;


    public List<String> getMenuCodes() {
        return menuCodes;
    }

    public void setMenuCodes(List<String> menuCodes) {
        this.menuCodes = menuCodes;
    }

}
