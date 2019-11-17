package com.kfwy.park.controller.ati;


import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.vo.TaskServiceRequest;
import com.kfwy.park.controller.ati.vo.TaskServiceResponse;
import com.kfwy.park.task.attract.business.ITodayTaskManager;
import com.kfwy.park.task.attract.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

/**
 * @Description 每日任务管理
 * @Auth xiongzhan
 * @Date 2018/8/3 9:54
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping(path = "/task")
public class TodayTaskService extends SpringRestService {

    @Autowired
    private ITodayTaskManager taskManager;


    /**
     * @Description 新增
     * @Auth xiongzhan
     * @Date 2018/8/3 10:45
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody TaskServiceRequest request) throws IllegalAccessException {

        TaskServiceResponse response = new TaskServiceResponse();
        TaskEntity taskEntity = request.getEntity();

        List<String> list = new ArrayList<>();
        list.add("banner");
        list.add("leaflets");
        list.add("friendsShare");
        list.add("pkCode");
        ParamUtil<TaskEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(taskEntity, list);

        taskEntity.setTaskCode(CodeGenUtils.generate());
        taskManager.create(taskEntity);
        response.setResult(taskEntity.getTaskCode());
        return this.success(response);
    }

    /**
     * @Description 非物理删除
     * @Auth xiongzhan
     * @Date 2018/8/3 10:45
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody TaskServiceRequest request) {

        //响应的json实体类字段
        TaskServiceResponse response = new TaskServiceResponse();
        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getTaskCode())) {
            return this.error(response,"删除失败，传入信息有误!");
        }
        request.getEntity().setIsDeleted(true);
        taskManager.update(request.getEntity());

        return this.success(response);

    }

    /**
     * @Description 修改信息
     * @Auth xiongzhan
     * @Date 2018/8/3 10:45
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody TaskServiceRequest request) throws IllegalAccessException {

        TaskServiceResponse response = new TaskServiceResponse();

        List<String> list = new ArrayList<>();
        list.add("banner");
        list.add("leaflets");
        list.add("friendsShare");
        ParamUtil<TaskEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(), list);

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getTaskCode())) {
            return this.error(response,"修改失败，传入信息有误!");
        }
        taskManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 任务查询
     * @Auth xiongzhan
     * @Date 2018/8/3 10:17
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody TaskServiceRequest request) throws IllegalAccessException {

        TaskServiceResponse response = new TaskServiceResponse();

        Date startDate = null;// 开始日期
        Date endDate = null;// 结束日期

        Map<String,Object> param = new HashMap<String,Object>();

        ParamUtil<TaskEntity> paramUtil = new ParamUtil<TaskEntity>();
        paramUtil.objectToMap(request.getEntity(),param);
        param.put("isDeleted",false);

        if (request.getEntity().getCreateDeptCode() != null && !request.getEntity().getCreateDeptCode().equals("")) {
            param.put("createDeptCode", request.getEntity().getCreateDeptCode());
        }

        if (request.getEntity().getTaskTimeList() != null) {
            startDate = DateFormatUtil.dayBegin(request.getEntity().getTaskTimeList().get(0));
            endDate = DateFormatUtil.dayEnd(request.getEntity().getTaskTimeList().get(1));
            param.put("taskTimeStart", startDate);
            param.put("taskTimeEnd", endDate);
        }
        param.put("cpyCode",CurrentContext.getCpyCode());

        PageResult<TaskEntity> result = taskManager.findByMap(param,request.getStart(),request.getPageSize(),"id",false);
        response.setResult(result);

        return this.success(response);

    }
    /**
     * @Description 首页每日任务看板查询接口,根据deptCode查询数据
     * @Auth xiongzhan
     * @Date 2018/8/7 10:17
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/select"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> select(@RequestBody TaskServiceRequest request) throws IllegalAccessException {

        TaskServiceResponse response = new TaskServiceResponse();

        Date date = new Date();
        Date firstDate = DateFormatUtil.monthBegin(date);// 本月第一天
        Date endDate = DateFormatUtil.monthEnd(date);// 本月最后一天

        List<TaskEntity> taskList = taskManager.select(request.getEntity());

        Map<String, Object> map = new HashMap<>();
        map.put("taskList", taskList);
        map.put("firstDate", DateFormatUtil.dayBegin(firstDate));
        map.put("endDate", DateFormatUtil.dayEnd(endDate));

        response.setResult(map);

        return this.success(response);
    }



    /**
     * @Description 通过empCode查询该员工当日有没有任务，有修改，没有新增
     * @Auth xiongzhan
     * @Date 2018/8/3 10:17
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/insert"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> insert(@RequestBody TaskServiceRequest request) throws IllegalAccessException {

        TaskServiceResponse response = new TaskServiceResponse();

        TaskEntity taskEntity = request.getEntity();
        List<String> list = new ArrayList<>();
        list.add("banner");
        list.add("leaflets");
        list.add("friendsShare");
        ParamUtil<TaskEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(taskEntity, list);

        taskEntity.setCpyName(CurrentContext.getUserInfo().getCpyName());
        taskEntity.setCreateName(CurrentContext.getUserInfo().getCreateName());
        taskEntity.setLastUpdateName(CurrentContext.getUserInfo().getLastUpdateName());

        taskManager.insert(taskEntity);
        return this.success(response);
    }
}

