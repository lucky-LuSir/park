package com.kfwy.park.bi.report.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.ati.contract.business.IContractHouseManager;
import com.kfwy.park.ati.contract.business.IRentIncrementManager;
import com.kfwy.park.ati.contract.business.IRentReceiptManager;
import com.kfwy.park.ati.contract.dao.IRentIncrementDbDao;
import com.kfwy.park.ati.contract.entity.ContractHouseEntity;
import com.kfwy.park.ati.contract.entity.RentIncrementEntity;
import com.kfwy.park.bi.report.business.IGrossBiManager;
import com.kfwy.park.bi.report.entity.GrossEntity;
import com.kfwy.park.pro.house.building.business.IFloorManager;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by gengtao on 2019/6/27.
 */
@Service
public class GrossBiManagerImpl extends AbstractManager<RentIncrementEntity> implements IGrossBiManager{
    @Autowired
    private IRentIncrementDbDao rentIncrementDbDao;
    @Autowired
    private IParkManager parkManager;
    @Autowired
    private IRentReceiptManager rentReceiptManager;
    @Autowired
    private IFloorManager floorManager;
    @Autowired
    private IContractHouseManager contractHouseManager;
    @Autowired
    private IRentIncrementManager rentIncrementManager;
    @Override
    protected IMyBatisBaseDao<RentIncrementEntity, Long> getMyBatisDao() {
        return rentIncrementDbDao;
    }

    //获取每月 整个楼层面积的承租金额
    @Override
    public BigDecimal getFloorMonthTenantPrice(FloorEntity floorEntity) {
        if(StringUtils.isEmpty(floorEntity.getTenantPrice()) || StringUtils.isEmpty(floorEntity.getTenantUnit())){
            throw new BusinessException("请完善 【"+floorEntity.getPkName()+" "+floorEntity.getBdName()+"栋 "+floorEntity.getThisFloor()+"层】 的层承租单价");
        }
        BigDecimal investArea = floorEntity.getInvestArea(); //计租面积
        //月承租单价
        BigDecimal monthUnitPrice = monthUnitPrice(floorEntity.getTenantPrice(), floorEntity.getTenantUnit());
        BigDecimal monthTenantPrice = monthUnitPrice.multiply(investArea);
        return monthTenantPrice;
    }

    //获取每月 已出租面积所支出的承租金额
    @Override
    public BigDecimal getFloorMonthInvestPrice(FloorEntity floorEntity, Date startTime, Date endTime){
        Map<String, Object> map = new HashMap<>();
        //月承租单价
        BigDecimal monthUnitPrice = monthUnitPrice(floorEntity.getTenantPrice(), floorEntity.getTenantUnit());

        map.put("flCode",floorEntity.getFlCode());
        map.put("ctStatus","4");
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted",false);
        map.put("startTime",startTime);
        map.put("endTime",endTime);
        List<ContractHouseEntity> houseEntities = contractHouseManager.selectByFlCode(map);

        BigDecimal price = new BigDecimal(0);
        for(ContractHouseEntity hh : houseEntities){
            BigDecimal cPrice = hh.getInvestArea().multiply(monthUnitPrice);
            price = price.add(cPrice);
        }
        return price;
    }

    //获取一层楼在某月的盈利金额
    @Override
    public BigDecimal getFloorMonthReceivedPrice(FloorEntity floorEntity, Date startTime, Date endTime){
        BigDecimal floorMonthReceivedPrice = new BigDecimal(0); //该楼层该月获得的盈利
        Map<String, Object> map = new HashMap<>();
        map.put("flCode",floorEntity.getFlCode());
        map.put("ctStatus","4");
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted",false);
        List<ContractHouseEntity> houseEntities = contractHouseManager.selectByFlCode(map);
        for(ContractHouseEntity hh : houseEntities){
            Map<String, Object> inMap = new HashMap<>();
            inMap.put("isDeleted", false);
            inMap.put("ctCode", hh.getCtCode());
            inMap.put("cpyCode", CurrentContext.getCpyCode());
            BigDecimal unitPrice = hh.getUnitPrice(); //初始单价
            BigDecimal monthUnitPrice = new BigDecimal(0); //本月单价

            List<RentIncrementEntity> incrementEntities = rentIncrementManager.findByMap(inMap,"inc_start_time", true);
            boolean flag = true;
            for(RentIncrementEntity in : incrementEntities){
                monthUnitPrice = unitPrice.multiply(new BigDecimal(100).add(in.getRentIncreaseProportion())).divide(new BigDecimal(100));
                if(startTime.compareTo(in.getIncStartTime()) <= 0 && endTime.compareTo(in.getIncStartTime()) >= 0 ){
                    flag = false;
                    break;
                }else if(startTime.compareTo(in.getIncStartTime()) >= 0 && startTime.compareTo(in.getIncEndTime()) <= 0){
                    flag = false;
                    break;
                }
            }
            if(flag){
                continue;
            }
            if(hh.getUnit().equals("元/㎡/天")){
                monthUnitPrice = monthUnitPrice.multiply(new BigDecimal(365).divide(new BigDecimal(12),4, BigDecimal.ROUND_HALF_UP));
            }else if(hh.getUnit().equals("元/㎡/年")){
                monthUnitPrice = monthUnitPrice.divide(new BigDecimal(12), 4, BigDecimal.ROUND_HALF_UP);
            }
            BigDecimal contractMonthReceivedPrice = monthUnitPrice.multiply(hh.getInvestArea()); //该合同中该楼层获得的盈利

            floorMonthReceivedPrice = floorMonthReceivedPrice.add(contractMonthReceivedPrice);
        }
        return floorMonthReceivedPrice;
    }

