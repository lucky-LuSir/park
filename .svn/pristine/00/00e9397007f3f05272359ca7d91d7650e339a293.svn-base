package com.kfwy.park.controller.sys.board;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.sys.board.vo.BoardServiceRequest;
import com.kfwy.park.controller.sys.board.vo.BoardServiceResponse;
import com.kfwy.park.sys.board.business.IBoardManager;
import com.kfwy.park.sys.board.entity.BoardEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.builder.BuilderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhengyang on 2018/7/25.
 */
@RestController
@RequestMapping("/board")
public class BoardService extends SpringRestService {

    @Autowired
    private IBoardManager boardManager;

    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody BoardServiceRequest request) {
        BoardServiceResponse response = new BoardServiceResponse();

        BoardEntity boardEntity = request.getEntity();
        String boardCode = CodeGenUtils.generate();
        boardEntity.setBoardCode(boardCode);
        boardManager.create(boardEntity);

        return this.success(response);
    }

    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody BoardServiceRequest request){

        BoardServiceResponse response = new BoardServiceResponse();

        boardManager.update(request.getEntity());

        return this.success(response);

    }
    @RequestMapping(path = "/delete",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody BoardServiceRequest request){

        BoardServiceResponse response = new BoardServiceResponse();
        boardManager.delete(request.getEntity().getId());
        return this.success(response);

    }

    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody BoardServiceRequest request) throws IllegalAccessException {
        BoardServiceResponse response = new BoardServiceResponse();

        Map<String,Object> param = new HashMap<String,Object>();
        ParamUtil<BoardEntity> paramUtil = new ParamUtil<BoardEntity>();
        paramUtil.objectToMap(request.getEntity(),param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<BoardEntity> result = boardManager.findByMap(param,request.getStart(),request.getPageSize(),"id",false);
        response.setResult(result);

        return this.success(response);
    }


    @RequestMapping(path = "/list",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody BoardServiceRequest request) {
        BoardServiceResponse response = new BoardServiceResponse();

        Map<String,Object> param = new HashMap<String,Object>();
        param.put("isDeleted",false);
        param.put("createCode", CurrentContext.getUserInfo().getUserCode());
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<BoardEntity> result = boardManager.findByMap(param,"id",false);
        response.setResult(result);

        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-05-29 08:34:53
     *@Description 用户管理批量添加看板
     *@Return
     **/
    @RequestMapping(path = "/batchCreate"
            ,method = RequestMethod.POST
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> batchCreate(@RequestBody BoardServiceRequest request){
        BoardServiceResponse response = new BoardServiceResponse();
        BoardEntity entity = request.getEntity();
        String createCode = entity.getCreateCode();
        String pgCode = entity.getPgCode();
        if(StringUtils.isEmpty(createCode) || StringUtils.isEmpty(pgCode)){
            throw new BuilderException("请选择用户或看板!");
        }
        boardManager.batchCreate(pgCode,createCode);
        return this.success(response);
    }



}
