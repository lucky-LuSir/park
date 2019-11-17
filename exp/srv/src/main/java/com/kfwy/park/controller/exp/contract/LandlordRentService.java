package com.kfwy.park.controller.exp.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.exp.contract.vo.LandlordRentServiceRequest;
import com.kfwy.park.controller.exp.contract.vo.LandlordRentServiceResponse;
import com.kfwy.park.exp.contract.business.ILandlordRentManager;
import com.kfwy.park.exp.contract.entity.LandlordRentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * Created by gengtao on 2019/4/18.
 */
@Component
@RequestMapping("/landlordRent")
public class LandlordRentService extends SpringRestService {
    @Autowired
    private ILandlordRentManager landlordRentManager;

    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody LandlordRentServiceRequest request) throws IllegalAccessException {

        LandlordRentServiceResponse response = new LandlordRentServiceResponse();
        LandlordRentEntity entity = request.getEntity();
        Date costStartTime = DateFormatUtil.dayBegin(entity.getCostStartTime());
        Date costEndTime = DateFormatUtil.dayEnd(entity.getCostEndTime());
        entity.setCostStartTime(costStartTime);
        entity.setCostEndTime(costEndTime);
        landlordRentManager.create(entity);

        return this.success(response);
    }

    /**
     * @Description 租金应收修改,唯一在修改按钮中调用
     * @Auth xiongzhan
     * @Date 2018/6/4 10:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody LandlordRentServiceRequest request) throws IllegalAccessException {

        LandlordRentServiceResponse response = new LandlordRentServiceResponse();
        LandlordRentEntity entity = request.getEntity();
        Date costStartTime = DateFormatUtil.dayBegin(entity.getCostStartTime());
        Date costEndTime = DateFormatUtil.dayEnd(entity.getCostEndTime());
        entity.setCostStartTime(costStartTime);
        entity.setCostEndTime(costEndTime);

        landlordRentManager.update(entity);
        return this.success(response);

    }


    /**
     * @Description 租金应收查询
     * @Auth xiongzhan
     * @Date 2018/6/4 10:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/list",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody LandlordRentServiceRequest request) throws IllegalAccessException {
        LandlordRentServiceResponse response = new LandlordRentServiceResponse();

        LandlordRentEntity entity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil paramUtil = new ParamUtil();
        paramUtil.objectToMap(entity,param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<LandlordRentEntity> result = landlordRentManager.findByMap(param,"cost_start_time",true);

        response.setResult(result);

        return this.success(response);
    }


    /**
     * @Description 非物理删除
     * @Auth xiongzhan
     * @Date 2018/6/4 10:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody LandlordRentServiceRequest request) {

        LandlordRentServiceResponse response = new LandlordRentServiceResponse();

        if(StringUtils.isEmpty(request.getEntity().getRentCode())) {
            throw new BusinessException("租金信息删除操作传入参数有误");
        }
        request.getEntity().setIsDeleted(true);
        landlordRentManager.update(request.getEntity());

        return this.success(response);

    }

    @RequestMapping(path = "/pay"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> pay(@RequestBody LandlordRentServiceRequest request) {

        LandlordRentServiceResponse response = new LandlordRentServiceResponse();
        LandlordRentEntity landlordRentEntity = request.getEntity();

        landlordRentManager.pay(landlordRentEntity);
        return this.success(response);
    }

}
