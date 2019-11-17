package com.kfwy.park.controller.exp.building.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.exp.house.building.entity.InvparkEntity;

/**
 * @Description 拓展
 * @Auth luming
 * @Date 2018/11/7 10:44
 * @Version 1.0
 * @Param
 * @return
 */
public class InvparkServiceRequest extends AbstractServiceRequest<InvparkEntity> {

    /**
     * 关键词搜索
     */
    private String keyword;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}
