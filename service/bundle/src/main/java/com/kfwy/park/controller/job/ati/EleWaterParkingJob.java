package com.kfwy.park.controller.job.ati;

import com.gniuu.framework.common.context.CurrentContext;
import com.kfwy.park.ati.contract.business.IElectricityReceiptManager;
import com.kfwy.park.ati.contract.business.IParkingReceiptManager;
import com.kfwy.park.ati.contract.business.IWaterReceiptManager;
import com.kfwy.park.ati.contract.entity.ElectricityReceiptEntity;
import com.kfwy.park.ati.contract.entity.ParkingReceiptEntity;
import com.kfwy.park.ati.contract.entity.WaterReceiptEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Description 未按时缴水电费和停车费的改为逾期
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Component
public class EleWaterParkingJob {

    @Autowired
    private IElectricityReceiptManager electricityReceiptManager;

    @Autowired
    private IWaterReceiptManager waterReceiptManager;

    @Autowired
    private IParkingReceiptManager parkingReceiptManager;

    public void overdue() {
        System.out.println("====================定时器水电费和停车费overdue开始=======================");

        Map<String, Object> param = null;
        //获取当前时间
        Date receivingDate = new Date();
        for(int j=0;j<2;j++) {
            if (j==0) {
                //查询支付状态为"1"的数据参数
                param = new HashMap<String,Object>();
                param.put("receivingDateEnd",receivingDate);
                param.put("payStatus","1");
                param.put("isDeleted",false);
            } else if(j==1) {
                //查询支付状态为"4"的数据参数
                param = new HashMap<String,Object>();
                param.put("receivingDateEnd",receivingDate);
                param.put("payStatus","4");
                param.put("isDeleted",false);
            }

            param.put("cpyCode", CurrentContext.getCpyCode());
            /**
             * 电费待支付状态改为逾期
             */
            List<ElectricityReceiptEntity> eleList = electricityReceiptManager.findByMap(param);

            if (eleList != null){
                for (int i=0;i<eleList.size();i++) {
                    ElectricityReceiptEntity ele = eleList.get(i);
                    //如果为待支付,转成逾期
                    ele.setPayStatus("3");

                    electricityReceiptManager.update(ele);

                }
            }


            /**
             * 水费待支付状态改为逾期
             */

            List<WaterReceiptEntity> waterList = waterReceiptManager.findByMap(param);

            if (waterList != null) {
                for (int i=0;i<waterList.size();i++) {
                    WaterReceiptEntity water = waterList.get(i);
                    //如果为待支付,转成逾期
                    water.setPayStatus("3");

                    waterReceiptManager.update(water);

                }
            }

            /**
             * 停车费待支付状态改为逾期
             */

            List<ParkingReceiptEntity> parkingList = parkingReceiptManager.findByMap(param);

            if (parkingList != null) {
                for (int i=0;i<parkingList.size();i++) {
                    ParkingReceiptEntity parking = parkingList.get(i);
                    //如果为待支付,转成逾期
                    parking.setPayStatus("3");

                    parkingReceiptManager.update(parking);

                }
            }
        }



        System.out.println("====================定时器水电费和停车费overdue结束=======================");

    }
}
