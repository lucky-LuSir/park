package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IPremiumManager;
import com.kfwy.park.ati.contract.entity.PremiumEntity;
import com.kfwy.park.controller.ati.contract.vo.PremiumServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.PremiumServiceResponse;
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
 * @Description 保险费
 * @Auth luming
 * @Date 2018/11/17 11:58
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping(path = "/premium")
public class PremiumService extends SpringRestService{

    @Autowired
    private IPremiumManager premiumManager;


    /**
     * @Description 新增
     * @Auth luming
     * @Date 2018/11/17 12:00
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody PremiumServiceRequest request) throws Exception {

        PremiumServiceResponse response = new PremiumServiceResponse();
        PremiumEntity premiumEntity = request.getEntity();

        String premiumCode = CodeGenUtils.generate();
        premiumEntity.setPremiumCode(premiumCode);

        premiumManager.create(premiumEntity);

        return this.success(response);
    }

    /**
     * @Description 修改
     * @Auth luming
     * @Date 2018/11/17 14:04
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody PremiumServiceRequest request) throws Exception {

        PremiumServiceResponse response = new PremiumServiceResponse();
        PremiumEntity premiumEntity = request.getEntity();

        premiumManager.update(premiumEntity);

        return this.success(response);
    }

    /**
     * @Description 分页查询
     * @Auth luming
     * @Date 2018/11/17 14:05
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody PremiumServiceRequest request) throws Exception {

        PremiumServiceResponse response = new PremiumServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<PremiumEntity> result = premiumManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询详情
     * @Auth luming
     * @Date 2018/11/17 14:08
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/detail"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody PremiumServiceRequest request) throws Exception {

        PremiumServiceResponse response = new PremiumServiceResponse();
        PremiumEntity premiumEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("premiumCode", premiumEntity.getPremiumCode());
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<PremiumEntity> result = premiumManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询详情
     * @Auth luming
     * @Date 2018/11/17 14:08
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody PremiumServiceRequest request) throws Exception {

        PremiumServiceResponse response = new PremiumServiceResponse();
        PremiumEntity premiumEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("ctCode", premiumEntity.getCtCode());
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<PremiumEntity> result = premiumManager.findByMap(map);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 非物理删除
     * @Auth luming
     * @Date 2018/11/19 15:37
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody PremiumServiceRequest request) throws Exception {

        PremiumServiceResponse response = new PremiumServiceResponse();
        PremiumEntity premiumEntity = request.getEntity();

        premiumEntity.setIsDeleted(true);

        premiumManager.update(premiumEntity);

        return this.success(response);
    }

    /**
     * @Description 保险费缴费
     * @Auth luming
     * @Date 2018/11/19 15:37
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/pay"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> pay(@RequestBody PremiumServiceRequest request) throws Exception {

        PremiumServiceResponse response = new PremiumServiceResponse();
        PremiumEntity premiumEntity = request.getEntity();

        premiumManager.pay(premiumEntity);

        return this.success(response);
    }

}
