package com.kfwy.park.bi.report.entity;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.context.RequestContext;
import com.kfwy.park.bi.report.business.IGrossBiManager;
import com.kfwy.park.bi.report.entity.CompanySituationEntity;
import com.kfwy.park.bi.report.entity.GrossEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Author gengtao
 * @Date 2019/8/1 9:27
 **/
public class GrossThreadEntity implements Runnable {

    private RequestContext requestContext;
    private IGrossBiManager grossBiManager;
    private CompanySituationEntity situationEntity;
    private Date monthBegin;
    private Date monthEnd;

    public GrossThreadEntity(RequestContext requestContext, IGrossBiManager grossBiManager, CompanySituationEntity situationEntity, Date monthBegin, Date monthEnd) {
        this.requestContext = requestContext;
        this.grossBiManager = grossBiManager;
        this.situationEntity = situationEntity;
        this.monthBegin = monthBegin;
        this.monthEnd = monthEnd;
    }

    @Override
    public void run() {
        //获取父线程上下文
        CurrentContext.setRequestContext(requestContext);

        getGross(situationEntity, monthBegin, monthEnd);
    }

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

    public CompanySituationEntity getSituationEntity() {
        return situationEntity;
    }

    public void setSituationEntity(CompanySituationEntity situationEntity) {
        this.situationEntity = situationEntity;
    }

    public Date getMonthBegin() {
        return monthBegin;
    }

    public void setMonthBegin(Date monthBegin) {
        this.monthBegin = monthBegin;
    }

    public Date getMonthEnd() {
        return monthEnd;
    }

    public void setMonthEnd(Date monthEnd) {
        this.monthEnd = monthEnd;
    }
}