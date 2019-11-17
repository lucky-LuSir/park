package com.kfwy.park.ati.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.customer.dao.ICustomerIntentParkDbDao;
import com.kfwy.park.ati.customer.entity.CustomerFollowupEntity;
import com.kfwy.park.ati.customer.entity.CustomerIntentParkEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/6/5 16:29
 */
@Repository
public class CustomerIntentParkDbDaoImpl extends AbstractMyBatisDao<CustomerIntentParkEntity,Long> implements ICustomerIntentParkDbDao {

    /**
     * 根据followupCode物理删除
     * @param followupCode
     * @return
     */
    @Override
    public int deletedByFollowupCode(String followupCode) {
        return this.getSqlSession().delete(this.mapperNamespace + "deletedByFollowupCode", followupCode);
    }

}
