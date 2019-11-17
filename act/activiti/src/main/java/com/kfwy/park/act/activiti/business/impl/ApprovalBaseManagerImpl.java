package com.kfwy.park.act.activiti.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.act.activiti.business.IApprovalBaseManager;
import com.kfwy.park.act.activiti.dao.IApprovalBaseDbDao;
import com.kfwy.park.act.activiti.entity.ApprovalBaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Created by luming on 2019/3/22.
 * 审批表
 */
@Service
public class ApprovalBaseManagerImpl extends AbstractManager<ApprovalBaseEntity> implements IApprovalBaseManager {
    @Autowired
    private IApprovalBaseDbDao approvalBaseDbDao;

    @Override
    protected IMyBatisBaseDao<ApprovalBaseEntity, Long> getMyBatisDao() {
        return approvalBaseDbDao;
    }

}
