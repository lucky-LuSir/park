package com.kfwy.park.controller.sys.msg;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.controller.sys.msg.vo.BargainMsgServiceRequest;
import com.kfwy.park.controller.sys.msg.vo.BargainMsgServiceResponse;
import com.kfwy.park.sys.msg.business.IBargainMsgManager;
import com.kfwy.park.sys.msg.entity.BargainMsgEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luming on 2019/6/21.
 */
@RequestMapping(path = "/bargain")
@Controller
public class BargainMsgService extends SpringRestService {
    @Autowired
    private IBargainMsgManager bargainMsgManager;

    /**
     * @Description 新增成交提醒
     * @author luming
     * @Date 2019/6/21 15:56
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody BargainMsgServiceRequest request) throws Exception {
        BargainMsgServiceResponse response = new BargainMsgServiceResponse();

        BargainMsgEntity bargainMsgEntity = request.getEntity();
        bargainMsgEntity.setBmCode(CodeGenUtils.generate());

        bargainMsgManager.checkParams(bargainMsgEntity);

        bargainMsgManager.create(bargainMsgEntity);

        return this.success(response);
    }

    /**
     * @Description 修改成交信息
     * @author luming
     * @Date 2019/6/21 15:56
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody BargainMsgServiceRequest request) throws Exception {
        BargainMsgServiceResponse response = new BargainMsgServiceResponse();

        BargainMsgEntity bargainMsgEntity = request.getEntity();

        bargainMsgManager.checkParams(bargainMsgEntity);

        bargainMsgManager.update(bargainMsgEntity);

        return this.success(response);
    }

    /**
     * @Description 分页查询
     * @author luming
     * @Date 2019/6/24 9:02
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody BargainMsgServiceRequest request) throws Exception {
        BargainMsgServiceResponse response = new BargainMsgServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<BargainMsgEntity> result = bargainMsgManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询详情
     * @author luming
     * @Date 2019/6/24 9:02
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/detail"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody BargainMsgServiceRequest request) throws Exception {
        BargainMsgServiceResponse response = new BargainMsgServiceResponse();

        BargainMsgEntity bargainMsgEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("bmCode", bargainMsgEntity.getBmCode());

        BargainMsgEntity entity = bargainMsgManager.findOne(map);

        response.setResult(entity);
        return this.success(response);
    }
}
