package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IContractHouseManager;
import com.kfwy.park.ati.contract.dao.IContractHouseDbdao;
import com.kfwy.park.ati.contract.entity.ContractHouseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/6/7 14:50
 */
@Service
public class ContractHouseManagerImpl extends AbstractManager<ContractHouseEntity> implements IContractHouseManager {

    @Autowired
    private IContractHouseDbdao contractHouseDbdao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.contractHouseDbdao;
    }

    @Override
    protected void beforeCreate(ContractHouseEntity chEntity) {
        chEntity.setRentHosCode(CodeGenUtils.generate());
    }


    /**
     * @Description 关联合同和合同房源查询
     * @author luming
     * @Date 2019/5/16 17:58
     * @Version 1.0
     * @Param [map]
     * @return com.kfwy.park.ati.contract.entity.ContractHouseEntity
     */
    @Override
    public List<ContractHouseEntity> selectFloorAndContractHouse(Map<String, Object> map) {
        return contractHouseDbdao.selectFloorAndContractHouse(map);
    }

    @Override
    public List<ContractHouseEntity> selectByFlCode(Map<String, Object> map){
        return contractHouseDbdao.selectByFlCode(map);
    }

    @Override
    public BigDecimal selectThreeMonthExpiredArea(List<String> list) {
        return contractHouseDbdao.selectThreeMonthExpiredArea(list);
    }
}
