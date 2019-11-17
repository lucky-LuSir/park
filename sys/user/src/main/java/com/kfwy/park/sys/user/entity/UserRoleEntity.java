package com.kfwy.park.sys.user.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * Created by davidcun on 2018/5/19.
 */
public class UserRoleEntity extends BaseEntity {

    /**
     * 用户编码
     */
    private String userCode;
    /**
     * 角色编码
     */
    private String roleCode;

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
