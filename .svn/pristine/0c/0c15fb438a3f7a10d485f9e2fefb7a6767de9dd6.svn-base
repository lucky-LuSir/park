package com.kfwy.park.sys.user.business;

import com.gniuu.framework.common.auth.Token;
import com.gniuu.framework.user.business.IBaseUserManager;
import com.kfwy.park.sys.user.entity.UserEntity;

/**
 * @description 用户登陆,退出
 * @auth davidCun
 * @date 2018/6/29 17:55
 * @version 1.0
 * @return  
 */
public interface IUserManager extends IBaseUserManager<UserEntity> {

    /**
     * @Description 用户登录，可能有多种登录方式
     * @Auth davidCun
     * @Date 2018/7/23 19:08
     * @Version 1.0
     * @Param
     * @return
     */
    public UserEntity login(UserEntity user, String keyword);

    /**
     * @Description 通过用户编码生成用户token
     * @Auth davidCun
     * @Date 2018/7/23 19:07
     * @Version 1.0
     * @Param
     * @return
     */
    public Token generateToken(String userCode);

    /**
     * @Description 通过授权退出登录
     * @Auth davidCun
     * @Date 2018/7/23 19:07
     * @Version 1.0
     * @Param
     * @return
     */
    public void logout(Token token);

    /**
     * @Description 新增用户
     * @Auth luming
     * @Date 2018/7/11 18:33
     * @Version 1.0
     * @Param
     * @return
     */
    public int create(UserEntity userEntity);
}
