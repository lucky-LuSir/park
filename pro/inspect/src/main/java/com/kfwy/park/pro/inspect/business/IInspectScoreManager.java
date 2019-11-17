package com.kfwy.park.pro.inspect.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.pro.inspect.entity.InspectScoreEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * 物业检查得分信息表 接口
 * @author gtaotao
 * @since 2019-07-09 13:49:25
 */
public interface IInspectScoreManager extends IManager<InspectScoreEntity>{
    /**
     *@Author gtaotao
     *@Date 2019-07-13 08:57:59
     *@Description 将得分信息转为tree结构
     *@Return
     **/
    InspectScoreEntity scoreInfoToTree(InspectScoreEntity rootEntity, List<InspectScoreEntity> inspectInfoEntityList);

    /**
     *@Author gtaotao
     *@Date 2019-07-13 09:09:51
     *@Description 更新Tree结构score
     *@Return
     **/
    void updateScoreTree(InspectScoreEntity scoreTree);

    /**
     *@Author gtaotao
     *@Date 2019-07-13 09:13:51
     *@Description 根据Tree获取分数
     *@Return
     **/
    Map<String, Object> totalGradeAndScoreWithTree(Map<String, Object> map, InspectScoreEntity scoreTree);
}