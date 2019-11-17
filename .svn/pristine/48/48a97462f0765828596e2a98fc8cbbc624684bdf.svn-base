package com.kfwy.park.act.activiti.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.park.act.activiti.emums.ApprovalStatus;
import com.kfwy.park.common.entity.TaskBaseEntity;

import java.util.Date;

/**
 * Created by kfwy_it_004 on 2019/3/21.
 */
public class ThrowLeasePayApplyEntity extends TaskBaseEntity {
    /**
     * 申请编号
     */
    private String ApplyCode;

    /**
     * 退租申请编码
     */
    private String throwLeaseApplyCode;

    /*
    合同编号
     */
    private String ctName;

    /*
    合同编号
     */
    private String ctCode;

    /**
     * 流程定义Id
     */
    private String pdId;


    /**
     * 汇款金额
     */
    private String payMoney;

    /**
     * 约定汇款日期
     */
    private Date appointPayTime;

    /**
     * 是否签订合同或协议 1是 0否
     */
    private String isContract;

    /**
     * 是否有发票 1是 0否
     */
    private String isInvoice;

    /**
     * 到票日期
     */
    private Date invoiceInTime;

    /**
     * 付款方
     */
    private String payCompany;

    /**
     * 收款方
     */
    private String  payee;

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
     * 申请事由
     */
    private String paymentApplyRemark;

    /**
     * 附件（图片）
     */
    private String outPicture;

    /**
     * 驳回原因
     */
    private String rejectReason;

    public String getApplyCode() {
        return ApplyCode;
    }

    public void setApplyCode(String applyCode) {
        ApplyCode = applyCode;
    }

    public String getPdId() {
        return pdId;
    }

    public void setPdId(String pdId) {
        this.pdId = pdId;
    }

    public String getPayMoney() {
        return payMoney;
    }

    public void setPayMoney(String payMoney) {
        this.payMoney = payMoney;
    }

    public String getIsContract() {
        return isContract;
    }

    public void setIsContract(String isContract) {
        this.isContract = isContract;
    }

    public String getIsInvoice() {
        return isInvoice;
    }

    public void setIsInvoice(String isInvoice) {
        this.isInvoice = isInvoice;
    }

    public String getPayCompany() {
        return payCompany;
    }

    public void setPayCompany(String payCompany) {
        this.payCompany = payCompany;
    }

    public String getPayee() {
        return payee;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPaymentApplyRemark() {
        return paymentApplyRemark;
    }

    public void setPaymentApplyRemark(String paymentApplyRemark) {
        this.paymentApplyRemark = paymentApplyRemark;
    }

    public Date getAppointPayTime() {
        return appointPayTime;
    }

    public void setAppointPayTime(Date appointPayTime) {
        this.appointPayTime = appointPayTime;
    }

    public Date getInvoiceInTime() {
        return invoiceInTime;
    }

    public void setInvoiceInTime(Date invoiceInTime) {
        this.invoiceInTime = invoiceInTime;
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

    public String getOutPicture() {
        return outPicture;
    }

    public void setOutPicture(String outPicture) {
        this.outPicture = outPicture;
    }

    public String getRejectReason() {
        return rejectReason;
    }

    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }

    public String getThrowLeaseApplyCode() {
        return throwLeaseApplyCode;
    }

    public void setThrowLeaseApplyCode(String throwLeaseApplyCode) {
        this.throwLeaseApplyCode = throwLeaseApplyCode;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }
}
