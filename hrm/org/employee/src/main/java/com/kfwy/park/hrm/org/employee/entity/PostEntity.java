package com.kfwy.park.hrm.org.employee.entity;

import com.gniuu.framework.entity.BaseEntity;


/**
 * @Description 岗位实体信息
 * @Auth davidCun
 * @Date 2018/6/12 14:33
 * @Version 1.0
 * @Param
 * @return
 */
public class PostEntity extends BaseEntity {

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /*---------------------------------------------get set方法---------------------------------------------*/
    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }
}
