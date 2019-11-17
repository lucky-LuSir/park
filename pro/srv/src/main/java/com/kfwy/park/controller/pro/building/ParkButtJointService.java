package com.kfwy.park.controller.pro.building;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.building.vo.ParkButtJointServiceRequest;
import com.kfwy.park.controller.pro.building.vo.ParkButtJointServiceResponse;
import com.kfwy.park.pro.house.building.business.IParkButtJointManager;
import com.kfwy.park.pro.house.building.entity.ParkButtJointEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 园区对接人
 * @Auth xiongzhan
 * @Date 2018/6/26 15:21
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/parkButtJoint")
public class ParkButtJointService extends SpringRestService {

    @Autowired
    private IParkButtJointManager parkButtJointManager;


    /**
     * @Description 新增园区对接人
     * @Auth xiongzhan
     * @Date 2018/6/26 15:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ParkButtJointServiceRequest request) {

        ParkButtJointServiceResponse response = new ParkButtJointServiceResponse();
        //TODO 根据业务需求对相应字段校验
        ParkButtJointEntity parkButtJointEntity = request.getEntity();
        parkButtJointEntity.setPbjCode(CodeGenUtils.generate());
        parkButtJointManager.create(parkButtJointEntity);
        response.setResult(parkButtJointEntity.getPkCode());
        return this.success(response);
    }

    /**
     * @Description 非物理删除
     * @Auth xiongzhan
     * @Date 2018/6/26 15:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody ParkButtJointServiceRequest request) throws IllegalAccessException {

        //响应的json实体类字段
        ParkButtJointServiceResponse response = new ParkButtJointServiceResponse();
        request.getEntity().setIsDeleted(true);
        parkButtJointManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 修改园区对接人信息
     * @Auth xiongzhan
     * @Date 2018/6/26 15:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody ParkButtJointServiceRequest request) {

        ParkButtJointServiceResponse response = new ParkButtJointServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getPbjCode())) {
            return this.error(response,"修改失败，传入信息有误!");
        }
        parkButtJointManager.update(request.getEntity());

        return this.success(response);

    }

    /**
     * @Description 园区对接人查询
     * @Auth xiongzhan
     * @Date 2018/6/26 15:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ParkButtJointServiceRequest request) throws IllegalAccessException {

        ParkButtJointServiceResponse response = new ParkButtJointServiceResponse();
        ParkButtJointEntity pbjEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<ParkButtJointEntity> paramUtil =  new ParamUtil<ParkButtJointEntity>();
        paramUtil.objectToMap(pbjEntity,param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<ParkButtJointEntity> result = parkButtJointManager.findByMap(param);
        response.setResult(result);

        return this.success(response);
    }

    @RequestMapping(path = "/queryPkcode"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryEmpGetpkCode(@RequestBody ParkButtJointServiceRequest request) throws IllegalAccessException {

        ParkButtJointServiceResponse response = new ParkButtJointServiceResponse();
        ParkButtJointEntity pbjEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<ParkButtJointEntity> paramUtil =  new ParamUtil<ParkButtJointEntity>();
        paramUtil.objectToMap(pbjEntity,param);
        //根据前台请求中的tokey,用后台全局对象获取当前用户部门code
        String empCode= CurrentContext.getUserInfo().getUserCode();
        param.put("empCode",empCode);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<ParkButtJointEntity> result = parkButtJointManager.findByMap(param);

        response.setResult(result);

        return this.success(response);
    }
    /**
     * @Description 园区对接人查询,通过关联查询查询人员信息
     * @Auth xiongzhan
     * @Date 2018/8/8 15:26
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/selectByPkCode"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectByPkCode(@RequestBody ParkButtJointServiceRequest request) throws IllegalAccessException {

        ParkButtJointServiceResponse response = new ParkButtJointServiceResponse();
        //TODO 根据查询场景添加业务逻辑
        ParkButtJointEntity pbjEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<ParkButtJointEntity> paramUtil =  new ParamUtil<ParkButtJointEntity>();
        paramUtil.objectToMap(pbjEntity,param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<ParkButtJointEntity> result = parkButtJointManager.selectByPkCode(param);
        response.setResult(result);

        return this.success(response);
    }

    /**
     * @Description 在招商管理页面绑定园区
     * @author wangchuang
     * @Date 2018/9/19 18:52
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/createByPkCode"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createByPkCode(@RequestBody ParkButtJointServiceRequest request) {
        ParkButtJointServiceResponse response = new ParkButtJointServiceResponse();

        ParkButtJointEntity pjbEntity = request.getEntity();
        if (StringUtils.isEmpty(pjbEntity.getPkCode())) {
            throw new BusinessException("招商管理绑定的园区信息有误");
        }
        if (StringUtils.isEmpty(pjbEntity.getEmpCode())) {
            throw new BusinessException("招商管理绑定的人员信息有误");
        }

        parkButtJointManager.createByPkCode(pjbEntity);

        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-06-14 10:18:11
     *@Description 保安设置默认园区
     *@Return
     **/
    @RequestMapping(path = "/createDefaultPk"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createDefaultPk(@RequestBody ParkButtJointServiceRequest request) {
        ParkButtJointServiceResponse response = new ParkButtJointServiceResponse();

        ParkButtJointEntity pjbEntity = request.getEntity();
        Map<String, Object> param = new HashMap<>();
        param.put("empCode",pjbEntity.getEmpCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<ParkButtJointEntity> list = parkButtJointManager.findByMap(param);
        if(list.size() > 0){
            pjbEntity.setId(list.get(0).getId());
            parkButtJointManager.update(pjbEntity);
        }else{
            pjbEntity.setPbjCode(CodeGenUtils.generate());
            parkButtJointManager.create(pjbEntity);
        }
        return this.success(response);
    }
}
