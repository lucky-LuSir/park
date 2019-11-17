package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IRentReceiptDetailManager;
import com.kfwy.park.ati.contract.entity.RentReceiptDetailEntity;
import com.kfwy.park.controller.ati.contract.vo.RentReceiptDetailRequest;
import com.kfwy.park.controller.ati.contract.vo.RentReceiptDetailResponse;
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
 * @Description 租金应收明细列表
 * @Auth xiongzhan
 * @Date 2018/6/4 10:22
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping("rentReceiptDetail")
public class RentReceiptDetailService extends SpringRestService {

    @Autowired
    private IRentReceiptDetailManager rentReceiptDetailManager;

    /**
     * @Description 新增租金应收明细
     * @Auth xiongzhan
     * @Date 2018/6/4 10:23
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody RentReceiptDetailRequest request) {

        RentReceiptDetailResponse response = new RentReceiptDetailResponse();

        rentReceiptDetailManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 修改租金应收明细
     * @Auth xiongzhan
     * @Date 2018/6/4 10:23
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody RentReceiptDetailRequest request){

        RentReceiptDetailResponse response = new RentReceiptDetailResponse();

        rentReceiptDetailManager.update(request.getEntity());

        return this.success(response);

    }


    /**
     * @Description 租金应收明细查询
     * @Auth xiongzhan
     * @Date 2018/6/4 10:23
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody RentReceiptDetailRequest request){
        RentReceiptDetailResponse response = new RentReceiptDetailResponse();

        RentReceiptDetailEntity rentReceiptDetailEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("id",rentReceiptDetailEntity.getId());
        param.put("cpyCode",rentReceiptDetailEntity.getCpyCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<RentReceiptDetailEntity> result = rentReceiptDetailManager.findByMap(param);

        response.setResult(result);

        return this.success(response);
    }


    /**
     * @Description 非物理删除
     * @Auth xiongzhan
     * @Date 2018/6/4 10:23
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody RentReceiptDetailRequest request) {


        RentReceiptDetailResponse response = new RentReceiptDetailResponse();
        request.getEntity().setIsDeleted(true);
        rentReceiptDetailManager.update(request.getEntity());

        return this.success(response);

    }
}



