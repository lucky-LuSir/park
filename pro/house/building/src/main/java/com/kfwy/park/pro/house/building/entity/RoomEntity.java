package com.kfwy.park.pro.house.building.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.pro.house.building.enums.FloorStatus;

import java.math.BigDecimal;

/**
 * 房间实体
 * @author xiongzhan
 */
public class RoomEntity extends BaseEntity {
    /**
     * 房间编码
     */
    private String roomCode;
    /**
     * 楼层编码
     */
    private String flCode;
    /**
     * 办公室编号
     */
    private String roomNumber;
    /**
     * 房间租金
     */
    private BigDecimal rentPrice;
    /**
     * 房间面积
     */
    private BigDecimal investArea;
    /**
     * 物业费
     */
    private BigDecimal property;
    /**
     *递增周期(单位:年)
     */
    private Integer increasingCycle;
    /**
     *免租期天数
     */
    private Integer freeRentDays;
    /**
     *付款规则(数字:付几个月)
     */
    private Integer payRule;
    /**
     *押金规则(数字:付几个月)
     */
    private Integer depositRule;

    /**
     * 状态
     *@see FloorStatus
     */
    private String roomStatus;

    /**
     * 状态名称
     */
    private String roomStatusName;
    /*==================================20180814============================*/

    public String getRoomCode() {
        return roomCode;
    }

    public void setRoomCode(String roomCode) {
        this.roomCode = roomCode;
    }

    public String getFlCode() {
        return flCode;
    }

    public void setFlCode(String flCode) {
        this.flCode = flCode;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public BigDecimal getRentPrice() {
        return rentPrice;
    }

    public void setRentPrice(BigDecimal rentPrice) {
        this.rentPrice = rentPrice;
    }

    public BigDecimal getInvestArea() {
        return investArea;
    }

    public void setInvestArea(BigDecimal investArea) {
        this.investArea = investArea;
    }

    public BigDecimal getProperty() {
        return property;
    }

    public void setProperty(BigDecimal property) {
        this.property = property;
    }

    public Integer getIncreasingCycle() {
        return increasingCycle;
    }

    public void setIncreasingCycle(Integer increasingCycle) {
        this.increasingCycle = increasingCycle;
    }

    public Integer getFreeRentDays() {
        return freeRentDays;
    }

    public void setFreeRentDays(Integer freeRentDays) {
        this.freeRentDays = freeRentDays;
    }

    public Integer getPayRule() {
        return payRule;
    }

    public void setPayRule(Integer payRule) {
        this.payRule = payRule;
    }

    public Integer getDepositRule() {
        return depositRule;
    }

    public void setDepositRule(Integer depositRule) {
        this.depositRule = depositRule;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus = roomStatus;
    }

    public String getRoomStatusName() {
        return DictionaryStorage.get(FloorStatus.class.getName(),this.getRoomStatus()).getName();
    }

    public void setRoomStatusName(String roomStatusName) {
        this.roomStatusName = roomStatusName;
    }
}
