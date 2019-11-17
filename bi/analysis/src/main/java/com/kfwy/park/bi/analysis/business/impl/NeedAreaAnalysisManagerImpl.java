package com.kfwy.park.bi.analysis.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.analysis.business.INeedAreaAnalysisManager;
import com.kfwy.park.bi.analysis.dao.INeedAreaAnalysisDbDao;
import com.kfwy.park.bi.analysis.entity.NeedAreaAnalysisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by luming on 2019/5/20.
 */
@Service
public class NeedAreaAnalysisManagerImpl extends AbstractManager<NeedAreaAnalysisEntity> implements INeedAreaAnalysisManager {
    @Autowired
    private INeedAreaAnalysisDbDao needAreaAnalysisDbDao;

    @Override
    protected IMyBatisBaseDao<NeedAreaAnalysisEntity, Long> getMyBatisDao() {
        return this.needAreaAnalysisDbDao;
    }

    /**
     * @Description 查询客户需求的面积统计数据
     * @author luming
     * @Date 2019/5/20 14:38
     * @Version 1.0
     * @Param []
     * @return com.kfwy.park.bi.analysis.entity.NeedAreaAnalysisEntity
     */
    @Override
    public NeedAreaAnalysisEntity selectNeedAreaAnalysis(Map<String, Object> map) {
        return needAreaAnalysisDbDao.selectNeedAreaAnalysis(map);
    }
}
