package com.kfwy.park.controller.pro.feedback;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.feedback.vo.FeedbackServiceRequest;
import com.kfwy.park.controller.pro.feedback.vo.FeedbackServiceResponse;
import com.kfwy.park.pro.feedback.business.IFeedbackManager;
import com.kfwy.park.pro.feedback.entity.FeedbackEntity;
import org.apache.commons.lang3.StringUtils;
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
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@RestController
@RequestMapping("/feedback")
public class FeedbackService extends SpringRestService {


    @Autowired
    private IFeedbackManager feedbackManager;

    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody FeedbackServiceRequest request) throws Exception {
        FeedbackServiceResponse response = new FeedbackServiceResponse();

        FeedbackEntity feedbackEntity = request.getEntity();

        List<String> list = new ArrayList<>();
        list.add("fbType");
        list.add("fbDescription");
        list.add("askQuestionName");
        list.add("askQuestionPhone");

        ParamUtil<FeedbackEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(feedbackEntity, list);

        feedbackManager.create(feedbackEntity);

        return this.success(response);
    }

    /**
     * @Description 更新方法
     * @author wangchuang
     * @Date 2019/7/12 11:41
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody FeedbackServiceRequest request) throws IllegalAccessException {

        FeedbackServiceResponse response = new FeedbackServiceResponse();

        FeedbackEntity feedbackEntity = request.getEntity();

        if(!StringUtils.isEmpty(feedbackEntity.getDealQuestionCode())) {
            //当修改问题反馈时传入处理人不为空，则将状态改为2：已受理
            feedbackEntity.setFbStatus("2");
        }

        List<String> list = new ArrayList();
        list.add("fbCode");
        list.add("fbType");
        list.add("fbDescription");
        list.add("fbStatus");
        list.add("askQuestionName");
        list.add("askQuestionPhone");
        ParamUtil<FeedbackEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(feedbackEntity,list);

        feedbackManager.update(feedbackEntity);

        return this.success(response);

    }

    /**
     * @Description 分页查询
     * @author wangchuang
     * @Date 2019/7/12 11:19
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody FeedbackServiceRequest request) throws IllegalAccessException {

        FeedbackServiceResponse response = new FeedbackServiceResponse();

        FeedbackEntity feedbackEntity = request.getEntity();
        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<FeedbackEntity> paramUtil = new ParamUtil<FeedbackEntity>();
        paramUtil.objectToMap(feedbackEntity, param);
        param.put("keyword",request.getKeyword());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<FeedbackEntity> result = feedbackManager.findByMap(param, request.getStart(), request.getPageSize(), "id", false);
        response.setResult(result);

        return this.success(response);
    }


    /**
     * @Description 不分页的查询
     * @author wangchuang
     * @Date 2019/7/12 11:22
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody FeedbackServiceRequest request) throws IllegalAccessException {

        FeedbackServiceResponse response = new FeedbackServiceResponse();

        FeedbackEntity feedbackEntity = request.getEntity();
        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<FeedbackEntity> paramUtil = new ParamUtil<FeedbackEntity>();
        paramUtil.objectToMap(feedbackEntity, param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<FeedbackEntity> result = feedbackManager.findByMap(param, "id", false);
        response.setResult(result);

        return this.success(response);
    }

}
