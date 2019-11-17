package com.kfwy.park.pro.car.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.pro.car.entity.CarEntity;

/**
 * Created by gengtao on 2019/6/11.
 */
public interface ICarManager extends IManager<CarEntity> {
    /**
     *@Author gtaotao
     *@Date 2019-07-16 16:50:43
     *@Description 更新全部字段
     *@Return
     **/
    void updataAll(CarEntity entity);

    /**
     *@Author gtaotao
     *@Date 2019-07-16 16:50:58
     *@Description 校验必填字段
     *@Return
     **/
    void checkParam(CarEntity entity) throws IllegalAccessException;
}
