package com.kfwy.park.bi.report.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.bi.report.entity.WaterReceivableBiEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description 应收水费报表
 * @Auth luming
 * @Date 2019/1/7 15:09
 * @Version 1.0
 * @Param
 * @return
 */
public interface IWaterReceivableBiManager extends IManager<WaterReceivableBiEntity> {

    /**
     * @Description 查询应收水费
     * @Auth luming
     * @Date 2018/12/14 15:13
     * @Version 1.0
     * @Param
     * @return
     */
    List<WaterReceivableBiEntity> selectWaterReceivableReport(Map<String, Object> map);

    /**
     *@author gtaotao
     *@Date 2019-07-31 09:06:31
     *@Description
     *@param
     *@return 获取水费已收,未收,逾期未收
     **/
    Map<String, Object> selectSumReceivable(Map<String, Object> map);
}
