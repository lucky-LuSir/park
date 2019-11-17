package com.kfwy.park.controller.pro.inspect;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.inspect.vo.InspectInfoServiceRequest;
import com.kfwy.park.controller.pro.inspect.vo.InspectInfoServiceResponse;
import com.kfwy.park.pro.inspect.business.IInspectInfoManager;
import com.kfwy.park.pro.inspect.entity.InspectInfoEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/inspectInfo")
public class InspectInfoService extends SpringRestService {
    @Autowired
    private IInspectInfoManager inspectInfoManager;

    @RequestMapping(path = "create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody InspectInfoServiceRequest request) throws IllegalAccessException {
        InspectInfoServiceResponse response = new InspectInfoServiceResponse();
        InspectInfoEntity inspectInfoEntity = request.getEntity();
        inspectInfoManager.checkParams(inspectInfoEntity);
        inspectInfoManager.createInfo(inspectInfoEntity);
        return this.success(response);
    }

    @RequestMapping(path = "list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody InspectInfoServiceRequest request) throws IllegalAccessException {
        InspectInfoServiceResponse response = new InspectInfoServiceResponse();
        InspectInfoEntity inspectInfoEntity = request.getEntity();
        ParamUtil<InspectInfoEntity> paramUtil = new ParamUtil<>();
        Map<String, Object> map = new HashMap<>();
        paramUtil.objectToMap(inspectInfoEntity, map);
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<InspectInfoEntity> list = inspectInfoManager.findByMap(map, "create_time", false);
        response.setResult(list);
        return this.success(response);
    }

    @RequestMapping(path = "query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody InspectInfoServiceRequest request) throws IllegalAccessException {
        InspectInfoServiceResponse response = new InspectInfoServiceResponse();
        InspectInfoEntity inspectInfoEntity = request.getEntity();
        ParamUtil<InspectInfoEntity> paramUtil = new ParamUtil<>();
        Map<String, Object> map = new HashMap<>();
        paramUtil.objectToMap(inspectInfoEntity, map);
        if(!StringUtils.isEmpty(request.getKeyword())){
            map.put("keyword",request.getKeyword());
        }
        if(null != request.getInspectStartTime() && null != request.getInspectEndTime()){
            Date startTime = DateFormatUtil.dayBegin(request.getInspectStartTime());
            Date endTime = DateFormatUtil.dayEnd(request.getInspectEndTime());
            map.put("inspectStartTime", startTime);
            map.put("inspectEndTime", endTime);
        }
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<InspectInfoEntity> list = inspectInfoManager.findByMap(map, request.getStart(), request.getPageSize(), "create_time", false);
        response.setResult(list);
        return this.success(response);
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-10 14:16:12
     *@Param inspectCode
     *@Description 获取包含树形score的评分信息
     *@Return
     **/
    @RequestMapping(path = "infoWithScoreTree"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> infoWithScoreTree(@RequestBody InspectInfoServiceRequest request) throws IllegalAccessException {
        InspectInfoServiceResponse response = new InspectInfoServiceResponse();
        InspectInfoEntity inspectInfoEntity = request.getEntity();
        if(StringUtils.isEmpty(inspectInfoEntity.getInspectCode())){
            throw new BusinessException("请输入评分编号!");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("inspectCode",inspectInfoEntity.getInspectCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        InspectInfoEntity result = inspectInfoManager.findInfoWithScoreTree(map);
        response.setResult(result);
        return this.success(response);
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-12 14:55:26
     *@Description 获取包含score信息的评分信息
     *@Return
     **/
    @RequestMapping(path = "infoWithScoreList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> infoWithScoreList(@RequestBody InspectInfoServiceRequest request) throws IllegalAccessException {
        InspectInfoServiceResponse response = new InspectInfoServiceResponse();
        InspectInfoEntity inspectInfoEntity = request.getEntity();
        if(StringUtils.isEmpty(inspectInfoEntity.getInspectCode())){
            throw new BusinessException("请输入评分编号!");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("inspectCode",inspectInfoEntity.getInspectCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        InspectInfoEntity result = inspectInfoManager.findInfoWithScoreList(map);
        response.setResult(result);
        return this.success(response);
    }

    @RequestMapping(path = "update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody InspectInfoServiceRequest request) throws IllegalAccessException {
        InspectInfoServiceResponse response = new InspectInfoServiceResponse();
        InspectInfoEntity inspectInfoEntity = request.getEntity();
        inspectInfoManager.checkParams(inspectInfoEntity);
        inspectInfoManager.updateInfo(inspectInfoEntity);
        return this.success(response);
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-15 08:51:42
     *@Description 虚拟删除检查评分信息
     *@Return
     **/
    @RequestMapping(path = "delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody InspectInfoServiceRequest request) throws IllegalAccessException {
        InspectInfoServiceResponse response = new InspectInfoServiceResponse();
        InspectInfoEntity inspectInfoEntity = request.getEntity();
        inspectInfoEntity.setIsDeleted(true);
        inspectInfoManager.update(inspectInfoEntity);
        return this.success(response);
    }
}
