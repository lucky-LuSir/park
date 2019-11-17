package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IRentReceiptManager;
import com.kfwy.park.ati.contract.entity.RentReceiptEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.contract.vo.RentReceiptRequest;
import com.kfwy.park.controller.ati.contract.vo.RentReceiptResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 租金应收列表
 * @Auth xiongzhan
 * @Date 2018/6/4 10:25
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping("rentReceipt")
public class RentReceiptService extends SpringRestService {
    @Autowired
    private IRentReceiptManager rentReceiptManager;

    /**
     * @Description 租金应收新增
     * @Auth xiongzhan
     * @Date 2018/6/4 10:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody RentReceiptRequest request) throws IllegalAccessException {

        RentReceiptResponse response = new RentReceiptResponse();

        RentReceiptEntity rentEntity = request.getEntity();

        //将必填字段放入容器
        List<String> params = new ArrayList<>();
        params.add("ctCode");
        params.add("pkCode");
        params.add("rentStartTime");
        params.add("rentEndTime");
        params.add("receivingDate");
        params.add("receivableRent");
        params.add("propertyPrice");
        if(rentEntity.getHasTax()) {
            params.add("taxPoint");
        }
        if(rentEntity.getProHasTax()) {
            params.add("proTaxPoint");
        }
        params.add("totalPrice");

        ParamUtil<RentReceiptEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(rentEntity, params);

        rentReceiptManager.create(request.getEntity());

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
    public ResponseEntity<IServiceResponse> update(@RequestBody RentReceiptRequest request) throws IllegalAccessException {

        RentReceiptResponse response = new RentReceiptResponse();
        RentReceiptEntity rentEntity = request.getEntity();
        Date rentStartTime = DateFormatUtil.dayBegin(rentEntity.getRentStartTime());
        Date rentEndTime = DateFormatUtil.dayEnd(rentEntity.getRentEndTime());
        Date receivingDate = DateFormatUtil.dayEnd(rentEntity.getReceivingDate());

        rentEntity.setRentStartTime(rentStartTime);
        rentEntity.setRentEndTime(rentEndTime);
        rentEntity.setReceivingDate(receivingDate);

        //将必填字段放入容器
        List<String> params = new ArrayList<>();
        params.add("ctCode");
        params.add("pkCode");
        params.add("rentStartTime");
        params.add("rentEndTime");
        params.add("receivingDate");
        params.add("receivableRent");
        params.add("realRent");
        params.add("paidPrice");
        params.add("hasTax");
        if (rentEntity.getHasTax()) {
            params.add("taxPoint");
        }
        if(rentEntity.getProHasTax()) {
            params.add("proTaxPoint");
        }
        params.add("payStatus");
        ParamUtil<RentReceiptEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(rentEntity,params);


        rentReceiptManager.update(rentEntity);

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
    public ResponseEntity<IServiceResponse> query(@RequestBody RentReceiptRequest request){
        RentReceiptResponse response = new RentReceiptResponse();

        RentReceiptEntity rentReceiptEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("ctCode",rentReceiptEntity.getCtCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<RentReceiptEntity> result = rentReceiptManager.findByMap(param,"rent_start_time",true);

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
    public ResponseEntity<IServiceResponse> delete(@RequestBody RentReceiptRequest request) {

        RentReceiptResponse response = new RentReceiptResponse();

        if(StringUtils.isEmpty(request.getEntity().getRentCode())) {
            throw new BusinessException("租金信息删除操作传入参数有误");
        }

        request.getEntity().setIsDeleted(true);
        rentReceiptManager.update(request.getEntity());

        return this.success(response);

    }

    /**
     * @Description 租金应收缴费接口
     * @Auth xiongzhan
     * @Date 2018/6/4 10:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/pay",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> pay(@RequestBody RentReceiptRequest request){

        RentReceiptResponse response = new RentReceiptResponse();

        rentReceiptManager.pay(request.getEntity());

        return this.success(response);

    }


    /**
     * @Description 根据租金编号查询本期租金
     * @Auth luming
     * @Date 2018/8/8 19:41
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/thisRent"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> thisRent(@RequestBody RentReceiptRequest request) {

        RentReceiptResponse response = new RentReceiptResponse();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("rentCode",request.getEntity().getRentCode());
        param.put("isDeleted",false);

        RentReceiptEntity result = rentReceiptManager.findOne(param);

        response.setResult(result);

        return this.success(response);

    }

}




