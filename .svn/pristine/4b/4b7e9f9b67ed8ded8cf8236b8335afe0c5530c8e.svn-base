package com.kfwy.park.pro.inspect.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.pro.inspect.business.IInspectInfoManager;
import com.kfwy.park.pro.inspect.business.IInspectScoreManager;
import com.kfwy.park.pro.inspect.dao.IInspectInfoDao;
import com.kfwy.park.pro.inspect.dao.IInspectScoreDao;
import com.kfwy.park.pro.inspect.entity.InspectInfoEntity;
import com.kfwy.park.pro.inspect.entity.InspectScoreEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

@Service
public class InspectInfoManagerImpl extends AbstractManager<InspectInfoEntity> implements IInspectInfoManager {
    @Autowired
    private IInspectInfoDao inspectInfoDao;
    @Autowired
    private IInspectScoreDao inspectScoreDao;
    @Autowired
    private IInspectScoreManager inspectScoreManager;


    @Override
    protected IMyBatisBaseDao<InspectInfoEntity, Long> getMyBatisDao() {
        return inspectInfoDao;
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-12 16:08:44
     *@Description 新建评分
     *@Return
     **/
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createInfo(InspectInfoEntity infoEntity) {
        String inspectCode = UUID.randomUUID().toString().replaceAll("-","");
        infoEntity.setInspectCode(inspectCode);

        BigDecimal totalGrade = new BigDecimal(0);
        BigDecimal totalScore = new BigDecimal(0);
        List<InspectScoreEntity> scoreEntityList = infoEntity.getScoreEntityList();
        for(InspectScoreEntity score : scoreEntityList){
            score.setInspectCode(inspectCode);
            if(null != score.getGrade()){
                totalGrade = totalGrade.add(score.getGrade());
            }
            if(null != score.getScore()){
                totalScore = totalScore.add(score.getScore());
            }
        }

        infoEntity.setTotalGrade(totalGrade);
        infoEntity.setTotalScore(totalScore);
        this.create(infoEntity);
        //插入得分信息
        inspectScoreDao.batchInsert(scoreEntityList);
    }


    /**
     *@Author gtaotao
     *@Date 2019-07-12 09:12:41
     *@Description 获取包含树形score信息的评分信息
     *@Return
     **/
    @Override
    public InspectInfoEntity findInfoWithScoreTree(Map<String, Object> map) {
        InspectInfoEntity inspectInfoEntity = this.findOne(map);
        List<InspectScoreEntity> scoreEntityList = inspectScoreManager.findByMap(map,"id",true);
        for(InspectScoreEntity score : scoreEntityList){
            if("0".equals(score.getParentCode())){
                InspectScoreEntity inspectScoreEntity = inspectScoreManager.scoreInfoToTree(score, scoreEntityList);
                inspectInfoEntity.setScoreEntityTree(inspectScoreEntity);
                break;
            }
        }
        return inspectInfoEntity;
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-12 16:07:54
     *@Description 获取包含score信息的评分信息
     *@Return
     **/
    @Override
    public InspectInfoEntity findInfoWithScoreList(Map<String, Object> map) {
        InspectInfoEntity inspectInfoEntity = this.findOne(map);
        List<InspectScoreEntity> scoreEntityList = inspectScoreManager.findByMap(map);
        inspectInfoEntity.setScoreEntityList(scoreEntityList);
        return inspectInfoEntity;
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-12 16:12:05
     *@Description 检查参数是否完整
     *@Return
     **/
    @Override
    public void checkParams(InspectInfoEntity entity) throws IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("taskCode");
        list.add("inspectTime");
        list.add("beInspectedEmpCode");
        list.add("beInspectedEmpName");
        list.add("inspectEmpCode");
        list.add("inspectEmpName");
        ParamUtil<InspectInfoEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(entity, list);
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-12 16:12:58
     *@Description 修改评分信息
     *@Return
     **/
    @Override
    public void updateInfo(InspectInfoEntity infoEntity) {
        InspectScoreEntity scoreEntityTree= infoEntity.getScoreEntityTree();

        inspectScoreManager.updateScoreTree(scoreEntityTree);

        BigDecimal grade = new BigDecimal(0);
        BigDecimal score = new BigDecimal(0);
        Map<String, Object> map = new HashMap<>();
        map.put("grade",new BigDecimal(0));
        map.put("score", new BigDecimal("0"));
        map = inspectScoreManager.totalGradeAndScoreWithTree(map, scoreEntityTree);
        BigDecimal totalGrade = (BigDecimal) map.get("grade");
        BigDecimal totalScore = (BigDecimal) map.get("score");

        infoEntity.setTotalGrade(totalGrade);
        infoEntity.setTotalScore(totalScore);
        this.update(infoEntity);

    }

}
