package com.kfwy.park.controller.pro.inspect;

import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.controller.pro.inspect.vo.InspectTaskObjServiceRequest;
import com.kfwy.park.controller.pro.inspect.vo.InspectTaskObjServiceResponse;
import com.kfwy.park.pro.inspect.business.IInspectTaskObjManager;
import com.kfwy.park.pro.inspect.entity.InspectTaskObjEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("inspectTaskObj")
public class InspectTaskObjService extends SpringRestService {
    @Autowired
    private IInspectTaskObjManager inspectTaskObjManager;

    /**
     *@Author gtaotao
     *@Date 2019-07-08 16:07:16
     *@Description 创建或修改事项关联
     *@Param ObjList 选择的事项规则编号
     *@Return
     **/
    @RequestMapping(path = "/save"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> batchCreate(@RequestBody InspectTaskObjServiceRequest request) {
        InspectTaskObjServiceResponse response = new InspectTaskObjServiceResponse();
        InspectTaskObjEntity taskObjEntity = request.getEntity();
        String taskCode = taskObjEntity.getTaskCode();
        String[] objArr = taskObjEntity.getObjRlCode().split(",");
        if(StringUtils.isEmpty(taskCode) || objArr.length <1){
            throw new BusinessException("请选择任务名或检查事项！");
        }
        List<InspectTaskObjEntity> taskObjEntityList = Stream.of(objArr).map(o -> {
            InspectTaskObjEntity entity = new InspectTaskObjEntity();
            entity.setTaskCode(taskCode);
            entity.setObjRlCode(o);
            return entity;
        }).collect(Collectors.toList());
        inspectTaskObjManager.save(taskCode, taskObjEntityList);
        return this.success(response);
    }
}
