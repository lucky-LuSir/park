package com.kfwy.park.hrm.org.employee.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.hrm.org.employee.dao.IPostDbDao;
import com.kfwy.park.hrm.org.employee.entity.PostEntity;
import org.springframework.stereotype.Repository;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/12 22:51
 * @Version 1.0
 */
@Repository
public class PostDbDaoImpl extends AbstractMyBatisDao<PostEntity,Long> implements IPostDbDao {


}
