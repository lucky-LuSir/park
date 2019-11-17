package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IConOperateHisManager;
import com.kfwy.park.ati.contract.entity.ConOperateHisEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.contract.vo.ConOperateHisServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.ConOperateHisServiceResponse;
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
 * @Description 合同审核记录
 * @Auth wangchuang
 * @Date 2018/12/10 14:15
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping(path = "/conOperateHis")
public class ConOperateHisService extends SpringRestService {

    @Autowired
    private IConOperateHisManager conOperateHisManager;

    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ConOperateHisServiceRequest request) throws IllegalAccessException {
        //响应json实体类字段
        ConOperateHisServiceResponse response = new ConOperateHisServiceResponse();

        //将必填字段放入容器
        List<String> params = new ArrayList<>();
        params.add("ctCode");
        params.add("ctStatus");
        params.add("operateRemark");

        ParamUtil<ConOperateHisEntity> paramUtil =  new ParamUtil<>();
        paramUtil.check(request.getEntity(), params);

        conOperateHisManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 非物理删除
     * @author wangchuang
     * @Date 2018/12/10 13:59
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody ConOperateHisServiceRequest request) {

        ConOperateHisServiceResponse response = new ConOperateHisServiceResponse();

        request.getEntity().setIsDeleted(true);
        request.getEntity().setCpyCode(CurrentContext.getCpyCode());
        conOperateHisManager.update(request.getEntity());

        return this.success(response);
    }

    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody ConOperateHisServiceRequest request) {
        ConOperateHisServiceResponse response = new ConOperateHisServiceResponse();

        conOperateHisManager.update(request.getEntity());

        return this.success(response);
    }

    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody ConOperateHisServiceRequest request) {
        ConOperateHisServiceResponse response = new ConOperateHisServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("ctCode", request.getEntity().getCtCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<ConOperateHisEntity> operateHisList = conOperateHisManager.findByMap(map, "id", false);

        response.setResult(operateHisList);
        return this.success(response);
    }

}
