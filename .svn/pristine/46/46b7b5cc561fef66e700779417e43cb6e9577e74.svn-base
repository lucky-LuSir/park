package com.kfwy.park.pro.inspect.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.pro.inspect.business.IInspectScoreManager;
import com.kfwy.park.pro.inspect.dao.IInspectScoreDao;
import com.kfwy.park.pro.inspect.entity.InspectScoreEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 物业检查得分信息表 服务实现类
 * @author gtaotao
 * @since 2019-07-09 13:49:25
 */
@Service
public class InspectScoreManagerImpl extends AbstractManager<InspectScoreEntity> implements IInspectScoreManager {
    @Autowired
    private IInspectScoreDao inspectScoreDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.inspectScoreDao;
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-13 08:57:59
     *@Description 将得分信息转为tree结构
     *@Return
     **/
    @Override
    public InspectScoreEntity scoreInfoToTree(InspectScoreEntity rootEntity, List<InspectScoreEntity> inspectInfoEntityList) {
        List<InspectScoreEntity> list = new ArrayList<>();
        for(InspectScoreEntity entity : inspectInfoEntityList){
            if(entity.getParentCode().equals(rootEntity.getObjRlCode())){
                list.add(entity);
            }
        }
        if(list.size() > 0){
            rootEntity.setChildren(list);
        }else{
            rootEntity.setChildren(null);
        }

        for(InspectScoreEntity childEntity : list){
            scoreInfoToTree(childEntity,inspectInfoEntityList);
        }
        return rootEntity;
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-13 09:09:51
     *@Description 更新Tree结构score
     *@Return
     **/
    @Override
    public void updateScoreTree(InspectScoreEntity scoreTree){
        this.update(scoreTree);
        List<InspectScoreEntity> entityList = scoreTree.getChildren();
        if(null != entityList && entityList.size() > 0){
            for(InspectScoreEntity sc : entityList){
                this.updateScoreTree(sc);
            }
        }
    }

    @Override
    public Map<String, Object> totalGradeAndScoreWithTree(Map<String, Object> map,  InspectScoreEntity scoreTree) {
        BigDecimal grade = (BigDecimal) map.get("grade");
        BigDecimal newGrade = (null != scoreTree.getGrade()) ? scoreTree.getGrade(): new BigDecimal(0);
        grade = grade.add(newGrade);
        map.put("grade",grade);

        BigDecimal score = (BigDecimal) map.get("score");
        BigDecimal newScore = (null != scoreTree.getScore()) ? scoreTree.getScore() : new BigDecimal(0);
        score = score.add(newScore);
        map.put("score",score);




        List<InspectScoreEntity> entityList = scoreTree.getChildren();
        if(null != entityList && entityList.size() > 0){
            for(InspectScoreEntity sc : entityList){
                this.totalGradeAndScoreWithTree(map, sc);
            }
        }
        return map;
    }


}