package com.kfwy.park.controller.hrm.employee;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.controller.hrm.employee.vo.PostDto;
import com.kfwy.park.controller.hrm.employee.vo.PostServiceRequest;
import com.kfwy.park.controller.hrm.employee.vo.PostServiceResponse;
import com.kfwy.park.hrm.org.employee.business.IPostManager;
import com.kfwy.park.hrm.org.employee.entity.PostEntity;
import com.kfwy.park.sys.auth.business.IMenuManager;
import com.kfwy.park.sys.user.business.IUserManager;
import com.kfwy.park.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
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
 * @Description 岗位管理
 * @Auth davidCun
 * @Date 2018/6/12 22:53
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping(path = "/post")
public class PostService extends SpringRestService {

    @Autowired
    private IPostManager postManager;

    @Autowired
    private IMenuManager menuManager;

    @Autowired
    private IUserManager userManager;


    /**
     * @return
     * @Description 新增岗位
     * @Auth xiepingping
     * @Date 2018/8/7 10:45
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody PostServiceRequest request) throws IllegalAccessException {

        PostServiceResponse response = new PostServiceResponse();

        if (request.getEntity().getPostName() == null) {
            throw new BusinessException("设置的必填字段" + "postName" + "不存在");
        }

        postManager.create(request.getEntity(), request.getMenuCodes());

        return this.success(response);
    }

    /**
     * @return
     * @Description 修改岗位
     * @Auth xiepingping
     * @Date 2018/8/7 10:45
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody PostServiceRequest request) throws IllegalAccessException {

        PostServiceResponse response = new PostServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getPostCode())) {
            throw new BusinessException("请先选择相应岗位分配权限");
        }

        postManager.update(request.getEntity(), request.getMenuCodes());

        return this.success(response);
    }


    /**
     * @return
     * @Description 查询岗位
     * @Auth xiepingping
     * @Date 2018/8/7 10:45
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody PostServiceRequest request) throws IllegalAccessException {

        PostServiceResponse response = new PostServiceResponse();

        PostEntity postEntity = request.getEntity();
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("postCode", postEntity.getPostCode());
        param.put("postName", postEntity.getPostName());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<PostEntity> result = postManager.findByMap(param, request.getStart(), request.getPageSize(), "create_time", false);

        response.setResult(result);

        return this.success(response);
    }

    /**
     * @return
     * @Description 查询岗位详情，并且携带菜单树结构
     * @Auth xiepingping
     * @Date 2018/8/7 10:45
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/detail",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> detail(@RequestBody PostServiceRequest request) {
        PostServiceResponse response = new PostServiceResponse();

        PostEntity post = postManager.findOne("postCode", request.getEntity().getPostCode());

        PostDto pd = new PostDto(post);

        pd.setMenus(menuManager.findUserMenuToTreeByUserCode(pd.getPostCode()));

        response.setResult(pd);

        return this.success(response);
    }

    /**
     * @return
     * @Description 查询所有职位
     * @Auth luming
     * @Date 2018/7/6 15:21
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/queryPostList",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryPostList(@RequestBody PostServiceRequest request) {

        PostServiceResponse response = new PostServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<PostEntity> result = postManager.findByMap(param);

        response.setResult(result);

        return this.success(response);
    }

    /**
     * @return
     * @Description 查询岗位详情，并且携带叶子菜单列表
     * @Auth xiepingping
     * @Date 2018/8/7 10:46
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/queryOne",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryOne(@RequestBody PostServiceRequest request) {
        PostServiceResponse response = new PostServiceResponse();

        PostEntity post = postManager.findOne("postCode", request.getEntity().getPostCode());

        PostDto pd = new PostDto(post);

        pd.setMenus(menuManager.findMenuListByPost(pd.getPostCode()));

        response.setResult(pd);

        return this.success(response);
    }

    /**
     * @Description 查询企业管理员所属的岗位及权限
     * @Auth luming
     * @Date 2019/7/8 18:18
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/cpyAuth",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cpyAuth(@RequestBody PostServiceRequest request) {
        PostServiceResponse response = new PostServiceResponse();

        // 根据公司中的userCode查询用户信息，得到postCode
        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);
        params.put("userCode", request.getUserCode());
        UserEntity userEntity = userManager.findOne(params);

        PostEntity post = postManager.findOne("postCode", userEntity.getPostCode());

        PostDto pd = new PostDto(post);
        pd.setMenus(menuManager.findMenuListByPost(pd.getPostCode()));

        response.setResult(pd);
        return this.success(response);
    }
}
