package com.kfwy.park.common.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 *@Author gengtao
 *@Date 2019-07-08 10:25:24
 *@Description element-ui的casecader样式
 *@Return
 **/
public class Cascader {

    private Long id;

    private String label;

    private String value;

    private List<Cascader> children;

    private BigDecimal grade;

    private String describe;

    private String type;

    public Cascader() {

    }

    public Cascader(Long id, String label, String value, String describe, String type) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.describe = describe;
        this.type = type;
    }

    public Cascader(Long id, String label, String value, BigDecimal grade, String describe, String type) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.grade = grade;
        this.describe = describe;
        this.type = type;
    }


    public List<Cascader> getChildren() {
        return children;
    }

    public void setChildren(List<Cascader> children) {
        this.children = children;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getGrade() {
        return grade;
    }

    public void setGrade(BigDecimal grade) {
        this.grade = grade;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
