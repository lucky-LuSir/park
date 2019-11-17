package com.kfwy.park.controller.sys.board;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.sys.board.vo.ProgramServiceRequest;
import com.kfwy.park.controller.sys.board.vo.ProgramServiceResponse;
import com.kfwy.park.sys.board.business.IProgramManager;
import com.kfwy.park.sys.board.entity.ProgramEntity;
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
 * Created by liuzhengyang on 2018/7/25.
 */
@RestController
@RequestMapping("/program")
public class ProgramService extends SpringRestService {

    @Autowired
    private IProgramManager programManager;

    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ProgramServiceRequest request) {
        ProgramServiceResponse response = new ProgramServiceResponse();

        programManager.create(request.getEntity());

        return this.success(response);
    }

    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody ProgramServiceRequest request){

        ProgramServiceResponse response = new ProgramServiceResponse();

        programManager.update(request.getEntity());

        return this.success(response);

    }

    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ProgramServiceRequest request) throws IllegalAccessException {
        ProgramServiceResponse response = new ProgramServiceResponse();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<ProgramEntity> paramUtil = new ParamUtil<ProgramEntity>();
        paramUtil.objectToMap(request.getEntity(),param);

        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<ProgramEntity> result = programManager.findByMap(param,request.getStart(),request.getPageSize(),"id",false);
        response.setResult(result);

        return this.success(response);
    }

    @RequestMapping(path = "/list",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody ProgramServiceRequest request) throws IllegalAccessException {
        ProgramServiceResponse response = new ProgramServiceResponse();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<ProgramEntity> paramUtil = new ParamUtil<ProgramEntity>();
        paramUtil.objectToMap(request.getEntity(),param);

        param.put("isDeleted",false);

        List<ProgramEntity> result = programManager.findByMap(param, "id", true);
        response.setResult(result);

        return this.success(response);
    }

    /**
     * @Description 根据关键字模糊查询
     * @author wangchuang
     * @Date 2018/9/28 13:48
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/selectBoardByKeyWord",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectByKeyWord(@RequestBody ProgramServiceRequest request) {
        ProgramServiceResponse response = new ProgramServiceResponse();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("keyword",request.getKeyword());
        map.put("isDeleted",false);

        List<ProgramEntity> result = programManager.findByMap(map);
        response.setResult(result);

        return this.success(response);

    }


}
