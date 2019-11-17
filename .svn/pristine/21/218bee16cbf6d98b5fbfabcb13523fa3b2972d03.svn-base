package com.kfwy.park.sys.cpy.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.common.dic.SexType;

/**
 * @description 公司实体
 * @auth davidCun
 * @date 2018/6/30 14:05
 * @version 1.0
 * @return
 */
public class CompanyEntity extends BaseEntity {

    /**
     * 公司代号
     */
    private String cpyMark;

    /**
     * 公司图标
     */
    private String cpyImg;

    /**
     * 企业管理员code
     */
    private String userCode;

    /**
     * 管理员名字
     */
    private String userName;

    /**
     * 性别
     * @see SexType
     */
    private String sex;

    /**
     * 性别名称
     */
    private String sexName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 公司地址
     */
    private String address;

    /*------------------------------------------- 字段分割线 -------------------------------------------*/

    public String getCpyImg() {
        return cpyImg;
    }

    public void setCpyImg(String cpyImg) {
        this.cpyImg = cpyImg;
    }

    public String getCpyMark() {
        return cpyMark;
    }

    public void setCpyMark(String cpyMark) {
        this.cpyMark = cpyMark;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSexName() {
        return DictionaryStorage.get(SexType.class.getName(), this.getSex()).getName();
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
