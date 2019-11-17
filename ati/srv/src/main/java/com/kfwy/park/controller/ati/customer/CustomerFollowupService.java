package com.kfwy.park.controller.ati.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.customer.business.ICustomerFollowupManager;
import com.kfwy.park.ati.customer.business.ICustomerIntentParkManager;
import com.kfwy.park.ati.customer.business.ICustomerManager;
import com.kfwy.park.ati.customer.entity.CustomerEntity;
import com.kfwy.park.ati.customer.entity.CustomerFollowupEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.customer.vo.CustomerFollowupServiceRequest;
import com.kfwy.park.controller.ati.customer.vo.CustomerFollowupServiceResponse;
import com.kfwy.park.hrm.org.employee.business.IEmployeeManager;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.*;

/**
 * @Description 客户跟进信息管理
 * @Auth wangchuang
 * @Date 2018/5/30 19:54
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping(path = "/followup")
public class CustomerFollowupService extends SpringRestService {

    @Autowired
    private ICustomerFollowupManager customerFollowupManager;

    @Autowired
    private ICustomerIntentParkManager customerIntentParkManager;

    @Autowired
    private ICustomerManager customerManager;

    @Autowired
    private IEmployeeManager employeeManager;

    @Autowired
    private IParkManager parkManager;

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 新增客户跟进管理信息
     * @Auth wangchuang
     * @Date 2018/5/30 9:16
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerFollowupServiceRequest request) throws IllegalAccessException {

        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse();
        CustomerFollowupEntity customerFollowupEntity = request.getEntity();

        // 根据业务需求对相应字段校验
        List<String> list = new ArrayList();

        // 跟进类型为1（电话拜访）需校验的字段
        if (customerFollowupEntity.getFollowupType().equals("1")) {
            list.add("followupType");
            list.add("cusStatus");
            list.add("followupRes");
        } else {
            list.add("followupType");
            list.add("cusStatus");
            list.add("followupTime");
            list.add("pkCode");
            list.add("followupRes");
        }

        ParamUtil<CustomerFollowupEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(customerFollowupEntity, list);
        if(!StringUtils.isEmpty(customerFollowupEntity.getPkCode())){
            Map<String,Object> map = new HashMap<>();
            map.put("pkCode",customerFollowupEntity.getPkCode());
            ParkEntity parkEntity = parkManager.findOne(map);
            customerFollowupEntity.setPkName(parkEntity.getPkName());
        }
        customerFollowupManager.create(customerFollowupEntity);

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 非物理删除
     * @Auth wangchuang
     * @Date 2018/5/30 9:16
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody CustomerFollowupServiceRequest request) throws Exception {

        //响应的json实体类字段
        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse();
        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getFollowupCode())) {
            return this.error(response, "删除失败，传入信息有误!");
        }

        customerFollowupManager.followupDelete(request.getEntity());
        return this.success(response);

    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 修改客户跟进信息
     * @Auth wangchuang
     * @Date 2018/5/30 9:18
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody CustomerFollowupServiceRequest request) throws Exception {
        //响应的json实体类字段
        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse();
        CustomerFollowupEntity customerFollowupEntity = request.getEntity();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getFollowupCode())) {
            return this.error(response, "修改失败，传入信息有误!");
        }

        // 根据业务需求对相应字段校验
        List<String> list = new ArrayList();

        // 跟进类型为1（电话拜访）需校验的字段
        if (customerFollowupEntity.getFollowupType().equals("1")) {
            list.add("followupType");
            list.add("cusStatus");
            list.add("followupRes");
        } else {
            list.add("followupType");
            list.add("cusStatus");
            list.add("followupTime");
            list.add("pkCode");
            list.add("followupRes");
        }

        ParamUtil<CustomerFollowupEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(customerFollowupEntity, list);

        customerFollowupManager.followupUpdate(customerFollowupEntity);

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 客户跟进查询
     * @Auth wangchuang
     * @Date 2018/5/30 9:19
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CustomerFollowupServiceRequest request) throws IllegalAccessException, ParseException {

        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse();

        CustomerFollowupEntity cusFollowup = request.getEntity();

        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<CustomerFollowupEntity> paramUtil = new ParamUtil<CustomerFollowupEntity>();
        paramUtil.objectToMap(cusFollowup, param);
        param.put("keyword", request.getKeyword());
        if (request.getFollowupTimeStart() != null && request.getFollowupTimeEnd() != null) {
            Date followupTimeStart = request.getFollowupTimeStart();
            Date followupTimeEnd = request.getFollowupTimeEnd();

            param.put("followupTimeStart", DateFormatUtil.dayBegin(followupTimeStart));
            param.put("followupTimeEnd", DateFormatUtil.dayEnd(followupTimeEnd));
        }

        if (cusFollowup != null) {
            if (!"".equals(cusFollowup.getCreateDeptCode())) {
                param.put("createDeptCode", cusFollowup.getCreateDeptCode());
            }
        }
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<CustomerFollowupEntity> result = customerFollowupManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);
        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询一条跟进的详细信息
     * @Auth wangchuang
     * @Date 2018/5/30 9:19
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    /*@RequestMapping(path = "/queryOneFollowup"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryOneFollowupInfo(@RequestBody CustomerFollowupServiceRequest request) {

        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse();

        String followupCode = request.getEntity().getFollowupCode();
        List<CustomerFollowupEntity> result = customerFollowupManager.selectOneFollowup(followupCode);

        response.setResult(result);
        return this.success(response);

    }*/

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 客户详情中查询客户跟进记录
     * @Auth wangchuang
     * @Date 2018/7/10 9:01
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/queryCusFollowup"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryCusFollowup(@RequestBody CustomerFollowupServiceRequest request) throws IllegalAccessException {

        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse();

        CustomerFollowupEntity followEntity = request.getEntity();

        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<CustomerFollowupEntity> paramUtil = new ParamUtil<CustomerFollowupEntity>();
        paramUtil.objectToMap(followEntity, param);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        //根据客户编码cusCode查询出改客户所有带看记录,分页显示
        PageResult<CustomerFollowupEntity> result = customerFollowupManager.findByMap(param, request.getStart(), request.getPageSize(), "id", false);

        /**
         * 获取followupCode去查询意向房源
         */
