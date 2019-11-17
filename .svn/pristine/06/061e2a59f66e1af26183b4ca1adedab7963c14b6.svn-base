package com.kfwy.park.controller.job.ati;

import com.gniuu.framework.common.context.CurrentContext;
import com.kfwy.park.ati.contract.business.IRentReceiptManager;
import com.kfwy.park.ati.contract.dao.IRentReceiptDbDao;
import com.kfwy.park.ati.contract.entity.RentReceiptEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzhengyang on 2018/7/18.
 */
@Component
public class ContractJob {

    @Autowired
    private IRentReceiptManager rentReceiptManager;
    @Autowired
    private IRentReceiptDbDao rentReceiptDbDao;

    /*
     * @Description 到交付日期,未交付的转成逾期,记录逾期天数和滞纳金
     * @Auth liuzhengyang
     * @Date 2018/7/18 11:29
     * @Version 1.0
     * @Param []
     * @return void
     */
    public void overdue() {
        System.out.println("====================定时器overdue开始=======================");

        //获取当天00:00:00
        Date receivingDate = DateFormatUtil.dayBegin(new Date());

        Map<String, Object> map = null;
        //1查询交钱日在这段时间的合同
        for(int j=0;j<2;j++) {
            if (j == 0) {
                map = new HashMap<String,Object>();
                map.put("receivingDateEnd",receivingDate);
                map.put("payStatus","1");
                map.put("isDeleted",false);
            } else if (j == 1) {
                map = new HashMap<String,Object>();
                map.put("receivingDateEnd",receivingDate);
                map.put("payStatus","4");
                map.put("isDeleted",false);
            }
            map.put("cpyCode", CurrentContext.getCpyCode());
            List<RentReceiptEntity> list = rentReceiptManager.findByMap(map);

            //2判断交钱状态
            if(list != null){
                for(int i = 0; i<list.size(); i++){
                    RentReceiptEntity rent = list.get(i);
                    //如果为待支付,转成逾期
                    rent.setPayStatus("3");
                    rentReceiptManager.update(rent);
                }
            }
        }

        //暂时关闭统计每条应收的逾期天数和滞纳金
        //统计逾期的天数和滞纳金并更新到每条逾期应收中
//        latePayment();

        System.out.println("========================定时器overdue结束==========================");
    }

    /**
     * @Description 统计逾期的天数和滞纳金并更新到每条逾期应收中
     * @author wangchuang
     * @Date 2019/4/12 10:06
     * @Version 1.0
     * @Param []
     * @return void
     **/
    public void latePayment() {

        //当前日期
        Date date = new Date();

        //查询逾期的费用
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("payStatus","3");
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<RentReceiptEntity> overdueList = rentReceiptManager.findByMap(map);

        /*
         * 遍历每条数据算出逾期天数和滞纳金
         * 滞纳金 = 月租金*12/365 * 5%
         */

        //本次应收间隔天数
        BigDecimal rentBetween = null;

        //日租金 (保留6位小数)
        BigDecimal dailyRent = null;

        //逾期天数
        BigDecimal overdueDays = null;

        //滞纳金
        BigDecimal latePayment = null;

        if (overdueList != null && overdueList.size() > 0) {
            for (RentReceiptEntity rent : overdueList) {

                //本次应收天数 = 相差天数+1
                rentBetween = DateFormatUtil.daysBetween(rent.getRentStartTime(), rent.getRentEndTime()).add(new BigDecimal(1));

                //日租金 (保留6位小数)
                dailyRent = rent.getReceivableRent().divide(rentBetween, 6, BigDecimal.ROUND_HALF_UP);

                //逾期天数 = 当天 - 应收款日 + 1
                overdueDays = (DateFormatUtil.daysBetween(rent.getReceivingDate(), date).add(new BigDecimal(1))).setScale(0, BigDecimal.ROUND_UP);
                rent.setOverdueDays(overdueDays.intValue());

                //保留两位小数  (滞纳金 = 日单价*逾期天数*5%)
                latePayment = dailyRent.multiply(overdueDays).multiply(new BigDecimal(0.05)).setScale(2,BigDecimal.ROUND_HALF_UP);
                rent.setLatePayment(latePayment);
            }

            rentReceiptDbDao.batchUpdate(overdueList);
        }
    }

    /*
     * @Description 提前一个月生成下期租金和物业费
     * @Auth liuzhengyang
     * @Date 2018/7/18 14:47
     * @Version 1.0
     * @Param []
     * @return void
     */
    public void generate() {


    }
}
