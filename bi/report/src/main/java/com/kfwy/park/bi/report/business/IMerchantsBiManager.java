package com.kfwy.park.bi.report.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.bi.report.entity.MerchantsBiEntity;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

/**
 * @Description 招商人员任务、去化数据
 * @Auth luming
 * @Date 2018/12/3 15:38
 * @Version 1.0
 * @Param
 * @return
 */
public interface IMerchantsBiManager extends IManager<MerchantsBiEntity> {

    /**
     * @Description 查询招商人员去化数据、日常任务
     * @Auth luming
     * @Date 2018/12/3 18:51
     * @Version 1.0
     * @Param
     * @return
     */
    List<MerchantsBiEntity> selectMerchants(MerchantsBiEntity merchantsBiEntity, Map<String, Object> map) throws ParseException;
}
