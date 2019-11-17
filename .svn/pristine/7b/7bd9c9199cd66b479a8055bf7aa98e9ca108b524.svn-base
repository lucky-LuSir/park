package com.kfwy.park.controller.bi;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IRentReceiptManager;
import com.kfwy.park.ati.contract.entity.RentReceiptEntity;
import com.kfwy.park.bi.report.business.IGrossBiManager;
import com.kfwy.park.common.easyexcel.export.exception.FileExportException;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ExportFileUtil;
import com.kfwy.park.controller.bi.vo.GrossBiEntity;
import com.kfwy.park.controller.bi.vo.GrossBiServiceRequest;
import com.kfwy.park.controller.bi.vo.GrossBiServiceResponse;
import com.kfwy.park.bi.report.entity.GrossEntity;
import com.kfwy.park.pro.house.building.business.IFloorManager;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liuzhengyang on 2018/9/14.
 */
@Component
@RequestMapping("/grossBi")
public class GrossBiService extends SpringRestService {

    @Autowired
    private IParkManager parkManager;
    @Autowired
    private IRentReceiptManager rentReceiptManager;
    @Autowired
    private IFloorManager floorManager;
    @Autowired
    private IGrossBiManager grossBiManager;



    @RequestMapping(path = "/select"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> select(@RequestBody GrossBiServiceRequest request) {

        GrossBiServiceResponse response = new GrossBiServiceResponse();
        List<GrossBiEntity> grossList = new ArrayList<>();
        String pkCode = request.getPkCode();
        String cityCode = request.getCityCode();
        String territoryType = request.getTerritoryType();
        Date selectMonth = request.getSelectMonth();
        //查询日期校验
        if (selectMonth == null) {
            throw new BusinessException("传入查询日期参数为空");
        }
        Map<String,Object> map = new HashMap<>();

        if(StringUtils.isNotBlank(pkCode)){
            map.put("pkCode",pkCode);
        }
        if(StringUtils.isNotBlank(cityCode)){
            map.put("cityCode",cityCode);
        }
        if(StringUtils.isNotBlank(territoryType)){
            map.put("territoryType",territoryType);
        }
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<ParkEntity> parkList = parkManager.findByMap(map);

        for (ParkEntity pk : parkList) {
            GrossBiEntity resGross = selectGrossInfo(pk, selectMonth);
            grossList.add(resGross);
        }
        //查询数据
    //    grossList = selectGrossBiList(pkCode,cityCode,selectMonth);

        response.setResult(grossList);
        return this.success(response);
    }

    /**
     * @Description 查询方法
     * @author wangchuang
     * @Date 2019/1/10 15:34
     * @Version 1.0
     * @Param [pkCode, cityCode, selectMonth]
     * @return java.util.List<com.kfwy.park.controller.bi.vo.GrossBiEntity>
     **/
    private List<GrossBiEntity> selectGrossBiList(String pkCode, String cityCode, Date selectMonth) {

        List<GrossBiEntity> grossList = new ArrayList<>();
        //情况一：指定某个园区查询毛利率报表
        if (StringUtils.isNotBlank(pkCode)) {
            //查询pkCode
            ParkEntity parkEntity = parkManager.findOne("pkCode", pkCode);
            GrossBiEntity resGross = selectGrossInfo(parkEntity, selectMonth);

            grossList.add(resGross);

        } else if (StringUtils.isNoneBlank(cityCode)) {// 情况二：指定某个城市查询毛利率报表

            Map<String, Object> param = new HashMap<>();
            param.put("cityCode", cityCode);
            param.put("isDeleted", false);
            param.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkEntity> parkList = parkManager.findByMap(param);

            for (ParkEntity pk : parkList) {
                GrossBiEntity resGross = selectGrossInfo(pk, selectMonth);
                grossList.add(resGross);
            }

        } else {//情况三：查询所有
            //查询所有
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("isDeleted", false);
            param.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkEntity> parkList = parkManager.findByMap(param);

            for (ParkEntity pk : parkList) {
                GrossBiEntity resGross = selectGrossInfo(pk, selectMonth);
                grossList.add(resGross);
            }
        }
        return grossList;
    }

    //查询一个园区毛利率相关信息
    private GrossBiEntity selectGrossInfo(ParkEntity parkEntity, Date selectMonth) {

        GrossBiEntity grossBiEntity = new GrossBiEntity();
        Date monthBegin = DateFormatUtil.monthBegin(selectMonth);
        Date monthEnd = DateFormatUtil.monthEnd(selectMonth);

        //月初
        monthBegin = DateFormatUtil.dayBegin(monthBegin);
        //月底
        monthEnd = DateFormatUtil.dayEnd(monthEnd);

        if (parkEntity != null) {

            grossBiEntity.setPkCode(parkEntity.getPkCode());
            grossBiEntity.setPkName(parkEntity.getPkName());
            //经营开始日
            grossBiEntity.setBusinessStartDay(parkEntity.getBusinessStartDay());
            //经营结束日
            grossBiEntity.setBusinessEndDay(parkEntity.getBusinessEndDay());
            //总承租面积
            grossBiEntity.setAcreage(parkEntity.getAcreage());
            //总计租面积
            grossBiEntity.setInvestArea(parkEntity.getInvestArea());
            //空置计租面积
            grossBiEntity.setRentableArea(parkEntity.getRentableArea());
            //已出租计租面积
            grossBiEntity.setRentInvestArea(parkEntity.getRentInvestArea());

            //去化率(已出租计租面积/总计租面积)
            grossBiEntity.setRate(grossBiEntity.getRentInvestArea().multiply(new BigDecimal(100)).divide(grossBiEntity.getInvestArea(), 2, BigDecimal.ROUND_HALF_UP));

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

            //园区每月租金
            grossBiEntity.setRentIncome(rentTotal);
            //园区每月物业费
            grossBiEntity.setPropertyIncome(propertyTotal);
            //园区每月总租金+园区每月物业费
            BigDecimal price = rentTotal.add(propertyTotal);

            //公摊比
            //园区拓展每月金额(单价*总承租面积*365/12)
            BigDecimal expandPrice = (parkEntity.getAveragePrice()).multiply(grossBiEntity.getAcreage()).multiply(new BigDecimal(365)).divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP);
            grossBiEntity.setRentCost(expandPrice);
            //毛利率 (园区月收入总租金-园区拓展每月金额)/园区月收入总租金
            BigDecimal gross = new BigDecimal(0);
            if (price.compareTo(BigDecimal.ZERO) == 1) {
                gross = (price.subtract(expandPrice)).multiply(new BigDecimal(100)).divide(price, 2, BigDecimal.ROUND_HALF_UP);
            }
            grossBiEntity.setGross(gross);

        }
        return grossBiEntity;
    }

