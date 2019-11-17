package com.kfwy.park.ati.contract.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;

/**
 * @Description 租金、电费、水费、停车费，支付表
 * @Auth luming
 * @Date 2018/9/19 18:43
 * @Version 1.0
 * @Param
 * @return
 */
public interface IPayManager extends IManager<RentReceiptPayEntity> {

    int deleteById(Long id);

}
