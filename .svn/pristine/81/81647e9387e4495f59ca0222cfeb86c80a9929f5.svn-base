package com.kfwy.park.pro.house.building.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.common.dic.FirefightingLevel;
import com.kfwy.park.pro.house.building.enums.BuildingStatus;
import com.kfwy.park.pro.house.building.enums.BuildingType;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by luming on 2018/5/29.
 */
public class BuildingEntity extends BaseEntity {
    /**
     * 园区编码
     */
    private String pkCode;

    /**
     * 园区名称
     */
    private String pkName;

    /**
     * 建筑编码
     */
    private String bdCode;

    /*----------------------------建筑基本信息------------------------*/

    /**
     * 当前楼栋
     */
    private String thisBuilding;

    /**
     * 建筑名称
     */
    private String bdName;

    /**
     * 建筑状态(字典)
     * @see BuildingStatus
     */
    private String buildingStatus;

    /**
     * 建筑状态名称(字典)
     */
    private String buildingStatusName;

    /**
     * 楼层总数
     */
    private String floorsTotal;

    /**
     * 计租面积
     */
    private BigDecimal investArea;

    /**
     * 建筑结构（字典）
     * @see BuildingType
     */
    private String buildingStructure;

    /**
     * 建筑结构名称（字典）
     */
    private String buildingStructureName;

    /**
     * 剩余承租面积
     */
    private BigDecimal rentableArea;

    /**
     * 电梯数量
     */
    private String elevatorQuantity;

    /**
     * 货梯承重
     */
    private BigDecimal freightElevatorWeight;

    /**
     * 货梯开门(单向,双向)
     */
    private String openDoor;

    /**
     * 消防等级(字典)
     * @see com.kfwy.park.common.dic.FirefightingLevel
     */
    private String firefightingLevel;

    /**
     * 消防等级名称(字典)
     */
    private String firefightingLevelName;

    /*------------------------------图片----------------------------*/

    /**
     * 楼栋视频
     */
    private String buildingVideo;

    /**
     * 外立面
     */
    private String outPicture;

    /*------------------------------ 一对多关联 ----------------------------*/

    /**
     * 关联楼层信息
     */
    private List<FloorEntity> floorList;


    /*------------------------------ 空置字段 ----------------------------*/
    /**
     * 是否有货梯 -
     */
    private Boolean isFreightElevator;
    /**
     * 货梯空间 -
     */
    private BigDecimal freightElevatorArea;
    /**
     * 电梯规格 -
     */
    private String elevatorSpecification;
    /*-------------------------园区地址-----------------------*/
    /**
     *房源省市区街道编码 -
     */
    private String provinceCode;
    private String cityCode;
    private String regionCode;
    private String streetCode;
    /**
     *房源省市区街道名称 -
     */
    private String provinceName;
    private String cityName;
    private String regionName;
    private String streetName;
    /**
     * 详细地址 -
     */
    private String address;
    /**
     * 经度坐标 -
     */
    private String longitudeCoordinates;
    /**
     * 纬度坐标 -
     */
    private String latitudeCoordinates;
    /*----------------------------配套信息------------------------------*/
    /**
     * 是否有卫生间 -
     */
    private Boolean isBathroom;
    /**
     * 是否有办公区 -
     */
    private Boolean isOffice;
    /**
     * 是否可环评 -
     */
    private Boolean hasEia;
    /**
     * 是否有产证 -
     */
    private Boolean isDeed;
    /**
     * 是否可注册 -
     */
    private Boolean hasRegister;
    /**
     * 有无排污证 -
     */
    private Boolean hasPollutionTreatment;
    /*------------------------------特点----------------------------*/
    /**
     * 建筑特色 -
     */
    private String buildingStyle;
    /**
     * 鸟瞰图 -
     */
    private String aerialView;
    /**
     * 道路图 -
     */
    private String roadMap;
    /**
     * 内部图 -
     */
    private String internalDiagram;


    /*=================================字段分割线===============================*/

    public String getPkCode() {
        return pkCode;
    }

