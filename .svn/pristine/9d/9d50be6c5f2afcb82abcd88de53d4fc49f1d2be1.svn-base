package com.kfwy.park.hrm.org.dept.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.hrm.org.dept.entity.DeptEntity;

import java.util.List;

/**
 *
 * @author davidcun
 * @date 2018/5/22
 */
public interface IDeptManager extends IManager<DeptEntity>{

    List<String> selectByUserCode(String userCode);

    /**
     * @Description 部门树
     * @author luming
     * @Date 2019/6/29 10:33
     * @Version 1.0
     * @Param [dept, deptList]
     * @return java.util.List<com.kfwy.park.hrm.org.dept.entity.DeptEntity>
     */
    List<DeptEntity> deptTree(DeptEntity dept, List<DeptEntity> deptList);


    /**
     * 部门转为树形结构
     * @return
     */
    List<DeptEntity> deptValueTree();
}
