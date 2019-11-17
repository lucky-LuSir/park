package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IWaterReceiptManager;
import com.kfwy.park.ati.contract.entity.WaterReceiptEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.contract.vo.WaterReceiptRequest;
import com.kfwy.park.controller.ati.contract.vo.WaterReceiptResponse;
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

/**
 * @Description 水费应收列表
 * @Auth xiongzhan
 * @Date 2018/6/4 10:29
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping("waterReceipt")
public class WaterReceiptService extends SpringRestService {
    @Autowired
    private IWaterReceiptManager waterReceiptManager;

    /**
     * @Description 水费应收新增
     * @Auth xiongzhan
     * @Date 2018/6/4 10:29
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody WaterReceiptRequest request) {

        WaterReceiptResponse response = new WaterReceiptResponse();

        waterReceiptManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 水费应收修改
     * @Auth xiongzhan
     * @Date 2018/6/4 10:30
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody WaterReceiptRequest request){

        WaterReceiptResponse response = new WaterReceiptResponse();

        waterReceiptManager.update(request.getEntity());

        return this.success(response);

    }


    /**
     * @Description 水费应收查询
     * @Auth xiongzhan
     * @Date 2018/6/4 10:39
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody WaterReceiptRequest request){
        WaterReceiptResponse response = new WaterReceiptResponse();

        WaterReceiptEntity waterReceiptEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("id",waterReceiptEntity.getId());
        param.put("cpyCode",waterReceiptEntity.getCpyCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<WaterReceiptEntity> result = waterReceiptManager.findByMap(param);

        response.setResult(result);

        return this.success(response);
    }


    /**
     * @Description 非物理删除
     * @Auth xiongzhan
     * @Date 2018/6/4 10:30
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody WaterReceiptRequest request) {


        WaterReceiptResponse response = new WaterReceiptResponse();
        request.getEntity().setIsDeleted(true);
        waterReceiptManager.update(request.getEntity());
        
        return this.success(response);

    }


    /**
     * @Description 查询
     * @author wangchuang
     * @Date 2018/7/20 16:14
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/list",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody WaterReceiptRequest request) throws IllegalAccessException {
        WaterReceiptResponse response = new WaterReceiptResponse();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<WaterReceiptEntity> paramUtil = new ParamUtil<WaterReceiptEntity>();
        paramUtil.objectToMap(request.getEntity(),param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<WaterReceiptEntity> result = waterReceiptManager.findByMap(param,"id",false);

        response.setResult(result);

        return this.success(response);
    }

    /**
     * @Description 水费缴费
     * @Auth luming
     * @Date 2018/9/10 18:32
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/pay"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> pay(@RequestBody WaterReceiptRequest request) {

        WaterReceiptResponse response = new WaterReceiptResponse();
        WaterReceiptEntity waterReceiptEntity = request.getEntity();

        waterReceiptManager.pay(waterReceiptEntity);

        return this.success(response);
    }
}




