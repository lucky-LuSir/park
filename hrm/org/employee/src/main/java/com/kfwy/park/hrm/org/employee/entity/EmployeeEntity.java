package com.kfwy.park.hrm.org.employee.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.common.dic.SexType;
import com.kfwy.park.hrm.org.employee.dictionary.EducationType;
import com.kfwy.park.hrm.org.employee.dictionary.EmployeeStatus;
import com.kfwy.park.hrm.org.employee.dictionary.HouseholdType;

import java.util.Date;

/**
 * @Description 员工实体信息
 * @Auth davidCun
 * @Date 2018/6/11 21:24
 * @Version 1.0
 * @Param
 * @return
 */
public class EmployeeEntity extends BaseEntity {

    /************基本信息*************/
    /**
     * 人员编号
     */
    private String empCode;

    /**
     * 工号(可登陆账号)
     */
    private String workNumber;

    /**
     * 人员头像
     */
    private String empImg;

    /**
     * 姓名
     */
    private String empName;

    /**
     * 性别(先生/女士)
     * @see com.kfwy.park.common.dic.SexType
     */
    private String sex;
    private String sexName;

    /**
     * 手机号
     */
    private String empPhone;

    /**
     * 身份证
     */
    private String card;

    /**
     * 出生日期
     */
    private Date birthDate;

    /**
     * 户籍所在地
     */
    private String householdLocation;

    /**
     * 户籍类型
     * @see HouseholdType
     */
    private String householdType;
    private String householdTypeName;

    /**
     * 毕业院校
     */
    private String graduate;

    /**
     * 学历
     * @see com.kfwy.park.hrm.org.employee.dictionary.EducationType
     */
    private String education;
    private String educationName;

    /**
     * 婚否
     */
    private Boolean hasMarry;


    /************紧急联系人**************/

    /**
     * 紧急联系人姓名(2018-7-4添加字段)
     */
    private String urgName;

    /**
     * 紧急联系人电话(2018-7-4添加字段)
     */
    private String urgPhone;

    /**
     * 与紧急联系人关系(2018-7-4添加字段)
     */
    private String urgRelation;


    /****************岗位信息****************/
    /**
     * 所属部门编码
     */
    private String deptCode;

    /**
     * 所属部门名称
     */
    private String deptName;

    /**
     * 岗位编码
     */
    private String postCode;

    /**
     * 岗位名称
     */
    private String postName;

    /*************劳务信息***************/
    /**
     * 劳动合同起始日
     */
    private Date contractStartDate;

    /**
     * 劳动合同到期日
     */
    private Date contractEndDate;

    /**
     * 社保地
     */
    private String socialSecurityCity;

    /**
     * 开户银行
     */
    private String openBank;

    /**
     * 银行卡号
     */
    private String bankCard;


    /*---------------------------------------------暂时不用字段---------------------------------------------*/
    /**
     * 管理区域(2018-7-4添加字段) -
     */
    private String manageArea;

    /**
     * 现住址 -
     */
    private String homeAddress;

    /**
     * 员工状态 -
     * @see com.kfwy.park.hrm.org.employee.dictionary.EmployeeStatus
     */
    private String status;
    private String statusName;

    /**
     * 是否生成User
     */
    private Boolean hasUser;

    /**
     * 员工是否在职
     */
    private Boolean empIsWork;

    /*---------------------------------------------get set方法---------------------------------------------*/
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusName() {
        return DictionaryStorage.get(EmployeeStatus.class.getName(),this.getStatus()).getName();
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getHouseholdTypeName() {
        return DictionaryStorage.get(HouseholdType.class.getName(),this.getHouseholdType()).getName();
    }

    public void setHouseholdTypeName(String householdTypeName) {
        this.householdTypeName = householdTypeName;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getEducationName() {
        return DictionaryStorage.get(EducationType.class.getName(),this.getEducation()).getName();
    }

    public String getEmpImg() {
        return empImg;
    }

    public void setEmpImg(String empImg) {
        this.empImg = empImg;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getSexName() {
        return DictionaryStorage.get(SexType.class.getName(),this.getSex()).getName();
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getWorkNumber() {
        return workNumber;
    }

    public void setWorkNumber(String workNumber) {
        this.workNumber = workNumber;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getUrgName() {
        return urgName;
    }

    public void setUrgName(String urgName) {
        this.urgName = urgName;
    }

    public String getUrgPhone() {
        return urgPhone;
    }

    public void setUrgPhone(String urgPhone) {
        this.urgPhone = urgPhone;
    }

    public String getUrgRelation() {
        return urgRelation;
    }

    public void setUrgRelation(String urgRelation) {
        this.urgRelation = urgRelation;
    }

    public String getManageArea() {
        return manageArea;
    }

    public void setManageArea(String manageArea) {
        this.manageArea = manageArea;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Boolean getHasMarry() {
        return hasMarry;
    }

    public void setHasMarry(Boolean hasMarry) {
        this.hasMarry = hasMarry;
    }

    public String getHouseholdType() {
        return householdType;
    }

    public void setHouseholdType(String householdType) {
        this.householdType = householdType;
    }

    public String getHouseholdLocation() {
        return householdLocation;
    }

    public void setHouseholdLocation(String householdLocation) {
        this.householdLocation = householdLocation;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getGraduate() {
        return graduate;
    }

    public void setGraduate(String graduate) {
        this.graduate = graduate;
    }

    public String getOpenBank() {
        return openBank;
    }

    public void setOpenBank(String openBank) {
        this.openBank = openBank;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public String getSocialSecurityCity() {
        return socialSecurityCity;
    }

    public void setSocialSecurityCity(String socialSecurityCity) {
        this.socialSecurityCity = socialSecurityCity;
    }

    public String getDeptCode() {
        return deptCode;
    }

    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

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

    public Boolean getHasUser() {
        return hasUser;
    }

    public void setHasUser(Boolean hasUser) {
        this.hasUser = hasUser;
    }

    public Boolean getEmpIsWork() {
        return empIsWork;
    }

    public void setEmpIsWork(Boolean empIsWork) {
        this.empIsWork = empIsWork;
    }
}
