package com.kfwy.park.ati.contract.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.entity.ContractHouseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption 合同房源
 * @Date 2018/6/7 14:45
 */
public interface IContractHouseDbdao extends IMyBatisBaseDao<ContractHouseEntity,Long> {
    public static final String SELECT_FLOOR_AND_CONTRACT_HOUSE = "selectFloorAndContractHouse";
    public static final String SELECT_INVEST_SRREA_BY_FL_CODE = "selectInvestAreaByFlCode";
    public static final String SELECT_BY_FLCODE = "selectByFlCode";
    public static final String SELECT_THREE_MONTH_EXPIRED_AREA ="selectThreeMonthExpiredArea";

    /**
     * @Description 关联合同和合同房源查询
     * @author luming
     * @Date 2019/5/16 17:58
     * @Version 1.0
     * @Param [map]
     * @return com.kfwy.park.ati.contract.entity.ContractHouseEntity
     */
    List<ContractHouseEntity> selectFloorAndContractHouse(Map<String, Object> map);

    List<ContractHouseEntity> selectInvestAreaByFlCode(Map<String,Object> map);

    List<ContractHouseEntity> selectByFlCode(Map<String, Object> map);

    BigDecimal selectThreeMonthExpiredArea(List<String> list);
}
