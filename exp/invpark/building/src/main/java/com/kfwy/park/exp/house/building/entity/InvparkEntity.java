package com.kfwy.park.exp.house.building.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.common.dic.FirefightingLevel;
import com.kfwy.park.common.dic.SexType;
import com.kfwy.park.exp.house.building.enums.*;

import java.math.BigDecimal;

/**
 * @Description 扩展模块，园区实体
 * @Auth luming
 * @Date 2018/11/5 8:57
 * @Version 1.0
 * @Param
 * @return
 */
public class InvparkEntity extends BaseEntity {

    /********************************************************************************************
     * 业主信息
     *******************************************************************************************/

    /**
     * 项目编码
     */
    private String itemCode;

    /**
     * 业主公司名
     */
    private String parkOwnerCpyName;

    /**
     * 业主姓名
     */
    private String parkOwnerName;

    /**
     * 业主性别（字典）
     * @see SexType
     */
    private String parkOwnerSex;

    /**
     * 业主性别名称（字典）
     */
    private String parkOwnerSexName;

    /**
     * 业主电话
     */
    private String parkOwnerPhone;

    /**
     * 业主备用联系人
     */
    private String parkOwnerReserveContactName;

    /**
     * 业主备用联系人性别（字典）
     * @see SexType
     */
    private String parkOwnerReserveContactSex;

    /**
     * 业主备用联系人性别名称（字典）
     */
    private String parkOwnerReserveContactSexName;

    /**
     * 业主备用联系人电话
     */
    private String parkOwnerReserveContactPhone;


    /********************************************************************************************
     * 位置信息
     *******************************************************************************************/

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
     * 距离主城区
     */
    private BigDecimal distanceDowntown;

    /**
     * 距离周围生活区域
     */
    private BigDecimal distanceNearbyLifeRegion;

    /**
     * 工业集中区（字典）
     *
     * @see ManufacturingDistrict
     */
    private String manufacturingDistrict;

    /**
     * 工业集中区名称（字典）
     */
    private String manufacturingDistrictName;

    /**
     * 高速公路入口距离
     */
    private BigDecimal distanceFreewayEntrance;

    /**
     * 1.5公里内是否有公共交通
     */
    private Boolean hasPublicTraffic;

    /**
     * 距离主干道
     */
    private BigDecimal distanceMainStreet;

    /**
     * 车道数量
     */
    private Integer laneQuantity;

    /**
     * 周边价格
     */
    private BigDecimal nearbyPrice;


    /********************************************************************************************
     * 厂房形象及装修
     *******************************************************************************************/

    /**
     * 是否是新厂房
     */
    private Boolean hasNewPlant;

    /**
     * 厂房备注
     */
    private String plantRemark;

    /**
     * 厂房装修情况（字典）
     *
     * @see PlantDecoration
     */
    private String plantDecoration;

    /**
     * 厂房装修情况名称（字典）
     */
    private String plantDecorationName;

    /**
     * 装修备注
     */
    private String decorationRemark;

    /**
     * 送电情况（字典）
     *
     * @see PlantElectricity
     */
    private String plantElectricity;

    /**
     * 送电情况名称（字典）
     */
    private String plantElectricityName;

    /**
     * 送电情况备注
     */
    private String electricityRemark;


    /********************************************************************************************
     * 厂房面积结构
     *******************************************************************************************/

    /**
     * 园区占地面积
     */
    private BigDecimal parkArea;

    /**
     * 总建筑面积
     */
    private BigDecimal parkBuildingArea;

    /**
     * 单层厂房栋数
     */
    private Integer singleFloorQuantity;

    /**
     * 单层厂房面积
     */
    private BigDecimal singleFloorArea;

    /**
     * 单层厂房层高
     */
    private BigDecimal singleFloorHeight;

    /**
     * 单层厂房备注
     */
    private String singleFloorRemark;

    /**
     * 多层厂房栋数
     */
    private Integer moreFloorQuantity;

    /**
     * 多层厂房面积
     */
    private BigDecimal moreFloorArea;

    /**
     * 多层厂房层高
     */
    private BigDecimal moreFloorHeight;

