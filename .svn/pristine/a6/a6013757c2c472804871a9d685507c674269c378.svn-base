package com.kfwy.park.controller.pro.building;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.building.vo.BuildingServiceRequest;
import com.kfwy.park.controller.pro.building.vo.BuildingServiceResponse;
import com.kfwy.park.pro.house.building.business.IBuildingManager;
import com.kfwy.park.pro.house.building.entity.BuildingEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 建筑管理
 * @Auth luming
 * @Date 2018/5/30 11:02
 * @Version 1.0
 * @Param
 * @return
 **/
@Controller
@RequestMapping("/building")
public class BuildingService extends SpringRestService {

    @Autowired
    private IBuildingManager buildingManager;

    /**
     * @Description 添加建筑
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody BuildingServiceRequest request) throws Exception {

        BuildingServiceResponse response = new BuildingServiceResponse();
        BuildingEntity dbEntity = request.getEntity();

        Boolean flag = buildingManager.acreage(dbEntity);
        if (flag){
            String bdCode = CodeGenUtils.generate();
            dbEntity.setBdCode(bdCode);

            List<String> list = new ArrayList();
            list.add("bdName");
            list.add("investArea");
            list.add("floorsTotal");
            list.add("buildingStructure");
            list.add("firefightingLevel");
            list.add("elevatorQuantity");
            list.add("freightElevatorWeight");
            list.add("openDoor");
            ParamUtil<BuildingEntity> paramUtil = new ParamUtil<>();
            paramUtil.check(dbEntity, list);
            buildingManager.create(dbEntity);

            response.setResult(bdCode);
        } else {
            throw new BusinessException("楼栋总面积面积大于园区面积!");
        }
        return this.success(response);
    }

    /**
     * @Description 非物理删除
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody BuildingServiceRequest request){

        BuildingServiceResponse response = new BuildingServiceResponse();

        request.getEntity().setIsDeleted(true);

        buildingManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 更新建筑
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody BuildingServiceRequest request) throws Exception {

        BuildingServiceResponse response = new BuildingServiceResponse();
        BuildingEntity buildingEntity = request.getEntity();
        Boolean flag = buildingManager.acreage(buildingEntity);

        if (flag){
            List<String> list = new ArrayList();
            list.add("bdName");
            list.add("investArea");
            list.add("floorsTotal");
            list.add("buildingStructure");
            list.add("firefightingLevel");
            list.add("elevatorQuantity");
            list.add("freightElevatorWeight");
            list.add("openDoor");
            ParamUtil<BuildingEntity> paramUtil = new ParamUtil<>();
            paramUtil.check(request.getEntity(), list);
            buildingManager.update(request.getEntity());
        } else {
            throw new BusinessException("楼栋面积面积大于园区面积!");
        }
        return this.success(response);
    }

    /**
     * @Description 查询建筑
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody BuildingServiceRequest request) throws IllegalAccessException {

        BuildingServiceResponse response = new BuildingServiceResponse();

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<BuildingEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(request.getEntity(),map);

        PageResult<BuildingEntity> result = buildingManager.findByMap(map, request.getStart(), request.getPageSize(), "id", true);
        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询建筑详情
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/details"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> details(@RequestBody BuildingServiceRequest request) throws Exception {

        BuildingServiceResponse response = new BuildingServiceResponse();

        BuildingEntity buildingEntity = request.getEntity();

        if (StringUtils.isEmpty(buildingEntity.getBdCode())) {
            response.setResult(buildingEntity);
            return this.success(response);
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("bdCode", buildingEntity.getBdCode());
            map.put("cpyCode", CurrentContext.getCpyCode());
            map.put("isDeleted", false);

            BuildingEntity result = buildingManager.details(map);
            response.setResult(result);
            return this.success(response);
        }

    }

    /**
     * @Description 根据pkCode查询园区的总楼栋数,并添加到集合中
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/queryByPkCode"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryByPkCode(@RequestBody BuildingServiceRequest request) throws IllegalAccessException {

        BuildingServiceResponse response = new BuildingServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<BuildingEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(request.getEntity(),map);

        List<BuildingEntity> result = buildingManager.parkAllBuilding(map);

        response.setResult(result);
        return this.success(response);
    }

}
