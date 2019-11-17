package com.kfwy.park.bi.analysis.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.analysis.business.IChannelAnalysisManager;
import com.kfwy.park.bi.analysis.dao.IChannelAreaAnalysisDbDao;
import com.kfwy.park.bi.analysis.entity.ChannelAnalysisEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by luming on 2019/5/20.
 */
@Service
public class ChannelAnalysisManagerImpl extends AbstractManager<ChannelAnalysisEntity> implements IChannelAnalysisManager {
    @Autowired
    private IChannelAreaAnalysisDbDao channelAreaAnalysisDbDao;

    @Override
    protected IMyBatisBaseDao<ChannelAnalysisEntity, Long> getMyBatisDao() {
        return this.channelAreaAnalysisDbDao;
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
    public ChannelAnalysisEntity selectChannelAnalysis(Map<String,Object> map) {
        return channelAreaAnalysisDbDao.selectChannelAnalysis(map);
    }
}
