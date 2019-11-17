package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IParkingReceiptManager;
import com.kfwy.park.ati.contract.entity.ParkingReceiptEntity;
import com.kfwy.park.controller.ati.contract.vo.ParkingReceiptServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.ParkingReceiptServiceResponse;
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
 * @Description 合同:停车费缴收管理
 * @Auth wangchuang
 * @Date 2018/6/8
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping(path = "/parkReceipt")
public class ParkingReceiptService extends SpringRestService {

    @Autowired
    private IParkingReceiptManager parkingReceiptManager;

    /**
     * @Description 新增合同:停车费缴收信息
     * @Auth wangchuang
     * @Date 2018/6/8 10:07
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ParkingReceiptServiceRequest request) {
        //响应json实体类字段
        ParkingReceiptServiceResponse response = new ParkingReceiptServiceResponse();

        //将校验字段放入在容器中
        List<String> params = new ArrayList<>();
        params.add("startTime");
        params.add("endTime");
        params.add("freeParkingCount");
        params.add("unitPrice");
        params.add("parkingSpace");
        params.add("unitPrice");

        parkingReceiptManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 非物理删除
     * @Auth wangchuang
     * @Date 2018/6/8 10:07
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody ParkingReceiptServiceRequest request) {
        //响应json实体类字段
        ParkingReceiptServiceResponse response = new ParkingReceiptServiceResponse();
        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getCtCode())) {
            return this.error(response,"删除失败，传入信息有误!");
        }
        request.getEntity().setIsDeleted(true);
        parkingReceiptManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 修改合同:停车费缴收信息
     * @Auth wangchuang
     * @Date 2018/6/8 10:09
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody ParkingReceiptServiceRequest request) {
        //响应json实体类字段
        ParkingReceiptServiceResponse response = new ParkingReceiptServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getCtCode())) {
            return this.error(response,"修改失败，传入信息有误!");
        }
        parkingReceiptManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 查询合同:停车费缴收信息
     * @Auth wangchuang
     * @Date 2018/6/8 10:10
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ParkingReceiptServiceRequest request) {
        //响应json实体类字段
        ParkingReceiptServiceResponse response = new ParkingReceiptServiceResponse();

        //TODO 根据查询场景添加业务逻辑

        ParkingReceiptEntity parkEntity = request.getEntity();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id",parkEntity.getId());
        param.put("ctCode",parkEntity.getCtCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<ParkingReceiptEntity> result = parkingReceiptManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 停车费缴费
     * @Auth luming
     * @Date 2018/9/10 18:31
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/pay"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> pay(@RequestBody ParkingReceiptServiceRequest request) {

        ParkingReceiptServiceResponse response = new ParkingReceiptServiceResponse();
        ParkingReceiptEntity parkEntity = request.getEntity();

        parkingReceiptManager.pay(parkEntity);

        return this.success(response);
    }
}
