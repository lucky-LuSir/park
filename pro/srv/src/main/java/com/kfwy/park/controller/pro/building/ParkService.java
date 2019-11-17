package com.kfwy.park.controller.pro.building;


import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.building.vo.ParkServiceRequest;
import com.kfwy.park.controller.pro.building.vo.ParkServiceResponse;
import com.kfwy.park.pro.house.building.business.IBuildingManager;
import com.kfwy.park.pro.house.building.business.IParkButtJointManager;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.entity.BuildingEntity;
import com.kfwy.park.pro.house.building.entity.ParkButtJointEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
 * @Description 园区管理
 * @Auth davidcun
 * @Date 2018/5/22 11:19
 * @Version 1.0
 * @Param
 * @return
 **/
@Controller
@RequestMapping("/park")
public class ParkService extends SpringRestService {

    @Autowired
    private IParkManager parkManager;

    @Autowired
    private IBuildingManager buildingManager;

    @Autowired
    private IParkButtJointManager parkButtJointManager;

    /**
     * @Description 新增园区信息
     * @Auth xiongzhan
     * @Date 2018/5/30 11:19
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ParkServiceRequest request) throws IllegalAccessException {
        ParkServiceResponse response = new ParkServiceResponse();
        ParkEntity parkEntity = request.getEntity();
        parkEntity.setPkCode(CodeGenUtils.generate());

        List<String> list = new ArrayList();
        list.add("pkName");
        list.add("acreage");
        list.add("investArea");
        list.add("areaRate");
        list.add("useElecTotal");
        list.add("shortestLease");
        list.add("parkingSpace");
        list.add("roadWidth");
        list.add("buildingTotal");
        list.add("longestLease");
        list.add("industryRestrictions");
        list.add("provinceCode");
        list.add("cityCode");
        list.add("regionCode");
        list.add("streetCode");
        list.add("address");
        list.add("businessStartDay");
        list.add("businessEndDay");
        list.add("meterReading");
        list.add("propertyCost");
        list.add("territoryType");
        ParamUtil<ParkEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(parkEntity, list);

        parkManager.create(parkEntity);
        response.setResult(parkEntity.getPkCode());
        return this.success(response);
    }

    /**
     * @Description 非物理删除
     * @Auth xiongzhan
     * @Date 2018/5/30 11:20
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody ParkServiceRequest request) {

        //响应的json实体类字段
        ParkServiceResponse response = new ParkServiceResponse();
        String msg = null;
        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getPkCode())) {
            this.error(response,"删除失败，传入信息有误!");
        }
        request.getEntity().setIsDeleted(true);
        parkManager.update(request.getEntity());
        return this.success(response);

    }

    /**
     * @Description 修改园区信息
     * @Auth xiongzhan
     * @Date 2018/5/30 11:20
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody ParkServiceRequest request) throws IllegalAccessException {

        ParkServiceResponse response = new ParkServiceResponse();
        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getPkCode())) {
            this.error(response,"修改失败，传入信息有误!");
        }

        List<String> list = new ArrayList();
        list.add("pkName");
        list.add("acreage");
        list.add("investArea");
        list.add("areaRate");
        list.add("useElecTotal");
        list.add("shortestLease");
        list.add("parkingSpace");
        list.add("roadWidth");
        list.add("buildingTotal");
        list.add("longestLease");
        list.add("industryRestrictions");
        list.add("provinceCode");
        list.add("cityCode");
        list.add("regionCode");
        list.add("streetCode");
        list.add("address");
        list.add("businessStartDay");
        list.add("businessEndDay");
        list.add("meterReading");
        list.add("propertyCost");
        ParamUtil<ParkEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(), list);
        parkManager.update(request.getEntity());
        return this.success(response);
    }

    /**
     * @Description 园区查询
     * @Auth xiongzhan
     * @Date 2018/5/30 11:20
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ParkServiceRequest request) throws IllegalAccessException{

        ParkServiceResponse response = new ParkServiceResponse();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<ParkEntity> paramUtil = new ParamUtil<ParkEntity>();
        paramUtil.objectToMap(request.getEntity(),param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<ParkEntity> result = parkManager.findByMap(param,request.getStart(),request.getPageSize(),"create_time",false);
        response.setResult(result);

        return this.success(response);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    /**
     * @Description 通过pkcode查询单个园区地址，再通过pkcode查询建筑信息
     * @Auth xiongzhan
     * @Date 2018/5/30 11:21
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/selectByPkCode"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectByPkCode(@RequestBody ParkServiceRequest request) throws Exception {
        ParkServiceResponse response = new ParkServiceResponse();
        Map<String,Object> param=new HashMap<>();

        ParamUtil<ParkEntity> paramUtil = new ParamUtil<ParkEntity>();
        paramUtil.objectToMap(request.getEntity(),param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        ParkEntity park = parkManager.details(param);
        String parkAddress=park.getProvinceName()+park.getCityName()+park.getRegionName()+park.getStreetName()+park.getAddress();

        List<BuildingEntity> buildingEntities=buildingManager.findByMap(param);

        Map<String,Object> result=new HashMap<>();
        result.put("pkName",park.getPkName());
        result.put("parkAddress",parkAddress);
        result.put("buildingEntities",buildingEntities);
        result.put("acreage",park.getAcreage());//承租总面积
        result.put("investArea",park.getInvestArea());//计租总面积
        result.put("rentableArea",park.getRentableArea());//剩余计租面积
        result.put("rentInvestArea",park.getRentInvestArea());//已租计租面积
        response.setResult(result);

        return this.success(response);
    }


    /*
     * @Description 查询list集合
     * @Auth liuzhengyang
     * @Date 2018/7/6 10:23
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody ParkServiceRequest request) throws IllegalAccessException {

        ParkServiceResponse response = new ParkServiceResponse();
        Map<String,Object> map=new HashMap<>();
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<ParkEntity> paramUtil = new ParamUtil<ParkEntity>();
        paramUtil.objectToMap(request.getEntity(),map);

        List<ParkEntity> pkList =  parkManager.findByMap(map);

        response.setResult(pkList);
        return this.success(response);
    }


    /**
     * @Description 详情页查询园区及其他信息
     * @Auth xiongzhan
     * @Date 2018/5/30 11:21
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/details"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> details(@RequestBody ParkServiceRequest request)  {

        ParkServiceResponse response = new ParkServiceResponse();
        Map<String,Object> param = new HashMap<String,Object>();
        try {
            ParamUtil<ParkEntity> paramUtil = new ParamUtil<ParkEntity>();
            paramUtil.objectToMap(request.getEntity(),param);
            param.put("isDeleted",false);
            param.put("cpyCode", CurrentContext.getCpyCode());

            ParkEntity park = parkManager.details(param);

            Map<String,Object> paramm = new HashMap<String,Object>();
            paramm.put("pkCode",park.getPkCode());
            paramm.put("buttJointType","1");
            paramm.put("isDeleted",false);
            paramm.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkButtJointEntity> atiButtJointList = parkButtJointManager.selectByPkCode(paramm);
            park.setAtiButtJointList(atiButtJointList);

            Map<String,Object> para = new HashMap<String,Object>();
            para.put("pkCode",park.getPkCode());
            para.put("buttJointType","2");
            para.put("isDeleted",false);
            para.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkButtJointEntity> proButtJointList = parkButtJointManager.selectByPkCode(para);
            park.setProButtJointList(proButtJointList);

            response.setResult(park);
        }catch (Exception e){
            this.error(response,"pkCode不唯一");
        }
        return this.success(response);
    }

    /**
     * @Description 查询所有园区总面积，可租用面积之和
     * @Auth luming
     * @Date 2018/9/25 15:09
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/getAllParkSum"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> getAllParkSum(@RequestBody ParkServiceRequest request) throws IllegalAccessException {

        ParkServiceResponse response = new ParkServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<ParkEntity> paramUtil = new ParamUtil<ParkEntity>();
        paramUtil.objectToMap(request.getEntity(), map);

        ParkEntity allParkSum = parkManager.getAllParkSum(map);

        response.setResult(allParkSum);
        return this.success(response);
    }

    /**
     * @Description 查询园区所属城市
     * @Auth luming
     * @Date 2018/10/17 11:18
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/selectParkCity"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectParkCity(@RequestBody ParkServiceRequest request) throws IllegalAccessException {

        ParkServiceResponse response = new ParkServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("cityCode", request.getEntity().getCityCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<ParkEntity> paramUtil = new ParamUtil<ParkEntity>();
        paramUtil.objectToMap(request.getEntity(), map);

        List<ParkEntity> parkList = parkManager.selectParkCity(map);

        response.setResult(parkList);
        return this.success(response);
    }

    /**
     * 查询所有大区
     */
    @RequestMapping(path = "/selectTerritory"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectTerritory(@RequestBody ParkServiceRequest request) throws IllegalAccessException {
        ParkServiceResponse response = new ParkServiceResponse();
        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        ParamUtil<ParkEntity> paramUtil = new ParamUtil<ParkEntity>();
        paramUtil.objectToMap(request.getEntity(), map);

        List<ParkEntity> territoryList = parkManager.selectAllTerritory(map);
        territoryList.removeAll(Collections.singleton(null));
        response.setResult(territoryList);
        return this.success(response);
    }

}
