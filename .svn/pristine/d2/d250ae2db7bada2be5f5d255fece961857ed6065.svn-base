package com.kfwy.park.controller.sys.area;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.controller.sys.area.vo.BaseAreaServiceRequest;
import com.kfwy.park.controller.sys.area.vo.BaseAreaServiceResponse;
import com.kfwy.park.sys.area.business.IBaseAreaManager;
import com.kfwy.park.sys.area.business.ICompanyAreaManager;
import com.kfwy.park.sys.area.dictionary.AreaStatus;
import com.kfwy.park.sys.area.entity.BaseAreaEntity;
import com.kfwy.park.sys.area.entity.CompanyAreaEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.*;

/**
* @Description 描述:省市区街道
* @Auth xpp
* @Date 2018/6/14 10:52
* @Version 1.0
* @Param 
* @return 
**/
@Controller
@RequestMapping("/baseArea")
public class BaseAreaService extends SpringRestService {

    @Autowired
    private IBaseAreaManager baseAreaManager;

    @Autowired
    private ICompanyAreaManager companyAreaManager;

    /**
    * @Description 描述:查询接口 queryNoPage
    * @Auth xpp
    * @Date 2018/8/13 16:10
    * @Version 1.0
    * @Param [request]
    * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
    **/
    @RequestMapping(value = "/queryNoPage", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody BaseAreaServiceRequest request) {

        BaseAreaServiceResponse response = new BaseAreaServiceResponse();


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

            response.setResult(baseAreaManager.findAreaByCode(request.getEntity().getAreaCode()));
            return this.success(response);

        }
        if(!StringUtils.isEmpty(request.getEntity().getParentCode())){
            param.put("parentCode",request.getEntity().getParentCode());
        }
        if(!StringUtils.isEmpty(request.getEntity().getLevel())){
            param.put("level",request.getEntity().getLevel());
        }
        //param.put("cpyCode", CurrentContext.getCpyCode());
        param.put("status", AreaStatus.ENABLE);


        List<BaseAreaEntity> list = baseAreaManager.findByMap(param,"sort",true);



        response.setResult(list);

