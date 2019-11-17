package com.kfwy.park.act.activiti.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.park.act.activiti.emums.ApprovalStatus;
import com.kfwy.park.act.activiti.emums.DutyType;
import com.kfwy.park.act.activiti.emums.MoneyType;
import com.kfwy.park.act.activiti.emums.ThrowLeaseType;
import com.kfwy.park.common.entity.TaskBaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by luming on 2019/3/11.
 * 退租单申请工作流实体
 */
public class ThrowLeaseApplyEntity extends TaskBaseEntity {
    /**
     * 流程申请编号
     */
    private String applyCode;

    /**
     * 流程定义Id
     */
    private String pdId;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 合同编码
     */
    private String ctCode;

    /**
     * 合同编号
     */
    private String ctName;

    /**
     * 审批状态
     * @see ApprovalStatus
     */
    private String approvalStatus;

    /**
     * 审批状态名称
     */
    private String approvalStatusName;

    /**
     * 退租类型
     * @see ThrowLeaseType
     */
    private String throwLeaseType;

    /**
     * 退租状态名字
     */
    private String throwLeaseTypeName;

    /**
     * 退租责任类型
     * @see DutyType
     */
    private String dutyType;

    /**
     * 退租责任类型名称
     */
    private String dutyTypeName;

    /**
     * 金额类型：收入或支出
     * @see MoneyType
     */
    private String moneyType;

    /**
     * 金额类型名称
     */
    private String moneyTypeName;

    /**
     * 金额
     */
    private BigDecimal money;

    /**
     * 甲方
     */
    private String partyA;

    /**
     * 甲方地址
     */
    private String partyAAddress;

    /**
     * 甲方法定代表
     */
    private String partyALegalRepresentative;

    /**
     * 甲方电话
     */
    private String partyAPhone;

    /**
     * 乙方
     */
    private String partyB;

    /**
     * 乙方地址
     */
    private String partyBAddress;

    /**
     * 乙方法定代表
     */
    private String partyBLegalRepresentative;

    /**
     * 乙方电话
     */
    private String partyBPhone;

    /**
     * 退租日期
     */
    private Date throwLeaseDate;

    /**
     * 租赁区域
     */
    private String leasedArea;

    /**
     * 退租面积
     */
    private BigDecimal throwLeaseArea;

    /**
     * 月租金
     */
    private BigDecimal monthlyRent;

    /**
     * 退款银行
     */
    private String refundBank;

    /**
     * 退款账号姓名
     */
    private String refundAccountName;

    /**
     * 退款银行账号
     */
    private String refundAccountNumber;

    /**
     * 退租备注说明
     */
    private String throwLeaseRemark;


    /*-------------------------------- 封装合同信息 --------------------------------*/
    /**
     * 合同开始时间
     */
    private Date contractStartTime;

    /**
     * 每月租金
     */
    public BigDecimal rentTotal;

    /*-------------------------------- get set 方法 --------------------------------*/

    public String getApplyCode() {
        return applyCode;
    }

    public void setApplyCode(String applyCode) {
        this.applyCode = applyCode;
    }

    public String getPdId() {
        return pdId;
    }

    public void setPdId(String pdId) {
        this.pdId = pdId;
    }

    @Override
    public String getProcessInstanceId() {
        return processInstanceId;
    }

    @Override
    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getApprovalStatusName() {
        return DictionaryStorage.get(ApprovalStatus.class.getName(), this.getApprovalStatus()).getName();
    }

    public void setApprovalStatusName(String approvalStatusName) {
        this.approvalStatusName = approvalStatusName;
    }

    public String getThrowLeaseType() {
        return throwLeaseType;
    }

    public void setThrowLeaseType(String throwLeaseType) {
        this.throwLeaseType = throwLeaseType;
    }

    public String getThrowLeaseTypeName() {
        return DictionaryStorage.get(ThrowLeaseType.class.getName(), this.getThrowLeaseType()).getName();
    }

    public void setThrowLeaseTypeName(String throwLeaseTypeName) {
        this.throwLeaseTypeName = throwLeaseTypeName;
    }

