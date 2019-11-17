package com.kfwy.park.controller.pro.carmanager;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.carmanager.vo.CarManagerServiceRequest;
import com.kfwy.park.controller.pro.carmanager.vo.CarManagerServiceResponse;
import com.kfwy.park.pro.carmanager.business.ICarManagerManager;
import com.kfwy.park.pro.carmanager.entity.CarManagerEntity;

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

/**
 * @author gtaotao
 * @since 2019-07-23 16:41:19
 */
@Controller
@RequestMapping("carManager")
public class CarManagerService extends SpringRestService{
    @Autowired
    private ICarManagerManager carManagerManager;
    
    @RequestMapping(path = "create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CarManagerServiceRequest request) throws IllegalAccessException {
        CarManagerServiceResponse response = new CarManagerServiceResponse();
        CarManagerEntity carManagerEntity = request.getEntity();
        carManagerManager.checkParam(carManagerEntity);
        carManagerEntity.setCarCode(CodeGenUtils.generate());
        carManagerManager.create(carManagerEntity);
        return this.success(response);
    }
    
    @RequestMapping(path = "list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody CarManagerServiceRequest request) throws IllegalAccessException {
        CarManagerServiceResponse response = new CarManagerServiceResponse();
        CarManagerEntity carManagerEntity = request.getEntity();
        ParamUtil<CarManagerEntity> paramUtil = new ParamUtil();
        Map<String, Object> map = new HashMap<>();
        paramUtil.objectToMap(carManagerEntity, map);
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<CarManagerEntity> list = carManagerManager.findByMap(map);
        response.setResult(list);
        return this.success(response);
    }
    
    @RequestMapping(path = "query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CarManagerServiceRequest request) throws IllegalAccessException {
        CarManagerServiceResponse response = new CarManagerServiceResponse();
        CarManagerEntity carManagerEntity = request.getEntity();
        ParamUtil<CarManagerEntity> paramUtil = new ParamUtil();
        Map<String, Object> map = new HashMap<>();
        paramUtil.objectToMap(carManagerEntity, map);
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<CarManagerEntity> list = carManagerManager.findByMap(map,request.getStart(), request.getPageSize(),"create_time",false);
        response.setResult(list);
        return this.success(response);
    }
    
    @RequestMapping(path = "update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody CarManagerServiceRequest request) throws IllegalAccessException {
        CarManagerServiceResponse response = new CarManagerServiceResponse();
        CarManagerEntity carManagerEntity = request.getEntity();
        carManagerManager.checkParam(carManagerEntity);
        carManagerManager.update(carManagerEntity);
        return this.success(response);
    }

    @RequestMapping(path = "delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody CarManagerServiceRequest request) throws IllegalAccessException {
        CarManagerServiceResponse response = new CarManagerServiceResponse();
        CarManagerEntity carManagerEntity = request.getEntity();
        if(StringUtils.isEmpty(carManagerEntity.getId())){
            throw new BusinessException("缺少必填的id!");
        }
        carManagerEntity.setIsDeleted(true);
        carManagerManager.update(carManagerEntity);
        return this.success(response);
    }

    @RequestMapping(path = "selectOne"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectOne(@RequestBody CarManagerServiceRequest request) throws IllegalAccessException {
        CarManagerServiceResponse response = new CarManagerServiceResponse();
        CarManagerEntity carManagerEntity = request.getEntity();
        CarManagerEntity entity= carManagerManager.findOne("id", carManagerEntity.getId());
        response.setResult(entity);
        return this.success(response);
    }
}