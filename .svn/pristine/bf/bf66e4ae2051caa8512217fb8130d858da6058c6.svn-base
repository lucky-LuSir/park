package com.kfwy.park.bi.analysis.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.analysis.entity.ChannelAnalysisEntity;

import java.util.Map;

/**
 * Created by luming on 2019/5/20.
 */
public interface IChannelAreaAnalysisDbDao extends IMyBatisBaseDao<ChannelAnalysisEntity, Long> {

    public static final String SELECT_CHANNEL_ANALYSIS = "selectChannelAnalysis";

    /**
     * @Description 查询获客渠道分析数据
     * @author luming
     * @Date 2019/5/20 14:31
     * @Version 1.0
     * @Param []
     * @return com.kfwy.park.bi.analysis.entity.NeedAreaAnalysisEntity
     */
    ChannelAnalysisEntity selectChannelAnalysis(Map<String,Object> map);
}
