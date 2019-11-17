package com.kfwy.park.pro.house.building.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.pro.house.building.business.IFloorManager;
import com.kfwy.park.pro.house.building.dao.IBuildingDbDao;
import com.kfwy.park.pro.house.building.dao.IFloorDbDao;
import com.kfwy.park.pro.house.building.entity.BuildingEntity;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Descirption ${DESCRIPTION}
 * @Date 2018/5/30 9:18
 */
@Service
public class FloorManagerImpl extends AbstractManager<FloorEntity> implements IFloorManager {

    @Autowired
    private IFloorDbDao floorDbDao;

    @Autowired
    private IBuildingDbDao buildingDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.floorDbDao;
    }

    /**
     * @return void
     * @Description 创建楼层前需要的操作
     * @Auth wangchuang
     * @Date 2018/7/13 14:21
     * @Version 1.0
     * @Param [parkEntity]
     **/
    @Override
    protected void beforeCreate(FloorEntity floorEntity) {
        //将新增楼层状态置为"热租中"
        floorEntity.setFloorStatus("1");
    }

    /**
     * @return void
     * @Description 创建楼层后需要的操作
     * @Auth wangchuang
     * @Date 2018/7/13 14:21
     * @Version 1.0
     * @Param [parkEntity]
     **/
    @Override
    protected void afterCreate(FloorEntity floorEntity) {

    }

    @Override
    protected void beforeUpdate(FloorEntity floorEntity) {

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("flCode", floorEntity.getFlCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        FloorEntity oldFlEntity = floorDbDao.selectUniqueByMap(map);
        //找出修改前数据的已出租面积和新修改的面积做对比
        BigDecimal oldRentedArea = oldFlEntity.getRentedArea();
        if(floorEntity.getInvestArea().compareTo(oldRentedArea) < 0) {
            throw new BusinessException("修改本层面积小于已出租面积，请确认后修改");
        }
        //算出新的剩余面积
        BigDecimal rentableArea = floorEntity.getInvestArea().subtract(floorEntity.getRentedArea());
        floorEntity.setRentableArea(rentableArea);
    }

    /**
     * @return com.kfwy.park.pro.house.building.entity.FloorEntity
     * @Description 查询一条详情
     * @Auth wangchuang
     * @Date 2018/6/22 15:40
     * @Version 1.0
     * @Param [param]
     **/
    @Override
    public FloorEntity findDetails(Map<String, Object> param) throws Exception {
        try {
            FloorEntity res = floorDbDao.selectUniqueByMap(param);
            return res;
        } catch (Exception e) {
            throw new Exception("查询出多条详情数据");
        }
    }

    /**
     * @return
     * @Description 创建楼层时验证本栋的所有楼层面积是否大于楼栋面积
     * @Auth luming
     * @Date 2018/8/25 14:36
     * @Version 1.0
     * @Param
     */
    @Override
    public Boolean acreage(FloorEntity floorEntity) throws Exception {
        Boolean flag = true;

        Map<String, Object> map = new HashMap<>();
        map.put("bdCode", floorEntity.getBdCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        //获取某栋楼的面积
        BuildingEntity buildingEntity = buildingDbDao.selectUniqueByMap(map);
        BigDecimal bigDecimalBdInvestArea = buildingEntity.getInvestArea();
        int intBdInvestArea = bigDecimalBdInvestArea.intValue();

        //前端输入的楼层面积
        BigDecimal flInvestArea = floorEntity.getInvestArea();
        int flInvestArea1 = flInvestArea.intValue();

        if (flInvestArea1 > intBdInvestArea) {
            flag = false;
        } else {
            List<FloorEntity> floorEntityList = floorDbDao.selectByMap(map);

            int flInvestAreaSum = 0;

            // 新增楼层
            if (floorEntity.getFlCode() == null) {
                for (int i = 0; i < floorEntityList.size(); i++) {
                    BigDecimal bigDecimalFlInvestArea = floorEntityList.get(i).getInvestArea();
                    int intFlInvestArea = bigDecimalFlInvestArea.intValue();
                    flInvestAreaSum += intFlInvestArea;
                }

                if ((flInvestAreaSum + flInvestArea1) > intBdInvestArea) {
                    flag = false;
                }
            } else {    // 修改楼层
                for (int i = 0; i < floorEntityList.size(); i++) {
                    String flCode = floorEntity.getFlCode();
                    String flCode1 = floorEntityList.get(i).getFlCode();

                    if (!flCode.equals(flCode1)) {
                        BigDecimal bigDecimalFlInvestArea = floorEntityList.get(i).getInvestArea();
                        int intFlInvestArea = bigDecimalFlInvestArea.intValue();
                        flInvestAreaSum += intFlInvestArea;
                    }
                }

                if ((flInvestAreaSum + flInvestArea1) > intBdInvestArea) {
                    flag = false;
                }
            }
        }
        return flag;
    }

    /**
     *@Author gengtao
     *@Date 2019-06-27 18:05:17
     *@Description 根据园区查找floor信息
     *@Return
     **/
    @Override
    public List<FloorEntity> selectWithPark(Map<String, Object> map){
        return floorDbDao.selectWithPark(map);
    }
}
