package com.kfwy.park.hrm.org.employee.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.hrm.org.employee.dao.IEmployeeDbDao;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by davidcun on 2018/5/16.
 */
@Repository
public class EmployeeDbDaoImpl extends AbstractMyBatisDao<EmployeeEntity,Long> implements IEmployeeDbDao {

    /**
     * @Description 修改根据deptCode修改deptName
     * @Auth xiongzhan
     * @Date 2018/7/18 11:08
     * @Version 1.0
     * @Param [param]
     * @return int
     **/
    @Override
    public int updateBydeptCode(EmployeeEntity param) {
        return this.getSqlSession().update(this.mapperNamespace + UPDATE_BY_PK_CODE, param);
    }
}
