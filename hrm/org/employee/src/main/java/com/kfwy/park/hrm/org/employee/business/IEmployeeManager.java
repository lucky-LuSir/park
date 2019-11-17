package com.kfwy.park.hrm.org.employee.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;

/**
 * @Description 人员管理
 * @Auth luming
 * @Date 2018/7/26 9:11
 * @Version 1.0
 * @Param
 * @return
 */
public interface IEmployeeManager extends IManager<EmployeeEntity> {

    /**
     * @Description 修改人员同时修改user表
     * @Auth luming
     * @Date 2018/8/6 8:41
     * @Version 1.0
     * @Param
     * @return
     */
    public void updateEmp(EmployeeEntity employeeEntity);

    /**
     * @Description 员工在职离职更改，离职就关闭登录账号，在职就打开登录账号
     * @author wangchuang
     * @Date 2019/8/24 16:14
     * @Version 1.0
     * @Param [employeeEntity]
     * @return int
     **/
    int openCloseAccount(EmployeeEntity employeeEntity);
}
