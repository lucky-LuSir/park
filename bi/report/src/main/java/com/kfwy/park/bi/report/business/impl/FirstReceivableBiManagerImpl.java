package com.kfwy.park.bi.report.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.bi.report.business.IFirstReceivableBiManager;
import com.kfwy.park.bi.report.dao.IFirstReceivableBiDbDao;
import com.kfwy.park.bi.report.entity.FirstReceivableBiEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 首笔款报表
 * @Auth luming
 * @Date 2019/1/8 19:19
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class FirstReceivableBiManagerImpl extends AbstractManager<FirstReceivableBiEntity> implements IFirstReceivableBiManager {

    @Autowired
    private IFirstReceivableBiDbDao firstReceivableBiDbDao;

    @Override
    protected IMyBatisBaseDao<FirstReceivableBiEntity, Long> getMyBatisDao() {
        return this.firstReceivableBiDbDao;
    }


    /**
     * @Description 查询首笔款报表
     * @Auth luming
     * @Date 2019/1/9 15:03
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<FirstReceivableBiEntity> selectFirstReceivableList(FirstReceivableBiEntity firstReceivableBiEntity) {

        Map<String, Object> map = new HashMap<>();

        /**
         * 查询某个日期范围内的租金首笔款集合
         * 时间最早的为首笔款
         */
        if (firstReceivableBiEntity.getStartDate() != null && firstReceivableBiEntity.getEndDate() != null) {
            Date dayBegin = DateFormatUtil.dayBegin(firstReceivableBiEntity.getStartDate());
            Date dayEnd = DateFormatUtil.dayEnd(firstReceivableBiEntity.getEndDate());
            map.put("startDate", dayBegin);
            map.put("endDate", dayEnd);
        } else {
            throw new BusinessException("查询日期不能为空！");
        }
        map.put("cpyCode", CurrentContext.getCpyCode());
        List<FirstReceivableBiEntity> firstReceivableList = firstReceivableBiDbDao.selectFirstDate(map);

        /**
         * 根据首笔款集合关联合同信息，查询首笔款报表
         */
        List<FirstReceivableBiEntity> list = new ArrayList<>();

        for (FirstReceivableBiEntity firstList : firstReceivableList) {
            String ctCode = firstList.getCtCode();
            String rentCode = firstList.getRentCode();
            Date rentStartTime = firstList.getRentStartTime();

            Map<String, Object> params = new HashMap<>();
            params.put("ctCode", ctCode);
            params.put("rentCode", rentCode);
            params.put("rentStartTime", rentStartTime);

            if(!StringUtils.isEmpty(firstReceivableBiEntity.getContractorDeptCode())){
                params.put("contractorDeptCode",firstReceivableBiEntity.getContractorDeptCode());
            }
            if(!StringUtils.isEmpty(firstReceivableBiEntity.getPkCode())){
                params.put("pkCode",firstReceivableBiEntity.getPkCode());
            }
            if (firstReceivableBiEntity.getStartDate() != null && firstReceivableBiEntity.getEndDate() != null) {
                Date dayBegin = DateFormatUtil.dayBegin(firstReceivableBiEntity.getStartDate());
                Date dayEnd = DateFormatUtil.dayEnd(firstReceivableBiEntity.getEndDate());
                params.put("startDate", dayBegin);
                params.put("endDate", dayEnd);
            } else {
                throw new BusinessException("查询日期不能为空！");
            }

            params.put("cpyCode", CurrentContext.getCpyCode());
            params.put("ctStatus", "4");

            FirstReceivableBiEntity firstEntity = firstReceivableBiDbDao.selectFirstInfo(params);

            if (firstEntity != null) {
                list.add(firstEntity);
            }
        }

        return list;
    }
}
