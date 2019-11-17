package com.kfwy.park.sys.user.dao.impl;

import com.gniuu.framework.user.dao.impl.AbstractBaseUserRedisDao;
import com.kfwy.park.sys.user.dao.IUserRedisDao;
import com.kfwy.park.sys.user.entity.UserEntity;
import org.springframework.stereotype.Repository;

/**
 * Created by davidcun on 2018/5/16.
 */
@Repository
public class UserRedisDaoImpl extends AbstractBaseUserRedisDao<UserEntity> implements IUserRedisDao<UserEntity> {


    public static String userCodeKey = "ky:park:user:code:";
    public static String userAuthKey = "ky:park:user:auth:";

    public String createUserCodeKey(String userCode){
        return userCodeKey+userCode;
    }

    public String createAuthKey(String authKey){
        return userAuthKey+authKey;
    }

}
