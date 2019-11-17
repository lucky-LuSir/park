package com.kfwy.park.bi.report.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.dao.IRentReceiptDbDao;
import com.kfwy.park.ati.contract.entity.RentReceiptEntity;
import com.kfwy.park.bi.report.business.IAccountReceivableBiManager;
import com.kfwy.park.bi.report.dao.IAccountReceivableBiDbDao;
import com.kfwy.park.bi.report.entity.AccountReceivableBiEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 应收费用报表
 * @Auth luming
 * @Date 2018/12/14 11:27
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class AccountReceivableBiManagerImpl extends AbstractManager<AccountReceivableBiEntity> implements IAccountReceivableBiManager {

    @Autowired
    private IAccountReceivableBiDbDao accountReceivableBiDbDao;

    @Autowired
    private IRentReceiptDbDao rentReceiptDbDao;

    @Override
    protected IMyBatisBaseDao<AccountReceivableBiEntity, Long> getMyBatisDao() {
        return this.accountReceivableBiDbDao;
    }

    /**
     * @Description 查询租金信息
     * @Auth luming
     * @Date 2018/12/14 15:13
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<AccountReceivableBiEntity> selectRentInfo(Map<String, Object> map) {

        /*
         * 检查每条数据是否有逾期天数和滞纳金，存在逾期天数则将滞纳金计入总应收里面(此操作只是用于汇总展示，并未更新数据库数据)
         */
        List<AccountReceivableBiEntity> resList = accountReceivableBiDbDao.selectRentInfo(map);
        if(resList != null && resList.size()>0) {
            for (AccountReceivableBiEntity acco : resList) {
                //校验是否存在逾期，存在逾期则将滞纳金算入总应收
                if(acco.getOverdueDays() != null && acco.getOverdueDays() > 0) {
                    acco.setTotalPrice(acco.getTotalPrice().add(acco.getLatePayment()));
                }
            }
        }

        return resList;
    }


    /**
     * @Description 因为税点本来是10%，现在税务局改为9%，税金在租金里面包含着，所以现在这个方法就是实现  租金/1.1*1.09  算出9个点应收钱
     * @author wangchuang
     * @Date 2019/4/23 9:50
     * @Version 1.0
     * @Param []
     * @return void
     **/
    public void mainTest() {

        Date date1 = new Date();
        Date date2 = new Date();

        Calendar c1 = Calendar.getInstance();
        c1.setTime(date1);
        c1.add(Calendar.MONTH, 1);

        Date monthBegin = DateFormatUtil.dayBegin(DateFormatUtil.monthBegin(c1.getTime()));

        Calendar c2 = Calendar.getInstance();
        c2.setTime(date2);
        c2.add(Calendar.YEAR, 20);

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("startDate", monthBegin);
        map.put("endDate", c2.getTime());
        map.put("ctStatus", "4");
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<AccountReceivableBiEntity> resList = accountReceivableBiDbDao.selectRentInfo(map);
        int i = 1;

        List<RentReceiptEntity> res = new ArrayList<>();

        //新租金
        BigDecimal newReceivableRent = new BigDecimal(0);

        //新总应收
        BigDecimal newTotalPrice = new BigDecimal(0);

        for(AccountReceivableBiEntity entity : resList) {
            if(entity.getTaxPoint() != null && "4".equals(entity.getTaxPoint())) {
                System.out.println("查询出来要修改数据序号："+ i);
                System.out.println("租金："+entity.getReceivableRent()+"=============税点："+entity.getTaxPointName()+"=============物业费："+entity.getPropertyPrice()+"=============总计："+ entity.getTotalPrice());

                /**
                 * 只操作本来税点为10%的数据
                 * 开始计算  新租金 = 租金/1.1*1.09  再算总应收
                 */

//                newReceivableRent = (entity.getReceivableRent().divide(new BigDecimal(1.1)).multiply(new BigDecimal(1.09))).setScale(2,BigDecimal.ROUND_HALF_UP);
                newReceivableRent = entity.getReceivableRent().multiply(new BigDecimal(1.09)).divide(new BigDecimal(1.1), 2, BigDecimal.ROUND_HALF_UP);
                newTotalPrice = newReceivableRent.add(entity.getPropertyPrice());
                System.out.println("更改后的数据-------租金："+newReceivableRent+"-----总应收"+newTotalPrice);

                entity.setReceivableRent(newReceivableRent);
                entity.setTotalPrice(newTotalPrice);
                RentReceiptEntity en = new RentReceiptEntity();

                en.setRentCode(entity.getRentCode());
                en.setReceivableRent(newReceivableRent);
                en.setTotalPrice(newTotalPrice);

                res.add(en);

                i++;
            }
            System.out.println("++++++++++++++++++++++更改数据总条数："+res.size());
        }


        rentReceiptDbDao.batchUpdate(res);



    }

    /**
     *@author gtaotao
     *@Date 2019-07-31 09:08:08
     *@Description
     *@param
     *@return 获取租金已收,未收,逾期未收
     **/
    @Override
    public Map<String, Object> selectSumReceivable(Map<String, Object> map) {
        return accountReceivableBiDbDao.selectSumReceivable(map);
    }
}
