package com.kfwy.park.sys.file.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.sys.file.entity.FileEntity;

import java.util.List;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/13 08:38
 * @Version 1.0
 */
public interface IFileManager extends IManager<FileEntity> {


    /**
     * @description
     * 通过文件编码查询文件实体信息
     *
     * @auth davidCun
     * @date 2018/6/13 10:31
     * @version 1.0
     * @param fileCode
     * @return com.kfwy.park.sys.file.entity.FileEntity
     */
    FileEntity findByCode(String fileCode);


    /**
     *
     * @param files
     */
    public void create(List<FileEntity> files);


    public void tranTest(String pm);

    List<FileEntity> selectCatalog(String code,List<FileEntity> list);
}
