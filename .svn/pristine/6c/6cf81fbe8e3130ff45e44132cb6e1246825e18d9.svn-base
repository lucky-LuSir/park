package com.kfwy.park.bi.report.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.bi.report.entity.ElectricityReceivableBiEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description 应收电费报表
 * @Auth luming
 * @Date 2019/1/7 15:09
 * @Version 1.0
 * @Param
 * @return
 */
public interface IElectricityReceivableBiManager extends IManager<ElectricityReceivableBiEntity> {

    /**
     * @Description 查询应收电费
     * @Auth luming
     * @Date 2018/12/14 15:13
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
