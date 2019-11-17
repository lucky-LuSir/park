package com.kfwy.park.controller.pro.inspect;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.entity.Cascader;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.inspect.vo.InspectObjectRuleServiceRequest;
import com.kfwy.park.controller.pro.inspect.vo.InspectObjectRuleServiceResponse;
import com.kfwy.park.pro.inspect.business.IInspectObjectRuleManager;
import com.kfwy.park.pro.inspect.entity.InspectObjectRuleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping("/inspectObjectRule")
public class InspectObjectRuleService extends SpringRestService {
    @Autowired
    private IInspectObjectRuleManager inspectObjectRuleManager;

    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody InspectObjectRuleServiceRequest request) throws IllegalAccessException {
        InspectObjectRuleEntity objectRuleEntity = request.getEntity();
        List<String> list = new ArrayList<>();
        list.add("objRlName");
        list.add("type");
        list.add("parentCode");
        list.add("templateCode");
        ParamUtil<InspectObjectRuleEntity> paramUtil = new ParamUtil();
        paramUtil.check(objectRuleEntity, list);
        objectRuleEntity.setObjRlCode(UUID.randomUUID().toString().replaceAll("-",""));
        inspectObjectRuleManager.create(objectRuleEntity);
        return this.success(new InspectObjectRuleServiceResponse());
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-08 14:43:32
     *@Description 将模板中的事项规则以cascader树形返回
     *@Return
     **/
    @RequestMapping(path = "/cascader"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cascader(@RequestBody InspectObjectRuleServiceRequest request) throws IllegalAccessException {
        InspectObjectRuleServiceResponse response = new InspectObjectRuleServiceResponse();
        InspectObjectRuleEntity objectRuleEntity = request.getEntity();
        List<InspectObjectRuleEntity> objectRuleEntityList = getList(objectRuleEntity);
        Cascader casecader = inspectObjectRuleManager.ObjRuleCascader(objectRuleEntityList);
        response.setResult(casecader);
        return this.success(response);
    }

    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody InspectObjectRuleServiceRequest request) throws IllegalAccessException {
        InspectObjectRuleServiceResponse response = new InspectObjectRuleServiceResponse();
        InspectObjectRuleEntity objectRuleEntity = request.getEntity();
        List<InspectObjectRuleEntity> objectRuleEntityList = getList(objectRuleEntity);
        response.setResult(objectRuleEntityList);
        return this.success(response);
    }

    protected List<InspectObjectRuleEntity> getList(InspectObjectRuleEntity objectRuleEntity) throws IllegalAccessException {
        Map<String, Object> param = new HashMap<>();
        ParamUtil<InspectObjectRuleEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(objectRuleEntity, param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<InspectObjectRuleEntity> objectRuleEntityList = inspectObjectRuleManager.findByMap(param,"create_time", true);
        return objectRuleEntityList;
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-08 15:31:42
     *@Description 获取任务的事项规则
     *@Return
     **/
    @RequestMapping(path = "/taskObjRule"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> taskObjRule(@RequestBody InspectObjectRuleServiceRequest request){
        String taskCode = request.getTaskCode();
        if (StringUtils.isEmpty(taskCode)){
            throw new BusinessException("任务编号为空！");
        }
        Map<String, Object> map = new HashMap<>();
        map.put("taskCode", taskCode);
        map.put("isDeleted",false);
        map.put("cpyCode",CurrentContext.getCpyCode());
        List<InspectObjectRuleEntity> list = inspectObjectRuleManager.findByTaskCode(map);
        InspectObjectRuleServiceResponse response = new InspectObjectRuleServiceResponse();
        response.setResult(list);
        return this.success(response);
    }

    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody InspectObjectRuleServiceRequest request){
        InspectObjectRuleEntity objectRuleEntity = request.getEntity();
        inspectObjectRuleManager.deleteObj(objectRuleEntity);
        return this.success(new InspectObjectRuleServiceResponse());
    }

    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody InspectObjectRuleServiceRequest request){
        InspectObjectRuleEntity objectRuleEntity = request.getEntity();
        inspectObjectRuleManager.update(objectRuleEntity);
        return this.success(new InspectObjectRuleServiceResponse());
    }
}
