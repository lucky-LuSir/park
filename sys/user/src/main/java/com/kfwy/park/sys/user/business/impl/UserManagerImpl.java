package com.kfwy.park.sys.user.business.impl;

import com.gniuu.framework.common.auth.Token;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.user.business.impl.AbstractBaseUserManager;
import com.kfwy.park.sys.user.business.IUserManager;
import com.kfwy.park.sys.user.dao.IUserDbDao;
import com.kfwy.park.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Description 用户登陆退出
 * @Auth davidcun
 * @Date 2018/5/16 19:09
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class UserManagerImpl extends AbstractBaseUserManager<UserEntity> implements IUserManager {

    @Autowired
    private IUserDbDao userDbDao;

    @Override
    public long getTokenExpire() {
        return 0;
    }

    @Override
    protected IMyBatisBaseDao<UserEntity, Long> getMyBatisDao() {
        return this.userDbDao;
    }

    @Override
    public UserEntity login(UserEntity login, String keyword) {
//        if(StringUtils.isEmpty(login.getCpyMark())){
//            throw new BusinessException("公司代号不能为空");
//        }

        if(StringUtils.isEmpty(login.getPassword())){
            throw new BusinessException("密码不能为空");
        }

        Map<String, Object> param = new HashMap<>();
//        addParam(param, "cpyMark", login.getCpyMark());// 登陆暂时不校验公司09月10日
        addParam(param, "keyword", keyword);
        addParam(param, "isDeleted", false);

        List<UserEntity> users = loginUserDbDao.selectByMap(param);

        if (users == null || users.size() < 1) {
//            throw new BusinessException("用户名或公司不存在");// 登陆暂时不校验公司09月10日
            throw new BusinessException("用户名不存在");
        }

        if (users.size() > 1) {
            throw new BusinessException("存在多个账号");
        }

        UserEntity user = users.get(0);

        //前端必须要传MD5加密的密码到服务端
        if(!user.getPassword().equals(login.getPassword())){
            throw new BusinessException("用户密码错误");
        }
        return user;
    }


    /**
     * @Description 退出登陆
     * @Auth davidcun
     * @Date 2018/7/12 13:42
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public void logout(Token token) {
        if(token!=null){
            loginUserRedisDao.deleteAuthKey(token.getAuthKey());
        }
    }


    @Override
    public Token generateToken(String userCode) {
        String authKey = UUID.randomUUID().toString().replaceAll("-","");

        Token token = new Token(userCode,authKey,getTokenExpire());
        return token;
    }

    private void addParam(Map<String, Object> param, String key, Object value) {
        if (param != null && key != null && value != null) {
            param.put(key, value);
        }
    }


    /**
     * @Description 新增用户
     * @Auth luming
     * @Date 2018/7/11 18:34
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int create(UserEntity userEntity) {
        userEntity.setUserCode(CodeGenUtils.generate());
        OperateFillUtils.fill(userEntity);
        return userDbDao.insert(userEntity);
    }
}