    public void setPkCode(String pkCode) {
        this.pkCode = pkCode;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public String getBdCode() {
        return bdCode;
    }

    public void setBdCode(String bdCode) {
        this.bdCode = bdCode;
    }

    public String getThisBuilding() {
        return thisBuilding;
    }

    public void setThisBuilding(String thisBuilding) {
        this.thisBuilding = thisBuilding;
    }

    public String getBdName() {
        return bdName;
    }

    public void setBdName(String bdName) {
        this.bdName = bdName;
    }

    public String getFloorsTotal() {
        return floorsTotal;
    }

    public void setFloorsTotal(String floorsTotal) {
        this.floorsTotal = floorsTotal;
    }

    public BigDecimal getInvestArea() {
        return investArea;
    }

    public void setInvestArea(BigDecimal investArea) {
        this.investArea = investArea;
    }

    public BigDecimal getRentableArea() {
        return rentableArea;
    }

    public void setRentableArea(BigDecimal rentableArea) {
        this.rentableArea = rentableArea;
    }

    public String getElevatorQuantity() {
        return elevatorQuantity;
    }

    public void setElevatorQuantity(String elevatorQuantity) {
        this.elevatorQuantity = elevatorQuantity;
    }

    public Boolean getIsFreightElevator() {
        return isFreightElevator;
    }

    public void setIsFreightElevator(Boolean isFreightElevator) {
        this.isFreightElevator = isFreightElevator;
    }

    public BigDecimal getFreightElevatorArea() {
        return freightElevatorArea;
    }

    public void setFreightElevatorArea(BigDecimal freightElevatorArea) {
        this.freightElevatorArea = freightElevatorArea;
    }

    public BigDecimal getFreightElevatorWeight() {
        return freightElevatorWeight;
    }

    public void setFreightElevatorWeight(BigDecimal freightElevatorWeight) {
        this.freightElevatorWeight = freightElevatorWeight;
    }

    public String getBuildingStructure() {
        return buildingStructure;
    }

    public void setBuildingStructure(String buildingStructure) {
        this.buildingStructure = buildingStructure;
    }

    public String getBuildingStructureName() {
        return DictionaryStorage.get(BuildingType.class.getName(),this.getBuildingStructure()).getName();
    }

    public void setBuildingStructureName(String buildingStructureName) {
        this.buildingStructureName = buildingStructureName;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public String getRegionCode() {
        return regionCode;
    }

    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }

    public String getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(String streetCode) {
        this.streetCode = streetCode;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLongitudeCoordinates() {
        return longitudeCoordinates;
    }

    public void setLongitudeCoordinates(String longitudeCoordinates) {
        this.longitudeCoordinates = longitudeCoordinates;
    }
    public String getLatitudeCoordinates() {
        return latitudeCoordinates;
    }

    public void setLatitudeCoordinates(String latitudeCoordinates) {
        this.latitudeCoordinates = latitudeCoordinates;
    }

    public Boolean getIsBathroom() {
        return isBathroom;
    }

    public void setIsBathroom(Boolean isBathroom) {
        this.isBathroom = isBathroom;
    }

    public Boolean getIsOffice() {
        return isOffice;
    }

    public void setIsOffice(Boolean isOffice) {
        this.isOffice = isOffice;
    }

    public Boolean getHasEia() {
        return hasEia;
    }

    public void setHasEia(Boolean hasEia) {
        this.hasEia = hasEia;
    }

    public Boolean getIsDeed() {
        return isDeed;
    }

    public void setIsDeed(Boolean isDeed) {
        this.isDeed = isDeed;
    }

    public String getBuildingStyle() {
        return buildingStyle;
    }

    public void setBuildingStyle(String buildingStyle) {
        this.buildingStyle = buildingStyle;
    }

    public String getBuildingVideo() {
        return buildingVideo;
    }

    public void setBuildingVideo(String buildingVideo) {
        this.buildingVideo = buildingVideo;
    }

    public String getOutPicture() {
        return outPicture;
    }

    public void setOutPicture(String outPicture) {
        this.outPicture = outPicture;
    }

    public String getAerialView() {
        return aerialView;
    }

    public void setAerialView(String aerialView) {
        this.aerialView = aerialView;
    }

    public String getRoadMap() {
        return roadMap;
    }

    public void setRoadMap(String roadMap) {
        this.roadMap = roadMap;
    }

    public String getInternalDiagram() {
        return internalDiagram;
    }

    public void setInternalDiagram(String internalDiagram) {
        this.internalDiagram = internalDiagram;
    }

    public String getBuildingStatus() {
        return buildingStatus;
    }

    public void setBuildingStatus(String buildingStatus) {
        this.buildingStatus = buildingStatus;
    }

    public String getOpenDoor() {
        return openDoor;
    }

    public void setOpenDoor(String openDoor) {
        this.openDoor = openDoor;
    }

    public String getElevatorSpecification() {
        return elevatorSpecification;
    }

    public void setElevatorSpecification(String elevatorSpecification) {
        this.elevatorSpecification = elevatorSpecification;
    }

    public String getFirefightingLevel() {
        return firefightingLevel;
    }

    public void setFirefightingLevel(String firefightingLevel) {
        this.firefightingLevel = firefightingLevel;
    }

    public Boolean getHasRegister() {
        return hasRegister;
    }

    public void setHasRegister(Boolean hasRegister) {
        this.hasRegister = hasRegister;
    }

    public Boolean getHasPollutionTreatment() {
        return hasPollutionTreatment;
    }

    public void setHasPollutionTreatment(Boolean hasPollutionTreatment) {
        this.hasPollutionTreatment = hasPollutionTreatment;
    }

    public String getFirefightingLevelName() {
        return DictionaryStorage.get(FirefightingLevel.class.getName(),this.getFirefightingLevel()).getName();
    }

    public void setFirefightingLevelName(String firefightingLevelName) {
        this.firefightingLevelName = firefightingLevelName;
    }

    public String getBuildingStatusName() {
        return DictionaryStorage.get(BuildingStatus.class.getName(),this.getBuildingStatus()).getName();
    }

    public void setBuildingStatusName(String buildingStatusName) {
        this.buildingStatusName = buildingStatusName;
    }

    public List<FloorEntity> getFloorList() {
        return floorList;
    }

    public void setFloorList(List<FloorEntity> floorList) {
        this.floorList = floorList;
    }
}
