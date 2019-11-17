package com.kfwy.park.controller.exp.building;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.exp.building.vo.InvparkServiceRequest;
import com.kfwy.park.controller.exp.building.vo.InvparkServiceResponse;
import com.kfwy.park.exp.house.building.business.IInvparkManager;
import com.kfwy.park.exp.house.building.entity.InvparkEntity;
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
 * @Description 拓展
 * @Auth luming
 * @Date 2018/11/7 10:44
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping("/invpark")
public class InvparkService extends SpringRestService {

    @Autowired
    private IInvparkManager invparkManager;

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
    public ResponseEntity<IServiceResponse> create(@RequestBody InvparkServiceRequest request) throws IllegalAccessException {

        InvparkServiceResponse response = new InvparkServiceResponse();

        String itemCode = CodeGenUtils.generate();

        //将必填字段放入容器
        List<String> params = new ArrayList<>();
        params.add("parkOwnerCpyName");
        params.add("parkOwnerName");
        params.add("parkOwnerSex");
        params.add("parkOwnerPhone");
        params.add("provinceCode");
        params.add("cityCode");
        params.add("regionCode");
        params.add("streetCode");
        params.add("provinceName");
        params.add("cityName");
        params.add("regionName");
        params.add("streetName");
        params.add("address");
        params.add("distanceDowntown");
        params.add("distanceNearbyLifeRegion");
        params.add("manufacturingDistrict");
        params.add("distanceFreewayEntrance");
        params.add("distanceMainStreet");
        params.add("laneQuantity");
        params.add("nearbyPrice");
        params.add("plantDecoration");
        params.add("plantElectricity");
        params.add("parkArea");
        params.add("singleFloorQuantity");
        params.add("singleFloorArea");
        params.add("singleFloorHeight");
        params.add("moreFloorQuantity");
        params.add("moreFloorArea");
        params.add("moreFloorHeight");
        params.add("moreFloorElevatorQuantity");
        params.add("elevatorLocation");
        params.add("officeBuildingQuantity");
        params.add("officeBuildingArea");
        params.add("officeBuildingHeight");
        params.add("otherBuildingQuantity");
        params.add("otherBuildingArea");
        params.add("vacancySoilArea");
        params.add("totalElectricity");
        params.add("firefightingLevel");
        params.add("estateYear");
        params.add("contractYear");
        params.add("unitPrice");
        params.add("strataFee");
        params.add("freeRentDate");
        params.add("taxRequest");
        params.add("restrictIndustry");

        ParamUtil<InvparkEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(), params);

        InvparkEntity invparkEntity = request.getEntity();
        invparkEntity.setItemCode(itemCode);
        invparkEntity.setOwnerDeptCode(CurrentContext.getUserInfo().getOwnerDeptCode());
        invparkEntity.setOwnerDeptName(CurrentContext.getUserInfo().getOwnerDeptName());

        invparkManager.create(invparkEntity);

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("itemCode", invparkEntity.getItemCode());
        result.put("score", invparkEntity.getScore());
        result.put("assessResult", invparkEntity.getAssessResult());

        response.setResult(result);

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
    public ResponseEntity<IServiceResponse> update(@RequestBody InvparkServiceRequest request) throws IllegalAccessException {

        InvparkServiceResponse response = new InvparkServiceResponse();
        InvparkEntity invparkEntity = request.getEntity();

        invparkManager.update(invparkEntity);

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
    public ResponseEntity<IServiceResponse> query(@RequestBody InvparkServiceRequest request) throws IllegalAccessException {

        InvparkServiceResponse response = new InvparkServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("keyword", request.getKeyword());
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<InvparkEntity> result = invparkManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

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
    public ResponseEntity<IServiceResponse> detail(@RequestBody InvparkServiceRequest request) throws IllegalAccessException {

        InvparkServiceResponse response = new InvparkServiceResponse();

        InvparkEntity invparkEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted",false);
        map.put("itemCode", invparkEntity.getItemCode());
        map.put("cpyCode", CurrentContext.getCpyCode());

        InvparkEntity entity = invparkManager.findOne(map);

        response.setResult(entity);
        return this.success(response);
    }

}
