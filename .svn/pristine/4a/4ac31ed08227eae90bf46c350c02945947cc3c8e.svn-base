package com.kfwy.park.hrm.org.dept.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.hrm.org.dept.entity.DeptEntity;

import java.util.List;
import java.util.Map;

/**
 *
 * @author davidcun
 * @date 2018/5/22
 */
public interface IDeptDbDao extends IMyBatisBaseDao<DeptEntity,Long> {
    public static final String SELECT_BY_USER_CODE = "selectByUserCode";

    List<String> selectByUserCode(String userCode);
}
