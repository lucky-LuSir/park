package com.kfwy.park.controller.act.activiti.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.act.activiti.entity.ThrowLeasePayApplyEntity;

/**
 * Created by kfwy_it_004 on 2019/3/22.
 */
public class ThrowLeasePayApplyServiceRequest extends AbstractServiceRequest<ThrowLeasePayApplyEntity> {
    /**
     * 流程key
     */


    /**
     * 审批结果
     */
    private Boolean Verify;

    private String keyword;

    /**
     * 重新提交
     */
    private Boolean reapply;

    public Boolean getVerify() {
        return Verify;
    }

    public void setVerify(Boolean verify) {
        Verify = verify;
    }

    public Boolean getReapply() {
        return reapply;
    }

    public void setReapply(Boolean reapply) {
        this.reapply = reapply;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
