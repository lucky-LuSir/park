package com.kfwy.park.bi.analysis.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.bi.analysis.dao.IChannelAreaAnalysisDbDao;
import com.kfwy.park.bi.analysis.entity.ChannelAnalysisEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by luming on 2019/5/20.
 */
@Repository
public class ChannelAnalysisDbDaoImpl extends AbstractMyBatisDao<ChannelAnalysisEntity, Long> implements IChannelAreaAnalysisDbDao {

    /**
     * @Description 查询获客渠道分析数据
     * @author luming
     * @Date 2019/5/20 14:32
     * @Version 1.0
     * @Param []
     * @return com.kfwy.park.bi.analysis.entity.NeedAreaAnalysisEntity
     */
    @Override
    public ChannelAnalysisEntity selectChannelAnalysis(Map<String,Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_CHANNEL_ANALYSIS, map);
    }
}
