package com.kfwy.park.controller.report;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IContractManager;
import com.kfwy.park.ati.contract.business.IRentIncrementManager;
import com.kfwy.park.ati.contract.business.IRentReceiptManager;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.ati.contract.entity.RentReceiptEntity;
import com.kfwy.park.bi.report.business.IGrossBiManager;
import com.kfwy.park.bi.report.entity.GrossEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.controller.bi.vo.GrossBiEntity;
import com.kfwy.park.controller.report.vo.ChartReportServiceRequest;
import com.kfwy.park.controller.report.vo.ChartReportServiceResponse;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by kfwy_it_004 on 2019/3/14.
 */
@Component
@RequestMapping("/chartReport")
public class ChartReportServive extends SpringRestService{
    @Autowired
    private IContractManager contractManager;

    @Autowired
    private IParkManager parkManager;

    @Autowired
    private IRentIncrementManager rentIncrementManager;

    @Autowired
    private IRentReceiptManager rentReceiptManager;

    @Autowired
    private IGrossBiManager grossBiManager;

    /**
     * 获取应收报表
     * @param request
     * @return
     */
    @RequestMapping(path = "/receivableList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> receivableList(@RequestBody ChartReportServiceRequest request){
        ChartReportServiceResponse response = new ChartReportServiceResponse();
        //获取数据
        Map<String,Object> result = getReceivableInfo(request);
        response.setResult(result);
        return this.success(response);
    }

    //获取应收报表数据
    public Map<String, Object> getReceivableInfo(ChartReportServiceRequest request){
        Map<String,Object> map = new HashMap<>();
        ParkEntity parkEntity = request.getEntity();
        if (request.getStartDate() != null && request.getEndDate() != null) {
            Date dayBegin = DateFormatUtil.dayBegin(request.getStartDate());
            Date dayEnd = DateFormatUtil.dayEnd(request.getEndDate());
            map.put("startDate", dayBegin);
            map.put("endDate", dayEnd);
        }
        if(StringUtils.isNotBlank(parkEntity.getPkCode())){
            map.put("pkCode",parkEntity.getPkCode());
        }
        if(StringUtils.isNotBlank(parkEntity.getCityCode())){
            map.put("cityCode",parkEntity.getCityCode());
        }
        if(StringUtils.isNotBlank(parkEntity.getTerritoryType())){
            map.put("territoryType",parkEntity.getTerritoryType());
        }
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        Map<String,Object> result = contractManager.receivableChart(map);
        return result;
    }


