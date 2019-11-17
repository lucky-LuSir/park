package com.kfwy.park.pro.house.building.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.pro.house.bi.business.IParkBiManager;
import com.kfwy.park.pro.house.bi.entity.ParkBiEntity;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.dao.IBuildingDbDao;
import com.kfwy.park.pro.house.building.dao.IFloorDbDao;
import com.kfwy.park.pro.house.building.dao.IParkDbDao;
import com.kfwy.park.pro.house.building.entity.BuildingEntity;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/16.
 */
@Service
@Transactional
public class ParkManagerImpl extends AbstractManager<ParkEntity> implements IParkManager {

    @Autowired
    private IParkDbDao parkDbDao;
    @Autowired
    private IBuildingDbDao buildingDbDao;
    @Autowired
    private IFloorDbDao floorDbDao;
    @Autowired
    private IParkBiManager parkBiManager;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.parkDbDao;
    }

    /*
         * @Description 创建园区前需要的操作
         * @Auth liuzhengyang
         * @Date 2018/6/27 18:54
         * @Version 1.0
         * @Param [entity]
         * @return void
         */
    @Override
    protected void beforeCreate(ParkEntity parkEntity) {
        //rentableArea(可租用含公摊面积) = investArea(园区总含公摊面积)
        parkEntity.setRentableArea(parkEntity.getInvestArea());
        //useElecSurplus(剩余电量) = useElecTotal(总用电容量)
        parkEntity.setUseElecSurplus(parkEntity.getUseElecTotal());
        //出租含公摊面积字段默认为0
        parkEntity.setRentInvestArea(new BigDecimal(0));
    }

    /*
     * @Description 创建园区后需要的操作
     * @Auth liuzhengyang
     * @Date 2018/6/27 18:54
     * @Version 1.0
     * @Param [entity]
     * @return void
     */
    @Override
    protected void afterCreate(ParkEntity parkEntity) {

        createParkBi(parkEntity);
    }

    /**
     * @Description 更新前操作
     * @Auth wangchuang
     * @Date 2018/7/14 11:42
     * @Version 1.0
     * @Param [parkEntity]
     * @return void
     **/
    @Override
    protected void beforeUpdate(ParkEntity parkEntity) {
        Map<String, Object> param = new HashMap<String, Object>();

        if (parkEntity != null && parkEntity.getPkCode() != null) {
            String pkName = parkEntity.getPkName();

            param.put("id",parkEntity.getId());
            param.put("pkCode", parkEntity.getPkCode());
            param.put("isDeleted", false);
            param.put("cpyCode", CurrentContext.getCpyCode());

            ParkEntity oldPark = parkDbDao.selectUniqueByMap(param);

            if (oldPark != null) {
                if (!pkName.equals(oldPark.getPkName())) {
                    //同步building表的pkName
                    updateBuildingPkName(parkEntity);

                    //同步floor表的pkName
                    updateFloorPkName(parkEntity);
                }
                //招商面积和园区电量更改
                updateAreaAndEle(parkEntity,oldPark);
            }
        }


        String pkCode = parkEntity.getPkCode();
        Map<String, Object> map = new HashMap<>();
        map.put("pkCode", pkCode);
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParkEntity oldPark = parkDbDao.selectUniqueByMap(map);// 查询园区原始数据


        if (parkEntity.getBuildingTotal().compareTo(oldPark.getBuildingTotal()) != 0) {
            /**
             * 查询当前园区下实际存在的楼栋数
             * 园区修改：当修改的楼栋数小于实际存在的楼栋数，则无法修改成功
             */
            int bdCount = buildingDbDao.selectCount("countByMap", map);
            int buildingTotal = parkEntity.getBuildingTotal().intValue();

            if (buildingTotal < bdCount) {
                throw new BusinessException("园区修改失败：该园区已添加了" + bdCount + "栋建筑");
            }


            // 批量修改楼栋中this_building
            List<BuildingEntity> buildingEntityList = buildingDbDao.selectByMap(map);
            for (int i = 0; i < buildingEntityList.size(); i++) {
                buildingEntityList.get(i).setThisBuilding(String.valueOf(i + 1));
                buildingDbDao.batchUpdate(buildingEntityList);
            }
        }
    }

    /**
     * @Description 更新后的操作
     * @Auth wangchuang
     * @Date 2018/7/13 16:12
     * @Version 1.0
     * @Param [t]
     * @return void
     **/
    @Override
    protected void afterUpdate(ParkEntity parkEntity) {

        //同步parkbi表中存在的冗余字段
        updateBiPark(parkEntity);

    }

    /**
     * @Description 更改floor表中的pkName
     * @Auth wangchuang
     * @Date 2018/7/14 9:25
     * @Version 1.0
     * @Param [parkEntity]
     * @return void
     **/
    public void updateFloorPkName(ParkEntity parkEntity) {

        /**
         * 同步楼层表中pkName:
         */

        FloorEntity flEntity = new FloorEntity();
        flEntity.setPkCode(parkEntity.getPkCode());
        flEntity.setPkName(parkEntity.getPkName());

        floorDbDao.updateBypkCode(flEntity);
    }

    /**
     * @Description 更改building表中的pkName
     * @Auth wangchuang
     * @Date 2018/7/13 18:16
     * @Version 1.0
     * @Param []
     * @return void
     **/
    public void updateBuildingPkName(ParkEntity parkEntity) {
        /**
         * 同步楼栋表中pkName:
         */

        BuildingEntity bdEntity = new BuildingEntity();

        bdEntity.setPkCode(parkEntity.getPkCode());
        bdEntity.setPkName(parkEntity.getPkName());

        buildingDbDao.updateBypkCode(bdEntity);
    }

    public void updateAreaAndEle(ParkEntity parkEntity,ParkEntity oldPark) {
        /*
        * 1. 更改是否小于已出租面积
        * 2. 将修改的差值添加到可招商面积中
        */

        //已租面积
        BigDecimal rentArea = parkEntity.getRentInvestArea();

        if (parkEntity.getInvestArea().floatValue() < rentArea.floatValue()) {
            throw new BusinessException("修改失败：计租面积小于已出租面积");
        } else if (parkEntity.getInvestArea().floatValue() >= rentArea.floatValue()) {
            //将改变的面积差值计算到可招商面积中
            parkEntity.setRentableArea(parkEntity.getInvestArea().subtract(rentArea));
        }

        /*
        * 1. 是否小于剩余电量
        * 2. 将修改的差值添加到剩余电量中
        */

        //使用的电量
//        BigDecimal rentEle = oldPark.getUseElecTotal().subtract(oldPark.getUseElecSurplus());
//
//        if (parkEntity.getUseElecTotal().floatValue() < rentEle.floatValue()) {
//            throw new BusinessException("修改失败:修改的总电量小于已使用电量");
//        } else if (parkEntity.getUseElecTotal().floatValue() >= rentEle.floatValue()) {
//            parkEntity.setUseElecSurplus(parkEntity.getUseElecSurplus().add(parkEntity.getUseElecTotal().subtract(oldPark.getUseElecTotal())));
//        }
    }

    /**
     * @Description 更改Parkbi表中存在的冗余字段
     * @Auth wangchuang
     * @Date 2018/7/13 18:46
     * @Version 1.0
     * @Param [parkEntity]
     * @return void
     **/
    public void updateBiPark(ParkEntity parkEntity) {
        /**
         * 同步parkbi表中冗余字段:
         */

        if (parkEntity != null && parkEntity.getPkCode() != null) {
            ParkBiEntity pkBiEntity = new ParkBiEntity();
            Map<String, Object> param = new HashMap<String, Object>();

            param.put("pkCode",parkEntity.getPkCode());
            param.put("isDeleted",false);
            param.put("cpyCode", CurrentContext.getCpyCode());

            List<ParkBiEntity> parkBiList = parkBiManager.findByMap(param);

            if (parkBiList.get(0) != null) {
                pkBiEntity.setId(parkBiList.get(0).getId());
                pkBiEntity.setPkCode(parkEntity.getPkCode());
                pkBiEntity.setPkName(parkEntity.getPkName());
                //园区承租面积
                pkBiEntity.setAcreage(parkEntity.getAcreage());
                //剩余计租面积（含公摊）
                pkBiEntity.setRentableArea(parkEntity.getRentableArea());
                //出租计租面积
                pkBiEntity.setRentInvestArea(parkEntity.getRentInvestArea());
                //计算空置率
                pkBiEntity.setAreaProp(pkBiEntity.getRentableArea().divide(parkEntity.getInvestArea(),4,BigDecimal.ROUND_HALF_UP));
                //公摊比
                pkBiEntity.setAreaRate(parkEntity.getAreaRate());
                //楼栋总数
                pkBiEntity.setBuildingTotal(parkEntity.getBuildingTotal());
                //每月抄表日
                pkBiEntity.setMeterData(parkEntity.getMeterReading());
                //园区剩余电量
                pkBiEntity.setUseElecSurplus(parkEntity.getUseElecSurplus());

                /**
                 * 省、市、区、街道、地址
                 */
                pkBiEntity.setProvinceName(parkEntity.getProvinceName());
                pkBiEntity.setCityName(parkEntity.getCityName());
                pkBiEntity.setRegionName(parkEntity.getRegionName());
                pkBiEntity.setStreetName(parkEntity.getStreetName());
                pkBiEntity.setAddress(parkEntity.getAddress());

                parkBiManager.update(pkBiEntity);
            }

        }
    }

    /*
     * @Description 创建园区时,同时创建该园区的园区统计表
     * @Auth liuzhengyang
     * @Date 2018/6/27 18:55
     * @Version 1.0
     * @Param [entity]
     * @return void
     */
    private void createParkBi(ParkEntity entity) {
        ParkBiEntity parkBiEntity = new ParkBiEntity();
        parkBiEntity.setPkCode(entity.getPkCode());
        parkBiEntity.setPkName(entity.getPkName());
        //省  市  区  街道  详细地址
        parkBiEntity.setProvinceName(entity.getProvinceName());
        parkBiEntity.setCityName(entity.getCityName());
        parkBiEntity.setRegionName(entity.getRegionName());
        parkBiEntity.setStreetName(entity.getStreetName());
        parkBiEntity.setAddress(entity.getAddress());
        //园区承租面积
        parkBiEntity.setAcreage(entity.getAcreage());
        //出租计租面积
        parkBiEntity.setRentInvestArea(new BigDecimal("0"));
        //空置面积
        parkBiEntity.setRentableArea(entity.getRentableArea());
        //空置率
        parkBiEntity.setAreaProp(parkBiEntity.getRentableArea().divide(entity.getInvestArea(), 4,BigDecimal.ROUND_HALF_UP));
        //公摊比
        parkBiEntity.setAreaRate(entity.getAreaRate());
        //15天内应缴租金户
        parkBiEntity.setRentWarningCount(0);
        //楼栋总数
        parkBiEntity.setBuildingTotal(entity.getBuildingTotal());
        //2个月内合同到期户
        parkBiEntity.setCtWarningCount(0);
        //费用逾期数
        parkBiEntity.setOverdueCount(0);
        //剩余电量
        parkBiEntity.setUseElecSurplus(entity.getUseElecSurplus());
        //抄表日期
        parkBiEntity.setMeterData(entity.getMeterReading());
        //所属大区(部门编号)
        parkBiEntity.setTerritoryType(entity.getTerritoryType());
        //所属大区名称
        parkBiEntity.setTerritoryTypeName(entity.getTerritoryTypeName());


        parkBiManager.create(parkBiEntity);
    }

    /**
     * @Description 通过pkCode查询楼栋集合，并且和楼栋总数匹配
     * @Auth xiongzhan
     * @Date 2018/6/30 15:43
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public ParkEntity details(Map<String,Object> map) throws Exception {
        ParkEntity parkEntity = null;

        try{
            parkEntity = parkDbDao.selectUniqueByMap(map);

            if (parkEntity == null) {
                throw new BusinessException("查询园区信息为空");
            }
            int totals =parkEntity.getBuildingTotal().intValue();

            List<BuildingEntity> resList = new ArrayList<BuildingEntity>();

            Map<String,Object> mapByCode = null;
            //该园区有数据的楼栋信息
            List<BuildingEntity> bdList =buildingDbDao.selectByMap(map,"cast(this_building as int)",true);
            //查询的楼栋信息包括楼层
            List<BuildingEntity> returnBdList = new ArrayList<BuildingEntity>();
            if (!bdList.isEmpty()) {
                for (BuildingEntity item :bdList) {
                    mapByCode = new HashMap<String, Object>();
                    mapByCode.put("bdCode",item.getBdCode());
                    mapByCode.put("isDeleted",false);
                    mapByCode.put("cpyCode", CurrentContext.getCpyCode());

                    returnBdList.add(detailsBybdCode(mapByCode));
                }

                //重组新集合
                for (int i=0,j = 0; i<totals;i++) {
                    if (returnBdList.size() >= (j+1)) {
                        if (Integer.parseInt(returnBdList.get(j).getThisBuilding())==(i+1)){
                            resList.add(returnBdList.get(j));
                            j++;
                            continue;
                        }
                    }
                    BuildingEntity paramObj = new BuildingEntity();
                    paramObj.setPkCode(map.get("pkCode").toString());
                    paramObj.setThisBuilding(String.valueOf((i+1)));
                    resList.add(paramObj);
                }
            }

            parkEntity.setBuildingList(resList);
        }catch (Exception e){
            throw new BusinessException("查询园区下楼栋信息失败");
        }

        return parkEntity;
    }

    /**
     * @Description 根据楼栋编号查询相应层信息
     * @Auth wangchuang
     * @Date 2018/7/3 9:27
     * @Version 1.0
     * @Param [map]
     * @return com.kfwy.park.pro.house.building.entity.BuildingEntity
     **/
    public BuildingEntity detailsBybdCode(Map<String, Object> map) throws Exception {
        BuildingEntity buildingEntity = new BuildingEntity();

        try{
            buildingEntity = buildingDbDao.selectUniqueByMap(map);
            /*
             * 跟据楼层集合楼层排序,集合内下标与楼层数相对应
             * 0:1层数据  1:2层数据  2:3层数据......
             * updateBy wangchuang  2018-6-29
             */
            int totals = Integer.parseInt(buildingEntity.getFloorsTotal());
            List<FloorEntity> list = floorDbDao.selectByMap(map,"cast(this_floor as int)",true);
            List<FloorEntity> paramList = new ArrayList<>();
            if (!list.isEmpty()) {
                for (int i=0,j = 0; i<totals;i++) {
                    if (list.size()>=(j+1)) {
                        if (Integer.parseInt(list.get(j).getThisFloor())==(i+1)){
                            paramList.add(list.get(j));
                            j++;
                            continue;
                        }
                    }
                    FloorEntity paramObj = new FloorEntity();
                    paramObj.setBdCode(map.get("bdCode").toString());
                    paramObj.setThisFloor(String.valueOf((i+1)));
                    paramList.add(paramObj);
                }
            }
            buildingEntity.setFloorList(paramList);
        }catch (Exception e){
            throw new BusinessException("查询到多条数据");
        }

        return buildingEntity;
    }

    /**
     * @Description 查询所有园区总面积，可租用面积之和
     * @Auth luming
     * @Date 2018/9/25 15:14
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public ParkEntity getAllParkSum(Map<String, Object> map) {
        ParkEntity allParkSum = parkDbDao.getAllParkSum(map);
        return allParkSum;
    }

    /**
     * @Description 查询园区所属城市
     * @Auth luming
     * @Date 2018/10/17 11:11
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<ParkEntity> selectParkCity(Map<String, Object> map) {
        return parkDbDao.selectParkCity(map);
    }

    /**
     * 获取所有大区
     * @param map
     * @return
     */
    @Override
    public List<ParkEntity> selectAllTerritory(Map<String,Object> map){
        return parkDbDao.selectAllTerritory(map);
    }
}
