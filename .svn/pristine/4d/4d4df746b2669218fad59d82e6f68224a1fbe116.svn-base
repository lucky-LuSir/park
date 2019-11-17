package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IElectricityReceiptManager;
import com.kfwy.park.ati.contract.business.IElectricityRecordManager;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.ati.contract.entity.ElectricityMeterEntity;
import com.kfwy.park.ati.contract.entity.ElectricityReceiptEntity;
import com.kfwy.park.ati.contract.entity.ElectricityRecordEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.contract.vo.ElectricityRecordServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.ElectricityRecordServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Description 电表抄表
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Transactional
@RestController
@RequestMapping(path = "/eleRecord")
public class ElectricityRecordService extends SpringRestService {

    @Autowired
    private IElectricityRecordManager electricityRecordManager;
    @Autowired
    private IElectricityReceiptManager electricityReceiptManager;


    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description
     * @author wangchuang
     * @Date 2018/8/16 11:21
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody ElectricityRecordServiceRequest request) throws Exception {
        ElectricityRecordServiceResponse response = new ElectricityRecordServiceResponse();

        List<ElectricityRecordEntity> recordEntityList = request.getRecordEntityList();

        //循环校验传入抄表信息集合中必填字段
        if (recordEntityList.size() > 0) {
            for (int i = 0; i < recordEntityList.size(); i++) {
                ElectricityRecordEntity recordEntity = recordEntityList.get(i);
                List<String> list = new ArrayList();
                list.add("eleMeterCode");
                list.add("ctCode");
                list.add("eleMeterName");
                list.add("electricityBillingType");
                if ("1".equals(recordEntity.getElectricityBillingType())) {
                    list.add("lastEleAverageDegree");
                    list.add("currentEleAverageDegree");
                    list.add("realDegree");
                } else if ("2".equals(recordEntity.getElectricityBillingType())) {
                    list.add("lastPeakDegree");
                    list.add("currentPeakDegree");
                    list.add("lastTroughDegree");
                    list.add("currentTroughDegree");
                    list.add("lastFlatDegree");
                    list.add("currentFlatDegree");
                    list.add("peakDegree");
                    list.add("troughDegree");
                    list.add("flatDegree");
                }
                ParamUtil<ElectricityRecordEntity> paramUtil = new ParamUtil<>();
                paramUtil.check(recordEntity, list);
            }
        }

        //判断抄表时间
        if (request.getEleTime().get(0) == null || request.getEleTime().get(1) == null) {
            throw new IllegalAccessException("抄表日期不能为空");
        }

        electricityRecordManager.batchCreate(recordEntityList, request.getEleTime());
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description
     * @author wangchuang
     * @Date 2018/8/16 11:22
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody ElectricityRecordServiceRequest request) {
        ElectricityRecordServiceResponse response = new ElectricityRecordServiceResponse();

        electricityRecordManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 查询分页
     * @author wangchuang
     * @Date 2018/8/16 11:28
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody ElectricityRecordServiceRequest request) throws IllegalAccessException {
        ElectricityRecordServiceResponse response = new ElectricityRecordServiceResponse();

        ElectricityRecordEntity recordEntity = request.getEntity();

        Map<String, Object> param = new HashMap<>();
        ParamUtil<ElectricityRecordEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(recordEntity, param);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        param.put("ctName", recordEntity.getCtName());
        param.put("pkCode", recordEntity.getPkCode());
        param.put("ctStatus", recordEntity.getCtStatus());

        if (recordEntity.getRecordMonth() != null) {
            Date recordMonthStart = DateFormatUtil.monthBeginDay(recordEntity.getRecordMonth());
            Date recordMonthEnd = DateFormatUtil.monthEndDay(recordEntity.getRecordMonth());

            param.put("recordMonthStart", recordMonthStart);
            param.put("recordMonthEnd", recordMonthEnd);
        }


        PageResult<ElectricityRecordEntity> result = electricityRecordManager.findByMap(param, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 查询
     * @author wangchuang
     * @Date 2018/8/16 11:39
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody ElectricityRecordServiceRequest request) throws IllegalAccessException {
        //响应json实体类字段
        ElectricityRecordServiceResponse response = new ElectricityRecordServiceResponse();

        ElectricityRecordEntity recordEntity = request.getEntity();

        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<ElectricityRecordEntity> paramUtil = new ParamUtil<ElectricityRecordEntity>();
        paramUtil.objectToMap(recordEntity, param);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<ElectricityRecordEntity> result = electricityRecordManager.findByMap(param);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     * @Description 查询上次抄表记录
     * @author wangchuang
     * @Date 2018/8/21 13:39
     * @Version 1.0
     * @Param [request]
     **/
    @RequestMapping(path = "/lastManual"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> lastManual(@RequestBody ElectricityRecordServiceRequest request) {
        ElectricityRecordServiceResponse response = new ElectricityRecordServiceResponse();

        Map<String, Object> param = new HashMap<String, Object>();

        param.put("ctCode", request.getEntity().getCtCode());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<ElectricityReceiptEntity> receiptList = electricityReceiptManager.findByMap(param, "id", false);
        if (receiptList.size() > 0) {
            //有上一期的抄表信息
            param.put("eleCode", receiptList.get(0).getEleCode());
            //这里算出下期开始时间(2020-8-5 23:59:59.999==>2018-8-6 00:00:00)
            Date curTime = receiptList.get(0).getCurrentBillingTime();
            curTime.setTime(curTime.getTime() + 1);

            //跟电表顺序对应(正序)
            List<ElectricityRecordEntity> recordList = electricityRecordManager.findByMap(param, "id", true);
            if (recordList.size() > 0) {
                for (int i = 0; i < recordList.size(); i++) {
                    recordList.get(i).setNextBillingTime(curTime);
                }
            }
            response.setResult(recordList);
        }

        return this.success(response);
    }

    /**
     * @Description 电表批量抄表
     * @author luming
     * @Date 2019/6/5 9:22
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/batchCreate"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> batchCreate(@RequestBody ElectricityRecordServiceRequest request) throws Exception {
        ElectricityRecordServiceResponse response = new ElectricityRecordServiceResponse();

        List<ContractEntity> contractList = request.getContractList();

        if (contractList != null) {
            for (ContractEntity c : contractList) {
                if (c.getMeterList() != null) {
                    for (ElectricityMeterEntity em : c.getMeterList()) {
                        ElectricityRecordEntity erEntity = em.getElectricityRecordEntity();
                        erEntity.setEleRecordCode(CodeGenUtils.generate());
                        electricityRecordManager.checkParams(em.getElectricityRecordEntity());
                        Date recordMonth = erEntity.getRecordMonth();
                        electricityRecordManager.create(erEntity);
                    }
                }
            }
        }
        return this.success(response);
    }

    /**
     * @Description 生成账单
     * @Auth luming
     * @Date 2019/8/8 9:42
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/createBill"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createBill(@RequestBody ElectricityRecordServiceRequest request) throws Exception {
        ElectricityRecordServiceResponse response = new ElectricityRecordServiceResponse();

        ElectricityRecordEntity entity = request.getEntity();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");

        List<ContractEntity> contractList = request.getContractList();

        if (contractList != null) {
            for (ContractEntity c : contractList) {
                /**
                 * 根据合同编号查询抄表记录
                 */
                Map<String, Object> map = new HashMap<>();
                map.put("ctCode", c.getCtCode());
                map.put("isDeleted", Boolean.FALSE);
                map.put("hasBill", Boolean.FALSE);

                if (entity.getRecordMonth() != null) {
                    Date dayBegin = DateFormatUtil.dayBegin(entity.getRecordMonth());
                    Date dayEnd = DateFormatUtil.monthEndDay(entity.getRecordMonth());

                    map.put("recordMonthStart", dayBegin);
                    map.put("recordMonthEnd", dayEnd);
                }

                List<ElectricityRecordEntity> list = electricityRecordManager.findByMap(map);


                StringBuilder sb = new StringBuilder();
                sb.append("合同编号：");
                sb.append(c.getCtName());
                sb.append("，客户名：");
                sb.append(c.getCompanyName());
                sb.append("，所属月份：");
                sb.append(sdf.format(request.getReceivingDate()));
                sb.append("，该合同在该月没有抄表或已经生成账单！");

                if (list.size() == 0) {
                    throw new BusinessException(sb.toString());
                }
                electricityRecordManager.createBill(list, request.getReceivingDate());
            }
        }

        return this.success(response);
    }
}
