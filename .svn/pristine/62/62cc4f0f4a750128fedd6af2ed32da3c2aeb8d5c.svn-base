package com.kfwy.park.exp.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.kfwy.park.common.dic.RentUnit;
import com.kfwy.park.common.dic.SexType;
import com.kfwy.park.common.entity.TaskBaseEntity;
import com.kfwy.park.exp.contract.enums.LdCtStatus;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * Created by luming on 2019/4/18.
 * 大房东合同
 */
public class LandlordContractEntity extends TaskBaseEntity {
    /**
     * 大房东合同编码（自动生成）
     */
    private String ldCode;

    /**
     * 大房东合同编号（按照既定规则生成）
     */
    private String ldCtName;

    /**
     * 合同状态
     * @see LdCtStatus
     */
    private String ldCtStatus;

    /**
     * 合同状态名称
     */
    private String ldCtStatusName;

    /**
     * 园区编号
     */
    private String pkCode;

    /**
     * 园区名称
     */
    private String pkName;

    /**
     * 承租面积（不含公摊，即产证面积）
     */
    private BigDecimal acreage;

    /**
     * 房东名字
     */
    private String landlordName;

    /**
     * 房东性别
     */
    private String landlordSex;

    /**
     * 房东性别名称
     */
    private String landlordSexName;

    /**
     * 房东电话
     */
    private String landlordPhone;

    /**
     * 签约人编码
     */
    private String contractorCode;

    /**
     * 签约人
     */
    private String contractor;

    /**
     * 签约人电话
     */
    private String contractorPhone;

    /**
     * 签约人部门
     */
    private String contractorDeptCode;

    /**
     * 合同开始日期
     */
    private Date contractStartDate;

    /**
     * 合同结束日期
     */
    private Date contractEndDate;

    /**
     * 提前多少天交租
     */
    private Integer advanceDay;

    /**
     * 是否详细递增
     */
    private Boolean hasDetailAdd;

    /**
     * 递增详情
     */
    private String progressiveIncreaseDetail;

    /**
     * 递增周期（每几年递增一次）
     */
    private Integer rentIncreasingCycle;

    /**
     * 租金递增比例（每次递增百分之多少）
     */
    private BigDecimal rentIncreaseProportion;

    /**
     * 租金单价
     */
    private BigDecimal rentUnivalence;

    /**
     * 租金单位
     * @see RentUnit
     */
    private String rentUnit;

    /**
     * 租金单位名称
     */
    private String rentUnitName;

    /**
     * 押几
     */
    private String depositRule;

    /**
     * 付几
     */
    private String payRule;

    /**
     * 押金
     */
    private BigDecimal deposit;

    /**
     * 合同附件
     */
    private String contractUrl;

    /**
     * 关联合同租金表
     */
    private List<LandlordRentEntity> landlordRentEntityList;

    /*-------------------------------- get set 方法 --------------------------------*/

    public String getLdCode() {
        return ldCode;
    }

    public void setLdCode(String ldCode) {
        this.ldCode = ldCode;
    }

    public String getLdCtName() {
        return ldCtName;
    }

    public void setLdCtName(String ldCtName) {
        this.ldCtName = ldCtName;
    }

    public String getLdCtStatus() {
        return ldCtStatus;
    }

    public void setLdCtStatus(String ldCtStatus) {
        this.ldCtStatus = ldCtStatus;
    }

    public String getLdCtStatusName() {
        return DictionaryStorage.get(LdCtStatus.class.getName(), this.getLdCtStatus()).getName();
    }

    public void setLdCtStatusName(String ldCtStatusName) {
        this.ldCtStatusName = ldCtStatusName;
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

    public BigDecimal getAcreage() {
        return acreage;
    }

    public void setAcreage(BigDecimal acreage) {
        this.acreage = acreage;
    }

    public String getLandlordName() {
        return landlordName;
    }

    public void setLandlordName(String landlordName) {
        this.landlordName = landlordName;
    }

    public String getLandlordSex() {
        return landlordSex;
    }

    public void setLandlordSex(String landlordSex) {
        this.landlordSex = landlordSex;
    }

    public String getLandlordSexName() {
        return DictionaryStorage.get(SexType.class.getName(), this.getLandlordSex()).getName();
    }

    public void setLandlordSexName(String landlordSexName) {
        this.landlordSexName = landlordSexName;
    }

    public String getLandlordPhone() {
        return landlordPhone;
    }

    public void setLandlordPhone(String landlordPhone) {
        this.landlordPhone = landlordPhone;
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

    public Date getContractStartDate() {
        return contractStartDate;
    }

    public void setContractStartDate(Date contractStartDate) {
        this.contractStartDate = contractStartDate;
    }

    public Date getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(Date contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public Integer getAdvanceDay() {
        return advanceDay;
    }

    public void setAdvanceDay(Integer advanceDay) {
        this.advanceDay = advanceDay;
    }

    public Boolean getHasDetailAdd() {
        return hasDetailAdd;
    }

    public void setHasDetailAdd(Boolean hasDetailAdd) {
        this.hasDetailAdd = hasDetailAdd;
    }

    public String getProgressiveIncreaseDetail() {
        return progressiveIncreaseDetail;
    }

    public void setProgressiveIncreaseDetail(String progressiveIncreaseDetail) {
        this.progressiveIncreaseDetail = progressiveIncreaseDetail;
    }

    public Integer getRentIncreasingCycle() {
        return rentIncreasingCycle;
    }

    public void setRentIncreasingCycle(Integer rentIncreasingCycle) {
        this.rentIncreasingCycle = rentIncreasingCycle;
    }

    public BigDecimal getRentIncreaseProportion() {
        return rentIncreaseProportion;
    }

    public void setRentIncreaseProportion(BigDecimal rentIncreaseProportion) {
        this.rentIncreaseProportion = rentIncreaseProportion;
    }

    public BigDecimal getRentUnivalence() {
        return rentUnivalence;
    }

    public void setRentUnivalence(BigDecimal rentUnivalence) {
        this.rentUnivalence = rentUnivalence;
    }

    public String getRentUnit() {
        return rentUnit;
    }

    public void setRentUnit(String rentUnit) {
        this.rentUnit = rentUnit;
    }

    public String getRentUnitName() {
        return DictionaryStorage.get(RentUnit.class.getName(), this.getRentUnit()).getName();
    }

    public void setRentUnitName(String rentUnitName) {
        this.rentUnitName = rentUnitName;
    }

    public String getDepositRule() {
        return depositRule;
    }

    public void setDepositRule(String depositRule) {
        this.depositRule = depositRule;
    }

    public String getPayRule() {
        return payRule;
    }

    public void setPayRule(String payRule) {
        this.payRule = payRule;
    }

    public BigDecimal getDeposit() {
        return deposit;
    }

    public void setDeposit(BigDecimal deposit) {
        this.deposit = deposit;
    }

    public List<LandlordRentEntity> getLandlordRentEntityList() {
        return landlordRentEntityList;
    }

    public void setLandlordRentEntityList(List<LandlordRentEntity> landlordRentEntityList) {
        this.landlordRentEntityList = landlordRentEntityList;
    }

    public String getContractorDeptCode() {
        return contractorDeptCode;
    }

    public void setContractorDeptCode(String contractorDeptCode) {
        this.contractorDeptCode = contractorDeptCode;
    }

    public String getContractUrl() {
        return contractUrl;
    }

    public void setContractUrl(String contractUrl) {
        this.contractUrl = contractUrl;
    }
}
