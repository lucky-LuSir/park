package com.kfwy.park.bi.report.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.report.entity.PremiumReceivableBiEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by gengtao on 2019/4/17.
 */

public interface PremiumReceivableBiDao extends IMyBatisBaseDao<PremiumReceivableBiEntity, Long> {
    public static final String SELECT_RECEIVABLE = "selectReceivable";
    /**
     *@Author gengtao
     *@Date 2019-04-17 18:37:07
     *@Description 查询应收保险费
     *@Return
     **/
    List<PremiumReceivableBiEntity> selectReceivable(Map<String, Object>map);
}
