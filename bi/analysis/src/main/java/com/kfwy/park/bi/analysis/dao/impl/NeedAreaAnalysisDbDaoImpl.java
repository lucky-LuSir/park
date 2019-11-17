package com.kfwy.park.bi.analysis.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.bi.analysis.dao.INeedAreaAnalysisDbDao;
import com.kfwy.park.bi.analysis.entity.NeedAreaAnalysisEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by luming on 2019/5/20.
 */
@Repository
public class NeedAreaAnalysisDbDaoImpl  extends AbstractMyBatisDao<NeedAreaAnalysisEntity, Long> implements INeedAreaAnalysisDbDao {

    /**
     * @Description 查询客户需求的面积统计数据
     * @author luming
     * @Date 2019/5/20 14:32
     * @Version 1.0
     * @Param []
     * @return com.kfwy.park.bi.analysis.entity.NeedAreaAnalysisEntity
     */
    @Override
    public NeedAreaAnalysisEntity selectNeedAreaAnalysis(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_NEED_AREA_ANALYSIS, map);
    }
}
