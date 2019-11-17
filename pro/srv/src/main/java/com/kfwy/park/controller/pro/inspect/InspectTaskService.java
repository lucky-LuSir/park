package com.kfwy.park.controller.pro.inspect;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.inspect.vo.InspectTaskServiceRequest;
import com.kfwy.park.controller.pro.inspect.vo.InspectTaskServiceResponse;
import com.kfwy.park.pro.inspect.business.IInspectObjectRuleManager;
import com.kfwy.park.pro.inspect.business.IInspectTaskManager;
import com.kfwy.park.pro.inspect.entity.InspectObjectRuleEntity;
import com.kfwy.park.pro.inspect.entity.InspectTaskEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

@Controller
@RequestMapping("inspectTask")
public class InspectTaskService extends SpringRestService {
    @Autowired
    private IInspectTaskManager inspectTaskManager;
    @Autowired
    private IInspectObjectRuleManager inspectObjectRuleManager;

    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody InspectTaskServiceRequest request){
        InspectTaskServiceResponse response = new InspectTaskServiceResponse();
        InspectTaskEntity taskEntity = request.getEntity();
        if(StringUtils.isEmpty(taskEntity.getTaskName())){
            throw new BusinessException("请输入任务名称!");
        }
        taskEntity.setTaskCode(UUID.randomUUID().toString().replaceAll("-",""));
        inspectTaskManager.create(taskEntity);
        return this.success(response);
    }

    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody InspectTaskServiceRequest request) throws IllegalAccessException {
        InspectTaskServiceResponse response = new InspectTaskServiceResponse();
        InspectTaskEntity taskEntity = request.getEntity();
        ParamUtil<InspectTaskEntity> paramUtil = new ParamUtil<>();
        Map<String,Object> map = new HashMap<>();
        paramUtil.objectToMap(taskEntity, map);
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<InspectTaskEntity> list = inspectTaskManager.findByMap(map, "create_time", false);
        response.setResult(list);
        return this.success(response);
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-10 14:18:05
     *@Description 获取任务信息且包含树形检查事项
     *@Return
     **/
    @RequestMapping(path = "/treeList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> treeList(@RequestBody InspectTaskServiceRequest request) throws IllegalAccessException {
        InspectTaskServiceResponse response = new InspectTaskServiceResponse();
        InspectTaskEntity taskEntity = request.getEntity();
        Map<String,Object> map = new HashMap<>();
        map.put("taskCode",taskEntity.getTaskCode());
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        InspectTaskEntity task = inspectTaskManager.findOne(map);
        //根据taskCode获取树形检查事项
        InspectObjectRuleEntity objTree = inspectObjectRuleManager.objWithTreeByTaskCode(map);
        task.setObjectRuleTree(objTree);
        response.setResult(task);
        return this.success(response);
    }

    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody InspectTaskServiceRequest request) throws IllegalAccessException {
        InspectTaskEntity taskEntity = request.getEntity();
        taskEntity.setIsDeleted(true);
        inspectTaskManager.update(taskEntity);
        return this.success(new InspectTaskServiceResponse());
    }

    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody InspectTaskServiceRequest request) throws IllegalAccessException {
        InspectTaskEntity taskEntity = request.getEntity();
        List<String> list = new ArrayList<>();
        list.add("taskCode");
        list.add("taskName");
        ParamUtil<InspectTaskEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(taskEntity,list);
        inspectTaskManager.update(taskEntity);
        return this.success(new InspectTaskServiceResponse());
    }
}
