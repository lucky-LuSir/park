package com.kfwy.park.controller.pro.car;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.car.vo.CarServiceRequest;
import com.kfwy.park.controller.pro.car.vo.CarServiceResponse;
import com.kfwy.park.pro.car.business.ICarManager;
import com.kfwy.park.pro.car.entity.CarEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


/**
 * Created by gengtao on 2019/6/11.
 */
@Controller
@RequestMapping("/car")
public class CarService extends SpringRestService {
    @Autowired
    private ICarManager carManager;

    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CarServiceRequest request) throws IllegalAccessException {
        CarEntity carEntity = request.getEntity();
        carManager.checkParam(carEntity);
        carEntity.setCmCode(UUID.randomUUID().toString().replaceAll("-",""));
        carManager.create(carEntity);
        return this.success(new CarServiceResponse());
    }

    @RequestMapping(path = "/query"
    ,method = RequestMethod.POST
    ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CarServiceRequest request) throws IllegalAccessException {
        CarServiceResponse response = new CarServiceResponse();
        CarEntity carEntity = request.getEntity();
        Map<String, Object> map = new HashMap<>();
        ParamUtil<CarEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(carEntity, map);
        if(!StringUtils.isEmpty(request.getOutDateStart())  && !StringUtils.isEmpty(request.getOutDateEnd())){
            map.put("outDateStart",request.getOutDateStart());
            map.put("outDateEnd",request.getOutDateEnd());
        }
        if(!StringUtils.isEmpty(request.getKeyword().trim())){
            map.put("keyword",request.getKeyword().trim());
        }
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<CarEntity> result = carManager.findByMap(map, request.getStart(), request.getPageSize(),"create_time",false);
        response.setResult(result);
        return this.success(response);
    }

    @RequestMapping(path = "/updateAll"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> updateAll(@RequestBody CarServiceRequest request) throws IllegalAccessException {
        CarEntity carEntity = request.getEntity();
        carManager.checkParam(carEntity);
        carManager.updataAll(carEntity);
        return this.success(new CarServiceResponse());
    }
}
