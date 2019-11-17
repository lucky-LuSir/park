package com.kfwy.park.act.activiti.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.act.activiti.dao.IThrowLeasePayApplyDbDao;
import com.kfwy.park.act.activiti.entity.ThrowLeasePayApplyEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by kfwy_it_004 on 2019/3/21.
 */
@Repository
public class ThrowLeasePayApplyDbDaoImpl extends AbstractMyBatisDao<ThrowLeasePayApplyEntity, Long> implements IThrowLeasePayApplyDbDao {
    @Override
    public List<ThrowLeasePayApplyEntity> selectDept(Map<String,Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace+"selectDept",map);
    }
}
