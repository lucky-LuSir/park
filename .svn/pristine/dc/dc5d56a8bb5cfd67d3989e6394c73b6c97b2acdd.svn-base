package com.kfwy.park.controller.pro.feedback;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.feedback.vo.FeedbackReplyServiceRequest;
import com.kfwy.park.controller.pro.feedback.vo.FeedbackReplyServiceResponse;
import com.kfwy.park.pro.feedback.business.IFeedbackReplyManager;
import com.kfwy.park.pro.feedback.entity.FeedbackReplyEntity;
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
@RequestMapping("/feedbackReply")
public class FeedbackReplyService extends SpringRestService {

    @Autowired
    private IFeedbackReplyManager feedbackReplyManager;

    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody FeedbackReplyServiceRequest request) throws IllegalAccessException {
        FeedbackReplyServiceResponse response = new FeedbackReplyServiceResponse();

        FeedbackReplyEntity feedbackReplyEntity = request.getEntity();

        List<String> list = new ArrayList<>();
        list.add("fbCode");
        list.add("replyDescription");

        ParamUtil<FeedbackReplyEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(feedbackReplyEntity, list);

        feedbackReplyManager.create(feedbackReplyEntity);

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
    public ResponseEntity<IServiceResponse> update(@RequestBody FeedbackReplyServiceRequest request) throws IllegalAccessException {

        FeedbackReplyServiceResponse response = new FeedbackReplyServiceResponse();

        FeedbackReplyEntity feedbackReplyEntity = request.getEntity();

        List<String> list = new ArrayList();
        list.add("replyCode");
        list.add("fbCode");
        list.add("replyDescription");
        ParamUtil<FeedbackReplyEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(feedbackReplyEntity,list);

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
    public ResponseEntity<IServiceResponse> query(@RequestBody FeedbackReplyServiceRequest request) throws IllegalAccessException {

        FeedbackReplyServiceResponse response = new FeedbackReplyServiceResponse();

        FeedbackReplyEntity feedbackReplyEntity = request.getEntity();
        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<FeedbackReplyEntity> paramUtil = new ParamUtil<FeedbackReplyEntity>();
        paramUtil.objectToMap(feedbackReplyEntity, param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<FeedbackReplyEntity> result = feedbackReplyManager.findByMap(param, request.getStart(), request.getPageSize(), "id", false);
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
    public ResponseEntity<IServiceResponse> list(@RequestBody FeedbackReplyServiceRequest request) throws IllegalAccessException {

        FeedbackReplyServiceResponse response = new FeedbackReplyServiceResponse();

        FeedbackReplyEntity feedbackReplyEntity = request.getEntity();
        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<FeedbackReplyEntity> paramUtil = new ParamUtil<FeedbackReplyEntity>();
        paramUtil.objectToMap(feedbackReplyEntity, param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<FeedbackReplyEntity> result = feedbackReplyManager.findByMap(param, "id", false);
        response.setResult(result);

        return this.success(response);
    }
}
