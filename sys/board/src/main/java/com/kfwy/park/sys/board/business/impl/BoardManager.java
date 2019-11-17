package com.kfwy.park.sys.board.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;

import com.kfwy.park.sys.board.business.IBoardManager;
import com.kfwy.park.sys.board.business.IProgramManager;
import com.kfwy.park.sys.board.dao.IBoardDbDao;
import com.kfwy.park.sys.board.entity.BoardEntity;

import com.kfwy.park.sys.board.entity.ProgramEntity;
import com.kfwy.park.sys.user.business.IUserManager;
import com.kfwy.park.sys.user.entity.UserEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Created by liuzhengyang on 2018/7/23.
 */
@Service
public class BoardManager extends AbstractManager<BoardEntity> implements IBoardManager {

    @Autowired
    private IBoardDbDao boardDbDao;
    @Autowired
    private IUserManager userManager;
    @Autowired
    private IProgramManager programManager;

    @Override
    protected IMyBatisBaseDao<BoardEntity, Long> getMyBatisDao() {
        return this.boardDbDao;
    }

    @Override
    public void delete(Long id) {
        boardDbDao.deleteById(id);
    }

    @Transactional
    @Override
    public void batchCreate(String pgCodes, String createCodes){
        String[] pgCodeArr = pgCodes.split(",");
        String[] createCodeArr = createCodes.split(",");
        Map<String, Object> pgMap = new HashMap<>();
        Stream.of(pgCodeArr).forEach(pgCode ->{
            ProgramEntity programEntity = programManager.findOne("pgCode",pgCode);
            if(null != programEntity){
                pgMap.put(pgCode,programEntity.getDefaultParam());
            }
        });

        Stream.of(createCodeArr).forEach(createCode ->{
            UserEntity userEntity = userManager.findOne("userCode",createCode);
            Stream.of(pgCodeArr).forEach(pgCode ->{
                BoardEntity entity = new BoardEntity();
                entity.setCreateCode(createCode);
                entity.setPgCode(pgCode.substring(2));//接收的是pg002,数据库存的是002
                entity.setBoardCode(CodeGenUtils.generate());
                entity.setParam((String)pgMap.get(pgCode));
                if(null != userEntity){
                    entity.setCpyCode(userEntity.getCpyCode());
                }
                entity.setLastUpdateCode(createCode);

                this.create(entity);
            });
        });
    }



}
