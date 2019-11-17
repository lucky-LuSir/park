package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IElectricityMeterManager;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.ati.contract.entity.ElectricityMeterEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.contract.vo.ElectricityMeterServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.ElectricityMeterServiceResponse;
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
 * @Description 电表管理（添加删除操作）
 * @author wangchuang
 * @Date 2018/8/17 11:07
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping(path = "/eleMeter")
public class ElectricityMeterService extends SpringRestService {

    @Autowired
    private IElectricityMeterManager electricityMeterManager;

    /**
     * @Description 创建电表
     * @author wangchuang
     * @Date 2018/8/17 11:07
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ElectricityMeterServiceRequest request) throws IllegalAccessException {

        ElectricityMeterServiceResponse response = new ElectricityMeterServiceResponse();

        ElectricityMeterEntity meterEntity = request.getEntity();
        //校验非空字段
        List<String> list = new ArrayList();
        list.add("ctCode");
        list.add("eleMeterName");
        list.add("multiple");
        list.add("electricityBillingType");
        if ("1".equals(meterEntity.getElectricityBillingType())){
            list.add("electricityAveragePrice");
            list.add("initElectricity");
        } else if ("2".equals(meterEntity.getElectricityBillingType())) {
            list.add("electricityPeak");
            list.add("electricityFlat");
            list.add("electricityTrough");
            list.add("electricityPeakPrice");
            list.add("electricityFlatPrice");
            list.add("electricityTroughPrice");
        }

        ParamUtil<ElectricityMeterEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(meterEntity,list);

        electricityMeterManager.create(meterEntity);

        return this.success(response);
    }

    /**
     * @Description 修改
     * @author wangchuang
     * @Date 2018/8/17 11:08
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody ElectricityMeterServiceRequest request) throws IllegalAccessException {

        ElectricityMeterServiceResponse response = new ElectricityMeterServiceResponse();

        ElectricityMeterEntity meterEntity = request.getEntity();
        //校验非空字段
        List<String> list = new ArrayList();
        list.add("ctCode");
        list.add("eleMeterName");
        list.add("multiple");
        list.add("electricityBillingType");
        if ("1".equals(meterEntity.getElectricityBillingType())){
            list.add("electricityAveragePrice");
            list.add("initElectricity");
        } else if ("2".equals(meterEntity.getElectricityBillingType())) {
            list.add("electricityPeak");
            list.add("electricityFlat");
            list.add("electricityTrough");
            list.add("electricityPeakPrice");
            list.add("electricityFlatPrice");
            list.add("electricityTroughPrice");
        }
        ParamUtil<ElectricityMeterEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(meterEntity,list);

        electricityMeterManager.update(meterEntity);
        
        return this.success(response);
    }

    /**
     * @Description 物理删除
     * @author wangchuang
     * @Date 2018/8/20 17:55
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody ElectricityMeterServiceRequest request) {
        ElectricityMeterServiceResponse response = new ElectricityMeterServiceResponse();

        ElectricityMeterEntity entity = request.getEntity();
        if (!StringUtils.isEmpty(entity)) {
            if (!StringUtils.isEmpty(entity.getId())) {
                electricityMeterManager.delete(entity.getId());
            }
        }

        return this.success(response);
    }

    /**
     * @Description 分页查询
     * @author wangchuang
     * @Date 2018/8/17 11:10
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ElectricityMeterServiceRequest request) throws IllegalAccessException {
        //响应json实体类字段
        ElectricityMeterServiceResponse response = new ElectricityMeterServiceResponse();

        ElectricityMeterEntity meterEntity = request.getEntity();
        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<ElectricityMeterEntity> paramUtil = new ParamUtil<ElectricityMeterEntity>();
        paramUtil.objectToMap(meterEntity,param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<ElectricityMeterEntity> result = electricityMeterManager.findByMap(param, request.getStart(), request.getPageSize(), "id", true);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询集合
     * @author wangchuang
     * @Date 2018/8/17 11:11
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody ElectricityMeterServiceRequest request) throws IllegalAccessException {

        //响应json实体类字段
        ElectricityMeterServiceResponse response = new ElectricityMeterServiceResponse();

        Map<String,Object> map=new HashMap<>();

        ParamUtil<ElectricityMeterEntity> paramUtil = new ParamUtil<ElectricityMeterEntity>();
        paramUtil.objectToMap(request.getEntity(),map);
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<ElectricityMeterEntity> result = electricityMeterManager.findByMap(map,"id",true);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 根据合同编号查询电表集合
     * @author luming
     * @Date 2019/5/29 17:44
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/ctCodeEleList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> ctCodeEleList(@RequestBody ElectricityMeterServiceRequest request) throws IllegalAccessException {
        ElectricityMeterServiceResponse response = new ElectricityMeterServiceResponse();

        List<ContractEntity> contractList = request.getContractList();

        for (ContractEntity list : contractList) {
            Map<String, Object> map = new HashMap<>();
            map.put("isDeleted", false);
            map.put("cpyCode", CurrentContext.getCpyCode());
            map.put("ctCode", list.getCtCode());
            map.put("eleMeterCode", request.getEleMeterCode());
            List<ElectricityMeterEntity> eleList = electricityMeterManager.eleMeterUnionRecord(map);

            list.setMeterList(eleList);
        }

        response.setResult(contractList);
        return this.success(response);
    }


    /**
     * @Description 电表启用禁用接口
     * @author wangchuang
     * @Date 2019/8/9 14:35
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/changeEleIsOpen"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> changeEleIsOpen(@RequestBody ElectricityMeterServiceRequest request) {

        ElectricityMeterServiceResponse response = new ElectricityMeterServiceResponse();
        ElectricityMeterEntity entity = request.getEntity();

        if (entity.getId() == null || StringUtils.isEmpty(entity.getEleMeterCode())) {
            throw new BusinessException("更新电表数据,ID和eleMeterCode不能为空");
        }

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("id",entity.getId());
        param.put("eleMeterCode", entity.getEleMeterCode());
        param.put("eleIsOpen", entity.getEleIsOpen());

        electricityMeterManager.changeEleIsOpen(param);

        return this.success(response);

    }
}