    /**
     * 获取去化概况报表
     */
    @RequestMapping(path = "/sellingReport"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> sellingReport(@RequestBody ChartReportServiceRequest request){
        if(null == request.getSelectMonth()){
            throw new BusinessException("请选择日期！");
        }
        ChartReportServiceResponse response = new ChartReportServiceResponse();
        //获取数据
        List<GrossBiEntity> result = getSellingInfo(request);
        response.setResult(result);
        return this.success(response);

    }

    //获取去化数据
    public List<GrossBiEntity> getSellingInfo(ChartReportServiceRequest request){
        List<GrossBiEntity> result = new ArrayList<>();
        ParkEntity parkEntity = request.getEntity();

        //情况一：获取所有大区的信息
        if(StringUtils.isBlank(parkEntity.getCityCode()) && StringUtils.isBlank(parkEntity.getTerritoryType()) && StringUtils.isBlank(parkEntity.getPkCode())){

            Map<String, Object> param = new HashMap<>();
            param.put("isDeleted", false);
            param.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkEntity> territoryList = parkManager.selectAllTerritory(param); //获取 所有大区
            territoryList.removeAll(Collections.singleton(null));  //去除所有null
            for(ParkEntity territory : territoryList){
                GrossBiEntity grossBiEntity = new GrossBiEntity();
                //获取大区的所有园区
                Map<String,Object> territoryTypeMap = new HashMap<>();
                territoryTypeMap.put("territoryType",territory.getTerritoryType());
                territoryTypeMap.put("isDeleted", false);
                territoryTypeMap.put("cpyCode", CurrentContext.getCpyCode());
                List<ParkEntity> parkList = parkManager.findByMap(territoryTypeMap);

                BigDecimal t_investArea = new BigDecimal("0"); //该大区计租总面积
                BigDecimal t_rentInvestArea = new BigDecimal("0");  //该大区已租面积
                BigDecimal t_rentableArea = new BigDecimal("0"); //该大区空置总面积
                BigDecimal t_totalThisMonthEndArea = new BigDecimal("0");  //该大区本月到期总面积
                BigDecimal t_totalNextMonthEndArea = new BigDecimal("0");  //该大区下月到期总面积

                //获取所有园区的各参数总值
                for(ParkEntity park : parkList){
                    GrossBiEntity totalInfo = getTotalInfo(park, request.getSelectMonth());
                    t_investArea = t_investArea.add(totalInfo.getInvestArea());
                    t_rentInvestArea = t_rentInvestArea.add(totalInfo.getRentInvestArea());
                    t_rentableArea = t_rentableArea.add(totalInfo.getRentableArea());
                    t_totalThisMonthEndArea = t_totalThisMonthEndArea.add(totalInfo.getTotalThisMonthEndArea());
                    t_totalNextMonthEndArea = t_totalNextMonthEndArea.add(totalInfo.getTotalNextMonthEndArea());
                }
                grossBiEntity.setTerritoryType(territory.getTerritoryType());  //大区类型ID
                grossBiEntity.setTerritoryTypeName(territory.getTerritoryTypeName());
                grossBiEntity.setInvestArea(t_investArea);//总计租面积
                grossBiEntity.setRentInvestArea(t_rentInvestArea);//总已出租面积
                grossBiEntity.setRentableArea(t_rentableArea);//总空置面积
                grossBiEntity.setTotalThisMonthEndArea(t_totalThisMonthEndArea);//本月到期总面积
                grossBiEntity.setTotalNextMonthEndArea(t_totalNextMonthEndArea);//次月到期总面积

                result.add(grossBiEntity);
            }
        }else{  //情况二：根据大区，城市，园区获取园区信息
            Map<String,Object> map = new HashMap<>();

            if(StringUtils.isNotBlank(parkEntity.getCityCode())){
                map.put("cityCode",parkEntity.getCityCode());
            }
            if(StringUtils.isNotBlank(parkEntity.getTerritoryType())){
                map.put("territoryType",parkEntity.getTerritoryType());
            }
            if(StringUtils.isNotBlank(parkEntity.getPkCode())){
                map.put("pkCode",parkEntity.getPkCode());
            }
            map.put("isDeleted",false);
            map.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkEntity> parkList = parkManager.findByMap(map);
            for(ParkEntity park : parkList){
                GrossBiEntity totalInfo = getTotalInfo(park, request.getSelectMonth());
                result.add(totalInfo);
            }
        }
        return result;
    }


    /**
     * 获取毛利率报表数据
     * @param request
     * @return
     */
    @RequestMapping(path = "/profitPort"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> profitPort(@RequestBody ChartReportServiceRequest request){
        if(null == request.getSelectMonth()){
            throw new BusinessException("请选择日期！");
        }
        List<GrossBiEntity> result = getProfitInfo(request);
        ChartReportServiceResponse response = new ChartReportServiceResponse();
        response.setResult(result);
        return this.success(response);
    }

    //获取毛利率数据
    public List<GrossBiEntity> getProfitInfo(ChartReportServiceRequest request){
        List<GrossBiEntity> result = new ArrayList<>();
        ParkEntity parkEntity = request.getEntity();

        //情况一：获取所有大区的信息
        if(StringUtils.isBlank(parkEntity.getCityCode()) && StringUtils.isBlank(parkEntity.getTerritoryType()) && StringUtils.isBlank(parkEntity.getPkCode())){

            Map<String, Object> param = new HashMap<>();
            param.put("isDeleted", false);
            param.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkEntity> territoryList = parkManager.selectAllTerritory(param); //获取 所有大区
            territoryList.removeAll(Collections.singleton(null));  //去除所有null
            for(ParkEntity territory : territoryList){
                GrossBiEntity grossBiEntity = new GrossBiEntity();
                //获取大区的所有园区
                Map<String,Object> territoryTypeMap = new HashMap<>();
                territoryTypeMap.put("territoryType",territory.getTerritoryType());
                territoryTypeMap.put("isDeleted", false);
                territoryTypeMap.put("cpyCode", CurrentContext.getCpyCode());
                List<ParkEntity> parkList = parkManager.findByMap(territoryTypeMap);

                BigDecimal t_RentIncome = new BigDecimal("0"); //该大区总房租费
                BigDecimal t_PropertyIncome = new BigDecimal("0");  //该大区总物业费
                BigDecimal t_RentCost = new BigDecimal("0"); //该大区总成本
                BigDecimal t_price = new BigDecimal("0"); //该大区总收入
                BigDecimal t_gross = new BigDecimal("0"); //该大区总利润

                //获取所有园区的各参数总值
                for(ParkEntity park : parkList){
                    GrossBiEntity totalInfo = getProfit(park, request.getSelectMonth());

                    t_RentIncome = t_RentIncome.add(totalInfo.getRentIncome());
                    t_PropertyIncome = t_PropertyIncome.add(totalInfo.getPropertyIncome());
                    t_RentCost = t_RentCost.add(totalInfo.getRentCost());
                }

                //毛利率 (园区收入总租金-园区拓展每月金额)/园区收入总租金
                t_price = t_RentIncome.add(t_PropertyIncome);
                if (t_price.compareTo(BigDecimal.ZERO) == 1) {
                    t_gross = (t_price.subtract(t_RentCost)).multiply(new BigDecimal(100)).divide(t_price, 2, BigDecimal.ROUND_HALF_UP);
                }
                grossBiEntity.setTerritoryType(territory.getTerritoryType());  //大区类型ID
                grossBiEntity.setTerritoryTypeName(territory.getTerritoryTypeName());
                grossBiEntity.setRentIncome(t_RentIncome);   //房租
                grossBiEntity.setPropertyIncome(t_PropertyIncome); //物业
                grossBiEntity.setRentCost(t_RentCost);  //成本
                grossBiEntity.setGross(t_gross); //利率

                result.add(grossBiEntity);
            }
        }else{  //情况二：根据大区，城市，园区获取园区信息
            Map<String,Object> map = new HashMap<>();

            if(StringUtils.isNotBlank(parkEntity.getCityCode())){
                map.put("cityCode",parkEntity.getCityCode());
            }
            if(StringUtils.isNotBlank(parkEntity.getTerritoryType())){
                map.put("territoryType",parkEntity.getTerritoryType());
            }
            if(StringUtils.isNotBlank(parkEntity.getPkCode())){
                map.put("pkCode",parkEntity.getPkCode());
            }
            map.put("isDeleted",false);
            map.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkEntity> parkList = parkManager.findByMap(map);
            for(ParkEntity park : parkList){
                GrossBiEntity grossBiEntity = getProfit(park, request.getSelectMonth());
                BigDecimal price = grossBiEntity.getRentIncome().add(grossBiEntity.getPropertyIncome());  //总租金
                BigDecimal gross = new BigDecimal("0");
                if(price.compareTo(BigDecimal.ZERO)==1){
                    gross = (price.subtract(grossBiEntity.getRentCost())).multiply(new BigDecimal(100)).divide(price, 2, BigDecimal.ROUND_HALF_UP); //毛利率
                }
                grossBiEntity.setGross(gross);
                result.add(grossBiEntity);
            }
        }
        return result;
    }

    /**
     * 获取单个园在该月份的去化信息
     * @return
     */
    public GrossBiEntity getTotalInfo(ParkEntity parkEntity, Date selectMonth){
        Date monthBegin = DateFormatUtil.monthBegin(selectMonth);
        Date monthEnd = DateFormatUtil.monthEnd(selectMonth);
        monthBegin = DateFormatUtil.dayBegin(monthBegin); //月初
        monthEnd = DateFormatUtil.dayEnd(monthEnd); //月底

        BigDecimal investArea =  parkEntity.getInvestArea(); //园区总计租面积

        //查出该园区的所有合同,得出已出租总招商面积,和金额
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("pkCode", parkEntity.getPkCode());
        map.put("monthBegin",monthBegin);
        map.put("monthEnd", monthEnd);
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("ctStatus","4");
        List<ContractEntity> contractEntityList = contractManager.selectConByMonth(map);

        BigDecimal rentInvestArea = new BigDecimal("0");  //该园区已租面积
        BigDecimal totalThisMonthEndArea = new BigDecimal("0");  //该园区本月到期总面积
        BigDecimal totalNextMonthEndArea = new BigDecimal("0");  //该园区下月到期总面积

        for(ContractEntity contract : contractEntityList){
            rentInvestArea = rentInvestArea.add(contract.getTotalRentalArea()) ;

            Calendar cal = Calendar.getInstance();
            cal.setTime(monthBegin);
            cal.add(Calendar.MONTH, +1);
            Date nextMonth = cal.getTime();  //下个月
            nextMonth = DateFormatUtil.dayBegin(nextMonth);

            Date contractMon = DateFormatUtil.dayBegin(DateFormatUtil.monthBegin(contract.getContractEndTime()));  //合同到期月初
            if(monthBegin.compareTo(contractMon) == 0){
                totalThisMonthEndArea = totalThisMonthEndArea.add(contract.getTotalRentalArea());
            }
            if(nextMonth.compareTo(contractMon) == 0){
                totalNextMonthEndArea = totalNextMonthEndArea.add(contract.getTotalRentalArea());
            }
        }

        BigDecimal rentableArea = investArea.subtract(rentInvestArea); //空置面积

        GrossBiEntity grossBiEntity = new GrossBiEntity();
        grossBiEntity.setPkCode(parkEntity.getPkCode());
        grossBiEntity.setPkName(parkEntity.getPkName());
        grossBiEntity.setInvestArea(investArea);//总计租面积
        grossBiEntity.setRentInvestArea(rentInvestArea);//总已出租面积
        grossBiEntity.setRentableArea(rentableArea);//总空置面积
        grossBiEntity.setTotalThisMonthEndArea(totalThisMonthEndArea);//本月到期总面积
        grossBiEntity.setTotalNextMonthEndArea(totalNextMonthEndArea);//次月到期总面积
        return grossBiEntity;
    }

    /**
     * 获取一个园区的物业费，租金和成本
     */
    public GrossBiEntity getProfit(ParkEntity parkEntity, Date selectMonth) {
        GrossBiEntity grossBiEntity = new GrossBiEntity();
        Date monthBegin = DateFormatUtil.monthBegin(selectMonth);
        Date monthEnd = DateFormatUtil.monthEnd(selectMonth);

        //月初
        monthBegin = DateFormatUtil.dayBegin(monthBegin);
        //月底
        monthEnd = DateFormatUtil.dayEnd(monthEnd);

        Map<String, Object> map = new HashMap<>();
        map.put("pkCode",parkEntity.getPkCode());
        map.put("receivingDateStart",monthBegin);
        map.put("receivingDateEnd",monthEnd);
        map.put("isDeleted",false);
        map.put("cpyCode",CurrentContext.getCpyCode());

        BigDecimal rentTotal = new BigDecimal("0");//园区每月租金
        BigDecimal propertyTotal = new BigDecimal("0");//园区每月物业费
        List<RentReceiptEntity> rentReceiptEntities = rentReceiptManager.findByPark(map);
        for(RentReceiptEntity entity : rentReceiptEntities){
            //毛利率数据
            BigDecimal totalLength = DateFormatUtil.daysBetween(entity.getRentStartTime(), entity.getRentEndTime()).add(new BigDecimal(1));
            BigDecimal monthLength = new BigDecimal(0);
            if(entity.getRentStartTime().compareTo(monthBegin)>= 0 && entity.getRentEndTime().compareTo(monthEnd) >=0){
                monthLength = DateFormatUtil.daysBetween(entity.getRentStartTime(), monthEnd).add(new BigDecimal(1));

            }else if(entity.getRentStartTime().compareTo(monthBegin)>=0 && entity.getRentEndTime().compareTo(monthEnd) <= 0){
                monthLength = DateFormatUtil.daysBetween(entity.getRentStartTime(), entity.getRentEndTime()).add(new BigDecimal(1));
            }else if(entity.getRentStartTime().compareTo(monthBegin) <= 0 && entity.getRentEndTime().compareTo(monthEnd) >= 0){
                monthLength = DateFormatUtil.daysBetween(monthBegin, monthEnd).add(new BigDecimal(1));
            }else if(entity.getRentStartTime().compareTo(monthBegin) <= 0 && entity.getRentEndTime().compareTo(monthEnd) <= 0){
                monthLength = DateFormatUtil.daysBetween(monthBegin, entity.getRentEndTime()).add(new BigDecimal(1));
            }
            //月租金 = 总租金/总天数*该月的天数
            BigDecimal rent = entity.getReceivableRent().divide(totalLength, 2, BigDecimal.ROUND_HALF_UP).multiply(monthLength);
            rentTotal = rentTotal.add(rent);
            //月物业费 = 总物业/总天数*该月的天数
            BigDecimal property = entity.getPropertyPrice().divide(totalLength, 2, BigDecimal.ROUND_HALF_UP).multiply(monthLength);
            propertyTotal = propertyTotal.add(property);

        }

        grossBiEntity.setPkCode(parkEntity.getPkCode());
        grossBiEntity.setPkName(parkEntity.getPkName());
        //园区每月租金
        grossBiEntity.setRentIncome(rentTotal);
        //园区每月物业费
        grossBiEntity.setPropertyIncome(propertyTotal);
        //园区拓展每月金额(单价*总承租面积*365/12)
        BigDecimal AveragePrice = parkEntity.getAveragePrice();
        BigDecimal Acreage = parkEntity.getAcreage();
        BigDecimal expandPrice = AveragePrice.multiply(Acreage).multiply(new BigDecimal(365)).divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
        grossBiEntity.setRentCost(expandPrice);
        return grossBiEntity;
    }

    /**
     *@Author gengtao
     *@Date 2019-05-14 13:56:35
     *@Description 应收,去化,毛利率总览
     *@Return
     **/
    @RequestMapping(path = "overView"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> overView(@RequestBody ChartReportServiceRequest request){
        if(null == request.getSelectMonth()){
            throw new BusinessException("请选择日期！");
        }
        Map<String, Object> result = new HashMap<>();

        ChartReportServiceResponse response = new ChartReportServiceResponse();
        //获取毛利率
        List<GrossBiEntity> profitInfo = getProfitInfo(request);
        Map<String, Object> profitMap = new HashMap<>();
        BigDecimal totalPropertyIncome = new BigDecimal("0"); //物业收入
        BigDecimal totalRentCost = new BigDecimal("0"); //成本
        BigDecimal totalRentIncome = new BigDecimal("0");//租金收入
        for(GrossBiEntity profit : profitInfo){
            totalPropertyIncome = totalPropertyIncome.add(profit.getPropertyIncome());
            totalRentCost = totalRentCost.add(profit.getRentCost());
            totalRentIncome = totalRentIncome.add(profit.getRentIncome());
        }
        profitMap.put("totalPropertyIncome",totalPropertyIncome);
        profitMap.put("totalRentCost",totalRentCost);
        profitMap.put("totalRentIncome",totalRentIncome);

        //获取去化
        List<GrossBiEntity> sellingInfo = getSellingInfo(request);
        Map<String, Object> sellingMap = new HashMap<>();
        BigDecimal totalRentableArea = new BigDecimal("0"); //空置面积
        BigDecimal totalRentInvestArea = new BigDecimal("0"); //已租面积
        for(GrossBiEntity selling : sellingInfo){
            totalRentableArea = totalRentableArea.add(selling.getRentableArea());
            totalRentInvestArea = totalRentInvestArea.add(selling.getRentInvestArea());
        }
        sellingMap.put("totalRentableArea",totalRentableArea);
        sellingMap.put("totalRentInvestArea",totalRentInvestArea);

        //获取应收
        Date selectMonth = request.getSelectMonth();
        Date monthBegin = DateFormatUtil.monthBegin(selectMonth);
        Date monthEnd = DateFormatUtil.monthEnd(selectMonth);
        request.setStartDate(monthBegin);
        request.setEndDate(monthEnd);
        Map<String,Object> receivableInfo = getReceivableInfo(request);
        Map<String, Object>receivableMap = new HashMap<>();
        receivableMap.put("totalPaidPrice",receivableInfo.get("totalPaidPrice"));
        receivableMap.put("totalWaitedPrice",receivableInfo.get("totalWaitedPrice"));

        result.put("receivableMap",receivableMap);
        result.put("sellingMap",sellingMap);
        result.put("profitMap",profitMap);
        response.setResult(result);
        return this.success(response);
    }

    //毛利率看板
    @RequestMapping(path = "/grossReport"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> grossReport(@RequestBody ChartReportServiceRequest request) {

        Date month = request.getSelectMonth();
        Date monthBegin = DateFormatUtil.monthBegin(month);
        Date monthEnd = DateFormatUtil.monthEnd(month);
        ParkEntity parkEntity = request.getEntity();
        //获取毛利率信息
        List<GrossEntity> result = grossBiManager.getGrossInfo(parkEntity, monthBegin, monthEnd);
        ChartReportServiceResponse response = new ChartReportServiceResponse();
        response.setResult(result);
        return this.success(response);
    }
}
