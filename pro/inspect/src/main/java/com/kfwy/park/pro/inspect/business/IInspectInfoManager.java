package com.kfwy.park.pro.inspect.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.pro.inspect.entity.InspectInfoEntity;
import com.kfwy.park.pro.inspect.entity.InspectScoreEntity;

import java.util.List;
import java.util.Map;

public interface IInspectInfoManager extends IManager<InspectInfoEntity> {
    /**
     *@Author gtaotao
     *@Date 2019-07-12 16:08:44
     *@Description 新建评分
     *@Return null
     **/
    void createInfo(InspectInfoEntity infoEntity);

    /**
     *@Author gtaotao
     *@Date 2019-07-12 09:12:41
     *@Description 获取包含树形score信息的评分信息
     *@Return
     **/
    InspectInfoEntity findInfoWithScoreTree(Map<String, Object> map);

    /**
     *@Author gtaotao
     *@Date 2019-07-12 16:07:54
     *@Description 获取包含score信息的评分信息
     *@Return
     **/
    InspectInfoEntity findInfoWithScoreList(Map<String, Object> map);

    /**
     *@Author gtaotao
     *@Date 2019-07-12 16:12:05
     *@Description 检查参数是否完整
     *@Return
     **/
    void checkParams(InspectInfoEntity entity) throws IllegalAccessException;

    /**
     *@Author gtaotao
     *@Date 2019-07-12 16:12:58
     *@Description 修改评分信息
     *@Return
     **/
    void updateInfo(InspectInfoEntity entity);
}
