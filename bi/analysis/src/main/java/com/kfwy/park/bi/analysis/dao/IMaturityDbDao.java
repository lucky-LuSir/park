package com.kfwy.park.bi.analysis.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.analysis.entity.MaturityEntity;

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
public interface IMaturityDbDao extends IMyBatisBaseDao<MaturityEntity, Long> {

    public static final String SELECT_MATURITY_CONTRACT = "selectMaturityContract";
    public static final String SELECT_MATURITY_CONTRACT_BY_TIME_AND_PARK = "selectMaturityContractByTimeAndPark";

    /**
     * @Description 查询某年每个月的合同到期数
     * @author wangchuang
     * @Date 2019/5/21 9:27
     * @Version 1.0
     * @Param [map]
     * @return java.util.List<com.kfwy.park.bi.analysis.entity.MaturityEntity>
     **/
    List<MaturityEntity> selectMaturityInfo(Map<String, Object> map);

    /**
     *@author gtaotao
     *@Date 2019-08-01 16:18:11
     *@Description 查询某短时间内,某园区每个月的合同到期数
     *@param
     *@return
     **/
    List<MaturityEntity> selectMaturityContractByTimeAndPark(Map<String, Object>map);
}
