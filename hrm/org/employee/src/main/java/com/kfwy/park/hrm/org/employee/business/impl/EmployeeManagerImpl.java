package com.kfwy.park.hrm.org.employee.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.hrm.org.employee.business.IEmployeeManager;
import com.kfwy.park.hrm.org.employee.dao.IEmployeeDbDao;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;
import com.kfwy.park.sys.user.dao.IUserDbDao;
import com.kfwy.park.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 人员管理
 * @Auth luming
 * @Date 2018/7/4 18:19
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class EmployeeManagerImpl extends AbstractManager<EmployeeEntity> implements IEmployeeManager {

    @Autowired
    private IEmployeeDbDao employeeDbDao;

    @Autowired
    private IUserDbDao userDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return employeeDbDao;
    }

    @Override
    protected void beforeCreate(EmployeeEntity employeeEntity) {

        //新建账号默认在职  true:在职   false:离职R
        employeeEntity.setEmpIsWork(Boolean.TRUE);


        /**
         * 于2019年2月21日注掉
         * 改为页面手动输入工号
         */

//        // 生成workNumber
//        Map<String, Object> map = new HashMap<>();
//        map.put("isDeleted", false);
//        String workNumber = userDbDao.getMaxWorkNumber(map);
//
//        if (!workNumber.isEmpty()) {
//            int jobNum = Integer.parseInt(workNumber) + 1;
//            String jn = String.format("%05d", jobNum);
//            employeeEntity.setWorkNumber(jn);
//        } else {
//            String jobNum = "00001";
//            employeeEntity.setWorkNumber(jobNum);
//        }
    }


    @Override
    protected void afterCreate(EmployeeEntity employeeEntity) {
        // 获取人员姓名,电话,姓别,在新增人员时添加到user表
        String empName = employeeEntity.getEmpName();
        String empPhone = employeeEntity.getEmpPhone();
        String workNumber = employeeEntity.getWorkNumber();
        String sex = employeeEntity.getSex();
        String postCode = employeeEntity.getPostCode();
        String empCode = employeeEntity.getEmpCode();
        String deptCode = employeeEntity.getDeptCode();
        String deptName = employeeEntity.getDeptName();
        String cpyCode = employeeEntity.getCpyCode();
        String cpyName = employeeEntity.getCpyName();
        String createName = employeeEntity.getCreateName();
        Boolean hasUser = employeeEntity.getHasUser();// 该字段为新增人员时，选择是否同时创建登陆账号

        if (hasUser) {
            // user表中添加的数据
            UserEntity userEntity = new UserEntity();
            userEntity.setUserCode(empCode);
            userEntity.setEmpCode(empCode);
            userEntity.setEmpName(empName);
            userEntity.setOwnerCode(empCode);
            userEntity.setOwnerName(empName);
            userEntity.setUserPhone(empPhone);
            userEntity.setWorkNumber(workNumber);
            userEntity.setUserName(empName);
            userEntity.setSex(sex);
            userEntity.setPostCode(postCode);
            userEntity.setDeptCode(deptCode);
            userEntity.setDeptName(deptName);
            userEntity.setOwnerDeptCode(deptCode);
            userEntity.setOwnerDeptName(deptName);
            userEntity.setCpyCode(cpyCode);
            userEntity.setCpyName(cpyName);
            userEntity.setCreateName(createName);
            userEntity.setPassword("e10adc3949ba59abbe56e057f20f883e");// MD5加密后,初始密码123456

            OperateFillUtils.fill(userEntity);// 设置创建时间,修改时间
            userDbDao.insert(userEntity);
        }
    }

    /**
     * @return
     * @Description 修改人员同时修改user表
     * @Auth luming
     * @Date 2018/8/6 8:42
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public void updateEmp(EmployeeEntity employeeEntity) {

        String empCode = employeeEntity.getEmpCode();

        Map<String, Object> map = new HashMap<>();
        map.put("empCode", empCode);
        map.put("isDeleted",false);
        UserEntity ue = userDbDao.selectUniqueByMap(map);

        //设置修改时间和修改人
        employeeEntity.setLastUpdateCode(CurrentContext.getUserCode());
        employeeEntity.setLastUpdateName(CurrentContext.getUserInfo().getUserName());
        employeeEntity.setLastUpdateTime(new Date());

        // 修改人员
        employeeDbDao.update(employeeEntity);

        // 获取人员姓名,电话,姓别
        String empName = employeeEntity.getEmpName();
        String empPhone = employeeEntity.getEmpPhone();
        String workNumber = employeeEntity.getWorkNumber();
        String sex = employeeEntity.getSex();
        String postCode = employeeEntity.getPostCode();
        String deptCode = employeeEntity.getDeptCode();
        String deptName = employeeEntity.getDeptName();
        Boolean hasUser = employeeEntity.getHasUser();

        UserEntity userEntity = new UserEntity();

        // 修改人员：user不存在，hasUser为true时，修改人员的同时添加user
        if (ue == null) {
            if(hasUser){
                // user表中添加的数据
                userEntity.setUserCode(CodeGenUtils.generate());
                userEntity.setEmpCode(empCode);
                userEntity.setUserPhone(empPhone);
                userEntity.setWorkNumber(workNumber);
                userEntity.setUserName(empName);
                userEntity.setEmpName(empName);
                userEntity.setSex(sex);
                userEntity.setPostCode(postCode);
                userEntity.setDeptCode(deptCode);
                userEntity.setDeptName(deptName);
                userEntity.setPassword("e10adc3949ba59abbe56e057f20f883e");// MD5加密后,初始密码123456

                OperateFillUtils.fill(userEntity);// 设置创建时间,修改时间
                userDbDao.insert(userEntity);
            }
        } else {
            userEntity.setWorkNumber(workNumber);
            userEntity.setUserName(empName);
            userEntity.setUserPhone(empPhone);
            userEntity.setSex(sex);
            userEntity.setEmpCode(empCode);
            userEntity.setEmpName(empName);
            userEntity.setDeptCode(deptCode);
            userEntity.setDeptName(deptName);
            userEntity.setPostCode(postCode);
            OperateFillUtils.fill(userEntity);// 设置创建时间,修改时间
            userDbDao.update(userEntity);
        }
    }

    /**
     * @Description 员工在职离职更改，离职就关闭登录账号，在职就打开登录账号
     * @author wangchuang
     * @Date 2019/8/24 16:14
     * @Version 1.0
     * @Param [employeeEntity]
     * @return int
     **/
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int openCloseAccount(EmployeeEntity employeeEntity) {

        //1、更新员工是否在职字段
        employeeDbDao.update(employeeEntity);

        //查询出该员工的登录账号
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("empCode", employeeEntity.getEmpCode());

        UserEntity userEntity = userDbDao.selectUniqueByMap(param);

        //若无开通登录账号则不作处理
        if(userEntity != null) {
            //2、如果是在职改为离职，将登录账号关闭，is_deleted字段改为true
            if(!employeeEntity.getEmpIsWork()){
                userEntity.setIsDeleted(true);
            } else {
                //3、如果是离职改为在职，将登录账号打开，is_deleted字段改为false
                userEntity.setIsDeleted(false);
            }

            userDbDao.update(userEntity);
        }




        return 1;
    }
}
