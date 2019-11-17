package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.*;
import com.kfwy.park.pro.house.building.enums.UseType;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/*
 * @Description 合同实体类
 * @Auth liuzhengyang
 * @Date 2018/6/14 10:21
 * @Version 1.0
 * @Param
 * @return
 */
public class ContractEntity extends BaseEntity {

    /**
     * 合同编号
     */
    private String ctCode;
    /**
     * 续签的合同编号
     */
    private String renewedContractCode;
    /**
     * 合同状态
     */
    private String ctStatus;
    /**
     * 合同状态名称
     *
     * @see CtStatus
     */
    private String ctStatusName;
    /**
     * 合同开始时间，同时代表了交付日期，代表了开始收水费、电费、物业费的日期
     */

    /**
     * 合同驳回原因
     * 数据库无此字段，用于给合同操作表赋值
     */
    private String operateRemark;

    private Date contractStartTime;
    /**
     * 合同结束时间
     */
    private Date contractEndTime;
    /**
     * 免租期天数
     */
    private Integer freeRentDays;
    /**
     * 免租期开始时间
     */
    private Date freeRentStartTime;
    /**
     * 免租期结束时间
     */
    private Date freeRentEndTime;
    /**
     * 开始计租日期(暂时不使用)
     */
    @Deprecated
    private Date startRentDate;
    /**
     * 合同编号
     */
    private String ctName;
    /**
     * 提前交租
     */
    private Integer advanceDateCount;
    /**
     * 签约日期
     */
    private Date contractDate;
    /**
     * 用途
     *
     * @see UseType
     */
    private String useType;
    /**
     * 用途名称
     */
    private String useTypeName;


    /*------------------------客户信息--------------------------*/
    /**
     * 客户编码
     */
    private String cusCode;
    /**
     * 公司名称
     */
    private String companyName;
    /**
     * 联系人姓名
     */
    private String relateName;
    /**
     * 联系人电话
     */
    private String relatePhone;
    /**
     * 客户座机 2018-08-14 添加
     */
    private String landlinePhone;
    /**
     * 联系人职务
     */
    private String relatePosition;
    /**
     * 所属行业
     */
    private String trade;
    /**
     * 所属行业名称
     */
    private String tradeName;
    /**
     * 公司产品
     */
    private String companyProduct;
    /**
     * 入驻园区
     */
    private String pkCode;
    /**
     * 入驻园区
     */
    private String pkName;
    /**
     * 签约人编码
     * contractor_code
     */
    private String contractorCode;
    /**
     * 签约人
     */
    private String contractor;
    /**
     * 签约联系电话
     */
    private String contractorPhone;
    /**
     * 签约部门编码
     * contractor_dept_code
     */
    private String contractorDeptCode;
    /**
     * 签约部门
     */
    private String contractorDept;
    /**
     * 甲方公司
     */
    private String partyName;
    /**
     * 客户类型(1是公司,2是个人)
     */
    private String customerType;
    /**
     * 联系人性别(1是男,2是女)
     */
    private String relateSex;

    /*------------------------租房信息--------------------------*/
    /**
     * 房源列表
     */
    private List<ContractHouseEntity> contractHouseList;
    /**
     * 房源地址
     */
    private String houseAddress;
    /**
     * 交付日期(暂时不使用)
     */
    @Deprecated
    private Date deliveryDate;
    /**
     * 免租期前后
     *
     * @see FreeRentAround
     */
    private String freeRentAround;
    /**
     * 免租期前后
     */
    private String freeRentAroundName;



    /*------------------------租金信息--------------------------*/
    /**
     * 总出租面积（含公摊）
     */
    private BigDecimal totalRentalArea;
    /**
     * 公摊比例
     */
    private String publicOfficeProportion;
    /**
     * 是否详细递增
     *
     * @see IncDetailType
     */
    private String incDetailType;
    /**
     * 是否详细递增名称
     */
    private String incDetailTypeName;
    /**
     * 租金递增比例
     */
    private BigDecimal rentIncreaseProportion;
    /**
     * 递增周期(单位:年)
     */
    private Integer increasingCycle;
    /**
     * 详情递增容器
     */
    private String incDetailCase;
    /**
     * 详情递增列表
     */
    private List<RentIncrementEntity> rentIncrementList;
    /**
     * 租金是否含税
     */
    private Boolean hasTax;
    /**
     * 发票税点
     *
     * @see TaxPoint
     */
    private String taxPoint;
    /**
     * 发票税点名称
     */
    private String taxPointName;
    /**
     * 付款规则(数字:付几个月)
     */
    private Integer payRule;
    /**
     * 实收押金
     */
    private BigDecimal deposit;
    /**
     * 租金应收列表
     */
    private List<RentReceiptEntity> rentReceiptList;
    /**
     * 租金合计/每月
     */
    private BigDecimal rentTotal;
    /**
     * 物业费合计/每月
     */
    private BigDecimal propertyTotal;