//        List<CustomerFollowupEntity> followRes = result.getData();
//        Map<String,Object> parkParam = new HashMap<String,Object>();
//        String followCode = "";
//        //意向房源集合
//        List<CustomerIntentParkEntity> byMap = new ArrayList<CustomerIntentParkEntity>();
//        if (followRes != null) {
//            for (int i=0;i<followRes.size();i++){
//                followCode = followRes.get(i).getFollowupCode();
//                parkParam.put("followupCode",followCode);
//                byMap = customerIntentParkManager.findByMap(parkParam);
//                followRes.get(i).setCusIntentParkList(byMap);
//            }
//        }
        response.setResult(result);
        return this.success(response);
    }


    /**
     * @return
     * @Description 看板：我的数据，查询本月意向客户
     * @Auth luming
     * @Date 2018/8/14 15:37
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/myData"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> myData(@RequestBody CustomerFollowupServiceRequest request) throws Exception {

        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse();

        // 本月第一天
        Calendar cf = Calendar.getInstance();
        cf.get(Calendar.YEAR);
        cf.get(Calendar.MONTH + 1);
        cf.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = cf.getTime();

        // 本月最后一天
        Calendar cl = Calendar.getInstance();
        cl.get(Calendar.YEAR);
        cl.get(Calendar.MONTH + 1);
        cl.set(Calendar.DAY_OF_MONTH, cl.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDate = cl.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("createCode", CurrentContext.getUserInfo().getUserCode());
        map.put("cusStatus", "2");// 谈判中
        map.put("followupTimeStart", DateFormatUtil.dayBegin(firstDate));
        map.put("followupTimeEnd", DateFormatUtil.dayEnd(lastDate));
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        int count = customerFollowupManager.queryThisMonthFollowup(map);

        response.setResult(count);
        return this.success(response);
    }

    /**
     * @return
     * @Description 根据cusCode查询跟进列表
     * @Auth luming
     * @Date 2018/9/18 10:15
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/followupList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> followupList(@RequestBody CustomerFollowupServiceRequest request) throws Exception {

        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse();
        CustomerFollowupEntity followupEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<CustomerFollowupEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(followupEntity, map);

        List<CustomerFollowupEntity> entityList = customerFollowupManager.findByMap(map);

        response.setResult(entityList);
        return this.success(response);
    }


    /**
     * @Description 批量修改跟进列表cus_channel，临时接口，后续要删除
     * @Auth luming
     * @Date 2018/10/22 13:42
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/testUpdate"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> testUpdate(@RequestBody CustomerFollowupServiceRequest request) throws Exception {

        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse();

        CustomerFollowupEntity followupEntity = new CustomerFollowupEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<CustomerFollowupEntity> flist = customerFollowupManager.findByMap(map);
        List<CustomerEntity> clist = customerManager.findByMap(map);

        for (int i = 0; i < flist.size(); i++) {
            for (int j = 0; j < clist.size(); j++) {
                String fCode = flist.get(i).getCusCode();
                String cCode = clist.get(j).getCusCode();
                if (fCode.equals(cCode)) {
                    followupEntity.setCusCode(clist.get(j).getCusCode());
                    followupEntity.setCusChannel(clist.get(j).getDitch());
                }
            }
            followupEntity.setId(flist.get(i).getId());
            customerFollowupManager.update(followupEntity);
        }
        return this.success(response);
    }


    /**
     * @Description 批量修改跟进列表create_dept_code，临时接口，后续要删除
     * @Auth luming
     * @Date 2018/10/22 13:42
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/updateDeptCode"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> updateDeptCode(@RequestBody CustomerFollowupServiceRequest request) throws Exception {

        CustomerFollowupServiceResponse response = new CustomerFollowupServiceResponse();

        CustomerFollowupEntity followupEntity = new CustomerFollowupEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<EmployeeEntity> empList = employeeManager.findByMap(map);
        List<CustomerFollowupEntity> cusFollowupList = customerFollowupManager.findByMap(map);

        for (int i = 0; i < cusFollowupList.size(); i++) {
            for (int j = 0; j < empList.size(); j++) {
                String createCode = cusFollowupList.get(i).getCreateCode();
                String empCode = empList.get(j).getEmpCode();
                if (createCode.equals(empCode)) {

                    String deptCode = empList.get(j).getDeptCode();
                    String deptName = empList.get(j).getDeptName();
                    Long id = cusFollowupList.get(i).getId();
                    String followupCode = cusFollowupList.get(i).getFollowupCode();

                    followupEntity.setCreateDeptCode(deptCode);
                    followupEntity.setCreateDeptName(deptName);
                    followupEntity.setId(id);
                    followupEntity.setFollowupCode(followupCode);

                    customerFollowupManager.update(followupEntity);
                    break;
                }
            }
        }


        return this.success(response);
    }




}
