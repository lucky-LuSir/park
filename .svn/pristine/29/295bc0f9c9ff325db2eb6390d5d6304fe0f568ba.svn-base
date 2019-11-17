package com.kfwy.park.act.activiti.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.act.activiti.entity.ThrowLeaseApplyEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by luming on 2019/3/13.
 * 退租申请工作流
 */
public interface IThrowLeaseApplyDbDao extends IMyBatisBaseDao<ThrowLeaseApplyEntity, Long> {
    List<ThrowLeaseApplyEntity> selectDept(Map<String,Object> map);

    /**
     *@author gtaotao
     *@Date 2019-08-15 17:06:56
     *@Description 获取6个月营收,根据合同时间获取退租信息
     *@param
     *@return
     **/
    List<ThrowLeaseApplyEntity> selectByContractTime(Map<String, Object> map);
}
