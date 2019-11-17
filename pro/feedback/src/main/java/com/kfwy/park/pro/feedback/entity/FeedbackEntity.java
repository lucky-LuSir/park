package com.kfwy.park.pro.feedback.entity;

import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.pro.feedback.enums.FbStatus;
import com.kfwy.park.pro.feedback.enums.FbType;

/**
 * @Description 问题反馈实体
 * @author wangchuang
 * @Date 2019/7/10 14:15
 * @Version 1.0
 * @Param 
 * @return 
 **/
public class FeedbackEntity extends BaseEntity {
    /**
     * 反馈问题编号
     */
    private String fbCode;
    /**
     * 反馈类型
     */
    private String fbType;
    /**
     * 反馈类型名称
     * @see FbType
     */
    private String fbTypeName;
    /**
     * 反馈问题标题
     */
    private String fbTitle;
    /**
     * 反馈描述
     */
    private String fbDescription;
    /**
     * 反馈图片
     */
    private String fbImg;
    /**
     * 反馈问题状态
     */
    private String fbStatus;
    /**
     * 反馈问题状态名称
     * @see FbStatus
     */
    private String fbStatusName;
    /**
     * 提出问题的人
     */
    private String askQuestionName;
    /**
     * 提出问题的人的手机号
     */
    private String askQuestionPhone;
    /**
     * 处理问题的人编码
     */
    private String dealQuestionCode;
    /**
     * 处理问题的人
     */
    private String dealQuestionName;
    /**
     * 处理问题的人的手机号
     */
    private String dealQuestionPhone;
    /**
     * 浏览人数
     */
    private Integer browseNumber;
    /**
     * 点赞数
     */
    private Integer likeNumber;
    /**
     * 评论数
     */
    private Integer commentNumber;


    /***************************get set***************************/

    public String getFbCode() {
        return fbCode;
    }

    public void setFbCode(String fbCode) {
        this.fbCode = fbCode;
    }

    public String getFbType() {
        return fbType;
    }

    public void setFbType(String fbType) {
        this.fbType = fbType;
    }

    public String getFbTypeName() {
        return DictionaryStorage.get(FbType.class.getName(), this.getFbType()).getName();
    }

    public void setFbTypeName(String fbTypeName) {
        this.fbTypeName = fbTypeName;
    }

    public String getFbTitle() {
        return fbTitle;
    }

    public void setFbTitle(String fbTitle) {
        this.fbTitle = fbTitle;
    }

    public String getFbDescription() {
        return fbDescription;
    }

    public void setFbDescription(String fbDescription) {
        this.fbDescription = fbDescription;
    }

    public String getFbImg() {
        return fbImg;
    }

    public void setFbImg(String fbImg) {
        this.fbImg = fbImg;
    }

    public String getFbStatus() {
        return fbStatus;
    }

    public void setFbStatus(String fbStatus) {
        this.fbStatus = fbStatus;
    }

    public String getFbStatusName() {
        return DictionaryStorage.get(FbStatus.class.getName(), this.getFbStatus()).getName();
    }

    public void setFbStatusName(String fbStatusName) {
        this.fbStatusName = fbStatusName;
    }

    public String getAskQuestionName() {
        return askQuestionName;
    }

    public void setAskQuestionName(String askQuestionName) {
        this.askQuestionName = askQuestionName;
    }

    public String getDealQuestionCode() {
        return dealQuestionCode;
    }

    public void setDealQuestionCode(String dealQuestionCode) {
        this.dealQuestionCode = dealQuestionCode;
    }

    public String getAskQuestionPhone() {
        return askQuestionPhone;
    }

    public void setAskQuestionPhone(String askQuestionPhone) {
        this.askQuestionPhone = askQuestionPhone;
    }

    public String getDealQuestionName() {
        return dealQuestionName;
    }

    public void setDealQuestionName(String dealQuestionName) {
        this.dealQuestionName = dealQuestionName;
    }

    public String getDealQuestionPhone() {
        return dealQuestionPhone;
    }

    public void setDealQuestionPhone(String dealQuestionPhone) {
        this.dealQuestionPhone = dealQuestionPhone;
    }

    public Integer getBrowseNumber() {
        return browseNumber;
    }

    public void setBrowseNumber(Integer browseNumber) {
        this.browseNumber = browseNumber;
    }

    public Integer getLikeNumber() {
        return likeNumber;
    }

    public void setLikeNumber(Integer likeNumber) {
        this.likeNumber = likeNumber;
    }

    public Integer getCommentNumber() {
        return commentNumber;
    }

    public void setCommentNumber(Integer commentNumber) {
        this.commentNumber = commentNumber;
    }
}
