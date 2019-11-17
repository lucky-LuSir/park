package com.kfwy.park.exp.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.exp.contract.business.ILandlordContractManager;
import com.kfwy.park.exp.contract.dao.ILandlordContractDbDao;
import com.kfwy.park.exp.contract.dao.ILandlordRentDao;
import com.kfwy.park.exp.contract.entity.LandlordContractEntity;
import com.kfwy.park.exp.contract.entity.LandlordRentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description 大房东合同
 * @author luming
 * @Date 2019/4/18 18:25
 * @Version 1.0
 * @Param
 * @return
 */
@Transactional
@Service
public class LandlordContractManagerImpl extends AbstractManager<LandlordContractEntity> implements ILandlordContractManager {
    @Autowired
    private ILandlordContractDbDao landlordContractDbDao;
    @Autowired
    private ILandlordRentDao landlordRentDao;

    @Override
    protected IMyBatisBaseDao<LandlordContractEntity, Long> getMyBatisDao() {
        return this.landlordContractDbDao;
    }

    /**
     * @Description 校验必填字段
     * @author luming
     * @Date 2019/4/19 9:25
     * @Version 1.0
     * @Param [landlordContractEntity]
     * @return void
     */
    @Override
    public void checkParams(LandlordContractEntity landlordContractEntity) throws IllegalAccessException {
        List<String> param = new ArrayList<>();
        param.add("pkCode");
        param.add("acreage");
        param.add("landlordName");
        param.add("landlordSex");
        param.add("landlordPhone");
        param.add("contractorCode");
        param.add("contractorPhone");
        param.add("contractStartDate");
        param.add("contractEndDate");
        param.add("advanceDay");
        param.add("rentUnivalence");
        param.add("rentUnit");
        param.add("depositRule");
        param.add("deposit");
        param.add("deposit");
        param.add("payRule");

        if (landlordContractEntity.getHasDetailAdd() == true) {
            param.add("progressiveIncreaseDetail");
        } else if (landlordContractEntity.getHasDetailAdd() == false) {
            param.add("rentIncreasingCycle");
            param.add("rentIncreaseProportion");
        }

        ParamUtil<LandlordContractEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(landlordContractEntity, param);
    }

    /**
     * @Description 新增大房东合同，同时新增租金
     * @author luming
     * @Date 2019/4/19 10:27
     * @Version 1.0
     * @Param [landlordContractEntity]
     * @return void
     */
    @Override
    public void createContract(LandlordContractEntity landlordContractEntity) {
        OperateFillUtils.fill(landlordContractEntity);
        landlordContractDbDao.insert(landlordContractEntity);

        List<LandlordRentEntity> landlordRentEntityList = landlordContractEntity.getLandlordRentEntityList();
        if (landlordRentEntityList != null) {
            for (LandlordRentEntity landlordRentEntity : landlordRentEntityList) {
                OperateFillUtils.fill(landlordRentEntity);
                landlordRentEntity.setRentCode(CodeGenUtils.generate());
                landlordRentEntity.setLdCode(landlordContractEntity.getLdCode());
                landlordRentEntity.setPayStatus("1");
            }
            landlordRentDao.batchInsert(landlordContractEntity.getLandlordRentEntityList());
        }
    }

    /**
     * @Description 修改大房东合同，同时修改租金
     * @author luming
     * @Date 2019/4/19 10:53
     * @Version 1.0
     * @Param [landlordContractEntity]
     * @return void
     */
    @Override
    public void updateContract(LandlordContractEntity landlordContractEntity) {
        landlordContractDbDao.update(landlordContractEntity);
        landlordRentDao.batchUpdate(landlordContractEntity.getLandlordRentEntityList());
    }

    /**
     * @Description 删除合同
     * @author luming
     * @Date 2019/4/19 11:17
     * @Version 1.0
     * @Param [landlordContractEntity]
     * @return void
     */
    @Override
    public void deleteContract(LandlordContractEntity landlordContractEntity) {
        landlordContractEntity.setIsDeleted(true);
        landlordContractDbDao.update(landlordContractEntity);

        List<LandlordRentEntity> landlordRentEntityList = landlordContractEntity.getLandlordRentEntityList();
        if (landlordRentEntityList.size() != 0) {
            for (LandlordRentEntity landlordRentEntity : landlordRentEntityList) {
                landlordRentEntity.setIsDeleted(true);
            }
            landlordRentDao.batchInsert(landlordContractEntity.getLandlordRentEntityList());
        }
    }

    /**
     * @Description 查询合同同时查询租金
     * @Auth luming
     * @Date 2019/4/25 11:11
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public LandlordContractEntity deatil(Map<String, Object> map) {
        LandlordContractEntity contractEntity = landlordContractDbDao.selectUniqueByMap(map);
        List<LandlordRentEntity> landlordRentList = landlordRentDao.selectByMap(map);
        contractEntity.setLandlordRentEntityList(landlordRentList);
        return contractEntity;
    }
}
