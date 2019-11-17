package com.kfwy.park.ati.contract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.business.IWaterRecordManager;
import com.kfwy.park.ati.contract.dao.IWaterReceiptDbDao;
import com.kfwy.park.ati.contract.dao.IWaterRecordDbDao;
import com.kfwy.park.ati.contract.entity.WaterReceiptEntity;
import com.kfwy.park.ati.contract.entity.WaterRecordEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Service
public class WaterRecordManagerImpl extends AbstractManager<WaterRecordEntity> implements IWaterRecordManager {

    @Autowired
    private IWaterRecordDbDao waterRecordDbDao;
    @Autowired
    private IWaterReceiptDbDao waterReceiptDbDao;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.waterRecordDbDao;
    }

    @Override
    protected void beforeCreate(WaterRecordEntity waterRecordEntity) {

    }

    /**
     * @Description 记录抄水表信息并生成本期水费
     * @author wangchuang
     * @Date 2018/11/22 17:22
     * @Version 1.0
     * @Param [waterRecordList, waterTime]
     * @return int
     **/
    @Override
    public int batchCreate(List<WaterRecordEntity> waterRecordList, List<Date> waterTime) {

        //转换时间格式00:00:000开始 23:59:999结束
        Date dayBegin = DateFormatUtil.dayBegin(waterTime.get(0));
        Date dayEnd = DateFormatUtil.dayEnd(waterTime.get(1));

        //本期费用实体
        WaterReceiptEntity waterReceipt = new WaterReceiptEntity();
        //本期费用： 1、本期水费编码
        String waterCode = CodeGenUtils.generate();
        waterReceipt.setWaterCode(waterCode);
        //本期费用:2、开始时间
        waterReceipt.setLastBillingTime(dayBegin);
        //本期费用:3、结束时间
        waterReceipt.setCurrentBillingTime(dayEnd);

        //本期抄表收款结束时间
        Date receivingDate = new Date(dayEnd.getTime()+1);
        Calendar a = Calendar.getInstance();
        a.setTime(receivingDate);
        int day = a.get(Calendar.DAY_OF_MONTH);
        if (day <= 15){
            a.set(Calendar.DAY_OF_MONTH,15);
        } else {
            a.add(Calendar.MONTH,1);
            a.set(Calendar.DAY_OF_MONTH,15);
        }

        //本期费用：4、截止日期
        waterReceipt.setReceivingDate(DateFormatUtil.dayEnd(a.getTime()));

        //本期费用: 5、合同编号
        waterReceipt.setCtCode(waterRecordList.get(0).getCtCode());

        //本期应收水费
        BigDecimal total = new BigDecimal(0);

        for(WaterRecordEntity recordEntity : waterRecordList) {
            recordEntity.setWaterRecordCode(CodeGenUtils.generate());
            // recordEntity.setWaterCode(waterCode);
            if("1".equals(recordEntity.getWaterBillingType())) {//均摊计费
                total = total.add(recordEntity.getWaterAmount());
            }else if("2".equals(recordEntity.getWaterBillingType())) {
                total = total.add(recordEntity.getWaterUnitPrice().multiply(recordEntity.getRealWater()));
            }
            OperateFillUtils.fill(recordEntity);
        }
        //本期费用：6、应收水费
        waterReceipt.setReceivablePrice(total);
        //本期费用：7、实收水费
        waterReceipt.setRealPrice(new BigDecimal(0));
        //本期费用：8、已收款
        waterReceipt.setPaidPrice(new BigDecimal(0));
        //本期费用: 9、状态
        waterReceipt.setPayStatus("1");

        OperateFillUtils.fill(waterReceipt);

        //记录抄表记录
        waterRecordDbDao.batchInsert(waterRecordList);

        //记录本期费用
        // waterReceiptDbDao.insert(waterReceipt);

        return 1;
    }

    /**
     * @Description 生成应收账单
     * @Auth luming
     * @Date 2019/8/9 9:00
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int createBill(List<WaterRecordEntity> list, Date receivingDate) throws Exception {
        String ctCode = list.get(0).getCtCode();
        Date recordMonth = list.get(0).getRecordMonth();
        String waterCode = CodeGenUtils.generate();

        WaterReceiptEntity waterReceipt = new WaterReceiptEntity();
        waterReceipt.setWaterCode(waterCode);
        waterReceipt.setCtCode(ctCode);
        waterReceipt.setLastBillingTime(recordMonth); // 上次抄表时间

        // 本期应收水费
        BigDecimal total = new BigDecimal(0);

        for (WaterRecordEntity wr : list) {
            String type = wr.getWaterBillingType();
            wr.setWaterCode(waterCode);
            wr.setHasBill(true);

            /**
             * 1为均价计费：water_amount水费金额累加
             * 2为抄表计费：水费单价 * 水实际用量
             */
            if ("1".equals(type)) {
                total = total.add(wr.getWaterAmount());
            } else if ("2".equals(type)) {
                total = total.add(wr.getWaterUnitPrice().multiply(wr.getRealWater()));
            }
        }

        // 本期费用：应收水费
        waterReceipt.setReceivablePrice(total);
        // 本期费用：实收水费
        waterReceipt.setRealPrice(new BigDecimal(0));
        // 本期费用：已收款
        waterReceipt.setPaidPrice(new BigDecimal(0));
        // 本期费用：状态
        waterReceipt.setPayStatus("1");
        // 截止收款日期
        waterReceipt.setReceivingDate(receivingDate);
        OperateFillUtils.fill(waterReceipt);

        int insert = waterReceiptDbDao.insert(waterReceipt);

        // 批量更新水表抄表
        int update = waterRecordDbDao.batchUpdate(list);

        return insert;
    }
}
