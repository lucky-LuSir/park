package com.kfwy.park.ati.contract.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.ati.contract.entity.ContractHouseEntity;
import org.omg.CORBA.OBJ_ADAPTER;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption 合同房源
 * @Date 2018/6/7 14:50
 */
public interface IContractHouseManager extends IManager<ContractHouseEntity> {

    /**
     * @Description 关联合同和合同房源查询
     * @author luming
     * @Date 2019/5/16 17:58
     * @Version 1.0
     * @Param [map]
     * @return com.kfwy.park.ati.contract.entity.ContractHouseEntity
     */
    List<ContractHouseEntity> selectFloorAndContractHouse(Map<String, Object> map);

    List<ContractHouseEntity> selectByFlCode(Map<String, Object> map);

    BigDecimal selectThreeMonthExpiredArea(List<String> list);
}
