package com.kfwy.park.bi.report.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.report.entity.ElectricityReceivableBiEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description 应收电费报表
 * @Auth luming
 * @Date 2019/1/7 14:59
 * @Version 1.0
 * @Param
 * @return
 */
public interface IElectricityReceivableBiDbDao extends IMyBatisBaseDao<ElectricityReceivableBiEntity, Long> {

    public static final String SELECT_ELECTRICITY_RECEIVABLE_REPORT = "selectElectricityReceivableReport";
    public static final String SELECT_SUM_RECEIVABLE = "selectSumReceivable";

    /**
     * @Description 应收电费报表
     * @Auth luming
     * @Date 2019/1/7 14:59
     * @Version 1.0
     * @Param
     * @return
     */
    List<ElectricityReceivableBiEntity> selectElectricityReceivableReport(Map<String, Object> map);

    /**
     *@author gtaotao
     *@Date 2019-07-31 09:06:31
     *@Description
     *@param
     *@return 获取电费已收,未收,逾期未收
     **/
    Map<String, Object> selectSumReceivable(Map<String, Object> map);
}
