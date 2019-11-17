package com.kfwy.park.pro.house.building.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.pro.house.building.enums.FloorStatus;
import com.kfwy.park.pro.house.building.enums.PriceUnit;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by davidcun on 2018/5/22.
 */
public class ParkEntity extends BaseEntity {

    /**
     * 园区编号
     */
    private String pkCode;
    /*-------------------------园区基本信息-------------------------*/
    /**
     * 园区名称
     */
    private String pkName;
    /**
     * 总用电量
     */
    private BigDecimal useElecTotal;
    /**
     * 地产年限
     */
    private BigDecimal estateYear;
    /**
     * 最短租期
     */
    private BigDecimal shortestLease;
    /**
     * 最长租期
     */
    private BigDecimal longestLease;
    /**
     * 道路宽度
     */
    private BigDecimal roadWidth;
    /**
     * 楼栋总数
     */
    private BigDecimal buildingTotal;
    /**
     * 所属板块
     */
    private String landBlock;
    /**
     * 行业限制
     */
    private String industryRestrictions;
    /*----------------------------园区基本信息--------------------------------*/
    /**
     * 园区承租面积
     */
    private BigDecimal acreage;

    /**
     * 园区公摊比
     * (招商面积-承租面积)/承租面积
     * area_rate
     */
    private BigDecimal areaRate;

    /**
     * 园区公摊面积
     */
    private BigDecimal investArea;

    /**
     * 出租含公摊面积(合同中该园区出租含公摊的面积和)
     */
    private BigDecimal rentInvestArea;

    /**
     * 可招商空地
     */
    private BigDecimal investmentVacantland;
    /**
     * 停车位数量
     */
    private Integer parkingSpace;
    /**
     * 园区起租面积
     */
    private BigDecimal minRentArea;
    /**
     * 园区拓展平均价格
     * average_price
     */
    private BigDecimal averagePrice;
    /*-------------------------园区基本信息（新增暂时不用）-------------------------*/
    /**
     * 剩余计租面积
     */
    private BigDecimal rentableArea;
    /**
     * 状态
     * @see FloorStatus
     */
    private String parkStatus;
    /**
     * 状态名称
     */
    private String parkStatusName;
    /*-----------------------------配套信息--------------------------*/
    /**
     * 有无产证
     */
    private boolean hasCertify;
    /**
     * 是否环评
     */
    private boolean hasEia;
    /**
     * 有无排污证
     */
    private boolean hasSewageCertify;
    /**
     * 有无超市
     */
    private boolean hasSupermarket;
    /**
     * 有无食堂
     */
    private boolean hasCanteen;
    /**
     * 有无公摊
     */
    private boolean hasPubBooth;
    /**
     * 有无办公楼
     */
    private boolean hasOfficeBuild;
    /**
     * 有无雨棚
     */
    private boolean hasCanopy;
    /**
     * 有无停车场
     */
    private boolean hasCarPark;
    /**
     * 有无卸货平台
     */
    private boolean hasLoadDock;
    /**
     * 有无充电桩
     */
    private boolean hasChargingPile;
    /**
     * 是否注册
     */
    private boolean hasRegister;
    /*-------------------------园区地址-----------------------*/
    /**
     * 房源省市区街道编码
     */
    private String provinceCode;
    private String cityCode;
    private String regionCode;
    private String streetCode;
    /**
     * 房源省市区街道名称
     */
    private String provinceName;
    private String cityName;
    private String regionName;
    private String streetName;
    /**
     * 详细地址
     */
    private String address;

    /**
     * 所属大区(部门编号)
     */
    private String territoryType;

    /**
     * 所属大区名称
     */
    private String territoryTypeName;


    /*-------------------------招商对接人员-----------------------*/
    /**
     * 更多招商对接人列表
     */
    private List<ParkButtJointEntity> atiButtJointList;
    /*----------------------------物业对接人员-----------------------------*/
    /**
     * 更多物业对接人列表
     */
    private List<ParkButtJointEntity> proButtJointList;
    /*-------------------------------其他信息-------------------------------*/
    /**
     * 经营开始日
     */
    private Date businessStartDay;
    /**
     * 经营到期日
     */
    private Date businessEndDay;
    /**
     * 每月抄表日
     */
    private String meterReading;
    /**
     * 剩余电量（详情页用）
     */
    private BigDecimal useElecSurplus;
    /**
     * 物业费
     */
    private BigDecimal propertyCost;
    /**
     * 主导行业
     */
    private String leadTrade;
    /**
     * 交通配套
     */
    private String trafficMatching;
    /**
     * 优惠政策
     */
    private String favouredPolicy;