    /**
     * 多层厂房备注
     */
    private BigDecimal moreFloorRemark;

    /**
     * 多层厂房电梯数
     */
    private Integer moreFloorElevatorQuantity;

    /**
     * 电梯位置
     */
    private String elevatorLocation;

    /**
     * 办公楼数量
     */
    private Integer officeBuildingQuantity;

    /**
     * 办公楼建筑面积
     */
    private BigDecimal officeBuildingArea;

    /**
     * 办公楼层高
     */
    private BigDecimal officeBuildingHeight;

    /**
     * 办公楼备注
     */
    private String officeBuildingRemark;

    /**
     * 其他附房数量
     */
    private Integer otherBuildingQuantity;

    /**
     * 其他附房建筑面积
     */
    private BigDecimal otherBuildingArea;

    /**
     * 附房占比
     */
    private BigDecimal otherBuildingRate;

    /**
     * 其他附房备注
     */
    private String otherBuildingRemark;

    /**
     * 空地面积
     */
    private BigDecimal vacancySoilArea;

    /**
     * 空地备注
     */
    private String vacancySoilRemark;


    /********************************************************************************************
     * 配电及消防
     *******************************************************************************************/

    /**
     * 总用电容量
     */
    private BigDecimal totalElectricity;

    /**
     * 消防等级（字典）
     *
     * @see FirefightingLevel
     */
    private String firefightingLevel;

    /**
     * 消防等级名称（字典）
     */
    private String firefightingLevelName;


    /********************************************************************************************
     * 商务条款及政策情况
     *******************************************************************************************/

    /**
     * 地产年限
     */
    private BigDecimal estateYear;

    /**
     * 合同年限
     */
    private BigDecimal contractYear;

    /**
     * 所属板块
     */
    private String belongArea;

    /**
     * 房产证
     */
    private Boolean hasHouseCertificate;

    /**
     * 环评证书
     */
    private Boolean hasEiaCertificate;

    /**
     * 排污证
     */
    private Boolean hasSewageCertificate;

    /**
     * 消防证
     */
    private Boolean hasFirefightingCertificate;

    /**
     * 拥有证书备注
     */
    private String certificateRemark;

    /**
     * 价格（单价）
     */
    private BigDecimal unitPrice;

    /**
     * 物业费
     */
    private BigDecimal strataFee;

    /**
     * 免租期
     */
    private BigDecimal freeRentDate;

    /**
     * 付款规则（字典）
     *
     * @see PayRule
     */
    private String payRule;

    /**
     * 付款规则名称（字典）
     */
    private String payRuleName;

    /**
     * 递增规则
     */
    private String increaseRule;

    /**
     * 递增规则名称
     *
     * @see IncreaseRule
     */
    private String increaseRuleName;

    /**
     * 税收要求（字典）
     *
     * @see TaxRequest
     */
    private String taxRequest;

    /**
     * 税收要求名称（字典）
     */
    private String taxRequestName;

    /**
     * 税金
     */
    private BigDecimal taxes;

    /**
     * 限制行业
     */
    private String restrictIndustry;


    /********************************************************************************************
     * 影像资料
     *******************************************************************************************/

    /**
     * 视频展示
     */
    private String showVideo;

    /**
     * 图片展示
     */
    private String showPicture;

    /********************************************************************************************
     * 拓展人员
     *******************************************************************************************/

    /**
     *拓展人员编码
     */
    private String expEmpCode;
    /**
     *拓展人姓名
     */
    private String expEmpName;
    /**
     * 拓展人电话
     */
    private String expEmpPhone;
    /**
     * 拓展人公司
     */
    private String expEmpCpyName;

    /********************************************************************************************
     * 项目评分
     *******************************************************************************************/

    /**
     * 评分
     */
    private Double score;

    /**
     * 评定结果
     */
    private String assessResult;

    /**
     * 项目状态
     */
    private String expStatus;
    /**
     * 项目状态名称
     * @see ExpStatus
     */
    private String expStatusName;


    /********************************************************************************************
     * get、set方法
     *******************************************************************************************/

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getParkOwnerCpyName() {
        return parkOwnerCpyName;
    }

