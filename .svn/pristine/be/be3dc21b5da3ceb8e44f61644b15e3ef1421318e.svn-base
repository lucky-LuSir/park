package com.kfwy.park.controller.job.pro;

import com.gniuu.framework.common.context.CurrentContext;
import com.kfwy.park.ati.contract.business.IContractManager;
import com.kfwy.park.ati.contract.business.IRentReceiptManager;
import com.kfwy.park.ati.customer.dao.ICustomerDbDao;
import com.kfwy.park.pro.house.bi.business.IParkBiManager;
import com.kfwy.park.pro.house.bi.entity.ParkBiEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description TODO
 * @author wangchuang
 * @Date 2018/7/18 17:22
 * @Version 1.0
 * @Param 
 * @return 
 **/
@Component
public class ParkListJob {

    @Autowired
    private IContractManager contractManager;
    @Autowired
    private IRentReceiptManager rentReceiptManager;
    @Autowired
    private IParkBiManager parkBiManager;
    @Autowired
    private ICustomerDbDao customerDbDao;


    /**
     * @Description 1、统计15天内应缴租金户
     *                2、统计2个月内合同到期数
     *                3、费用逾期数
     *                4、意向客户数(谈判中的客户)
     *                5、入住企业数
     * @author wangchuang
     * @Date 2018/7/18 17:25
     * @Version 1.0
     * @Param []
     * @return void
     **/
    public void expiring() {

        System.out.println("====================定时器开始=======================");

        //查询所有园区
        Map<String, Object> parkMap = new HashMap<String, Object>();
        parkMap.put("isDeleted", false);
        parkMap.put("cpyCode", CurrentContext.getCpyCode());
        List<ParkBiEntity> allParkList = parkBiManager.findByMap(parkMap);
        String pkCode;
        Long pkId;
        if (allParkList != null) {
            for (int i=0;i<allParkList.size();i++) {
                ParkBiEntity pbEntity = new ParkBiEntity();
                pkCode = allParkList.get(i).getPkCode();
                pkId = allParkList.get(i).getId();
                if (StringUtils.isEmpty(pkCode)&&(pkId == 0)) {
                    continue;
                }
                pbEntity.setId(pkId);
                pbEntity.setPkCode(pkCode);

                /*-------------------统计15天内应缴租金户--------------*/

                Calendar c = Calendar.getInstance();

                //控制时为00
                c.set(Calendar.HOUR_OF_DAY,0);
                //控制分为00
                c.set(Calendar.MINUTE,0);
                //控制秒为00
                c.set(Calendar.SECOND,0);

                Date time = c.getTime();


                c.add(Calendar.DAY_OF_MONTH,15);

                Date time2 = c.getTime();


                //查询出该园区下的合同编号
                Map<String, Object> rentMap = new HashMap<String, Object>();
                rentMap.put("pkCode", pkCode);
                rentMap.put("receivingDateStart", time);
                rentMap.put("receivingDateEnd", time2);
                rentMap.put("isDeleted", false);
                rentMap.put("cpyCode", CurrentContext.getCpyCode());
                //获取到15天内应缴租金户
                int rmNum = rentReceiptManager.rentMaturityTime(rentMap);

                //查询费用逾期数
                int soNum = rentReceiptManager.selectOverdueCount(pkCode);

                /*-------------------2个月内合同到期数-----------------*/

                Calendar c2 = Calendar.getInstance();

                //控制时为00
                c2.set(Calendar.HOUR_OF_DAY,0);
                //控制分为00
                c2.set(Calendar.MINUTE,0);
                //控制秒为00
                c2.set(Calendar.SECOND,0);


                Map<String, Object> contractMap = new HashMap<String,Object>();

                contractMap.put("pkCode",pkCode);
                contractMap.put("contractEndTimeStart",c2.getTime());
                contractMap.put("cpyCode", CurrentContext.getCpyCode());

                //获取2个月后的时间
                c2.add(Calendar.MONTH,2);
                contractMap.put("contractEndTimeEnd",c2.getTime());

                //查询2个月内合同到期数
                int cmNum = contractManager.contractMaturityTime(contractMap);

                //15天内应缴租金户
                pbEntity.setRentWarningCount(rmNum);
                //费用逾期数
                pbEntity.setOverdueCount(soNum);
                //2个月内合同到期数
                pbEntity.setCtWarningCount(cmNum);


                /*------------------------意向客户数(谈判中的客户)------------------*/
                Map<String, Object> intentCusMap = new HashMap<String, Object>();
                intentCusMap.put("pkCode",pkCode);
                intentCusMap.put("cusStatus","2");
                intentCusMap.put("isDeleted", false);
                intentCusMap.put("cpyCode", CurrentContext.getCpyCode());

                int intentCount = customerDbDao.countByMap(intentCusMap);
                pbEntity.setIntentCusCount(intentCount);
                /*------------------------入住企业数--------------*/
                int conCount = contractManager.queryConByPkCode(pkCode);
                pbEntity.setCusCount(conCount);

                //将统计数据跟新到园区列表中
                parkBiManager.update(pbEntity);

            }
        }


    }
}
