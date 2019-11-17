package com.kfwy.park.controller.report;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.context.RequestContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.act.activiti.business.IThrowLeaseApplyManager;
import com.kfwy.park.act.activiti.entity.ThrowLeaseApplyEntity;
import com.kfwy.park.ati.contract.business.IContractManager;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.bi.analysis.entity.MaturityEntity;
import com.kfwy.park.bi.report.business.IAccountReceivableBiManager;
import com.kfwy.park.bi.report.business.IElectricityReceivableBiManager;
import com.kfwy.park.bi.report.business.IGrossBiManager;
import com.kfwy.park.bi.report.business.IWaterReceivableBiManager;
import com.kfwy.park.bi.report.entity.CompanySituationEntity;
import com.kfwy.park.bi.report.entity.GrossEntity;
import com.kfwy.park.bi.report.entity.GrossThreadEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.controller.report.vo.CompanySituationServiceRequest;
import com.kfwy.park.controller.report.vo.CompanySituationServiceResponse;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * @Author gengtao
 * @Date 2019/7/30 16:20
 **/
@Controller
@RequestMapping("companySituation")
public class CompanySituationService extends SpringRestService {
    @Autowired
    private IWaterReceivableBiManager waterReceivableBiManager;
    @Autowired
    private IElectricityReceivableBiManager electricityReceivableBiManager;
    @Autowired
    private IAccountReceivableBiManager accountReceivableBiManager;
    @Autowired
    private IGrossBiManager grossBiManager;
    @Autowired
    private IParkManager parkManager;
    @Autowired
    private IContractManager contractManager;
    @Autowired
    private IThrowLeaseApplyManager throwLeaseApplyManager;

