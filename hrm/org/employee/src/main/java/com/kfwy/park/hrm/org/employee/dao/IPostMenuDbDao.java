package com.kfwy.park.hrm.org.employee.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.hrm.org.employee.entity.PostMenuEntity;

/**
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/6/29 15:41
 */
public interface IPostMenuDbDao extends IMyBatisBaseDao<PostMenuEntity,Long> {

    /**
     * 根据岗位编码删除岗位及菜单的关联
     * @param postCode
     * @return
     */
    int deleteByPostCode(String postCode);
}
