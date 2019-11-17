package com.kfwy.park.bi.analysis.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.bi.analysis.dao.IMaturityDbDao;
import com.kfwy.park.bi.analysis.entity.MaturityEntity;
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
public class MaturityDbDaoImpl extends AbstractMyBatisDao<MaturityEntity, Long> implements IMaturityDbDao {

    /**
     * @Description 查询某年每个月的合同到期数
     * @author wangchuang
     * @Date 2019/5/21 9:31
     * @Version 1.0
     * @Param [map]
     * @return java.util.List<com.kfwy.park.bi.analysis.entity.MaturityEntity>
     **/
    @Override
    public List<MaturityEntity> selectMaturityInfo(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_MATURITY_CONTRACT, map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-01 16:18:11
     *@Description 查询某短时间内,某园区每个月的合同到期数
     *@param
     *@return
     **/
    @Override
    public List<MaturityEntity> selectMaturityContractByTimeAndPark(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_MATURITY_CONTRACT_BY_TIME_AND_PARK, map);
    }
}
