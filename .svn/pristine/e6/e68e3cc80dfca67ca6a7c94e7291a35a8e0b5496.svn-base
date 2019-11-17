package com.kfwy.park.controller.exp.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.exp.contract.vo.LandlordContractServiceRequest;
import com.kfwy.park.controller.exp.contract.vo.LandlordContractServiceResponse;
import com.kfwy.park.exp.contract.business.ILandlordContractManager;
import com.kfwy.park.exp.contract.entity.LandlordContractEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 大房东合同
 * @author luming
 * @Date 2019/4/19 9:08
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping("/landlord")
public class LandlordContractService extends SpringRestService {
    @Autowired
    private ILandlordContractManager landlordContractManager;

    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody LandlordContractServiceRequest request) throws IllegalAccessException {
        LandlordContractServiceResponse response = new LandlordContractServiceResponse();

        LandlordContractEntity landlordContractEntity = request.getEntity();
        String ldCode = CodeGenUtils.generate();
        landlordContractEntity.setLdCode(ldCode);

        // 校验大房东合同必填字段
        landlordContractManager.checkParams(landlordContractEntity);

        // 新增大房东合同，同时新增合同租金
        landlordContractManager.createContract(landlordContractEntity);

        return this.success(response);
    }

    /**
     * @Description 修改
     * @author luming
     * @Date 2019/4/19 10:36
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody LandlordContractServiceRequest request) throws IllegalAccessException {
        LandlordContractServiceResponse response = new LandlordContractServiceResponse();

        LandlordContractEntity landlordContractEntity = request.getEntity();

        // 校验大房东合同必填字段
        landlordContractManager.checkParams(landlordContractEntity);

        // 修改大房东合同，同时修改租金
        landlordContractManager.updateContract(landlordContractEntity);

        return this.success(response);
    }

    /**
     * @Description 删除合同
     * @author luming
     * @Date 2019/4/19 11:16
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody LandlordContractServiceRequest request) throws IllegalAccessException {
        LandlordContractServiceResponse response = new LandlordContractServiceResponse();

        LandlordContractEntity landlordContractEntity = request.getEntity();

        // 修改大房东合同，同时修改租金
        landlordContractManager.updateContract(landlordContractEntity);

        return this.success(response);
    }

    /**
     * @Description 查询，提供分页
     * @author luming
     * @Date 2019/4/19 11:22
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody LandlordContractServiceRequest request) throws IllegalAccessException {
        LandlordContractServiceResponse response = new LandlordContractServiceResponse();

        LandlordContractEntity landlordContractEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<LandlordContractEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(landlordContractEntity, map);

        PageResult<LandlordContractEntity> pageResult = landlordContractManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(pageResult);
        return this.success(response);
    }

    /**
     * @Description 合同详情
     * @Auth luming
     * @Date 2019/4/25 8:54
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/detail"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody LandlordContractServiceRequest request) throws IllegalAccessException {
        LandlordContractServiceResponse response = new LandlordContractServiceResponse();

        LandlordContractEntity landlordContractEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<LandlordContractEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(landlordContractEntity, map);

        LandlordContractEntity contractEntity = landlordContractManager.deatil(map);

        response.setResult(contractEntity);
        return this.success(response);
    }

    @RequestMapping(path = "/updateContractUrl"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> updataContractUrl(@RequestBody LandlordContractServiceRequest request) throws IllegalAccessException {
        LandlordContractServiceResponse response = new LandlordContractServiceResponse();
        LandlordContractEntity landlordContractEntity = request.getEntity();

        List<String> params = new ArrayList<>();
        params.add("ldCode");
        params.add("contractUrl");
        ParamUtil<LandlordContractEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(landlordContractEntity, params);
        landlordContractManager.update(landlordContractEntity);

        return this.success(response);
    }
}
