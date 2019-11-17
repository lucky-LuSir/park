package com.kfwy.park.controller.ati.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.customer.business.ICustomerNeedAreaManager;
import com.kfwy.park.ati.customer.entity.CustomerNeedAreaEntity;
import com.kfwy.park.controller.ati.customer.vo.CustomerNeedAreaServiceRequest;
import com.kfwy.park.controller.ati.customer.vo.CustomerNeedAreaServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 客户区域需求
 * @Auth luming
 * @Date 2018/6/15 11:03
 * @Version 1.0
 * @Param
 * @return
 */
@Controller
@RequestMapping("/needArea")
public class CustomerNeedAreaService extends SpringRestService {

    @Autowired
    private ICustomerNeedAreaManager customerNeedAreaManager;


    /**
     * @Description 新增客户所需区域
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerNeedAreaServiceRequest request){

        CustomerNeedAreaServiceResponse response = new CustomerNeedAreaServiceResponse();

        //TODO 根据业务需求对相应字段校验

        customerNeedAreaManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 非物理删除
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody CustomerNeedAreaServiceRequest request){

        CustomerNeedAreaServiceResponse response = new CustomerNeedAreaServiceResponse();

        request.getEntity().setIsDeleted(true);
        customerNeedAreaManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 更新客户所需区域
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody CustomerNeedAreaServiceRequest request){

        CustomerNeedAreaServiceResponse response = new CustomerNeedAreaServiceResponse();

        customerNeedAreaManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 查询客户所需区域
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CustomerNeedAreaServiceRequest request){

        CustomerNeedAreaServiceResponse response = new CustomerNeedAreaServiceResponse();

        CustomerNeedAreaEntity customerEntity = request.getEntity();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("id",customerEntity.getId());
        param.put("cusCode",customerEntity.getCusCode());
        param.put("cpyCode",customerEntity.getCpyCode());
        param.put("needStreet",customerEntity.getStreetName());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<CustomerNeedAreaEntity> result = customerNeedAreaManager.findByMap(param,request.getStart(),request.getPageSize(),"create_time",false);
        response.setResult(result);
        return this.success(response);
    }

}
