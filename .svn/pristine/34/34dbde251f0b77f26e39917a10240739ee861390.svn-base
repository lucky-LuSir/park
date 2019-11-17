package com.kfwy.park.ati.customer.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.customer.enums.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by davidcun on 2018/5/22.
 * 新增客户
 */
public class CustomerEntity extends BaseEntity {
    /**
     * 客户编码
     */
    private String cusCode;

    /**
     * 当前业务人员编号
     */
    private String empCode;
    /**
     * 当前业务人员名字
     */
    private String empName;


    /**
     * 绑定园区编号
     */
    private String pkCode;

    /**
     * 园区名称
     */
    private String pkName;

    /**
     * 客户类型（字典）
     * @see CustomerType
     */
    private String customerType;

    /**
     * 客户类型名称（字典）
     */
    private String customerTypeName;

    /**
     * 公司名称
     */
    private String companyName;



    /**
     * 行业分类（字典）
     * @see TradeType
     */
    private String trade;

    /**
     * 行业分类名称（字典）
     */
    private String tradeName;



    /**
     * 公司产品
     */
    private String companyProduct;

    /**
     * 客户状态(字典)
     * @see CusStatus
     */
    private String cusStatus;

    /**
     * 客户状态(字典)
     */
    private String cusStatusName;

    /**
     * 客户渠道（字典）
     * @see DitchType
     */
    private String ditch;

    /**
     * 客户渠道名称（字典）
     */
    private String ditchName;



    /*------------------------客户需求信息--------------------------*/




    /**
     * 主面积
     */
    private BigDecimal mainArea;
    /**
     * 入驻时间
     */
    private Date entryTime;



    /**
     * 电量要求
     */
    private String electricAsk;

    /**
     * 楼层需求（字典）
     * @see FloorAsk
     */
    private String floorAsk;

    /**
     * 楼层需求名称（字典）
     */
    private String floorAskName;

    /**
     * 层高要求
     */
    private String floorHeightAsk;
    /**
     * 用途
     * @see UseTypeCus
     */
    private String useType;
    /**
     * 用途名称
     */
    private String useTypeName;


    /**
     * 找房原因（字典）
     * @see ReasonType
     */
    private String reason;

    /**
     * 找房原因名称（字典）
     */
    private String reasonName;

    /**
     * 是否需要环评
     */
    private Boolean hasEia;

    /**
     * 是否需要产证
     */
    private Boolean hasCertify;

    /**
     * 是否需要办公区(办公楼)
     */
    private Boolean hasOfficeBuild;

    /**
     * 是否有食堂
     */
    private Boolean hasCanteen;

    /**
     * 是否有超市
     */
    private Boolean hasSupermarket;

    /**
     * 是否有雨棚
     */
    private Boolean hasCanopy;

    /**
     * 有无卸货平台
     */
    private boolean hasLoadDock;

    /**
     * 有无充电桩
     */
    private boolean hasChargingPile;

    /**
     * 有无排污证
     */
    private boolean hasSewageCertify;

    /**
     * 更多需求描述(客户关系问题)
     */
    private String moreDepict;


    /*--------------------------------联系人信息---------------------------------*/

    /**
     * 主联系人电话
     */
    private String mainContactPhone;

    /**
     * 客户座机 2018-08-14 添加
     */
    private String landlinePhone;

    /**
     * 主联系人姓名
     */
    private String mainContactName;

    /**
     * 主联系人性别
     */
    private String mainContactSex;

    /**
     * 主联系人性别(字典)
     * @see com.kfwy.park.ati.customer.enums.ContactSexName
     */
    private String mainContactSexName;

    /**
     * 主联系人职务名称
     */
    private String mainContactJobName;

    /*--------------------------------子List字段---------------------------------*/

    /**
     * 更多联系人列表
     */
    private List<CustomerRelateEntity> contactList;


    /**
     * 客户需求区域列表
     */
    private List<CustomerNeedAreaEntity> needAreaList;