    /*-------------------------------上传园区视频-------------------------------*/
    /**
     * 园区视频
     */
    private String parkVideo;
    /**
     * 鸟瞰图
     */
    private String aerialView;
    /**
     * 外立面(园区环境)
     */
    private String outPicture;
    /*-------------------------------楼栋集合-------------------------------*/
    /**
     * 楼栋集合
     */
    private List<BuildingEntity> buildingList;





    /*-------------------------------闲置字段-------------------------------*/
    /**
     * 建筑标题 -
     */
    private String buildTitle;
    /**
     * 园区起租价格 -
     */
    private BigDecimal rentPrice;
    /**
     * 价格单位（字典） 新 -
     * @see PriceUnit
     */
    private String priceUnit;
    /**
     * 价格单位名称（字典） 新 -
     */
    private String priceUnitName;
    /**
     * 空置面积 -
     */
    private BigDecimal vacantArea;
    /**
     * 最大通行车辆 -
     */
    private BigDecimal maxPassCar;
    /**
     * 经营年限 -
     */
    private  BigDecimal manageYear;
    /**
     * 物业公司 -
     */
    private String propertyCompany;
    /**
     * 主对接人编码 -
     */
    private String atiButtJointCode;
    /**

     * 主对接人姓名 -
     */
    private String atiButtJointName;
    /**
     * 主对接人电话 -
     */
    private String atiButtJointPhone;
    /**
     * 主对接人编码 -
     */
    private String proButtJointCode;
    /**
     * 主对接人姓名 -
     */
    private String proButtJointName;
    /**
     * 主对接人电话 -
     */
    private String proButtJointPhone;
    /**
     * 主对接人岗位 -
     */
    private String proButtJointPosition;
    /**
     * 园区特色（新增不用） -
     */
    private String describe;
    /**
     * 道路图 -
     */
    private String roadMap;
    /**
     * 内部图 -
     */
    private String internalDiagram;
    /**
     * 园区业态 -
     */
    private String parkFormat;

    /*====================================字段分割线====================================*/
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


    public String getLandBlock() {
        return landBlock;
    }

    public void setLandBlock(String landBlock) {
        this.landBlock = landBlock;
    }

    public BigDecimal getMinRentArea() {
        return minRentArea;
    }

    public void setMinRentArea(BigDecimal minRentArea) {
        this.minRentArea = minRentArea;
    }

    public BigDecimal getEstateYear() {
        return estateYear;
    }

    public void setEstateYear(BigDecimal estateYear) {
        this.estateYear = estateYear;
    }


    public BigDecimal getUseElecTotal() {
        return useElecTotal;
    }

    public void setUseElecTotal(BigDecimal useElecTotal) {
        this.useElecTotal = useElecTotal;
    }

    public BigDecimal getRoadWidth() {
        return roadWidth;
    }

    public void setRoadWidth(BigDecimal roadWidth) {
        this.roadWidth = roadWidth;
    }


    public BigDecimal getPropertyCost() {
        return propertyCost;
    }

    public void setPropertyCost(BigDecimal propertyCost) {
        this.propertyCost = propertyCost;
    }

    public boolean isHasCertify() {
        return hasCertify;
    }

    public void setHasCertify(boolean hasCertify) {
        this.hasCertify = hasCertify;
    }

    public boolean isHasEia() {
        return hasEia;
    }

    public void setHasEia(boolean hasEia) {
        this.hasEia = hasEia;
    }

    public boolean isHasSewageCertify() {
        return hasSewageCertify;
    }

    public void setHasSewageCertify(boolean hasSewageCertify) {
        this.hasSewageCertify = hasSewageCertify;
    }

    public boolean isHasCanteen() {
        return hasCanteen;
    }

    public void setHasCanteen(boolean hasCanteen) {
        this.hasCanteen = hasCanteen;
    }

    public boolean isHasPubBooth() {
        return hasPubBooth;
    }

    public void setHasPubBooth(boolean hasPubBooth) {
        this.hasPubBooth = hasPubBooth;
    }

    public boolean isHasOfficeBuild() {
        return hasOfficeBuild;
    }

    public void setHasOfficeBuild(boolean hasOfficeBuild) {
        this.hasOfficeBuild = hasOfficeBuild;
    }

