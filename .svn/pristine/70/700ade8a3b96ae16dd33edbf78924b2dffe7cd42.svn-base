package com.kfwy.park.ati.contract.business;

import com.gniuu.framework.common.business.IManager;
import com.kfwy.park.ati.contract.entity.WaterRecordEntity;

import java.util.Date;
import java.util.List;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
public interface IWaterRecordManager extends IManager<WaterRecordEntity> {

    /**
     * @Description 记录抄水表信息并生成本期水费
     * @author wangchuang
     * @Date 2018/11/22 17:21
     * @Version 1.0
     * @Param [waterRecordList, waterTime]
     * @return int
     **/
    int batchCreate(List<WaterRecordEntity> waterRecordList, List<Date> waterTime);

    /**
     * @Description 生成应收账单
     * @Auth luming
     * @Date 2019/8/9 9:00
     * @Version 1.0
     * @Param
     * @return
     */
    int createBill(List<WaterRecordEntity> list, Date receivingDate) throws Exception;
}