    /*------------------------押金信息--------------------------*/
    /**
     * 押金规则(数字:付几个月)
     */
    private Integer depositRule;
    /**
     * 应收押金金额
     */
    private BigDecimal receivableDeposit;
    /**
     * 押金状态
     * @see DepositStatus
     */
    private String depositStatus;
    /**
     * 押金状态名称
     */
    private String depositStatusName;
    /**
     * 电费押金
     */
    private BigDecimal eleDeposit;
    /**
     * 水费押金
     */
    private BigDecimal waterDeposit;

    /**
     * 首期支付房屋租金
     */
    private BigDecimal fpayHouseRent;
    /**
     * 首期支付物业费
     */
    private BigDecimal fpayPropertyPrice;
    /**
     * 首期支付保险费
     */
    private BigDecimal fpayInsurancePrice;
    /**
     * 首期支付工程押金
     */
    private BigDecimal fpayProjectDeposit;
    /**
     * 首期支付保证金
     */
    private BigDecimal fpayBond;



    /*------------------------物业费信息--------------------------*/
    /**
     * 物业费单价
     */
    private BigDecimal propertyUnitPrice;
    /**
     * 物业费单位
     */
    private String propertyUnit;

    /**
     * 物业费是否含票
     */
    private Boolean hasInvoiceByProperty;
    /**
     * 物业费几个月缴一次
     */
    private Integer monthCountByProperty;
    /**
     * 物业费开始计费日期
     */
    private Date billingDateByProperty;



    /*------------------------用电信息--------------------------*/
    /**
     * 总用电量
     */
    private BigDecimal totalElectricity;
    /**
     * 总用电量单位
     */
    private String totalElectricityUnit;
    /**
     * 基础电费单价
     */
    private BigDecimal basicElectricityUnitPrice;
    /**
     * 基础电费单价单位
     */
    private String basicElectricityUnit;
    /**
     * 每月基础电费
     */
    private BigDecimal basicElectricityPriceByMonth;
    /**
     * 加收损耗
     */
    private BigDecimal addLoss;
    /**
     * 电费应收列表
     */
    private List<ElectricityReceiptEntity> electricityReceiptList;
    /**
     * 电费是否含税
     */
    private Boolean elHasTax;

    /**
     * 新增电表集合
     */
    private List<ElectricityMeterEntity> meterList;

    /**
     * 电表名
     */
    private String eleMeterName;

    /**
     * 水表名
     */
    private String waterMeterName;


    /*------------------------水费信息--------------------------*/

    /**
     * 水费计费方式
     */
    private String waterBillingType;

    /**
     * 水费金额
     */
    private BigDecimal waterAmount;

    /**
     * 水费单价
     */
    private BigDecimal waterPrice;
    /**
     * 水费单价单位
     */
    private String waterUnit;
    /**
     * 水费应收列表
     */
    private List<WaterReceiptEntity> waterReceiptList;
    /**
     * 水表初始值
     */
    private BigDecimal initWater;
    /**
     * 水表是否含税
     */
    private Boolean waHasTax;
    /**
     * 水表集合
     */
    private List<WaterMeterEntity> waterMeterList;


    /*------------------------停车费信息--------------------------*/
    /**
     * 免费停车位个数
     */
    private Integer freeParkingCount;
    /**
     * 停车位单价
     */
    private BigDecimal parkingPrice;
    /**
     * 当前停车位个数（不含免）
     */
    private Integer parkingSpace;
    /**
     * 新增停车位时间
     */
    private Date parkingAddTime;
    /**
     * 停车费应收列表
     */
    private List<ParkingReceiptEntity> parkingReceiptList;
    /**
     *
     */
    private boolean paHasTax;

