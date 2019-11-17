package com.kfwy.park.ati.customer.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.business.ICustomerChannelManager;
import com.kfwy.park.ati.customer.dao.ICustomerChannelDbDao;
import com.kfwy.park.ati.customer.entity.CustomerChannelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by liuzhengyang on 2018/9/21.
 */
@Service
public class CustomerChannelManagerImpl extends AbstractManager<CustomerChannelEntity> implements ICustomerChannelManager {

    @Autowired
    private ICustomerChannelDbDao customerChannelDbDao;

    @Override
    protected IMyBatisBaseDao<CustomerChannelEntity, Long> getMyBatisDao() {
        return customerChannelDbDao;
    }

    /**
     * @Description 关联查询渠道和渠道单位
     * @Auth luming
     * @Date 2018/10/15 17:11
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<CustomerChannelEntity> queryChannelCpy(Map<String, Object> map) {
        return customerChannelDbDao.queryChannelCpy(map);
    }

    /**
     * @Description 查询单条渠道并关联单位
     * @Auth luming
     * @Date 2018/10/29 18:17
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public CustomerChannelEntity selectChannelAndCpy(Map<String, Object> map) {
        return customerChannelDbDao.selectChannelAndCpy(map);
    }
}