    @RequestMapping(value = "select"
    ,method = RequestMethod.POST
    ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
    ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> select(@RequestBody CompanySituationServiceRequest request) throws IllegalAccessException, InterruptedException {
        CompanySituationServiceResponse response = new CompanySituationServiceResponse();
        CompanySituationEntity situationEntity = request.getEntity();

        Date month = request.getMonthDate();
        if(null == month){
            month = new Date();
        }

        Date beginDate = DateFormatUtil.monthBeginDay(month);

        Date endDate = DateFormatUtil.monthEndDay(month);

        //获取主线程上下文
        RequestContext requestContext = CurrentContext.getRequestContext();
        //开启子线程获取毛利率
        GrossThreadEntity grossThreadEntity = new GrossThreadEntity(requestContext,grossBiManager,situationEntity,beginDate, endDate);
        Thread threadGro = new Thread(grossThreadEntity);
        threadGro.start();

    //    this.getGross(situationEntity,beginDate, endDate);
        //水费收费信息
        this.getWaterReceived(situationEntity, beginDate, endDate);
        //电费收费信息
        this.getElectricityReceived(situationEntity, beginDate, endDate);
        //租金收费信息
        this.getRentReceived(situationEntity, beginDate, endDate);
        //去化信息
        this.getSellingInfo(situationEntity);
        //近期到期客户信息
        this.getMaturityContractInfo(situationEntity,month,request.getMaturityNum());
        //近期营收
        int[] monthArray = {-2, -1, 0, 1, 2, 3};
        this.getSomeMonthRevenue(monthArray,situationEntity, month);

        //等待子线程结束
        threadGro.join();
        situationEntity.setProjectGross(grossThreadEntity.getSituationEntity().getProjectGross());
        situationEntity.setRentGross(grossThreadEntity.getSituationEntity().getRentGross());

        response.setResult(situationEntity);
        return this.success(response);
    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 09:48:31
     *@Description 获取水费信息
     *@param
     *@return
     **/
    public void getWaterReceived(CompanySituationEntity situationEntity, Date beginDate, Date endDate){
        Map<String, Object> params = new HashMap<>();
        this.getParams(situationEntity, params);

        //获取本月已收应收,不考虑收款状态
        params.put("startDate", beginDate);
        params.put("endDate",endDate);
        //获取水费已收和未收
        Map<String, Object> waterReceivableMap = waterReceivableBiManager.selectSumReceivable(params);
        //水费已收
        BigDecimal waterPaidPrice = (BigDecimal) waterReceivableMap.get("paidprice");
        //水费应收
        BigDecimal waterReceivablePrice = (BigDecimal) waterReceivableMap.get("receivableprice");
        //水费税收
        // BigDecimal waterTaxPrice = (BigDecimal) waterReceivableMap.get("taxprice");
        //水费未收
        BigDecimal waterUnReceivable = waterReceivablePrice.subtract(waterPaidPrice);

        //获取逾期信息,逾期状态为3,逾期不考虑时间
        params.put("payStatus","3");
        params.remove("startDate");
        params.remove("endDate");
        //获取水费逾期未收
        Map<String, Object> waterOverdueUnReceivedMap = waterReceivableBiManager.selectSumReceivable(params);
        //逾期应收
        BigDecimal waterOverdueReceived = (BigDecimal) waterOverdueUnReceivedMap.get("receivableprice");
        //逾期已收
        BigDecimal waterOverduePaid = (BigDecimal) waterOverdueUnReceivedMap.get("paidprice");
        //逾期未收
        BigDecimal waterOverdueUnReceived = waterOverdueReceived.subtract(waterOverduePaid);

        situationEntity.setWaterReceived(waterPaidPrice); //已收
        situationEntity.setWaterUnReceived(waterUnReceivable); //未收
        situationEntity.setWaterOverdueUnReceived(waterOverdueUnReceived); //逾期未收
    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 10:04:15
     *@Description 获取电费信息
     *@param
     *@return
     **/
    public void getElectricityReceived(CompanySituationEntity situationEntity, Date beginDate, Date endDate){
        Map<String, Object>params = new HashMap<>();
        this.getParams(situationEntity, params);
        //获取本月已收应收,不考虑收款状态
        params.put("startDate", beginDate);
        params.put("endDate",endDate);
        Map<String, Object> electricityReceivableMap = electricityReceivableBiManager.selectSumReceivable(params);
        BigDecimal electricityPaidPrice = (BigDecimal) electricityReceivableMap.get("paidprice");
        BigDecimal electricityReceivablePrice = (BigDecimal) electricityReceivableMap.get("receivableprice");
        BigDecimal electricityUnReceivable = electricityReceivablePrice.subtract(electricityPaidPrice);

        //获取逾期信息,逾期状态为3,逾期不考虑时间
        params.put("payStatus","3");
        params.remove("startDate");
        params.remove("endDate");
        Map<String, Object> electricityOverdueUnReceivedMap = electricityReceivableBiManager.selectSumReceivable(params);
        //逾期应收
        BigDecimal electricityOverdueReceived = (BigDecimal) electricityOverdueUnReceivedMap.get("receivableprice");
        //逾期已支付
        BigDecimal electricityOverduePaidPrice = (BigDecimal) electricityOverdueUnReceivedMap.get("receivableprice");
        //逾期未支付
        BigDecimal electricityOverdueUnReceived = electricityOverdueReceived.subtract(electricityOverduePaidPrice);

        situationEntity.setElectricityReceived(electricityPaidPrice);
        situationEntity.setElectricityUnReceived(electricityUnReceivable);
        situationEntity.setElectricityOverdueUnReceived(electricityOverdueUnReceived);
    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 10:04:15
     *@Description 获取租金信息
     *@param
     *@return
     **/
    public void getRentReceived(CompanySituationEntity situationEntity, Date beginDate, Date endDate){
        Map<String, Object>params = new HashMap<>();
        this.getParams(situationEntity, params);

        //获取本月已收应收,不考虑收款状态
        params.put("startDate", beginDate);
        params.put("endDate",endDate);
        Map<String, Object> rentReceivableMap = accountReceivableBiManager.selectSumReceivable(params);
        BigDecimal rentPaidPrice = (BigDecimal) rentReceivableMap.get("paidprice");
        BigDecimal rentReceivablePrice = (BigDecimal) rentReceivableMap.get("receivableprice");
        BigDecimal rentUnReceivable = rentReceivablePrice.subtract(rentPaidPrice);

        //获取逾期信息,逾期状态为3,逾期不考虑时间
        params.put("payStatus","3");
        params.remove("startDate");
        params.remove("endDate");
        Map<String, Object> rentOverdueUnReceivedMap = accountReceivableBiManager.selectSumReceivable(params);
        //逾期应收
        BigDecimal rentOverdueReceived = (BigDecimal) rentOverdueUnReceivedMap.get("receivableprice");
        //逾期已收
        BigDecimal rentOverduePaidPrice = (BigDecimal) rentOverdueUnReceivedMap.get("paidprice");
        //逾期未收
        BigDecimal rentOverdueUnReceived = rentOverdueReceived.subtract(rentOverduePaidPrice);

        situationEntity.setRentReceived(rentPaidPrice);
        situationEntity.setRentUnReceived(rentUnReceivable);
        situationEntity.setRentOverdueUnreceived(rentOverdueUnReceived);
    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 11:33:52
     *@Description 获取毛利率
     *@param
     *@return
     **/
    public void getGross(CompanySituationEntity situationEntity, Date monthBegin, Date monthEnd){
        ParkEntity parkEntity = new ParkEntity();
        if(!StringUtils.isEmpty(situationEntity.getTerritoryType())){
            parkEntity.setTerritoryType(situationEntity.getTerritoryType());
        }
        if(!StringUtils.isEmpty(situationEntity.getCityCode())){
            parkEntity.setCityCode(situationEntity.getCityCode());
        }
        if(!StringUtils.isEmpty(situationEntity.getPkCode())){
            parkEntity.setPkCode(situationEntity.getPkCode());
        }
        List<GrossEntity> gross = grossBiManager.getGrossInfo(parkEntity, monthBegin, monthEnd);
        //总收入
        BigDecimal totalRentIncome = new BigDecimal(0);
        //项目总承租金额
        BigDecimal totalProjectPrice = new BigDecimal(0);
        //出租总承租金额
        BigDecimal totalInvestPrice = new BigDecimal(0);
        for(GrossEntity gro : gross){
            totalRentIncome = totalRentIncome.add(gro.getRentIncome());
            totalProjectPrice = totalProjectPrice.add(gro.getProjectPrice());
            totalInvestPrice = totalInvestPrice.add(gro.getInvestPrice());
        }
        Map<String, Object> grossMap = grossBiManager.computeCross(totalRentIncome, totalProjectPrice, totalInvestPrice);
        BigDecimal projectGross = (BigDecimal) grossMap.get("projectGross");
        BigDecimal investGross = (BigDecimal) grossMap.get("investGross");
        situationEntity.setProjectGross(projectGross);
        situationEntity.setRentGross(investGross);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-01 17:42:58
     *@Description 获取去化信息
     *@param
     *@return
     **/
    public void getSellingInfo(CompanySituationEntity situationEntity){
        String pkCode = situationEntity.getPkCode();
        String cityCode = situationEntity.getCityCode();
        String territoryType = situationEntity.getTerritoryType();
        Map<String, Object> parkMap = new HashMap<>();
        parkMap.put("isDeleted", false);
        parkMap.put("cpyCode",CurrentContext.getCpyCode());
        if(org.apache.commons.lang3.StringUtils.isNotBlank(pkCode)){
            parkMap.put("pkCode",pkCode);
        }
        if(org.apache.commons.lang3.StringUtils.isNotBlank(cityCode)){
            parkMap.put("cityCode",cityCode);
        }
        if(org.apache.commons.lang3.StringUtils.isNotBlank(territoryType)){
            parkMap.put("territoryType",territoryType);
        }
        List<ParkEntity> parkEntityList = parkManager.findByMap(parkMap);

        //总计租
        BigDecimal totalInvestArea = new BigDecimal(0);
        //总已出租
        BigDecimal totalRentInvestArea = new BigDecimal(0);
        for(ParkEntity park : parkEntityList){
            totalInvestArea = totalInvestArea.add(park.getInvestArea());
            totalRentInvestArea = totalRentInvestArea.add(park.getRentInvestArea());
        }
        BigDecimal totalRentableArea = totalInvestArea.subtract(totalRentInvestArea);
        //去化率：总已出租/总计租
        BigDecimal rate = new BigDecimal(0);
        if(totalInvestArea.compareTo(BigDecimal.ZERO) != 0){
            rate = totalRentInvestArea.multiply(new BigDecimal(100)).divide(totalInvestArea,2,BigDecimal.ROUND_HALF_UP);
        }
        situationEntity.setInvestArea(totalInvestArea);
        situationEntity.setRentedArea(totalRentInvestArea);
        situationEntity.setRentableArea(totalRentableArea);
        situationEntity.setRate(rate);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-01 16:48:06
     *@Description 获取近期到期合同数据
     *@param situationEntity 包含数据的实体
     *@param monthDate 选中的月份
     *@param maturityNum 后几个月
     *@return
     **/
    public void getMaturityContractInfo(CompanySituationEntity situationEntity,Date monthDate, int maturityNum){
        Map<String, Object> map = new HashMap<>();
        Date monthBegin = DateFormatUtil.monthBegin(monthDate);
        Date contractEndTimeStart = DateFormatUtil.dayBegin(monthBegin);

        Date monthEnd = DateFormatUtil.getSomeMonthEnd(monthDate,maturityNum);
        Date contractEndTimeEnd = DateFormatUtil.dayEnd(monthEnd);

        map.put("contractEndTimeStart",contractEndTimeStart);
        map.put("contractEndTimeEnd",contractEndTimeEnd);
        String pkCode = situationEntity.getPkCode();
        String cityCode = situationEntity.getCityCode();
        String territoryType = situationEntity.getTerritoryType();
        map.put("isDeleted", false);
        map.put("cpyCode",CurrentContext.getCpyCode());
        if(org.apache.commons.lang3.StringUtils.isNotBlank(pkCode)){
            map.put("pkCode",pkCode);
        }
        if(org.apache.commons.lang3.StringUtils.isNotBlank(cityCode)){
            map.put("cityCode",cityCode);
        }
        if(org.apache.commons.lang3.StringUtils.isNotBlank(territoryType)){
            map.put("territoryType",territoryType);
        }

        //获取每个合同的到期面积
        List<Map<String, Object>> areaList = contractManager.selectMaturityArea(map);
        //到期数量
        situationEntity.setMaturityCusNum(areaList.size());

        //获取近几个月的年、月
        List<Map<String, String>> dateList = this.getBehindMonth(monthDate, maturityNum);

        //总到期面积
        BigDecimal totalArea = new BigDecimal(0);
        boolean if_totalArea = true;

        //存放每个月到期信息
        List<MaturityEntity> maturityEntityList =  new ArrayList<>();

        for (Map<String, String> date : dateList) {
            String date_year = date.get("year");
            String date_month = date.get("month");

            MaturityEntity maturityEntity = new MaturityEntity();
            maturityEntity.setMaturityYear(date_year);
            maturityEntity.setMaturityMonth(date_month);
            maturityEntity.setMaturityArea(new BigDecimal(0));

            for (Map<String, Object> m : areaList) {
                //每个合同的到期面积
                BigDecimal totalinvestarea = (BigDecimal) m.get("totalinvestarea");

                //总面积只循环一圈
                if (if_totalArea){
                    //计算总到期面积
                    totalArea = totalArea.add(totalinvestarea);
                }

                Date nowDate = (Date) m.get("contractendtime");
                Map<String, String> yearAndMon = DateFormatUtil.getYearAndMonth(nowDate);
                String nowYear = yearAndMon.get("year");
                String nowMonth = yearAndMon.get("month");

                //累加该月的总面积
                if (date_year.equals(nowYear) && date_month.equals(nowMonth)) {
                    BigDecimal maturityArea = maturityEntity.getMaturityArea();
                    maturityArea = maturityArea.add(totalinvestarea);
                    maturityEntity.setMaturityArea(maturityArea);
                }
            }
            //总面积不再累加
            if_totalArea = false;

            maturityEntityList.add(maturityEntity);
        }
        //到期总面积
        situationEntity.setMaturityArea(totalArea);

        situationEntity.setMaturityEntityList(maturityEntityList);
    }

    /*public void getMaturityContractInfo(CompanySituationEntity situationEntity,Date monthDate, int maturityNum){
        Map<String, Object> map = new HashMap<>();
        Date monthBegin = DateFormatUtil.monthBegin(monthDate);
        Date contractEndTimeStart = DateFormatUtil.dayBegin(monthBegin);

        Date monthEnd = DateFormatUtil.getSomeMonthEnd(monthDate,maturityNum);
        Date contractEndTimeEnd = DateFormatUtil.dayEnd(monthEnd);

        map.put("contractEndTimeStart",contractEndTimeStart);
        map.put("contractEndTimeEnd",contractEndTimeEnd);
        String pkCode = situationEntity.getPkCode();
        String cityCode = situationEntity.getCityCode();
        String territoryType = situationEntity.getTerritoryType();
        map.put("isDeleted", false);
        map.put("cpyCode",CurrentContext.getCpyCode());
        if(org.apache.commons.lang3.StringUtils.isNotBlank(pkCode)){
            map.put("pkCode",pkCode);
        }
        if(org.apache.commons.lang3.StringUtils.isNotBlank(cityCode)){
            map.put("cityCode",cityCode);
        }
        if(org.apache.commons.lang3.StringUtils.isNotBlank(territoryType)){
            map.put("territoryType",territoryType);
        }

        //获取每个月的到期数量
        List<MaturityEntity> everyMonthMaturity = maturityManager.selectMaturityContractByTimeAndPark(map);

        //获取每个合同的到期面积
        List<Map<String, Object>> areaList = contractManager.selectMaturityArea(map);
        //到期数量
        situationEntity.setMaturityCusNum(areaList.size());

        //总到期面积
        BigDecimal totalArea = new BigDecimal(0);

        for (Map<String, Object> m : areaList){
            //每个合同的到期面积
            BigDecimal totalinvestarea = (BigDecimal) m.get("totalinvestarea");
            //计算总到期面积
            totalArea = totalArea.add(totalinvestarea);

            //获取每个合同的到期年、月
            Calendar cal = Calendar.getInstance();
            cal.setTime((Date) m.get("contractendtime"));
            String year = String.valueOf(cal.get(Calendar.YEAR));
            int endMonth = cal.get(Calendar.MONTH)+1;
            String month = null;
            if(endMonth < 10){
                month = "0" + String.valueOf(endMonth);
            }else{
                month = String.valueOf(endMonth);
            }

            for(MaturityEntity maturityEntity : everyMonthMaturity){
                if(year.equals(maturityEntity.getMaturityYear()) && month.equals(maturityEntity.getMaturityMonth())){
                    if(null != maturityEntity.getMaturityArea()){
                        BigDecimal maturityArea = maturityEntity.getMaturityArea();
                        maturityArea = maturityArea.add(totalinvestarea);
                        maturityEntity.setMaturityArea(maturityArea);
                    }else{
                        maturityEntity.setMaturityArea(totalinvestarea);
                    }
                }
            }
        }
        //到期面积
        situationEntity.setMaturityArea(totalArea);
        //每个月的到期数量与到期面积

        this.fillMaturityArea(everyMonthMaturity, monthDate, maturityNum);




        situationEntity.setMaturityEntityList(everyMonthMaturity);

    }*/

    /**
     *@author gtaotao
     *@Date 2019-08-15 16:14:43
     *@Description 获取6个月内营收  先计算每个月中,合同状态为二审通过、退租、退租中的rent_total之和，然后减去其中已退租的和本月合同到期但状态为二审通过的rent_total
     *@param
     *@return
     **/
    public void getSomeMonthRevenue(int[] monthArray,CompanySituationEntity situationEntity, Date monthDate){
        List<MaturityEntity> maturityEntityList = new ArrayList<>();

        for (int i : monthArray){
            MaturityEntity maturityEntity = new MaturityEntity();

            //该月开始时间
            Date previousMonthBegin = DateFormatUtil.getSomeMonthBegin(monthDate,i);

            //获取年、月
            Map<String, String> yearAndMon = DateFormatUtil.getYearAndMonth(previousMonthBegin);
            maturityEntity.setMaturityYear(yearAndMon.get("year"));
            maturityEntity.setMaturityMonth(yearAndMon.get("month"));

            //该月开始时间
            Date monthBegin = DateFormatUtil.dayBegin(previousMonthBegin);
            //该月结束时间
            Date monthEnd = DateFormatUtil.monthEndDay(previousMonthBegin);


            Map<String, Object> map = new HashMap<>();
            //获取参数
            this.getParams(situationEntity, map);

            map.put("isDeleted",false);
            map.put("cpyCode", CurrentContext.getCpyCode());
            map.put("monthBegin", monthBegin);
            map.put("monthEnd", monthEnd);

            //获取该月所有合同总金额
            BigDecimal monthRentTotal = contractManager.selectSumRentTotal(map);

            //查询退租表中 合同开始时间到结束时间包含该月份 的退租信息
            List<ThrowLeaseApplyEntity> throwLeaseApplyEntityList = throwLeaseApplyManager.selectByContractTime(map);

            //查询合同结束日期在本月的合同
            List<ContractEntity> contractEntityList = contractManager.selectByContractMonth(map);

            /**
             *营收中删除退租和退租中多收的费用
             * 本月营收 = monthRentTotal - 本月多收费用的天数 * 本月每天的费用
             * 1 合同退租时间 < 该月 , 多收天数 = 一个月
             * 2 本月月初 <=合同退租时间 <= 该月月末 && 合同开始时间 <= 本月月初, 多收天数 = 月末 - 退租时间
             * 3 本月月初 <=合同退租时间 <= 该月月末 && 合同开始时间 >= 本月月初, 多收天数 = (合同开始时间 - 月初) + (月末 - 退租时间)
             * 4 合同退租时间 > 该月月末 && 合同开始时间 <= 本月月初 ,多收天数 = 0
             * 5 合同退租时间 > 该月月末 && 合同开始时间 >= 本月月初, 多收天数 = 合同开始时间 - 月初
             **/
            for(ThrowLeaseApplyEntity applyEntity : throwLeaseApplyEntityList){
                BigDecimal dayRent = applyEntity.getRentTotal().multiply(new BigDecimal(12)).divide(new BigDecimal(365),2,BigDecimal.ROUND_HALF_UP);

                //退租时间
                Date rentDate = applyEntity.getThrowLeaseDate();
                //合同开始时间
                Date conBeginDate = applyEntity.getContractStartTime();

                BigDecimal dayNum = new BigDecimal(0);

                //合同退租时间 < 该月
                if(rentDate.compareTo(monthBegin) <=0){
                    dayNum = new BigDecimal(365).divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);

                }else if(monthBegin.compareTo(rentDate) <= 0 && monthEnd.compareTo(rentDate) >=0){// 本月月初 <=合同退租时间 <= 该月月末
                    if(conBeginDate.compareTo(monthBegin) <= 0){ // 合同开始时间 <= 本月月初
                        dayNum = DateFormatUtil.daysBetween(rentDate, monthEnd);

                    }else if(conBeginDate.compareTo(monthBegin) >= 0){ //合同开始时间 >= 本月月初
                        BigDecimal dayNum_prev = DateFormatUtil.daysBetween(monthBegin, conBeginDate);
                        BigDecimal dayNum_behi = DateFormatUtil.daysBetween(rentDate, monthEnd);
                        dayNum = dayNum_prev.add(dayNum_behi);
                    }
                }else if (rentDate.compareTo(monthEnd) >= 0 && conBeginDate.compareTo(monthBegin) >= 0){  //合同退租时间 > 该月月末 && 合同开始时间 >= 本月月初
                    dayNum = DateFormatUtil.daysBetween(monthBegin, conBeginDate);
                }

                System.out.println(applyEntity.getCtCode());
                monthRentTotal = monthRentTotal.subtract(dayRent.multiply(dayNum));

//                if(maturityEntity.getMaturityMonth().equals("06")){
//                    writeDate(applyEntity.getCtCode(), dayRent, dayNum, "D:\\test.txt");
//                }

            }

            /**
             *营收中删除合同结束时间在该月,且状态为二审通过的合同中多收的费用
             * 1 合同开始日期 > 月初 , 多收天数 = (合同开始日期 - 月初) + (月末 - 合同结束日期)
             * 2 合同开始日期 < 月末 , 多收天数 = 月末 - 合同结束日期
             */
            for (ContractEntity contractEntity : contractEntityList){
                BigDecimal dayNum = new BigDecimal(0);
                BigDecimal dayRent = contractEntity.getRentTotal().multiply(new BigDecimal(12)).divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_UP);
                Date conBeginDate = contractEntity.getContractStartTime();
                Date conEndDate = contractEntity.getContractEndTime();
                if(conBeginDate.compareTo(monthBegin) >= 0){
                    BigDecimal dayNum_prev = DateFormatUtil.daysBetween(monthBegin, conBeginDate);
                    BigDecimal dayNum_behi = DateFormatUtil.daysBetween(conEndDate, monthEnd);
                    dayNum = dayNum_prev.add(dayNum_behi);
                    monthRentTotal = monthRentTotal.subtract(dayRent.multiply(dayNum));
                }else if(conBeginDate.compareTo(monthBegin) <= 0){
                    dayNum = DateFormatUtil.daysBetween(conEndDate, monthEnd);
                }
                monthRentTotal = monthRentTotal.subtract(dayRent.multiply(dayNum));
            }
            monthRentTotal = monthRentTotal.setScale(0,BigDecimal.ROUND_HALF_UP);
            maturityEntity.setRevenue(monthRentTotal);
            maturityEntityList.add(maturityEntity);
        }
        situationEntity.setEveryMonthRevenue(maturityEntityList);
    }

    public void writeDate(String ctCode, BigDecimal dayRent, BigDecimal dayNum, String fileName){
        String data = ctCode + "    " + String.valueOf(dayRent)+"   " + String.valueOf(dayNum) + "\r\n";
        File file = new File(fileName);
        try {
            FileOutputStream out = new FileOutputStream(file, true);
            OutputStreamWriter writer = new OutputStreamWriter(out);
            writer.write(data);
            writer.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void getSomeMonthRevenue_b(int[] monthArray, CompanySituationEntity situationEntity, Date monthDate){
        List<MaturityEntity> maturityEntityList = new ArrayList<>();
        for(int i : monthArray){
            MaturityEntity maturityEntity = new MaturityEntity();

            //该月开始时间
            Date previousMonthBegin = DateFormatUtil.getSomeMonthBegin(monthDate,i);

            //获取年、月
            Map<String, String> yearAndMon = DateFormatUtil.getYearAndMonth(previousMonthBegin);
            maturityEntity.setMaturityYear(yearAndMon.get("year"));
            maturityEntity.setMaturityMonth(yearAndMon.get("month"));

            //该月开始时间
            Date monthBegin = DateFormatUtil.dayBegin(previousMonthBegin);
            //该月结束时间
            Date monthEnd = DateFormatUtil.monthEndDay(previousMonthBegin);

            Map<String, Object> map = new HashMap<>();
            map.put("isDeleted",false);
            map.put("cpyCode", CurrentContext.getCpyCode());
            map.put("monthBegin", monthBegin);
            map.put("monthEnd", monthEnd);
            List<ContractEntity> contractEntityList = contractManager.selectByTime(map);

            //本月营收所有合同应收 = 本月每个合同应收之和
            BigDecimal monthRevenue = new BigDecimal(0);

            for (ContractEntity con : contractEntityList){
                if(con.getCtCode().equals("190102d477aace951e44")){
                    System.out.println(con.getCtCode());
                }
                Date conStartDate = con.getContractStartTime();
                Date conEndDate = con.getContractEndTime();

                //实际每个合同本月营收 = 合同每天营收 * 实租天数
                BigDecimal realRent = new BigDecimal(0);
                //本月实租天数
                BigDecimal dayNum = new BigDecimal(0);

                //合同中月营收 转为 天营收
                BigDecimal dayRent = con.getRentTotal().multiply(new BigDecimal(12)).divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_UP);

                /**
                 *当合同状态为退租和退租中时,本月营收 = 每天单价 * 该月实租天数
                 * 1 合同退租时间 < 本月, 本月实租天数 = 0
                 * 2 本月月初 <=合同退租时间 <= 该月月末 && 合同开始时间 <= 本月月初, 本月实租天数 = 退租时间 - 月初 + 1
                 * 3 本月月初 <=合同退租时间 <= 该月月末 && 合同开始时间 >= 本月月初, 本月实租天数 = 退租时间 - 合同开始时间 + 1
                 * 4 合同退租时间 > 该月月末 && 合同开始时间 <= 本月月初, 本月实租天数 = 365/12
                 * 5 合同退租时间 > 该月月末 && 合同开始时间 >= 本月月初, 本月实租天数 = 本月月末 - 合同开始时间 + 1;
                 *
                 * 当合同状态为二审通过,但合同结束日期在本月时,本月营收 = 每天单价 * 该月实租天数
                 * 1 合同开始日期 >= 月初, 本月实租天数 = 合同结束日期 - 合同开始时间 +1
                 * 2 合同开始日期 <= 月初, 本月实租天数 = 合同结束日期 - 月初 +1
                 */
                if (con.getCtStatus().equals("7") || con.getCtStatus().equals("9")){
                    //con中关联了退租表,如果合同退租,则con中一定有退租时间
                    if (null == con.getThrowLeaseDate()){
                        throw new BusinessException("数据出错,未查到退租信息!");
                    }
                    //退租时间
                    Date rentDate = con.getThrowLeaseDate();

                    if (rentDate.compareTo(monthBegin) <= 0){
                        //当合同为退租，且合同退租时间 < 本月,则该合同本月营收为0
                        dayNum = dayNum;
                    }else if (rentDate.compareTo(monthBegin) >= 0 && rentDate.compareTo(monthEnd) <=0){
                        if (conStartDate.compareTo(monthBegin) <= 0){
                            //本月月初 <=合同退租时间 <= 该月月末 && 合同开始时间 <= 本月月初
                            dayNum = DateFormatUtil.daysBetween(monthBegin,rentDate).add(new BigDecimal(1));
                        }else{
                            //本月月初 <=合同退租时间 <= 该月月末 && 合同开始时间 >= 本月月初
                            dayNum = DateFormatUtil.daysBetween(conStartDate,rentDate).add(new BigDecimal(1));
                        }
                    }else if (rentDate.compareTo(monthEnd) >=0){
                        if (conStartDate.compareTo(monthBegin) <=0){
                            //合同退租时间 > 该月月末 && 合同开始时间 <= 本月月初
                            dayNum = new BigDecimal(365).divide(new BigDecimal(12),2, BigDecimal.ROUND_HALF_UP);
                        }else if (conStartDate.compareTo(monthBegin) >=0){
                            dayNum = DateFormatUtil.daysBetween(conStartDate, monthEnd).add(new BigDecimal(1));
                        }
                    }
                }else if(conEndDate.compareTo(monthBegin) >=0 && conEndDate.compareTo(monthEnd) <= 0){
                    if(conStartDate.compareTo(monthBegin) >= 0){
                        //合同开始日期 >= 月初
                        dayNum = DateFormatUtil.daysBetween(conStartDate, conEndDate).add(new BigDecimal(1));
                    }else if (conStartDate.compareTo(monthBegin) <= 0){
                        //合同开始日期 <= 月初
                        dayNum = DateFormatUtil.daysBetween(monthBegin, conEndDate).add(new BigDecimal(1));
                    }
                }else {
                    dayNum = new BigDecimal(365).divide(new BigDecimal(12),2, BigDecimal.ROUND_HALF_UP);
                }

                //实际本月营收 = 合同每天营收 * 实租天数
                realRent = dayRent.multiply(dayNum);

                //本月营收所有合同应收 = 本月每个合同应收之和
                monthRevenue = monthRevenue.add(realRent);

                if(maturityEntity.getMaturityMonth().equals("06")){
                    writeDate(con.getCtCode(), dayRent, dayNum, "D:\\test2.txt");
                }
            }
            monthRevenue = monthRevenue.setScale(0, BigDecimal.ROUND_HALF_UP);
            maturityEntity.setRevenue(monthRevenue);
            maturityEntityList.add(maturityEntity);
        }
        situationEntity.setEveryMonthRevenue(maturityEntityList);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-15 11:33:27
     *@Description 获取近几个月的年、月
     *@param monthDate 所查月份
     *@param maturityNum 后面月份的数量
     *@return
     **/
    public List<Map<String,String>> getBehindMonth(Date monthDate, int maturityNum){
        List<Map<String,String>> list = new ArrayList<>();
        for (int i = 0; i <= maturityNum; i++){
            Date nowMonth = DateFormatUtil.getSomeMonthEnd(monthDate,i);
            Map<String, String> date = DateFormatUtil.getYearAndMonth(nowMonth);
            list.add(date);
        }
        return list;
    }

    /**
     *@author gtaotao
     *@Date 2019-08-06 17:06:19
     *@Description 填充到期信息，填充每个月的到期面积与到期数量，为空时置为0
     *@param everyMonthMaturity 到期信息列表
     *@param  monthDate 起始月
     *@param  maturityNum 近几个月
     *@return
     **/
    public void fillMaturityArea(List<MaturityEntity> everyMonthMaturity, Date monthDate, int maturityNum){
        for(int i = 0; i <= maturityNum; i++){
            Date nowMonth = DateFormatUtil.getSomeMonthEnd(monthDate,i);
            Calendar cal = Calendar.getInstance();
            cal.setTime(nowMonth);
            String year = String.valueOf(cal.get(Calendar.YEAR));
            int endMonth = cal.get(Calendar.MONTH)+1;

            String month = null;
            if(endMonth < 10){
                month = "0" + String.valueOf(endMonth);
            }else{
                month = String.valueOf(endMonth);
            }
            boolean flag = true;
            for (MaturityEntity maturityEntity : everyMonthMaturity){
                if(year.equals(maturityEntity.getMaturityYear()) && month.equals(maturityEntity.getMaturityMonth())){
                    if(null == maturityEntity.getMaturityArea()){
                        maturityEntity.setMaturityArea(new BigDecimal(0));
                    }
                    if (null == maturityEntity.getMaturityCount()){
                        maturityEntity.setMaturityCount(0);
                    }
                    flag = false;
                }
            }
            if (flag){
                MaturityEntity entity = new MaturityEntity();
                entity.setMaturityYear(year);
                entity.setMaturityMonth(month);
                entity.setMaturityCount(0);
                entity.setMaturityArea(new BigDecimal(0));
                everyMonthMaturity.add(entity);
            }
        }
    }

    /**
     *@author gtaotao
     *@Date 2019-08-19 09:21:05
     *@Description 获取条件参数
     *@param
     *@return
     **/
    public void getParams(CompanySituationEntity situationEntity, Map<String, Object> map){
        if (StringUtils.isNoneBlank(situationEntity.getCityCode())){
            map.put("cityCode",situationEntity.getCityCode());
        }
        if(StringUtils.isNoneBlank(situationEntity.getPkCode())){
            map.put("pkCode", situationEntity.getPkCode());
        }
        if(StringUtils.isNoneBlank(situationEntity.getTerritoryType())){
            map.put("territoryType", situationEntity.getTerritoryType());
        }
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted", false);
    }
}