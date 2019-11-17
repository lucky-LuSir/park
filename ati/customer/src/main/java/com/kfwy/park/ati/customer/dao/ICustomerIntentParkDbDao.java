package com.kfwy.park.ati.customer.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.entity.CustomerFollowupEntity;
import com.kfwy.park.ati.customer.entity.CustomerIntentParkEntity;

import java.util.List;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/6/5 16:27
 */

public interface ICustomerIntentParkDbDao extends IMyBatisBaseDao<CustomerIntentParkEntity,Long> {

    /**
     * 根据followupCode物理删除
     * @param followupCode
     * @return
     */
    int deletedByFollowupCode(String followupCode);

}
