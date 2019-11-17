package com.kfwy.park.ati.contract.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.entity.RentReceiptEntity;

import java.util.List;
import java.util.Map;

/**
 * Created by xiongzhan on 2018/6/6.
 */
public interface IRentReceiptDbDao extends IMyBatisBaseDao<RentReceiptEntity,Long> {

    /**
     * 查询费用逾期数
     */
    public static final String SELECT_OVERDUE_COUNT = "selectOverdueCount";

    public static final String SELECT_BY_PARK = "selectByPark";

    /**
     * @Description 查询费用逾期数
     * @author wangchuang
     * @Date 2018/7/19 20:25
     * @Version 1.0
     * @Param [pkCode]
     * @return int
     **/
    public int selectOverdueCount(String pkCode);

    List<RentReceiptEntity> selectByPark(Map<String,Object> map);
}
