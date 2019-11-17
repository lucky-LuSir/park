package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IWaterReceiptManager;
import com.kfwy.park.ati.contract.business.IWaterRecordManager;
import com.kfwy.park.ati.contract.entity.*;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.contract.vo.WaterRecordServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.WaterRecordServiceResponse;
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
 * @Description 水表抄表
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Transactional
@RestController
@RequestMapping("waterRecord")
public class WaterRecordService extends SpringRestService {

    @Autowired
    private IWaterRecordManager waterRecordManager;
    @Autowired
    private IWaterReceiptManager waterReceiptManager;


    @RequestMapping(path = "/create",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody WaterRecordServiceRequest request) throws IllegalAccessException {

        WaterRecordServiceResponse response = new WaterRecordServiceResponse();

        List<WaterRecordEntity> waterRecordList = request.getWaterRecordList();

        if(waterRecordList.size() > 0) {
            for(int i=0; i< waterRecordList.size(); i++) {
                WaterRecordEntity waterRecordEntity = waterRecordList.get(i);
                //将必填字段放入水费容器
                List<String> param = new ArrayList<>();
                param.add("waterMeterCode");
                param.add("waterMeterName");
                param.add("ctCode");
                param.add("waterBillingType");
                if ("2".equals(waterRecordEntity.getWaterBillingType())) {
                    param.add("lastBillingWater");
                    param.add("currentBillingWater");
                    param.add("waterUnitPrice");
                    param.add("realWater");
                }
                if ("1".equals(waterRecordEntity.getWaterBillingType())) {
                    param.add("waterAmount");
                }

                ParamUtil<WaterRecordEntity> paramUtil = new ParamUtil<>();
                paramUtil.check(waterRecordEntity,param);
            }
        }

        //判断抄表时间
        if (request.getWaterTime().get(0) == null || request.getWaterTime().get(1) == null) {
            throw new IllegalAccessException("抄表日期不能为空");
        }

        waterRecordManager.batchCreate(waterRecordList, request.getWaterTime());

        return this.success(response);
    }
    
    /**
     * @Description 修改
     * @author wangchuang
     * @Date 2018/11/21 9:50
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody WaterRecordServiceRequest request) throws IllegalAccessException {
        WaterRecordServiceResponse response = new WaterRecordServiceResponse();

        //将必填字段放入水费容器
        List<String> param = new ArrayList<>();
        param.add("waterRecordCode");
        param.add("waterMeterCode");
        param.add("ctCode");
        param.add("waterBillingType");
        if ("2".equals(request.getEntity().getWaterBillingType())) {
            param.add("lastBillingWater");
            param.add("currentBillingWater");
            param.add("waterUnitPrice");
            param.add("realWater");
        }
        if ("1".equals(request.getEntity().getWaterBillingType())) {
            param.add("waterAmount");
        }

        ParamUtil<WaterRecordEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(request.getEntity(), param);

        waterRecordManager.update(request.getEntity());

        return this.success(response);
    }

    /**
     * @Description 分页查询
     * @author wangchuang
     * @Date 2018/11/21 9:53
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody WaterRecordServiceRequest request) throws IllegalAccessException {
        WaterRecordServiceResponse response = new WaterRecordServiceResponse();
        WaterRecordEntity waterRecordEntity = request.getEntity();

        Map<String, Object> param = new HashMap<String, Object>();
        ParamUtil<WaterRecordEntity> paramUtil = new ParamUtil<WaterRecordEntity>();
        paramUtil.objectToMap(waterRecordEntity,param);
        param.put("isDeleted",false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        param.put("ctName", waterRecordEntity.getCtName());
        param.put("pkCode", waterRecordEntity.getPkCode());
        param.put("ctStatus", waterRecordEntity.getCtStatus());

        if (waterRecordEntity.getRecordMonth() != null) {
            Date recordMonthStart = DateFormatUtil.monthBeginDay(waterRecordEntity.getRecordMonth());
            Date recordMonthEnd = DateFormatUtil.monthEndDay(waterRecordEntity.getRecordMonth());

            param.put("recordMonthStart", recordMonthStart);
            param.put("recordMonthEnd", recordMonthEnd);
        }

        PageResult<WaterRecordEntity> result = waterRecordManager.findByMap(param, request.getStart(), request.getPageSize(), "id", true);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询集合
     * @author wangchuang
     * @Date 2018/11/21 9:54
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody WaterRecordServiceRequest request) throws IllegalAccessException {
        WaterRecordServiceResponse response = new WaterRecordServiceResponse();

        Map<String,Object> map=new HashMap<>();

        ParamUtil<WaterRecordEntity> paramUtil = new ParamUtil<WaterRecordEntity>();
        paramUtil.objectToMap(request.getEntity(),map);
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<WaterRecordEntity> result = waterRecordManager.findByMap(map,"id",true);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description TODO
     * @author wangchuang
     * @Date 2018/11/21 16:58
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/lastWaterRecord"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> lastWaterRecord(@RequestBody WaterRecordServiceRequest request) {
        WaterRecordServiceResponse response = new WaterRecordServiceResponse();

        Map<String,Object> map=new HashMap<>();
        map.put("ctCode", request.getEntity().getCtCode());
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<WaterReceiptEntity> receiptList = waterReceiptManager.findByMap(map, "id", false);
        if(receiptList.size() > 0) {
            //有上一期抄表记录
            map.put("waterCode", receiptList.get(0).getWaterCode());
            //这里算出下期开始时间(2020-8-5 23:59:59.999==>2018-8-6 00:00:00)
            Date curTime = receiptList.get(0).getCurrentBillingTime();
            curTime.setTime(curTime.getTime()+1);

            //跟水表顺序对应(正序)
            List<WaterRecordEntity> recordList = waterRecordManager.findByMap(map, "id", true);
            if(recordList.size() > 0) {
                for (int i=0;i<recordList.size();i++) {
                    recordList.get(i).setNextBillingTime(curTime);
                }
            }
            response.setResult(recordList);
        }

        return this.success(response);
    }

    /**
     * @Description 水表批量抄表
     * @Auth luming
     * @Date 2019/6/6 16:06
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/batchCreate"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> batchCreate(@RequestBody WaterRecordServiceRequest request) {
        WaterRecordServiceResponse response = new WaterRecordServiceResponse();

        List<ContractEntity> contractList = request.getContractList();

        if (contractList != null) {
            for (ContractEntity c : contractList) {
                if (c.getWaterMeterList() != null) {
                    for (WaterMeterEntity wm : c.getWaterMeterList()) {
                        WaterRecordEntity waterRecordEntity = wm.getWaterRecordEntity();
                        waterRecordEntity.setWaterRecordCode(CodeGenUtils.generate());
                        waterRecordManager.create(waterRecordEntity);
                    }
                }
            }
        }
        return this.success(response);
    }

    /**
     * @Description 生成账单
     * @Auth luming
     * @Date 2019/8/9 8:55
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/createBill"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> createBill(@RequestBody WaterRecordServiceRequest request) throws Exception {
        WaterRecordServiceResponse response = new WaterRecordServiceResponse();

        WaterRecordEntity entity = request.getEntity();

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

                List<WaterRecordEntity> list = waterRecordManager.findByMap(map);

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
                waterRecordManager.createBill(list, request.getReceivingDate());
            }
        }
        return this.success(response);
    }
}
