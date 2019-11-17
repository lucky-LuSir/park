package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.ati.contract.business.IConOperateHisManager;
import com.kfwy.park.ati.contract.business.IContractManager;
import com.kfwy.park.ati.contract.business.IRentIncrementManager;
import com.kfwy.park.ati.contract.dao.IContractDbDao;
import com.kfwy.park.ati.contract.dao.IContractHouseDbdao;
import com.kfwy.park.ati.contract.dao.IElectricityMeterDbDao;
import com.kfwy.park.ati.contract.dao.IElectricityReceiptDbDao;
import com.kfwy.park.ati.contract.dao.IPremiumDbDao;
import com.kfwy.park.ati.contract.dao.IRentIncrementDbDao;
import com.kfwy.park.ati.contract.dao.IRentReceiptDbDao;
import com.kfwy.park.ati.contract.dao.IWaterMeterDbDao;
import com.kfwy.park.ati.contract.dao.IWaterReceiptDbDao;
import com.kfwy.park.ati.contract.entity.*;
import com.kfwy.park.ati.customer.business.ICustomerManager;
import com.kfwy.park.ati.customer.business.ICustomerUpDownManager;
import com.kfwy.park.ati.customer.entity.CustomerEntity;
import com.kfwy.park.ati.customer.entity.CustomerUpDownEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.EntityCompareUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.pro.house.building.business.IBuildingManager;
import com.kfwy.park.pro.house.building.business.IFloorManager;
import com.kfwy.park.pro.house.building.business.IParkManager;
import com.kfwy.park.pro.house.building.dao.IBuildingDbDao;
import com.kfwy.park.pro.house.building.dao.IFloorDbDao;
import com.kfwy.park.pro.house.building.dao.IParkDbDao;
import com.kfwy.park.pro.house.building.entity.BuildingEntity;
import com.kfwy.park.pro.house.building.entity.FloorContractHouseEntity;
import com.kfwy.park.pro.house.building.entity.FloorEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 */
@Service
@Transactional
public class ContractManagerImpl extends AbstractManager<ContractEntity> implements IContractManager {

