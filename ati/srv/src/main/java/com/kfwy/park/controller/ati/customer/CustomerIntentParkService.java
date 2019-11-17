package com.kfwy.park.controller.ati.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.customer.business.ICustomerIntentParkManager;
import com.kfwy.park.ati.customer.entity.CustomerIntentParkEntity;
import com.kfwy.park.controller.ati.customer.vo.CustomerIntentParkServiceRequest;
import com.kfwy.park.controller.ati.customer.vo.CustomerIntentParkServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 客户意向房源
 * @Auth wangchuang
 * @Date 2018/6/5 16:42
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping(path = "/intnetPark")
public class CustomerIntentParkService extends SpringRestService {

    /**
     * 单独对意向房源进行操作的接口,根据情况使用,一般该表(子表)增删改随着客户跟进(主表)增删改走
     */


    @Autowired
    private ICustomerIntentParkManager iCustomerIntentParkManager;

    /**
     * @Description 新增客户意向房源信息
     * @Auth wangchuang
     * @Date 2018/6/5 9:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerIntentParkServiceRequest request) {

        //响应的json实体类字段
        CustomerIntentParkServiceResponse response = new CustomerIntentParkServiceResponse();
        //TODO 根据业务需求对相应字段校验

        iCustomerIntentParkManager.create(request.getEntity());

        return this.success(response);

    }

    /**
     * @Description 非物理删除
     * @Auth wangchuang
     * @Date 2018/6/5 9:27
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody CustomerIntentParkServiceRequest request) {

        //响应的json实体类字段
        CustomerIntentParkServiceResponse response = new CustomerIntentParkServiceResponse();
        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getFollowupCode())) {
            return this.error(response,"删除失败，传入信息有误!");
        }
        request.getEntity().setIsDeleted(true);
        iCustomerIntentParkManager.update(request.getEntity());

        return this.success(response);

    }

    /**
     * @Description 修改客户意向房源信息
     * @Auth wangchuang
     * @Date 2018/6/5 9:27
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody CustomerIntentParkServiceRequest request) {
        //响应的json实体类字段
        CustomerIntentParkServiceResponse response = new CustomerIntentParkServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getFollowupCode())) {
            return this.error(response,"修改失败，传入信息有误!");
        }
        iCustomerIntentParkManager.update(request.getEntity());

        return this.success(response);


    }

    /**
     * @Description 客户意向房源查询
     * @Auth wangchuang
     * @Date 2018/6/5 9:27
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CustomerIntentParkServiceRequest request) {

        CustomerIntentParkServiceResponse response = new CustomerIntentParkServiceResponse();

        //TODO 根据查询场景添加业务逻辑
        CustomerIntentParkEntity intentPark = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("id",intentPark.getId());
        param.put("deptCode",intentPark.getFollowupCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<CustomerIntentParkEntity> result = iCustomerIntentParkManager.findByMap(param,request.getStart(),request.getPageSize(),"create_time",false);

        response.setResult(result);
        return this.success(response);

    }
}
