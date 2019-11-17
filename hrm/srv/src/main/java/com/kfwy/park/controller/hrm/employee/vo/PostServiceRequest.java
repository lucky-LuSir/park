package com.kfwy.park.controller.hrm.employee.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.hrm.org.employee.entity.PostEntity;

import java.util.List;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/12 22:54
 * @Version 1.0
 */
public class PostServiceRequest extends AbstractServiceRequest<PostDto> {

    private List<String> menuCodes;

    /**
     * 用户编码
     */
    private String userCode;

    /*---------------------------------------------------------------------------------*/

    public List<String> getMenuCodes() {
        return menuCodes;
    }

    public void setMenuCodes(List<String> menuCodes) {
        this.menuCodes = menuCodes;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }
}