    /*------------------------应付中介费信息intermediary--------------------------*/

    /**
     * 合同编号
     */
    private String imContractCode;
    /**
     * 客户编号
     */
    private String imCusCode;
    /**
     * 客户名称
     */
    private String imCusName;
    /**
     * 中介公司
     */
    private String imCompany;
    /**
     * 联系人电话
     */
    private String imRelatePhone;
    /**
     * 收款账户
     */
    private String imReceiptAccount;
    /**
     * 收款账号
     */
    private String imReceiptNumber;
    /**
     * 开户银行
     */
    private String imBank;
    /**
     * 应付金额
     */
    private BigDecimal imPayableAmount;
    /**
     * 状态
     *
     * @see ImStatus
     */
    private String imStatus;
    /**
     * 状态名称
     */
    private String imStatusName;


    /**
     * 合同url
     */
    private String contractUrl;


    /*------------------------本期费用和下期费用所需字段--------------------------*/

    /**
     * 本期租金物业费current
     */
    private BigDecimal currentRentReceipt;
    /**
     * 租金物业费状态
     */
    private String rentReceiptPayStatus;
    /**
     * 本期电费
     */
    private BigDecimal currentElectricityReceipt;
    /**
     * 电费状态
     */
    private String electricityReceiptPayStatus;
    /**
     * 本期水费
     */
    private BigDecimal currentWaterReceipt;
    /**
     * 水费状态
     */
    private String waterReceiptPayStatus;
    /**
     * 本期停车费
     */
    private BigDecimal currentParkingReceipt;
    /**
     * 停车费状态
     */
    private String parkingReceiptPayStatus;
    /**
     * 下期租金物业费
     */
    private BigDecimal nextRentReceipt;

    /************************************************
     * 合同添加税收信息
     ***********************************************/

    /**
     * 产值
     */
    private BigDecimal outputValue;

    /**
     * 税收是否可落户当地
     */
    private Boolean hasRevenueBackLocal;

    /**
     * 税收金额
     */
    private BigDecimal taxTake;

    /**
     * 设备清单
     */
    private String equipmentList;

    /**
     * 设备价值
     */
    private BigDecimal equipmentValue;

    /**
     * 合同删除原因
     */
    private String deleteCause;

    /**
     * 删除类型
     * 1 合同重复录入
     * 2 合同录入错误
     */
    private String deleteType;

    /************************************************
     * 保险费用
     ***********************************************/

    /**
     * 保险费列表
     */
    private List<PremiumEntity> premiumReceiptList;


    /**
     * 合同修改记录
     * @return
     */
    private String contractEditRemark;

    /**
     *退租时间
     */
    private Date throwLeaseDate;

