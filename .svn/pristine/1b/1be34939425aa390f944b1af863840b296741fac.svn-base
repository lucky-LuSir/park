package com.kfwy.park.sys.user.dao.impl;

import com.gniuu.framework.user.dao.impl.AbstractBaseUserDbDao;
import com.kfwy.park.sys.user.dao.IUserDbDao;
import com.kfwy.park.sys.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by davidcun on 2018/5/16.
 */
@Repository
public class UserDbDaoImpl extends AbstractBaseUserDbDao<UserEntity> implements IUserDbDao {


    /**
     * @Description 新增用户
     * @Auth luming
     * @Date 2018/7/11 18:21
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int create(UserEntity userEntity) {
        return this.getSqlSession().insert(this.mapperNamespace + "batchInsert",userEntity);
    }

    /**
     * @Description 获取最大工号
     * @Auth luming
     * @Date 2018/8/29 9:20
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public String getMaxWorkNumber(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + "selectMaxWorkNumber" , map);
    }

    /**
     * @Description 修改部门的同时修改user表中的部门信息
     * @Auth luming
     * @Date 2018/12/5 18:55
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int updateDeptByCode(UserEntity userEntity) {
        return this.getSqlSession().update(this.mapperNamespace + UPDATE_DAPT_BY_CODE, userEntity);
    }
}
