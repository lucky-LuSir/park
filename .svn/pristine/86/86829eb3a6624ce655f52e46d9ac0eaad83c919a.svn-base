package com.kfwy.park.hrm.org.employee.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.hrm.org.employee.dao.IPostMenuDbDao;
import com.kfwy.park.hrm.org.employee.entity.PostMenuEntity;
import org.springframework.stereotype.Repository;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/6/29 15:42
 */
@Repository
public class PostMenuDbDaoImpl extends AbstractMyBatisDao<PostMenuEntity,Long> implements IPostMenuDbDao {


    @Override
    public int deleteByPostCode(String postCode) {
        return this.getSqlSession().delete(this.mapperNamespace+"deleteByPostCode",postCode);
    }
}
