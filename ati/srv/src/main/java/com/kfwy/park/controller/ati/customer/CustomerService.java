package com.kfwy.park.controller.ati.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.customer.business.ICustomerManager;
import com.kfwy.park.ati.customer.entity.CustomerEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.customer.vo.CustomerServiceRequest;
import com.kfwy.park.controller.ati.customer.vo.CustomerServiceResponse;
import com.kfwy.park.hrm.org.employee.business.IEmployeeManager;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 客户管理模块
 * @Auth luming
 * @Date 2018/6/14 14:48
 * @Version 1.0
 * @Param
 * @return
 */
@Controller
@RequestMapping("/customer")
public class CustomerService extends SpringRestService {

    @Autowired
    private ICustomerManager customerManager;

    @Autowired
    private IEmployeeManager employeeManager;

    @Autowired
    private IParkManager parkManager;
    /*
     * @Description 客户新增
     * @Auth luming
     * @Date 2018/6/14 14:44
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerServiceRequest request) throws IllegalAccessException {

        CustomerServiceResponse response = new CustomerServiceResponse();

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity = request.getEntity();


//        // 设置负责人empCode和empName
//        String empCode = CurrentContext.getUserInfo().getEmpCode();
//        customerEntity.setEmpCode(CurrentContext.getUserInfo().getEmpCode());
//        customerEntity.setEmpName(CurrentContext.getUserInfo().getEmpName());

        // 根据业务需求对相应字段校验
        List<String> list = new ArrayList();
        list.add("customerType");
        list.add("companyName");

        list.add("trade");
        list.add("companyProduct");
        list.add("mainContactName");

        list.add("mainContactSex");
        list.add("mainContactPhone");
        list.add("ditch");

        list.add("mainArea");
        list.add("floorHeightAsk");
        list.add("electricAsk");

        list.add("entryTime");
        list.add("floorAsk");

        list.add("moreDepict");

        ParamUtil<CustomerEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(customerEntity,list);
        if(StringUtils.isNotBlank(customerEntity.getPkCode())){
            Map<String,Object> map = new HashMap<>();
            map.put("pkCode",customerEntity.getPkCode());
            ParkEntity parkEntity = parkManager.findOne(map);
            customerEntity.setPkName(parkEntity.getPkName());
        }

        String cusCode = CodeGenUtils.generate();
        customerEntity.setCusCode(cusCode);
        String ownerDeptCode = CurrentContext.getUserInfo().getOwnerDeptCode();
        customerManager.create(customerEntity);

        Map<String, Object> map = new HashMap<>();
        map.put("cusCode", cusCode);
        map.put("ditch", customerEntity.getDitch());
        map.put("mainContactName", customerEntity.getMainContactName());
        map.put("mainContactPhone", customerEntity.getMainContactPhone());

        response.setResult(map);
        return this.success(response);
    }


    /*
     * @Description 非物理删除
     * @Auth luming
     * @Date 2018/6/14 14:48
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse();

        request.getEntity().setIsDeleted(true);
        customerManager.update(request.getEntity());

        return this.success(response);
    }


    /*
     * @Description 更新客户
     * @Auth luming
     * @Date 2018/6/14 14:48
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody CustomerServiceRequest request) throws IllegalAccessException {

        CustomerServiceResponse response = new CustomerServiceResponse();

        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity  = request.getEntity();
        // 根据业务需求对相应字段校验
        List<String> list = new ArrayList();
        list.add("customerType");
        list.add("companyName");
        list.add("trade");
        list.add("companyProduct");
        list.add("mainContactName");
        list.add("mainContactSex");
        list.add("mainContactPhone");
        list.add("ditch");
        list.add("mainArea");
        list.add("floorHeightAsk");
        list.add("electricAsk");
        list.add("entryTime");
        list.add("floorAsk");
        list.add("moreDepict");

        ParamUtil<CustomerEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(customerEntity,list);
        if (!"10".equals(customerEntity.getDitch()) && !"7".equals(customerEntity.getDitch()) && !"8".equals(customerEntity.getDitch())) {
            customerEntity.setChannelCode(null);
        }
        customerManager.update(customerEntity);

        return this.success(response);
    }


    /*
     * @Description 查询客户
     * @Auth luming
     * @Date 2018/6/14 14:47
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CustomerServiceRequest request) throws IllegalAccessException {
        CustomerServiceResponse response = new CustomerServiceResponse();

        CustomerEntity customerEntity = request.getEntity();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<CustomerEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(customerEntity,map);

        PageResult<CustomerEntity> result = customerManager.findByMap(map, request.getStart(), request.getPageSize(),"id",false);

        response.setResult(result);
        return this.success(response);
    }


    /*
     * @Description 查询客户
     * @Auth luming
     * @Date 2018/6/14 14:47
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody CustomerServiceRequest request) {
        CustomerServiceResponse response = new CustomerServiceResponse();

        CustomerEntity customerEntity = request.getEntity();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("keyword", request.getKeyword());
        if(customerEntity != null){
            param.put("id", customerEntity.getId());
            param.put("cusCode", customerEntity.getCusCode());
            param.put("companyProduct", customerEntity.getCompanyProduct());
        }
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<CustomerEntity> result =  customerManager.findByMap(param, "id", false);

        response.setResult(result);
        return this.success(response);
    }


    /*
     * @Description 按照cusCode查询客户详情,并关联客户联系人,客户需求区域
     * @Auth luming
     * @Date 2018/6/14 14:47
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/details"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> details(@RequestBody CustomerServiceRequest request) throws Exception {

        CustomerServiceResponse response = new CustomerServiceResponse();

        CustomerEntity customerEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<CustomerEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(customerEntity,map);

        CustomerEntity details = customerManager.details(map);

        response.setResult(details);
        return this.success(response);
    }


    /*
     * @Description 仅查询客户表详情
     * @Auth liuzhengyang
     * @Date 2018/7/10 10:05
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/detailsOnlyTable"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detailsOnlyTable(@RequestBody CustomerServiceRequest request) throws Exception {

        CustomerServiceResponse response = new CustomerServiceResponse();

        CustomerEntity customerEntity = request.getEntity();

        CustomerEntity details = customerManager.findOne("cusCode", customerEntity.getCusCode());

        response.setResult(details);
        return this.success(response);
    }


    /*
     * @Description 通过搜索框查询客户
     * @Auth luming
     * @Date 2018/6/14 14:47
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/selectByKeyword"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectByKeyword(@RequestBody CustomerServiceRequest request) throws IllegalAccessException {

        CustomerServiceResponse response = new CustomerServiceResponse();

        CustomerEntity customerEntity = request.getEntity();

        Map<String,Object> map = new HashMap<String,Object>();
        if(request.getStartDate() != null && request.getEndDate() != null){
            Date dayBegin = DateFormatUtil.dayBegin(request.getStartDate());
            Date dayEnd = DateFormatUtil.dayEnd(request.getEndDate());
            map.put("createTimeStart",dayBegin);
            map.put("createTimeEnd",dayEnd);
        }
        //大区筛选
        String territoryType = request.getTerritoryType();
        if(!StringUtils.isEmpty(territoryType)){
            map.put("territoryType",territoryType);
        }
        map.put("keyword",request.getKeyword());
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<CustomerEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(customerEntity,map);

        PageResult<CustomerEntity> page = customerManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(page);
        return this.success(response);
    }


    /**
     * @Description 根据客户状态查询
     * @Auth luming
     * @Date 2018/7/6 8:39
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/queryByStatus"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryByStatus(@RequestBody CustomerServiceRequest request) throws IllegalAccessException {
        CustomerServiceResponse response = new CustomerServiceResponse();

        CustomerEntity customerEntity = request.getEntity();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("cusStatus", customerEntity.getCusStatus());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<CustomerEntity> result = customerManager.findByMap(param, request.getStart(), request.getPageSize(),"id",true);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 校驗客戶電話是否唯一
     * @Auth luming
     * @Date 2018/10/25 14:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/checkCusPhone"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryPhoneFlag(@RequestBody CustomerServiceRequest request) throws Exception {

        CustomerServiceResponse response = new CustomerServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("mainContactPhone", request.getEntity().getMainContactPhone());
        map.put("isDeleted", false);

        List<CustomerEntity> list = null;

        if (!request.getEntity().getMainContactPhone().isEmpty()) {
            list = customerManager.findByMap(map);
        }
        response.setResult(list);
        return this.success(response);
    }

    /**
     * @Description 客户来源渠道报表统计
     * @Auth luming
     * @Date 2018/10/25 14:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/ditchReport"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> ditchReport(@RequestBody CustomerServiceRequest request) throws Exception {

        CustomerServiceResponse response = new CustomerServiceResponse();

        CustomerEntity customerEntity = request.getEntity();

        Date date = new Date();
        Date firstDate = DateFormatUtil.monthBegin(date);// 本月第一天
        Date endDate = DateFormatUtil.monthEnd(date);// 本月最后一天

        Date dayBegin = null;
        Date dayEnd = null;

        if (request.getStartDate() == null && request.getEndDate() == null) {
            dayBegin = DateFormatUtil.dayBegin(firstDate);
            dayEnd = DateFormatUtil.dayEnd(endDate);
        } else {
            dayBegin = DateFormatUtil.dayBegin(request.getStartDate());
            dayEnd = DateFormatUtil.dayEnd(request.getEndDate());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("createTimeStart", dayBegin);
        map.put("createTimeEnd", dayEnd);
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        map.put("followupType","2");
        List<CustomerEntity> countList = customerManager.queryDitchCount(map);

        Map<String, Object> result = new HashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String sdate = sdf.format(firstDate);
        String edate = sdf.format(endDate);
        result.put("countList", countList);
        result.put("firstDate", sdate);
        result.put("endDate", edate);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询每个职员招到的客户数
     * @Auth gengtao
     * @Date 2019/3/11 14:12
     * @Version 1.0
     * @return
     */
    @RequestMapping(path = "/empCount", method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> empCount(@RequestBody CustomerServiceRequest request) throws Exception{
        CustomerServiceResponse response = new CustomerServiceResponse();
        CustomerEntity customerEntity = request.getEntity();
        Date date = new Date();
        Date firstDate = DateFormatUtil.monthBegin(date);// 本月第一天
        Date endDate = DateFormatUtil.monthEnd(date);// 本月最后一天

        Date dayBegin = null;
        Date dayEnd = null;

        if (request.getStartDate() == null && request.getEndDate() == null) {
            dayBegin = DateFormatUtil.dayBegin(firstDate);
            dayEnd = DateFormatUtil.dayEnd(endDate);
        } else {
            dayBegin = DateFormatUtil.dayBegin(request.getStartDate());
            dayEnd = DateFormatUtil.dayEnd(request.getEndDate());
        }

        Map<String, Object> map = new HashMap<>();
        map.put("start",request.getStart());
        map.put("pageSize",request.getPageSize());
        map.put("createTimeStart", dayBegin);
        map.put("createTimeEnd", dayEnd);
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<CustomerEntity> result = customerManager.queryEmpCount(map,request.getStart(),request.getPageSize());

        /*Map<String, Object> result = new HashMap<>();
        result.put("countList", countList);
        result.put("firstDate", firstDate);
        result.put("endDate", endDate);*/

        response.setResult(result);
        return this.success(response);

    }

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:20:21
     *@Description 统计各园区客户数
     *@Return
     **/
    @RequestMapping(path = "/analysisByPk", method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> analysis(@RequestBody CustomerServiceRequest request) throws Exception {
        CustomerServiceResponse response = new CustomerServiceResponse();
        ParamUtil<CustomerEntity> paramUtil = new ParamUtil();
        CustomerEntity customerEntity = request.getEntity();
        Map<String, Object> param = new HashMap<>();
        paramUtil.objectToMap(customerEntity, param);
        param.put("isDeleted",false);
        param.put("cpyCode",CurrentContext.getCpyCode());
        List<CustomerEntity> list = customerManager.analysisByPk(param);
        response.setResult(list);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-05-20 09:20:21
     *@Description 统计各行业客户数
     *@Return
     **/
    @RequestMapping(path = "/analysisByTrade", method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> analysisByTrade(@RequestBody CustomerServiceRequest request) throws Exception {
        CustomerServiceResponse response = new CustomerServiceResponse();
        ParamUtil<CustomerEntity> paramUtil = new ParamUtil();
        CustomerEntity customerEntity = request.getEntity();
        Map<String, Object> param = new HashMap<>();
        paramUtil.objectToMap(customerEntity, param);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<CustomerEntity> list = customerManager.analysisByTrade(param);
        response.setResult(list);
        return this.success(response);
    }


    /**
     *@Author gengtao
     *@Date 2019-05-20 09:20:21
     *@Description 统计分析
     *@Return
     **/
    @RequestMapping(path = "/status", method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> status(@RequestBody CustomerServiceRequest request) throws Exception {
        CustomerServiceResponse response = new CustomerServiceResponse();
        Map<String,Object> map = new HashMap<>();
        map.put("isDeleted",false);
        map.put("cpyCode",CurrentContext.getCpyCode());
        List<CustomerEntity> list = customerManager.selectStatus(map);
        response.setResult(list);
        return this.success(response);
    }
}
