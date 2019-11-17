package com.kfwy.park.sys.cpy.business;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.RestBusinessException;
import com.kfwy.park.hrm.org.employee.business.IEmployeeManager;
import com.kfwy.park.hrm.org.employee.dao.IPostDbDao;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;
import com.kfwy.park.hrm.org.employee.entity.PostEntity;
import com.kfwy.park.sys.cpy.dao.ICompanyDbDao;
import com.kfwy.park.sys.cpy.entity.CompanyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Description 公司管理
 * @Auth luming
 * @Date 2018/7/30 10:00
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class CompanyManagerImpl extends AbstractManager<CompanyEntity> implements ICompanyManager {

    @Autowired
    private ICompanyDbDao companyDbDao;

    @Autowired
    private IEmployeeManager employeeManager;

    @Autowired
    private IPostDbDao postDbDao;


    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.companyDbDao;
    }

    @Override
    protected void beforeCreate(CompanyEntity companyEntity) {

    }

    @Override
    protected void afterCreate(CompanyEntity companyEntity) {
        /**
         * 新增公司后，创建默认岗位“企业管理员”，关联权限为空
         */
        PostEntity postEntity = new PostEntity();
        postEntity.setPostCode(CodeGenUtils.generate());
        postEntity.setPostName("企业管理员");
        postEntity.setCpyCode(companyEntity.getCpyCode());
        postEntity.setCpyName(companyEntity.getCpyName());
        postEntity.setRemark("公司最高权限账号");
        postDbDao.insert(postEntity);

        /**
         * 新增公司后，创建人员，并且创建人员的登陆账号
         */
        EmployeeEntity employeeEntity = new EmployeeEntity();
        employeeEntity.setEmpCode(companyEntity.getUserCode());
        employeeEntity.setEmpName(companyEntity.getUserName());
        employeeEntity.setSex(companyEntity.getSex());
        employeeEntity.setEmpPhone(companyEntity.getPhone());
        employeeEntity.setPostCode(postEntity.getPostCode());
        employeeEntity.setPostName(postEntity.getPostName());
        employeeEntity.setHasUser(true);
        employeeEntity.setCpyCode(companyEntity.getCpyCode());
        employeeEntity.setCpyName(companyEntity.getCpyName());
        employeeManager.create(employeeEntity);


        // 添加公司时，同时新增一个admin的user用户
//        UserEntity userEntity = new UserEntity();
//        userEntity.setUserCode(code);
//        userEntity.setUserName("admin");
//        userEntity.setPassword("e10adc3949ba59abbe56e057f20f883e");// MD5加密后,初始密码123456
//        userEntity.setUserPhone(cpyPhone);
//        userEntity.setEmpCode(code);
//        userEntity.setEmpName("admin");
//        userEntity.setPostCode("19062522e10563e4a841");// 企业管理员  其他企业的公司管理员岗位（最高权限）
//        userEntity.setSex("1");
//        userEntity.setCpyCode(cpyCode);
//        userEntity.setCpyName(cpyName);


        // 获取最大workNumber
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("isDeleted", false);
//        String workNumber = userDbDao.getMaxWorkNumber(map);

        //生成workNumber3
//        if (!workNumber.isEmpty()) {
//            int jobNum = Integer.parseInt(workNumber) + 1;
//            String jn = String.format("%06d", jobNum);
//            userEntity.setWorkNumber(jn);
//        } else {
//            String jobNum = "00001";
//            userEntity.setWorkNumber(jobNum);
//        }

//        String jobNum = "00001";
//        userEntity.setWorkNumber(jobNum);

//        OperateFillUtils.fill(userEntity);//设置创建时间,修改时间
//        userDbDao.insert(userEntity);
    }

    @Override
    protected void beforeUpdate(CompanyEntity companyEntity) {
        if (StringUtils.isEmpty(companyEntity.getCpyCode())
                && companyEntity.getId() == null) {
            throw new RestBusinessException("更新企业帐套，cpyCode和Id不能都为空");
        }
    }
}
