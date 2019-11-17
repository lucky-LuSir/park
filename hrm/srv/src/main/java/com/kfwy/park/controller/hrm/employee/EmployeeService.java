package com.kfwy.park.controller.hrm.employee;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.gniuu.framework.user.dao.impl.AbstractBaseUserRedisDao;
import com.kfwy.park.common.utils.MD5Utils;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.hrm.employee.vo.EmployeeServiceRequest;
import com.kfwy.park.controller.hrm.employee.vo.EmployeeServiceResponse;
import com.kfwy.park.hrm.org.employee.business.IEmployeeManager;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;
import com.kfwy.park.sys.user.business.IUserDataManager;
import com.kfwy.park.sys.user.dao.IUserDbDao;
import com.kfwy.park.sys.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description 人员管理
 * @auth davidCun
 * @date 2018/7/4 10:59
 * @return
 */
@RestController
@RequestMapping(path = "/employee")
public class EmployeeService extends SpringRestService {

    @Autowired
    private IEmployeeManager employeeManager;

    @Autowired
    private IUserDataManager userDataManager;

    @Autowired
    private RedisOperations redisOperations;

    @Autowired
    private AbstractBaseUserRedisDao abstractBaseUserRedisDao;


    /**
     * @return
     * @Description 添加人员
     * @Auth luming
     * @Date 2018/7/25 17:45
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody EmployeeServiceRequest request) throws IllegalAccessException {

        EmployeeServiceResponse response = new EmployeeServiceResponse();

        EmployeeEntity employeeEntity = request.getEntity();

        employeeEntity.setEmpCode(CodeGenUtils.generate());// 人员表设置empCode

        List<String> list = new ArrayList();
        list.add("empName");
        list.add("sex");
        list.add("empPhone");
        list.add("card");
        list.add("birthDate");
//        list.add("householdLocation");
        list.add("deptName");
        list.add("postName");
        list.add("hasUser");

        ParamUtil<EmployeeEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(employeeEntity, list);

        employeeManager.create(employeeEntity);

        response.setResult(employeeEntity.getEmpCode());
        return this.success(response);
    }


    /**
     * @return
     * @Description 修改用户
     * @Auth luming
     * @Date 2018/7/26 9:07
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody EmployeeServiceRequest request) throws IllegalAccessException {

        EmployeeServiceResponse response = new EmployeeServiceResponse();

        List<String> list = new ArrayList();
        list.add("empName");
        list.add("sex");
        list.add("empPhone");
        list.add("card");
        list.add("birthDate");
//        list.add("householdLocation");
        list.add("deptName");
        list.add("postName");
        list.add("hasUser");

        ParamUtil<EmployeeEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(), list);

        employeeManager.updateEmp(request.getEntity());

        String userCodeKey = abstractBaseUserRedisDao.createUserCodeKey(request.getEntity().getEmpCode());
        redisOperations.delete(userCodeKey);

        return this.success(response);
    }


    /**
     * @return
     * @Description 查询所有人员信息
     * @Auth xiongzhan
     * @Date 2018/7/24 13:38
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/list",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody EmployeeServiceRequest request) {

        EmployeeServiceResponse response = new EmployeeServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        EmployeeEntity employeeEntity = request.getEntity();
        if(employeeEntity != null){
            param.put("postCode", request.getEntity().getPostCode());
        }
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<EmployeeEntity> result = employeeManager.findByMap(param);

        response.setResult(result);
        return this.success(response);
    }


    /**
     * @return
     * @Description 多条件查询员工, 搜索框查询
     * @Auth luming
     * @Date 2018/7/7 13:38
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody EmployeeServiceRequest request) {

        EmployeeServiceResponse response = new EmployeeServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("keyword", request.getKeyword());
        param.put("cpyCode", CurrentContext.getCpyCode());
        param.put("isDeleted", false);

        PageResult<EmployeeEntity> result = employeeManager.findByMap(param, request.getStart(), request.getPageSize(), "id", true);

        response.setResult(result);
        return this.success(response);
    }


    /**
     * @return
     * @Description 查询人员详情
     * @Auth luming
     * @Date 2018/7/7 14:37
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/details",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> details(@RequestBody EmployeeServiceRequest request) throws IllegalAccessException {

        EmployeeServiceResponse response = new EmployeeServiceResponse();

        EmployeeEntity employeeEntity = request.getEntity();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted", false);

        ParamUtil<EmployeeEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(employeeEntity, param);

        EmployeeEntity empEntity = employeeManager.findOne(param);

        response.setResult(empEntity);
        return this.success(response);
    }


    @Autowired
    private IUserDbDao userDbDao;
    @RequestMapping(path = "/test",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> test(@RequestBody EmployeeServiceRequest request) throws IllegalAccessException {

        String[] names = {
                "付大庆","张松","后倩云","周江怡"
        };
        String[] works = {
                "00473","00360","00315","00411"
        };
        String[] secures = {
                "123456","123456","950403","654321"
        };

        int count = 0;
        List<UserEntity> list = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            String nam = names[i];
            UserEntity user = userDbDao.selectUniqueByProp("userName", names[i]);
            if (user != null) {
                user.setWorkNumber(works[i]);
                user.setPassword(MD5Utils.md5(secures[i]));

                list.add(user);
            } else {
                count += 1;
            }
        }
        System.out.println(count);
        userDbDao.batchUpdate(list);
        //插入未处理人员
        //查看mapper文件
        return null;
    }


    /**
     * @return
     * @Description 将emp表中的数据同步修改到user表中，此为测试接口，可删除
     * @Auth luming
     * @Date 2018/12/5 17:38
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/testUpdate",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> testUpdate(@RequestBody EmployeeServiceRequest request) {

        EmployeeServiceResponse response = new EmployeeServiceResponse();

        Map<String, Object> map = new HashMap<>();

        List<EmployeeEntity> empList = employeeManager.findByMap(map);

        List<UserEntity> userList = userDataManager.findByMap(map);

        for (EmployeeEntity empEntity : empList) {
            for (UserEntity userEntity : userList) {
                String empCode = empEntity.getEmpCode();
                String uEmpCode = userEntity.getEmpCode();

                if (empCode.equals(uEmpCode)) {
                    UserEntity userObj = new UserEntity();
                    userObj.setDeptName(empEntity.getDeptName());
                    userObj.setDeptCode(empEntity.getDeptCode());
                    userObj.setId(userEntity.getId());

                    userDataManager.update(userObj);
                }
            }
        }
        return this.success(response);
    }

    /**
     * @Description 员工在职离职更改，离职就关闭登录账号，在职就打开登录账号
     * @author wangchuang
     * @Date 2019/8/24 16:05
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/openCloseAccount",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> openCloseAccount(@RequestBody EmployeeServiceRequest request){
        EmployeeServiceResponse response = new EmployeeServiceResponse();

        EmployeeEntity entity = request.getEntity();

        employeeManager.openCloseAccount(entity);

        return this.success(response);

    }
}