    public void setParkOwnerCpyName(String parkOwnerCpyName) {
        this.parkOwnerCpyName = parkOwnerCpyName;
    }

    public String getParkOwnerName() {
        return parkOwnerName;
    }

    public void setParkOwnerName(String parkOwnerName) {
        this.parkOwnerName = parkOwnerName;
    }

    public String getParkOwnerSex() {
        return parkOwnerSex;
    }

    public void setParkOwnerSex(String parkOwnerSex) {
        this.parkOwnerSex = parkOwnerSex;
    }

    public String getParkOwnerSexName() {
        return DictionaryStorage.get(SexType.class.getName(), this.getParkOwnerSex()).getName();
    }

    public void setParkOwnerSexName(String parkOwnerSexName) {
        this.parkOwnerSexName = parkOwnerSexName;
    }

    public String getParkOwnerPhone() {
        return parkOwnerPhone;
    }

    public void setParkOwnerPhone(String parkOwnerPhone) {
        this.parkOwnerPhone = parkOwnerPhone;
    }

    public String getParkOwnerReserveContactName() {
        return parkOwnerReserveContactName;
    }

    public void setParkOwnerReserveContactName(String parkOwnerReserveContactName) {
        this.parkOwnerReserveContactName = parkOwnerReserveContactName;
    }

    public String getParkOwnerReserveContactSex() {
        return parkOwnerReserveContactSex;
    }

    public void setParkOwnerReserveContactSex(String parkOwnerReserveContactSex) {
        this.parkOwnerReserveContactSex = parkOwnerReserveContactSex;
    }

    public String getParkOwnerReserveContactSexName() {
        return DictionaryStorage.get(SexType.class.getName(), this.getParkOwnerReserveContactSex()).getName();
    }

    public void setParkOwnerReserveContactSexName(String parkOwnerReserveContactSexName) {
        this.parkOwnerReserveContactSexName = parkOwnerReserveContactSexName;
    }

    public String getParkOwnerReserveContactPhone() {
        return parkOwnerReserveContactPhone;
    }

