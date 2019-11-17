package com.kfwy.park.pro.house.building.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.pro.house.building.business.IBuildingManager;
import com.kfwy.park.pro.house.building.dao.IBuildingDbDao;
import com.kfwy.park.pro.house.building.dao.IFloorDbDao;
import com.kfwy.park.pro.house.building.dao.IParkDbDao;
import com.kfwy.park.pro.house.building.entity.BuildingEntity;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by luming on 2018/5/29.
 */
@Service
public class BuildingManagerImpl extends AbstractManager<BuildingEntity> implements IBuildingManager {
    @Autowired
    private IBuildingDbDao buildingDbDao;

    @Autowired
    private IFloorDbDao floorDbDao;

    @Autowired
    private IParkDbDao parkDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.buildingDbDao;
    }

    @Override
    protected void beforeCreate(BuildingEntity buildingEntity) {
        buildingEntity.setBuildingStatus("1");
    }


    /**
     * @return void
     * @Description 更新前操作
     * @Auth wangchuang
     * @Date 2018/7/14 11:45
     * @Version 1.0
     * @Param [buildingEntity]
     **/
    @Override
    protected void beforeUpdate(BuildingEntity buildingEntity) {
        Map<String, Object> param = new HashMap<String, Object>();
        if (buildingEntity != null && buildingEntity.getBdCode() != null) {
            String bdName = buildingEntity.getBdName();
            param.put("id", buildingEntity.getId());
            param.put("bdCode", buildingEntity.getBdCode());
            param.put("isDeleted", false);
            param.put("cpyCode", CurrentContext.getCpyCode());
            BuildingEntity oldBdEntity = buildingDbDao.selectUniqueByMap(param);
            if (oldBdEntity != null) {
                if (!bdName.equals(oldBdEntity.getBdName())) {
                    //同步floor表的pkName
                    updateFloorBdName(buildingEntity);
                }
            }
        }


        /**
         * 修改楼栋时，如果修改的楼层数小于当前楼栋实际存在的最大楼层数，则修改失败
         */
        String bdCode = buildingEntity.getBdCode();

        Map<String, Object> map = new HashMap<>();
        map.put("bdCode", bdCode);
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        int floorsTotal = Integer.parseInt(buildingEntity.getFloorsTotal());

        //本栋出租面积
        BigDecimal rentedArea = new BigDecimal(0);

        List<FloorEntity> floorEntityList = floorDbDao.selectByMap(map);
        if (!floorEntityList.isEmpty()) {
            int floorCount = floorEntityList.size();

            if (floorsTotal < floorCount) {
                throw new BusinessException("楼栋修改失败：该楼栋最大楼层是" + floorCount + "层");
            }
        }
        for(int i=0;i<floorEntityList.size();i++) {
            //出租面积累加
            rentedArea = rentedArea.add(floorEntityList.get(i).getRentedArea());
        }

        /**
         * 修改楼栋时，如果总面积小于已出租的面积，则修改失败
         */

        BigDecimal investArea = buildingEntity.getInvestArea();

        buildingEntity.setRentableArea(investArea.subtract(rentedArea));

        int result = investArea.compareTo(rentedArea);

        if (result == -1) {
            throw new BusinessException("楼栋修改失败：该楼栋的承租面积小于已租面积");
        }


    }

    /**
     * @return void
     * @Description 更改floor表中的bdName
     * @Auth wangchuang
     * @Date 2018/7/14 9:25
     * @Version 1.0
     * @Param [parkEntity]
     **/
    public void updateFloorBdName(BuildingEntity buildingEntity) {

        /**
         * 同步楼层表中bdName:
         */

        FloorEntity flEntity = new FloorEntity();
        flEntity.setBdCode(buildingEntity.getBdCode());
        flEntity.setBdName(buildingEntity.getBdName());

        floorDbDao.updateBypkCode(flEntity);
    }


    /***
     * @Description 根据bdCode查询楼栋详情
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public BuildingEntity details(Map<String, Object> map) throws Exception {
        BuildingEntity buildingEntity = new BuildingEntity();

        try {
            buildingEntity = buildingDbDao.selectUniqueByMap(map);
            /*
             * 跟据楼层集合楼层排序,集合内下标与楼层数相对应
             * 0:1层数据  1:2层数据  2:3层数据......
             * updateBy wangchuang  2018-6-29
             */
            int totals = Integer.parseInt(buildingEntity.getFloorsTotal());
            List<FloorEntity> list = floorDbDao.selectByMap(map, "cast(this_floor as int)", true);
            List<FloorEntity> paramList = new ArrayList<>();
            FloorEntity paramObj = null;
            if (!list.isEmpty()) {
                for (int i = 0, j = 0; i < totals; i++) {
                    if (list.size() >= (j + 1)) {
                        if (Integer.parseInt(list.get(j).getThisFloor()) == (i + 1)) {
                            paramList.add(list.get(j));
                            j++;
                            continue;
                        }
                    }
                    paramObj = new FloorEntity();
                    paramObj.setPkCode(buildingEntity.getPkCode());
                    paramObj.setPkName(buildingEntity.getPkName());
                    paramObj.setBdCode(buildingEntity.getBdCode());
                    paramObj.setBdName(buildingEntity.getBdName());
                    paramObj.setThisFloor(String.valueOf((i + 1)));
                    paramList.add(paramObj);
                }
            } else {
                for (int i = 0, j = 0; i < totals; i++) {
                    paramObj = new FloorEntity();
                    paramObj.setPkCode(buildingEntity.getPkCode());
                    paramObj.setPkName(buildingEntity.getPkName());
                    paramObj.setBdCode(buildingEntity.getBdCode());
                    paramObj.setBdName(buildingEntity.getBdName());
                    paramObj.setThisFloor(String.valueOf((i + 1)));
                    paramList.add(paramObj);
                }
            }
            buildingEntity.setFloorList(paramList);
        } catch (Exception e) {
            throw new Exception("查询到多条数据");
        }

        return buildingEntity;
    }

    /***
     * @Description 查询园区所有楼栋
     * @Auth luming
     * @Date 2018/6/15 11:03
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<BuildingEntity> parkAllBuilding(Map<String, Object> map) {
        ParkEntity parkEntity = parkDbDao.selectUniqueByMap(map);
        BigDecimal bdTotal = parkEntity.getBuildingTotal();
        int buildingTotal = bdTotal.intValue();
        List<BuildingEntity> bdList = buildingDbDao.selectByMap(map, "cast(this_building as int)", true);
        List<BuildingEntity> list = new ArrayList<>();
        if (!bdList.isEmpty()) {
            for (int i = 0, j = 0; i < buildingTotal; i++) {
                if (bdList.size() >= (j + 1)) {
                    if (Integer.parseInt(bdList.get(j).getThisBuilding()) == (i + 1)) {
                        list.add(bdList.get(j));
                        j++;
                        continue;
                    }
                }
                BuildingEntity buildingEntity = new BuildingEntity();
                buildingEntity.setThisBuilding(String.valueOf((i + 1)));
                list.add(buildingEntity);
            }
        }
        return list;
    }

    /**
     * @return
     * @Description 创建楼栋时校验楼栋面积是否大于园区面积
     * @Auth luming
     * @Date 2018/8/25 14:36
     * @Version 1.0
     * @Param
     */
    @Override
    public Boolean acreage(BuildingEntity buildingEntity) throws Exception {
        Boolean flag = true;

        Map<String, Object> map = new HashMap<>();
        map.put("pkCode", buildingEntity.getPkCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        // 获取园区总面积
        ParkEntity parkEntity = parkDbDao.selectUniqueByMap(map);
        int pkInvestArea = parkEntity.getInvestArea().intValue();

        // 用户输入的楼栋面积
        int bdInvestArea = buildingEntity.getInvestArea().intValue();

        //判断前端输入楼栋面积是否大于园区面积
        if (bdInvestArea > pkInvestArea) {
            flag = false;
        } else {
            List<BuildingEntity> buildingEntityList = buildingDbDao.selectByMap(map);

            int bdInvestAreaSum = 0;// 新增楼栋，循环所有面积之和，与园区面积作比较

            // bdCode为null，新增楼栋
            if (buildingEntity.getBdCode() == null) {
                for (int i = 0; i < buildingEntityList.size(); i++) {
                    BigDecimal bigDecimalBdInvestArea = buildingEntityList.get(i).getInvestArea();
                    int intBdInvestArea = bigDecimalBdInvestArea.intValue();
                    bdInvestAreaSum += intBdInvestArea;
                }

                if ((bdInvestArea + bdInvestArea) > pkInvestArea) {
                    flag = false;
                }
            } else { // 修改楼栋
                for (int i = 0; i < buildingEntityList.size(); i++) {
                    String bdCode = buildingEntity.getBdCode();
                    String bdCode1 = buildingEntityList.get(i).getBdCode();

                    if (!bdCode.equals(bdCode1)) {
                        BigDecimal bigDecimalBdInvestArea = buildingEntityList.get(i).getInvestArea();
                        int intBdInvestArea = bigDecimalBdInvestArea.intValue();
                        bdInvestAreaSum += intBdInvestArea;
                    }
                }

                if ((bdInvestAreaSum + bdInvestArea) > pkInvestArea) {
                    flag = false;
                }
            }
        }
        return flag;
    }
}
