package com.kfwy.park.controller.sys.area;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.controller.sys.area.vo.CompanyAreaServiceRequest;
import com.kfwy.park.controller.sys.area.vo.CompanyAreaServiceResponse;
import com.kfwy.park.sys.area.business.ICompanyAreaManager;
import com.kfwy.park.sys.area.dictionary.AreaStatus;
import com.kfwy.park.sys.area.entity.CompanyAreaEntity;
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
 * @version 1.0
 * @description TODO
 * @auth davidCun
 * @date 2018/7/3 09:27
 */
@RestController
@RequestMapping(path = "/cpyArea")
public class CompanyAreaService extends SpringRestService {

    @Autowired
    private ICompanyAreaManager companyAreaManager;

    @RequestMapping(value = "/queryNoPage", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CompanyAreaServiceRequest request) {

        CompanyAreaServiceResponse response = new CompanyAreaServiceResponse();


        //查询省市区，考虑到没有分页的情况，不能查询很多数据
        if (request.getEntity() == null
                || (StringUtils.isEmpty(request.getEntity().getAreaCode())
                && StringUtils.isEmpty(request.getEntity().getParentCode())
                && request.getEntity().getLevel() == null)
                || (request.getEntity().getLevel() != null
                && request.getEntity().getLevel() > 2
                && StringUtils.isEmpty(request.getEntity().getAreaCode())
                && StringUtils.isEmpty(request.getEntity().getParentCode()))
        ) {

            return this.error(response,"此接口最多只支持查询不分页的不指定父级编码的区以上的数据");


        }

        Map<String,Object> param = new HashMap<String,Object>();

        if(!StringUtils.isEmpty(request.getEntity().getAreaCode())){
            param.put("areaCode",request.getEntity().getAreaCode());

            response.setResult(companyAreaManager.findAreaByCode(request.getEntity().getAreaCode()));
            return this.success(response);

        }
        if(!StringUtils.isEmpty(request.getEntity().getParentCode())){
            param.put("parentCode",request.getEntity().getParentCode());
        }
        if(!StringUtils.isEmpty(request.getEntity().getLevel())){
            param.put("level",request.getEntity().getLevel());
        }
        param.put("cpyCode", CurrentContext.getCpyCode());
        param.put("status", AreaStatus.ENABLE);


        List<CompanyAreaEntity> list = companyAreaManager.findByMap(param,"sort",true);



        response.setResult(list);

        return this.success(response);
    }






}
