package com.kfwy.park.bi.report.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.report.entity.WaterReceivableBiEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description 应收水费报表
 * @Auth luming
 * @Date 2019/1/7 14:59
 * @Version 1.0
 * @Param
 * @return
 */
public interface IWaterReceivableBiDbDao extends IMyBatisBaseDao<WaterReceivableBiEntity, Long> {

    public static final String SELECT_WATER_RECEIVABLE_REPORT = "selectWaterReceivableReport";
    public static final String SELECT_SUM_RECEIVABLE = "selectSumReceivable";

    /**
     * @Description 应收水费报表
     * @Auth luming
     * @Date 2019/1/7 14:59
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
     *@return 获取电费已收,未收,逾期未收
     **/
    Map<String, Object> selectSumReceivable(Map<String, Object> map);
}
