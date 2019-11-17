package com.kfwy.park.ati.customer.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.customer.dao.ICustomerChannelDbDao;
import com.kfwy.park.ati.customer.entity.CustomerChannelEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by liuzhengyang on 2018/9/21.
 */
@Repository
public class CustomerChannelDbDaoImpl extends AbstractMyBatisDao<CustomerChannelEntity,Long> implements ICustomerChannelDbDao {

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
        return this.getSqlSession().selectList(this.mapperNamespace + "selectChannelCpy",map);
    }

    /**
     * @Description 查询单条渠道并关联单位
     * @Auth luming
     * @Date 2018/10/29 18:13
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public CustomerChannelEntity selectChannelAndCpy(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectChannelAndCpy", map);
    }
}