    /*===================================20180529字段==============================*/

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
    }

    public String getRenewedContractCode() {
        return renewedContractCode;
    }

    public void setRenewedContractCode(String renewedContractCode) {
        this.renewedContractCode = renewedContractCode;
    }

    public String getCtStatus() {
        return ctStatus;
    }

    public void setCtStatus(String ctStatus) {
        this.ctStatus = ctStatus;
    }

    public String getCtStatusName() {
        return DictionaryStorage.get(CtStatus.class.getName(), this.getCtStatus()).getName();
    }

    public void setCtStatusName(String ctStatusName) {
        this.ctStatusName = ctStatusName;
    }

    public Date getContractStartTime() {
        return contractStartTime;
    }

    public void setContractStartTime(Date contractStartTime) {
        this.contractStartTime = contractStartTime;
    }

    public Date getContractEndTime() {
        return contractEndTime;
    }

    public void setContractEndTime(Date contractEndTime) {
        this.contractEndTime = contractEndTime;
    }

    public Integer getFreeRentDays() {
        return freeRentDays;
    }

    public void setFreeRentDays(Integer freeRentDays) {
        this.freeRentDays = freeRentDays;
    }

    public Date getFreeRentStartTime() {
        return freeRentStartTime;
    }

    public void setFreeRentStartTime(Date freeRentStartTime) {
        this.freeRentStartTime = freeRentStartTime;
    }

    public Date getFreeRentEndTime() {
        return freeRentEndTime;
    }

    public void setFreeRentEndTime(Date freeRentEndTime) {
        this.freeRentEndTime = freeRentEndTime;
    }

    public Date getStartRentDate() {
        return startRentDate;
    }

    public void setStartRentDate(Date startRentDate) {
        this.startRentDate = startRentDate;
    }

    public String getCtName() {
        return ctName;
    }

    public void setCtName(String ctName) {
        this.ctName = ctName;
    }

    public Integer getAdvanceDateCount() {
        return advanceDateCount;
    }

    public void setAdvanceDateCount(Integer advanceDateCount) {
        this.advanceDateCount = advanceDateCount;
    }

    public Date getContractDate() {
        return contractDate;
    }

    public void setContractDate(Date contractDate) {
        this.contractDate = contractDate;
    }

    public String getUseType() {
        return useType;
    }

    public void setUseType(String useType) {
        this.useType = useType;
    }

    public String getUseTypeName() {
        return useTypeName;
    }

    public void setUseTypeName(String useTypeName) {
        this.useTypeName = useTypeName;
    }

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

    public String getRelateName() {
        return relateName;
    }

    public void setRelateName(String relateName) {
        this.relateName = relateName;
    }

    public String getRelatePhone() {
        return relatePhone;
    }

    public void setRelatePhone(String relatePhone) {
        this.relatePhone = relatePhone;
    }

    public String getLandlinePhone() {
        return landlinePhone;
    }

    public void setLandlinePhone(String landlinePhone) {
        this.landlinePhone = landlinePhone;
    }

    public String getRelatePosition() {
        return relatePosition;
    }

    public void setRelatePosition(String relatePosition) {
        this.relatePosition = relatePosition;
    }

    public String getTrade() {
        return trade;
    }

    public void setTrade(String trade) {
        this.trade = trade;
    }

    public String getTradeName() {
        return tradeName;
    }

    public void setTradeName(String tradeName) {
        this.tradeName = tradeName;
    }

    public String getCompanyProduct() {
        return companyProduct;
    }

    public void setCompanyProduct(String companyProduct) {
        this.companyProduct = companyProduct;
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

    public String getContractorCode() {
        return contractorCode;
    }

    public void setContractorCode(String contractorCode) {
        this.contractorCode = contractorCode;
    }

    public String getContractor() {
        return contractor;
    }

    public void setContractor(String contractor) {
        this.contractor = contractor;
    }

    public String getContractorPhone() {
        return contractorPhone;
    }

    public void setContractorPhone(String contractorPhone) {
        this.contractorPhone = contractorPhone;
    }

    public String getContractorDeptCode() {
        return contractorDeptCode;
    }

    public void setContractorDeptCode(String contractorDeptCode) {
        this.contractorDeptCode = contractorDeptCode;
    }

    public String getContractorDept() {
        return contractorDept;
    }

    public void setContractorDept(String contractorDept) {
        this.contractorDept = contractorDept;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getRelateSex() {
        return relateSex;
    }

    public void setRelateSex(String relateSex) {
        this.relateSex = relateSex;
    }

    public List<ContractHouseEntity> getContractHouseList() {
        return contractHouseList;
    }

    public void setContractHouseList(List<ContractHouseEntity> contractHouseList) {
        this.contractHouseList = contractHouseList;
    }

    public String getHouseAddress() {
        return houseAddress;
    }

    public void setHouseAddress(String houseAddress) {
        this.houseAddress = houseAddress;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getFreeRentAround() {
        return freeRentAround;
    }

    public void setFreeRentAround(String freeRentAround) {
        this.freeRentAround = freeRentAround;
    }

    public String getFreeRentAroundName() {
        return freeRentAroundName;
    }

    public void setFreeRentAroundName(String freeRentAroundName) {
        this.freeRentAroundName = freeRentAroundName;
    }

    public BigDecimal getTotalRentalArea() {
        return totalRentalArea;
    }

    public void setTotalRentalArea(BigDecimal totalRentalArea) {
        this.totalRentalArea = totalRentalArea;
    }

    public String getPublicOfficeProportion() {
        return publicOfficeProportion;
    }

    public void setPublicOfficeProportion(String publicOfficeProportion) {
        this.publicOfficeProportion = publicOfficeProportion;
    }

    public String getIncDetailType() {
        return incDetailType;
    }

    public void setIncDetailType(String incDetailType) {
        this.incDetailType = incDetailType;
    }

    public String getIncDetailTypeName() {
        return incDetailTypeName;
    }

    public void setIncDetailTypeName(String incDetailTypeName) {
        this.incDetailTypeName = incDetailTypeName;
    }

    public BigDecimal getRentIncreaseProportion() {
        return rentIncreaseProportion;
    }

    public void setRentIncreaseProportion(BigDecimal rentIncreaseProportion) {
        this.rentIncreaseProportion = rentIncreaseProportion;
    }

    public String getEleMeterName() {
        return eleMeterName;
    }

    public void setEleMeterName(String eleMeterName) {
        this.eleMeterName = eleMeterName;
    }

    public Integer getIncreasingCycle() {
        return increasingCycle;
    }

    public void setIncreasingCycle(Integer increasingCycle) {
        this.increasingCycle = increasingCycle;
    }

    public String getIncDetailCase() {
        return incDetailCase;
    }

    public void setIncDetailCase(String incDetailCase) {
        this.incDetailCase = incDetailCase;
    }

    public List<RentIncrementEntity> getRentIncrementList() {
        return rentIncrementList;
    }

    public void setRentIncrementList(List<RentIncrementEntity> rentIncrementList) {
        this.rentIncrementList = rentIncrementList;
    }

    public Boolean getHasTax() {
        return hasTax;
    }

    public void setHasTax(Boolean hasTax) {
        this.hasTax = hasTax;
    }

    public String getTaxPoint() {
        return taxPoint;
    }

    public void setTaxPoint(String taxPoint) {
        this.taxPoint = taxPoint;
    }

    public String getTaxPointName() {
        return taxPointName;
    }

    public void setTaxPointName(String taxPointName) {
        this.taxPointName = taxPointName;
    }

    public Integer getPayRule() {
        return payRule;
    }

    public void setPayRule(Integer payRule) {
        this.payRule = payRule;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public List<RentReceiptEntity> getRentReceiptList() {
        return rentReceiptList;
    }

    public void setRentReceiptList(List<RentReceiptEntity> rentReceiptList) {
        this.rentReceiptList = rentReceiptList;
    }

    public BigDecimal getRentTotal() {
        return rentTotal;
    }

    public void setRentTotal(BigDecimal rentTotal) {
        this.rentTotal = rentTotal;
    }

    public BigDecimal getPropertyTotal() {
        return propertyTotal;
    }

    public void setPropertyTotal(BigDecimal propertyTotal) {
        this.propertyTotal = propertyTotal;
    }

    public Integer getDepositRule() {
        return depositRule;
    }

    public void setDepositRule(Integer depositRule) {
        this.depositRule = depositRule;
    }

    public BigDecimal getReceivableDeposit() {
        return receivableDeposit;
    }

    public void setReceivableDeposit(BigDecimal receivableDeposit) {
        this.receivableDeposit = receivableDeposit;
    }

    public String getDepositStatus() {
        return depositStatus;
    }

    public void setDepositStatus(String depositStatus) {
        this.depositStatus = depositStatus;
    }

    public String getDepositStatusName() {
        return depositStatusName;
    }

    public void setDepositStatusName(String depositStatusName) {
        this.depositStatusName = depositStatusName;
    }

    public BigDecimal getEleDeposit() {
        return eleDeposit;
    }

    public void setEleDeposit(BigDecimal eleDeposit) {
        this.eleDeposit = eleDeposit;
    }

    public BigDecimal getWaterDeposit() {
        return waterDeposit;
    }

    public void setWaterDeposit(BigDecimal waterDeposit) {
        this.waterDeposit = waterDeposit;
    }

    public BigDecimal getFpayHouseRent() {
        return fpayHouseRent;
    }

    public void setFpayHouseRent(BigDecimal fpayHouseRent) {
        this.fpayHouseRent = fpayHouseRent;
    }

    public BigDecimal getFpayPropertyPrice() {
        return fpayPropertyPrice;
    }

    public void setFpayPropertyPrice(BigDecimal fpayPropertyPrice) {
        this.fpayPropertyPrice = fpayPropertyPrice;
    }

    public BigDecimal getFpayInsurancePrice() {
        return fpayInsurancePrice;
    }

    public void setFpayInsurancePrice(BigDecimal fpayInsurancePrice) {
        this.fpayInsurancePrice = fpayInsurancePrice;
    }

    public BigDecimal getFpayProjectDeposit() {
        return fpayProjectDeposit;
    }

    public void setFpayProjectDeposit(BigDecimal fpayProjectDeposit) {
        this.fpayProjectDeposit = fpayProjectDeposit;
    }

    public BigDecimal getFpayBond() {
        return fpayBond;
    }

    public void setFpayBond(BigDecimal fpayBond) {
        this.fpayBond = fpayBond;
    }

    public BigDecimal getPropertyUnitPrice() {
        return propertyUnitPrice;
    }

    public void setPropertyUnitPrice(BigDecimal propertyUnitPrice) {
        this.propertyUnitPrice = propertyUnitPrice;
    }

    public String getPropertyUnit() {
        return propertyUnit;
    }

    public void setPropertyUnit(String propertyUnit) {
        this.propertyUnit = propertyUnit;
    }

    public Boolean getHasInvoiceByProperty() {
        return hasInvoiceByProperty;
    }

    public void setHasInvoiceByProperty(Boolean hasInvoiceByProperty) {
        this.hasInvoiceByProperty = hasInvoiceByProperty;
    }

    public Integer getMonthCountByProperty() {
        return monthCountByProperty;
    }

    public void setMonthCountByProperty(Integer monthCountByProperty) {
        this.monthCountByProperty = monthCountByProperty;
    }

    public Date getBillingDateByProperty() {
        return billingDateByProperty;
    }

    public void setBillingDateByProperty(Date billingDateByProperty) {
        this.billingDateByProperty = billingDateByProperty;
    }

    public BigDecimal getTotalElectricity() {
        return totalElectricity;
    }

    public void setTotalElectricity(BigDecimal totalElectricity) {
        this.totalElectricity = totalElectricity;
    }

    public String getTotalElectricityUnit() {
        return totalElectricityUnit;
    }

    public void setTotalElectricityUnit(String totalElectricityUnit) {
        this.totalElectricityUnit = totalElectricityUnit;
    }

    public BigDecimal getBasicElectricityUnitPrice() {
        return basicElectricityUnitPrice;
    }

    public void setBasicElectricityUnitPrice(BigDecimal basicElectricityUnitPrice) {
        this.basicElectricityUnitPrice = basicElectricityUnitPrice;
    }

    public String getBasicElectricityUnit() {
        return basicElectricityUnit;
    }

    public void setBasicElectricityUnit(String basicElectricityUnit) {
        this.basicElectricityUnit = basicElectricityUnit;
    }

    public BigDecimal getBasicElectricityPriceByMonth() {
        return basicElectricityPriceByMonth;
    }

    public void setBasicElectricityPriceByMonth(BigDecimal basicElectricityPriceByMonth) {
        this.basicElectricityPriceByMonth = basicElectricityPriceByMonth;
    }

    public BigDecimal getAddLoss() {
        return addLoss;
    }

    public void setAddLoss(BigDecimal addLoss) {
        this.addLoss = addLoss;
    }

    public List<ElectricityReceiptEntity> getElectricityReceiptList() {
        return electricityReceiptList;
    }

    public void setElectricityReceiptList(List<ElectricityReceiptEntity> electricityReceiptList) {
        this.electricityReceiptList = electricityReceiptList;
    }

    public Boolean getElHasTax() {
        return elHasTax;
    }

    public void setElHasTax(Boolean elHasTax) {
        this.elHasTax = elHasTax;
    }

    public List<ElectricityMeterEntity> getMeterList() {
        return meterList;
    }

    public void setMeterList(List<ElectricityMeterEntity> meterList) {
        this.meterList = meterList;
    }

    public String getWaterBillingType() {
        return waterBillingType;
    }

    public void setWaterBillingType(String waterBillingType) {
        this.waterBillingType = waterBillingType;
    }

    public BigDecimal getWaterAmount() {
        return waterAmount;
    }

    public void setWaterAmount(BigDecimal waterAmount) {
        this.waterAmount = waterAmount;
    }

    public BigDecimal getWaterPrice() {
        return waterPrice;
    }

    public void setWaterPrice(BigDecimal waterPrice) {
        this.waterPrice = waterPrice;
    }

    public String getWaterUnit() {
        return waterUnit;
    }

    public void setWaterUnit(String waterUnit) {
        this.waterUnit = waterUnit;
    }

    public List<WaterReceiptEntity> getWaterReceiptList() {
        return waterReceiptList;
    }

    public void setWaterReceiptList(List<WaterReceiptEntity> waterReceiptList) {
        this.waterReceiptList = waterReceiptList;
    }

    public BigDecimal getInitWater() {
        return initWater;
    }

    public void setInitWater(BigDecimal initWater) {
        this.initWater = initWater;
    }

    public Boolean getWaHasTax() {
        return waHasTax;
    }

    public void setWaHasTax(Boolean waHasTax) {
        this.waHasTax = waHasTax;
    }

    public List<WaterMeterEntity> getWaterMeterList() {
        return waterMeterList;
    }

    public void setWaterMeterList(List<WaterMeterEntity> waterMeterList) {
        this.waterMeterList = waterMeterList;
    }

    public Integer getFreeParkingCount() {
        return freeParkingCount;
    }

    public void setFreeParkingCount(Integer freeParkingCount) {
        this.freeParkingCount = freeParkingCount;
    }

    public BigDecimal getParkingPrice() {
        return parkingPrice;
    }

    public void setParkingPrice(BigDecimal parkingPrice) {
        this.parkingPrice = parkingPrice;
    }

    public Integer getParkingSpace() {
        return parkingSpace;
    }

    public void setParkingSpace(Integer parkingSpace) {
        this.parkingSpace = parkingSpace;
    }

    public Date getParkingAddTime() {
        return parkingAddTime;
    }

    public void setParkingAddTime(Date parkingAddTime) {
        this.parkingAddTime = parkingAddTime;
    }

    public List<ParkingReceiptEntity> getParkingReceiptList() {
        return parkingReceiptList;
    }

    public void setParkingReceiptList(List<ParkingReceiptEntity> parkingReceiptList) {
        this.parkingReceiptList = parkingReceiptList;
    }

    public boolean isPaHasTax() {
        return paHasTax;
    }

    public void setPaHasTax(boolean paHasTax) {
        this.paHasTax = paHasTax;
    }

    public String getImContractCode() {
        return imContractCode;
    }

    public void setImContractCode(String imContractCode) {
        this.imContractCode = imContractCode;
    }

    public String getImCusCode() {
        return imCusCode;
    }

    public void setImCusCode(String imCusCode) {
        this.imCusCode = imCusCode;
    }

    public String getImCusName() {
        return imCusName;
    }

    public void setImCusName(String imCusName) {
        this.imCusName = imCusName;
    }

    public String getImCompany() {
        return imCompany;
    }

    public void setImCompany(String imCompany) {
        this.imCompany = imCompany;
    }

    public String getImRelatePhone() {
        return imRelatePhone;
    }

    public void setImRelatePhone(String imRelatePhone) {
        this.imRelatePhone = imRelatePhone;
    }

    public String getImReceiptAccount() {
        return imReceiptAccount;
    }

    public void setImReceiptAccount(String imReceiptAccount) {
        this.imReceiptAccount = imReceiptAccount;
    }

    public String getImReceiptNumber() {
        return imReceiptNumber;
    }

    public void setImReceiptNumber(String imReceiptNumber) {
        this.imReceiptNumber = imReceiptNumber;
    }

    public String getImBank() {
        return imBank;
    }

    public void setImBank(String imBank) {
        this.imBank = imBank;
    }

    public BigDecimal getImPayableAmount() {
        return imPayableAmount;
    }

    public void setImPayableAmount(BigDecimal imPayableAmount) {
        this.imPayableAmount = imPayableAmount;
    }

    public String getImStatus() {
        return imStatus;
    }

    public void setImStatus(String imStatus) {
        this.imStatus = imStatus;
    }

    public String getImStatusName() {
        return imStatusName;
    }

    public void setImStatusName(String imStatusName) {
        this.imStatusName = imStatusName;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }

    public BigDecimal getCurrentRentReceipt() {
        return currentRentReceipt;
    }

    public void setCurrentRentReceipt(BigDecimal currentRentReceipt) {
        this.currentRentReceipt = currentRentReceipt;
    }

    public String getRentReceiptPayStatus() {
        return rentReceiptPayStatus;
    }

    public void setRentReceiptPayStatus(String rentReceiptPayStatus) {
        this.rentReceiptPayStatus = rentReceiptPayStatus;
    }

    public BigDecimal getCurrentElectricityReceipt() {
        return currentElectricityReceipt;
    }

    public void setCurrentElectricityReceipt(BigDecimal currentElectricityReceipt) {
        this.currentElectricityReceipt = currentElectricityReceipt;
    }

    public String getElectricityReceiptPayStatus() {
        return electricityReceiptPayStatus;
    }

    public void setElectricityReceiptPayStatus(String electricityReceiptPayStatus) {
        this.electricityReceiptPayStatus = electricityReceiptPayStatus;
    }

    public BigDecimal getCurrentWaterReceipt() {
        return currentWaterReceipt;
    }

    public void setCurrentWaterReceipt(BigDecimal currentWaterReceipt) {
        this.currentWaterReceipt = currentWaterReceipt;
    }

    public String getWaterReceiptPayStatus() {
        return waterReceiptPayStatus;
    }

    public void setWaterReceiptPayStatus(String waterReceiptPayStatus) {
        this.waterReceiptPayStatus = waterReceiptPayStatus;
    }

    public BigDecimal getCurrentParkingReceipt() {
        return currentParkingReceipt;
    }

    public void setCurrentParkingReceipt(BigDecimal currentParkingReceipt) {
        this.currentParkingReceipt = currentParkingReceipt;
    }

    public String getParkingReceiptPayStatus() {
        return parkingReceiptPayStatus;
    }

    public void setParkingReceiptPayStatus(String parkingReceiptPayStatus) {
        this.parkingReceiptPayStatus = parkingReceiptPayStatus;
    }

    public BigDecimal getNextRentReceipt() {
        return nextRentReceipt;
    }

    public void setNextRentReceipt(BigDecimal nextRentReceipt) {
        this.nextRentReceipt = nextRentReceipt;
    }

    public BigDecimal getOutputValue() {
        return outputValue;
    }

    public void setOutputValue(BigDecimal outputValue) {
        this.outputValue = outputValue;
    }

    public Boolean getHasRevenueBackLocal() {
        return hasRevenueBackLocal;
    }

    public void setHasRevenueBackLocal(Boolean hasRevenueBackLocal) {
        this.hasRevenueBackLocal = hasRevenueBackLocal;
    }

    public BigDecimal getTaxTake() {
        return taxTake;
    }

    public void setTaxTake(BigDecimal taxTake) {
        this.taxTake = taxTake;
    }

    public String getEquipmentList() {
        return equipmentList;
    }

    public void setEquipmentList(String equipmentList) {
        this.equipmentList = equipmentList;
    }

    public BigDecimal getEquipmentValue() {
        return equipmentValue;
    }

    public void setEquipmentValue(BigDecimal equipmentValue) {
        this.equipmentValue = equipmentValue;
    }

    public List<PremiumEntity> getPremiumReceiptList() {
        return premiumReceiptList;
    }

    public void setPremiumReceiptList(List<PremiumEntity> premiumReceiptList) {
        this.premiumReceiptList = premiumReceiptList;
    }

    public String getDeleteCause() {
        return deleteCause;
    }

    public void setDeleteCause(String deleteCause) {
        this.deleteCause = deleteCause;
    }

    public String getDeleteType() {
        return deleteType;
    }

    public void setDeleteType(String deleteType) {
        this.deleteType = deleteType;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
    }

    public String getWaterMeterName() {
        return waterMeterName;
    }

    public void setWaterMeterName(String waterMeterName) {
        this.waterMeterName = waterMeterName;
    }

    public String getContractEditRemark() {
        return contractEditRemark;
    }

    public void setContractEditRemark(String contractEditRemark) {
        this.contractEditRemark = contractEditRemark;
    }

    public Date getThrowLeaseDate() {
        return throwLeaseDate;
    }

    public void setThrowLeaseDate(Date throwLeaseDate) {
        this.throwLeaseDate = throwLeaseDate;
    }
}
