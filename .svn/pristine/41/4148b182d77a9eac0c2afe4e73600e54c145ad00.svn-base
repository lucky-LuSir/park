package com.kfwy.park.exp.house.building.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.exp.house.building.enums.ExpStatus;
import com.kfwy.park.exp.house.building.enums.InvFollowupType;

/**
 * @Description 拓展跟进
 * @Auth luming
 * @Date 2018/11/5 8:57
 * @Version 1.0
 * @Param
 * @return
 */
public class InvparkFollowupEntity extends BaseEntity {

    /**
     * 跟进编码
     */
    private String followupCode;

    /**
     * 项目编码
     */
    private String itemCode;

    /**
     * 客户跟进(来访)类型
     * @see com.kfwy.park.exp.house.building.enums.InvFollowupType
     */
    private String invFollowupType;

    /**
     * 跟进(来访)类型名称
     */
    private String invFollowupTypeName;

    /**
     * 项目状态
     */
    private String expStatus;

    /**
     * 项目状态名称
     * @see ExpStatus
     */
    private String expStatusName;

    /**
     * 跟进结果
     */
    private String followupResult;

    /************************************************************************
     * get、set 方法
     ***********************************************************************/

    public String getFollowupCode() {
        return followupCode;
    }

    public void setFollowupCode(String followupCode) {
        this.followupCode = followupCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getExpStatus() {
        return expStatus;
    }

    public void setExpStatus(String expStatus) {
        this.expStatus = expStatus;
    }

    public String getFollowupResult() {
        return followupResult;
    }

    public void setFollowupResult(String followupResult) {
        this.followupResult = followupResult;
    }

    public String getExpStatusName() {
        return DictionaryStorage.get(ExpStatus.class.getName(), this.getExpStatus()).getName();
    }

    public String getInvFollowupType() {
        return invFollowupType;
    }

    public void setInvFollowupType(String invFollowupType) {
        this.invFollowupType = invFollowupType;
    }

    public String getInvFollowupTypeName() {
        return DictionaryStorage.get(InvFollowupType.class.getName(), this.getInvFollowupType()).getName();
    }

    public void setInvFollowupTypeName(String invFollowupTypeName) {
        this.invFollowupTypeName = invFollowupTypeName;
    }

    public void setExpStatusName(String expStatusName) {
        this.expStatusName = expStatusName;
    }

}
