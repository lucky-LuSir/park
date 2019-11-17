package com.kfwy.park.sys.user.dao;

import com.gniuu.framework.user.dao.IBaseUserDbDao;
import com.kfwy.park.sys.user.entity.UserEntity;

import java.util.Map;

/**
 * Created by davidcun on 2018/5/16.
 */
public interface IUserDbDao  extends IBaseUserDbDao<UserEntity>{

    public static final String UPDATE_DAPT_BY_CODE = "updateDeptByCode";

    /**
     * @Description 新增用户
     * @Auth luming
     * @Date 2018/7/11 18:19
     * @Version 1.0
     * @Param
     * @return
     */
    int create(UserEntity userEntity);

    /**
     * @Description 获取最大工号
     * @Auth luming
     * @Date 2018/8/29 9:21
     * @Version 1.0
     * @Param
     * @return
     */
    String getMaxWorkNumber(Map<String,Object> map);

    /**
     * @Description 修改部门的同时修改user表中的部门信息
     * @Auth luming
     * @Date 2018/12/5 18:53
     * @Version 1.0
     * @Param
     * @return
     */
    int updateDeptByCode(UserEntity userEntity);
}
