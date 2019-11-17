package com.kfwy.park.controller.act.activiti.vo;

import com.gniuu.framework.service.AbstractServiceRequest;
import com.kfwy.park.common.entity.TaskBaseEntity;

/**
 * Created by luming on 2019/3/14.
 * 工作流管理
 */
public class ActivitiManageServiceRequest extends AbstractServiceRequest<TaskBaseEntity>{
    /**
     * 部署文件路径
     */
    private String filePath;

    /*--------------------------------- get set 方法 ---------------------------------*/
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
