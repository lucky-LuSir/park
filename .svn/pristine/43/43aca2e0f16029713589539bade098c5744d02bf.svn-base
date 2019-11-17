package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IElectricityReceiptManager;
import com.kfwy.park.ati.contract.entity.ElectricityReceiptEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.contract.vo.ElectricityReceiptServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.ElectricityReceiptServiceResponse;
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
 * @Description 合同:电费缴收管理
 * @Auth wangchuang
 * @Date 2018/6/7 20:42
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping(path = "/eleReceipt")
public class ElectricityReceiptService extends SpringRestService {

    @Autowired
    private IElectricityReceiptManager electricityReceiptManager;

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 新增合同:电费缴收信息
     * @Auth wangchuang
     * @Date 2018/6/7 9:35
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ElectricityReceiptServiceRequest request) throws IllegalAccessException {
        //响应json实体类字段
        ElectricityReceiptServiceResponse response = new ElectricityReceiptServiceResponse();

        ElectricityReceiptEntity eleEntity = request.getEntity();

        //将必填字段放入电费容器
        List<String> params = new ArrayList<>();
        params.add("ctCode");
        params.add("lastBillingTime");
        params.add("currentBillingTime");
//        params.add("electricityBillingType");
//        if ("1".equals(eleEntity.getElectricityBillingType())) {
//            //均价计费
//            params.add("realDegree");
//            params.add("lastEleAverageDegree");
//            params.add("currentEleAverageDegree");
//        } else if ("2".equals(eleEntity.getElectricityBillingType())) {
//            //峰谷值计费
//            params.add("peakDegree");
//            params.add("flatDegree");
//            params.add("troughDegree");
//            params.add("lastPeakDegree");
//            params.add("lastFlatDegree");
//            params.add("lastTroughDegree");
//            params.add("currentPeakDegree");
//            params.add("currentFlatDegree");
//            params.add("currentTroughDegree");
//        }
        params.add("payStatus");

        ParamUtil<ElectricityReceiptEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(eleEntity, params);

        String eleCode = CodeGenUtils.generate();
        request.getEntity().setEleCode(eleCode);
        electricityReceiptManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 非物理删除
     * @Auth wangchuang
     * @Date 2018/6/7 9:38
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody ElectricityReceiptServiceRequest request) {
        //响应json实体类字段
        ElectricityReceiptServiceResponse response = new ElectricityReceiptServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getCtCode())) {
            return this.error(response, "删除失败，传入信息有误!");
        }
        request.getEntity().setIsDeleted(true);
        electricityReceiptManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 修改合同:电费缴收信息
     * @Auth wangchuang
     * @Date 2018/6/7 9:39
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody ElectricityReceiptServiceRequest request) {
        //响应json实体类字段
        ElectricityReceiptServiceResponse response = new ElectricityReceiptServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getCtCode())) {
            return this.error(response, "修改失败，传入信息有误!");
        }
        electricityReceiptManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 查询合同:房源信息
     * @Auth wangchuang
     * @Date 2018/6/7 9:39
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ElectricityReceiptServiceRequest request) throws IllegalAccessException {
        //响应json实体类字段
        ElectricityReceiptServiceResponse response = new ElectricityReceiptServiceResponse();

        //TODO 根据查询场景添加业务逻辑

        ElectricityReceiptEntity eleEntity = request.getEntity();
        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<ElectricityReceiptEntity> paramUtil = new ParamUtil<ElectricityReceiptEntity>();
        paramUtil.objectToMap(request.getEntity(), param);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<ElectricityReceiptEntity> result = electricityReceiptManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 查询
     * @author wangchuang
     * @Date 2018/7/20 16:15
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody ElectricityReceiptServiceRequest request) throws IllegalAccessException {

        //响应json实体类字段
        ElectricityReceiptServiceResponse response = new ElectricityReceiptServiceResponse();

        Map<String, Object> map = new HashMap<>();

        ParamUtil<ElectricityReceiptEntity> paramUtil = new ParamUtil<ElectricityReceiptEntity>();
        paramUtil.objectToMap(request.getEntity(), map);
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<ElectricityReceiptEntity> result = electricityReceiptManager.findByMap(map, "id", false);

        response.setResult(result);
        return this.success(response);
    }


    /**
     * @Description 电费缴费
     * @Auth luming
     * @Date 2018/9/10 17:48
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/pay"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> pay(@RequestBody ElectricityReceiptServiceRequest request) throws IllegalAccessException {

        ElectricityReceiptServiceResponse response = new ElectricityReceiptServiceResponse();
        ElectricityReceiptEntity electricityReceiptEntity = request.getEntity();

        electricityReceiptManager.pay(electricityReceiptEntity);
        return this.success(response);
    }
}
