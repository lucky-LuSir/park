package com.kfwy.park.controller.file;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.controller.file.vo.FileServiceRequest;
import com.kfwy.park.controller.file.vo.FileServiceResponse;
import com.kfwy.park.sys.file.business.IFileManager;
import com.kfwy.park.sys.file.dictionary.FileType;
import com.kfwy.park.sys.file.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/*
 * @Description FileEntity对数据库表的增删改查
 * @Auth liuzhengyang
 * @Date 2018/6/29 8:50
 * @Version 1.0
 * @Param
 * @return
 */
@Controller
@RequestMapping(path = "/file/data")
public class FileDataService extends SpringRestService {

    @Autowired
    private IFileManager fileManager;

    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody FileServiceRequest request) {

        FileServiceResponse response = new FileServiceResponse();

        FileEntity fileEntity = request.getEntity();
        String code = CodeGenUtils.generate();
        fileEntity.setFileCode(code);
        fileEntity.setFileType(FileType.FOLDER);
        fileManager.create(fileEntity);

        response.setResult(code);
        return this.success(response);
    }

    @RequestMapping(path = "/update",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody FileServiceRequest request){

        FileServiceResponse response = new FileServiceResponse();

        fileManager.update(request.getEntity());

        return this.success(response);

    }

    @RequestMapping(path = "/query",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody FileServiceRequest request){
        FileServiceResponse response = new FileServiceResponse();

        Map<String,Object> map = new HashMap<>();
        if(request.getEntity() != null){
            map.put("createCode", CurrentContext.getUserInfo().getUserCode());
            map.put("parentCode",request.getEntity().getParentCode());
            map.put("isDeleted", false);
        }

        PageResult<FileEntity> page = fileManager.findByMap(map,request.getStart(),request.getPageSize(),"id",false);
        response.setResult(page);

        return this.success(response);
    }

    /*
     * @Description 查询目录
     * @Auth liuzhengyang
     * @Date 2018/7/12 11:30
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/catalog",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> catalog(@RequestBody FileServiceRequest request){
        FileServiceResponse response = new FileServiceResponse();

        Map<String,Object> map = new HashMap<>();

        List<FileEntity> list = new ArrayList<FileEntity>();
        list =  fileManager.selectCatalog(request.getEntity().getParentCode(),list);
        Collections.reverse(list);

        response.setResult(list);
        return this.success(response);
    }


}
