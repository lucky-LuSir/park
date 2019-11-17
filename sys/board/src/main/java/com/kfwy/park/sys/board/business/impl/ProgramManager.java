package com.kfwy.park.sys.board.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.sys.board.business.IProgramManager;
import com.kfwy.park.sys.board.dao.IProgramDbDao;
import com.kfwy.park.sys.board.entity.ProgramEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by liuzhengyang on 2018/7/23.
 */
@Service
public class ProgramManager extends AbstractManager<ProgramEntity> implements IProgramManager {

    @Autowired
    private IProgramDbDao programDbDao;
    @Override
    protected IMyBatisBaseDao<ProgramEntity, Long> getMyBatisDao() {
        return this.programDbDao;
    }
}
