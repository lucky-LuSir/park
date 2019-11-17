package com.kfwy.park.controller.ati.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.customer.business.ICustomerChannelManager;
import com.kfwy.park.ati.customer.entity.CustomerChannelEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.customer.vo.CustomerChannelServiceRequest;
import com.kfwy.park.controller.ati.customer.vo.CustomerChannelServiceResponse;
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
 * Created by liuzhengyang on 2018/9/25.
 */
@RestController
@RequestMapping("/customer/channel")
public class CustomerChannelService extends SpringRestService {

    @Autowired
    private ICustomerChannelManager customerChannelManager;

    /*
     * @Description 新增客户渠道
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
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerChannelServiceRequest request) throws IllegalAccessException {
        CustomerChannelServiceResponse response = new CustomerChannelServiceResponse();

        CustomerChannelEntity customerChannelEntity = request.getEntity();

        ParamUtil<CustomerChannelEntity> paramUtil = new ParamUtil<>();

        String channelCode = CodeGenUtils.generate();
        customerChannelEntity.setChannelCode(channelCode);
        customerChannelManager.create(customerChannelEntity);

        response.setResult(channelCode);

        return this.success(response);
    }

    /*
     * @Description 查询客户渠道列表，并关联渠道单位
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
    public ResponseEntity<IServiceResponse> list(@RequestBody CustomerChannelServiceRequest request) throws IllegalAccessException {
        CustomerChannelServiceResponse response = new CustomerChannelServiceResponse();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyword", request.getKeyword());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<CustomerChannelEntity> result = customerChannelManager.queryChannelCpy(map);

        response.setResult(result);

        return this.success(response);
    }


    /**
     * @Description 渠道列表
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
    public ResponseEntity<IServiceResponse> query(@RequestBody CustomerChannelServiceRequest request) throws IllegalAccessException {
        CustomerChannelServiceResponse response = new CustomerChannelServiceResponse();

        CustomerChannelEntity customerChannelEntity = request.getEntity();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("keyword", request.getKeyword());

        ParamUtil<CustomerChannelEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(customerChannelEntity, map);
        if(!StringUtils.isEmpty(customerChannelEntity.getCreateDeptCode())){
            map.put("createDeptCode",customerChannelEntity.getCreateDeptCode());
        }

        PageResult<CustomerChannelEntity> result = customerChannelManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(result);

        return this.success(response);
    }

    /*
     * @Description 通过code查询客户渠道
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
    public ResponseEntity<IServiceResponse> details(@RequestBody CustomerChannelServiceRequest request) throws IllegalAccessException {
        CustomerChannelServiceResponse response = new CustomerChannelServiceResponse();

        if(request.getEntity() != null&& !StringUtils.isEmpty(request.getEntity().getChannelCode())){
            CustomerChannelEntity customerChannelEntity = customerChannelManager.findOne("channelCode",request.getEntity().getChannelCode());
            response.setResult(customerChannelEntity);
        }else{
            throw new BusinessException("查询渠道信息失败");
        }

        return this.success(response);
    }


    /**
     * @Description 根据渠道手机号查询渠道集合，用于验证手机号是否唯一
     * @Auth luming
     * @Date 2018/10/24 18:32
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/getList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getList(@RequestBody CustomerChannelServiceRequest request) throws IllegalAccessException {

        CustomerChannelServiceResponse response = new CustomerChannelServiceResponse();
        CustomerChannelEntity channelEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("phone", channelEntity.getPhone());
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted", false);

        List<CustomerChannelEntity> list = null;

        if (channelEntity.getPhone() != null) {
            list = customerChannelManager.findByMap(map);
        }

        response.setResult(list);
        return this.success(response);
    }


    /**
     * @Description 查询单条渠道并关联单位
     * @Auth luming
     * @Date 2018/10/29 18:06
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/queryChannelAndCpy"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryChannelAndCpy(@RequestBody CustomerChannelServiceRequest request) throws IllegalAccessException {

        CustomerChannelServiceResponse response = new CustomerChannelServiceResponse();
        CustomerChannelEntity channelEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("channelCode", channelEntity.getChannelCode());
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted", false);

       CustomerChannelEntity entity = customerChannelManager.selectChannelAndCpy(map);

        response.setResult(entity);
        return this.success(response);
    }

    @RequestMapping(path = "/update"
        ,method = RequestMethod.POST
        ,consumes = MediaType.APPLICATION_JSON_VALUE
        ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody CustomerChannelServiceRequest request) throws IllegalAccessException{
        CustomerChannelServiceResponse response = new CustomerChannelServiceResponse();
        CustomerChannelEntity entity = request.getEntity();
        if(StringUtils.isEmpty(entity.getId()) && StringUtils.isEmpty(entity.getChannelCode())){
            return this.error(response,"修改失败，传入信息有误!");
        }
        List<String> list = new ArrayList();
        list.add("channelStatus");
        list.add("userName");
        list.add("phone");
        list.add("sex");
        list.add("post");
        ParamUtil<CustomerChannelEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(entity, list);

        entity.setLastUpdateName(CurrentContext.getUserInfo().getUserName());

        customerChannelManager.update(entity);

        return this.success(response);
    }
}
