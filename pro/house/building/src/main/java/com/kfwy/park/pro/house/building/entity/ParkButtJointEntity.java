package com.kfwy.park.pro.house.building.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.pro.house.building.enums.ButtJointType;

/**
 * Created by xiongzhan on 2018/6/13.
 */
public class ParkButtJointEntity extends BaseEntity {

    /**
     * 对接人编码
     */
    private String pbjCode;
    /**
     * 园区编号
     */
    private String pkCode;
    /**
     * 园区地址
     */
    private String pkAddress;
    /**
     * 人员编码
     */
    private String empCode;
    /**
     * 对接人姓名
     */
    private String empName;

    /**
     * 对接人电话
     */
    private String empPhone;


    /**
     * 对接人类型（字典）
     * @see ButtJointType
     */
    private String buttJointType;

    /**
     * 对接人类型名字（字典）
     */
    private String buttJointTypeName;

    /**
     *岗位
     */
    private String postName;

    /**
     *责任描述
     */
    private String dutyDescription;




    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
    }

    public String getPkAddress() {
        return pkAddress;
    }

    public void setPkAddress(String pkAddress) {
        this.pkAddress = pkAddress;
    }

    public String getPbjCode() {
        return pbjCode;
    }

    public void setPbjCode(String pbjCode) {
        this.pbjCode = pbjCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getButtJointType() {
        return buttJointType;
    }

    public void setButtJointType(String buttJointType) {
        this.buttJointType = buttJointType;
    }

    public String getButtJointTypeName() {
        return DictionaryStorage.get(ButtJointType.class.getName(),this.getButtJointType()).getName();
    }

    public void setButtJointTypeName(String buttJointTypeName) {
        this.buttJointTypeName = buttJointTypeName;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getDutyDescription() {
        return dutyDescription;
    }

    public void setDutyDescription(String dutyDescription) {
        this.dutyDescription = dutyDescription;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
}