    /**
     *@Author gengtao
     *@Date 2019-06-27 15:09:04
     *@Description 根据单价和单位计算每月的单价
     *@param tenantPrice 单价
     *@param unit 单位
     *@Return  每月单价
     **/
    @Override
    public BigDecimal monthUnitPrice(BigDecimal tenantPrice, String unit){
        BigDecimal monthUnitPrice = new BigDecimal(0);
        switch (unit) {
            case "1":
                monthUnitPrice = new BigDecimal(365).divide(new BigDecimal(12), 4, BigDecimal.ROUND_HALF_UP).multiply(tenantPrice);
                break;
            case "2":
                monthUnitPrice = tenantPrice;
                break;
            case "3":
                monthUnitPrice = tenantPrice.divide(new BigDecimal(12), 4, BigDecimal.ROUND_HALF_UP);
                break;
        }
        return monthUnitPrice;
    }

    /**
     *@Author gengtao
     *@Date 2019-06-27 19:27:55
     *@Description 获取园区去化率
     *@Return
     **/
    @Override
    public void getParkRate(ParkEntity parkEntity ,GrossEntity grossEntity){

        grossEntity.setPkCode(parkEntity.getPkCode());
        grossEntity.setPkName(parkEntity.getPkName());
        //经营开始日
        grossEntity.setBusinessStartDay(parkEntity.getBusinessStartDay());
        //经营结束日
        grossEntity.setBusinessEndDay(parkEntity.getBusinessEndDay());
        //总承租面积
        grossEntity.setAcreage(parkEntity.getAcreage());
        //总计租面积
        grossEntity.setInvestArea(parkEntity.getInvestArea());
        //空置计租面积
        grossEntity.setRentableArea(parkEntity.getRentableArea());
        //已出租计租面积
        grossEntity.setRentInvestArea(parkEntity.getRentInvestArea());

        //去化率(已出租计租面积/总计租面积)
        grossEntity.setRate(grossEntity.getRentInvestArea().multiply(new BigDecimal(100)).divide(grossEntity.getInvestArea(), 2, BigDecimal.ROUND_HALF_UP));
    }

    @Override
    public GrossEntity getFloorMonGross(FloorEntity floorEntity, Date monthBegin, Date monthEnd) {
        //获取每月 整个楼层面积的承租金额
        BigDecimal floorMonthTenantPrice = getFloorMonthTenantPrice(floorEntity);

        //获取一层楼在某月的盈利金额
        BigDecimal floorMonthReceivedPrice = getFloorMonthReceivedPrice(floorEntity, monthBegin, monthEnd);

        //获取每月 出租面积的承租金额
        BigDecimal floorMonthInvestPrice = getFloorMonthInvestPrice(floorEntity, monthBegin, monthEnd);

        /*BigDecimal projectProfit = new BigDecimal(0);
        //项目毛利率
        if(floorMonthTenantPrice.compareTo(BigDecimal.ZERO) != 0){
            projectProfit = (floorMonthReceivedPrice.subtract(floorMonthTenantPrice)).divide(floorMonthTenantPrice, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        }
        BigDecimal investProfit = new BigDecimal(0);
        //出租毛利率
        if(floorMonthInvestPrice.compareTo(BigDecimal.ZERO) != 0){
            investProfit = (floorMonthReceivedPrice.subtract(floorMonthInvestPrice)).divide(floorMonthInvestPrice, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        }*/
        Map<String, Object> grossRes = this.computeCross(floorMonthReceivedPrice, floorMonthTenantPrice, floorMonthInvestPrice);
        BigDecimal projectProfit = (BigDecimal) grossRes.get("projectGross");
        BigDecimal investProfit = (BigDecimal) grossRes.get("investGross");

        GrossEntity grossEntity = new GrossEntity();
        grossEntity.setFlCode(floorEntity.getFlCode());
        grossEntity.setFlName(floorEntity.getThisFloor());
        grossEntity.setProjectGross(projectProfit);
        grossEntity.setProjectPrice(floorMonthTenantPrice);
        grossEntity.setInvestGross(investProfit);
        grossEntity.setInvestPrice(floorMonthInvestPrice);
        grossEntity.setRentIncome(floorMonthReceivedPrice);
        return grossEntity;
    }


