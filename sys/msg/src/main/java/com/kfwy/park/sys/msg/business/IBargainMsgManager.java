package com.kfwy.park.sys.msg.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.sys.msg.entity.BargainMsgEntity;

/**
 * Created by luming on 2019/6/21.
 */
public interface IBargainMsgManager extends IManager<BargainMsgEntity> {

    /**
     * @Description 校验必填字段
     * @author luming
     * @Date 2019/6/21 15:51
     * @Version 1.0
     * @Param [bargainMsgEntity]
     * @return void
     */
    void checkParams(BargainMsgEntity bargainMsgEntity) throws IllegalAccessException;
}
