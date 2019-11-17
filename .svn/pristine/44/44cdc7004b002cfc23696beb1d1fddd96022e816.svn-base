package com.kfwy.park.sys.msg.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.sys.msg.enums.ChannelType;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by luming on 2019/6/21.
 * 成交消息
 */
public class BargainMsgEntity extends BaseEntity {
    /**
     * 成交消息code
     */
    private String bmCode;

    /**
     * 成交日期
     */
    private Date bargainDate;

    /**
     * 园区code
     */
    private String pkCode;

    /**
     * 园区名
     */
    private String pkName;

    /**
     * 签单人code
     */
    private String empCode;

    /**
     * 签单人名字
     */
    private String empName;

    /**
     * 出租面积
     */
    private BigDecimal area;

    /**
     * 年租金
     */
    private BigDecimal rent;

    /**
     * 客户来源渠道
     * @see ChannelType
     */
    private String channel;

    /**
     * 渠道名字
     */
    private String channelName;

    /*------------------------------------------------------------------------*/

    public String getBmCode() {
        return bmCode;
    }

    public void setBmCode(String bmCode) {
        this.bmCode = bmCode;
    }

    public Date getBargainDate() {
        return bargainDate;
    }

    public void setBargainDate(Date bargainDate) {
        this.bargainDate = bargainDate;
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

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getRent() {
        return rent;
    }

    public void setRent(BigDecimal rent) {
        this.rent = rent;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannelName() {
        return DictionaryStorage.get(ChannelType.class.getName(), this.getChannel()).getName();
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}
