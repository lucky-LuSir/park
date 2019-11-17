package com.kfwy.park.bi.report.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.bi.report.dao.IFirstReceivableBiDbDao;
import com.kfwy.park.bi.report.entity.FirstReceivableBiEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @Description 首笔款报表
 * @Auth luming
 * @Date 2019/1/8 19:18
 * @Version 1.0
 * @Param
 * @return
 */
@Repository
public class FirstReceivableBiDbDaoImpl extends AbstractMyBatisDao<FirstReceivableBiEntity, Long> implements IFirstReceivableBiDbDao {

    /**
     * @Description 查询首笔款的日期：日期最早的为首笔款
     * @Auth luming
     * @Date 2019/1/8 18:59
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<FirstReceivableBiEntity> selectFirstDate(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_FIRST_DATE, map);
    }

    /**
     * @Description 查询首笔款信息
     * @Auth luming
     * @Date 2019/1/8 19:14
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public FirstReceivableBiEntity selectFirstInfo(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_FIRST_INFO, map);
    }
}
