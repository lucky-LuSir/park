package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IElectricityReceiptManager;
import com.kfwy.park.ati.contract.business.IElectricityRecordManager;
import com.kfwy.park.ati.contract.dao.IContractDbDao;
import com.kfwy.park.ati.contract.dao.IElectricityMeterDbDao;
import com.kfwy.park.ati.contract.dao.IElectricityReceiptDbDao;
import com.kfwy.park.ati.contract.dao.IElectricityRecordDbDao;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.ati.contract.entity.ElectricityMeterEntity;
import com.kfwy.park.ati.contract.entity.ElectricityReceiptEntity;
import com.kfwy.park.ati.contract.entity.ElectricityRecordEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Service
public class ElectricityRecordManagerImpl extends AbstractManager<ElectricityRecordEntity> implements IElectricityRecordManager {

    @Autowired
    private IElectricityReceiptManager electricityReceiptManager;

    @Autowired
    private IElectricityReceiptDbDao electricityReceiptDbDao;

    @Autowired
    private IElectricityRecordDbDao electricityRecordDbDao;

    @Autowired
    private IElectricityMeterDbDao electricityMeterDbDao;

    @Autowired
    private IContractDbDao contractDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.electricityRecordDbDao;
    }

    @Override
    protected void beforeCreate(ElectricityRecordEntity recordEntity) {

    }

    /**
     * @return int
     * @Description 插入抄表记录信息
     * @author wangchuang
     * @Date 2018/8/22 9:39
     * @Version 1.0
     * @Param [recordEntityList, eleTime]
     **/
    @Override
    @Transactional
    public int batchCreate(List<ElectricityRecordEntity> recordEntityList, List<Date> eleTime) throws Exception {

        String ctCode = recordEntityList.get(0).getCtCode();
        //抄表日期
        Date lastBillingTime = eleTime.get(0);
        //结束日期加上23:59:59.999
        Date currentBillingTime = DateFormatUtil.dayEnd(eleTime.get(1));
        //插入电费应收列表数据
        ElectricityReceiptEntity receiptEntity = new ElectricityReceiptEntity();
        String eleCode = CodeGenUtils.generate();
        //应收列表数据:1-4
        receiptEntity.setEleCode(eleCode);
        receiptEntity.setCtCode(ctCode);
        receiptEntity.setLastBillingTime(lastBillingTime);
        receiptEntity.setCurrentBillingTime(currentBillingTime);

        //取合同中需要数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ctCode", ctCode);
        map.put("isDeleted", false);

        ContractEntity contractEntity = contractDbDao.selectUniqueByMap(map);

        if (contractEntity != null) {
            //总用电量
            BigDecimal totalElectricity = contractEntity.getTotalElectricity();
            //基础电费单价
            BigDecimal basicElectricityUnitPrice = contractEntity.getBasicElectricityUnitPrice();
            //加收损耗(%)
            BigDecimal addLoss = contractEntity.getAddLoss();
            addLoss = addLoss.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(1));

            //初始化：每月基础电费
            BigDecimal bm = new BigDecimal(0);

            //判断是否是一个满月
            Calendar cal_last = Calendar.getInstance();
            cal_last.setTime(lastBillingTime);
            //当前时间加上一个月和结束时间做对比
            cal_last.add(Calendar.MONTH, 1);

            /*******************start************************/
            /*
             * 本段说明：
             * 例：当开始时间是10.31时，用Calendar增加一个月是11.30，而不是11.31，所以这里增加个判断，
             * 1、如果增加一个月时间是这个月的月底时间，那就把时间变为月底时间的23:59:59.999
             * 2、如果增加一个月时间不是月底时间，那就把时间的getTime()-1
             * 然后拿这个出来的时间去判断是否是一个满月
             */
            Date monthEnd = DateFormatUtil.monthEnd(cal_last.getTime());
            Date validDate = new Date();
            if (monthEnd.getTime() == cal_last.getTime().getTime()) {
                validDate.setTime(DateFormatUtil.dayEnd(cal_last.getTime()).getTime());
            } else {
                validDate.setTime(cal_last.getTime().getTime() - 1);
            }
            /*******************end************************/

            if (validDate.getTime() == currentBillingTime.getTime()) {
                //正是一个满月
                bm = basicElectricityUnitPrice.multiply(totalElectricity);
            } else {
                //非满月，使用多少天算多少
                // 1.1 得到本期的天数
                BigDecimal betweenDays = DateFormatUtil.daysBetween(lastBillingTime, currentBillingTime).add(new BigDecimal(1));
                //计算使用天数占一个月比例
                BigDecimal scale = betweenDays.multiply(new BigDecimal(12)).divide(new BigDecimal(365), 2, BigDecimal.ROUND_HALF_UP);
                //1.2 基础电费单价*总用电量*天数
                bm = basicElectricityUnitPrice.multiply(totalElectricity).multiply(scale);
            }

            //应收列表数据:5(每月基础电费)
            receiptEntity.setBasicElectricityPriceByMonth(bm);

            //查询出该合同绑定的电表
            List<ElectricityMeterEntity> meterList = electricityMeterDbDao.selectByMap(map, "id", true);

            if (meterList.size() > 0) {
                //电费波峰单价
                BigDecimal electricityPeakPrice = new BigDecimal(0);
                //电费平值单价
                BigDecimal electricityFlatPrice = new BigDecimal(0);
                //电费波谷单价
                BigDecimal electricityTroughPrice = new BigDecimal(0);
                //电费均价单价
                BigDecimal electricityAveragePrice = new BigDecimal(0);

                //电表倍率
                BigDecimal multiple = new BigDecimal(0);
                //多表用电总费用
                BigDecimal allElePrice = new BigDecimal(0);

                for (int i = 0; i < recordEntityList.size(); i++) {
                    recordEntityList.get(i).setEleRecordCode(CodeGenUtils.generate());
                    // recordEntityList.get(i).setEleCode(eleCode);
                    String eleMeterCode = recordEntityList.get(i).getEleMeterCode();

                    //去电表集合中找出本条数据对应的电表
                    for (int j = 0; j < meterList.size(); j++) {
                        if (eleMeterCode.equals(meterList.get(j).getEleMeterCode())) {
                            //找到电表
                            electricityPeakPrice = meterList.get(j).getElectricityPeakPrice();
                            electricityFlatPrice = meterList.get(j).getElectricityFlatPrice();
                            electricityTroughPrice = meterList.get(j).getElectricityTroughPrice();
                            electricityAveragePrice = meterList.get(j).getElectricityAveragePrice();

                            multiple = meterList.get(j).getMultiple();
                        }
                    }
                    //若峰值单价或均值单价都为空  说明未找到该电表,抛异常,结束循环
                    if (electricityPeakPrice == null && electricityAveragePrice == null) {
                        throw new Exception("未找到对应电表");
                    }

                    //2.计算用电费用总额

                    String eleType = recordEntityList.get(i).getElectricityBillingType();

                    //一个电表用电费用
                    BigDecimal elePrice = new BigDecimal(0);
                    if ("2".equals(eleType)) {//峰.平.谷计费方式
                        //1.波峰.平.谷电量*单价
                        BigDecimal peak = recordEntityList.get(i).getPeakDegree().multiply(electricityPeakPrice);
                        BigDecimal flat = recordEntityList.get(i).getFlatDegree().multiply(electricityFlatPrice);
                        BigDecimal trough = recordEntityList.get(i).getTroughDegree().multiply(electricityTroughPrice);

                        //用电费用
                        elePrice = (peak.add(flat).add(trough)).multiply(multiple).multiply(addLoss);

                        //得到用到的总用电量
                        BigDecimal rDegree = recordEntityList.get(i).getPeakDegree().add(recordEntityList.get(i).getFlatDegree().add(recordEntityList.get(i).getTroughDegree()));
                        recordEntityList.get(i).setRealDegree(rDegree);
                    } else if ("1".equals(eleType)) {//均价计费方式
                        elePrice = recordEntityList.get(i).getRealDegree().multiply(electricityAveragePrice).multiply(multiple).multiply(addLoss);
                    }
                    allElePrice = allElePrice.add(elePrice);
                    OperateFillUtils.fill(recordEntityList.get(i));
                }
                //应收列表数据: 6: 用电费用总和
                receiptEntity.setElectricityPrice(allElePrice);

                //应收列表数据: 7: 应收电费 = 每月基础电费 + 用电费用总和
                receiptEntity.setReceivablePrice(bm.add(allElePrice));

                //应收列表数据: 8: 实收电费
                receiptEntity.setRealPrice(new BigDecimal(0));

                //应收列表数据: 9: 支付状态
                receiptEntity.setPayStatus("1");

                //应收列表数据: 10: 本期抄表收款结束时间
                Date receivingDate = new Date(currentBillingTime.getTime() + 1);
                Calendar a = Calendar.getInstance();
                a.setTime(receivingDate);
                int day = a.get(Calendar.DAY_OF_MONTH);
                if (day <= 15) {
                    a.set(Calendar.DAY_OF_MONTH, 15);
                } else {
                    a.add(Calendar.MONTH, 1);
                    a.set(Calendar.DAY_OF_MONTH, 15);
                }
                receiptEntity.setReceivingDate(DateFormatUtil.dayEnd(a.getTime()));
            } else {
                throw new Exception("查询出该合同绑定的电表不存在");
            }
        }

        //插入应收列表数据
