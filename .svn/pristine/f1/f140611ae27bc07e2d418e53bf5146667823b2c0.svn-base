package com.kfwy.park.exp.contract.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.exp.contract.entity.LandlordContractEntity;

import java.util.Map;

/**
 * @Description 大房东合同
 * @author luming
 * @Date 2019/4/18 18:25
 * @Version 1.0
 * @Param
 * @return
 */
public interface ILandlordContractManager extends IManager<LandlordContractEntity> {
    /**
     * @Description 校验必填字段
     * @author luming
     * @Date 2019/4/19 9:24
     * @Version 1.0
     * @Param [landlordContractEntity]
     * @return void
     */
    void checkParams(LandlordContractEntity landlordContractEntity) throws IllegalAccessException;

    /**
     * @Description 新增大房东合同，同时新增租金
     * @author luming
     * @Date 2019/4/19 10:26
     * @Version 1.0
     * @Param [landlordContractEntity]
     * @return void
     */
    void createContract(LandlordContractEntity landlordContractEntity);

    /**
     * @Description 修改大房东合同，同时修改租金
     * @author luming
     * @Date 2019/4/19 10:53
     * @Version 1.0
     * @Param [landlordContractEntity]
     * @return void
     */
    void updateContract(LandlordContractEntity landlordContractEntity);

    /**
     * @Description 删除合同
     * @author luming
     * @Date 2019/4/19 11:17
     * @Version 1.0
     * @Param [landlordContractEntity]
     * @return void
     */
    void deleteContract(LandlordContractEntity landlordContractEntity);

    /**
     * @Description 查询合同同时查询租金
     * @Auth luming
     * @Date 2019/4/25 11:11
     * @Version 1.0
     * @Param
     * @return
     */
    LandlordContractEntity deatil(Map<String, Object> map);
}
