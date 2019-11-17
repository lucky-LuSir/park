package com.kfwy.park.bi.report.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.bi.report.entity.PremiumReceivableBiEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by gengtao on 2019/4/17.
 */
public interface PremiumReceivableBiManager extends IManager<PremiumReceivableBiEntity>{
    /**
     *@Author gengtao
     *@Date 2019-04-17 18:37:07
     *@Description 查询应收保险费
     *@Return
     **/
    List<PremiumReceivableBiEntity> selectReceivable(Map<String, Object> map);
}