    @Autowired
    private IContractDbDao contractDbDao;
    @Autowired
    private IContractHouseDbdao contractHouseDbDao;
    @Autowired
    private IRentReceiptDbDao rentReceiptDbDao;
    @Autowired
    private IFloorDbDao floorDbDao;
    @Autowired
    private IFloorManager floorManager;
    @Autowired
    private IBuildingManager buildingManager;
    @Autowired
    private IParkManager parkManager;
    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    private ICustomerUpDownManager customerUpDownManager;
    @Autowired
    private IRentIncrementManager rentIncrementManager;
    @Autowired
    private IRentIncrementDbDao rentIncrementDbDao;
    @Autowired
    private IElectricityMeterDbDao iElectricityMeterDbDao;
    @Autowired
    private IWaterMeterDbDao waterMeterDbDao;
    @Autowired
    private IPremiumDbDao premiumDbDao;
    @Autowired
    private IConOperateHisManager conOperateHisManager;
    @Autowired
    private IWaterReceiptDbDao waterReceiptDbDao;
    @Autowired
    private IElectricityReceiptDbDao electricityReceiptDbDao;
    @Autowired
    private IParkDbDao parkDbDao;
    @Autowired
    private IBuildingDbDao buildingDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.contractDbDao;
    }


    @Override
    protected void beforeCreate(ContractEntity contractEntity) {
        //合同结束时间处理,指定时间的那天 23:59:59.999 的时间2018-8-6 00:00:00==2020-8-5 23:59:59.999
        Date contractEndTime = DateFormatUtil.dayEnd(contractEntity.getContractEndTime());
        Date freeRentEndTime = DateFormatUtil.dayEnd(contractEntity.getFreeRentEndTime());
        contractEntity.setContractEndTime(contractEndTime);
        contractEntity.setFreeRentEndTime(freeRentEndTime);

        //校验合同开始日期和结束日期
//        validateStartEndDate(contractEntity);

        //自动生成合同编号(000120180920001)
        autoCtName(contractEntity);


        //在新增合同之前将房源列表的出租面积统计出来给合同表的承包面积
        List<ContractHouseEntity> contractHouseList = contractEntity.getContractHouseList();
        BigDecimal totalRentalArea = new BigDecimal(0);
        for (ContractHouseEntity con : contractHouseList) {
            totalRentalArea = totalRentalArea.add(con.getInvestArea());
        }
        contractEntity.setTotalRentalArea(totalRentalArea);

        //合同状态置为待提交
        contractEntity.setCtStatus("1");
    }

    @Override
    protected void afterCreate(ContractEntity contractEntity) {

        //新增合同房源
        createCtHouse(contractEntity);

        //初始化房租物业费
//        initRentReceipt(contractEntity);

        //新增租金列表信息
        createRentReceiptInfo(contractEntity);

        //新增租金递增详细
        createIncDetail(contractEntity);

        //房源变化(层,栋,园,parkBi)
//        updateHouse(contractEntity);
//
//        //客户变化
//        updateCustomer(contractEntity);

        // 新增保险费
        createPremium(contractEntity);

        //添加电表
        try {
            addMeterList(contractEntity);
        } catch (Exception e) {
            throw new BusinessException("电表信息填写不完全");
        }

        //添加水表
        try {
            addWaterMeterList(contractEntity);
        } catch (Exception e) {
            throw new BusinessException("水表信息填写不完全");
        }
    }

    /**
     * @Description 用于合同变更
     * @author wangchuang
     * @Date 2019/4/15 15:55
     * @Version 1.0
     * @Param [contractEntity]
     * @return int
     **/
    @Override
    public int updateContract(ContractEntity contractEntity) {
        recoverContract(contractEntity, "1");// 合同关联的合同房源信息非物理删除

        /*
        * 修改二审通过的合同，在recoverContract方法中已经把合同房源表中的旧房源非物理删除了
        * 这里只需将修改后的房源新增就好了
        * */
        if (StringUtils.isEmpty(contractEntity.getDeleteType())) {
            createCtHouse(contractEntity);// 新增合同房源信息
        }

        //修改合同递增信息
        delIncDelInfo(contractEntity);//先删除本合同已经存在的递增详情
        createIncDetail(contractEntity);//再新增新的递增详情

        contractDbDao.update(contractEntity);

        // 修改合同房源
//        List<ContractHouseEntity> houseList = contractEntity.getContractHouseList();
//        contractHouseDbDao.batchUpdate(houseList);

//        if (StringUtils.isEmpty(contractEntity.getDeleteType())) {
//            updateHouse(contractEntity);// 房源变化（层、栋、园、parkBi）
//        }

        return 1;
    }

    /**
     * @Description 修改合同基本信息专用接口
     * @author wangchuang
     * @Date 2019/6/13 10:06
     * @Version 1.0
     * @Param [contractEntity]
     * @return int
     **/
    @Override
    public int basicInfoUpdate(ContractEntity contractEntity) {

        //查询出更新前的老数据
        ContractEntity oldEntity = contractDbDao.selectById(contractEntity.getId());

        String userName = CurrentContext.getUserInfo().getUserName();
        String dateStr = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());

        StringBuffer changeStr = new StringBuffer("["+userName+"在"+dateStr+"修改:");

        boolean res = EntityCompareUtil.compareObject(oldEntity, contractEntity);

        //如果存在差异
        if(res) {
            Map<String, Map<String, Object>> resultMap = EntityCompareUtil.compareFields(oldEntity, contractEntity);

            if(resultMap.size() > 0) {
                System.out.println("oldEntity与contractEntity的属性值有差异,差异结果如下：");
                Iterator<String> iterator = resultMap.keySet().iterator();

                while(iterator.hasNext()) {
                    String key = iterator.next();
                    changeStr.append("("+key+":"+resultMap.get(key).get("oldValue")+"改变成"+resultMap.get(key).get("newValue")+")---");
                }
            } else {
                System.out.println("oldEntity与customerEntity的属性值无差异");
            }
            System.out.println("存入数据库的字符串：" + changeStr);
        }

        changeStr.append("]");

        if(StringUtils.isEmpty(oldEntity.getContractEditRemark())) {
            contractEntity.setContractEditRemark(changeStr.toString());
        } else {
            StringBuffer newChangeStr = new StringBuffer(oldEntity.getContractEditRemark()).append(",").append(changeStr);
            contractEntity.setContractEditRemark(newChangeStr.toString());
        }
        return contractDbDao.update(contractEntity);
    }

    /**
     * @Description 删除合同
     * @author wangchuang
     * @Date 2019/4/15 16:38
     * @Version 1.0
     * @Param [contractEntity]
     * @return int
     **/
    @Override
    public int deleteContract(ContractEntity contractEntity) {
        recoverContract(contractEntity,"2");// 合同关联的合同房源信息非物理删除

        //修改合同递增信息
        delIncDelInfo(contractEntity);//先删除本合同已经存在的递增详情

        if (contractEntity.getIsDeleted()) {
            // 删除合同时客户状态改为跟进中
            Map<String, Object> map = new HashMap<>();
            map.put("cusCode", contractEntity.getCusCode());
            CustomerEntity customerEntity = customerManager.findOne(map);

            CustomerEntity entity = new CustomerEntity();
            entity.setId(customerEntity.getId());
            entity.setCusCode(customerEntity.getCusCode());
            entity.setCusStatus("1");
            customerManager.update(entity);

            // 合同删除后同时删除合同关联的费用详情
            RentReceiptEntity rentReceiptEntity = new RentReceiptEntity();
            rentReceiptEntity.setCtCode(contractEntity.getCtCode());
            rentReceiptEntity.setIsDeleted(true);
            rentReceiptDbDao.update(rentReceiptEntity);

            // 删除水费
            WaterReceiptEntity waterReceiptEntity = new WaterReceiptEntity();
            waterReceiptEntity.setCtCode(contractEntity.getCtCode());
            waterReceiptEntity.setIsDeleted(true);
            waterReceiptDbDao.update(waterReceiptEntity);

            // 删除电费
            ElectricityReceiptEntity electricityReceiptEntity = new ElectricityReceiptEntity();
            electricityReceiptEntity.setCtCode(contractEntity.getCtCode());
            electricityReceiptEntity.setIsDeleted(true);
            electricityReceiptDbDao.update(electricityReceiptEntity);

            // 删除保险费
            PremiumEntity premiumEntity = new PremiumEntity();
            premiumEntity.setCtCode(contractEntity.getCtCode());
            premiumEntity.setIsDeleted(true);
            premiumDbDao.update(premiumEntity);
        }
        return contractDbDao.update(contractEntity);
    }

    /**
     * @return
     * @Description 修改合同时，合同房源表关联数据进行非物理删除，并且新增新的数据
     * @Auth luming
     * @Date 2018/11/28 10:14
     * @Version 1.0
     * @Param
     * flag: 1：合同变更（历史房源非物理删除）
     *       2：删除合同（历史房源非物理删除）
     *       3：退租完成（历史房源保留）
     */
    @Override
    public void recoverContract(ContractEntity contractEntity, String flag) {
        Map<String, Object> map = new HashMap<>();
        map.put("ctCode", contractEntity.getCtCode());
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        // 查询原来的合同信息
        ContractEntity oldContractEntity = contractDbDao.selectUniqueByMap(map);
        // 获取原来的房源信息
        List<ContractHouseEntity> oldContractHouseList = contractHouseDbDao.selectByMap(map);

        /**
         * 当flag等于3(退租完成)时，执行房源面积还原（因为只有审核通过前可以删合同，
         * 审核通过前还没有去房源扣面积，故删除合同和修改功能不减房源面积）
         */
        if("3".equals(flag)) {
            // 面积恢复
            for (ContractHouseEntity oldContractHouse : oldContractHouseList) {
                Map<String, Object> params = new HashMap<>();
                params.put("flCode", oldContractHouse.getFlCode());
                params.put("bdCode", oldContractHouse.getBdCode());
                params.put("pkCode", oldContractHouse.getPkCode());
                params.put("isDeleted", false);
                params.put("cpyCode", CurrentContext.getCpyCode());

                BigDecimal investArea = oldContractHouse.getInvestArea();// 原来的房源出租面积

            /*---------------------------------------- 修改楼层 ----------------------------------------*/
                FloorEntity floorEntity = floorManager.findOne(params);

                // 楼层原始数据
                BigDecimal flRentedArea = floorEntity.getRentedArea();// 已租面积
                BigDecimal flRentableArea = floorEntity.getRentableArea();// 剩余可租面积

                // 楼层数据还原
                BigDecimal flRestoreRentedArea = flRentedArea.subtract(investArea);// 已租面积还原
                BigDecimal flRestoreRentableArea = flRentableArea.add(investArea);// 剩余可租面积还原

                floorEntity.setRentedArea(flRestoreRentedArea);
                floorEntity.setRentableArea(flRestoreRentableArea);
                floorEntity.setFloorStatus("1");
                floorManager.update(floorEntity);

            /*---------------------------------------- 修改楼栋 ----------------------------------------*/
                BuildingEntity buildingEntity = buildingManager.findOne(params);

                // 楼栋原始数据
                BigDecimal bdRentableArea = buildingEntity.getRentableArea();// 剩余可租面积

                // 楼栋数据还原
                BigDecimal bdRestoreRentableArea = bdRentableArea.add(investArea);// 剩余可租面积还原
                buildingEntity.setRentableArea(bdRestoreRentableArea);
                buildingEntity.setBuildingStatus("1");
                buildingManager.update(buildingEntity);

            /*---------------------------------------- 修改园区 ----------------------------------------*/
                ParkEntity parkEntity = parkManager.findOne(params);

                BigDecimal totalElectricity = oldContractEntity.getTotalElectricity();// 合同电量

                // 园区原始数据
                BigDecimal pkRentableArea = parkEntity.getRentableArea();// 剩余可租面积
                BigDecimal pkRentInvestArea = parkEntity.getRentInvestArea();// 出租计租面积
                BigDecimal useElecSurplus = parkEntity.getUseElecSurplus();// 园区电量

                // 园区数据还原
                BigDecimal pkRestoreRentableArea = pkRentableArea.add(investArea);// 剩余可租面积还原
                BigDecimal pkRestoreRentInvestArea = pkRentInvestArea.subtract(investArea);// 出租计租面积还原
                BigDecimal restoreElectricity = useElecSurplus.add(totalElectricity);// 电量还原

                parkEntity.setRentableArea(pkRestoreRentableArea);
                parkEntity.setRentInvestArea(pkRestoreRentInvestArea);
                parkEntity.setUseElecSurplus(restoreElectricity);
                parkManager.update(parkEntity);
            }

            /**
             * 退租完成后，客户状态修改为已退租
             * 修改于 2019-07-31
             */
            String cusCode = oldContractEntity.getCusCode();
            CustomerEntity customerEntity = customerManager.findOne("cusCode", cusCode);
            customerEntity.setCusStatus("6");// 已退租
            customerManager.update(customerEntity);
        }

        // 当flag传入为1时，就是讲房源非物理删除
        if(!("3".equals(flag))) {
            // 将原来的房源都进行非物理删除
            if (oldContractHouseList.size() > 0) {
                for (ContractHouseEntity oldContractHouse : oldContractHouseList) {
                    oldContractHouse.setIsDeleted(true);
                }
                contractHouseDbDao.batchUpdate(oldContractHouseList);
            }

        }

    }

    /**
     * @Description 删除改合同已经存在的递增详情
     * @author wangchuang
     * @Date 2019/1/3 8:48
     * @Version 1.0
     * @Param [contractEntity]
     * @return void
     **/
    private void delIncDelInfo(ContractEntity contractEntity) {
        if(StringUtils.isEmpty(contractEntity.getCtCode())) {
            throw new BusinessException("删除递增详情时传参有误");
        }

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ctCode",contractEntity.getCtCode());
        param.put("isDeleted",false);

        rentIncrementDbDao.delIncDelByCtCode(param);
    }

    /**
     * @return void
     * @Description 新增合同后>客户变化
     * @Auth liuzhengyang
     * @Date 2018/7/17 18:04
     * @Version 1.0
     * @Param [contractEntity]
     */
    private void updateCustomer(ContractEntity contractEntity) {

        CustomerEntity cus = customerManager.findOne("cusCode", contractEntity.getCusCode());

        //客户状态-已入住
        cus.setCusStatus("3");

        customerManager.update(cus);

        //同时生成客户下架记录
        CustomerUpDownEntity upDownEntity = new CustomerUpDownEntity();
        upDownEntity.setCusCode(cus.getCusCode());
        upDownEntity.setCusDownType("1");//内部成交
        upDownEntity.setCusUpdownType("2");//下架
        upDownEntity.setCusDownReason("合同成交");
        customerUpDownManager.create(upDownEntity);
    }

    /*
     * @Description 新增合同后>房源变化
     * @Auth liuzhengyang
     * @Date 2018/7/17 17:56
     * @Version 1.0
     * @Param [contractEntity]
     * @return void
     */
    private void updateHouse(ContractEntity contractEntity) {

        List<ContractHouseEntity> contractHouseList = contractEntity.getContractHouseList();

        if(contractHouseList != null && contractHouseList.size() > 0) {
            for (ContractHouseEntity ctHos : contractHouseList) {
                String flCode = ctHos.getFlCode();
                BigDecimal area = ctHos.getInvestArea();

            /*------------房间 暂不关联房源中的房间(by wangchuang)----------------*/
//            if (roomCode != null && !(roomCode.equals(""))) {
//                //修改面积
//                RoomEntity room = new RoomEntity();
//                room = roomManager.findOne("roomCode", roomCode);
//                if (room != null) {
//                    ctHos.setRoomNumber(room.getRoomNumber());
//                    if (room.getAcreage().floatValue() < area.floatValue()) {
//                        throw new BusinessException("房间面积不足");
//                    } else if (room.getAcreage().floatValue() == area.floatValue()) {
//                        room.setRoomStatus("2");
//                    }
//                    roomManager.update(room);
//                } else if (room == null) {
//                    throw new BusinessException("未查到该房间roomCode:" + roomCode);
//                }
//            }

            /*------------层----------------*/
                //修改面积
                FloorEntity fl = floorManager.findOne("flCode", flCode);
                if (fl.getRentableArea().floatValue() < area.floatValue()) {
                    throw new BusinessException("楼层面积不足");
                } else if (fl.getRentableArea().floatValue() == area.floatValue()) {
                    //若面积为0,则修改状态为已租完
                    fl.setFloorStatus("2");
                }
                fl.setRentableArea(fl.getRentableArea().subtract(area));
                fl.setRentedArea(fl.getRentedArea().add(area));

                floorManager.update(fl);

            /*------------栋----------------*/
                //修改面积
                BuildingEntity bd = buildingManager.findOne("bdCode", fl.getBdCode());
                if (bd.getRentableArea().floatValue() < area.floatValue()) {
                    throw new BusinessException("楼栋面积不足");
                } else if (bd.getRentableArea().floatValue() == area.floatValue()) {
                    //若面积为0,则修改状态为已租完
                    bd.setBuildingStatus("2");
                }
                bd.setRentableArea(bd.getRentableArea().subtract(area));
                //楼栋暂时没有已租用面积字段

                buildingManager.update(bd);



            /*------------园----------------*/
                //修改面积
                ParkEntity pk = parkManager.findOne("pkCode", fl.getPkCode());
                if (pk.getRentableArea().floatValue() < area.floatValue()) {
                    throw new BusinessException("园区面积不足");
                } else if (pk.getRentableArea().floatValue() == area.floatValue()) {
                    //若面积为0,则修改状态为已租完
                    pk.setParkStatus("2");
                }
                pk.setRentableArea(pk.getRentableArea().subtract(area));

                if (pk.getRentInvestArea() == null) {
                    pk.setRentInvestArea(new BigDecimal(0));
                }

                //含公摊面积计入到园区
                pk.setRentInvestArea(pk.getRentInvestArea().add(ctHos.getInvestArea()));

                parkManager.update(pk);
            }
        }



        /*------------处理电量----------------*/
        ParkEntity pk = parkManager.findOne("pkCode", contractEntity.getPkCode());
        //允许电量为负数
//        if (pk.getUseElecSurplus().floatValue() < contractEntity.getTotalElectricity().floatValue()) {
//            throw new BusinessException("园区剩余电量不足");
//        }
        //计算园区剩余电量
        pk.setUseElecSurplus(pk.getUseElecSurplus().subtract(contractEntity.getTotalElectricity()));


        //计算园区剩余停车位数(暂时不做)
//        Integer parking = contractEntity.getParkingCount() + contractEntity.getFreeParkingCount();
//        if (pk.getParkingSpace() < parking) {
//            throw new BusinessException("园区剩余停车位不足");
//        }
//        pk.setParkingSpace(pk.getParkingSpace()-parking);

        parkManager.update(pk);
    }


    /*
     * @Description 新增合同后>新增合同房源
     * @Auth liuzhengyang
     * @Date 2018/7/17 16:39
     * @Version 1.0
     * @Param [contractEntity]
     * @return void
     */
    private void createCtHouse(ContractEntity contractEntity) {
        List<ContractHouseEntity> contractHouseList = contractEntity.getContractHouseList();

        if(contractHouseList != null && contractHouseList.size()>0) {
            for (int i = 0; i < contractHouseList.size(); i++) {
                ContractHouseEntity hos = contractHouseList.get(i);

                String flCode = hos.getFlCode();
                hos.setPropertyUnitPrice(contractEntity.getPropertyUnitPrice());
                FloorEntity fl = floorDbDao.selectUniqueByProp("flCode", flCode);

                hos.setCtCode(contractEntity.getCtCode());
                hos.setRentHosCode(CodeGenUtils.generate());
                hos.setPkCode(fl.getPkCode());
                hos.setPkName(fl.getPkName());
                hos.setBdCode(fl.getBdCode());
                hos.setBdName(fl.getBdName());
                hos.setFlCount(Integer.parseInt(fl.getThisFloor()));
                OperateFillUtils.fill(hos);
            }

            contractHouseDbDao.batchInsert(contractHouseList);
        }
    }

    /*
     * @Description 一键生成租金详情
     * @Auth liuzhengyang
     * @Date 2018/7/17 16:41
     * @Version 1.0
     * @Param [cont]
     * @return void
     */
    @Override
    public void initRentReceipt(ContractEntity contractEntity) {

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("ctCode", contractEntity.getCtCode());
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());
        //将完整合同信息查询出来
        contractEntity = this.findOne(param);

        //将租金递增情况查询出来
        List<RentIncrementEntity> rentIncrementEntityList = rentIncrementManager.findByMap(param, "id", true);


        //重新生成一个合同数据,后面会修改该类数据
        ContractEntity cont = contractEntity;
        //将合同结束时间处理,将传入的2018-8-5 23:59:59.999转换成2020-8-6 00:00:00
        Date todoDate = cont.getContractEndTime();
        todoDate.setTime(todoDate.getTime() + 1);
        cont.setContractEndTime(todoDate);

        List<RentReceiptEntity> rentList = new ArrayList<>();

        //处理期数集合
        List<Date> dateList = new ArrayList<>();
        instalment(dateList, cont.getContractStartTime(), cont.getContractEndTime(), cont.getPayRule());

        //递增次数
        int incCount = 0;
        for (int j = 0; j < dateList.size(); j++) {
            Date date = dateList.get(j);

            //判断该期数是否在递增日,是则修改合同中的金额数据
            int inc = isInc(rentIncrementEntityList, date, cont);
            incCount += inc;


            RentReceiptEntity rent = new RentReceiptEntity();
            rent.setCtCode(cont.getCtCode());
            rent.setPkCode(cont.getPkCode());
            rent.setPayStatus("1");
            rent.setHasTax(false);
            rent.setRealRent(new BigDecimal(0));
            //填充基础字段方法
            OperateFillUtils.fill(rent);
            //由于明细列表需要code,在这里生成
            rent.setRentCode(CodeGenUtils.generate());
            rent.setRentStartTime(date);

            //每期结束时间的前一天xxxx-xx-xx 23:59:59.999
            Date endDate = new Date();
            endDate.setTime(date.getTime() - 1);

            Calendar c = Calendar.getInstance();//获得一个日历的实例
            c.setTime(endDate);//设置日历时间
            c.add(Calendar.MONTH, cont.getPayRule());//在日历的月份上增加n个月

            //判断本期是否是n个月(付n压m)
            boolean isAllRent = true;
            if (c.getTime().getTime() >= cont.getContractEndTime().getTime()) {
                rent.setRentEndTime(cont.getContractEndTime());
                isAllRent = false;

                //收款时间
                rent.setReceivingDate(rent.getRentEndTime());
            } else {
                rent.setRentEndTime(c.getTime());
                //收款时间
                c.add(Calendar.DATE, -(cont.getAdvanceDateCount()));
                rent.setReceivingDate(c.getTime());
            }


            //处理租金和物业费（考虑免租期，要除去免租期的租金，物业费照常算）
            Date freeRentStartTime = DateFormatUtil.dayBegin(cont.getFreeRentStartTime());
            Date freeRentEndTime = DateFormatUtil.dayEnd(cont.getFreeRentEndTime());

            //本期租金存在免租期
            boolean isFreeTime = ((freeRentStartTime.getTime() >= rent.getRentStartTime().getTime() && freeRentStartTime.getTime() < rent.getRentEndTime().getTime() + 1)
                    || (freeRentEndTime.getTime() + 1 > rent.getRentStartTime().getTime() && freeRentEndTime.getTime() <= rent.getRentEndTime().getTime()));
            if (isFreeTime) {
                //去计算存在免租期的本期租金
                BigDecimal autoRent = outFreeTimeRent(freeRentStartTime, freeRentEndTime, rent.getRentStartTime(), rent.getRentEndTime(), cont.getRentTotal(), cont.getPayRule());
                rent.setAutoRent(autoRent);
            }

            //本期是否是n个月满期
            if (isAllRent) {
                rent.setPropertyPrice(cont.getPropertyTotal().multiply(new BigDecimal(cont.getPayRule())));
                if (!isFreeTime) {
                    rent.setAutoRent(cont.getRentTotal().multiply(new BigDecimal(cont.getPayRule())));
                }
            } else {//本期不足n个月

                BigDecimal propertyPrice = new BigDecimal(0);
                BigDecimal rentTotal = new BigDecimal(0);

                Calendar pCalStart = Calendar.getInstance();
                pCalStart.setTime(rent.getRentStartTime());
                Calendar pCalEnd = Calendar.getInstance();
                pCalEnd.setTime(rent.getRentStartTime());
                pCalEnd.add(Calendar.MONTH, 1);
                while (pCalEnd.getTime().getTime() <= rent.getRentEndTime().getTime()) {
                    propertyPrice = propertyPrice.add(cont.getPropertyTotal());

                    pCalStart.setTime(pCalEnd.getTime());
                    pCalEnd.add(Calendar.MONTH, 1);
                }

                //不足一个月的按天来算
                BigDecimal proDaysBetween = DateFormatUtil.daysBetween(pCalStart.getTime(), pCalEnd.getTime());

                //每日物业费
                BigDecimal proByDay = cont.getPropertyTotal().divide(proDaysBetween, 4, BigDecimal.ROUND_HALF_UP);

                //本月天数
                BigDecimal days = DateFormatUtil.daysBetween(pCalStart.getTime(), rent.getRentEndTime());
                propertyPrice = propertyPrice.add(proByDay.multiply(days));

                //本期没有免租期
                if (!isFreeTime) {
                    while (pCalEnd.getTime().getTime() <= rent.getRentEndTime().getTime()) {
                        rentTotal = rentTotal.add(cont.getRentTotal());

                        pCalStart.setTime(pCalEnd.getTime());
                        pCalEnd.add(Calendar.MONTH, 1);
                    }

                    //算出本月天的单价
                    BigDecimal unitByDays = cont.getRentTotal().divide(proDaysBetween, 4, BigDecimal.ROUND_HALF_UP);

                    rentTotal = rentTotal.add(unitByDays.multiply(days));
                    rent.setAutoRent(rentTotal);
                }

                rent.setPropertyPrice(propertyPrice);
            }

            rent.setReceivableRent(rent.getAutoRent());

            //合计应收金额 = 本期租金 + 本期物业费
            rent.setTotalPrice(rent.getReceivableRent().add(rent.getPropertyPrice()));

            //已收金额置为0
            rent.setPaidPrice(new BigDecimal(0));

            rentList.add(rent);

        }

        rentReceiptDbDao.batchInsert(rentList);
    }

    /**
     * @return java.util.List<com.kfwy.park.ati.contract.entity.ContractEntity>
     * @Description 查询某月存在的合同
     * @author wangchuang
     * @Date 2018/11/1 11:55
     * @Version 1.0
     * @Param [map]
     **/
    @Override
    public List<ContractEntity> selectConByMonth(Map<String, Object> map) {
        return contractDbDao.selectConByMonth(map);
    }

    /**
     * @return void
     * @Description 本期租金存在免租期，出去免租期计算得到本月租金
     * @author wangchuang
     * @Date 2018/10/23 18:26
     * @Version 1.0
     * @Param [freeRentStartTime, freeRentEndTime, rentStartTime, rentEndTime, rentTotal, payRule]
     **/
    private BigDecimal outFreeTimeRent(Date freeRentStartTime, Date freeRentEndTime, Date rentStartTime, Date rentEndTime, BigDecimal rentTotal, Integer payRule) {

        //结束时间的时间搓加1，方便计算两时间相差的天数
        freeRentEndTime.setTime(freeRentEndTime.getTime() + 1);

        //本期计算得到的钱
        BigDecimal autoRent = new BigDecimal(0);

        //每个月开始时间
        Calendar sCalStart = Calendar.getInstance();
        //每个月结束时间
        Calendar sCalEnd = Calendar.getInstance();


        sCalEnd.setTime(rentStartTime);

        //循环本期月数(付6则循环6次)
        for (int i = 0; i < payRule; i++) {

            sCalStart.setTime(sCalEnd.getTime());
            sCalEnd.add(Calendar.MONTH, 1);
            boolean noAllMonth = false;
            if (sCalEnd.getTime().getTime() > rentEndTime.getTime()) {
                //最后一个不满月
                noAllMonth = true;
            }

            //本月共有多少天
            BigDecimal thisMonthDays = DateFormatUtil.daysBetween(sCalStart.getTime(), sCalEnd.getTime());
            //算出本月天的单价
            BigDecimal unitByDays = rentTotal.divide(thisMonthDays, 4, BigDecimal.ROUND_HALF_UP);

            boolean flag = true;
            //本月存在免租期开始
            if (freeRentStartTime.getTime() >= sCalStart.getTime().getTime() && freeRentStartTime.getTime() < sCalEnd.getTime().getTime()) {
                //算出本月开始日到免租期开始日有多少天
                BigDecimal first = DateFormatUtil.daysBetween(sCalStart.getTime(), freeRentStartTime);

                //第一段的钱
                autoRent = autoRent.add(first.multiply(unitByDays));
                flag = false;
            }
            //本月存在免租期结束
            if (freeRentEndTime.getTime() >= sCalStart.getTime().getTime() && freeRentEndTime.getTime() < sCalEnd.getTime().getTime()) {
                //算出免租期结束日到当月结束日有多少天
                BigDecimal second;
                //如果是最后一个不满月
                if (noAllMonth) {
                    second = DateFormatUtil.daysBetween(freeRentEndTime, rentEndTime);
                } else {
                    second = DateFormatUtil.daysBetween(freeRentEndTime, sCalEnd.getTime());
                }

                //第二段存在即算上钱
                autoRent = autoRent.add(second.multiply(unitByDays));
                flag = false;
            }
            //免租期包含这一整月,本月不计租
            if (sCalStart.getTime().getTime() >= freeRentStartTime.getTime() && sCalEnd.getTime().getTime() <= freeRentEndTime.getTime()) {
                flag = false;
            }
            //免租期不在本月中按整月计算
            if (flag) {
                autoRent = autoRent.add(rentTotal);
            }

            //如果是最后一个不满月,跳出循环，结束
            if (noAllMonth) {
                return autoRent;
            }
        }

        return autoRent;

    }

    /*
     * @Description 判断该期数start是否在递增日
     * @Auth liuzhengyang
     * @Date 2018/7/26 17:27
     * @Version 1.0
     * @Param [incDateList, date, cont]
     * @return void
     */
    private int isInc(List<RentIncrementEntity> rentIncrementEntityList, Date date, ContractEntity cont) {
        for (RentIncrementEntity rentInc : rentIncrementEntityList) {
            //看当前期在租金递增表中的租金是多少
            if (rentInc.getIncStartTime().getTime() == date.getTime()) {
                cont.setRentTotal(rentInc.getRentTotal());
                return 1;
            }
        }
        return 0;
    }

    /*
     * @Description 租金递增日集合:例每2年递增5%
     * @Auth liuzhengyang
     * @Date 2018/7/26 17:30
     * @Version 1.0
     * @Param [incDateList, startTime, endTime, inc]
     * @return void
     */
    private void incDate(List<Date> incDateList, Date startTime, Date endTime, Integer inc) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        c.setTime(startTime);//设置日历时间
        c.add(Calendar.YEAR, inc);//在日历的年份上增加n个年
        if (c.getTime().getTime() <= endTime.getTime()) {
            incDateList.add(c.getTime());
            incDate(incDateList, c.getTime(), endTime, inc);
        } else {
            return;
        }
    }

    /*
     * @Description 租金递增日集合:第一年n%,第二年n%,第三年...
     * @Auth liuzhengyang
     * @Date 2018/8/1 14:51
     * @Version 1.0
     * @Param [incDateList, startTime, endTime, incDetailCase]
     * @return void
     */
    private void incDateDetail(List<Date> incDateList, Date startTime, Date endTime, String[] incs) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        c.setTime(startTime);//设置日历时间
        for (String st : incs) {
            c.add(Calendar.YEAR, 1);//在日历的年份上增加1年
            incDateList.add(c.getTime());
        }
    }

    public static void main(String[] args) throws ParseException {
        String str = "1,2,3,4,5,6,";
        String[] strarr = str.split(",");

        System.out.println(Arrays.toString(strarr));

        List<Date> incDateList = new ArrayList<>();
        String[] incs = str.split(",");
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        c.setTime(new Date());//设置日历时间
        for (String st : incs) {
            c.add(Calendar.YEAR, 1);//在日历的年份上增加1年
            incDateList.add(c.getTime());
        }
        System.out.println(incDateList);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String str1 = "2012-01-01";
        String str2 = "2014-02-24";
        Calendar bef = Calendar.getInstance();
        Calendar aft = Calendar.getInstance();
        bef.setTime(sdf.parse(str1));
        aft.setTime(sdf.parse(str2));
        int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
        int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
        System.out.println(Math.abs(month + result));

    }

    /*
     * @Description 处理期数集合
     * @Auth liuzhengyang
     * @Date 2018/7/26 14:31
     * @Version 1.0
     * @Param [dateList, contractStartTime, contractEndTime, payRule]
     * @return void
     */
    private void instalment(List<Date> dateList, Date startTime, Date endTime, Integer payRule) {
        Calendar c = Calendar.getInstance();//获得一个日历的实例
        c.setTime(startTime);//设置日历时间
        c.add(Calendar.MONTH, payRule);//在日历的月份上增加n个月
        if (c.getTime().getTime() <= endTime.getTime()) {
            dateList.add(startTime);
            instalment(dateList, c.getTime(), endTime, payRule);
        } else {
            dateList.add(startTime);
            return;
        }
    }


    /**
     * @return int
     * @Description 查询2个月内合同到期数
     * @author wangchuang
     * @Date 2018/7/20 10:30
     * @Version 1.0
     * @Param [map]
     **/
    @Override
    public int contractMaturityTime(Map<String, Object> map) {
        return contractDbDao.selectCount("countByMap", map);
    }

    /**
     * @return
     * @Description 根据pkCode查询合同
     * @Auth luming
     * @Date 2018/7/28 8:43
     * @Version 1.0
     * @Param
     */
    @Override
    public int queryConByPkCode(String pkCode) {
        return contractDbDao.queryConByPkCode(pkCode);
    }

    /**
     * @return java.util.List<com.kfwy.park.ati.contract.entity.ContractEntity>
     * @Description 看板:租金费用异常查询
     * @author wangchuang
     * @Date 2018/8/7 16:34
     * @Version 1.0
     * @Param [param]
     **/
    @Override
    public PageResult<ContractEntity> queryRentCostException(Map<String, Object> param, int start, int pageSize) {
        PageResult<ContractEntity> result = contractDbDao.selectByStatement(RENT_EXC_QUERY, param, start, pageSize, "id", false);
        return result;
    }

    /**
     * @return com.gniuu.framework.dataaccess.PageResult<com.kfwy.park.ati.contract.entity.ContractEntity>
     * @Description 看板:电费费用异常查询
     * @author wangchuang
     * @Date 2018/8/8 16:21
     * @Version 1.0
     * @Param [pkCode, start, pageSize]
     **/
    @Override
    public PageResult<ContractEntity> queryEleCostException(Map<String, Object> param, int start, int pageSize) {
        PageResult<ContractEntity> result = contractDbDao.selectByStatement(ELE_EXC_QUERY, param, start, pageSize, "id", false);
        return result;
    }

    /**
     * @return com.gniuu.framework.dataaccess.PageResult<com.kfwy.park.ati.contract.entity.ContractEntity>
     * @Description 看板:水费费用异常查询
     * @author wangchuang
     * @Date 2018/8/8 18:23
     * @Version 1.0
     * @Param [pkCode, start, pageSize]
     **/
    @Override
    public PageResult<ContractEntity> queryWaterCostException(Map<String, Object> param, int start, int pageSize) {
        PageResult<ContractEntity> result = contractDbDao.selectByStatement(WATER_EXC_QUERY, param, start, pageSize, "id", false);
        return result;
    }

    /**
     * @return com.gniuu.framework.dataaccess.PageResult<com.kfwy.park.ati.contract.entity.ContractEntity>
     * @Description 看板:停车费费用异常查询
     * @author wangchuang
     * @Date 2018/8/8 19:00
     * @Version 1.0
     * @Param [pkCode, start, pageSize]
     **/
    @Override
    public PageResult<ContractEntity> queryParkingCostException(Map<String, Object> param, int start, int pageSize) {
        PageResult<ContractEntity> result = contractDbDao.selectByStatement(PARKING_EXC_QUERY, param, start, pageSize, "id", false);
        return result;
    }

    /**
     * @return
     * @Description 看板：我的看板，计算本月招商总面积
     * @Auth luming
     * @Date 2018/8/13 15:17
     * @Version 1.0
     * @Param
     */
    @Override
    public int queryThisMonthAreaSum(Map<String, Object> map) throws Exception {
        int sum = contractDbDao.queryThisMonthAreaSum(map);
        return sum;
    }

    /**
     * @return
     * @Description 看板：我的看板，本月新签合同
     * @Auth luming
     * @Date 2018/8/14 11:22
     * @Version 1.0
     * @Param
     */
    @Override
    public int queryThisMonthContract(Map<String, Object> map) throws Exception {
        int count = contractDbDao.queryThisMonthContract(map);
        return count;
    }

    /**
     * @return void
     * @Description 判断合同开始时间和结束时间
     * @author wangchuang
     * @Date 2018/8/24 16:34
     * @Version 1.0
     * @Param [contractEndTime, contractStartTime]
     **/
    private void validateStartEndDate(ContractEntity contractEntity) {
        Date contractStartTime = contractEntity.getContractStartTime();
        Date contractEndTime = contractEntity.getContractEndTime();
        //存放处理的时间
        Date date = new Date();
        //处理时间+1时间搓
        date.setTime(contractEndTime.getTime() + 1);
        Integer payRule = contractEntity.getPayRule();

        Calendar start = Calendar.getInstance();
        start.setTime(contractStartTime);
        Calendar end = Calendar.getInstance();
        end.setTime(date);

        int year = end.get(Calendar.YEAR) - start.get(Calendar.YEAR);
        int month = end.get(Calendar.MONTH) - start.get(Calendar.MONTH);

        //得到月数
        int monthNum = year * 12 + month;

        if (monthNum % payRule != 0) {
            throw new BusinessException("选择合同期限与支付方式不匹配");
        }
        start.add(Calendar.MONTH, monthNum);
        if (!start.getTime().equals(end.getTime())) {
            throw new BusinessException("合同日期选择存在误差");
        }

    }

    @Override
    public Map<String, BigDecimal> selectGross(Map<String, Object> map) {
        Map<String, BigDecimal> result = contractDbDao.selectGross(map);
        return result;
    }

    /**
     * @return void
     * @Description 自动生成合同编号
     * @author wangchuang
     * @Date 2018/9/26 18:40
     * @Version 1.0
     * @Param [contractEntity]
     **/
    private void autoCtName(ContractEntity contractEntity) {
        //生成编号例：0001(园区id)201809(合同开始年月)0001(当月自增长编号)  0001 201809 0001

        //1、根据合同开始日期查询该日期下有多少合同
        Date contractStartTime = contractEntity.getContractStartTime();

        Map<String, Object> param = new HashMap<String, Object>();
        Date monthBegin = DateFormatUtil.monthBegin(contractStartTime);
        Date monthEnd = DateFormatUtil.monthEnd(contractStartTime);

        param.put("contractStartTimeStart", monthBegin);
        param.put("contractStartTimeEnd", monthEnd);
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        List<ContractEntity> resList = this.findByMap(param);
        int ctNum = resList.size() + 1;

        //2、第3部分---自增编号
        String lastNum = String.format("%04d", ctNum);

        //3、第2部分---合同开始时间
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
        String conStartTimeStr = sdf.format(contractStartTime);

        //4、第1部分---园区id
        Map<String, Object> parkParam = new HashMap<String, Object>();
        parkParam.put("pkCode", contractEntity.getPkCode());
        parkParam.put("isDeleted", false);

        ParkEntity parkEntity = parkManager.findOne(parkParam);
        long parkId = parkEntity.getId();

        String firstId = String.format("%04d", parkId);

        //5、拼接一起
        String ctName = firstId + conStartTimeStr + lastNum;

        contractEntity.setCtName(ctName);

    }

    /**
     * @return void
     * @Description 新增租金递增详情
     * @author wangchuang
     * @Date 2018/10/19 15:36
     * @Version 1.0
     * @Param [contractEntity]
     **/
    private void createIncDetail(ContractEntity contractEntity) {

        List<RentIncrementEntity> rentIncrementList = contractEntity.getRentIncrementList();

        if(rentIncrementList != null && rentIncrementList.size()>0) {
            //将前台传入的租金递增详情记录在租金递增表中
            for (RentIncrementEntity inc : rentIncrementList) {
                inc.setIncCode(CodeGenUtils.generate());
                inc.setCtCode(contractEntity.getCtCode());
                OperateFillUtils.fill(inc);
            }

            rentIncrementDbDao.batchInsert(rentIncrementList);
        }

    }

    /**
     * @return void
     * @Description 新增合同：输入的租金费用新增
     * @author wangchuang
     * @Date 2018/11/13 16:43
     * @Version 1.0
     * @Param [contractEntity]
     **/
    private void createRentReceiptInfo(ContractEntity contractEntity) {
        List<RentReceiptEntity> rentReceiptList = contractEntity.getRentReceiptList();

        String ctCode = contractEntity.getCtCode();
        String pkCode = contractEntity.getPkCode();

        for (RentReceiptEntity rent : rentReceiptList) {
            rent.setRentCode(CodeGenUtils.generate());
            rent.setCtCode(ctCode);
            rent.setPkCode(pkCode);
            rent.setPayStatus("1");
            rent.setRealRent(new BigDecimal(0));
            rent.setPaidPrice(new BigDecimal(0));

            //逾期天数默认为0
            rent.setOverdueDays(0);
            //滞纳金默认为0
            rent.setLatePayment(new BigDecimal(0));
            //优惠金额默认为0
            rent.setDiscountsPrice(new BigDecimal(0));

            Date rentStartTime = DateFormatUtil.dayBegin(rent.getRentStartTime());
            Date rentEndTime = DateFormatUtil.dayEnd(rent.getRentEndTime());
            Date receivingDate = DateFormatUtil.dayEnd(rent.getReceivingDate());
            rent.setRentStartTime(rentStartTime);
            rent.setRentEndTime(rentEndTime);
            rent.setReceivingDate(receivingDate);

            //填充基础字段方法
            OperateFillUtils.fill(rent);
        }

        rentReceiptDbDao.batchInsert(rentReceiptList);
    }

    /**
     * @return
     * @Description 新增保险费
     * @Auth luming
     * @Date 2018/11/21 10:14
     * @Version 1.0
     * @Param
     */
    private void createPremium(ContractEntity contractEntity) {

        List<PremiumEntity> premiumReceiptList = contractEntity.getPremiumReceiptList();

        String ctCode = contractEntity.getCtCode();

        for (PremiumEntity premiumEntity : premiumReceiptList) {
            Date dayBegin = DateFormatUtil.dayBegin(premiumEntity.getPremiumStartDate());
            Date dayEnd = DateFormatUtil.dayEnd(premiumEntity.getPremiumEndDate());
            Date deadline = DateFormatUtil.dayEnd(premiumEntity.getDeadline());

            premiumEntity.setPremiumCode(CodeGenUtils.generate());
            premiumEntity.setCtCode(ctCode);
            premiumEntity.setPremiumStartDate(dayBegin);
            premiumEntity.setPremiumEndDate(dayEnd);
            premiumEntity.setDeadline(deadline);
            premiumEntity.setPayStatus("1");
            premiumEntity.setPaidPrice(new BigDecimal(0));
            premiumEntity.setThePayment(new BigDecimal(0));
            OperateFillUtils.fill(premiumEntity);
        }
        premiumDbDao.batchInsert(premiumReceiptList);
    }

    /**
     * @return void
     * @Description 添加电表信息
     * @author wangchuang
     * @Date 2018/11/14 17:32
     * @Version 1.0
     * @Param [contractEntity]
     **/
    private void addMeterList(ContractEntity contractEntity) throws IllegalAccessException {
        String ctCode = contractEntity.getCtCode();
        List<ElectricityMeterEntity> meterList = contractEntity.getMeterList();
        for (ElectricityMeterEntity meter : meterList) {
            meter.setEleMeterCode(CodeGenUtils.generate());
            meter.setCtCode(ctCode);

            List<String> list = new ArrayList();
            list.add("ctCode");
            list.add("eleMeterName");
            list.add("multiple");
            list.add("electricityBillingType");
            if ("1".equals(meter.getElectricityBillingType())) {
                list.add("electricityAveragePrice");
                list.add("initElectricity");
            } else if ("2".equals(meter.getElectricityBillingType())) {
                list.add("electricityPeak");
                list.add("electricityFlat");
                list.add("electricityTrough");
                list.add("electricityPeakPrice");
                list.add("electricityFlatPrice");
                list.add("electricityTroughPrice");
            }

            ParamUtil<ElectricityMeterEntity> paramUtil = new ParamUtil<>();
            paramUtil.check(meter, list);

            OperateFillUtils.fill(meter);
        }

        iElectricityMeterDbDao.batchInsert(meterList);
    }

    /**
     * @return void
     * @Description 添加水表信息
     * @author wangchuang
     * @Date 2018/11/23 9:33
     * @Version 1.0
     * @Param [contractEntity]
     **/
    private void addWaterMeterList(ContractEntity contractEntity) throws IllegalAccessException {
        List<WaterMeterEntity> waterMeterList = contractEntity.getWaterMeterList();

        for (WaterMeterEntity waterMeter : waterMeterList) {
            waterMeter.setCtCode(contractEntity.getCtCode());
            waterMeter.setWaterMeterCode(CodeGenUtils.generate());

            //将必填字段放入水费容器
            List<String> param = new ArrayList<>();
            param.add("ctCode");
            param.add("waterMeterName");
            param.add("waterBillingType");
            if ("2".equals(waterMeter.getWaterBillingType())) {
                param.add("waterUnitPrice");
                param.add("initWater");
            }
            if ("1".equals(waterMeter.getWaterBillingType())) {
                param.add("waterAmount");
            }

            ParamUtil<WaterMeterEntity> paramUtil = new ParamUtil<>();
            paramUtil.check(waterMeter, param);

            OperateFillUtils.fill(waterMeter);
        }

        waterMeterDbDao.batchInsert(waterMeterList);
    }

    /**
     * @return
     * @Description 合同审批状态
     * @Auth luming
     * @Date 2018/12/11 9:06
     * @Version 1.0
     * @Param
     */
    @Override
    public void contractVerify(ContractEntity contractEntity) {

        Map<String, Object> map = new HashMap<>();
        map.put("id", contractEntity.getId());
        map.put("ctCode", contractEntity.getCtCode());
        map.put("ctStatus", contractEntity.getCtStatus());
        map.put("cpyCode", CurrentContext.getCpyCode());


        /**
         * 合同审批流程表
         */
        ConOperateHisEntity conOperateHisEntity = new ConOperateHisEntity();
        conOperateHisEntity.setCtCode(contractEntity.getCtCode());
        conOperateHisEntity.setOperateRemark(contractEntity.getOperateRemark());

        // 撤回审核，合同状态重置为“1”——待审核状态
        if ("1".equals(contractEntity.getCtStatus())) {
            conOperateHisEntity.setCtStatus("1");
        }

        if ("2".equals(contractEntity.getCtStatus())) {
            conOperateHisEntity.setCtStatus("2");
        }

        // 一审通过
        if ("3".equals(contractEntity.getCtStatus())) {
            conOperateHisEntity.setCtStatus("3");
        }

        // 二审通过
        if ("4".equals(contractEntity.getCtStatus())) {
            conOperateHisEntity.setCtStatus("4");

            Map<String, Object> params = new HashMap<>();
            params.put("ctCode", contractEntity.getCtCode());
            params.put("isDeleted", false);

            List<ContractHouseEntity> contractHouseList = contractHouseDbDao.selectByMap(params);// 查询房源信息
            ContractEntity oldContractEntity = this.findOne(params);
            oldContractEntity.setContractHouseList(contractHouseList);

            updateHouse(oldContractEntity);// 房源变化(层,栋,园,parkBi)
            updateCustomer(oldContractEntity);// 客户变化
        }

        // 驳回
        if ("5".equals(contractEntity.getCtStatus())) {
            conOperateHisEntity.setCtStatus("5");
        }

        if ("6".equals(contractEntity.getCtStatus())) {
            conOperateHisEntity.setCtStatus("6");
        }


        contractDbDao.contractVerify(map);// 修改合同审批状态
        conOperateHisManager.create(conOperateHisEntity);// 创建审批记录
    }


    /**
     * 获取应收
     */
    @Override
    public Map<String,Object> receivableChart(Map<String,Object>map){
        Map<String,Object> result = new HashMap<>();
        Map<String,BigDecimal> price = getPrice(map);
        result.put("rentReceivablePrice",price.get("rentReceivablePrice"));
        result.put("propertyReceivablePrice",price.get("propertyReceivablePrice"));
        result.put("waterReceivablePrice",price.get("waterReceivablePrice"));
        result.put("electricityReceivablePrice",price.get("electricityReceivablePrice"));
        result.put("premiumReceivablePrice",price.get("premiumReceivablePrice"));
        BigDecimal totalPaidPrice = price.get("rentPaidPrice").add(price.get("waterPaidPrice")).add(price.get("electricityPaidPrice")).add(price.get("premiumPaidPrice"));
        BigDecimal totalWaitedPrice = price.get("rentWaitedPrice").add(price.get("waterWaitedPrice")).add(price.get("electricityWaitedPrice")).add(price.get("premiumWaitedPrice"));
        result.put("totalPaidPrice",totalPaidPrice);
        result.put("totalWaitedPrice",totalWaitedPrice);
        //  result.put("totalPaidPrice",price.get("rentPaidPrice")+price.get("waterPaidPrice")+price.get("electricityPaidPrice")+price.get("premiumPaidPrice"));
      //  result.put("totalWaitedPrice",price.get("rentWaitedPrice")+price.get("waterWaitedPrice")+price.get("electricityWaitedPrice")+price.get("premiumWaitedPrice"));
        return result;
    }


    public Map<String,BigDecimal> getPrice(Map<String,Object>map){
        Map<String,BigDecimal> result = new HashMap<>();

        map.put("receivablePrice","receivable_rent");
        map.put("paidPrice","paid_price");
        map.put("priceTable","t_yq_ati_con_rent_receipt");
        List<Map<String,Object>> rentReceive = contractDbDao.sumReceivablePrice(map);
        rentReceive.removeAll(Collections.singleton(null));
        BigDecimal rentReceivablePrice = new BigDecimal("0");
        BigDecimal rentPaidPrice = new BigDecimal("0");
        if(rentReceive.size()>0){
            if(null != rentReceive.get(0).get("receivableprice")){
                rentReceivablePrice = (BigDecimal) rentReceive.get(0).get("receivableprice");
            }
            if(null != rentReceive.get(0).get("paidprice")){
                rentPaidPrice = (BigDecimal) rentReceive.get(0).get("paidprice");
            }
        }
        result.put("rentReceivablePrice",rentReceivablePrice); //应付
        result.put("rentPaidPrice",rentPaidPrice); //房租+物业已付


        //获取物业费
        map.put("receivablePrice","property_price");
        List<Map<String,Object>> propertyReceive = contractDbDao.sumReceivablePrice(map);
        propertyReceive.removeAll(Collections.singleton(null));
        BigDecimal propertyReceivablePrice = new BigDecimal("0");
        if(propertyReceive.size()>0){
            if(null != propertyReceive.get(0).get("receivableprice")){
                propertyReceivablePrice = (BigDecimal) propertyReceive.get(0).get("receivableprice");
            }
        }
        result.put("propertyReceivablePrice",propertyReceivablePrice); //物业费应付
        BigDecimal rentWaitedPrice = rentReceivablePrice.add(propertyReceivablePrice).subtract(rentPaidPrice);//房租+物业待付
        result.put("rentWaitedPrice",rentWaitedPrice); //房租+物业待付

        //获取水费
        map.put("receivablePrice","receivable_price");
        map.put("priceTable","t_yq_ati_con_water_receipt");
        List<Map<String,Object>> waterReceive = contractDbDao.sumReceivablePrice(map);
        waterReceive.removeAll(Collections.singleton(null));
        BigDecimal waterPaidPrice = new BigDecimal("0");
        BigDecimal waterReceivablePrice = new BigDecimal("0");
        if(waterReceive.size()>0){
            if(null != waterReceive.get(0).get("receivableprice")){
                waterReceivablePrice = (BigDecimal) waterReceive.get(0).get("receivableprice");
            }
            if(null != waterReceive.get(0).get("paidprice")){
                waterPaidPrice = (BigDecimal) waterReceive.get(0).get("paidprice");
            }
        }
        result.put("waterReceivablePrice",waterReceivablePrice);
        result.put("waterPaidPrice",waterPaidPrice);
        BigDecimal waterWaitedPrice = waterReceivablePrice.subtract(waterPaidPrice);
        result.put("waterWaitedPrice",waterWaitedPrice);

        //获取电费
        map.put("priceTable","t_yq_ati_con_electricity_receipt");
        List<Map<String,Object>> electricityReceive = contractDbDao.sumReceivablePrice(map);
        electricityReceive.removeAll(Collections.singleton(null));
        BigDecimal electricityReceivablePrice = new BigDecimal("0");
        BigDecimal electricityPaidPrice = new BigDecimal("0");
        if(electricityReceive.size()>0){
            if(null != electricityReceive.get(0).get("receivableprice")){
                electricityReceivablePrice = (BigDecimal) electricityReceive.get(0).get("receivableprice");
            }
            if(null != electricityReceive.get(0).get("paidprice")){
                electricityPaidPrice = (BigDecimal) electricityReceive.get(0).get("paidprice");
            }
        }
        result.put("electricityReceivablePrice",electricityReceivablePrice);
        result.put("electricityPaidPrice",electricityPaidPrice);
        BigDecimal electricityWaitedPrice = electricityReceivablePrice.subtract(electricityPaidPrice);
        result.put("electricityWaitedPrice",electricityWaitedPrice);

        //获取保险费
        map.put("receivablePrice","receivable_premium");
        map.put("priceTable","t_yq_ati_con_rent_premium");
        map.put("deadLineStartDate",map.get("startDate"));
        map.put("deadLineEndDate",map.get("endDate"));
        map.put("startDate",null);
        map.put("endDate",null);
        List<Map<String,Object>> premiumReceive = contractDbDao.sumReceivablePrice(map);
        premiumReceive.removeAll(Collections.singleton(null));
        BigDecimal premiumReceivablePrice = new BigDecimal("0");
        BigDecimal premiumPaidPrice = new BigDecimal("0");
        if(premiumReceive.size()>0){
            if(null != premiumReceive.get(0).get("receivableprice")){
                premiumReceivablePrice = (BigDecimal) premiumReceive.get(0).get("receivableprice");
            }
            if(null != premiumReceive.get(0).get("paidprice")){
                premiumPaidPrice = (BigDecimal) premiumReceive.get(0).get("paidprice");
            }
        }

        result.put("premiumReceivablePrice",premiumReceivablePrice);
        result.put("premiumPaidPrice",premiumPaidPrice);
        BigDecimal premiumWaitedPrice = premiumReceivablePrice.subtract(premiumPaidPrice);
        result.put("premiumWaitedPrice",premiumWaitedPrice);

        return result;
    }

    @Override
    public PageResult<ContractEntity> selectInfoByEmpCode(Map<String,Object>map,int start,int pageSize){
        return contractDbDao.selectByStatement("selectInfoByEmpCode",map,start,pageSize);
    }

    @Override
    public int selectInfoByEmpCodeCount(Map<String,Object> map){
        return contractDbDao.selectInfoByEmpCodeCount(map);
    }


    /**
     * @Description 园区详情中的层信息显示该层入驻的客户信息和出租面积
     * @author luming
     * @Date 2019/5/16 9:52
     * @Version 1.0
     * @Param [map]
     * @return com.kfwy.park.pro.house.building.entity.ParkEntity
     */
    @Override
    public ParkEntity parkDetails(Map<String, Object> map) throws Exception {
        ParkEntity parkEntity = null;

        try{
            parkEntity = parkDbDao.selectUniqueByMap(map);

            if (parkEntity == null) {
                throw new BusinessException("查询园区信息为空");
            }
            int totals =parkEntity.getBuildingTotal().intValue();

            List<BuildingEntity> resList = new ArrayList<>();

            Map<String,Object> mapByCode = null;
            //该园区有数据的楼栋信息
            List<BuildingEntity> bdList =buildingDbDao.selectByMap(map,"cast(this_building as int)",true);
            //查询的楼栋信息包括楼层
            List<BuildingEntity> returnBdList = new ArrayList<>();
            if (!bdList.isEmpty()) {
                for (BuildingEntity item :bdList) {
                    mapByCode = new HashMap<>();
                    mapByCode.put("bdCode",item.getBdCode());
                    mapByCode.put("isDeleted",false);
                    mapByCode.put("cpyCode", CurrentContext.getCpyCode());

                    returnBdList.add(detailsByBdCode(mapByCode));
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


    public BuildingEntity detailsByBdCode(Map<String, Object> map) throws Exception {
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


            // 根据pkCode查询合同、合同房源集合
            selectCtHouseFloor(buildingEntity.getPkCode(), list);


            List<FloorEntity> paramList = new ArrayList<>();
            if (!list.isEmpty()) {
                for (int i = 0, j = 0; i < totals; i++) {
                    if (list.size() >= (j + 1)) {
                        if (Integer.parseInt(list.get(j).getThisFloor()) == (i + 1)) {
                            paramList.add(list.get(j));
                            j++;
                            continue;
                        }
                    }
                    FloorEntity paramObj = new FloorEntity();
                    paramObj.setBdCode(map.get("bdCode").toString());
                    paramObj.setThisFloor(String.valueOf((i + 1)));
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
     * @Description 查询楼栋详情并关联合同和合同房源信息
     * @author luming
     * @Date 2019/5/16 16:56
     * @Version 1.0
     * @Param [map]
     * @return com.kfwy.park.pro.house.building.entity.BuildingEntity
     */
    @Override
    public BuildingEntity buildingDetails(Map<String, Object> map) throws Exception {
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


            // 根据pkCode查询合同、合同房源集合
            selectCtHouseFloor(buildingEntity.getPkCode(), list);


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

    /**
     * @Description 查询合同、合同房源信息，并合并到层信息中
     * @author luming
     * @Date 2019/5/17 10:02
     * @Version 1.0
     * @Param [pkCode, list]
     * @return void
     */
    public void selectCtHouseFloor(String pkCode, List<FloorEntity> list) {
        // 根据pkCode查询合同、合同房源集合
        Map<String, Object> ctMap = new HashMap<>();
        ctMap.put("cpyCode", CurrentContext.getCpyCode());
        ctMap.put("pkCode", pkCode);
        ctMap.put("isDeleted", false);

        List<ContractHouseEntity> ctHouseList = contractHouseDbDao.selectFloorAndContractHouse(ctMap);

        // 将合同房源数据封装进floorEntity中
        for (FloorEntity floorEntity : list) {
            List<FloorContractHouseEntity> floorContractHouseEntityList = new ArrayList<>();
            for (ContractHouseEntity contractHouseEntity : ctHouseList) {
                if (floorEntity.getFlCode().equals(contractHouseEntity.getFlCode())) {
                    FloorContractHouseEntity floorContractHouseEntity = new FloorContractHouseEntity();

                    floorContractHouseEntity.setCtCode(contractHouseEntity.getCtCode());
                    floorContractHouseEntity.setCtName(contractHouseEntity.getCtName());
                    floorContractHouseEntity.setCompanyName(contractHouseEntity.getCompanyName());
                    floorContractHouseEntity.setRelateName(contractHouseEntity.getRelateName());
                    floorContractHouseEntity.setCtInvestArea(contractHouseEntity.getInvestArea());

                    floorContractHouseEntityList.add(floorContractHouseEntity);

                    floorEntity.setFloorContractHouseEntityList(floorContractHouseEntityList);
                }
            }
        }
    }

    /**
     *@Author gengtao
     *@Date 2019-06-03 10:46:10
     *@Description 根据年份和月份查询数据
     *@Return
     **/
    @Override
    public PageResult<ContractEntity> selectByMMAndYY(Map<String,Object> map,int start, int pageSize){
        return contractDbDao.selectByStatement("selectByMMAndYY",map,start,pageSize);
    }

    @Override
    public int selectByMMAndYYCount(Map<String, Object> map){
        return contractDbDao.selectByMMAndYYCount(map);
    }

    @Override
    public List<ContractEntity> findCus(Map<String, Object> map){
        return contractDbDao.selectCus(map);
    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 15:24:26
     *@Description 获取三个月到期合同编号
     *@param
     *@return
     **/
    @Override
    public List<String> selectThreeMonthExpiredCtCode(Map<String, Object>map) {
        return contractDbDao.selectThreeMonthExpiredCtCode(map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-01 16:40:35
     *@Description 获取近期到期面积
     *@param
     *@return
     **/
    @Override
    public List<Map<String, Object>> selectMaturityArea(Map<String, Object> map) {
        return contractDbDao.selectMaturityArea(map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-15 16:43:14
     *@Description 获取6个月营收信息
     *@param
     *@return
     **/
    @Override
    public BigDecimal selectSumRentTotal(Map<String, Object> map) {
        return contractDbDao.selectSumRentTotal(map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-15 16:43:14
     *@Description 获取6个月营收信息,查询合同结束日期在本月的合同
     *@param
     *@return
     **/
    @Override
    public List<ContractEntity> selectByContractMonth(Map<String, Object> map) {
        return contractDbDao.selectByContractMonth(map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-15 16:43:14
     *@Description 获取6个月营收信息,查询合同结束日期在本月的合同
     *@param
     *@return
     **/
    @Override
    public List<ContractEntity> selectByTime(Map<String, Object> map) {
        return contractDbDao.selectByTime(map);
    }

    /**
     * @Description 合同关联水电表，此接口用于物业中的水电抄表展示
     * @Auth luming
     * @Date 2019/8/20 16:25
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public PageResult<ContractEntity> queryMeter(Map<String, Object> map, int start, int pageSize) {
        return contractDbDao.selectByStatement("selectByMapMeter", map, start, pageSize, "id", false);
    }
}
