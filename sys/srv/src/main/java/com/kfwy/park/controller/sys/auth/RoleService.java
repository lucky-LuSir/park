package com.kfwy.park.controller.sys.auth;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.controller.sys.auth.vo.RoleDto;
import com.kfwy.park.controller.sys.auth.vo.RoleServiceRequest;
import com.kfwy.park.controller.sys.auth.vo.RoleServiceResponse;
import com.kfwy.park.sys.auth.business.IMenuManager;
import com.kfwy.park.sys.auth.business.IRoleManager;
import com.kfwy.park.sys.auth.entity.RoleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 */
@RestController
@RequestMapping("/role")
public class RoleService extends SpringRestService {

    @Autowired
    private IRoleManager roleManager;

    @Autowired
    private IMenuManager menuManager;

    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody RoleServiceRequest request){

        RoleServiceResponse response = new RoleServiceResponse();

        roleManager.create(request.getEntity());

        return this.success(response);
    }

    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody RoleServiceRequest request){

        RoleServiceResponse response = new RoleServiceResponse();

        roleManager.update(request.getEntity(),request.getMenuCodes());

        return this.success(response);
    }

    /**
     * 查询角色列表
     * @param request
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody RoleServiceRequest request){

        RoleServiceResponse response = new RoleServiceResponse();

        RoleEntity roleEntity = request.getEntity();
        Map<String,Object> param = new HashMap<String,Object>();
        param.put("roleName", roleEntity.getRoleName());
        param.put("isDeleted",Boolean.FALSE);
        param.put("cpyCode", CurrentContext.getUserInfo().getCpyCode());

        PageResult<RoleEntity> result = roleManager.findByMap(param,request.getStart(),request.getPageSize());

        response.setResult(result);

        return this.success(response);
    }


    @RequestMapping(path = "/queryOne",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryOne(@RequestBody RoleServiceRequest request){
        RoleServiceResponse response = new RoleServiceResponse();

        RoleEntity role = roleManager.findOne("roleCode",request.getEntity().getRoleCode());

        RoleDto rd = new RoleDto(role);

        rd.setMenus(menuManager.findMenuListByRole(rd.getRoleCode()));

        rd.setRoleName("role的queryOne");
        response.setResult(rd);

        return this.success(response);
    }

}
