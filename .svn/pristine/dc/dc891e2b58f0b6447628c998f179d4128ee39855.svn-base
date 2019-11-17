package com.kfwy.park.controller.sys.sms;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.controller.sys.sms.vo.SmsSendHistoryServiceRequest;
import com.kfwy.park.controller.sys.sms.vo.SmsSendHistoryServiceResponse;
import com.kfwy.park.sys.sms.business.ISmsSendHistoryManager;
import com.kfwy.park.sys.sms.entity.SmsSendHistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 历史发送记录
 * @Auth luming
 * @Date 2019/7/27 14:14
 * @Version 1.0
 * @Param
 * @return
 */
@RequestMapping(path = "/smsSendHistory")
@Controller
public class SmsSendHistoryService extends SpringRestService {
    @Autowired
    private ISmsSendHistoryManager smsSendHistoryManager;

    /**
     * @Description 分页查询
     * @Auth luming
     * @Date 2019/7/11 13:49
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody SmsSendHistoryServiceRequest request) throws Exception {
        SmsSendHistoryServiceResponse response = new SmsSendHistoryServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        if (request.getSendDateStart() != null && request.getSendDateEnd() != null) {
            Date sendDateStart = DateFormatUtil.dayBegin(request.getSendDateStart());
            Date sendDateEnd = DateFormatUtil.dayEnd(request.getSendDateEnd());

            map.put("sendDateStart", sendDateStart);
            map.put("sendDateEnd", sendDateEnd);
        }

        PageResult<SmsSendHistoryEntity> result = smsSendHistoryManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(result);
        return this.success(response);
    }
}
