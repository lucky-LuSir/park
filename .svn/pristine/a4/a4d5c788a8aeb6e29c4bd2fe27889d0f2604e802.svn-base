package com.kfwy.park.ati.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.contract.dao.IRentReceiptDbDao;
import com.kfwy.park.ati.contract.entity.RentReceiptEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by xiongzhan on 2018/6/6.
 */
@Repository
public class RentReceiptDbDaoImpl extends AbstractMyBatisDao<RentReceiptEntity,Long> implements IRentReceiptDbDao{


    /**
     * @Description 查询费用逾期数
     * @author wangchuang
     * @Date 2018/7/19 20:25
     * @Version 1.0
     * @Param [pkCode]
     * @return int
     **/
    @Override
    public int selectOverdueCount(String pkCode) {
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_OVERDUE_COUNT, pkCode);
    }

    @Override
    public List<RentReceiptEntity> selectByPark(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_BY_PARK, map);
    }


}
