package com.kfwy.park.bi.analysis.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.analysis.entity.NeedAreaAnalysisEntity;

import java.util.Map;

/**
 * Created by luming on 2019/5/20.
 */
public interface INeedAreaAnalysisDbDao extends IMyBatisBaseDao<NeedAreaAnalysisEntity, Long> {

    public static final String SELECT_NEED_AREA_ANALYSIS = "selectNeedAreaAnalysis";

    /**
     * @Description 查询客户需求的面积统计数据
     * @author luming
     * @Date 2019/5/20 14:31
     * @Version 1.0
     * @Param []
     * @return com.kfwy.park.bi.analysis.entity.NeedAreaAnalysisEntity
     */
    NeedAreaAnalysisEntity selectNeedAreaAnalysis(Map<String, Object> map);
}
