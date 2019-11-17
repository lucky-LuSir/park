package com.kfwy.park.controller.exp.building;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.exp.building.vo.InvparkFollowupServiceRequest;
import com.kfwy.park.controller.exp.building.vo.InvparkFollowupServiceResponse;
import com.kfwy.park.exp.house.building.business.IInvparkFollowupManager;
import com.kfwy.park.exp.house.building.entity.InvparkFollowupEntity;
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
 * @Description 拓展跟进
 * @Auth luming
 * @Date 2018/11/7 10:44
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping("/invparkFollowup")
public class InvparkFollowupService extends SpringRestService {

    @Autowired
    private IInvparkFollowupManager invparkFollowupManager;

    /**
     * @Description 新增
     * @Auth luming
     * @Date 2018/11/7 10:53
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody InvparkFollowupServiceRequest request) throws IllegalAccessException {

        InvparkFollowupServiceResponse response = new InvparkFollowupServiceResponse();
        InvparkFollowupEntity InvparkFollowupEntity = request.getEntity();

        String followupCode = CodeGenUtils.generate();

        List<String> params = new ArrayList<>();
        params.add("expStatus");
        params.add("followupResult");

        ParamUtil<InvparkFollowupEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(InvparkFollowupEntity, params);

        InvparkFollowupEntity.setFollowupCode(followupCode);
        InvparkFollowupEntity.setOwnerDeptCode(CurrentContext.getUserInfo().getOwnerDeptCode());
        InvparkFollowupEntity.setOwnerDeptName(CurrentContext.getUserInfo().getOwnerDeptName());

        invparkFollowupManager.create(InvparkFollowupEntity);

        response.setResult(InvparkFollowupEntity);

        return this.success(response);
    }

    /**
     * @Description 修改
     * @Auth luming
     * @Date 2018/11/7 10:53
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody InvparkFollowupServiceRequest request) throws IllegalAccessException {

        InvparkFollowupServiceResponse response = new InvparkFollowupServiceResponse();
        InvparkFollowupEntity InvparkFollowupEntity = request.getEntity();

        invparkFollowupManager.update(InvparkFollowupEntity);

        return this.success(response);
    }

    /**
     * @Description 分页查询
     * @Auth luming
     * @Date 2018/11/7 10:59
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody InvparkFollowupServiceRequest request) throws IllegalAccessException {

        InvparkFollowupServiceResponse response = new InvparkFollowupServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<InvparkFollowupEntity> result = invparkFollowupManager.findByMap(map, request.getStart(), request.getPageSize(), "id", true);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询详情
     * @Auth luming
     * @Date 2018/11/7 11:20
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/detail"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody InvparkFollowupServiceRequest request) throws IllegalAccessException {

        InvparkFollowupServiceResponse response = new InvparkFollowupServiceResponse();

        InvparkFollowupEntity InvparkFollowupEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted",false);
        map.put("itemCode", InvparkFollowupEntity.getItemCode());
        map.put("cpyCode", CurrentContext.getCpyCode());

        InvparkFollowupEntity entity = invparkFollowupManager.findOne(map);

        response.setResult(entity);
        return this.success(response);
    }

    /**
     * @Description 跟进集合
     * @Auth luming
     * @Date 2018/11/7 11:20
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody InvparkFollowupServiceRequest request) throws IllegalAccessException {

        InvparkFollowupServiceResponse response = new InvparkFollowupServiceResponse();

        InvparkFollowupEntity InvparkFollowupEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted",false);
        map.put("itemCode", InvparkFollowupEntity.getItemCode());
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<InvparkFollowupEntity> list = invparkFollowupManager.findByMap(map);

        response.setResult(list);
        return this.success(response);
    }
}
