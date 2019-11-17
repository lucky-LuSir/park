package com.kfwy.park.bi.report.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.bi.report.entity.FirstReceivableBiEntity;

import java.util.List;

/**
 * @Description 首笔款报表
 * @Auth luming
 * @Date 2019/1/8 19:18
 * @Version 1.0
 * @Param
 * @return
 */
public interface IFirstReceivableBiManager extends IManager<FirstReceivableBiEntity> {

    /**
     * @Description 查询首笔款报表
     * @Auth luming
     * @Date 2019/1/9 15:03
     * @Version 1.0
     * @Param
     * @return
     */
    List<FirstReceivableBiEntity> selectFirstReceivableList(FirstReceivableBiEntity firstReceivableBiEntity);
}
