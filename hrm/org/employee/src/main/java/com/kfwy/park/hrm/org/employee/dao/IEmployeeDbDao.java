package com.kfwy.park.hrm.org.employee.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;

/**
 * Created by davidcun on 2018/5/16.
 */
public interface IEmployeeDbDao extends IMyBatisBaseDao<EmployeeEntity,Long> {

    public static final String  UPDATE_BY_PK_CODE = "updateBydeptCode";

    /**
     * @Description 修改根据deptCode修改deptName
     * @Auth xiongzhan
     * @Date 2018/7/18 11:08
     * @Version 1.0
     * @Param [param]
     * @return int
     **/
    public int updateBydeptCode(EmployeeEntity param);
}
