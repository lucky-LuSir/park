package com.kfwy.park.sys.board.entity;


import com.gniuu.framework.entity.BaseEntity;

/*
 * @Description 首页看板
 * @Auth liuzhengyang
 * @Date 2018/7/23 13:52
 * @Version 1.0
 * @Param
 * @return
 */
public class BoardEntity extends BaseEntity {


    /**
     * 看板编码
     */
    private String boardCode;
    /**
     * 小程序编码
     */
    private String pgCode;
    /**
     * 参数json
     */
    private String param;


    /*************************get set 方法*********************************/
    public String getBoardCode() {
        return boardCode;
    }

    public void setBoardCode(String boardCode) {
        this.boardCode = boardCode;
    }

    public String getPgCode() {
        return pgCode;
    }

    public void setPgCode(String pgCode) {
        this.pgCode = pgCode;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
}
