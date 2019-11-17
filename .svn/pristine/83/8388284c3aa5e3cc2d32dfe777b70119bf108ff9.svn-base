package com.kfwy.park.bi.report.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.report.business.IElectricityReceivableBiManager;
import com.kfwy.park.bi.report.dao.IElectricityReceivableBiDbDao;
import com.kfwy.park.bi.report.entity.ElectricityReceivableBiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Description 应收电费报表
 * @Auth luming
 * @Date 2019/1/7 15:10
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class ElectricityReceivableBiManagerImpl extends AbstractManager<ElectricityReceivableBiEntity> implements IElectricityReceivableBiManager {

    @Autowired
    private IElectricityReceivableBiDbDao electricityReceivableBiDbDao;

    @Override
    protected IMyBatisBaseDao<ElectricityReceivableBiEntity, Long> getMyBatisDao() {
        return this.electricityReceivableBiDbDao;
    }

    /**
     * @Description 查询应收电费
     * @Auth luming
     * @Date 2019/1/7 15:10
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<ElectricityReceivableBiEntity> selectElectricityReceivableReport(Map<String, Object> map) {
        return electricityReceivableBiDbDao.selectElectricityReceivableReport(map);
    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 09:08:08
     *@Description
     *@param
     *@return 获取电费已收,未收,逾期未收
     **/
    @Override
    public Map<String, Object> selectSumReceivable(Map<String, Object> map) {
        return electricityReceivableBiDbDao.selectSumReceivable(map);
    }
}
