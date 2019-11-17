package com.kfwy.park.pro.feedback.entity;

import com.gniuu.framework.entity.BaseEntity;

/**
 * @author wangchuang
 * @Description 问题反馈回复
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public class FeedbackReplyEntity extends BaseEntity {

    /**
     * 反馈回复编号
     */
    private String replyCode;

    /**
     * 反馈问题编号
     */
    private String fbCode;

    /**
     * 回复内容
     */
    private String replyDescription;

    /**
     * 回复图片
     */
    private String replyImg;


    /***************************get set***************************/

    public String getReplyCode() {
        return replyCode;
    }

    public void setReplyCode(String replyCode) {
        this.replyCode = replyCode;
    }

    public String getFbCode() {
        return fbCode;
    }

    public void setFbCode(String fbCode) {
        this.fbCode = fbCode;
    }

    public String getReplyDescription() {
        return replyDescription;
    }

    public void setReplyDescription(String replyDescription) {
        this.replyDescription = replyDescription;
    }

    public String getReplyImg() {
        return replyImg;
    }

    public void setReplyImg(String replyImg) {
        this.replyImg = replyImg;
    }
}