    public String getDutyType() {
        return dutyType;
    }

    public void setDutyType(String dutyType) {
        this.dutyType = dutyType;
    }

    public String getDutyTypeName() {
        return DictionaryStorage.get(DutyType.class.getName(), this.getDutyType()).getName();
    }

    public void setDutyTypeName(String dutyTypeName) {
        this.dutyTypeName = dutyTypeName;
    }

    public String getMoneyType() {
        return moneyType;
    }

    public void setMoneyType(String moneyType) {
        this.moneyType = moneyType;
    }

    public String getMoneyTypeName() {
        return DictionaryStorage.get(MoneyType.class.getName(), this.getMoneyType()).getName();
    }

    public void setMoneyTypeName(String moneyTypeName) {
        this.moneyTypeName = moneyTypeName;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getPartyA() {
        return partyA;
    }

    public void setPartyA(String partyA) {
        this.partyA = partyA;
    }

    public String getPartyAAddress() {
        return partyAAddress;
    }

    public void setPartyAAddress(String partyAAddress) {
        this.partyAAddress = partyAAddress;
    }

    public String getPartyALegalRepresentative() {
        return partyALegalRepresentative;
    }

    public void setPartyALegalRepresentative(String partyALegalRepresentative) {
        this.partyALegalRepresentative = partyALegalRepresentative;
    }

    public String getPartyAPhone() {
        return partyAPhone;
    }

    public void setPartyAPhone(String partyAPhone) {
        this.partyAPhone = partyAPhone;
    }

    public String getPartyB() {
        return partyB;
    }

    public void setPartyB(String partyB) {
        this.partyB = partyB;
    }

    public String getPartyBAddress() {
        return partyBAddress;
    }

    public void setPartyBAddress(String partyBAddress) {
        this.partyBAddress = partyBAddress;
    }

    public String getPartyBLegalRepresentative() {
        return partyBLegalRepresentative;
    }

    public void setPartyBLegalRepresentative(String partyBLegalRepresentative) {
        this.partyBLegalRepresentative = partyBLegalRepresentative;
    }

    public String getPartyBPhone() {
        return partyBPhone;
    }

    public void setPartyBPhone(String partyBPhone) {
        this.partyBPhone = partyBPhone;
    }

    public Date getThrowLeaseDate() {
        return throwLeaseDate;
    }

    public void setThrowLeaseDate(Date throwLeaseDate) {
        this.throwLeaseDate = throwLeaseDate;
    }

    public String getLeasedArea() {
        return leasedArea;
    }

    public void setLeasedArea(String leasedArea) {
        this.leasedArea = leasedArea;
    }

    public BigDecimal getThrowLeaseArea() {
        return throwLeaseArea;
    }

    public void setThrowLeaseArea(BigDecimal throwLeaseArea) {
        this.throwLeaseArea = throwLeaseArea;
    }

    public BigDecimal getMonthlyRent() {
        return monthlyRent;
    }

    public void setMonthlyRent(BigDecimal monthlyRent) {
        this.monthlyRent = monthlyRent;
    }

    public String getRefundBank() {
        return refundBank;
    }

    public void setRefundBank(String refundBank) {
        this.refundBank = refundBank;
    }

    public String getRefundAccountName() {
        return refundAccountName;
    }

    public void setRefundAccountName(String refundAccountName) {
        this.refundAccountName = refundAccountName;
    }

    public String getRefundAccountNumber() {
        return refundAccountNumber;
    }

    public void setRefundAccountNumber(String refundAccountNumber) {
        this.refundAccountNumber = refundAccountNumber;
    }

    public String getThrowLeaseRemark() {
        return throwLeaseRemark;
    }

    public void setThrowLeaseRemark(String throwLeaseRemark) {
        this.throwLeaseRemark = throwLeaseRemark;
    }

    public Date getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public BigDecimal getRentTotal() {
        return rentTotal;
    }

    public void setRentTotal(BigDecimal rentTotal) {
        this.rentTotal = rentTotal;
    }
}
