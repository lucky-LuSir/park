package com.kfwy.park.pro.house.building.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.common.dic.FirefightingLevel;
import com.kfwy.park.pro.house.building.enums.DecorationType;
import com.kfwy.park.pro.house.building.enums.FloorStatus;
import com.kfwy.park.pro.house.building.enums.FloorType;
import com.kfwy.park.pro.house.building.enums.PriceUnit;
import com.kfwy.park.pro.house.building.enums.UseType;
import com.kfwy.park.pro.house.building.enums.WareHouseType;

import java.math.BigDecimal;
import java.util.List;

/**
 * 楼层实体
 * @author wangchuang
 */
public class FloorEntity extends BaseEntity{
    /**
     * 主键园区编码
     */
    private String pkCode;
    /**
     * 主键园区名称
     */
    private String pkName;
    /**
     * 建筑编码
     */
    private String bdCode;
    /**
     * 建筑名称
     */
    private String bdName;
    /**
     * 楼层编码
     */
    private String flCode;
    /*----------------------------楼层基本信息----------------------*/
    /**
     * 计租面积
     */
    private BigDecimal investArea;
    /**
     * 可出租面积
     */
    private BigDecimal rentableArea;
    /**
     * 已租用面积
     */
    private BigDecimal rentedArea;
    /**
     * 层高
     */
    private BigDecimal floorHeight;
    /**
     * 地坪类型
     * @see FloorType
     */
    private String floorType;
    /**
     * 地坪类型名称
     */
    private String floorTypeName;
    /**
     * 状态 -
     * @see FloorStatus
     */
    private String floorStatus;
    /**
     * 状态名称 -
     */
    private String floorStatusName;
    /**
     * 地坪承重
     */
    private BigDecimal floorBearWeight;
    /**
     * 所在层
     */
    private String thisFloor;
    /**
     * 用途
     * @see UseType
     */
    private String useType;
    /**
     * 装修类型
     * @see DecorationType
     */
    private String decorationType;
    /**
     * 装修类型名称
     */
    private String decorationTypeName;
    /**
     * 进深
     */
    private String floorDeep;
    /**
     * 柱距
     */
    private BigDecimal pillarSpacing;
    /**
     * 层租金
     */
    private BigDecimal floorPrice;
    /*---------------------------配套信息------------------------*/
    /**
     * 是否通电梯
     */
    private boolean hasElevator;
    /**
     * 是否有行车
     */
    private boolean hasDriving;
    /**
     * 是否有办公区域
     */
    private boolean hasOfficeArea;
    /**
     * 是否有卫生间
     */
    private boolean hasBathroom;
    /*-------------------------------上传园区楼层图片-------------------------------*/
    /**
     * 外立面
     */
    private String outPicture;
    /**
     * 鸟瞰图
     */
    private String aerialView;
    /**
     * 内部图
     */
    private String internalDiagram;

    /**
     * 销售定价
     */
    private BigDecimal floorUnitPrice;
    /**
     * 定价单位
     * @see PriceUnit
     */
    private String priceUnit;
    /**
     * 价格单位名称
     */
    private String priceUnitName;

    /**
     * 承租单价
     */
    private BigDecimal tenantPrice;
    /**
     * 承租单价单位
     * @see PriceUnit
     */
    private String tenantUnit;
    /**
     * 承租单价单位名称
     */
    private String tenantUnitName;

    /**-------------------毛利率-------------------**/
    /**
     * 承租毛利率
     */
    private BigDecimal projectGross;
    /**
     * 出租毛利率
     */
    private BigDecimal investGross;

    /*---------------------------- 封装合同信息 ----------------------------*/

    private List<FloorContractHouseEntity> floorContractHouseEntityList;




/*============================================目前未用字段=====================================*/

    /**
     * 库房类型 -
     * @see WareHouseType
     */
    private String wareHouseType;
    /**
     * 库房类型名称 -
     */
    private String wareHouseTypeName;
    /**
     * 最长租期 -
     */
    private BigDecimal maxRentDate;
    /**
     * 最短租期 -
     */
    private BigDecimal minRentDate;
    /**
     * 最小分割面积 -
     */
    private BigDecimal minSegnebtationArea;
    /**
     * 分割描述 -
     */
    private String segmentationDes;
    /**
     * 行业限制 -
     */
    private String businessLimit;
    /**
     * 消防等级 -
     * @see FirefightingLevel
     */
    private String firefightingLevel;
    /**
     * 消防等级名称 -
     */
    private String firefightingLevelName;
    /*---------------------------配套信息------------------------*/
    /**
     * 是否可分割 -
     */
    private boolean hasSegmentation;
    /**
     * 是否有产证 -
     */
    private boolean hasCertify;
    /**
     * 是否有货台 -
     */
    private boolean hasFreightTable;
    /**
     * 是否有货梯 -
     */
    private boolean hasFreightElevator;