    public boolean isHasCanopy() {
        return hasCanopy;
    }

    public void setHasCanopy(boolean hasCanopy) {
        this.hasCanopy = hasCanopy;
    }

    public boolean isHasCarPark() {
        return hasCarPark;
    }

    public void setHasCarPark(boolean hasCarPark) {
        this.hasCarPark = hasCarPark;
    }

    public boolean isHasLoadDock() {
        return hasLoadDock;
    }

    public void setHasLoadDock(boolean hasLoadDock) {
        this.hasLoadDock = hasLoadDock;
    }

    public boolean isHasSupermarket() {
        return hasSupermarket;
    }

    public void setHasSupermarket(boolean hasSupermarket) {
        this.hasSupermarket = hasSupermarket;
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


    public String getLeadTrade() {
        return leadTrade;
    }

    public void setLeadTrade(String leadTrade) {
        this.leadTrade = leadTrade;
    }


    public String getParkVideo() {
        return parkVideo;
    }

    public void setParkVideo(String parkVideo) {
        this.parkVideo = parkVideo;
    }

    public String getOutPicture() {
        return outPicture;
    }

    public void setOutPicture(String outPicture) {
        this.outPicture = outPicture;
    }

    public BigDecimal getRentableArea() {
        return rentableArea;
    }

    public void setRentableArea(BigDecimal rentableArea) {
        this.rentableArea = rentableArea;
    }


    public BigDecimal getLongestLease() {
        return longestLease;
    }

    public void setLongestLease(BigDecimal longestLease) {
        this.longestLease = longestLease;
    }

    public BigDecimal getShortestLease() {
        return shortestLease;
    }

    public void setShortestLease(BigDecimal shortestLease) {
        this.shortestLease = shortestLease;
    }


    public BigDecimal getBuildingTotal() {
        return buildingTotal;
    }

    public void setBuildingTotal(BigDecimal buildingTotal) {
        this.buildingTotal = buildingTotal;
    }

    public String getIndustryRestrictions() {
        return industryRestrictions;
    }

    public void setIndustryRestrictions(String industryRestrictions) {
        this.industryRestrictions = industryRestrictions;
    }

    public boolean isHasChargingPile() {
        return hasChargingPile;
    }

    public void setHasChargingPile(boolean hasChargingPile) {
        this.hasChargingPile = hasChargingPile;
    }

    public String getTrafficMatching() {
        return trafficMatching;
    }

    public void setTrafficMatching(String trafficMatching) {
        this.trafficMatching = trafficMatching;
    }

    public String getFavouredPolicy() {
        return favouredPolicy;
    }

    public void setFavouredPolicy(String favouredPolicy) {
        this.favouredPolicy = favouredPolicy;
    }

    public String getAerialView() {
        return aerialView;
    }

    public void setAerialView(String aerialView) {
        this.aerialView = aerialView;
    }


    public List<ParkButtJointEntity> getAtiButtJointList() {
        return atiButtJointList;
    }

    public void setAtiButtJointList(List<ParkButtJointEntity> atiButtJointList) {
        this.atiButtJointList = atiButtJointList;
    }

    public List<ParkButtJointEntity> getProButtJointList() {
        return proButtJointList;
    }

    public void setProButtJointList(List<ParkButtJointEntity> proButtJointList) {
        this.proButtJointList = proButtJointList;
    }

    public BigDecimal getInvestmentVacantland() {
        return investmentVacantland;
    }

    public void setInvestmentVacantland(BigDecimal investmentVacantland) {
        this.investmentVacantland = investmentVacantland;
    }

    public Integer getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(Integer parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Date getBusinessStartDay() {
        return businessStartDay;
    }

    public void setBusinessStartDay(Date businessStartDay) {
        this.businessStartDay = businessStartDay;
    }

    public Date getBusinessEndDay() {
        return businessEndDay;
    }

    public void setBusinessEndDay(Date businessEndDay) {
        this.businessEndDay = businessEndDay;
    }

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public String getMeterReading() {
        return meterReading;
    }

    public void setMeterReading(String meterReading) {
        this.meterReading = meterReading;
    }

    public BigDecimal getUseElecSurplus() {
        return useElecSurplus;
    }

    public void setUseElecSurplus(BigDecimal useElecSurplus) {
        this.useElecSurplus = useElecSurplus;
    }

    public List<BuildingEntity> getBuildingList() {
        return buildingList;
    }

    public void setBuildingList(List<BuildingEntity> buildingList) {
        this.buildingList = buildingList;
    }

    public String getParkStatus() {
        return parkStatus;
    }

    public void setParkStatus(String parkStatus) {
        this.parkStatus = parkStatus;
    }

    public String getParkStatusName() {
        return DictionaryStorage.get(FloorStatus.class.getName(),this.getParkStatus()).getName();
    }

    public void setParkStatusName(String parkStatusName) {
        this.parkStatusName = parkStatusName;
    }

    public boolean isHasRegister() {
        return hasRegister;
    }

    public void setHasRegister(boolean hasRegister) {
        this.hasRegister = hasRegister;
    }

    public String getBuildTitle() {
        return buildTitle;
    }

    public void setBuildTitle(String buildTitle) {
        this.buildTitle = buildTitle;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public String getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(String priceUnit) {
        this.priceUnit = priceUnit;
    }

    public String getPriceUnitName() {
        return priceUnitName;
    }

    public void setPriceUnitName(String priceUnitName) {
        this.priceUnitName = priceUnitName;
    }

    public BigDecimal getVacantArea() {
        return vacantArea;
    }

    public void setVacantArea(BigDecimal vacantArea) {
        this.vacantArea = vacantArea;
    }

    public BigDecimal getMaxPassCar() {
        return maxPassCar;
    }

    public void setMaxPassCar(BigDecimal maxPassCar) {
        this.maxPassCar = maxPassCar;
    }

    public BigDecimal getManageYear() {
        return manageYear;
    }

    public void setManageYear(BigDecimal manageYear) {
        this.manageYear = manageYear;
    }

    public String getPropertyCompany() {
        return propertyCompany;
    }

    public void setPropertyCompany(String propertyCompany) {
        this.propertyCompany = propertyCompany;
    }

    public String getAtiButtJointCode() {
        return atiButtJointCode;
    }

    public void setAtiButtJointCode(String atiButtJointCode) {
        this.atiButtJointCode = atiButtJointCode;
    }

    public String getAtiButtJointName() {
        return atiButtJointName;
    }

    public void setAtiButtJointName(String atiButtJointName) {
        this.atiButtJointName = atiButtJointName;
    }

    public String getAtiButtJointPhone() {
        return atiButtJointPhone;
    }

    public void setAtiButtJointPhone(String atiButtJointPhone) {
        this.atiButtJointPhone = atiButtJointPhone;
    }

    public String getProButtJointCode() {
        return proButtJointCode;
    }

    public void setProButtJointCode(String proButtJointCode) {
        this.proButtJointCode = proButtJointCode;
    }

    public String getProButtJointName() {
        return proButtJointName;
    }

    public void setProButtJointName(String proButtJointName) {
        this.proButtJointName = proButtJointName;
    }

    public String getProButtJointPhone() {
        return proButtJointPhone;
    }

    public void setProButtJointPhone(String proButtJointPhone) {
        this.proButtJointPhone = proButtJointPhone;
    }

    public String getProButtJointPosition() {
        return proButtJointPosition;
    }

    public void setProButtJointPosition(String proButtJointPosition) {
        this.proButtJointPosition = proButtJointPosition;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
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

    public String getParkFormat() {
        return parkFormat;
    }

    public void setParkFormat(String parkFormat) {
        this.parkFormat = parkFormat;
    }

    public BigDecimal getAveragePrice() {
        return averagePrice;
    }

    public void setAveragePrice(BigDecimal averagePrice) {
        this.averagePrice = averagePrice;
    }

    public BigDecimal getAreaRate() {
        return areaRate;
    }

    public void setAreaRate(BigDecimal areaRate) {
        this.areaRate = areaRate;
    }

    public BigDecimal getRentInvestArea() {
        return rentInvestArea;
    }

    public void setRentInvestArea(BigDecimal rentInvestArea) {
        this.rentInvestArea = rentInvestArea;
    }

    public BigDecimal getInvestArea() {
        return investArea;
    }

    public void setInvestArea(BigDecimal investArea) {
        this.investArea = investArea;
    }

    public String getTerritoryType() {
        return territoryType;
    }

    public void setTerritoryType(String territoryType) {
        this.territoryType = territoryType;
    }

    public String getTerritoryTypeName() {
        return territoryTypeName;
    }

    public void setTerritoryTypeName(String territoryTypeName) {
        this.territoryTypeName = territoryTypeName;
    }
}