    public void setParkOwnerReserveContactPhone(String parkOwnerReserveContactPhone) {
        this.parkOwnerReserveContactPhone = parkOwnerReserveContactPhone;
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

    public BigDecimal getDistanceDowntown() {
        return distanceDowntown;
    }

    public void setDistanceDowntown(BigDecimal distanceDowntown) {
        this.distanceDowntown = distanceDowntown;
    }

    public BigDecimal getDistanceNearbyLifeRegion() {
        return distanceNearbyLifeRegion;
    }

    public void setDistanceNearbyLifeRegion(BigDecimal distanceNearbyLifeRegion) {
        this.distanceNearbyLifeRegion = distanceNearbyLifeRegion;
    }

    public String getManufacturingDistrict() {
        return manufacturingDistrict;
    }

    public void setManufacturingDistrict(String manufacturingDistrict) {
        this.manufacturingDistrict = manufacturingDistrict;
    }

    public String getManufacturingDistrictName() {
        return DictionaryStorage.get(ManufacturingDistrict.class.getName(), this.getManufacturingDistrict()).getName();
    }

    public void setManufacturingDistrictName(String manufacturingDistrictName) {
        this.manufacturingDistrictName = manufacturingDistrictName;
    }

    public BigDecimal getDistanceFreewayEntrance() {
        return distanceFreewayEntrance;
    }

    public void setDistanceFreewayEntrance(BigDecimal distanceFreewayEntrance) {
        this.distanceFreewayEntrance = distanceFreewayEntrance;
    }

    public Boolean getHasPublicTraffic() {
        return hasPublicTraffic;
    }

    public void setHasPublicTraffic(Boolean hasPublicTraffic) {
        this.hasPublicTraffic = hasPublicTraffic;
    }

    public BigDecimal getDistanceMainStreet() {
        return distanceMainStreet;
    }

    public void setDistanceMainStreet(BigDecimal distanceMainStreet) {
        this.distanceMainStreet = distanceMainStreet;
    }

    public Integer getLaneQuantity() {
        return laneQuantity;
    }

    public void setLaneQuantity(Integer laneQuantity) {
        this.laneQuantity = laneQuantity;
    }

    public BigDecimal getNearbyPrice() {
        return nearbyPrice;
    }

    public void setNearbyPrice(BigDecimal nearbyPrice) {
        this.nearbyPrice = nearbyPrice;
    }

    public Boolean getHasNewPlant() {
        return hasNewPlant;
    }

    public void setHasNewPlant(Boolean hasNewPlant) {
        this.hasNewPlant = hasNewPlant;
    }

    public String getPlantRemark() {
        return plantRemark;
    }

    public void setPlantRemark(String plantRemark) {
        this.plantRemark = plantRemark;
    }

    public String getPlantDecoration() {
        return plantDecoration;
    }

    public void setPlantDecoration(String plantDecoration) {
        this.plantDecoration = plantDecoration;
    }

    public String getPlantDecorationName() {
        return DictionaryStorage.get(PlantDecoration.class.getName(), this.getPlantDecoration()).getName();
    }

    public void setPlantDecorationName(String plantDecorationName) {
        this.plantDecorationName = plantDecorationName;
    }

    public String getDecorationRemark() {
        return decorationRemark;
    }

    public void setDecorationRemark(String decorationRemark) {
        this.decorationRemark = decorationRemark;
    }

    public String getPlantElectricity() {
        return plantElectricity;
    }

    public void setPlantElectricity(String plantElectricity) {
        this.plantElectricity = plantElectricity;
    }

    public String getPlantElectricityName() {
        return DictionaryStorage.get(PlantElectricity.class.getName(), this.getPlantElectricity()).getName();
    }

    public void setPlantElectricityName(String plantElectricityName) {
        this.plantElectricityName = plantElectricityName;
    }

    public String getElectricityRemark() {
        return electricityRemark;
    }

    public void setElectricityRemark(String electricityRemark) {
        this.electricityRemark = electricityRemark;
    }

    public BigDecimal getParkArea() {
        return parkArea;
    }

    public void setParkArea(BigDecimal parkArea) {
        this.parkArea = parkArea;
    }

    public BigDecimal getParkBuildingArea() {
        return parkBuildingArea;
    }

    public void setParkBuildingArea(BigDecimal parkBuildingArea) {
        this.parkBuildingArea = parkBuildingArea;
    }

    public Integer getSingleFloorQuantity() {
        return singleFloorQuantity;
    }

    public void setSingleFloorQuantity(Integer singleFloorQuantity) {
        this.singleFloorQuantity = singleFloorQuantity;
    }

    public BigDecimal getSingleFloorArea() {
        return singleFloorArea;
    }

    public void setSingleFloorArea(BigDecimal singleFloorArea) {
        this.singleFloorArea = singleFloorArea;
    }

    public BigDecimal getSingleFloorHeight() {
        return singleFloorHeight;
    }

    public void setSingleFloorHeight(BigDecimal singleFloorHeight) {
        this.singleFloorHeight = singleFloorHeight;
    }

    public String getSingleFloorRemark() {
        return singleFloorRemark;
    }

    public void setSingleFloorRemark(String singleFloorRemark) {
        this.singleFloorRemark = singleFloorRemark;
    }

    public Integer getMoreFloorQuantity() {
        return moreFloorQuantity;
    }

    public void setMoreFloorQuantity(Integer moreFloorQuantity) {
        this.moreFloorQuantity = moreFloorQuantity;
    }

    public BigDecimal getMoreFloorArea() {
        return moreFloorArea;
    }

    public void setMoreFloorArea(BigDecimal moreFloorArea) {
        this.moreFloorArea = moreFloorArea;
    }

    public BigDecimal getMoreFloorHeight() {
        return moreFloorHeight;
    }

    public void setMoreFloorHeight(BigDecimal moreFloorHeight) {
        this.moreFloorHeight = moreFloorHeight;
    }

    public BigDecimal getMoreFloorRemark() {
        return moreFloorRemark;
    }

    public void setMoreFloorRemark(BigDecimal moreFloorRemark) {
        this.moreFloorRemark = moreFloorRemark;
    }

    public Integer getMoreFloorElevatorQuantity() {
        return moreFloorElevatorQuantity;
    }

    public void setMoreFloorElevatorQuantity(Integer moreFloorElevatorQuantity) {
        this.moreFloorElevatorQuantity = moreFloorElevatorQuantity;
    }

    public String getElevatorLocation() {
        return elevatorLocation;
    }

    public void setElevatorLocation(String elevatorLocation) {
        this.elevatorLocation = elevatorLocation;
    }

    public Integer getOfficeBuildingQuantity() {
        return officeBuildingQuantity;
    }

    public void setOfficeBuildingQuantity(Integer officeBuildingQuantity) {
        this.officeBuildingQuantity = officeBuildingQuantity;
    }

    public BigDecimal getOfficeBuildingArea() {
        return officeBuildingArea;
    }

    public void setOfficeBuildingArea(BigDecimal officeBuildingArea) {
        this.officeBuildingArea = officeBuildingArea;
    }

    public BigDecimal getOfficeBuildingHeight() {
        return officeBuildingHeight;
    }

    public void setOfficeBuildingHeight(BigDecimal officeBuildingHeight) {
        this.officeBuildingHeight = officeBuildingHeight;
    }

    public String getOfficeBuildingRemark() {
        return officeBuildingRemark;
    }

    public void setOfficeBuildingRemark(String officeBuildingRemark) {
        this.officeBuildingRemark = officeBuildingRemark;
    }

    public Integer getOtherBuildingQuantity() {
        return otherBuildingQuantity;
    }

    public void setOtherBuildingQuantity(Integer otherBuildingQuantity) {
        this.otherBuildingQuantity = otherBuildingQuantity;
    }

    public BigDecimal getOtherBuildingArea() {
        return otherBuildingArea;
    }

    public void setOtherBuildingArea(BigDecimal otherBuildingArea) {
        this.otherBuildingArea = otherBuildingArea;
    }

    public BigDecimal getOtherBuildingRate() {
        return otherBuildingRate;
    }

    public void setOtherBuildingRate(BigDecimal otherBuildingRate) {
        this.otherBuildingRate = otherBuildingRate;
    }

    public String getOtherBuildingRemark() {
        return otherBuildingRemark;
    }

    public void setOtherBuildingRemark(String otherBuildingRemark) {
        this.otherBuildingRemark = otherBuildingRemark;
    }

    public BigDecimal getVacancySoilArea() {
        return vacancySoilArea;
    }

    public void setVacancySoilArea(BigDecimal vacancySoilArea) {
        this.vacancySoilArea = vacancySoilArea;
    }

    public String getVacancySoilRemark() {
        return vacancySoilRemark;
    }

    public void setVacancySoilRemark(String vacancySoilRemark) {
        this.vacancySoilRemark = vacancySoilRemark;
    }

    public BigDecimal getTotalElectricity() {
        return totalElectricity;
    }

    public void setTotalElectricity(BigDecimal totalElectricity) {
        this.totalElectricity = totalElectricity;
    }

    public String getFirefightingLevel() {
        return firefightingLevel;
    }

    public void setFirefightingLevel(String firefightingLevel) {
        this.firefightingLevel = firefightingLevel;
    }

    public String getFirefightingLevelName() {
        return DictionaryStorage.get(FirefightingLevel.class.getName(), this.getFirefightingLevel()).getName();
    }

    public void setFirefightingLevelName(String firefightingLevelName) {
        this.firefightingLevelName = firefightingLevelName;
    }

    public BigDecimal getEstateYear() {
        return estateYear;
    }

    public void setEstateYear(BigDecimal estateYear) {
        this.estateYear = estateYear;
    }

    public BigDecimal getContractYear() {
        return contractYear;
    }

    public void setContractYear(BigDecimal contractYear) {
        this.contractYear = contractYear;
    }

    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
    }

