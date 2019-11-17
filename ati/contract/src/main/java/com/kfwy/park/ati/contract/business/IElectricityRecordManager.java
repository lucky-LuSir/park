package com.kfwy.park.ati.contract.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.ati.contract.entity.ElectricityRecordEntity;

import java.util.Date;
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
public interface IElectricityRecordManager extends IManager<ElectricityRecordEntity> {

    /**
     * @Description 插入抄表记录信息
     * @author wangchuang
     * @Date 2018/8/22 9:38
     * @Version 1.0
     * @Param [recordEntityList]
     * @return int
     **/
    int batchCreate(List<ElectricityRecordEntity> recordEntityList,List<Date> eleTime) throws Exception;

    /**
     * @Description 生成应收账单
     * @Auth luming
     * @Date 2019/8/8 10:17
     * @Version 1.0
     * @Param
     * @return
     */
    int createBill(List<ElectricityRecordEntity> list, Date receivingDate) throws Exception;

    /**
     * @Description 校验必填字段
     * @author luming
     * @Date 2019/6/5 10:39
     * @Version 1.0
     * @Param [throwLeaseApplyEntity]
     * @return void
     */
    void checkParams(ElectricityRecordEntity electricityRecordEntity) throws IllegalAccessException;
}