    /**
     * @Description 导出报表操作
     * @author wangchuang
     * @Date 2019/1/9 17:12
     * @Version 1.0
     * @Param [httpServletRequest, httpServletResponse]
     * @return void
     **/
    @RequestMapping(path = "/exportGrossBiExcel",
            method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws FileExportException, IOException {

//        HSSFWorkbook workbook = null;
        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;
        String pkCode = httpServletRequest.getParameter("pkCode");
        String cityCode = httpServletRequest.getParameter("cityCode");
        String selectMonthParam = httpServletRequest.getParameter("selectMonth");
        String territoryType = httpServletRequest.getParameter("territoryType");

        //查询日期校验
        if (selectMonthParam == null) {
            throw new BusinessException("传入查询日期参数为空");
        }

        Map<String,Object> param = new HashMap<>();
        if(StringUtils.isNotBlank(pkCode)){
            param.put("pkCode",pkCode);
        }
        if(StringUtils.isNotBlank(cityCode)){
            param.put("cityCode",cityCode);
        }
        if(StringUtils.isNotBlank(territoryType)){
            param.put("territoryType",territoryType);
        }
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        Long selectMonthStr = new Long(selectMonthParam);
        Date selectMonth = new Date(selectMonthStr);
        Date monthBegin = DateFormatUtil.monthBegin(selectMonth);
        Date monthEnd = DateFormatUtil.monthEnd(selectMonth);

        String path = "/exportTemplate/" + "grossBiReport" + ".xml";

        //导出内容查询参数
        List<ParkEntity> parkList = parkManager.findByMap(param);
        List<GrossEntity> grossEntityArrayList = new ArrayList<>();
        for(ParkEntity p : parkList){
            GrossEntity grossEntity = grossBiManager.getParkMonthGross(p, monthBegin, monthEnd);
            grossBiManager.getParkRate(p,grossEntity); //获取去化率信息
            grossEntityArrayList.add(grossEntity);
        }

        if(grossEntityArrayList.size() <= 0) {
            throw new BusinessException("无数据导出");
        }

        //用于导出文件名显示月份
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String startMonthStr = sdf.format(selectMonth);
        String excelName = startMonthStr + "的毛利率报表.xlsx";
        ExportFileUtil<GrossEntity> exportFileUtil = new ExportFileUtil();
        exportFileUtil.exportExcel(httpServletResponse, GrossBiService.class,path,grossEntityArrayList,excelName);

    }

    /**
     *@Author gengtao
     *@Date 2019-06-27 15:25:47
     *@Description 楼层某月项目毛利率和出租毛利率
     *@Return
     **/
    @RequestMapping(path = "/floorMonthProjectProfit"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> floorMonthProjectProfit(@RequestBody GrossBiServiceRequest request){
        GrossBiServiceResponse response = new GrossBiServiceResponse();
        String flCode = request.getFlCode();
        Date month = request.getSelectMonth();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("flCode", flCode);
        FloorEntity floorEntity = floorManager.findOne(map);

        Date monthBegin = DateFormatUtil.monthBegin(month);
        Date monthEnd = DateFormatUtil.monthEnd(month);

        BigDecimal floorMonthReceivedPrice = grossBiManager.getFloorMonthReceivedPrice(floorEntity, monthBegin, monthEnd); //出租收入金额
        BigDecimal floorMonthTenantPrice = grossBiManager.getFloorMonthTenantPrice(floorEntity);//整个楼层面积的承租金额
        BigDecimal floorMonthInvestPrice = grossBiManager.getFloorMonthInvestPrice(floorEntity,monthBegin, monthEnd); //已出租面积的承租金额
        BigDecimal projectProfit = new BigDecimal(0);
        BigDecimal investProfit = new BigDecimal(0);

        if(floorMonthReceivedPrice.compareTo(BigDecimal.ZERO) != 0){
            //项目毛利率 = (（每层出租面积*每层出租单价  之和）-（总面积*每层承租单价  之和）) / （每层出租面积*每层出租单价  之和）
            projectProfit = (floorMonthReceivedPrice.subtract(floorMonthTenantPrice)).divide(floorMonthReceivedPrice, 2, BigDecimal.ROUND_HALF_UP);
        }
        if(floorMonthReceivedPrice.compareTo(BigDecimal.ZERO) != 0){
            //出租毛利率 = (（每层出租面积*每层出租单价  之和）-（每层出租面积*每层承租单价  之和）) / （每层出租面积*每层出租单价  之和）
            investProfit = (floorMonthReceivedPrice.subtract(floorMonthInvestPrice)).divide(floorMonthReceivedPrice, 2, BigDecimal.ROUND_HALF_UP);
        }

        GrossEntity grossEntity = new GrossEntity();
        grossEntity.setFlCode(flCode);
        grossEntity.setFlName(floorEntity.getThisFloor());
        grossEntity.setProjectGross(projectProfit);
        grossEntity.setInvestGross(investProfit);

        response.setResult(grossEntity);
        return this.success(response);
    }

    /**
     *@Author gengtao
     *@Date 2019-06-27 17:52:29
     *@Description 园区项目毛利率和出租毛利率
     *@Return
     **/
    @RequestMapping(path = "/parkMonthGross"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> parkMonthProjectProfit(@RequestBody GrossBiServiceRequest request) {
        GrossBiServiceResponse response = new GrossBiServiceResponse();

        String pkCode = request.getPkCode();
        String territoryType = request.getTerritoryType();
        String cityCode = request.getCityCode();
        Date selectMonth = request.getSelectMonth();

        //查询日期校验
        if (selectMonth == null) {
            throw new BusinessException("毛利率查询传入查询日期参数为空");
        }

        Date monthBegin = DateFormatUtil.monthBeginDay(selectMonth);
        Date monthEnd = DateFormatUtil.monthEndDay(selectMonth);

        Map<String, Object> parkMap = new HashMap<>();
        parkMap.put("isDeleted", false);
        parkMap.put("cpyCode",CurrentContext.getCpyCode());

        if(StringUtils.isNotBlank(pkCode)){
            parkMap.put("pkCode",pkCode);
        }

        if(StringUtils.isNotBlank(cityCode)){
            parkMap.put("cityCode",cityCode);
        }

        if(StringUtils.isNotBlank(territoryType)){
            parkMap.put("territoryType",territoryType);
        }

        List<ParkEntity> parkEntityList = parkManager.findByMap(parkMap);
        List<GrossEntity> grossEntityArrayList = new ArrayList<>();

        for(ParkEntity p : parkEntityList){
            GrossEntity grossEntity = grossBiManager.getParkMonthGross(p, monthBegin, monthEnd);
            grossBiManager.getParkRate(p,grossEntity); //获取去化率信息
            grossEntityArrayList.add(grossEntity);
        }

        response.setResult(grossEntityArrayList);
        return this.success(response);
    }

}
