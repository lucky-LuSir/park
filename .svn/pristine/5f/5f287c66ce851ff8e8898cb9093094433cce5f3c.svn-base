package com.kfwy.park.ati.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.contract.dao.IRentIncrementDbDao;
import com.kfwy.park.ati.contract.entity.RentIncrementEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@Repository
public class RentIncrementDbDaoImpl extends AbstractMyBatisDao<RentIncrementEntity, Long> implements IRentIncrementDbDao {

    /**
     * @Description 查询某合同在某月的递增信息
     * @author wangchuang
     * @Date 2018/10/31 16:54
     * @Version 1.0
     * @Param [param]
     * @return java.util.List<com.kfwy.park.ati.contract.entity.RentIncrementEntity>
     **/
    @Override
    public List<RentIncrementEntity> selectOneIncDel(Map<String, Object> param) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_ONE_INC_DEL, param);
    }

    /**
     * @Description 根据合同编号物理删除本合同存在的递增详情
     * @author wangchuang
     * @Date 2019/1/3 9:07
     * @Version 1.0
     * @Param [param]
     * @return int
     **/
    @Override
    public int delIncDelByCtCode(Map<String, Object> param) {
        return this.getSqlSession().delete(this.mapperNamespace + DEL_INC_DEL_BY_CT_CODE, param);
    }
}
