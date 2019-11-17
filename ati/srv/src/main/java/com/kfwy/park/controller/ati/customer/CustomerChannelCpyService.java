package com.kfwy.park.controller.ati.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.customer.business.ICustomerChannelCpyManager;
import com.kfwy.park.ati.customer.entity.CustomerChannelCpyEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.customer.vo.CustomerChannelCpyServiceRequest;
import com.kfwy.park.controller.ati.customer.vo.CustomerChannelCpyServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * @Description 渠道单位
 * @Auth liuzhengyang
 * @Date 2018/9/25 10:32
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping("/customer/channel/cpy")
public class CustomerChannelCpyService extends SpringRestService {
    
    @Autowired
    private ICustomerChannelCpyManager customerChannelCpyManager;


    /*
     * @Description 新增客户渠道单位
     * @Auth liuzhengyang
     * @Date 2018/9/25 10:35
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerChannelCpyServiceRequest request) throws IllegalAccessException {
        CustomerChannelCpyServiceResponse response = new CustomerChannelCpyServiceResponse();

        CustomerChannelCpyEntity customerChannelCpyEntity = request.getEntity();

        ParamUtil<CustomerChannelCpyEntity> paramUtil = new ParamUtil<>();

        String companyCode = CodeGenUtils.generate();
        customerChannelCpyEntity.setCompanyCode(companyCode);
        customerChannelCpyManager.create(customerChannelCpyEntity);


        response.setResult(companyCode);

        return this.success(response);
    }


    /*
     * @Description 查询客户渠道单位
     * @Auth liuzhengyang
     * @Date 2018/9/25 10:35
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody CustomerChannelCpyServiceRequest request) throws IllegalAccessException {
        CustomerChannelCpyServiceResponse response = new CustomerChannelCpyServiceResponse();

        CustomerChannelCpyEntity CustomerChannelCpyEntity = request.getEntity();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<CustomerChannelCpyEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(CustomerChannelCpyEntity, map);

        List<CustomerChannelCpyEntity> result = customerChannelCpyManager.findByMap(map);

        response.setResult(result);

        return this.success(response);
    }

    /**
     * @Description 单位列表
     * @Auth luming
     * @Date 2018/9/27 16:13
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CustomerChannelCpyServiceRequest request) throws IllegalAccessException {

        CustomerChannelCpyServiceResponse response = new CustomerChannelCpyServiceResponse();
        CustomerChannelCpyEntity customerChannelCpyEntity = request.getEntity();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<CustomerChannelCpyEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(customerChannelCpyEntity, map);
        if(!StringUtils.isEmpty(customerChannelCpyEntity.getCreateDeptCode())){
            map.put("createDeptCode",customerChannelCpyEntity.getCreateDeptCode());
        }

        PageResult<CustomerChannelCpyEntity> result = customerChannelCpyManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(result);

        return this.success(response);
    }


    /*
     * @Description 通过code查询客户渠道单位
     * @Auth liuzhengyang
     * @Date 2018/9/25 10:35
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/details"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> details(@RequestBody CustomerChannelCpyServiceRequest request) throws IllegalAccessException {
        CustomerChannelCpyServiceResponse response = new CustomerChannelCpyServiceResponse();

        if(request.getEntity() != null&& StringUtils.isNotBlank(request.getEntity().getCompanyCode())){
            CustomerChannelCpyEntity CustomerChannelCpyEntity = customerChannelCpyManager.findOne("companyCode",request.getEntity().getCompanyCode());
            response.setResult(CustomerChannelCpyEntity);
        }else{
            throw new BusinessException("查询渠道单位信息失败");
        }

        return this.success(response);

    }

    /**
     * @Description 根据渠道单位名称查询渠道单位集合，用于校验单位名称是否唯一
     * @Auth luming
     * @Date 2018/10/24 18:57
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/getList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getList(@RequestBody CustomerChannelCpyServiceRequest request) throws IllegalAccessException {

        CustomerChannelCpyServiceResponse response = new CustomerChannelCpyServiceResponse();
        CustomerChannelCpyEntity channelCpyEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("channelCpyName", channelCpyEntity.getChannelCpyName());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<CustomerChannelCpyEntity> list = null;
        if (channelCpyEntity.getChannelCpyName() != null) {
            list = customerChannelCpyManager.findByMap(map);
        }
        response.setResult(list);
        return this.success(response);

    }
}