    /**
     * 渠道总数统计（客户来源）
     */
    private Integer ditchCount;

    /**
     * 渠道复看总数统计（客户来源）
     */
    private Integer reWatchCount;

    /**
     * 职员招商总数
     */
    private Integer empCount;

    /*** 中介模块(使用中)xpp 2018-08-22******/

    /*--------------------------------中介信息-------------------------------*/

    /**
     * 中介联系人编码
     */
    private String agencyPersonCode;

    /**
     * 中介公司编码
     */
    private String agencyCpyCode;

    /**
     * 中介公司名称
     */
    private String agencyCpyName;

    /**
     * 中介联系人姓名
     */
    private String agencyPersonName;

    /**
     * 中介联系人电话
     */
    private String agencyPersonPhone;

    /**
     * 中介联系人职务
     */
    private String agencyPersonJob;

    /**
     * 中介联系人性别
     */
    private String agencyPersonSex;

    /**
     * 中介联系人性别(字典) -
     * @see com.kfwy.park.ati.customer.enums.ContactSexName
     */
    private String agencyPersonSexName;

    /*--------------------------------中介信息 end-------------------------------*/

    /*--------------------------------渠道信息(不存数据库) start-------------------------------*/
    /**
     * 渠道编码
     */
    private String channelCode;
    /**
     * 渠道类型
     * @see ChannelStatus
     */
    private String channelStatus;
    /**
     * 渠道类型名称
     */
    private String channelStatusName;
    /**
     * 联系人姓名
     */
    private String channelUserName;
    /**
     * 联系人电话
     */
    private String channelPhone;
    /**
     * 联系人性别
     */
    private String channelSex;
    /**
     * 联系人职务
     */
    private String channelPost;

    /**
     * 公司编码
     */
    private String channelCompanyCode;
    /**
     * 单位名称
     */
    private String channelCpyName;
    /**
     * 单位描述
     */
    private String channelCpyRemark;
    /**
     * 单位地址
     */
    private String channelCpyAddress;

    /**
     * 附件
     */
    private String attachment;

    /*--------------------------------附加字段---------------------------------------------*/
    /**
     * 统计的数量
     */
    private Integer counter;

    /*--------------------------------渠道信息(不存数据库) end-------------------------------*/




    /*--------------------------------不用字段-start--------------------------------*/

    /**
     * 行业性质,暂时不用 2018-6-21  luming -
     */
    private String tradeNature;

    /**
     * 是否需要注册  (暂时不用  2018-7-5) -
     */
    private Boolean hasRegister;

    /**
     * 是否需要装修  (暂时不用  2018-7-5) -
     */
    private Boolean hasDecorate;

    /**
     * 是否接受公摊  (暂时不用  2018-7-5) -
     */
    private Boolean hasPubBooth;

    /**
     * 最小面积 暂时不用 xpp 2018-07-30 -
     */
    private BigDecimal minArea;

    /**
     * 最大面积 暂时不用 xpp 2018-07-30 -
     */
    private BigDecimal maxArea;

    /**
     * 最小期望价格 不用 xpp 2018-07-30 -
     */
    private BigDecimal minHopePrice;

    /**
     * 最大期望价格  不用 xpp 2018-07-30 -
     */
    private BigDecimal maxHopePrice;

    /**
     * 资金账户 不用 xpp 2018-07-30 -
     */
    private String fundsAccount;

    /**
     * 主联系人职务(角色)(字典),暂时不用  2018-6-21  luming -
     * @see ContactJob
     */
    private String mainContactJob;



    /*=============================================字段分割线=============================================*/

    public String getCusCode() {
        return cusCode;
    }