    /*-------------------------------上传园区视频-------------------------------*/
    /**
     * 园区视频 -
     */
    private String floorVideo;
    /**
     * 道路图 -
     */
    private String roadMap;
    /*============================================目前未用字段 end=====================================*/

    /*============================================字段分割线=====================================*/

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

    public String getBdName() {
        return bdName;
    }

    public void setBdName(String bdName) {
        this.bdName = bdName;
    }

    public String getFlCode() {
        return flCode;
    }

    public void setFlCode(String flCode) {
        this.flCode = flCode;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getDecorationType() {
        return decorationType;
    }

    public void setDecorationType(String decorationType) {
        this.decorationType = decorationType;
    }

    public String getDecorationTypeName() {
        return DictionaryStorage.get(DecorationType.class.getName(),this.getDecorationType()).getName();
    }

    public void setDecorationTypeName(String decorationTypeName) {
        this.decorationTypeName = decorationTypeName;
    }

    public String getWareHouseType() {
        return wareHouseType;
    }

    public void setWareHouseType(String wareHouseType) {
        this.wareHouseType = wareHouseType;
    }

    public String getWareHouseTypeName() {
        return DictionaryStorage.get(WareHouseType.class.getName(),this.getWareHouseType()).getName();
    }

    public void setWareHouseTypeName(String wareHouseTypeName) {
        this.wareHouseTypeName = wareHouseTypeName;
    }

    public String getFloorType() {
        return floorType;
    }

    public void setFloorType(String floorType) {
        this.floorType = floorType;
    }

    public String getFloorTypeName() {
        return DictionaryStorage.get(FloorType.class.getName(),this.getFloorType()).getName();
    }

    public void setFloorTypeName(String floorTypeName) {
        this.floorTypeName = floorTypeName;
    }

    public BigDecimal getFloorBearWeight() {
        return floorBearWeight;
    }

    public void setFloorBearWeight(BigDecimal floorBearWeight) {
        this.floorBearWeight = floorBearWeight;
    }

    public String getThisFloor() {
        return thisFloor;
    }

    public void setThisFloor(String thisFloor) {
        this.thisFloor = thisFloor;
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

    public BigDecimal getRentedArea() {
        return rentedArea;
    }

    public void setRentedArea(BigDecimal rentedArea) {
        this.rentedArea = rentedArea;
    }

    public BigDecimal getFloorHeight() {
        return floorHeight;
    }

    public void setFloorHeight(BigDecimal floorHeight) {
        this.floorHeight = floorHeight;
    }

    public BigDecimal getFloorUnitPrice() {
        return floorUnitPrice;
    }

    public void setFloorUnitPrice(BigDecimal floorUnitPrice) {
        this.floorUnitPrice = floorUnitPrice;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getPriceUnitName() {
        return DictionaryStorage.get(PriceUnit.class.getName(),this.getPriceUnit()).getName();
    }

    public void setPriceUnitName(String priceUnitName) {
        this.priceUnitName = priceUnitName;
    }

    public BigDecimal getPillarSpacing() {
        return pillarSpacing;
    }

    public void setPillarSpacing(BigDecimal pillarSpacing) {
        this.pillarSpacing = pillarSpacing;
    }

    public String getFloorDeep() {
        return floorDeep;
    }

    public void setFloorDeep(String floorDeep) {
        this.floorDeep = floorDeep;
    }

    public boolean isHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    public boolean isHasDriving() {
        return hasDriving;
    }

    public void setHasDriving(boolean hasDriving) {
        this.hasDriving = hasDriving;
    }

    public boolean isHasSegmentation() {
        return hasSegmentation;
    }

    public void setHasSegmentation(boolean hasSegmentation) {
        this.hasSegmentation = hasSegmentation;
    }

    public boolean isHasCertify() {
        return hasCertify;
    }

    public void setHasCertify(boolean hasCertify) {
        this.hasCertify = hasCertify;
    }

    public boolean isHasBathroom() {
        return hasBathroom;
    }

    public void setHasBathroom(boolean hasBathroom) {
        this.hasBathroom = hasBathroom;
    }

    public boolean isHasFreightTable() {
        return hasFreightTable;
    }

    public void setHasFreightTable(boolean hasFreightTable) {
        this.hasFreightTable = hasFreightTable;
    }

    public boolean isHasFreightElevator() {
        return hasFreightElevator;
    }

    public void setHasFreightElevator(boolean hasFreightElevator) {
        this.hasFreightElevator = hasFreightElevator;
    }

    public boolean isHasOfficeArea() {
        return hasOfficeArea;
    }

    public void setHasOfficeArea(boolean hasOfficeArea) {
        this.hasOfficeArea = hasOfficeArea;
    }

    public BigDecimal getMinSegnebtationArea() {
        return minSegnebtationArea;
    }

    public void setMinSegnebtationArea(BigDecimal minSegnebtationArea) {
        this.minSegnebtationArea = minSegnebtationArea;
    }

    public String getSegmentationDes() {
        return segmentationDes;
    }

    public void setSegmentationDes(String segmentationDes) {
        this.segmentationDes = segmentationDes;
    }

    public String getBusinessLimit() {
        return businessLimit;
    }

    public void setBusinessLimit(String businessLimit) {
        this.businessLimit = businessLimit;
    }

    public String getFirefightingLevel() {
        return firefightingLevel;
    }

    public void setFirefightingLevel(String firefightingLevel) {
        this.firefightingLevel = firefightingLevel;
    }

    public String getFirefightingLevelName() {
        return DictionaryStorage.get(FirefightingLevel.class.getName(),this.getFirefightingLevel()).getName();
    }

    public void setFirefightingLevelName(String firefightingLevelName) {
        this.firefightingLevelName = firefightingLevelName;
    }

    public BigDecimal getMinRentDate() {
        return minRentDate;
    }

    public void setMinRentDate(BigDecimal minRentDate) {
        this.minRentDate = minRentDate;
    }

    public BigDecimal getMaxRentDate() {
        return maxRentDate;
    }

    public void setMaxRentDate(BigDecimal maxRentDate) {
        this.maxRentDate = maxRentDate;
    }

    public String getFloorStatus() {
        return floorStatus;
    }

    public void setFloorStatus(String floorStatus) {
        this.floorStatus = floorStatus;
    }

    public String getFloorStatusName() {
        return DictionaryStorage.get(FloorStatus.class.getName(),this.getFloorStatus()).getName();
    }

    public void setFloorStatusName(String floorStatusName) {
        this.floorStatusName = floorStatusName;
    }

    public String getFloorVideo() {
        return floorVideo;
    }

    public void setFloorVideo(String floorVideo) {
        this.floorVideo = floorVideo;
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

    public BigDecimal getFloorPrice() {
        return floorPrice;
    }

    public void setFloorPrice(BigDecimal floorPrice) {
        this.floorPrice = floorPrice;
    }

    public List<FloorContractHouseEntity> getFloorContractHouseEntityList() {
        return floorContractHouseEntityList;
    }

    public void setFloorContractHouseEntityList(List<FloorContractHouseEntity> floorContractHouseEntityList) {
        this.floorContractHouseEntityList = floorContractHouseEntityList;
    }

    public BigDecimal getTenantPrice() {
        return tenantPrice;
    }

    public void setTenantPrice(BigDecimal tenantPrice) {
        this.tenantPrice = tenantPrice;
    }

    public String getTenantUnit() {
        return tenantUnit;
    }

    public void setTenantUnit(String tenantUnit) {
        this.tenantUnit = tenantUnit;
    }

    public String getTenantUnitName() {
        return DictionaryStorage.get(PriceUnit.class.getName(),this.getTenantUnit()).getName();
    }

    public void setTenantUnitName(String tenantUnitName) {
        this.tenantUnitName = tenantUnitName;
    }

    public BigDecimal getProjectGross() {
        return projectGross;
    }

    public void setProjectGross(BigDecimal projectGross) {
        this.projectGross = projectGross;
    }

    public BigDecimal getInvestGross() {
        return investGross;
    }

    public void setInvestGross(BigDecimal investGross) {
        this.investGross = investGross;
    }
}