    public Boolean getHasHouseCertificate() {
        return hasHouseCertificate;
    }

    public void setHasHouseCertificate(Boolean hasHouseCertificate) {
        this.hasHouseCertificate = hasHouseCertificate;
    }

    public Boolean getHasEiaCertificate() {
        return hasEiaCertificate;
    }

    public void setHasEiaCertificate(Boolean hasEiaCertificate) {
        this.hasEiaCertificate = hasEiaCertificate;
    }

    public Boolean getHasSewageCertificate() {
        return hasSewageCertificate;
    }

    public void setHasSewageCertificate(Boolean hasSewageCertificate) {
        this.hasSewageCertificate = hasSewageCertificate;
    }

    public Boolean getHasFirefightingCertificate() {
        return hasFirefightingCertificate;
    }

    public void setHasFirefightingCertificate(Boolean hasFirefightingCertificate) {
        this.hasFirefightingCertificate = hasFirefightingCertificate;
    }

    public String getCertificateRemark() {
        return certificateRemark;
    }

    public void setCertificateRemark(String certificateRemark) {
        this.certificateRemark = certificateRemark;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getStrataFee() {
        return strataFee;
    }

    public void setStrataFee(BigDecimal strataFee) {
        this.strataFee = strataFee;
    }

    public BigDecimal getFreeRentDate() {
        return freeRentDate;
    }

    public void setFreeRentDate(BigDecimal freeRentDate) {
        this.freeRentDate = freeRentDate;
    }

    public String getPayRule() {
        return payRule;
    }

    public void setPayRule(String payRule) {
        this.payRule = payRule;
    }

    public String getPayRuleName() {
        return DictionaryStorage.get(PayRule.class.getName(), this.getPayRule()).getName();
    }

    public void setPayRuleName(String payRuleName) {
        this.payRuleName = payRuleName;
    }

    public String getIncreaseRule() {
        return increaseRule;
    }

    public void setIncreaseRule(String increaseRule) {
        this.increaseRule = increaseRule;
    }

    public String getIncreaseRuleName() {
        return DictionaryStorage.get(IncreaseRule.class.getName(), this.getIncreaseRule()).getName();
    }

    public void setIncreaseRuleName(String increaseRuleName) {
        this.increaseRuleName = increaseRuleName;
    }

    public String getTaxRequest() {
        return taxRequest;
    }

    public void setTaxRequest(String taxRequest) {
        this.taxRequest = taxRequest;
    }

    public String getTaxRequestName() {
        return DictionaryStorage.get(TaxRequest.class.getName(), this.getTaxRequest()).getName();
    }

    public void setTaxRequestName(String taxRequestName) {
        this.taxRequestName = taxRequestName;
    }

    public BigDecimal getTaxes() {
        return taxes;
    }

    public void setTaxes(BigDecimal taxes) {
        this.taxes = taxes;
    }

    public String getRestrictIndustry() {
        return restrictIndustry;
    }

    public void setRestrictIndustry(String restrictIndustry) {
        this.restrictIndustry = restrictIndustry;
    }

    public String getShowVideo() {
        return showVideo;
    }

    public void setShowVideo(String showVideo) {
        this.showVideo = showVideo;
    }

    public String getShowPicture() {
        return showPicture;
    }

    public void setShowPicture(String showPicture) {
        this.showPicture = showPicture;
    }

    public String getExpEmpCode() {
        return expEmpCode;
    }

    public void setExpEmpCode(String expEmpCode) {
        this.expEmpCode = expEmpCode;
    }

    public String getExpEmpName() {
        return expEmpName;
    }

    public void setExpEmpName(String expEmpName) {
        this.expEmpName = expEmpName;
    }

    public String getExpEmpPhone() {
        return expEmpPhone;
    }

    public void setExpEmpPhone(String expEmpPhone) {
        this.expEmpPhone = expEmpPhone;
    }

    public String getExpEmpCpyName() {
        return expEmpCpyName;
    }

    public void setExpEmpCpyName(String expEmpCpyName) {
        this.expEmpCpyName = expEmpCpyName;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getAssessResult() {
        return assessResult;
    }

    public void setAssessResult(String assessResult) {
        this.assessResult = assessResult;
    }

    public String getExpStatus() {
        return expStatus;
    }

    public void setExpStatus(String expStatus) {
        this.expStatus = expStatus;
    }

    public String getExpStatusName() {
        return DictionaryStorage.get(ExpStatus.class.getName(), this.getExpStatus()).getName();
    }

    public void setExpStatusName(String expStatusName) {
        this.expStatusName = expStatusName;
    }
}
