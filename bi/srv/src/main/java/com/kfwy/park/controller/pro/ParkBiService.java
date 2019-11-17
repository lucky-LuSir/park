package com.kfwy.park.controller.pro;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.bi.report.business.IGrossBiManager;
import com.kfwy.park.bi.report.entity.GrossEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.vo.ParkBiServiceRequest;
import com.kfwy.park.controller.pro.vo.ParkBiServiceResponse;
import com.kfwy.park.pro.house.bi.business.IParkBiManager;
import com.kfwy.park.pro.house.bi.entity.ParkBiEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/*
 * @Description 园区统计列表
 * @Auth liuzhengyang
 * @Date 2018/6/26 18:22
 * @Version 1.0
 * @Param
 * @return
 */
@Controller
@RequestMapping("/parkBi")
public class ParkBiService extends SpringRestService {

    @Autowired
    private IParkBiManager parkBiManager;
    @Autowired
    private IGrossBiManager grossBiManager;


    /*
     * @Description TODO
     * @Auth liuzhengyang
     * @Date 2018/6/26 18:35
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ParkBiServiceRequest request) {

        ParkBiServiceResponse response = new ParkBiServiceResponse();

        //TODO 实际业务代码

        ParkBiEntity parkBiEntity = request.getEntity();
        parkBiManager.create(parkBiEntity);

        return this.success(response);
    }


    /*
     * @Description TODO
     * @Auth liuzhengyang
     * @Date 2018/6/26 18:35
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody ParkBiServiceRequest request) {
        //TODO 实际业务
        ParkBiServiceResponse response = new ParkBiServiceResponse();

        request.getEntity().setIsDeleted(true);
        parkBiManager.update(request.getEntity());

        return this.success(response);
    }


    /*
     * @Description TODO
     * @Auth liuzhengyang
     * @Date 2018/6/26 18:35
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody ParkBiServiceRequest request) {

        ParkBiServiceResponse response = new ParkBiServiceResponse();

        parkBiManager.update(request.getEntity());

        return this.success(response);
    }


    /*
     * @Description 园区统计列表
     * @Auth liuzhengyang
     * @Date 2018/6/26 18:35
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ParkBiServiceRequest request) {

        ParkBiServiceResponse response = new ParkBiServiceResponse();

        ParkBiEntity parkBiEntity = request.getEntity();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", parkBiEntity.getId());

        if (!StringUtils.isEmpty(request.getEntity().getCityName())) {
            param.put("cityName",request.getEntity().getCityName());
        }
        if (!StringUtils.isEmpty(request.getEntity().getRegionName())) {
            param.put("regionName",request.getEntity().getRegionName());
        }

        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<ParkBiEntity> result = parkBiManager.findByMap(param, request.getStart(), request.getPageSize(),"id",false);
        if(!StringUtils.isEmpty(result.getData())){
            Date now = new Date();
            Date monthBegin = DateFormatUtil.monthBegin(now);
            Date monthEnd = DateFormatUtil.monthEnd(now);
            result.getData().stream().map(p -> {
                ParkEntity parkEntity = new ParkEntity();
                parkEntity.setPkCode(p.getPkCode());
                //获取毛利率信息
                GrossEntity grossEntity = grossBiManager.getParkMonthGross(parkEntity, monthBegin, monthEnd);
                p.setProjectGross(grossEntity.getProjectGross());
                p.setInvestGross(grossEntity.getInvestGross());
                return  p;
            }).collect(Collectors.toList());
        }
        response.setResult(result);
        return this.success(response);
    }


    /**
     * @Description 查询园区
     * @author wangchuang
     * @Date 2018/7/24 19:18
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody ParkBiServiceRequest request) throws IllegalAccessException {

        ParkBiServiceResponse response = new ParkBiServiceResponse();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<ParkBiEntity> paramUtil = new ParamUtil<ParkBiEntity>();
        paramUtil.objectToMap(request.getEntity(),param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<ParkBiEntity> result = parkBiManager.findByMap(param);
        response.setResult(result);

        return this.success(response);
    }

    /**
     * @Description 统计园区所在城市
     * @Auth wangchuang
     * @Date 2018/7/9 14:54
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/queryCity"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryCity(@RequestBody ParkBiServiceRequest request) {
        ParkBiServiceResponse response = new ParkBiServiceResponse();

        try {
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("isDeleted",false);
            param.put("cpyCode", CurrentContext.getCpyCode());

            List<ParkBiEntity> parkCityList = parkBiManager.queryCity(param);

            response.setResult(parkCityList);
        }catch (Exception e){
            this.error(response,"查询园区所在城市异常");
        }

        return this.success(response);
    }

    /**
     * @Description 统计园区在某城市下所有的区
     * @Auth wangchuang
     * @Date 2018/7/9 16:30
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/queryRegion"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryRegion(@RequestBody ParkBiServiceRequest request) {
        ParkBiServiceResponse response = new ParkBiServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getCityName())) {
            return this.error(response,"传入城市名为空");
        }

        String cityName = request.getEntity().getCityName();
        try {
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("cityName", cityName);
            param.put("isDeleted",false);
            param.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkBiEntity> parkCityList = parkBiManager.queryRegion(param);

            response.setResult(parkCityList);
        }catch (Exception e){
            this.error(response,"统计园区在"+cityName+"下所有的区异常");
        }

        return this.success(response);
    }
}
