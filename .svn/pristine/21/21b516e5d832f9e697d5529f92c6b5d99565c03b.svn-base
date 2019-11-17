package com.kfwy.park.bi.report.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.report.entity.AccountReceivableBiEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description 应收费用报表
 * @Auth luming
 * @Date 2018/12/14 11:24
 * @Version 1.0
 * @Param
 * @return
 */
public interface IAccountReceivableBiDbDao extends IMyBatisBaseDao<AccountReceivableBiEntity, Long> {

    public static final String SELECT_RENT_INFO = "selectRentInfo";
    public static final String SELECT_SUM_RECEIVABLE = "selectSumReceivable";

    /**
     * @Description 查询租金信息
     * @Auth luming
     * @Date 2018/12/14 15:11
     * @Version 1.0
     * @Param
     * @return
     */
    List<AccountReceivableBiEntity> selectRentInfo(Map<String, Object> map);

    /**
     *@author gtaotao
     *@Date 2019-07-31 09:06:31
     *@Description
     *@param
     *@return 获取租金已收,未收,逾期未收
     **/
    Map<String, Object> selectSumReceivable(Map<String, Object> map);
}
