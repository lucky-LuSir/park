package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IWaterMeterManager;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.ati.contract.entity.WaterMeterEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.contract.vo.WaterMeterServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.WaterMeterServiceResponse;
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
 * @Description 水表管理
 * @author wangchuang
 * @Date 2018/11/20 17:54
 * @Version 1.0
 * @Param 
 * @return 
 **/
@RestController
@RequestMapping("waterMeter")
public class WaterMeterService extends SpringRestService {

    @Autowired
    private IWaterMeterManager waterMeterManager;

    /**
     * @Description 新增水表
     * @author wangchuang
     * @Date 2018/11/20 17:53
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody WaterMeterServiceRequest request) throws IllegalAccessException {

        WaterMeterServiceResponse response = new WaterMeterServiceResponse();

        //将必填字段放入水费容器
        List<String> param = new ArrayList<>();
        param.add("ctCode");
        param.add("waterMeterName");
        param.add("waterBillingType");
        if ("2".equals(request.getEntity().getWaterBillingType())) {
            param.add("waterUnitPrice");
            param.add("initWater");
        }
        if ("1".equals(request.getEntity().getWaterBillingType())) {
            param.add("waterAmount");
        }

        ParamUtil<WaterMeterEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(),param);

        waterMeterManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 修改水表
     * @author wangchuang
     * @Date 2018/11/20 17:53
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody WaterMeterServiceRequest request) throws IllegalAccessException {

        WaterMeterServiceResponse response = new WaterMeterServiceResponse();

        WaterMeterEntity waterMeterEntity = request.getEntity();
        //校验非空字段
        List<String> param = new ArrayList();
        param.add("waterMeterCode");
        param.add("ctCode");
        param.add("waterMeterName");
        param.add("waterBillingType");
        if ("2".equals(request.getEntity().getWaterBillingType())) {
            param.add("waterUnitPrice");
            param.add("initWater");
        }
        if ("1".equals(request.getEntity().getWaterBillingType())) {
            param.add("waterAmount");
        }
        ParamUtil<WaterMeterEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(waterMeterEntity,param);

        waterMeterManager.update(waterMeterEntity);

        return this.success(response);
    }

    /**
     * @Description 删除水表
     * @author wangchuang
     * @Date 2018/11/20 17:53
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody WaterMeterServiceRequest request) {
        WaterMeterServiceResponse response = new WaterMeterServiceResponse();

        WaterMeterEntity entity = request.getEntity();

        if (!StringUtils.isEmpty(entity)) {
            if (!StringUtils.isEmpty(entity.getId())) {
                waterMeterManager.delete(entity.getId());
            } else {
                throw new BusinessException("请指定一个表进行删除操作");
            }
        }
        return this.success(response);
    }

    /**
     * @Description 分页查询水表
     * @author wangchuang
     * @Date 2018/11/20 17:58
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody WaterMeterServiceRequest request) throws IllegalAccessException {
        //响应json实体类字段
        WaterMeterServiceResponse response = new WaterMeterServiceResponse();

        WaterMeterEntity waterMeterEntity = request.getEntity();
        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<WaterMeterEntity> paramUtil = new ParamUtil<WaterMeterEntity>();
        paramUtil.objectToMap(waterMeterEntity,param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<WaterMeterEntity> result = waterMeterManager.findByMap(param, request.getStart(), request.getPageSize(), "id", true);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询水表集合
     * @author wangchuang
     * @Date 2018/11/20 18:02
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody WaterMeterServiceRequest request) throws IllegalAccessException {
        WaterMeterServiceResponse response = new WaterMeterServiceResponse();

        Map<String,Object> map=new HashMap<>();

        ParamUtil<WaterMeterEntity> paramUtil = new ParamUtil<WaterMeterEntity>();
        paramUtil.objectToMap(request.getEntity(),map);
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<WaterMeterEntity> result = waterMeterManager.findByMap(map,"id",true);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 根据合同编号查询水表集合
     * @Auth luming
     * @Date 2019/6/6 10:29
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/queryWaterMeterListByCtCode"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> ctCodeEleList(@RequestBody WaterMeterServiceRequest request) throws IllegalAccessException {
        WaterMeterServiceResponse response = new WaterMeterServiceResponse();

        List<ContractEntity> contractList = request.getContractList();

        for (ContractEntity list : contractList) {
            Map<String, Object> map = new HashMap<>();
            map.put("isDeleted", false);
            map.put("cpyCode", CurrentContext.getCpyCode());
            map.put("ctCode", list.getCtCode());
            map.put("waterMeterCode", request.getWaterMeterCode());
            List<WaterMeterEntity> waterMeterList = waterMeterManager.waterMeterUnionRecord(map);

            list.setWaterMeterList(waterMeterList);
        }

        response.setResult(contractList);
        return this.success(response);
    }

    /**
     * @Description 水表是否开启
     * @author wangchuang
     * @Date 2019/8/13 11:46
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/changeWaterIsOpen"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> changeWaterIsOpen(@RequestBody WaterMeterServiceRequest request) {

        WaterMeterServiceResponse response = new WaterMeterServiceResponse();
        WaterMeterEntity entity = request.getEntity();

        if (entity.getId() == null || StringUtils.isEmpty(entity.getWaterMeterCode())) {
            throw new BusinessException("更新水表数据,ID和waterMeterCode不能为空");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", entity.getId());
        param.put("waterMeterCode", entity.getWaterMeterCode());
        param.put("waterIsOpen", entity.getWaterIsOpen());

        waterMeterManager.changeWaterIsOpen(param);
        return this.success(response);
    }
}
