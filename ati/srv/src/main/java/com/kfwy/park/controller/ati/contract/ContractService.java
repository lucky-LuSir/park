package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.*;
import com.kfwy.park.ati.contract.entity.*;
import com.kfwy.park.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.park.common.easyexcel.export.FileExportor;
import com.kfwy.park.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.park.common.easyexcel.export.exception.FileExportException;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.contract.vo.ContractServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.ContractServiceResponse;
import com.kfwy.park.pro.house.building.business.IParkButtJointManager;
import com.kfwy.park.pro.house.building.entity.BuildingEntity;
import com.kfwy.park.pro.house.building.entity.ParkButtJointEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.apache.ibatis.builder.BuilderException;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 合同管理
 * @Auth davidcun
 * @Date 2018/5/22 9:54
 * @Version 1.0
 * @Param
 * @return
 **/
@RestController
@RequestMapping(path = "/contract")
public class ContractService extends SpringRestService {

    @Autowired
    private IContractManager contractManager;

    @Autowired
    private IContractHouseManager contractHouseManager;

    @Autowired
    private IRentReceiptManager rentReceiptManager;

    @Autowired
    private IElectricityReceiptManager electricityReceiptManager;

    @Autowired
    private IParkingReceiptManager parkingReceiptManager;

    @Autowired
    private IWaterReceiptManager waterReceiptManager;

    @Autowired
    private IPremiumManager premiumManager;

    @Autowired
    private IRentReceiptDetailManager rentReceiptDetailManager;

    @Autowired
    private IParkButtJointManager parkButtJointManager;

    @Autowired
    private IElectricityMeterManager electricityMeterManager;

