package com.kfwy.park.exp.house.building.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.exp.house.building.business.IInvparkManager;
import com.kfwy.park.exp.house.building.dao.IInvparkDbDao;
import com.kfwy.park.exp.house.building.entity.InvparkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;

/**
 * @Description 拓展
 * @Auth luming
 * @Date 2018/11/7 10:33
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class InvparkManagerImpl extends AbstractManager<InvparkEntity> implements IInvparkManager{

    @Autowired
    private IInvparkDbDao invparkDbDao;

    @Override
    protected IMyBatisBaseDao<InvparkEntity, Long> getMyBatisDao() {
        return invparkDbDao;
    }

    @Override
    protected void beforeCreate(InvparkEntity invparkEntity) {

        //总建筑面积
        BigDecimal parkBuildingArea = invparkEntity.getSingleFloorArea().add(invparkEntity.getMoreFloorArea())
                .add(invparkEntity.getOfficeBuildingArea()).add(invparkEntity.getOtherBuildingArea());
        //记录总建筑面积
        invparkEntity.setParkBuildingArea(parkBuildingArea);

        //附房占比
        BigDecimal otherBuildRate = invparkEntity.getOtherBuildingArea().divide(invparkEntity.getParkBuildingArea(), 4,BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(100));
        //记录附房占比
        invparkEntity.setOtherBuildingRate(otherBuildRate);

        //计算评分
        calParkScore(invparkEntity);

    }

    @Override
    protected void afterCreate(InvparkEntity invparkEntity) {
    }

    /**
     * @Description 计算评分
     * @author wangchuang
     * @Date 2018/11/7 15:02
     * @Version 1.0
     * @Param [invparkEntity]
     * @return void
     **/
    private void calParkScore(InvparkEntity invparkEntity) {

        //总分
        double score = 0;

        /*********************************************************/
        /**
         * 交通指数  总分：40分
         */

        //园区与主城区的距离
        double distDowntown = invparkEntity.getDistanceDowntown().doubleValue();
        if (distDowntown >=0 && distDowntown<= 5) {
            score = score + 12;
        } else if(distDowntown > 5 && distDowntown <= 10) {
            score = score + 7;
        } else {
            score = score + 3;
        }

        //园区是否处于工业集中区
        String manufacturingDistrict = invparkEntity.getManufacturingDistrict();
        if ("1".equals(manufacturingDistrict)) {
            score = score + 10;
        } else if("2".equals(manufacturingDistrict)) {
            score = score + 6;
        } else if("3".equals(manufacturingDistrict)) {
            score = score + 3;
        }

        //园区与高速入口的距离
        double distFreeEntrance = invparkEntity.getDistanceFreewayEntrance().doubleValue();
        if (distFreeEntrance >= 0 && distFreeEntrance<= 5) {
            score = score + 6;
        } else if(distFreeEntrance > 5 && distFreeEntrance <= 10) {
            score = score + 3;
        } else {
            score = score + 1;
        }

        //园区前面道路双向车道数
        Integer laneQuantity = invparkEntity.getLaneQuantity();
        if (laneQuantity >= 4) {
            score = score + 5;
        } else {
            score = score + 3;
        }

        //园区与主干道的距离
        double distMainStreet = invparkEntity.getDistanceMainStreet().doubleValue();
        if (distMainStreet >= 0 && distMainStreet <= 200) {
            score = score + 4;
        } else if(distMainStreet > 200 && distMainStreet <= 500) {
            score = score + 3;
        } else {
            score = score + 2;
        }

        //1.5公里内是否有公共交通

        if (invparkEntity.getHasPublicTraffic()) {
            score = score + 3;
        } else {
            score = score + 1;
        }


        /*********************************************************/
        /**
         * 厂房基本情况  总分：37分
         */

        //是否是新厂房
        if (invparkEntity.getHasNewPlant()) {
            score = score + 2;
        }

        //厂房装修情况
        String plantDecoration = invparkEntity.getPlantDecoration();
        if ("1".equals(plantDecoration)) {
            score = score + 4;
        } else if("2".equals(plantDecoration)) {
            score = score + 3;
        } else if("3".equals(plantDecoration)) {
            score = score + 2;
        }

        //送电情况
        String plantElectricity = invparkEntity.getPlantElectricity();
        if ("1".equals(plantElectricity)) {
            score = score + 6;
        } else if("2".equals(plantElectricity)) {
            score = score + 4;
        } else if("3".equals(plantElectricity)) {
            score = score + 1;
        }


        //园区内厂房结构及数量
        Integer singleFloorQuantity = invparkEntity.getSingleFloorQuantity();
        Integer moreFloorQuantity = invparkEntity.getMoreFloorQuantity();
        double floorHeight = 0;
        if (singleFloorQuantity > 0 && moreFloorQuantity == 0) {
            //只有单层
            score = score + 2;
            floorHeight = invparkEntity.getSingleFloorHeight().doubleValue();
            //园区内单栋高层厂房电梯数量（园区只有单层厂房，该项直接满分）
            score = score + 2;
        } else if(singleFloorQuantity == 0 && moreFloorQuantity >0) {
            //只有高层
            score = score + 1;
            floorHeight = invparkEntity.getMoreFloorHeight().doubleValue();
            //园区内单栋高层厂房电梯数量（园区只有单层厂房，该项直接满分）
            if (invparkEntity.getMoreFloorElevatorQuantity() >= 2) {
                score = score + 2;
            } if (invparkEntity.getMoreFloorElevatorQuantity() == 1) {
                score = score + 1;
            }
        } else {
            score = score + 1.5;
            if (invparkEntity.getSingleFloorHeight().doubleValue() > invparkEntity.getMoreFloorHeight().doubleValue()) {
                floorHeight = invparkEntity.getSingleFloorHeight().doubleValue();
            } else {
                floorHeight = invparkEntity.getMoreFloorHeight().doubleValue();
            }

            //园区内单栋高层厂房电梯数量（园区只有单层厂房，该项直接满分）
            if (invparkEntity.getMoreFloorElevatorQuantity() >= 2) {
                score = score + 2;
            } if (invparkEntity.getMoreFloorElevatorQuantity() == 1) {
                score = score + 1;
            }
        }

        //层高
        if (floorHeight > 7) {
            score = score + 3;
        } else if(floorHeight >= 4 && floorHeight <= 7) {
            score = score + 2;
        } else if (floorHeight > 0 && floorHeight < 4) {
            score = score + 1;
        }

        //电梯位置
        String elevatorLocation = invparkEntity.getElevatorLocation();
        if ("中间位置双开".equals(elevatorLocation)) {
            score = score + 3;
        } else if("两头位置双开".equals(elevatorLocation)) {
            score = score + 2;
        } else if("其他情况非双开".equals(elevatorLocation)) {
            score = score + 1;
        }

        //厂房与附房占比

        double otherBuildRate = invparkEntity.getOtherBuildingRate().doubleValue();
        if (otherBuildRate > 0 && otherBuildRate < 10) {
            score = score + 5;
        } else if(otherBuildRate > 10 && otherBuildRate <= 20) {
            score = score + 3;
        } else if(otherBuildRate > 20 && otherBuildRate < 25) {
            score = score + 1;
        }

        //园区配电大小
        //每1000平配电多少

        double eleSize = invparkEntity.getTotalElectricity().divide(invparkEntity.getParkBuildingArea(),4, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(1000)).doubleValue();
        if (eleSize > 30) {
            score = score + 5;
        } else if(eleSize == 30) {
            score = score + 3;
        } else if(eleSize > 0 && eleSize < 30) {
            score = score + 1;
        }

        //园区消防等级
        String firefightingLevel = invparkEntity.getFirefightingLevel();
        if ("1".equals(firefightingLevel) || "2".equals(firefightingLevel) || "3".equals(firefightingLevel)) {
            score = score + 5;
        } else if("4".equals(firefightingLevel)) {
            score = score + 3;
        } else if("5".equals(firefightingLevel)) {
            score = score + 1;
        }


        /*********************************************************/
        /**
         * 商务条款
         */

        //租金与周边市场价进行对比
        if(invparkEntity.getUnitPrice().compareTo(invparkEntity.getNearbyPrice()) == -1) {
            score = score + 5;
        } else if(invparkEntity.getUnitPrice().compareTo(invparkEntity.getNearbyPrice()) == 0) {
            score = score + 3;
        } else if(invparkEntity.getUnitPrice().compareTo(invparkEntity.getNearbyPrice()) == 1) {
            score = score + 1;
        }

        //房东给予的免租期期限
        double freeRentDate = invparkEntity.getFreeRentDate().doubleValue();
        if(freeRentDate > 2) {
            score = score + 4;
        } else if(freeRentDate > 1 && freeRentDate <= 2) {
            score = score + 2;
        } else if(freeRentDate >= 0.5 && freeRentDate <= 1) {
            score = score + 1;
        }

        //租金递增方式
        String increaseRule = invparkEntity.getIncreaseRule();
        if(!StringUtils.isEmpty(increaseRule)) {
            if("1".equals(increaseRule)) {
                score = score + 2;
            } else if("2".equals(increaseRule)) {
                score = score + 1.5;
            } else if("3".equals(increaseRule)) {
                score = score + 1;
            }
        }

        //租金及押金的付款方式
        String payRule = invparkEntity.getPayRule();
        if(!StringUtils.isEmpty(payRule)) {
            if("1".equals(payRule)) {
                score = score + 2;
            } else if("2".equals(payRule)) {
                score = score + 1.5;
            } else if("3".equals(payRule)) {
                score = score + 1;
            }
        }

        /*********************************************************/
        /**
         * 政府导向及业主背景
         */

        //政府对税收的要求
        String taxRequest = invparkEntity.getTaxRequest();
        if("1".equals(taxRequest)) {
            score = score + 10;
        } else if("2".equals(taxRequest)) {
            score = score + 6;
        } else if("3".equals(taxRequest)) {
            score = score + 3;
        }

        String res = "";
        if(score >= 80) {
            res = "优质";
        } else if(score >= 70 && score < 80) {
            res = "良好";
        } else if(score >= 60 && score < 70) {
            res = "合格";
        } else {
            res = "不合格";
        }

        invparkEntity.setScore(score);
        invparkEntity.setAssessResult(res);

    }


}
