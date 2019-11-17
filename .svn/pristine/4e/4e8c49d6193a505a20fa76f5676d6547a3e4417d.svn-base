package com.kfwy.park.sys.file.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.sys.file.business.IFileManager;
import com.kfwy.park.sys.file.dao.IFileDbDao;
import com.kfwy.park.sys.file.entity.FileEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Description TODO
 * @Auth davidCun
 * @Date 2018/6/13 08:39
 * @Version 1.0
 */
@Service
public class FileManagerImpl extends AbstractManager<FileEntity> implements IFileManager {

    @Autowired
    private IFileDbDao fileDbDao;


    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.fileDbDao;
    }

    @Override
    public FileEntity findByCode(String fileCode) {

        return fileDbDao.selectUniqueByProp("fileCode",fileCode);
    }

    @Transactional
    public void create(List<FileEntity> files){

        fileDbDao.batchInsert(files);

    }

    @Override
    @Transactional
    public void tranTest(String pm) {

        FileEntity file = new FileEntity();

        create(file);

        if(pm.equalsIgnoreCase("1")){
            throw new  BusinessException("cuowu");
        }

    }

    /*
     * @Description 迭代查询目录
     * @Auth liuzhengyang
     * @Date 2018/7/12 11:56
     * @Version 1.0
     * @Param [code]
     * @return java.util.List<com.kfwy.park.sys.file.entity.FileEntity>
     */
    @Override
    public List<FileEntity> selectCatalog(String code,List<FileEntity> list) {

        FileEntity fileEntity = fileDbDao.selectUniqueByProp("fileCode",code);
        //点击我的文件夹时
        if(fileEntity == null){
            defaultAdd(list);
            return list;
        }
        list.add(fileEntity);
        if("0".equals(fileEntity.getParentCode())){
            defaultAdd(list);
            return list;
        }else {
            selectCatalog(fileEntity.getParentCode(),list);
        }
        return list;
    }

    private void defaultAdd(List<FileEntity> list) {
        FileEntity defalt = new FileEntity();
        defalt.setFileCode("0");
        defalt.setFileName("我的文件夹");
        list.add(defalt);
    }
}
