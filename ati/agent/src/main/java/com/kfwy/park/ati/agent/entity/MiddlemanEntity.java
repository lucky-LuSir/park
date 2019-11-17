package com.kfwy.park.ati.agent.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.ati.agent.enums.MiddlemanType;
/**
 * Created by xiongzhan on 2018/6/2.
 */
public class MiddlemanEntity extends BaseEntity{

    /**
     * 中介人编号
     */
    private String midCode;
    /**
     * 中介人类型
     * @see MiddlemanType
     */
    private String middlemanType;

    /**
     * //中介人类型名称
     */
    private String middlemanTypeName;

    /**
     * 中介人姓名
     */
    private String middlemanName;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 固定电话
     */
    private String telephone;



    public String getMidCode() {
        return midCode;
    }

    public void setMidCode(String midCode) {
        this.midCode = midCode;
    }

    public String getMiddlemanType() {
        return middlemanType;
    }

    public void setMiddlemanType(String middlemanType) {
        this.middlemanType = middlemanType;
    }

    public String getMiddlemanName() {
        return middlemanName;
    }

    public void setMiddlemanName(String middlemanName) {
        this.middlemanName = middlemanName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getMiddlemanTypeName() {
        return DictionaryStorage.get(MiddlemanType.class.getName(),this.getMiddlemanType()).getName();
    }

    public void setMiddlemanTypeName(String middlemanTypeName) {
        this.middlemanTypeName = middlemanTypeName;
    }
}
