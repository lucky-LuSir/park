package com.kfwy.park.bi.report.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.ati.contract.entity.RentIncrementEntity;
import com.kfwy.park.bi.report.entity.GrossEntity;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by gengtao on 2019/6/27.
 */
public interface IGrossBiManager extends IManager<RentIncrementEntity>{
    //获取每月 整个楼层面积的承租金额
    BigDecimal getFloorMonthTenantPrice(FloorEntity floorEntity);

    //获取每月 已出租面积所支出的承租金额
    BigDecimal getFloorMonthInvestPrice(FloorEntity floorEntity, Date startTime, Date endTime);

    //获取一层楼在某月的盈利金额
    BigDecimal getFloorMonthReceivedPrice(FloorEntity floorEntity, Date startTime, Date endTime);

    /**
     *@Author gengtao
     *@Date 2019-06-27 15:09:04
     *@Description 根据单价和单位计算每月的单价
     *@param tenantPrice 单价
     *@param unit 单位
     *@Return  每月单价
     **/
    BigDecimal monthUnitPrice(BigDecimal tenantPrice, String unit);

    /**
     *@Author gengtao
     *@Date 2019-06-27 19:21:51
     *@Description 获取园区的去化率
     *@Return
     **/
    void getParkRate(ParkEntity parkEntity, GrossEntity grossEntity);

    /**
     *@Author gengtao
     *@Date 2019-06-28 11:44:52
     *@Description 获取楼层的毛利率
     *@Return
     **/
    GrossEntity getFloorMonGross(FloorEntity floorEntity, Date monthBegin, Date monthEnd);

    /**
     *@Author gengtao
     *@Date 2019-06-28 09:44:36
     *@Description 获取园区的毛利率
     *@Return
     **/
    GrossEntity getParkMonthGross(ParkEntity parkEntity, Date monthBegin, Date monthEnd);

    /**
     *@Author gengtao
     *@Date 2019-06-28 16:41:19
     *@Description 获取大区的毛利率
     *@Return
     **/
    GrossEntity getTerritoryMonthGross(String territoryType, Date monthBegin, Date monthEnd);

    /**
     *@author gtaotao
     *@Date 2019-07-31 11:59:25
     *@Description 毛利率计算公式
     *@param
     *@return
     **/
    Map<String, Object> computeCross(BigDecimal incomePrice, BigDecimal projectPrice, BigDecimal investPrice);

    /**
     *@Author gengtao
     *@Date 2019-06-28 16:41:54
     *@Description 获取楼层的 全面承租金额 已出租承租金额 出租收入金额
     *@Return
     **/
    GrossEntity getFloorPrice(ParkEntity parkEntity, Date monthBegin, Date monthEnd);

    /**
     *@author gtaotao
     *@Date 2019-07-31 11:29:23
     *@Description  获取毛利率信息
     *@param parkEntity 园区实体类
     *@param monthBegin 月初
     *@param monthEnd 月末
     *@return
     **/
    List<GrossEntity> getGrossInfo(ParkEntity parkEntity, Date monthBegin, Date monthEnd);
}
