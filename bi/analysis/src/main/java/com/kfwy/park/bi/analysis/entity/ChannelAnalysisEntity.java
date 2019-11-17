package com.kfwy.park.bi.analysis.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * Created by luming on 2019/5/20.
 */
public class ChannelAnalysisEntity extends BaseEntity {
    /**
     * 58同城
     */
    private Integer app58;

    /**
     * 户外横幅
     */
    private Integer streamer;

    /**
     * 小广告/传单
     */
    private Integer advertising;

    /**
     * 微信朋友圈
     */
    private Integer friendsShare;

    /**
     * 园区建筑广告
     */
    private Integer buildingAdvertising;

    /**
     * 拨打电话
     */
    private Integer callPhone;

    /**
     * 中介
     */
    private Integer middleman;

    /**
     * 兼职/转介绍
     */
    private Integer pluralistic;

    /**
     * 其他
     */
    private Integer other;

    /**
     * 库房无忧
     */
    private Integer kfwy;

    /*-----------------------------------------------------------------------------*/

    public Integer getApp58() {
        return app58;
    }

    public void setApp58(Integer app58) {
        this.app58 = app58;
    }

    public Integer getStreamer() {
        return streamer;
    }

    public void setStreamer(Integer streamer) {
        this.streamer = streamer;
    }

    public Integer getAdvertising() {
        return advertising;
    }

    public void setAdvertising(Integer advertising) {
        this.advertising = advertising;
    }

    public Integer getFriendsShare() {
        return friendsShare;
    }

    public void setFriendsShare(Integer friendsShare) {
        this.friendsShare = friendsShare;
    }

    public Integer getBuildingAdvertising() {
        return buildingAdvertising;
    }

    public void setBuildingAdvertising(Integer buildingAdvertising) {
        this.buildingAdvertising = buildingAdvertising;
    }

    public Integer getCallPhone() {
        return callPhone;
    }

    public void setCallPhone(Integer callPhone) {
        this.callPhone = callPhone;
    }

    public Integer getMiddleman() {
        return middleman;
    }

    public void setMiddleman(Integer middleman) {
        this.middleman = middleman;
    }

    public Integer getPluralistic() {
        return pluralistic;
    }

    public void setPluralistic(Integer pluralistic) {
        this.pluralistic = pluralistic;
    }

    public Integer getOther() {
        return other;
    }

    public void setOther(Integer other) {
        this.other = other;
    }

    public Integer getKfwy() {
        return kfwy;
    }

    public void setKfwy(Integer kfwy) {
        this.kfwy = kfwy;
    }
}
