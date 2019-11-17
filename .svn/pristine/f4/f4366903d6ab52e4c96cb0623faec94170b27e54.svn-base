package com.kfwy.park.controller.sys.user;

import com.gniuu.framework.common.auth.Token;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CookieHelper;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.AbstractServiceResponse;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.sys.user.vo.UserDto;
import com.kfwy.park.controller.sys.user.vo.UserServiceRequest;
import com.kfwy.park.controller.sys.user.vo.UserServiceResponse;
import com.kfwy.park.sys.auth.business.IMenuManager;
import com.kfwy.park.sys.auth.business.IRoleManager;
import com.kfwy.park.sys.auth.entity.MenuEntity;
import com.kfwy.park.sys.auth.entity.RoleEntity;
import com.kfwy.park.sys.cpy.business.ICompanyManager;
import com.kfwy.park.sys.cpy.entity.CompanyEntity;
import com.kfwy.park.sys.user.business.IUserDataManager;
import com.kfwy.park.sys.user.business.IUserManager;
import com.kfwy.park.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 用户登陆信息
 * @Auth davidCun
 * @Date 2018/7/11 17:20
 * @Version 1.0
 * @Param
 * @return
 */
@Controller
@RequestMapping("/user")
public class UserService extends SpringRestService {

    @Autowired
    private IUserManager userManager;

    @Autowired
    private IUserDataManager userDataManager;

    @Autowired
    private IMenuManager menuManager;

    @Autowired
    private IRoleManager roleManager;

    @Autowired
    private ICompanyManager companyManager;

    /**
     * @Description 添加用户
     * @Auth luming
     * @Date 2018/7/11 17:44
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> create(@RequestBody UserServiceRequest request) {
        UserServiceResponse response = new UserServiceResponse();
        UserEntity userEntity = request.getEntity();
        userManager.create(userEntity);
        return this.success(response);
    }

    @RequestMapping(path = "/updateUserRoleList",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> updateUserRoleList(@RequestBody UserServiceRequest request) throws IllegalAccessException {

        UserServiceResponse response = new UserServiceResponse();

        userDataManager.updateUserRoleList(request.getEntity(),request.getRoleCodes());

        return this.success(response);
    }

    /**
     * @Description 登陆
     * @Auth davidCun
     * @Date 2018/7/11 17:01
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<IServiceResponse> login(@RequestBody UserServiceRequest request,
                                                  HttpServletRequest req, HttpServletResponse resp) {

        AbstractServiceResponse response = new AbstractServiceResponse();

        UserEntity user = userManager.login(request.getEntity(), request.getKeyword());

        Token token = null;
        if (user != null) {
            //1、创建token
            token = userManager.generateToken(user.getUserCode());
            //2、把token写入cookie
            CookieHelper.addUserLoginTokenToCookie(req, resp, token);
            //3、保存authKey与userCode关系
            userManager.saveAuthKey(token.getAuthKey(), token.getUserCode());
        }

        //查询出公司的图片code
        Map<String,Object> param = new HashMap<>();
        param.put("cpyCode",user.getCpyCode());
        param.put("isDeleted",false);

        CompanyEntity companyEntity = companyManager.findOne(param);

        Map<String,Object> map = new HashMap<>();
        map.put("token",token.toBase64String());
        map.put("postCode",user.getPostCode());
        map.put("userName",user.getUserName());
        map.put("userCode",user.getUserCode());
        map.put("empCode",user.getEmpCode());
        map.put("deptCode",user.getDeptCode());
        map.put("cpyCode",user.getCpyCode());
        map.put("cpyName",user.getCpyName());
        map.put("cpyImg",companyEntity.getCpyImg());

        //超级管理员判断
//        String workNumAdmin = user.getWorkNumber();
//        if(workNumAdmin.equals("admin")){
//            List<MenuEntity> allMenu = menuManager.findAllMenuTree();
//            map.put("menuTreeList",allMenu);
//        }else{
//
//        }
        //a获取用户权限
        String upostCode = user.getUserCode();

        //a.5 根据总叶子权限递归遍历获得完整权限树
        List<MenuEntity> menuTreeResult = new ArrayList<MenuEntity>();
        menuTreeResult = menuManager.getMenuResult(upostCode,user.getEmpCode());
        //放在map中传
        map.put("menuTreeList",menuTreeResult);

        response.setResult(map);

        return this.success(response);
    }


    /**
     * @Description 退出
     * @Auth davidCun
     * @Date 2018/7/11 17:02
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/logout", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> logout(HttpServletRequest req, HttpServletResponse resp) {

        AbstractServiceResponse response = new AbstractServiceResponse();

//        System.out.println("查看token::"+request.getToken());
//        userManager.logout(request.getToken());

        userManager.logout(CurrentContext.getToken());

        CookieHelper.invalidateUserLoginTokenCookie(req, resp);

        return this.success(response);
    }


    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody UserServiceRequest request) throws IllegalAccessException {

        UserServiceResponse response = new UserServiceResponse();

        UserEntity uEntity = request.getEntity();
        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<UserEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(uEntity, param);
        if(StringUtils.isNotBlank(uEntity.getUserCode())){
            param.put("userCode",uEntity.getUserCode());
        }
        if(StringUtils.isNotBlank(uEntity.getUserName())){
            param.put("userName",uEntity.getUserName());
        }
        param.put("cpyCode",CurrentContext.getCpyCode());
        param.put("isDeleted",false);

        PageResult<UserEntity> result = userDataManager.findByMap(param,request.getStart(),request.getPageSize(),"create_time",false);

        response.setResult(result);

        return this.success(response);
    }

    @RequestMapping(path = "/queryOne",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryOne(@RequestBody UserServiceRequest request){
        UserServiceResponse response = new UserServiceResponse();

        UserEntity uEntity = userDataManager.findOne("userCode",request.getEntity().getUserCode());

        UserDto pd = new UserDto(uEntity);

        List<RoleEntity> roles = new ArrayList<RoleEntity>();
        roles = roleManager.findRoleListByUser(pd.getUserCode());
        pd.setRoles(roleManager.findRoleListByUser(pd.getUserCode()));

        response.setResult(pd);

        return this.success(response);
    }

    /**
     * @Description 修改密码
     * @Auth luming
     * @Date 2018/8/28 15:13
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody UserServiceRequest request){

        UserServiceResponse response = new UserServiceResponse();
        UserEntity uEntity = request.getEntity();

        //查询当前登陆用户的信息
        UserEntity userEntity = userDataManager.queryCurrentUser();
        String pwd = userEntity.getPassword();

        //与前端旧密码对比
        if(pwd.equals(uEntity.getPassword())){
            UserEntity ue = new UserEntity();
            ue.setPassword(request.getNewPwd());
            ue.setId(userEntity.getId());
            ue.setUserCode(userEntity.getUserCode());
            ue.setEmpCode(userEntity.getEmpCode());

            userDataManager.update(ue);
        }else{
            throw new BusinessException("原密码错误!");
        }

        return this.success(response);
    }
}