    public void setCusCode(String cusCode) {
        this.cusCode = cusCode;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTradeNature() {
        return tradeNature;
    }

    public void setTradeNature(String tradeNature) {
        this.tradeNature = tradeNature;
    }

    public String getCompanyProduct() {
        return companyProduct;
    }

    public void setCompanyProduct(String companyProduct) {
        this.companyProduct = companyProduct;
    }

    public String getDitch() {
        return ditch;
    }

    public void setDitch(String ditch) {
        this.ditch = ditch;
    }

    public String getDitchName() {
        return DictionaryStorage.get(DitchType.class.getName(),this.getDitch()).getName();
    }

    public void setDitchName(String ditchName) {
        this.ditchName = ditchName;
    }

    public BigDecimal getMinArea() {
        return minArea;
    }

    public void setMinArea(BigDecimal minArea) {
        this.minArea = minArea;
    }

    public BigDecimal getMaxArea() {
        return maxArea;
    }

    public void setMaxArea(BigDecimal maxArea) {
        this.maxArea = maxArea;
    }

    public BigDecimal getMainArea() {
        return mainArea;
    }

    public void setMainArea(BigDecimal mainArea) {
        this.mainArea = mainArea;
    }

    public BigDecimal getMinHopePrice() {
        return minHopePrice;
    }

    public void setMinHopePrice(BigDecimal minHopePrice) {
        this.minHopePrice = minHopePrice;
    }

    public BigDecimal getMaxHopePrice() {
        return maxHopePrice;
    }

    public void setMaxHopePrice(BigDecimal maxHopePrice) {
        this.maxHopePrice = maxHopePrice;
    }

    public String getElectricAsk() {
        return electricAsk;
    }

    public void setElectricAsk(String electricAsk) {
        this.electricAsk = electricAsk;
    }

    public String getFloorAsk() {
        return floorAsk;
    }

    public void setFloorAsk(String floorAsk) {
        this.floorAsk = floorAsk;
    }

    public String getFloorAskName() {
        return DictionaryStorage.get(FloorAsk.class.getName(),this.getFloorAsk()).getName();
    }

    public void setFloorAskName(String floorAskName) {
        this.floorAskName = floorAskName;
    }

    public String getFloorHeightAsk() {
        return floorHeightAsk;
    }

    public void setFloorHeightAsk(String floorHeightAsk) {
        this.floorHeightAsk = floorHeightAsk;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReasonName() {
        return DictionaryStorage.get(ReasonType.class.getName(),this.getReason()).getName();
    }

    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    public Boolean getHasEia() {
        return hasEia;
    }

    public void setHasEia(Boolean hasEia) {
        this.hasEia = hasEia;
    }

    public Boolean getHasRegister() {
        return hasRegister;
    }

    public void setHasRegister(Boolean hasRegister) {
        this.hasRegister = hasRegister;
    }

    public Boolean getHasCertify() {
        return hasCertify;
    }

    public void setHasCertify(Boolean hasCertify) {
        this.hasCertify = hasCertify;
    }

    public Boolean getHasOfficeBuild() {
        return hasOfficeBuild;
    }

    public void setHasOfficeBuild(Boolean hasOfficeBuild) {
        this.hasOfficeBuild = hasOfficeBuild;
    }

    public String getMoreDepict() {
        return moreDepict;
    }

    public void setMoreDepict(String moreDepict) {
        this.moreDepict = moreDepict;
    }

    public String getAgencyPersonCode() {
        return agencyPersonCode;
    }

    public void setAgencyPersonCode(String agencyPersonCode) {
        this.agencyPersonCode = agencyPersonCode;
    }

    public String getAgencyCpyCode() {
        return agencyCpyCode;
    }

    public void setAgencyCpyCode(String agencyCpyCode) {
        this.agencyCpyCode = agencyCpyCode;
    }

    public String getAgencyCpyName() {
        return agencyCpyName;
    }

    public void setAgencyCpyName(String agencyCpyName) {
        this.agencyCpyName = agencyCpyName;
    }

    public String getAgencyPersonName() {
        return agencyPersonName;
    }

    public void setAgencyPersonName(String agencyPersonName) {
        this.agencyPersonName = agencyPersonName;
    }

    public String getAgencyPersonPhone() {
        return agencyPersonPhone;
    }

    public void setAgencyPersonPhone(String agencyPersonPhone) {
        this.agencyPersonPhone = agencyPersonPhone;
    }

    public String getLandlinePhone() {
        return landlinePhone;
    }

    public void setLandlinePhone(String landlinePhone) {
        this.landlinePhone = landlinePhone;
    }

    public String getAgencyPersonJob() {
        return agencyPersonJob;
    }

    public void setAgencyPersonJob(String agencyPersonJob) {
        this.agencyPersonJob = agencyPersonJob;
    }

    public String getFundsAccount() {
        return fundsAccount;
    }

    public void setFundsAccount(String fundsAccount) {
        this.fundsAccount = fundsAccount;
    }

    public String getMainContactPhone() {
        return mainContactPhone;
    }

    public void setMainContactPhone(String mainContactPhone) {
        this.mainContactPhone = mainContactPhone;
    }

    public String getMainContactName() {
        return mainContactName;
    }

    public void setMainContactName(String mainContactName) {
        this.mainContactName = mainContactName;
    }

    public String getMainContactSex() {
        return mainContactSex;
    }

    public void setMainContactSex(String mainContactSex) {
        this.mainContactSex = mainContactSex;
    }

    public String getMainContactJob() {
        return mainContactJob;
    }

    public void setMainContactJob(String mainContactJob) {
        this.mainContactJob = mainContactJob;
    }

    public String getMainContactJobName() {
        return DictionaryStorage.get(ContactJob.class.getName(),this.getMainContactJob()).getName();
    }

    public void setMainContactJobName(String mainContactJobName) {
        this.mainContactJobName = mainContactJobName;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public void setEntryTime(Date entryTime) {
        this.entryTime = entryTime;
    }

    public Boolean getHasDecorate() {
        return hasDecorate;
    }

    public void setHasDecorate(Boolean hasDecorate) {
        this.hasDecorate = hasDecorate;
    }

    public Boolean getHasPubBooth() {
        return hasPubBooth;
    }

    public void setHasPubBooth(Boolean hasPubBooth) {
        this.hasPubBooth = hasPubBooth;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getCustomerTypeName() {
        return DictionaryStorage.get(CustomerType.class.getName(),this.getCustomerType()).getName();
    }

    public void setCustomerTypeName(String customerTypeName) {
        this.customerTypeName = customerTypeName;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getTradeName() {
        return DictionaryStorage.get(TradeType.class.getName(),this.getTrade()).getName();
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getCusStatus() {
        return cusStatus;
    }

    public void setCusStatus(String cusStatus) {
        this.cusStatus = cusStatus;
    }

    public String getCusStatusName() {
        return DictionaryStorage.get(CusStatus.class.getName(),this.getCusStatus()).getName();
    }

    public Boolean getHasCanteen() {
        return hasCanteen;
    }

    public void setHasCanteen(Boolean hasCanteen) {
        this.hasCanteen = hasCanteen;
    }

    public Boolean getHasSupermarket() {
        return hasSupermarket;
    }

    public void setHasSupermarket(Boolean hasSupermarket) {
        this.hasSupermarket = hasSupermarket;
    }

    public Boolean getHasCanopy() {
        return hasCanopy;
    }

    public void setHasCanopy(Boolean hasCanopy) {
        this.hasCanopy = hasCanopy;
    }

    public boolean isHasLoadDock() {
        return hasLoadDock;
    }

    public void setHasLoadDock(boolean hasLoadDock) {
        this.hasLoadDock = hasLoadDock;
    }

    public boolean isHasChargingPile() {
        return hasChargingPile;
    }

    public void setHasChargingPile(boolean hasChargingPile) {
        this.hasChargingPile = hasChargingPile;
    }

    public boolean isHasSewageCertify() {
        return hasSewageCertify;
    }

    public void setHasSewageCertify(boolean hasSewageCertify) {
        this.hasSewageCertify = hasSewageCertify;
    }

    public void setCusStatusName(String cusStatusName) {
        this.cusStatusName = cusStatusName;
    }

    public List<CustomerRelateEntity> getContactList() {
        return contactList;
    }

    public void setContactList(List<CustomerRelateEntity> contactList) {
        this.contactList = contactList;
    }

    public List<CustomerNeedAreaEntity> getNeedAreaList() {
        return needAreaList;
    }

    public void setNeedAreaList(List<CustomerNeedAreaEntity> needAreaList) {
        this.needAreaList = needAreaList;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getAgencyPersonSexName() {
        return DictionaryStorage.get(ContactSexName.class.getName(),this.getAgencyPersonSex()).getName();
    }

    public void setAgencyPersonSexName(String agencyPersonSexName) {
        this.agencyPersonSexName = agencyPersonSexName;
    }

    public String getAgencyPersonSex() {
        return agencyPersonSex;
    }

    public void setAgencyPersonSex(String agencyPersonSex) {
        this.agencyPersonSex = agencyPersonSex;
    }

    public String getMainContactSexName() {
        return DictionaryStorage.get(ContactSexName.class.getName(),this.getMainContactSex()).getName();
    }

    public void setMainContactSexName(String mainContactSexName) {
        this.mainContactSexName = mainContactSexName;
    }


    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getUseTypeName() {
        return DictionaryStorage.get(UseTypeCus.class.getName(),this.getUseType()).getName();
    }

    public void setUseTypeName(String useTypeName) {
        this.useTypeName = useTypeName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

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

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelStatus() {
        return channelStatus;
    }

    public void setChannelStatus(String channelStatus) {
        this.channelStatus = channelStatus;
    }

    public String getChannelStatusName() {
        return DictionaryStorage.get(ChannelStatus.class.getName(), this.getChannelStatus()).getName();
    }

    public void setChannelStatusName(String channelStatusName) {
        this.channelStatusName = channelStatusName;
    }

    public String getChannelUserName() {
        return channelUserName;
    }

    public void setChannelUserName(String channelUserName) {
        this.channelUserName = channelUserName;
    }

    public String getChannelPhone() {
        return channelPhone;
    }

    public void setChannelPhone(String channelPhone) {
        this.channelPhone = channelPhone;
    }

    public String getChannelSex() {
        return channelSex;
    }

    public void setChannelSex(String channelSex) {
        this.channelSex = channelSex;
    }

    public String getChannelPost() {
        return channelPost;
    }

    public void setChannelPost(String channelPost) {
        this.channelPost = channelPost;
    }

    public String getChannelCompanyCode() {
        return channelCompanyCode;
    }

    public void setChannelCompanyCode(String channelCompanyCode) {
        this.channelCompanyCode = channelCompanyCode;
    }

    public String getChannelCpyName() {
        return channelCpyName;
    }

    public void setChannelCpyName(String channelCpyName) {
        this.channelCpyName = channelCpyName;
    }

    public String getChannelCpyRemark() {
        return channelCpyRemark;
    }

    public void setChannelCpyRemark(String channelCpyRemark) {
        this.channelCpyRemark = channelCpyRemark;
    }

    public String getChannelCpyAddress() {
        return channelCpyAddress;
    }

    public void setChannelCpyAddress(String channelCpyAddress) {
        this.channelCpyAddress = channelCpyAddress;
    }

    public Integer getDitchCount() {
        return ditchCount;
    }

    public void setDitchCount(Integer ditchCount) {
        this.ditchCount = ditchCount;
    }

    public Integer getEmpCount() {
        return empCount;
    }

    public void setEmpCount(Integer empCount) {
        this.empCount = empCount;
    }

    public Integer getReWatchCount() {
        return reWatchCount;
    }

    public void setReWatchCount(Integer reWatchCount) {
        this.reWatchCount = reWatchCount;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }
}
