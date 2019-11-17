package com.kfwy.park.controller.ati.agent;


import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.agent.business.IMiddlemanManager;
import com.kfwy.park.ati.agent.entity.MiddlemanEntity;
import com.kfwy.park.controller.ati.agent.vo.MiddleManServiceRequest;
import com.kfwy.park.controller.ati.agent.vo.MiddlemanServiceResponse;
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
 * @Description 中介联系人管理
 * @Auth xiongzhan
 * @Date 2018/6/2 10:41
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping("middleman")
public class MiddlemanService extends SpringRestService {

    @Autowired
    private IMiddlemanManager middlemanManager;

    /**
     * @Description 中介人新增
     * @Auth xiongzhan
     * @Date 2018/6/2 10:43
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody MiddleManServiceRequest request) {

        MiddlemanServiceResponse response = new MiddlemanServiceResponse();

        middlemanManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 中介人修改
     * @Auth xiongzhan
     * @Date 2018/6/2 10:41
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody MiddleManServiceRequest request){

        MiddlemanServiceResponse response = new MiddlemanServiceResponse();

        middlemanManager.update(request.getEntity());

        return this.success(response);

    }


    /**
     * @Description 中介人查询
     * @Auth xiongzhan
     * @Date 2018/6/2 10:42
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody MiddleManServiceRequest request){
        MiddlemanServiceResponse response = new MiddlemanServiceResponse();

        MiddlemanEntity middlemanEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("id",middlemanEntity.getId());
        param.put("middlemanCode",middlemanEntity.getMidCode());
        param.put("cpyCode",middlemanEntity.getCpyCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<MiddlemanEntity> result = middlemanManager.findByMap(param);

        response.setResult(result);

        return this.success(response);
    }


    /**
     * @Description 非物理删除
     * @Auth xiongzhan
     * @Date 2018/6/2 10:42
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody MiddleManServiceRequest request) {


        MiddlemanServiceResponse response = new MiddlemanServiceResponse();
        request.getEntity().setIsDeleted(true);
        request.getEntity().setCpyCode(CurrentContext.getCpyCode());
        middlemanManager.update(request.getEntity());

        return this.success(response);
    }

}
