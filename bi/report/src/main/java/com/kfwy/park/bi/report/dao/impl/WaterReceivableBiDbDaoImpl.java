package com.kfwy.park.bi.report.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.bi.report.dao.IWaterReceivableBiDbDao;
import com.kfwy.park.bi.report.entity.WaterReceivableBiEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description 应收水费报表
 * @Auth luming
 * @Date 2019/01/07 15:25
 * @Version 1.0
 * @Param
 * @return
 */
@Repository
public class WaterReceivableBiDbDaoImpl extends AbstractMyBatisDao<WaterReceivableBiEntity, Long> implements IWaterReceivableBiDbDao {


    /**
     * @Description 查询应收水费信息
     * @Auth luming
     * @Date 2019/01/07 15:25
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<WaterReceivableBiEntity> selectWaterReceivableReport(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_WATER_RECEIVABLE_REPORT, map);
    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 09:06:31
     *@Description
     *@param
     *@return 获取水费已收,未收,逾期未收
     **/
    @Override
    public Map<String, Object> selectSumReceivable(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_SUM_RECEIVABLE, map);
    }
}
