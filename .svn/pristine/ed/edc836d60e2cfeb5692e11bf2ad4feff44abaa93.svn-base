package com.kfwy.park.hrm.org.dept.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.hrm.org.dept.business.IDeptManager;
import com.kfwy.park.hrm.org.dept.dao.IDeptDbDao;
import com.kfwy.park.hrm.org.dept.entity.DeptEntity;
import com.kfwy.park.hrm.org.employee.dao.IEmployeeDbDao;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;
import com.kfwy.park.sys.user.dao.IUserDbDao;
import com.kfwy.park.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author davidcun
 * @date 2018/5/22
 */
@Service
public class DeptManagerImpl extends AbstractManager<DeptEntity> implements IDeptManager {

    @Autowired
    private IDeptDbDao deptDbDao;
    @Autowired
    private IEmployeeDbDao employeeDbDao;
    @Autowired
    private IUserDbDao userDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.deptDbDao;
    }

    @Override
    protected void beforeCreate(DeptEntity deptEntity) {
        deptEntity.setDeptCode(CodeGenUtils.generate());
    }

    /**
     * @Description 更新后的操作
     * @Auth xiongzhan
     * @Date 2018/7/18 11:12
     * @Version 1.0
     * @Param [t]
     * @return void
     **/
    @Override
    protected void afterUpdate(DeptEntity deptEntity) {
        // 修改部门之后去修改人员所属部门名字
        if (deptEntity != null && deptEntity.getDeptCode() != null) {

            EmployeeEntity bdEntity = new EmployeeEntity();
            bdEntity.setDeptCode(deptEntity.getDeptCode());
            bdEntity.setDeptName(deptEntity.getDeptName());
            employeeDbDao.updateBydeptCode(bdEntity);

            // 修改部门同时修改user表
            UserEntity userEntity = new UserEntity();
            userEntity.setDeptCode(deptEntity.getDeptCode());
            userEntity.setDeptName(deptEntity.getDeptName());
            userDbDao.updateDeptByCode(userEntity);
        }

        super.afterUpdate(deptEntity);
    }


    @Override
    public List<String> selectByUserCode(String userCode){
        return deptDbDao.selectByUserCode(userCode);
    }

    /**
     * @Description 递归获得部门树
     * @author luming
     * @Date 2019/6/29 10:34
     * @Version 1.0
     * @Param [dept, deptList]
     * @return java.util.List<com.kfwy.park.hrm.org.dept.entity.DeptEntity>
     */
    @Override
    public List<DeptEntity> deptTree(DeptEntity dept, List<DeptEntity> deptList) {
        dept.setLabel(dept.getDeptName());
        dept.setValue(dept.getDeptCode());

        // 当前部门下的所有子部门
        List<DeptEntity> list = new ArrayList<>();
        for (DeptEntity d : deptList) {
            if (dept.getDeptCode().equals(d.getParentDeptCode())) {
                d.setLabel(d.getDeptName());
                d.setValue(d.getDeptCode());
                list.add(d);
            }
        }

        if (list.size() > 0) {
            dept.setChildren(list);
        } else {
            dept.setChildren(null);
        }

        for (DeptEntity d : list) {
            List<DeptEntity> deptEntities = deptTree(d, deptList);
        }

        return list;
    }

    @Override
    public List<DeptEntity> deptValueTree() {
        List<DeptEntity> list = new ArrayList<>();

        // 查询所有部门
        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);
        params.put("cpyCode", CurrentContext.getCpyCode());
        List<DeptEntity> deptList = this.findByMap(params);

        if (deptList.size() > 0) {
            // 查询最顶层部门，即CEO
            Map<String, Object> map = new HashMap<>();
            map.put("deptType", "1");
            map.put("isDeleted", false);
            map.put("cpyCode", CurrentContext.getCpyCode());
            DeptEntity deptOne = this.findOne(map);

            List<DeptEntity> deptEntityList = deptTree(deptOne, deptList);
            deptOne.setChildren(deptEntityList);
            list.add(deptOne);
        }

        return list;
    }
}
