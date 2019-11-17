package com.kfwy.park.ati.contract.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.contract.enums.CtStatus;

/**
 * @author wangchuang
 * @Description 合同操作记录表
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class ConOperateHisEntity extends BaseEntity {

    /**
     * 合同编号
     */
    private String ctCode;

    /**
     * 操作合同状态
     */
    private String ctStatus;

    /**
     * 合同状态名称
     * @see CtStatus
     */
    private String ctStatusName;

    /**
     * 操作说明
     */
    private String operateRemark;

    public String getCtCode() {
        return ctCode;
    }

    public void setCtCode(String ctCode) {
        this.ctCode = ctCode;
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

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
    }
}
