package com.kfwy.park.controller.pro.building;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.building.vo.RoomServiceRequest;
import com.kfwy.park.controller.pro.building.vo.RoomServiceResponse;
import com.kfwy.park.pro.house.building.business.IRoomManager;
import com.kfwy.park.pro.house.building.entity.RoomEntity;
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
 * @Description 房间管理
 * @Auth xiongzhan
 * @Date 2018/8/14 14:20
 * @Version 1.0
 * @Param
 * @return
 **/
@Controller
@RequestMapping("/room")
public class RoomService extends SpringRestService {

    @Autowired
    private IRoomManager roomManager;


    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 新增
     * @Auth xiongzhan
     * @Date 2018/8/14 14:20
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody RoomServiceRequest request) throws Exception {

        RoomServiceResponse response = new RoomServiceResponse();
        RoomEntity roomEntity = request.getEntity();

        Boolean flag = roomManager.acreage(roomEntity);
        if (flag){
            List<String> list = new ArrayList();
            list.add("roomNumber");
            list.add("rentPrice");
            list.add("investArea");
            list.add("property");

            list.add("increasingCycle");
            list.add("freeRentDays");
            list.add("payRule");
            list.add("depositRule");
            ParamUtil<RoomEntity> paramUtil = new ParamUtil<>();
            paramUtil.check(roomEntity, list);

            roomManager.create(roomEntity);
        } else {
            throw new BusinessException("房间面积大于楼层面积！");
        }
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 非物理删除
     * @Auth xiongzhan
     * @Date 2018/8/14 14:20
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody RoomServiceRequest request) {

        RoomServiceResponse response = new RoomServiceResponse();
        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getRoomCode())) {
            return this.error(response, "删除失败，传入信息有误!");
        }
        request.getEntity().setIsDeleted(true);
        roomManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 真实删除房间
     * @Auth luming
     * @Date 2018/10/30 11:33
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/realDelete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> realDelete(@RequestBody RoomServiceRequest request) {

        RoomServiceResponse response = new RoomServiceResponse();

        RoomEntity roomEntity = request.getEntity();

        if ("1".equals(roomEntity.getRoomStatus())) {
            roomManager.realDelete(roomEntity.getRoomCode());
        } else {
            throw new BusinessException("房间删除失败，该房间已出租！");
        }
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 修改信息
     * @Auth xiongzhan
     * @Date 2018/8/14 14:20
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody RoomServiceRequest request) throws Exception {

        RoomServiceResponse response = new RoomServiceResponse();
        RoomEntity roomEntity = request.getEntity();
        Boolean flag = roomManager.acreage(roomEntity);

        if(flag){
            if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getRoomCode())) {
                return this.error(response, "修改失败，传入信息有误!");
            }
            List<String> list = new ArrayList();
            list.add("roomNumber");
            list.add("rentPrice");
            list.add("investArea");
            list.add("property");
            list.add("increasingCycle");
            list.add("freeRentDays");
            list.add("payRule");
            list.add("depositRule");
            ParamUtil<RoomEntity> paramUtil = new ParamUtil<>();
            paramUtil.check(roomEntity, list);

            roomManager.update(request.getEntity());
        } else {
            throw new BusinessException("房间面积大于楼层面积!");
        }
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 楼层历史价格查询
     * @Auth xiongzhan
     * @Date 2018/8/14 14:20
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody RoomServiceRequest request) {

        RoomServiceResponse response = new RoomServiceResponse();
        RoomEntity fphEntity = request.getEntity();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("id", fphEntity.getId());
        param.put("roomCode", fphEntity.getRoomCode());
        param.put("flCode", fphEntity.getFlCode());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<RoomEntity> result = roomManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);
        response.setResult(result);

        return this.success(response);
    }

    /**
     * @Description 查询房间列表
     * @Auth luming
     * @Date 2018/9/12 11:55
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody RoomServiceRequest request) {

        RoomServiceResponse response = new RoomServiceResponse();
        RoomEntity roomEntity = request.getEntity();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("flCode", roomEntity.getFlCode());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<RoomEntity> result = roomManager.findByMap(param);
        response.setResult(result);

        return this.success(response);
    }

    /**
     * @Description 查看房间详情
     * @Auth luming
     * @Date 2018/9/26 14:06
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/details"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> details(@RequestBody RoomServiceRequest request) {

        RoomServiceResponse response = new RoomServiceResponse();
        RoomEntity roomEntity = request.getEntity();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("roomCode", roomEntity.getRoomCode());
        param.put("roomStatus","1");
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        RoomEntity result = roomManager.findOne(param);
        response.setResult(result);

        return this.success(response);
    }
}
