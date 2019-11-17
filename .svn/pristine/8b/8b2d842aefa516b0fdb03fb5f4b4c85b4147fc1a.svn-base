package com.kfwy.park.controller.hrm.dept;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.hrm.dept.vo.DeptServiceRequest;
import com.kfwy.park.controller.hrm.dept.vo.DeptServiceResponse;
import com.kfwy.park.controller.hrm.employee.vo.EmployeeServiceRequest;
import com.kfwy.park.controller.hrm.employee.vo.EmployeeServiceResponse;
import com.kfwy.park.hrm.org.dept.business.IDeptManager;
import com.kfwy.park.hrm.org.dept.entity.DeptEntity;
import com.kfwy.park.hrm.org.employee.business.IEmployeeManager;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 部门管理
 * @Auth wangchuang
 * @Date 2018/5/30 10:44
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/dept")
public class DeptService extends SpringRestService {

    @Autowired
    private IDeptManager deptManager;
    @Autowired
    private IEmployeeManager empManager;

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 新增部门信息
     * @Auth wangchuang
     * @Date 2018/5/30 10:45
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody DeptServiceRequest request) throws IllegalAccessException {

        DeptServiceResponse response = new DeptServiceResponse();

        List<String> list = new ArrayList<>();
        list.add("deptName");
        ParamUtil<Object> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(), list);

        deptManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 非物理删除
     * @Auth wangchuang
     * @Date 2018/5/30 10:45
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody DeptServiceRequest request) {

        DeptServiceResponse response = new DeptServiceResponse();


        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getCityCode())) {
            return this.error(response, "删除失败，传入信息有误!");
        }
        request.getEntity().setIsDeleted(true);
        deptManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 修改部门信息
     * @Auth wangchuang
     * @Date 2018/5/30 10:45
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody DeptServiceRequest request) throws IllegalAccessException {

        DeptServiceResponse response = new DeptServiceResponse();

        List<String> list = new ArrayList<>();
        list.add("deptName");
        ParamUtil<Object> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(), list);

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getCityCode())) {
            return this.error(response, "修改失败，传入信息有误!");
        }
        deptManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 部门查询
     * @Auth wangchuang
     * @Date 2018/5/30 10:45
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody DeptServiceRequest request) throws IllegalAccessException {

        DeptServiceResponse response = new DeptServiceResponse();

        DeptEntity deptEntity = request.getEntity();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<DeptEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(deptEntity, param);

        PageResult<DeptEntity> result = deptManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);
        response.setResult(result);

        return this.success(response);
    }


    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody DeptServiceRequest request) throws IllegalAccessException {

        DeptServiceResponse response = new DeptServiceResponse();

        DeptEntity deptEntity = request.getEntity();

        Map<String, Object> param = new HashMap<String, Object>();

        ParamUtil<DeptEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(deptEntity, param);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<DeptEntity> result = deptManager.findByMap(param);
        response.setResult(result);

        return this.success(response);
    }


    /**
     * @Description 根据deptCode查询部门
     * @Auth
     * @Date 2018/10/18 14:49
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/findByCode"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> findByCode(@RequestBody DeptServiceRequest request) throws IllegalAccessException {

        DeptServiceResponse response = new DeptServiceResponse();

        DeptEntity deptEntity = request.getEntity();
        DeptEntity dept = deptManager.findOne("deptCode",deptEntity.getDeptCode());
        response.setResult(dept);

        return this.success(response);
    }

    /**
     * @return
     * @Description 查询部门列表,添加多个数据
     * @Auth luming
     * @Date 2018/7/5 17:28
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/queryDeptList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryDeptList(@RequestBody DeptServiceRequest request) throws IllegalAccessException {

        DeptServiceResponse response = new DeptServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<DeptEntity> deptList = deptManager.findByMap(param, "id", false);

        List<EmployeeEntity> empAllList = empManager.findByMap(param);

        PageResult<EmployeeEntity> empList = empManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);

        List<DeptEntity> deptTree = deptManager.deptValueTree();

        Map<String, Object> result = new HashMap<>();
        result.put("deptList", deptList);
        result.put("empAllList", empAllList);
        result.put("empList", empList);
        result.put("deptTree",deptTree);

        response.setResult(result);

        return this.success(response);
    }

    /**
     * @return
     * @Description 条件查询员工
     * @Auth xiongzhan
     * @Date 2018/7/9 13:38
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/selectEmpByDeptCode",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectEmpByDeptCode(@RequestBody EmployeeServiceRequest request) throws IllegalAccessException {

        EmployeeServiceResponse response = new EmployeeServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<EmployeeEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(request.getEntity(), param);

        PageResult<EmployeeEntity> result = empManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 用于element-ui Cascader组件，以级联的形式展示部门信息
     * @author luming
     * @Date 2019/6/28 10:40
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/cascaderDeptTree",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cascaderDeptTree(@RequestBody DeptServiceRequest request) throws IllegalAccessException {
        DeptServiceResponse response = new DeptServiceResponse();

        List<DeptEntity> list = deptManager.deptValueTree();
        response.setResult(list);
        return this.success(response);
    }
}
