package com.kfwy.park.controller.ati.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.customer.business.ICustomerUpDownManager;
import com.kfwy.park.ati.customer.entity.CustomerUpDownEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.customer.vo.CustomerUpDownServiceRequest;
import com.kfwy.park.controller.ati.customer.vo.CustomerUpDownServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
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
 * @author wangchuang
 * @Description 客户上下架
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@RestController
@RequestMapping("/customerUpDown")
public class CustomerUpDownService extends SpringRestService {

    @Autowired
    private ICustomerUpDownManager customerUpDownManager;

    /**
     * @Description 客户上下架新增信息
     * @author wangchuang
     * @Date 2018/8/9 18:41
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerUpDownServiceRequest request) throws IllegalAccessException {
        CustomerUpDownServiceResponse response = new CustomerUpDownServiceResponse();

        //将校验字段放入校验容器中
        List<String> list = new ArrayList();
        list.add("cusCode");
        list.add("cusUpdownType");
        if ("2".equals(request.getEntity().getCusUpdownType())) {
            //下架状态
            list.add("cusDownType");
            list.add("dealPeople");
            list.add("cusDownReason");
            list.add("provinceCode");
            list.add("cityCode");
            list.add("regionCode");
            list.add("provinceName");
            list.add("cityName");
            list.add("regionName");
        }
        list.add("cusEmpCode");
        list.add("cusEmpName");
        list.add("empCode");
        list.add("empName");
        ParamUtil<CustomerUpDownEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(), list);

        customerUpDownManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 更改客户上下架信息
     * @author wangchuang
     * @Date 2018/8/9 18:43
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody CustomerUpDownServiceRequest request) throws IllegalAccessException {
        CustomerUpDownServiceResponse response = new CustomerUpDownServiceResponse();

        //将校验字段放入校验容器中
        List<String> list = new ArrayList();
        list.add("cusCode");
        list.add("cusUpdownType");
        if ("2".equals(request.getEntity().getCusUpdownType())) {
            //下架状态
            list.add("cusDownType");
            list.add("dealPeople");
            list.add("cusDownReason");
            list.add("provinceCode");
            list.add("cityCode");
            list.add("regionCode");
            list.add("provinceName");
            list.add("cityName");
            list.add("regionName");
        }
        list.add("cusEmpCode");
        list.add("cusEmpName");
        list.add("empCode");
        list.add("empName");
        ParamUtil<CustomerUpDownEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(), list);

        customerUpDownManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 查询客户上下架(分页)
     * @author wangchuang
     * @Date 2018/8/9 18:47
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CustomerUpDownServiceRequest request) throws IllegalAccessException {
        CustomerUpDownServiceResponse response = new CustomerUpDownServiceResponse();

        CustomerUpDownEntity upDownEntity = request.getEntity();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<CustomerUpDownEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(upDownEntity,map);

        PageResult<CustomerUpDownEntity> result = customerUpDownManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(result);

        return this.success(response);

    }

    /**
     * @Description 查询客户上下架
     * @author wangchuang
     * @Date 2018/8/9 18:49
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody CustomerUpDownServiceRequest request) throws IllegalAccessException {
        CustomerUpDownServiceResponse response = new CustomerUpDownServiceResponse();

        CustomerUpDownEntity upDownEntity = request.getEntity();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<CustomerUpDownEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(upDownEntity,map);

        List<CustomerUpDownEntity> result = customerUpDownManager.findByMap(map);

        response.setResult(result);

        return this.success(response);
    }

}
