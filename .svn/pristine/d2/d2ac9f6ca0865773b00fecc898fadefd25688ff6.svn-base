package com.kfwy.park.pro.inspect.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.common.entity.Cascader;
import com.kfwy.park.pro.inspect.business.IInspectObjectRuleManager;
import com.kfwy.park.pro.inspect.dao.IInspectObjectRuleDao;
import com.kfwy.park.pro.inspect.dao.IInspectTaskObjDao;
import com.kfwy.park.pro.inspect.entity.InspectObjectRuleEntity;
import com.kfwy.park.pro.inspect.entity.InspectTaskObjEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class InspectObjectRuleManagerImpl extends AbstractManager<InspectObjectRuleEntity> implements IInspectObjectRuleManager {
    @Autowired
    private IInspectObjectRuleDao inspectObjectRuleDao;
    @Autowired
    private IInspectTaskObjDao inspectTaskObjDao;

    @Override
    protected IMyBatisBaseDao<InspectObjectRuleEntity, Long> getMyBatisDao() {
        return inspectObjectRuleDao;
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-08 15:21:10
     *@Description 将模板中的事项规则以cascader树形返回
     *@Return
     **/
    @Override
    public Cascader ObjRuleCascader(List<InspectObjectRuleEntity> inspectObjectRuleEntity){
        //获取根节点
        BigDecimal totalGrade = new BigDecimal(0);
        InspectObjectRuleEntity rootEntity = new InspectObjectRuleEntity();
        for(InspectObjectRuleEntity objectRuleEntity : inspectObjectRuleEntity){
            if(null != objectRuleEntity.getGrade()){
                totalGrade = totalGrade.add(objectRuleEntity.getGrade());
            }
            if(("0").equals(objectRuleEntity.getParentCode())){
                rootEntity = objectRuleEntity;
            }
        }
        Cascader cascader = new Cascader(rootEntity.getId(),rootEntity.getObjRlName()+"（共 "+totalGrade+"分）", rootEntity.getObjRlCode(), rootEntity.getDescribe(),rootEntity.getType());
        this.getNode(cascader, inspectObjectRuleEntity);

        return cascader;
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-10 14:36:37
     *@Description 通过taskCode获取任务对应的检查事项
     *@Return
     **/
    @Override
    public List<InspectObjectRuleEntity> findByTaskCode(Map<String, Object> map) {
        return inspectObjectRuleDao.selectByTaskCode(map);
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-10 14:42:43
     *@Description 删除检查事项
     *@Return
     **/
    @Override
    public void deleteObj(InspectObjectRuleEntity entity) {
        List<InspectObjectRuleEntity> inspectObjectRuleEntityList = new ArrayList<>();
        inspectObjectRuleEntityList.add(entity);
        this.getChildNode(inspectObjectRuleEntityList, entity);

        //物理删除
        List<Long>ids =  inspectObjectRuleEntityList.stream().map(InspectObjectRuleEntity::getId).collect(Collectors.toList());
        inspectObjectRuleDao.batchDeleteByIds(ids);

        //虚拟删除
//        List<InspectObjectRuleEntity> objectRuleEntityList = inspectObjectRuleEntityList.stream().map(ins -> {
//            ins.setIsDeleted(true);
//            return ins;
//        }).collect(Collectors.toList());
//        inspectObjectRuleDao.batchUpdate(objectRuleEntityList);

    }

    /**
     *@Author gtaotao
     *@Date 2019-07-10 14:43:11
     *@Description 根据templateCode 删除检查事项
     *@Return
     **/
    @Override
    public void deleteByTemplateCode(Map<String, Object> map) {
        inspectObjectRuleDao.deleteByTemplateCode(map);
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-10 14:43:38
     *@Description 根据taskCode获取树形检查事项
     *@Return
     **/
    @Override
    public InspectObjectRuleEntity objWithTreeByTaskCode(Map<String, Object>map) {
        List<InspectObjectRuleEntity> objectRuleEntityList = this.findByTaskCode(map);
        InspectObjectRuleEntity result = new InspectObjectRuleEntity();
        for(InspectObjectRuleEntity entity : objectRuleEntityList){
            if(("0").equals(entity.getParentCode())){
                result = this.objToTree(entity, objectRuleEntityList);
                break;
            }
        }
        return result;
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-22 08:54:11
     *@Description 递归获取某个节点下的所有子节点
     *@Return
     **/
    public List<InspectObjectRuleEntity> getChildNode(List<InspectObjectRuleEntity> inspectObjectRuleEntityList, InspectObjectRuleEntity entity){
        Map<String, Object> map = new HashMap<>();
        map.put("parentCode", entity.getObjRlCode());
        map.put("cpyCode", CurrentContext.getCpyCode());
        map.put("isDeleted",false);
        List<InspectObjectRuleEntity> list = this.findByMap(map);

        inspectObjectRuleEntityList.addAll(list);

        list.stream().forEach(li -> getChildNode(inspectObjectRuleEntityList, li));
        return inspectObjectRuleEntityList;
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-22 08:54:24
     *@Description 获取每个节点的子节点
     *@Return
     **/
    public Cascader getNode(Cascader cascader, List<InspectObjectRuleEntity> cEntityList){
        List<Cascader> casecaderList = cEntityList.stream()
                .filter(e -> e.getParentCode().equals(cascader.getValue()))
                .map(p -> {
                    if(null !=p.getGrade()){
                        return new Cascader(p.getId(),p.getObjRlName()+"（ "+p.getGrade()+"分）", p.getObjRlCode(), p.getGrade(), p.getDescribe(),p.getType());
                    }else{
                        return new Cascader(p.getId(),p.getObjRlName(), p.getObjRlCode(), p.getDescribe(),p.getType());
                    }
                })
                .collect(Collectors.toList());

        if(casecaderList.size() > 0){
            cascader.setChildren(casecaderList);
        }else {
            cascader.setChildren(null);
        }

        casecaderList.stream().forEach(c -> getNode(c, cEntityList));

        return cascader;
    }

    /**
     *@Author gtaotao
     *@Date 2019-07-10 15:08:42
     *@Description 将检查事项以树形返回
     *@Return
     **/
    @Override
    public InspectObjectRuleEntity objToTree(InspectObjectRuleEntity rootEntity, List<InspectObjectRuleEntity> objectRuleEntityList){
        List<InspectObjectRuleEntity> list = new ArrayList<>();
        for(InspectObjectRuleEntity entity : objectRuleEntityList){
            if(entity.getParentCode().equals(rootEntity.getObjRlCode())){
                list.add(entity);
            }
        }
        if(list.size() > 0){
            rootEntity.setChildren(list);
        }else{
            rootEntity.setChildren(null);
        }

        for(InspectObjectRuleEntity childEntity : list){
            objToTree(childEntity,objectRuleEntityList);
        }
        return rootEntity;
    }
}
