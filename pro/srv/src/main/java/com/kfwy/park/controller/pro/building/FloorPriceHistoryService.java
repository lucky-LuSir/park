package com.kfwy.park.controller.pro.building;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.controller.pro.building.vo.FloorPriceHistoryServiceRequest;
import com.kfwy.park.controller.pro.building.vo.FloorPriceHistoryServiceResponse;
import com.kfwy.park.pro.house.building.business.IFloorPriceHistoryManager;
import com.kfwy.park.pro.house.building.entity.FloorPriceHistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 楼层价格历史管理
 * @Auth wangchuang
 * @Date 2018/5/30 14:09
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping("/floorPriceHistory")
public class FloorPriceHistoryService extends SpringRestService {

    @Autowired
    private IFloorPriceHistoryManager floorPriceHistoryManager;

    /**
     * @Description 新增历史价格信息
     * @Auth wangchuang
     * @Date 2018/5/30 14:09
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/create"
                    , method = RequestMethod.POST
                    , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
                    , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody FloorPriceHistoryServiceRequest request) {

        FloorPriceHistoryServiceResponse response = new FloorPriceHistoryServiceResponse();
        //TODO 根据业务需求对相应字段校验

        floorPriceHistoryManager.create(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 非物理删除
     * @Auth wangchuang
     * @Date 2018/5/30 14:09
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody FloorPriceHistoryServiceRequest request) {

        //响应的json实体类字段
        FloorPriceHistoryServiceResponse response = new FloorPriceHistoryServiceResponse();
        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getFlCode())) {
            return this.error(response,"删除失败，传入信息有误!");
        }
        request.getEntity().setIsDeleted(true);
        floorPriceHistoryManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 修改楼层信息
     * @Auth wangchuang
     * @Date 2018/5/30 14:09
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody FloorPriceHistoryServiceRequest request) {

        FloorPriceHistoryServiceResponse response = new FloorPriceHistoryServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getFlCode())) {
            return this.error(response,"修改失败，传入信息有误!");
        }
        floorPriceHistoryManager.update(request.getEntity());

        return this.success(response);

    }

    /**
     * @Description 楼层历史价格查询
     * @Auth wangchuang
     * @Date 2018/5/30 14:09
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody FloorPriceHistoryServiceRequest request) {

        FloorPriceHistoryServiceResponse response = new FloorPriceHistoryServiceResponse();
        //TODO 根据查询场景添加业务逻辑
        FloorPriceHistoryEntity fphEntity = request.getEntity();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("id",fphEntity.getId());
        param.put("pkCode",fphEntity.getPkCode());
        param.put("bdCode",fphEntity.getBdCode());
        param.put("flCode",fphEntity.getFlCode());
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<FloorPriceHistoryEntity> result = floorPriceHistoryManager.findByMap(param,request.getStart(),request.getPageSize(),"create_time",false);
        response.setResult(result);

        return this.success(response);
    }
}
