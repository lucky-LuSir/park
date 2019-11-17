package com.kfwy.park.ati.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.contract.dao.IContractHouseDbdao;
import com.kfwy.park.ati.contract.entity.ContractHouseEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption 合同房源
 * @Date 2018/6/7 14:47
 */
@Repository
public class ContractHouseDbdaoImpl extends AbstractMyBatisDao<ContractHouseEntity,Long> implements IContractHouseDbdao {

    /**
     * @Description 关联合同和合同房源查询
     * @author luming
     * @Date 2019/5/16 17:57
     * @Version 1.0
     * @Param [map]
     * @return com.kfwy.park.ati.contract.entity.ContractHouseEntity
     */
    @Override
    public List<ContractHouseEntity> selectFloorAndContractHouse(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_FLOOR_AND_CONTRACT_HOUSE, map);
    }

    @Override
    public List<ContractHouseEntity> selectInvestAreaByFlCode(Map<String,Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_INVEST_SRREA_BY_FL_CODE, map);
    }

    @Override
    public List<ContractHouseEntity> selectByFlCode(Map<String, Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_BY_FLCODE, map);
    }

    @Override
    public BigDecimal selectThreeMonthExpiredArea(List<String> list) {
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_THREE_MONTH_EXPIRED_AREA, list);
    }
}
