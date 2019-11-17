package com.kfwy.park.controller.ati.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.customer.business.ICustomerRelateManager;
import com.kfwy.park.ati.customer.entity.CustomerRelateEntity;
import com.kfwy.park.controller.ati.customer.vo.CustomerRelateServiceRequest;
import com.kfwy.park.controller.ati.customer.vo.CustomerRelateServiceResponse;
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
 * @Description 客户联系人
 * @Auth luming
 * @Date 2018/6/15 11:03
 * @Version 1.0
 * @Param
 * @return
 */
@Controller
@RequestMapping("/relate")
public class CustomerRelateService extends SpringRestService {

    @Autowired
    private ICustomerRelateManager customerRelateManager;

    /**
     * @Description 添加客户联系人
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
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerRelateServiceRequest request){

        CustomerRelateServiceResponse response = new CustomerRelateServiceResponse();

        //TODO 根据业务需求对相应字段校验

        customerRelateManager.create(request.getEntity());

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
    public ResponseEntity<IServiceResponse> delete(@RequestBody CustomerRelateServiceRequest request){

        CustomerRelateServiceResponse response = new CustomerRelateServiceResponse();

        request.getEntity().setIsDeleted(true);
        customerRelateManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 更新客户联系人
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
    public ResponseEntity<IServiceResponse> update(@RequestBody CustomerRelateServiceRequest request){

        CustomerRelateServiceResponse response = new CustomerRelateServiceResponse();

        customerRelateManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 查询
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
    public ResponseEntity<IServiceResponse> query(@RequestBody CustomerRelateServiceRequest request){

        CustomerRelateServiceResponse response = new CustomerRelateServiceResponse();

        CustomerRelateEntity customerRelateEntity = request.getEntity();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("id",customerRelateEntity.getId());
        param.put("cusCode",customerRelateEntity.getCusCode());
        param.put("cpyName",customerRelateEntity.getCpyName());
        param.put("contactPhone",customerRelateEntity.getContactPhone());
        param.put("contactName",customerRelateEntity.getContactName());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<CustomerRelateEntity> result = customerRelateManager.findByMap(param,request.getStart(),request.getPageSize(),"create_time",false);
        response.setResult(result);
        return this.success(response);
    }
}
