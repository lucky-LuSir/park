package com.kfwy.park.controller.pro.building;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.bi.report.business.IGrossBiManager;
import com.kfwy.park.bi.report.entity.GrossEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.building.vo.FloorServiceRequest;
import com.kfwy.park.controller.pro.building.vo.FloorServiceResponse;
import com.kfwy.park.pro.house.building.business.IFloorManager;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Description 楼层管理
 * @Auth wangchuang
 * @Date 2018/5/30 9:21
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/floor")
public class FloorService extends SpringRestService {

    @Autowired
    private IFloorManager floorManager;
    @Autowired
    private IGrossBiManager grossBiManager;


    /**
     * @Description 新增楼层管理信息
     * @Auth wangchuang
     * @Date 2018/6/12 15:21
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody FloorServiceRequest request) throws Exception {

        FloorServiceResponse response = new FloorServiceResponse();
        FloorEntity entity = request.getEntity();

        Boolean flag = floorManager.acreage(entity);
        if(flag){
            if (StringUtils.isEmpty(entity.getPkCode())) {
                return this.error(response,"园区编号不能为空");
            }
            if (StringUtils.isEmpty(entity.getBdCode())) {
                return this.error(response,"楼栋编号不能为空");
            }

            String flCode = CodeGenUtils.generate();
            entity.setFlCode(flCode);
            entity.setRentedArea(new BigDecimal("0"));

            List<String> list = new ArrayList();
            list.add("investArea");
            list.add("floorHeight");
            list.add("floorBearWeight");
            list.add("floorType");
            list.add("decorationType");
            list.add("floorDeep");
            list.add("pillarSpacing");
            list.add("useType");
            ParamUtil<FloorEntity> paramUtil = new ParamUtil<>();
            paramUtil.check(entity, list);
            floorManager.create(entity);
            response.setResult(flCode);
        } else {
            throw new BusinessException("楼层面积大于楼栋面积!");
        }
        return this.success(response);
    }

    /**
     * @Description 楼层管理信息非逻辑删除
     * @Auth wangchuang
     * @Date 2018/6/12 15:21
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody FloorServiceRequest request) {

        //响应的json实体类字段
        FloorServiceResponse response = new FloorServiceResponse();
        FloorEntity entity = request.getEntity();

        //param: 只存放逻辑删除需要参数
        FloorEntity param = new FloorEntity();
        if (!StringUtils.isEmpty(entity)) {
            if (!StringUtils.isEmpty(entity.getId()) || !StringUtils.isEmpty(entity.getFlCode())) {
                if (!StringUtils.isEmpty(entity.getId())) {
                    param.setId(entity.getId());
                }
                if (!StringUtils.isEmpty(entity.getFlCode())) {
                    param.setFlCode(entity.getFlCode());
                }
                param.setIsDeleted(true);

                floorManager.update(param);
                return this.success(response);
            }
        }
        return this.error(response,"删除失败，传入信息有误!");

    }

    /**
     * @Description 修改楼层信息
     * @Auth wangchuang
     * @Date 2018/6/12 15:22
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody FloorServiceRequest request) throws Exception {

        FloorServiceResponse response = new FloorServiceResponse();
        FloorEntity floorEntity = request.getEntity();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getFlCode())) {
            return this.error(response,"修改失败，传入信息有误!");
        }

        Boolean flag = floorManager.acreage(floorEntity);
        if (flag){
            List<String> list = new ArrayList();
            list.add("investArea");
            list.add("floorHeight");
            list.add("floorBearWeight");
            list.add("floorType");
            list.add("decorationType");
            list.add("floorDeep");
            list.add("pillarSpacing");
            list.add("useType");
            ParamUtil<FloorEntity> paramUtil = new ParamUtil<>();
            paramUtil.check(request.getEntity(), list);
            floorManager.update(request.getEntity());
            response.setResult(request.getEntity().getFlCode());
        } else {
            throw new BusinessException("楼层面积大于楼栋面积!");
        }
        return this.success(response);
    }

    /**
     * @Description 楼层查询返回分页
     * @Auth wangchuang
     * @Date 2018/6/12 15:22
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody FloorServiceRequest request) throws IllegalAccessException {

        FloorServiceResponse response = new FloorServiceResponse();
        //TODO 根据查询场景添加业务逻辑
        FloorEntity floorEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<FloorEntity> paramUtil = new ParamUtil<FloorEntity>();
        paramUtil.objectToMap(floorEntity,param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<FloorEntity> result = floorManager.findByMap(param,request.getStart(),request.getPageSize(),"create_time",false);
        response.setResult(result);

        return this.success(response);
    }
    /**
     * @Description 楼层查询返回list
     * @Auth wangchuang
     * @Date 2018/6/12 15:23
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryList(@RequestBody FloorServiceRequest request) throws IllegalAccessException {

        FloorServiceResponse response = new FloorServiceResponse();
        //TODO 根据查询场景添加业务逻辑
        FloorEntity floorEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<FloorEntity> paramUtil = new ParamUtil<FloorEntity>();
        paramUtil.objectToMap(floorEntity,param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<FloorEntity> result = floorManager.findByMap(param, "pk_code", true);
        response.setResult(result);

        return this.success(response);
    }

    /**
     * @Description 查询楼层详情
     * @Auth wangchuang
     * @Date 2018/6/25 11:18
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/queryDetails"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryDetails(@RequestBody FloorServiceRequest request) throws Exception {

        FloorServiceResponse response = new FloorServiceResponse();
        //TODO 根据查询场景添加业务逻辑
        FloorEntity floorEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<FloorEntity> paramUtil = new ParamUtil<FloorEntity>();
        paramUtil.objectToMap(floorEntity,param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        FloorEntity floorDetails = floorManager.findDetails(param);
        if (floorDetails != null) {
            Date now = new Date();
            Date monthBegin = DateFormatUtil.monthBegin(now);
            Date monthEnd = DateFormatUtil.monthEnd(now);

            GrossEntity grossEntity = grossBiManager.getFloorMonGross(floorDetails,monthBegin,monthEnd);
            floorDetails.setProjectGross(grossEntity.getProjectGross());
            floorDetails.setInvestGross(grossEntity.getInvestGross());
            response.setResult(floorDetails);
        } else {
            response.setResult(floorEntity);
        }

        return this.success(response);
    }
}
