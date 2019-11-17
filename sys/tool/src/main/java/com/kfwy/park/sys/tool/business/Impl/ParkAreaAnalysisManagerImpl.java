package com.kfwy.park.sys.tool.business.Impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IContractManager;
import com.kfwy.park.ati.contract.dao.IContractHouseDbdao;
import com.kfwy.park.ati.contract.entity.ContractHouseEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.dao.IFloorDbDao;
import com.kfwy.park.pro.house.building.dao.IParkDbDao;
import com.kfwy.park.pro.house.building.entity.BuildingEntity;
import com.kfwy.park.pro.house.building.entity.FloorContractHouseEntity;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import com.kfwy.park.sys.tool.business.IParkAreaAnalysisManager;
import com.kfwy.park.sys.tool.business.entity.ParkAreaAnalysisEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by gengtao on 2019/5/30.
 */
@Service
public class ParkAreaAnalysisManagerImpl extends AbstractManager<ParkEntity> implements IParkAreaAnalysisManager {
    @Autowired
    private IParkDbDao parkDbDao;
    @Autowired
    private IContractManager contractManager;
    @Autowired
    private IParkManager parkManager;
    @Autowired
    private IFloorDbDao floorDbDao;
    @Autowired
    private IContractHouseDbdao contractHouseDbdao;

    @Override
    protected IMyBatisBaseDao<ParkEntity, Long> getMyBatisDao() {
        return parkDbDao;
    }

    @Override
    public List<ParkAreaAnalysisEntity> parkDatials(ParkEntity entity) throws IllegalAccessException {
        List<ParkAreaAnalysisEntity> parkAreaAnalysisEntityList = new ArrayList<>();

        Map<String, Object> floorMap = new HashMap<>();
        ParamUtil<ParkEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(entity, floorMap);
        floorMap.put("isDeleted", false);
        floorMap.put("cpyCode", CurrentContext.getCpyCode());
        List<FloorEntity> floorEntities = floorDbDao.selectWithPark(floorMap);
        floorEntities.stream().forEach(floorEntity -> {
            Map<String, Object> hoseMap = new HashMap<>();
            hoseMap.put("isDeleted", false);
            hoseMap.put("flCode", floorEntity.getFlCode());
            hoseMap.put("cpyCode", CurrentContext.getCpyCode());

            //获取合同签订面积
            List<ContractHouseEntity> contractHouseEntities = contractHouseDbdao.selectInvestAreaByFlCode(hoseMap);
            BigDecimal contractRentedArea = new BigDecimal("0");

            for(ContractHouseEntity hose :contractHouseEntities){
                contractRentedArea = contractRentedArea.add(hose.getInvestArea());
            }

            String checked = null;
            if(contractRentedArea.compareTo(floorEntity.getRentedArea()) == 0){
                checked = "正常";
            }else{
                checked = "异常";
            }

            ParkAreaAnalysisEntity analysisEntity = new ParkAreaAnalysisEntity(
                    floorEntity.getPkCode(),
                    floorEntity.getPkName(),
                    floorEntity.getBdCode(),
                    floorEntity.getBdName(),
                    floorEntity.getFlCode(),
                    floorEntity.getThisFloor(),
                    floorEntity.getInvestArea(),
                    floorEntity.getRentableArea(),
                    floorEntity.getRentedArea(),
                    contractRentedArea,
                    checked);
            parkAreaAnalysisEntityList.add(analysisEntity);
        });


        return parkAreaAnalysisEntityList;
    }
}
