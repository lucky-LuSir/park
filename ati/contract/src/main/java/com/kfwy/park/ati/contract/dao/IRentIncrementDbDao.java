package com.kfwy.park.ati.contract.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.entity.RentIncrementEntity;

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
public interface IRentIncrementDbDao extends IMyBatisBaseDao<RentIncrementEntity, Long> {

    /**
     * 查询某合同在某月的递增信息
     */
    public static final String SELECT_ONE_INC_DEL = "selectOneIncDel";

    /**
     *
     */
    public static final  String DEL_INC_DEL_BY_CT_CODE = "delIncDelByCtCode";

    /**
     * @Description 查询某合同在某月的递增信息
     * @Date 2018/10/31 16:53
     * @author wangchuang
     * @Version 1.0
     * @Param [param]
     * @return java.util.List<com.kfwy.park.ati.contract.entity.RentIncrementEntity>
     **/
    List<RentIncrementEntity> selectOneIncDel(Map<String, Object> param);

    /**
     * @Description 根据合同编号物理删除本合同存在的递增详情
     * @author wangchuang
     * @Date 2019/1/3 9:06
     * @Version 1.0
     * @Param [param]
     * @return int
     **/
    int delIncDelByCtCode(Map<String, Object> param);
}