    @Autowired
    private IWaterMeterManager waterMeterManager;

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 新增合同信息
     * @Auth wangchuang
     * @Date 2018/5/22 9:55
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ContractServiceRequest request) throws IllegalAccessException {
        ContractServiceResponse response = new ContractServiceResponse();
        ContractEntity conEntity = request.getEntity();

        //将必填字段放入容器
        List<String> params = new ArrayList<>();
        params.add("cusCode");
        params.add("customerType");
        params.add("companyName");
        params.add("tradeName");
        params.add("companyProduct");
        params.add("relateName");
        params.add("relateSex");
        params.add("relatePhone");
        params.add("pkCode");
        params.add("pkName");
        params.add("contractorCode");
        params.add("contractor");
        params.add("contractorDeptCode");
        params.add("contractorDept");
        params.add("contractorPhone");
        params.add("partyName");
//        params.add("deliveryDate");
        params.add("advanceDateCount");
        params.add("contractDate");
        params.add("contractStartTime");
        params.add("contractEndTime");
        params.add("freeRentStartTime");
        params.add("freeRentEndTime");
//        params.add("startRentDate");
        params.add("incDetailType");

        /*********判断租金规则 开始************/
        if ("1".equals(conEntity.getIncDetailType())) {//详细递增
            if (conEntity.getIncDetailCase().indexOf(",,") >= 0) {
                throw new BusinessException("详细递增信息填写不完全");
            }
        } else if ("2".equals(conEntity.getIncDetailType())) {//非详细递增
            params.add("increasingCycle");
            params.add("rentIncreaseProportion");
        }
        /*********判断租金规则  结束************/

        params.add("propertyUnitPrice");

        // 出租楼层非空校验
        List<ContractHouseEntity> conHouseList = conEntity.getContractHouseList();
        if (conHouseList != null) {
            for (int i = 0; i < conHouseList.size(); i++) {
                if (StringUtils.isEmpty(conHouseList.get(i).getFlCode()) ||
                        StringUtils.isEmpty(conHouseList.get(i).getInvestArea()) ||
                        StringUtils.isEmpty(conHouseList.get(i).getAreaRate()) ||
                        StringUtils.isEmpty(conHouseList.get(i).getUnitPrice())) {

                    throw new BusinessException("出租楼层信息填写不完全");
                }
            }
        }

        params.add("payRule");
        params.add("depositRule");
        params.add("deposit");
        params.add("basicElectricityPriceByMonth");
        params.add("totalElectricity");
        params.add("basicElectricityUnitPrice");
        params.add("addLoss");

        params.add("freeParkingCount");
        params.add("parkingPrice");
        params.add("contractUrl");

        params.add("outputValue");
        params.add("hasRevenueBackLocal");
        params.add("taxTake");
        params.add("equipmentList");
        params.add("equipmentValue");
        ParamUtil<ContractEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(conEntity, params);

        conEntity.setCtCode(CodeGenUtils.generate());
        contractManager.create(conEntity);

        response.setResult(conEntity.getCtCode());

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 非物理删除
     * @Auth wangchuang
     * @Date 2018/5/22 9:58
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody ContractServiceRequest request) {
        ContractServiceResponse response = new ContractServiceResponse();

        if (StringUtils.isEmpty(request.getEntity().getId()) && StringUtils.isEmpty(request.getEntity().getCtCode())) {
            return this.error(response, "删除失败，传入信息有误!");
        }

        ContractEntity contractEntity = request.getEntity();
        contractEntity.setIsDeleted(true);

//        contractManager.update(request.getEntity());
        contractManager.deleteContract(contractEntity);

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 用于修改非二审通过的合同信息
     * @Auth wangchuang
     * @Date 2018/5/22 9:55
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody ContractServiceRequest request) throws IllegalAccessException {
        ContractServiceResponse response = new ContractServiceResponse();
        ContractEntity contractEntity = request.getEntity();

        if (StringUtils.isEmpty(contractEntity.getId())) {
            return this.error(response, "修改失败，传入信息有误!");
        }

        List<String> params = new ArrayList<>();
        params.add("ctCode");
        params.add("cusCode");
        params.add("customerType");
        params.add("companyName");
        params.add("tradeName");
        params.add("companyProduct");
        params.add("relateName");
        params.add("relateSex");
        params.add("relatePhone");
        params.add("pkCode");
        params.add("pkName");
        params.add("contractorCode");
        params.add("contractor");
        params.add("contractorDeptCode");
        params.add("contractorDept");
        params.add("contractorPhone");
        params.add("partyName");
        params.add("advanceDateCount");
        params.add("contractDate");
        params.add("contractStartTime");
        params.add("contractEndTime");
        params.add("freeRentStartTime");
        params.add("freeRentEndTime");
//        params.add("startRentDate");
        params.add("incDetailType");

        /*********判断租金规则 开始************/
        if ("1".equals(contractEntity.getIncDetailType())) {//详细递增
            if (contractEntity.getIncDetailCase().indexOf(",,") >= 0) {
                throw new BusinessException("详细递增信息填写不完全");
            }
        } else if ("2".equals(contractEntity.getIncDetailType())) {//非详细递增
            params.add("increasingCycle");
            params.add("rentIncreaseProportion");
        }
        /*********判断租金规则  结束************/

        params.add("propertyUnitPrice");

        // 出租楼层非空校验
        List<ContractHouseEntity> conHouseList = contractEntity.getContractHouseList();
        if (conHouseList != null) {
            for (int i = 0; i < conHouseList.size(); i++) {
                if (StringUtils.isEmpty(conHouseList.get(i).getFlCode()) ||
                        StringUtils.isEmpty(conHouseList.get(i).getInvestArea()) ||
                        StringUtils.isEmpty(conHouseList.get(i).getAreaRate()) ||
                        StringUtils.isEmpty(conHouseList.get(i).getUnitPrice())) {

                    throw new BusinessException("出租楼层信息填写不完全");
                }
            }
        } else {
            throw new BusinessException("请填写房屋出租情况");
        }

        params.add("payRule");
        params.add("depositRule");
        params.add("deposit");
        params.add("basicElectricityPriceByMonth");
        params.add("totalElectricity");
        params.add("basicElectricityUnitPrice");
        params.add("addLoss");

        params.add("freeParkingCount");
        params.add("parkingPrice");
        params.add("contractUrl");

        params.add("outputValue");
        params.add("hasRevenueBackLocal");
        params.add("taxTake");
        params.add("equipmentList");
        params.add("equipmentValue");
        ParamUtil<ContractEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(contractEntity, params);

        // 合同状态为驳回，修改合同时将状态重置为待提交
        if ("5".equals(contractEntity.getCtStatus())) {
            contractEntity.setCtStatus("1");
        }

//        contractManager.update(contractEntity);
        contractManager.updateContract(contractEntity);

        return this.success(response);
    }

    @RequestMapping(path = "/basicInfoUpdate"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> basicInfoUpdate(@RequestBody ContractServiceRequest request) {
        ContractServiceResponse response = new ContractServiceResponse();

        ContractEntity requestEntity = request.getEntity();

        if(StringUtils.isEmpty(requestEntity.getCtCode())) {
            throw new BusinessException("更新唯一code参数传入错误");
        }

        contractManager.basicInfoUpdate(requestEntity);


        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 查询合同
     * @Auth wangchuang
     * @Date 2018/5/22 9:57
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ContractServiceRequest request) throws Exception {
        ContractServiceResponse response = new ContractServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<ContractEntity> paramUtil = new ParamUtil<ContractEntity>();
        paramUtil.objectToMap(request.getEntity(), param);

        if(!StringUtils.isEmpty(request.getContractDateStart()) && !StringUtils.isEmpty(request.getContractDateEnd())){
            Date contractDateStart = DateFormatUtil.dayBegin(request.getContractDateStart());
            Date contractDateEnd = DateFormatUtil.dayEnd(request.getContractDateEnd());
            param.put("contractDateStart",contractDateStart);
            param.put("contractDateEnd",contractDateEnd);
        }

        param.put("keyword", request.getKeyword());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        param.put("ctStatusFlag", request.getCtStatusFlag());
        PageResult<ContractEntity> result = contractManager.findByMap(param, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(result);

        return this.success(response);
    }

    /**
     * @Description 合同关联水电表，此接口用于物业中的水电抄表展示
     * @Auth luming
     * @Date 2019/8/20 16:18
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/queryMeter"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryMeter(@RequestBody ContractServiceRequest request) throws Exception {
        ContractServiceResponse response = new ContractServiceResponse();

        Map<String, Object> param = new HashMap<>();
        ParamUtil<ContractEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(request.getEntity(), param);

        if(!StringUtils.isEmpty(request.getContractDateStart()) && !StringUtils.isEmpty(request.getContractDateEnd())){
            Date contractDateStart = DateFormatUtil.dayBegin(request.getContractDateStart());
            Date contractDateEnd = DateFormatUtil.dayEnd(request.getContractDateEnd());
            param.put("contractDateStart",contractDateStart);
            param.put("contractDateEnd",contractDateEnd);
        }

        param.put("keyword", request.getKeyword());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        param.put("ctStatusFlag", request.getCtStatusFlag());
        PageResult<ContractEntity> result = contractManager.queryMeter(param, request.getStart(), request.getPageSize());

        response.setResult(result);

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 查询合同:仅合同列表页面调用
     * @Auth wangchuang
     * @Date 2018/5/22 9:57
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/queryDetail"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryDetail(@RequestBody ContractServiceRequest request) throws Exception {
        ContractServiceResponse response = new ContractServiceResponse();
        ContractEntity contractEntity = request.getEntity();

        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<ContractEntity> paramUtil = new ParamUtil<ContractEntity>();
        paramUtil.objectToMap(contractEntity, param);

        if(!StringUtils.isEmpty(request.getContractDateStart()) && !StringUtils.isEmpty(request.getContractDateEnd())){
            Date contractDateStart = DateFormatUtil.dayBegin(request.getContractDateStart());
            Date contractDateEnd = DateFormatUtil.dayEnd(request.getContractDateEnd());
            param.put("contractDateStart",contractDateStart);
            param.put("contractDateEnd",contractDateEnd);
        }
        param.put("keyword", request.getKeyword());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<ContractEntity> res = contractManager.findByMap(param, request.getStart(), request.getPageSize(), "id", false);

        List<ContractEntity> result = res.getData();

//        if (result != null) {
//            for (int i = 0; i < result.size(); i++) {
//                Map<String, Object> paramm = new HashMap<String, Object>();
//                paramm.put("ctCode", result.get(i).getCtCode());
//                paramm.put("isDeleted", false);
//                result.get(i).setContractHouseList(contractHouseManager.findByMap(paramm));
//
//                //拿到本期费用和下期费用
//                Date now = new Date();
//                List<RentReceiptEntity> rentList = rentReceiptManager.findByMap(paramm);
//                for (int j = 0; j < rentList.size(); j++) {
//                    if (rentList.get(j).getRentStartTime().compareTo(now) < 0 && rentList.get(j).getRentEndTime().compareTo(now) > 0) {
//                        result.get(i).setCurrentRentReceipt(rentList.get(j).getReceivableRent().add(rentList.get(j).getPropertyPrice()));
//                        result.get(i).setRentReceiptPayStatus(rentList.get(j).getPayStatus());
//                        //通过结束时间拿到下期租金物业费
//                        for (int l = 0; l < rentList.size(); l++) {
//                            if (rentList.get(j).getRentEndTime().compareTo(rentList.get(l).getRentStartTime()) == 0) {
//                                result.get(i).setNextRentReceipt(rentList.get(l).getReceivableRent().add(rentList.get(l).getPropertyPrice()));
//                            }
//                        }
//                    }
//                }
//
//                List<ElectricityReceiptEntity> eleList = electricityReceiptManager.findByMap(paramm);
//                for (int j = 0; j < eleList.size(); j++) {
//                    if (eleList.get(j).getLastBillingTime().compareTo(now) < 0 && eleList.get(j).getCurrentBillingTime().compareTo(now) > 0) {
//                        result.get(i).setCurrentElectricityReceipt(eleList.get(j).getReceivablePrice());
//                        result.get(i).setElectricityReceiptPayStatus(eleList.get(j).getPayStatus());
//                    }
//                }
//
//                List<ParkingReceiptEntity> parkingList = parkingReceiptManager.findByMap(paramm);
//                for (int j = 0; j < parkingList.size(); j++) {
//                    if (parkingList.get(j).getStartTime().compareTo(now) < 0 && parkingList.get(j).getEndTime().compareTo(now) > 0) {
//                        result.get(i).setCurrentParkingReceipt(parkingList.get(j).getReceivablePrice());
//                        result.get(i).setParkingReceiptPayStatus(parkingList.get(j).getPayStatus());
//                    }
//                }
//
//                List<WaterReceiptEntity> waterList = waterReceiptManager.findByMap(paramm);
//                for (int j = 0; j < waterList.size(); j++) {
//                    if (waterList.get(j).getLastBillingTime().compareTo(now) < 0 && waterList.get(j).getCurrentBillingTime().compareTo(now) > 0) {
//                        result.get(i).setCurrentWaterReceipt(waterList.get(j).getReceivablePrice());
//                        result.get(i).setWaterReceiptPayStatus(waterList.get(j).getPayStatus());
//                    }
//                }
//            }
//        }

//        res.setData(result);
        response.setResult(res);

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 查询合同集合, 同时查询每个合同的房源集合
     * @Auth xiongzhan
     * @Date 2018/7/20 9:57
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody ContractServiceRequest request) throws Exception {
        ContractServiceResponse response = new ContractServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<ContractEntity> paramUtil = new ParamUtil<ContractEntity>();
        paramUtil.objectToMap(request.getEntity(), param);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<ContractEntity> result = contractManager.findByMap(param);

        response.setResult(result);

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 通过ctCode查询合同, 房源列表，租金列表，水费列表，电费列表
     * @Auth wangchuang
     * @Date 2018/5/22 9:57
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/detail"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectByctCode(@RequestBody ContractServiceRequest request) throws Exception {
        //通过ctCode查询合同,房源列表，租金列表，水费列表，电费列表，保险费列表
        ContractServiceResponse response = new ContractServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ctCode", request.getEntity().getCtCode());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        ContractEntity contractEntity = contractManager.findOne(param);

        contractEntity.setContractHouseList(contractHouseManager.findByMap(param, "id", true));
        contractEntity.setElectricityReceiptList(electricityReceiptManager.findByMap(param, "id", true));
        contractEntity.setParkingReceiptList(parkingReceiptManager.findByMap(param, "id", true));
        contractEntity.setWaterReceiptList(waterReceiptManager.findByMap(param, "id", true));
        contractEntity.setPremiumReceiptList(premiumManager.findByMap(param, "id", true));

        List<RentReceiptEntity> rentReceiptEntities = rentReceiptManager.findByMap(param, "rent_start_time", true);

        //暂不需要租金详细表，先注释不查询
//        if (!rentReceiptEntities.isEmpty()) {
//            for (int i = 0; i < rentReceiptEntities.size(); i++) {
//                Map<String, Object> paramm = new HashMap<String, Object>();
//                paramm.put("rentCode", rentReceiptEntities.get(i).getRentCode());
//                paramm.put("isDeleted", false);
//                rentReceiptEntities.get(i).setRentReceiptList(rentReceiptDetailManager.findByMap(paramm));
//            }
//        }
        contractEntity.setRentReceiptList(rentReceiptEntities);

        response.setResult(contractEntity);

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 根据ct_code查询一条合同信息
     * @author wangchuang
     * @Date 2018/7/23 11:50
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/oneConDetail"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> oneConDetail(@RequestBody ContractServiceRequest request) throws IllegalAccessException {
        ContractServiceResponse response = new ContractServiceResponse();
        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<ContractEntity> paramUtil = new ParamUtil<ContractEntity>();
        paramUtil.objectToMap(request.getEntity(), param);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        ContractEntity result = contractManager.findOne(param);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @return
     * @Description 根据 pkCode 查询园区入驻的客户
     * @Auth luming
     * @Date 2018/7/27 18:51
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/queryConByPkCode"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryConByPkCode(@RequestBody ContractServiceRequest request) throws IllegalAccessException {

        ContractServiceResponse response = new ContractServiceResponse();
        ContractEntity entity = request.getEntity();

        int count = contractManager.queryConByPkCode(entity.getPkCode());
        response.setResult(count);
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 看板:租金费用异常查询
     * @author wangchuang
     * @Date 2018/8/7 15:49
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/queryRentCostException"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryCostException(@RequestBody ContractServiceRequest request) {

        ContractServiceResponse response = new ContractServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pkCode", request.getEntity().getPkCode());
        param.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<ContractEntity> result = contractManager.queryRentCostException(param, request.getStart(), request.getPageSize());

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 看板:电费费用异常查询
     * @author wangchuang
     * @Date 2018/8/8 18:21
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/queryEleCostException"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryEleCostException(@RequestBody ContractServiceRequest request) {

        ContractServiceResponse response = new ContractServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pkCode", request.getEntity().getPkCode());
        param.put("cpyCode", CurrentContext.getCpyCode());

        PageResult<ContractEntity> result = contractManager.queryEleCostException(param, request.getStart(), request.getPageSize());

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 看板:水费费用异常查询
     * @author wangchuang
     * @Date 2018/8/8 18:21
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/queryWaterCostException"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryWaterCostException(@RequestBody ContractServiceRequest request) {

        ContractServiceResponse response = new ContractServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pkCode", request.getEntity().getPkCode());
        param.put("cpyCode", CurrentContext.getCpyCode());


        PageResult<ContractEntity> result = contractManager.queryWaterCostException(param, request.getStart(), request.getPageSize());

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 看板:停车费费用异常查询
     * @author wangchuang
     * @Date 2018/8/8 19:01
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/queryParkingCostException"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> queryParkingCostException(@RequestBody ContractServiceRequest request) {

        ContractServiceResponse response = new ContractServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("pkCode", request.getEntity().getPkCode());
        param.put("cpyCode", CurrentContext.getCpyCode());


        PageResult<ContractEntity> result = contractManager.queryParkingCostException(param, request.getStart(), request.getPageSize());

        response.setResult(result);
        return this.success(response);
    }


    /**
     * @return
     * @Description 看板：我的数据
     * @Auth luming
     * @Date 2018/8/13 13:36
     * @Version 1.0
     * @Param
     */
    @RequestMapping(path = "/myData"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> myData(@RequestBody ContractServiceRequest request) throws Exception {

        ContractServiceResponse response = new ContractServiceResponse();

        // 本月第一天
        Calendar cf = Calendar.getInstance();
        cf.get(Calendar.YEAR);
        cf.get(Calendar.MONTH + 1);
        cf.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = cf.getTime();

        // 本月最后一天
        Calendar cl = Calendar.getInstance();
        cl.get(Calendar.YEAR);
        cl.get(Calendar.MONTH + 1);
        cl.set(Calendar.DAY_OF_MONTH, cl.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDate = cl.getTime();

        Map<String, Object> map = new HashMap<>();
        map.put("createCode", CurrentContext.getUserInfo().getUserCode());//签约人编号，即当前登陆账号
        map.put("contractDateStart", DateFormatUtil.dayBegin(firstDate));
        map.put("contractDateEnd", DateFormatUtil.dayEnd(lastDate));
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        int sum = contractManager.queryThisMonthAreaSum(map);//本月招商面积
        int count = contractManager.queryThisMonthContract(map);//本月新签合同

        Map<String, Object> result = new HashMap<>();
        result.put("areaSum", sum);
        result.put("contractCount", count);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 一键生成租金详情
     * @author wangchuang
     * @Date 2018/10/19 16:47
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/generateRentDetail"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> generateRentDetail(@RequestBody ContractServiceRequest request) {
        ContractServiceResponse response = new ContractServiceResponse();

        if (request.getEntity().getCtCode() == null || request.getEntity().getCtCode() == "") {
            throw new BusinessException("费用详情租金递增详情传入参数为空");
        }

        contractManager.initRentReceipt(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 合同审批状态
     * @Auth luming
     * @Date 2018/12/11 8:59
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/contractVerify"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> contractVerify(@RequestBody ContractServiceRequest request) {

        ContractServiceResponse response = new ContractServiceResponse();

        ContractEntity contractEntity = request.getEntity();

        // 修改合同状态
        contractManager.contractVerify(contractEntity);

        return this.success(response);
    }

    /**
     * @Description 园区用户信息导出
     * @author wangchuang
     * @Date 2019/1/10 18:56
     * @Version 1.0
     * @Param [httpServletRequest, httpServletResponse]
     * @return void
     **/
    @RequestMapping(path = "/exportparkCustomInfoExcel",
            method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws FileExportException, IOException {

        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;
        Map<String, Object> map = new HashMap<>();
        StringBuffer name = new StringBuffer();

        if(!StringUtils.isEmpty(httpServletRequest.getParameter("startDate")) && !StringUtils.isEmpty(httpServletRequest.getParameter("endDate"))){
            String startDate = httpServletRequest.getParameter("startDate");
            String endDate = httpServletRequest.getParameter("endDate");
            Long sDate = new Long(startDate);
            Long eDate = new Long(endDate);
            Date startTime = new Date(sDate);
            Date endTime = new Date(eDate);

            Date dayBegin = DateFormatUtil.dayBegin(startTime);
            Date dayEnd = DateFormatUtil.dayEnd(endTime);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
            String startDateStr = sdf.format(dayBegin);
            String endDateStr = sdf.format(dayEnd);
            name.append(startDateStr).append("-").append(endDateStr);
            //导出内容查询参数

            map.put("contractDateStart", dayBegin);
            map.put("contractDateEnd", dayEnd);
        }

        String tradeName = httpServletRequest.getParameter("tradeName");
        String pkCode = httpServletRequest.getParameter("pkCode");

        String path = "/exportTemplate/" + "parkCustomInfoReport" + ".xml";
        ExportConfig exportConfig = ExportConfigFactory.getExportConfig(ContractService.class.getResourceAsStream(path));

        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        if(org.apache.commons.lang3.StringUtils.isNotBlank(tradeName)){
            map.put("tradeName",tradeName);
        }
        if(org.apache.commons.lang3.StringUtils.isNotBlank(pkCode)){
            map.put("pkCode",pkCode);
        }
        map.put("ctStatus","4");

        List<ContractEntity> data = contractManager.findByMap(map,  "id", false);

        if(data.size() <= 0) {
            throw new BusinessException("无数据导出");
        }

        try {
            workbook =  FileExportor.getExportWorkbook(exportConfig,data);
            String excelName = name.toString() + "园区用户信息报表.xlsx";
            String filename = new String(excelName.getBytes("UTF-8"), "ISO8859-1");

            httpServletResponse.setHeader("Content-Type", "application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + filename);
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setCharacterEncoding("UTF-8");
            out = httpServletResponse.getOutputStream();
            if (workbook != null) {
                workbook.write(out);
            } else {
                workbook.write(out);
            }
            out.flush();
        }catch (Exception e){

        }finally {
            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    printWriter = null;
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    out = null;
                }
            }

            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    workbook = null;
                }
            }
        }
    }

    /**
     *@Author gengtao
     *@Date 2019-04-09 09:07:28
     *@Description 根据管理员编号，获取其三个月到期的合同数量
     *@Return
     **/
    @RequestMapping( path = "/expireInfoCount"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> expireInfoCount(@RequestBody ContractServiceRequest request) throws IllegalAccessException {
        ContractServiceResponse response = new ContractServiceResponse();
        Map<String,Object> map = new HashMap<>();
        ContractEntity entity = request.getEntity();
        ParamUtil paramUtil = new ParamUtil();
        paramUtil.objectToMap(entity,map);

        map.put("contractEndTimeStart",request.getContractDateStart());
        map.put("contractEndTimeEnd",request.getContractDateEnd());
        map.put("ctStatus","4");
        map.put("empCode",CurrentContext.getUserCode());
        map.put("isDeleted",false);
        map.put("cpyCode",CurrentContext.getCpyCode());
        int count = contractManager.selectInfoByEmpCodeCount(map);
        response.setResult(count);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-04-09 09:07:28
     *@Description 根据管理员编号，获取其三个月到期的合同信息
     *@Return
     **/
    @RequestMapping( path = "/expireInfo"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> expireInfo(@RequestBody ContractServiceRequest request) throws IllegalAccessException {
        ContractServiceResponse response = new ContractServiceResponse();
        Map<String,Object> map = new HashMap<>();
        ContractEntity entity = request.getEntity();
        ParamUtil paramUtil = new ParamUtil();
        paramUtil.objectToMap(entity,map);

        if(request.getIfMe().equals("1")){
            map.put("empCode",CurrentContext.getUserCode());
        }

        Date contractEndTimeStart = DateFormatUtil.dayBegin(request.getContractDateStart());
        Date contractEndTimeEnd = DateFormatUtil.dayEnd(request.getContractDateEnd());
        map.put("contractEndTimeStart",contractEndTimeStart);
        map.put("contractEndTimeEnd",contractEndTimeEnd);

        map.put("isDeleted",false);
        map.put("cpyCode",CurrentContext.getCpyCode());
        PageResult<ContractEntity> list = contractManager.selectInfoByEmpCode(map,request.getStart(), request.getPageSize());
        response.setResult(list);
        return this.success(response);
    }

    /**
     * @Description 查询园区详情并关联合同、合同房源
     * @author luming
     * @Date 2019/5/15 15:40
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping( path = "/parkDetail"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> parkDetail(@RequestBody ContractServiceRequest request) throws Exception {
        ContractServiceResponse response = new ContractServiceResponse();

        ContractEntity contractEntity = request.getEntity();

        try {
            Map<String, Object> param = new HashMap<>();
            param.put("isDeleted", false);
            param.put("pkCode", contractEntity.getPkCode());
            param.put("cpyCode", CurrentContext.getCpyCode());

            ParkEntity park = contractManager.parkDetails(param);

            Map<String, Object> paramm = new HashMap<>();
            paramm.put("pkCode", park.getPkCode());
            paramm.put("buttJointType", "1");
            paramm.put("isDeleted", false);
            paramm.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkButtJointEntity> atiButtJointList = parkButtJointManager.selectByPkCode(paramm);
            park.setAtiButtJointList(atiButtJointList);

            Map<String, Object> para = new HashMap<>();
            para.put("pkCode", park.getPkCode());
            para.put("buttJointType", "2");
            para.put("isDeleted", false);
            para.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkButtJointEntity> proButtJointList = parkButtJointManager.selectByPkCode(para);
            park.setProButtJointList(proButtJointList);

            response.setResult(park);
        } catch (Exception e) {
            this.error(response, "pkCode不唯一");
        }

        return this.success(response);
    }


    /**
     * @Description 查询楼栋详情并关联合同房源
     * @author luming
     * @Date 2019/5/16 16:48
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping( path = "/buildingDetail"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> buildingDetail(@RequestBody ContractServiceRequest request) throws Exception {
        ContractServiceResponse response = new ContractServiceResponse();
        ContractEntity contractEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("bdCode", request.getBdCode());
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted", false);

        BuildingEntity result = contractManager.buildingDetails(map);
        response.setResult(result);

        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-05-16 14:42:53
     *@Description 修改部分数据
     *@Return
     **/
    @RequestMapping(path = "/updateQuery"
            ,method = RequestMethod.POST
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> updateQuery(@RequestBody ContractServiceRequest request) throws IllegalAccessException {
        ContractServiceResponse response = new ContractServiceResponse();
        ContractEntity contractEntity = request.getEntity();
        List<String> params = new ArrayList<>();
        params.add("ctCode");
        ParamUtil<ContractEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(contractEntity,params);
        contractManager.update(contractEntity);
        return this.success(response);
    }

    @RequestMapping(path = "/expairDetail"
            ,method = RequestMethod.POST
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> expairDetail(@RequestBody ContractServiceRequest request) throws IllegalAccessException {
        ContractServiceResponse response = new ContractServiceResponse();
        String mmAndYy = request.getMmAndYY();
        if(StringUtils.isEmpty(mmAndYy)){
            throw new BuilderException("参数异常!");
        }

        String mmAndYyArr[] = mmAndYy.split("-");
        String mm = mmAndYyArr[0];
        String yy = mmAndYyArr[1];
        Map<String,Object> map = new HashMap<>();
        map.put("mm",mm);
        map.put("yy",yy);
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted", false);
        PageResult<ContractEntity> info = contractManager.selectByMMAndYY(map,request.getStart(), request.getPageSize());
        response.setResult(info);
        return this.success(response);
    }

    @RequestMapping(path = "/cusList"
            ,method = RequestMethod.POST
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> cusList(@RequestBody ContractServiceRequest request) throws IllegalAccessException {
        ContractServiceResponse response = new ContractServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<ContractEntity> paramUtil = new ParamUtil<ContractEntity>();
        paramUtil.objectToMap(request.getEntity(), param);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        List<ContractEntity> result = contractManager.findCus(param);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 电表剖面图：合同关联查询电表，电表关联抄表记录，并取最新一条的抄表数据
     * @Auth luming
     * @Date 2019/8/14 16:40
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/findEleMeterRecord"
            ,method = RequestMethod.POST
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> findEleMeterRecord(@RequestBody ContractServiceRequest request) throws IllegalAccessException {
        ContractServiceResponse response = new ContractServiceResponse();

        ContractEntity entity = request.getEntity();

        Date recordMonth = request.getRecordMonth();

        Map<String, Object> map = new HashMap<>();
        map.put("ctCode", entity.getCtCode());
        map.put("ctName", entity.getCtName());
        map.put("pkCode", entity.getPkCode());
        map.put("ctStatus", "4");

        PageResult<ContractEntity> pageResult = contractManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        if(pageResult.getData() == null || pageResult.getData().size() == 0) {
            throw new BusinessException("未找到该园区下签订的合同");
        }

        for (ContractEntity c : pageResult.getData()) {
            Map<String, Object> mp = new HashMap<>();
            mp.put("isDeleted", false);
            mp.put("cpyCode", CurrentContext.getCpyCode());
            mp.put("ctCode", c.getCtCode());

            if (recordMonth != null) {
                Date recordMonthStart = DateFormatUtil.monthBeginDay(recordMonth);
                Date recordMonthEnd = DateFormatUtil.monthEndDay(recordMonth);

                mp.put("recordMonthStart", recordMonthStart);
                mp.put("recordMonthEnd", recordMonthEnd);
            }

            List<ElectricityMeterEntity> eleList = electricityMeterManager.eleMeterUnionRecord(mp);
            c.setMeterList(eleList);
        }

        response.setResult(pageResult);
        return this.success(response);
    }

    /**
     * @Description 水表剖面图：合同关联查询水表，水表关联抄表记录，并取最新一条的抄表数据
     * @Auth luming
     * @Date 2019/8/15 10:22
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/findWaterMeterRecord"
            ,method = RequestMethod.POST
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> findWaterMeterRecord(@RequestBody ContractServiceRequest request) throws IllegalAccessException {
        ContractServiceResponse response = new ContractServiceResponse();

        ContractEntity entity = request.getEntity();

        Date recordMonth = request.getRecordMonth();

        Map<String, Object> map = new HashMap<>();
        map.put("ctCode", entity.getCtCode());
        map.put("ctName", entity.getCtName());
        map.put("pkCode", entity.getPkCode());
        map.put("ctStatus", "4");

        PageResult<ContractEntity> pageResult = contractManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        for (ContractEntity c : pageResult.getData()) {
            Map<String, Object> mp = new HashMap<>();
            mp.put("isDeleted", false);
            mp.put("cpyCode", CurrentContext.getCpyCode());
            mp.put("ctCode", c.getCtCode());

            if (recordMonth != null) {
                Date recordMonthStart = DateFormatUtil.monthBeginDay(recordMonth);
                Date recordMonthEnd = DateFormatUtil.monthEndDay(recordMonth);

                mp.put("recordMonthStart", recordMonthStart);
                mp.put("recordMonthEnd", recordMonthEnd);
            }

            List<WaterMeterEntity> waterMeterEntityList = waterMeterManager.waterMeterUnionRecord(mp);
            c.setWaterMeterList(waterMeterEntityList);
        }

        response.setResult(pageResult);
        return this.success(response);
    }
}
