package com.kfwy.park.controller.sys.cpy;

import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.sys.cpy.vo.CompanyServiceRequest;
import com.kfwy.park.controller.sys.cpy.vo.CompanyServiceResponse;
import com.kfwy.park.sys.cpy.business.ICompanyManager;
import com.kfwy.park.sys.cpy.entity.CompanyEntity;
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
 * @Description 公司管理
 * @Auth luming
 * @Date 2018/7/30 9:33
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping(path = "/company")
public class CompanyService extends SpringRestService {

    @Autowired
    private ICompanyManager companyManager;


    /**
     * @Description 添加公司
     * @Auth luming
     * @Date 2018/7/30 9:36
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody CompanyServiceRequest request) throws IllegalAccessException {

        CompanyServiceResponse response = new CompanyServiceResponse();
        CompanyEntity companyEntity = request.getEntity();

        String cpyCode = CodeGenUtils.generate();
        companyEntity.setCpyCode(cpyCode);
        companyEntity.setUserCode(CodeGenUtils.generate());

        companyManager.create(companyEntity);

        return this.success(response);
    }


    /**
     * @Description 非物理删除公司
     * @Auth luming
     * @Date 2018/7/24 10:49
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/delete",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody CompanyServiceRequest request) {
        CompanyServiceResponse response = new CompanyServiceResponse();

        CompanyEntity cpyEntity = request.getEntity();
        cpyEntity.setIsDeleted(true);

        companyManager.update(cpyEntity);

        return this.success(response);
    }


    /**
     * @Description 修改公司
     * @Auth luming
     * @Date 2018/7/30 9:36
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody CompanyServiceRequest request) throws IllegalAccessException {
        CompanyServiceResponse response = new CompanyServiceResponse();

        companyManager.update(request.getEntity());

        return this.success(response);
    }


    /**
     * @Description 查询公司
     * @Auth luming
     * @Date 2018/7/30 9:36
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody CompanyServiceRequest request) throws IllegalAccessException {

        CompanyServiceResponse response = new CompanyServiceResponse();

        Map<String,Object> param = new HashMap<>();
        param.put("isDeleted",false);

        ParamUtil<CompanyEntity> paramUtil = new ParamUtil();
        paramUtil.objectToMap(request.getEntity(),param);

        PageResult<CompanyEntity> result = companyManager.findByMap(param,request.getStart(),request.getPageSize(),"id",false);

        response.setResult(result);

        return this.success(response);
    }

    /**
     * @Description 查询公司详情
     * @author luming
     * @Date 2019/6/26 17:00
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/detail",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody CompanyServiceRequest request) throws IllegalAccessException {
        CompanyServiceResponse response = new CompanyServiceResponse();
        CompanyEntity companyEntity = request.getEntity();

        Map<String, Object> param = new HashMap<>();
        param.put("phone", companyEntity.getPhone());
        param.put("cpyCode", companyEntity.getCpyCode());
        param.put("isDeleted", false);

        companyEntity = companyManager.findOne(param);

        response.setResult(companyEntity);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-07-02 09:12:56
     *@Description 获取公司信息
     *@Return
     **/
    @RequestMapping(path = "/list",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody CompanyServiceRequest request) throws IllegalAccessException {

        CompanyServiceResponse response = new CompanyServiceResponse();

        Map<String,Object> param = new HashMap<>();
        param.put("isDeleted",false);

        ParamUtil<CompanyEntity> paramUtil = new ParamUtil();
        paramUtil.objectToMap(request.getEntity(),param);
        if(!StringUtils.isEmpty(request.getCpyCode())){
            param.put("cpyCode",request.getCpyCode());
        }

        List<CompanyEntity> result = companyManager.findByMap(param);

        response.setResult(result);

        return this.success(response);
    }
}
