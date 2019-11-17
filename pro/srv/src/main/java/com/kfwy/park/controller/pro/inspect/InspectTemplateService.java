package com.kfwy.park.controller.pro.inspect;


import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.pro.inspect.vo.InspectTemplateServiceRequest;
import com.kfwy.park.controller.pro.inspect.vo.InspectTemplateServiceResponse;
import com.kfwy.park.pro.inspect.business.IInspectTemplateManager;
import com.kfwy.park.pro.inspect.entity.InspectTemplateEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.util.StringUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping({"/inspectTemplate"})
public class InspectTemplateService extends SpringRestService {
    @Autowired
    private IInspectTemplateManager inspectTemplateManager;
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody InspectTemplateServiceRequest request){
        InspectTemplateServiceResponse response = new InspectTemplateServiceResponse();
        InspectTemplateEntity templateEntity = request.getEntity();
        if(StringUtils.isEmpty(templateEntity.getTemplateName())){
            throw new BusinessException("请输入模板名称!");
        }
        inspectTemplateManager.createTemplate(templateEntity);
        return this.success(response);
    }



    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody InspectTemplateServiceRequest request) throws IllegalAccessException {
        InspectTemplateServiceResponse response = new InspectTemplateServiceResponse();
        InspectTemplateEntity templateEntity = request.getEntity();
        ParamUtil<InspectTemplateEntity> paramUtil = new ParamUtil();
        Map<String, Object> param = new HashMap<>();
        paramUtil.objectToMap(templateEntity, param);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<InspectTemplateEntity> result = inspectTemplateManager.findByMap(param, "create_time", false);
        response.setResult(result);
        return this.success(response);
    }

    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody InspectTemplateServiceRequest request) {
        InspectTemplateServiceResponse response = new InspectTemplateServiceResponse();
        InspectTemplateEntity templateEntity = request.getEntity();
        if(StringUtils.isEmpty(templateEntity.getId()) || StringUtils.isEmpty(templateEntity.getTemplateCode())){
            throw new BusinessException("缺少模板id或code!");
        }
        inspectTemplateManager.deleteTemplate(templateEntity);
        return this.success(response);
    }

    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody InspectTemplateServiceRequest request) {
        InspectTemplateServiceResponse response = new InspectTemplateServiceResponse();
        InspectTemplateEntity templateEntity = request.getEntity();
        if(StringUtils.isEmpty(templateEntity.getId()) || StringUtils.isEmpty(templateEntity.getTemplateCode())){
            throw new BusinessException("缺少模板id或code!");
        }
        inspectTemplateManager.update(templateEntity);
        return this.success(response);
    }

}
