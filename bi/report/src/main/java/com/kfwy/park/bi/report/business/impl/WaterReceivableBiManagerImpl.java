package com.kfwy.park.bi.report.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.report.business.IWaterReceivableBiManager;
import com.kfwy.park.bi.report.dao.IWaterReceivableBiDbDao;
import com.kfwy.park.bi.report.entity.WaterReceivableBiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 应收水费报表
 * @Auth luming
 * @Date 2019/1/7 15:10
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class WaterReceivableBiManagerImpl extends AbstractManager<WaterReceivableBiEntity> implements IWaterReceivableBiManager {

    @Autowired
    private IWaterReceivableBiDbDao waterReceivableBiDbDao;

    @Override
    protected IMyBatisBaseDao<WaterReceivableBiEntity, Long> getMyBatisDao() {
        return this.waterReceivableBiDbDao;
    }

    /**
     * @Description 查询应收水费
     * @Auth luming
     * @Date 2019/1/7 15:10
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<WaterReceivableBiEntity> selectWaterReceivableReport(Map<String, Object> map) {
        return waterReceivableBiDbDao.selectWaterReceivableReport(map);
    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 09:08:08
     *@Description
     *@param
     *@return 获取水费已收,未收,逾期未收
     **/
    @Override
    public Map<String, Object> selectSumReceivable(Map<String, Object> map) {
        return waterReceivableBiDbDao.selectSumReceivable(map);
    }
}
