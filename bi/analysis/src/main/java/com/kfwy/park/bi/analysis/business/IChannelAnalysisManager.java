package com.kfwy.park.bi.analysis.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.bi.analysis.entity.ChannelAnalysisEntity;

import java.util.Map;

/**
 * Created by luming on 2019/5/20.
 */
public interface IChannelAnalysisManager extends IManager<ChannelAnalysisEntity> {

    /**
     * @Description 查询客户需求的面积统计数据
     * @author luming
     * @Date 2019/5/20 14:36
     * @Version 1.0
     * @Param []
     * @return com.kfwy.park.bi.analysis.entity.NeedAreaAnalysisEntity
     */
    ChannelAnalysisEntity selectChannelAnalysis(Map<String,Object> map);
}
