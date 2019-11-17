package com.kfwy.park.exp.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.exp.contract.dao.ILandlordContractDbDao;
import com.kfwy.park.exp.contract.entity.LandlordContractEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description 大房东合同
 * @author luming
 * @Date 2019/4/18 18:21
 * @Version 1.0
 * @Param
 * @return
 */
@Repository
public class LandlordContractDbDaoImpl extends AbstractMyBatisDao<LandlordContractEntity, Long> implements ILandlordContractDbDao {
    @Override
    public List<LandlordContractEntity> selectDept(Map<String,Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace+"selectDept",map);
    }
}
