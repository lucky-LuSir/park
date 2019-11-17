package com.kfwy.park.hrm.org.dept.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.park.hrm.org.dept.entity.DeptEntity;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author davidcun
 * @date 2018/5/22
 */
@Repository
public class DeptDbDaoImpl extends AbstractMyBatisDao<DeptEntity,Long> implements IDeptDbDao {

    @Override
    public List<String> selectByUserCode(String userCode){
        return this.getSqlSession().selectList(this.mapperNamespace+SELECT_BY_USER_CODE,userCode);
    }
}
