package com.kfwy.park.controller.ati.customer;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.customer.business.ICustomerChannelFollowupManager;
import com.kfwy.park.ati.customer.entity.CustomerChannelFollowupEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.customer.vo.CustomerChannelFollowupServiceRequest;
import com.kfwy.park.controller.ati.customer.vo.CustomerChannelFollowupServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 渠道跟进
 * @Auth luming
 * @Date 2018/10/24 11:35
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping(path = "/channelFollowup")
public class CustomerChannelFollowupService extends SpringRestService {

    @Autowired
    private ICustomerChannelFollowupManager channelFollowupManager;

    /**
     * @Description 新增渠道跟进
     * @Auth luming
     * @Date 2018/10/24 11:35
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CustomerChannelFollowupServiceRequest request) throws IllegalAccessException {

        CustomerChannelFollowupServiceResponse response = new CustomerChannelFollowupServiceResponse();
        CustomerChannelFollowupEntity channelFollowup = request.getEntity();

        channelFollowup.setFollowupCode(CodeGenUtils.generate());

        List<String> list = new ArrayList();
        list.add("followupType");
        list.add("followupResult");

        ParamUtil<CustomerChannelFollowupEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(channelFollowup, list);

        channelFollowupManager.create(channelFollowup);

        return this.success(response);
    }

    /**
     * @Description 修改渠道跟进
     * @Auth luming
     * @Date 2018/10/24 11:52
     * @Version 1.0
     * @Param 
     * @return 
     */
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody CustomerChannelFollowupServiceRequest request) throws Exception {

        CustomerChannelFollowupServiceResponse response = new CustomerChannelFollowupServiceResponse();
        CustomerChannelFollowupEntity channelFollowup = request.getEntity();

        List<String> list = new ArrayList();
        list.add("followupType");
        list.add("followupResult");

        ParamUtil<CustomerChannelFollowupEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(channelFollowup, list);

        channelFollowupManager.update(channelFollowup);

        return this.success(response);
    }

    /**
     * @Description 渠道跟进查询，分页
     * @Auth luming
     * @Date 2018/10/24 11:55
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CustomerChannelFollowupServiceRequest request) throws IllegalAccessException, ParseException {

        CustomerChannelFollowupServiceResponse response = new CustomerChannelFollowupServiceResponse();
        CustomerChannelFollowupEntity channelFollowup = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("createDeptCode", channelFollowup.getCreateDeptCode());
        map.put("createCode", channelFollowup.getCreateCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<CustomerChannelFollowupEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(channelFollowup, map);

        PageResult<CustomerChannelFollowupEntity> result = channelFollowupManager.findByMap(map, request.getStart(), request.getPageSize(), "create_time", false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 渠道跟进查询，集合
     * @Auth luming
     * @Date 2018/10/24 11:55
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody CustomerChannelFollowupServiceRequest request) throws IllegalAccessException, ParseException {

        CustomerChannelFollowupServiceResponse response = new CustomerChannelFollowupServiceResponse();
        CustomerChannelFollowupEntity channelFollowup = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<CustomerChannelFollowupEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(channelFollowup, map);

        List<CustomerChannelFollowupEntity> result = channelFollowupManager.findByMap(map);

        response.setResult(result);
        return this.success(response);
    }

}