    /**
     *@Author gengtao
     *@Date 2019-06-28 09:45:08
     *@Description 获取园区的毛利率
     *@Return
     **/
    @Override
    public GrossEntity getParkMonthGross(ParkEntity parkEntity, Date monthBegin, Date monthEnd){
        //获取楼层的 全面承租金额 已出租承租金额 出租收入金额
        GrossEntity grossEntity = getFloorPrice(parkEntity, monthBegin, monthEnd);
        BigDecimal parkMonthTenantPrice = grossEntity.getProjectPrice();
        BigDecimal parkMonthReceivedPrice = grossEntity.getRentIncome();
        BigDecimal parkMonthInvestPrice = grossEntity.getInvestPrice();

        Map<String, Object> grossRes = this.computeCross(parkMonthReceivedPrice, parkMonthTenantPrice, parkMonthInvestPrice);
        BigDecimal projectGross = (BigDecimal) grossRes.get("projectGross");
        BigDecimal investGross = (BigDecimal) grossRes.get("investGross");

        grossEntity.setProjectGross(projectGross);
        grossEntity.setInvestGross(investGross);
        return grossEntity;
    }

    /**
     *@Author gengtao
     *@Date 2019-06-28 17:17:55
     *@Description 获取大区的毛利率
     *@Return
     **/
    @Override
    public GrossEntity getTerritoryMonthGross(String territoryType, Date monthBegin, Date monthEnd) {
        Map<String, Object> parkMap = new HashMap<>();
        parkMap.put("territoryType", territoryType);
        parkMap.put("isDeleted", false);
        parkMap.put("cpyCode", CurrentContext.getCpyCode());
        List<ParkEntity> parkEntities = parkManager.findByMap(parkMap);
        String territoryTypeName = parkEntities.get(0).getTerritoryTypeName();

        //大区承租金额
        BigDecimal territoryMonthTenantPrice = new BigDecimal(0);
        //大区出租金额
        BigDecimal territoryMonthInvestPrice = new BigDecimal(0);
        //大区盈利金额
        BigDecimal territoryMonthReceivedPrice = new BigDecimal(0);

        for(ParkEntity p : parkEntities){
            //获取楼层的 全面承租金额 已出租承租金额 出租收入金额
            GrossEntity grossEntity = getFloorPrice(p, monthBegin, monthEnd);
            //园区承租金额
            BigDecimal parkMonthTenantPrice = grossEntity.getProjectPrice();
            //园区出租金额
            BigDecimal parkMonthInvestPrice = grossEntity.getInvestPrice();
            //园区盈利金额
            BigDecimal parkMonthReceivedPrice = grossEntity.getRentIncome();

            territoryMonthTenantPrice = territoryMonthTenantPrice.add(parkMonthTenantPrice);
            territoryMonthInvestPrice = territoryMonthInvestPrice.add(parkMonthInvestPrice);
            territoryMonthReceivedPrice = territoryMonthReceivedPrice.add(parkMonthReceivedPrice);
        }
        Map<String, Object> grossRes = this.computeCross(territoryMonthReceivedPrice, territoryMonthTenantPrice, territoryMonthInvestPrice);
        BigDecimal projectGross = (BigDecimal) grossRes.get("projectGross");
        BigDecimal investGross = (BigDecimal) grossRes.get("investGross");

        GrossEntity grossEntity = new GrossEntity();
        grossEntity.setTerritoryType(territoryType);
        grossEntity.setTerritoryTypeName(territoryTypeName);
        grossEntity.setInvestPrice(territoryMonthInvestPrice);
        grossEntity.setProjectPrice(territoryMonthTenantPrice);
        grossEntity.setRentIncome(territoryMonthReceivedPrice);
        grossEntity.setProjectGross(projectGross);
        grossEntity.setInvestGross(investGross);
        return grossEntity;
    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 11:59:25
     *@Description 毛利率计算公式
     *@param
     *@return
     **/
    public Map<String, Object> computeCross(BigDecimal incomePrice, BigDecimal projectPrice, BigDecimal investPrice){
        BigDecimal projectGross = new BigDecimal(0);
        BigDecimal investGross = new BigDecimal(0);
        if(incomePrice.compareTo(BigDecimal.ZERO) != 0){
            projectGross = (incomePrice.subtract(projectPrice)).divide(incomePrice, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        }
        if(incomePrice.compareTo(BigDecimal.ZERO) != 0){
            investGross = (incomePrice.subtract(investPrice)).divide(incomePrice, 4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        }
        Map<String, Object> result = new HashMap<>();
        result.put("projectGross",projectGross);
        result.put("investGross",investGross);
        return result;
    }

    /**
     *@Author gengtao
     *@Date 2019-06-28 17:03:55
     *@Description 获取楼层的 全面承租金额 已出租承租金额 出租收入金额
     *@Return
     **/
    @Override
    public GrossEntity getFloorPrice(ParkEntity parkEntity, Date monthBegin, Date monthEnd) {
        Map<String, Object> floorMap = new HashMap<>();
        floorMap.put("isDeleted", false);
        floorMap.put("cpyCode", CurrentContext.getCpyCode());
        floorMap.put("pkCode",parkEntity.getPkCode());
        List<FloorEntity> floorEntities = floorManager.findByMap(floorMap);

        //园区承租金额
        BigDecimal parkMonthTenantPrice = new BigDecimal(0);
        //园区出租金额
        BigDecimal parkMonthInvestPrice = new BigDecimal(0);
        //园区盈利金额
        BigDecimal parkMonthReceivedPrice = new BigDecimal(0);

        for(FloorEntity f : floorEntities){
            //获取每月 整个楼层面积的承租金额
            BigDecimal floorMonthTenantPrice = getFloorMonthTenantPrice(f);

            parkMonthTenantPrice = parkMonthTenantPrice.add(floorMonthTenantPrice);

            //获取一层楼在某月的盈利金额
            BigDecimal floorMonthReceivedPrice = getFloorMonthReceivedPrice(f, monthBegin, monthEnd);
            parkMonthReceivedPrice = parkMonthReceivedPrice.add(floorMonthReceivedPrice);

            //获取每月 出租面积的承租金额
            BigDecimal floorMonthInvestPrice = getFloorMonthInvestPrice(f, monthBegin, monthEnd);
            parkMonthInvestPrice = parkMonthInvestPrice.add(floorMonthInvestPrice);
        }

        GrossEntity grossEntity = new GrossEntity();
        grossEntity.setInvestPrice(parkMonthInvestPrice);
        grossEntity.setProjectPrice(parkMonthTenantPrice);
        grossEntity.setRentIncome(parkMonthReceivedPrice);
        grossEntity.setPkCode(parkEntity.getPkCode());
        grossEntity.setPkName(parkEntity.getPkName());
        grossEntity.setTerritoryType(parkEntity.getTerritoryType());
        grossEntity.setTerritoryTypeName(parkEntity.getTerritoryTypeName());

        return  grossEntity;
    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 11:29:23
     *@Description  获取毛利率信息
     *@param parkEntity 园区实体类
     *@param monthBegin 月初
     *@param monthEnd 月末
     *@return
     **/
    public List<GrossEntity> getGrossInfo(ParkEntity parkEntity, Date monthBegin, Date monthEnd){
        List<GrossEntity> result = new ArrayList<>();
        String cityCode = parkEntity.getCityCode();
        String territoryType = parkEntity.getTerritoryType();
        String pkCode = parkEntity.getPkCode();
        //情况一：获取所有大区的信息
        if (StringUtils.isEmpty(cityCode) && StringUtils.isEmpty(territoryType) && StringUtils.isEmpty(pkCode)) {
            Map<String, Object> param = new HashMap<>();
            param.put("isDeleted", false);
            param.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkEntity> territoryList = parkManager.selectAllTerritory(param); //获取 所有大区
            territoryList.removeAll(Collections.singleton(null));  //去除所有null

            for (ParkEntity territory : territoryList) {
                GrossEntity grossEntity = this.getTerritoryMonthGross(territory.getTerritoryType(), monthBegin, monthEnd);
                result.add(grossEntity);
            }
        }else{
            Map<String,Object> map = new HashMap<>();

            if(org.apache.commons.lang3.StringUtils.isNotBlank(parkEntity.getCityCode())){
                map.put("cityCode",parkEntity.getCityCode());
            }
            if(org.apache.commons.lang3.StringUtils.isNotBlank(parkEntity.getTerritoryType())){
                map.put("territoryType",parkEntity.getTerritoryType());
            }
            if(org.apache.commons.lang3.StringUtils.isNotBlank(parkEntity.getPkCode())){
                map.put("pkCode",parkEntity.getPkCode());
            }
            map.put("isDeleted",false);
            map.put("cpyCode", CurrentContext.getCpyCode());
            List<ParkEntity> parkList = parkManager.findByMap(map);
            for(ParkEntity p : parkList){
                GrossEntity grossEntity = this.getParkMonthGross(p, monthBegin, monthEnd);
                result.add(grossEntity);
            }
        }
        return result;
    }
}
