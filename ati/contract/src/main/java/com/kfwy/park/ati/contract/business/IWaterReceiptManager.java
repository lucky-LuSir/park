package com.kfwy.park.ati.contract.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.ati.contract.entity.WaterReceiptEntity;

/**
 * Created by xiongzhan on 2018/6/6.
 */
public interface IWaterReceiptManager  extends IManager<WaterReceiptEntity> {

    /**
     * @Description 水费缴费
     * @Auth luming
     * @Date 2018/9/10 17:46
     * @Version 1.0
     * @Param
     * @return
     */
    void pay(WaterReceiptEntity entity);
}
