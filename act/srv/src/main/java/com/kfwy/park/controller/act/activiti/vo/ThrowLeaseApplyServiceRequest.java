package com.kfwy.park.controller.act.activiti.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.act.activiti.entity.ThrowLeaseApplyEntity;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;

/**
 * Created by luming on 2019/3/13.
 * 退租申请工作流
 */
public class ThrowLeaseApplyServiceRequest extends AbstractServiceRequest<ThrowLeaseApplyEntity> {

//    /**
//     * 园区一审
//     */
//    private Boolean firstVerify;
//
//    /**
//     * 财务二审
//     */
//    private Boolean secondVerify;

    /**
     * 审批节点
     */
    private Boolean verify;

    /**
     * 重新提交
     */
    private Boolean reapply;

    /**
     * 审批原因
     */
    private String approveReason;

    private String keyword;

    /*-------------------------------------- get set 方法 --------------------------------------*/

//    public Boolean getFirstVerify() {
//        return firstVerify;
//    }
//
//    public void setFirstVerify(Boolean firstVerify) {
//        this.firstVerify = firstVerify;
//    }
//
//    public Boolean getSecondVerify() {
//        return secondVerify;
//    }
//
//    public void setSecondVerify(Boolean secondVerify) {
//        this.secondVerify = secondVerify;
//    }


    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getVerify() {
        return verify;
    }

    public void setVerify(Boolean verify) {
        this.verify = verify;
    }

    public Boolean getReapply() {
        return reapply;
    }

    public void setReapply(Boolean reapply) {
        this.reapply = reapply;
    }

    public String getApproveReason() {
        return approveReason;
    }

    public void setApproveReason(String approveReason) {
        this.approveReason = approveReason;
    }
}