        return this.success(response);
    }

    @RequestMapping(value = "/enableArea", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> enableArea(@RequestBody BaseAreaServiceRequest request) {

        BaseAreaServiceResponse response = new BaseAreaServiceResponse();
        int enableFlag= request.getEnableFlag();
        BaseAreaEntity provinceObj= request.getProvinceObj();
        BaseAreaEntity cityObj= request.getCityObj();
        BaseAreaEntity regionObj= request.getRegionObj();

        List<BaseAreaEntity> readyInsertList = new ArrayList<BaseAreaEntity>();
        //1(激活省及下地区)
        if(enableFlag==1){
            readyInsertList.clear();

            //A查出省及管辖地区
            BaseAreaEntity provOne=baseAreaManager.findAreaByCode(provinceObj.getAreaCode());
            readyInsertList.add(provOne);
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("parentCode",provinceObj.getAreaCode());
            List<BaseAreaEntity> cityAllList = baseAreaManager.findByMap(param,"sort",true);
            readyInsertList.addAll(cityAllList);
            for(int i=0;i<cityAllList.size();i++){
                param.clear();
                param.put("parentCode",cityAllList.get(i).getAreaCode());
                List<BaseAreaEntity> regionAllList = baseAreaManager.findByMap(param,"sort",true);
                readyInsertList.addAll(regionAllList);
                for(int j=0;j<regionAllList.size();j++){
                    param.clear();
                    param.put("parentCode",regionAllList.get(j).getAreaCode());
                    List<BaseAreaEntity> streetAllList = baseAreaManager.findByMap(param,"sort",true);
                    readyInsertList.addAll(streetAllList);
                }

            }
            checkAndCreate(readyInsertList);

        }
        //2(激活市及下地区)
        else if(enableFlag==2){

            readyInsertList.clear();

            //A查出省及管辖地区
            BaseAreaEntity provOne=baseAreaManager.findAreaByCode(provinceObj.getAreaCode());
            readyInsertList.add(provOne);
            BaseAreaEntity cityOne=baseAreaManager.findAreaByCode(cityObj.getAreaCode());
            readyInsertList.add(cityOne);
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("parentCode",cityObj.getAreaCode());
            List<BaseAreaEntity> regionAllList = baseAreaManager.findByMap(param,"sort",true);
            readyInsertList.addAll(regionAllList);

            for(int j=0;j<regionAllList.size();j++){
                param.clear();
                String regionAreaCode = regionAllList.get(j).getAreaCode();
                param.put("parentCode",regionAreaCode);
                List<BaseAreaEntity> streetAllList = baseAreaManager.findByMap(param,"sort",true);
                readyInsertList.addAll(streetAllList);
            }

            checkAndCreate(readyInsertList);

        }
        //3(激活县及下地区)
        else if(enableFlag==3){

            readyInsertList.clear();

            //A查出省及管辖地区
            BaseAreaEntity provOne=baseAreaManager.findAreaByCode(provinceObj.getAreaCode());
            readyInsertList.add(provOne);
            BaseAreaEntity cityOne=baseAreaManager.findAreaByCode(cityObj.getAreaCode());
            readyInsertList.add(cityOne);
            BaseAreaEntity regionOne=baseAreaManager.findAreaByCode(regionObj.getAreaCode());
            readyInsertList.add(regionOne);
            Map<String,Object> param = new HashMap<String,Object>();
            param.put("parentCode",regionObj.getAreaCode());
            List<BaseAreaEntity> streetAllList = baseAreaManager.findByMap(param,"sort",true);
            readyInsertList.addAll(streetAllList);

            checkAndCreate(readyInsertList);
        }
        //4(其他情况)
        else{
            response.setMessage("启用等级enableFlag为:"+enableFlag+",不符合激活等级,激活等级为(1,2,3)");
            return this.error(response,"启用等级enableFlag为:"+enableFlag+",不符合激活等级,激活等级为(1,2,3)");

        }

        return this.success(response);
    }

    public CompanyAreaEntity baseArea2CpyArea (BaseAreaEntity baseArea){
        CompanyAreaEntity cpyAreaObj = new CompanyAreaEntity();
        cpyAreaObj.setAreaCode(baseArea.getAreaCode());
        cpyAreaObj.setParentCode(baseArea.getParentCode());
        cpyAreaObj.setName(baseArea.getName());
        cpyAreaObj.setShortName(baseArea.getShortName());
        cpyAreaObj.setLevel(baseArea.getLevel());
        cpyAreaObj.setSort(baseArea.getSort());
        cpyAreaObj.setStatus(baseArea.getStatus());
        cpyAreaObj.setLongitude(baseArea.getLongitude());
        cpyAreaObj.setLatitude(baseArea.getLatitude());
        //基本数据
        cpyAreaObj.setCreateTime(new Date());
        cpyAreaObj.setLastUpdateTime(new Date());
        cpyAreaObj.setIsDeleted(false);
        return cpyAreaObj;
    }

    public void checkAndCreate(List<BaseAreaEntity> readyInsertList){
        //B新增到cpyArea表中
        //B.1判断cpyArea表中是否已经存在
        for(int i=0;i<readyInsertList.size();i++){
            Map<String, Object> comMap = new HashMap<>();
            comMap.put("areaCode",readyInsertList.get(i).getAreaCode());
            comMap.put("cpyCode", CurrentContext.getCpyCode());
            CompanyAreaEntity selectOne = companyAreaManager.findOne(comMap);
            if(selectOne==null){
                BaseAreaEntity baseArea = readyInsertList.get(i);
                CompanyAreaEntity cpyAreaEntity = baseArea2CpyArea(baseArea);
                companyAreaManager.create(cpyAreaEntity);
            }else{
                //已存在,不做新增操作,继续下次循环
                System.out.println("已存在,不做新增操作,继续下次循环,BaseAreaService"+i);
            }
        }
    }

}
