package com.kfwy.park.bi.report.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.bi.report.dao.IAccountReceivableBiDbDao;
import com.kfwy.park.bi.report.entity.AccountReceivableBiEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description 应收费用报表
 * @Auth luming
 * @Date 2018/12/14 11:25
 * @Version 1.0
 * @Param
 * @return
 */
@Repository
public class AccountReceivableBiDbDaoImpl extends AbstractMyBatisDao<AccountReceivableBiEntity, Long> implements IAccountReceivableBiDbDao {


    /**
     * @Description 查询租金信息
     * @Auth luming
     * @Date 2018/12/14 15:11
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<AccountReceivableBiEntity> selectRentInfo(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_RENT_INFO, map);
    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 09:06:31
     *@Description
     *@param
     *@return 获取租金已收,未收,逾期未收
     **/
    @Override
    public Map<String, Object> selectSumReceivable(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_SUM_RECEIVABLE, map);
    }
}
