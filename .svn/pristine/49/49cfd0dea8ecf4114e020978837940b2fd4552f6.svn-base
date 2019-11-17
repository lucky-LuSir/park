package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IRentIncrementManager;
import com.kfwy.park.ati.contract.entity.RentIncrementEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.contract.vo.RentIncrementServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.RentIncrementServiceResponse;
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
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@RestController
@RequestMapping(path = "/rentIncrement")
public class RentIncrementService extends SpringRestService {

    @Autowired
    private IRentIncrementManager rentIncrementManager;

    /**
     * @Description TODO
     * @author wangchuang
     * @Date 2018/10/18 16:33
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody RentIncrementServiceRequest request) {

        RentIncrementServiceResponse response = new RentIncrementServiceResponse();

        rentIncrementManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description TODO
     * @author wangchuang
     * @Date 2018/10/18 16:33
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody RentIncrementServiceRequest request) {
        RentIncrementServiceResponse response = new RentIncrementServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getIncCode())) {
            return this.error(response, "删除失败，传入信息有误!");
        }
        request.getEntity().setIsDeleted(true);
        rentIncrementManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description TODO
     * @author wangchuang
     * @Date 2018/10/18 16:34
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody RentIncrementServiceRequest request) {
        RentIncrementServiceResponse response = new RentIncrementServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getIncCode())) {
            return this.error(response, "删除失败，传入信息有误!");
        }

        rentIncrementManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description TODO
     * @author wangchuang
     * @Date 2018/10/18 16:36
     * @Version 1.0
     * @Param []
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody RentIncrementServiceRequest request) throws IllegalAccessException {
        RentIncrementServiceResponse response = new RentIncrementServiceResponse();

        RentIncrementEntity rentIncrementEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();

        ParamUtil<RentIncrementEntity> paramUtil = new ParamUtil<>();

        paramUtil.objectToMap(rentIncrementEntity,param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<RentIncrementEntity> result = rentIncrementManager.findByMap(param);

        response.setResult(result);

        return this.success(response);
    }

    /**
     * @Description TODO
     * @author wangchuang
     * @Date 2018/10/18 16:41
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody RentIncrementServiceRequest request) {
        RentIncrementServiceResponse response = new RentIncrementServiceResponse();

        RentIncrementEntity rentIncrementEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("id",rentIncrementEntity.getId());
        param.put("incCode",rentIncrementEntity.getIncCode());
        param.put("ctCode",rentIncrementEntity.getCtCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<RentIncrementEntity> pageResult = rentIncrementManager.findByMap(param, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(pageResult);

        return this.success(response);
    }
}
