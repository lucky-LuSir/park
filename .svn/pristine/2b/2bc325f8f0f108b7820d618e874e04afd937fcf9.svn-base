package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IContractHouseManager;
import com.kfwy.park.ati.contract.entity.ContractHouseEntity;
import com.kfwy.park.controller.ati.contract.vo.ContractHouseServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.ContractHouseServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 合同:房源信息管理
 * @Auth wangchuang
 * @Date 2018/6/7 14:55
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping(path = "/ctHouse")
public class ContractHouseService extends SpringRestService {

    @Autowired
    private IContractHouseManager contractHouseManager;

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 新增合同:房源信息
     * @Auth wangchuang
     * @Date 2018/6/7  9:29
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ContractHouseServiceRequest request) {
        //响应json实体类字段
        ContractHouseServiceResponse response = new ContractHouseServiceResponse();

        contractHouseManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 非物理删除
     * @Auth wangchuang
     * @Date 2018/6/7  9:29
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody ContractHouseServiceRequest request) {

        ContractHouseServiceResponse response = new ContractHouseServiceResponse();

        request.getEntity().setIsDeleted(true);
        contractHouseManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 修改合同:房源信息
     * @Auth wangchuang
     * @Date 2018/6/7  9:29
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody ContractHouseServiceRequest request) {
        ContractHouseServiceResponse response = new ContractHouseServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getRentHosCode())) {
            return this.error(response, "修改失败，传入信息有误!");
        }
        contractHouseManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 查询合同:房源信息
     * @Auth wangchuang
     * @Date 2018/6/7  9:29
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ContractHouseServiceRequest request) {

        ContractHouseServiceResponse response = new ContractHouseServiceResponse();

        ContractHouseEntity ctHusEntity = request.getEntity();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", ctHusEntity.getId());
        param.put("rentHosCode", ctHusEntity.getRentHosCode());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<ContractHouseEntity> result = contractHouseManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @return
     * @Description 根据合同编号查询与合同关联的房源信息
     * @Auth luming
     * @Date 2018/9/5 21:59
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> details(@RequestBody ContractHouseServiceRequest request) {
        ContractHouseServiceResponse response = new ContractHouseServiceResponse();
        ContractHouseEntity contractHouseEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("ctCode", contractHouseEntity.getCtCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<ContractHouseEntity> houseList = contractHouseManager.findByMap(map);

        response.setResult(houseList);
        return this.success(response);
    }
}
