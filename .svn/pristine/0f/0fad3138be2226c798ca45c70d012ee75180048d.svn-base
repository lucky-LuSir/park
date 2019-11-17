package com.kfwy.park.hrm.org.bi.entity;

import com.gniuu.framework.entity.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @Description 找商人员数据看板 实体Bean
 * @Auth xpp
 * @Date 2018/8/3
 * @Version 1.0
 */
public class EmpBiEntity extends BaseEntity {


    /**
     * 排名
     */
    private int rankNum;
    /**
     * 员工code
     */
    private String empCode;
    /**
     * 姓名
     */
    private String empName;

    /**
     * 电话
     */
    private String empPhone;

    /**
     * 负责园区(中文名字)
     * 说明:园区详情页-->招商对接人员列表 所关联的园区
     */
    private String pkName;

    /**
     * 本周接待客户(统计)
     * 说明:followup,跟进客户条数
     */
    private int cusReceiveCount;

    /**
     * 获客数，获客数不包含洽谈中，跟进中，不合适等状态，查询所有
     */
    private int cusCount;

    /**
     * 任务时间段(只是查询用,不记入数据库),时间开始和结束
     */
    private List<Date> dateStartAndEndList;

    /**
     * 洽谈中的客户(统计)
     * 说明:(本周)创建过的客户 create_code=#{empCode}
     */
    private int cusNegotiateCount;

    /**
     * 招商入驻客户(统计)
     * 说明:本周emp_code = #{empCode}用户empCode的客户
     */
    private int cusEntryCount;

    /**
     * 本月去化面积(统计)
     * 说明:t_yq_ati_con_contract_house 中出租面积之和
     */
    private BigDecimal signAreaMonth;

    /*=================================字段分割线====================================*/

    public EmpBiEntity() {
        //创建对象时,初始化数据
        this.signAreaMonth = new BigDecimal("0.00");
    }

    public int getRankNum() {
        return rankNum;
    }

    public void setRankNum(int rankNum) {
        this.rankNum = rankNum;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public int getCusReceiveCount() {
        return cusReceiveCount;
    }

    public void setCusReceiveCount(int cusReceiveCount) {
        this.cusReceiveCount = cusReceiveCount;
    }

    public int getCusNegotiateCount() {
        return cusNegotiateCount;
    }

    public void setCusNegotiateCount(int cusNegotiateCount) {
        this.cusNegotiateCount = cusNegotiateCount;
    }

    public int getCusEntryCount() {
        return cusEntryCount;
    }

    public void setCusEntryCount(int cusEntryCount) {
        this.cusEntryCount = cusEntryCount;
    }

    public BigDecimal getSignAreaMonth() {
        return signAreaMonth;
    }

    public void setSignAreaMonth(BigDecimal signAreaMonth) {
        this.signAreaMonth = signAreaMonth;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public int getCusCount() {
        return cusCount;
    }

    public void setCusCount(int cusCount) {
        this.cusCount = cusCount;
    }

    public List<Date> getDateStartAndEndList() {
        return dateStartAndEndList;
    }

    public void setDateStartAndEndList(List<Date> dateStartAndEndList) {
        this.dateStartAndEndList = dateStartAndEndList;
    }
}
