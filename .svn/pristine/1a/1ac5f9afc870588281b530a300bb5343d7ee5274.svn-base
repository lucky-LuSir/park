package com.kfwy.park.sys.board.entity;


import com.gniuu.framework.dic.DictionaryStorage;
import com.gniuu.framework.entity.BaseEntity;
import com.kfwy.park.sys.board.enums.ProgramType;

/*
 * @Description 首页小程序
 * @Auth liuzhengyang
 * @Date 2018/7/23 13:52
 * @Version 1.0
 * @Param
 * @return
 */
public class ProgramEntity extends BaseEntity {


    /**
     * 小程序编码
     */
    private String pgCode;
    /**
     * 小程序名称
     */
    private String pgName;
    /**
     * 小程序类型
     */
    private String programType;
    /**
     * 小程序类型名称
     */
    private String programTypeName;
    /**
     * 小程序缩略图片
     */
    private String url;

    private String defaultParam;

    /*************************get set 方法*********************************/
    public String getPgCode() {
        return pgCode;
    }

    public void setPgCode(String pgCode) {
        this.pgCode = pgCode;
    }

    public String getPgName() {
        return pgName;
    }

    public void setPgName(String pgName) {
        this.pgName = pgName;
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType;
    }

    public String getProgramTypeName() {
        return DictionaryStorage.get(ProgramType.class.getName(),this.getProgramType()).getName();
    }

    public void setProgramTypeName(String programTypeName) {
        this.programTypeName = programTypeName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDefaultParam() {
        return defaultParam;
    }

    public void setDefaultParam(String defaultParam) {
        this.defaultParam = defaultParam;
    }
}
