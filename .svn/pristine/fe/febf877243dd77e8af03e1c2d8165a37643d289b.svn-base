package com.kfwy.park.ati.contract.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.ati.contract.entity.RentReceiptEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by xiongzhan on 2018/6/6.
 */
public interface IRentReceiptManager  extends IManager<RentReceiptEntity> {

    /**
     * @Description 查询15天内应缴租金户
     * @author wangchuang
     * @Date 2018/7/19 15:01
     * @Version 1.0
     * @Param []
     * @return int
     **/
    public int rentMaturityTime(Map<String, Object> map);

    /**
     * @Description 查询费用逾期数
     * @author wangchuang
     * @Date 2018/7/19 20:22
     * @Version 1.0
     * @Param [pkCode]
     * @return int
     **/
    public int selectOverdueCount(String pkCode);

    void pay(RentReceiptEntity entity);

    List<RentReceiptEntity> findByPark(Map<String, Object> map);

}
