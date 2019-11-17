package com.kfwy.park.ati.customer.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.entity.CustomerChannelEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by liuzhengyang on 2018/9/21.
 */
public interface ICustomerChannelDbDao extends IMyBatisBaseDao<CustomerChannelEntity,Long> {

    /**
     * @Description 关联查询渠道和渠道单位
     * @Auth luming
     * @Date 2018/10/15 17:11
     * @Version 1.0
     * @Param
     * @return
     */
    List<CustomerChannelEntity> queryChannelCpy(Map<String, Object> map);

    /**
     * @Description 查询单条渠道并关联单位
     * @Auth luming
     * @Date 2018/10/29 18:13
     * @Version 1.0
     * @Param
     * @return
     */
    CustomerChannelEntity selectChannelAndCpy(Map<String, Object> map);
}
