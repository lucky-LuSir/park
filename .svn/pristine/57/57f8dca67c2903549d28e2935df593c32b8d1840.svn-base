package com.kfwy.park.bi.report.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.report.entity.FirstReceivableBiEntity;

import java.util.List;
import java.util.Map;

/**
 * @Description 查询首笔款
 * @Auth luming
 * @Date 2019/1/8 18:49
 * @Version 1.0
 * @Param
 * @return
 */
public interface IFirstReceivableBiDbDao extends IMyBatisBaseDao<FirstReceivableBiEntity, Long> {

    public static final String SELECT_FIRST_DATE = "selectFirstDate";
    public static final String SELECT_FIRST_INFO = "selectFirstInfo";

    /**
     * @Description 查询首笔款的日期：日期最早的为首笔款
     * @Auth luming
     * @Date 2019/1/8 18:52
     * @Version 1.0
     * @Param
     * @return
     */
    List<FirstReceivableBiEntity> selectFirstDate(Map<String, Object> map);

    /**
     * @Description 查询首笔款信息
     * @Auth luming
     * @Date 2019/1/8 19:14
     * @Version 1.0
     * @Param
     * @return
     */
    FirstReceivableBiEntity selectFirstInfo(Map<String, Object> map);
}