//        electricityReceiptManager.create(receiptEntity);

        int res = electricityRecordDbDao.batchInsert(recordEntityList);

        return res;
    }

    /**
     * @return
     * @Description 生成应收账单
     * @Auth luming
     * @Date 2019/8/8 10:17
     * @Version 1.0
     * @Param
     */
    @Override
    @Transactional
    public int createBill(List<ElectricityRecordEntity> list, Date receivingDate) throws Exception {
        String ctCode = list.get(0).getCtCode();
        Date recordMonth = list.get(0).getRecordMonth();
        String eleCode = CodeGenUtils.generate();

        ElectricityReceiptEntity receiptEntity = new ElectricityReceiptEntity();
        receiptEntity.setEleCode(eleCode);
        receiptEntity.setCtCode(ctCode);
        receiptEntity.setLastBillingTime(recordMonth);// 抄表记录所属的月份，即：代表当前抄表所属的期
        // receiptEntity.setCurrentBillingTime(entity.getCurrentMeterRecordDate());

        // 查询合同信息，从合同中取出所需数据
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("ctCode", ctCode);
        map.put("isDeleted", Boolean.FALSE);
        ContractEntity contractEntity = contractDbDao.selectUniqueByMap(map);

        if (contractEntity != null) {
            // 总用电量
            BigDecimal totalElectricity = contractEntity.getTotalElectricity();

            // 基础电费单价
            BigDecimal basicElectricityUnitPrice = contractEntity.getBasicElectricityUnitPrice();

            // 加收损耗(%)
            BigDecimal addLoss = contractEntity.getAddLoss();
            addLoss = addLoss.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP).add(new BigDecimal(1));

            // 每月基础电费
            BigDecimal bm = basicElectricityUnitPrice.multiply(totalElectricity);
            receiptEntity.setBasicElectricityPriceByMonth(bm);

            //查询出该合同绑定的电表
            List<ElectricityMeterEntity> meterList = electricityMeterDbDao.selectByMap(map, "id", true);

            if (meterList.size() > 0) {
                BigDecimal electricityPeakPrice = new BigDecimal(0);// 电费峰单价
                BigDecimal electricityFlatPrice = new BigDecimal(0);// 电费平单价
                BigDecimal electricityTroughPrice = new BigDecimal(0);// 电费谷单价
                BigDecimal electricityAveragePrice = new BigDecimal(0);// 电费均价单价

                BigDecimal multiple = new BigDecimal(0);// 电表倍率

                BigDecimal allElePrice = new BigDecimal(0);// 多表用电总费用

                for (ElectricityRecordEntity er : list) {
                    String eleMeterCode = er.getEleMeterCode();
                    er.setEleCode(eleCode);
                    er.setHasBill(true);

                    // 去电表集合中找出本条数据对应的电表
                    for (ElectricityMeterEntity em : meterList) {
                        if (eleMeterCode.equals(em.getEleMeterCode())) {
                            electricityPeakPrice = em.getElectricityPeakPrice();
                            electricityFlatPrice = em.getElectricityFlatPrice();
                            electricityTroughPrice = em.getElectricityTroughPrice();
                            electricityAveragePrice = em.getElectricityAveragePrice();

                            multiple = em.getMultiple();
                        }
                    }

                    // 若峰值单价或均值单价都为空，说明未找到该电表，抛异常，结束循环
                    if (electricityPeakPrice == null && electricityAveragePrice == null) {
                        throw new Exception("未找到对应电表");
                    }

                    // 2.计算用电费用总额

                    String eleType = er.getElectricityBillingType();

                    // 一个电表用电费用
                    BigDecimal elePrice = new BigDecimal(0);
                    // 峰、平、谷计费方式
                    if ("2".equals(eleType)) {
                        // 峰、平、谷电量 * 单价
                        BigDecimal peak = er.getPeakDegree().multiply(electricityPeakPrice);
                        BigDecimal flat = er.getFlatDegree().multiply(electricityFlatPrice);
                        BigDecimal trough = er.getTroughDegree().multiply(electricityTroughPrice);

                        // 用电费用 = （峰 + 平 + 谷）* 电表倍率 * 电损
                        elePrice = (peak.add(flat).add(trough)).multiply(multiple).multiply(addLoss);

                        // 得到用到的总用电量 = 峰 + 平 + 谷
                        BigDecimal rDegree = er.getPeakDegree().add(er.getFlatDegree().add(er.getTroughDegree()));
                    } else if ("1".equals(eleType)) {
                        // 均价计费方式 = 实用度数 * 电费均价单价 * 电表倍率 * 电损
                        elePrice = er.getRealDegree().multiply(electricityAveragePrice).multiply(multiple).multiply(addLoss);
                    }
                    allElePrice = allElePrice.add(elePrice);
                    OperateFillUtils.fill(er);
                }

                // 应收列表数据: 6: 用电费用总和
                receiptEntity.setElectricityPrice(allElePrice);

                // 应收列表数据: 7: 应收电费 = 每月基础电费 + 用电费用总和
                receiptEntity.setReceivablePrice(bm.add(allElePrice));

                // 应收列表数据: 8: 实收电费
                receiptEntity.setRealPrice(new BigDecimal(0));

                // 应收列表数据: 9: 支付状态
                receiptEntity.setPayStatus("1");

                // 应收列表数据: 10: 本期抄表收款结束时间
                receiptEntity.setReceivingDate(receivingDate);
                OperateFillUtils.fill(receiptEntity);
            }
        }

        int insert = electricityReceiptDbDao.insert(receiptEntity);

        // 批量更新电表抄表
        int update = electricityRecordDbDao.batchUpdate(list);

        return insert;
    }

    /**
     * @return void
     * @Description 检验必填字段
     * @author luming
     * @Date 2019/6/5 10:40
     * @Version 1.0
     * @Param [electricityRecordEntity]
     */
    @Override
    public void checkParams(ElectricityRecordEntity electricityRecordEntity) throws IllegalAccessException {
        List<String> list = new ArrayList<>();
        list.add("eleMeterCode");
        list.add("ctCode");
        list.add("eleMeterName");
        list.add("electricityBillingType");
        if ("1".equals(electricityRecordEntity.getElectricityBillingType())) {
            list.add("lastEleAverageDegree");
            list.add("currentEleAverageDegree");
            list.add("realDegree");
        } else if ("2".equals(electricityRecordEntity.getElectricityBillingType())) {
            list.add("lastPeakDegree");
            list.add("currentPeakDegree");
            list.add("lastTroughDegree");
            list.add("currentTroughDegree");
            list.add("lastFlatDegree");
            list.add("currentFlatDegree");
            list.add("peakDegree");
            list.add("troughDegree");
            list.add("flatDegree");
        }

        ParamUtil<ElectricityRecordEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(electricityRecordEntity, list);
    }
}
