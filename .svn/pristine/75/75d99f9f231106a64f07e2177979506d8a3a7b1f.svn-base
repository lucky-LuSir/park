package com.kfwy.park.ati.customer.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.ati.customer.entity.CustomerChannelEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by liuzhengyang on 2018/9/21.
 */
public interface ICustomerChannelManager extends IManager<CustomerChannelEntity> {
    /**
     * @return
     * @Description 关联查询渠道和渠道单位
     * @Auth luming
     * @Date 2018/10/15 17:11
     * @Version 1.0
     * @Param
     */
    List<CustomerChannelEntity> queryChannelCpy(Map<String, Object> map);

    /**
     * @Description 查询单条渠道并关联单位
     * @Auth luming
     * @Date 2018/10/29 18:16
     * @Version 1.0
     * @Param
     * @return
     */
    CustomerChannelEntity selectChannelAndCpy(Map<String, Object> map);
}
