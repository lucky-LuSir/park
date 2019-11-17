package com.kfwy.park.act.activiti.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.act.activiti.dao.IThrowLeaseApplyDbDao;
import com.kfwy.park.act.activiti.entity.ThrowLeaseApplyEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by luming on 2019/3/13.
 * 退租申请工作流
 */
@Repository
public class ThrowLeaseApplyDbDaoImpl extends AbstractMyBatisDao<ThrowLeaseApplyEntity, Long> implements IThrowLeaseApplyDbDao {
    @Override
    public List<ThrowLeaseApplyEntity> selectDept(Map<String,Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace+"selectDept",map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-15 17:06:56
     *@Description 获取6个月营收,根据合同时间获取退租信息
     *@param
     *@return
     **/
    @Override
    public List<ThrowLeaseApplyEntity> selectByContractTime(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + "selectByContractTime", map);
    }
}
