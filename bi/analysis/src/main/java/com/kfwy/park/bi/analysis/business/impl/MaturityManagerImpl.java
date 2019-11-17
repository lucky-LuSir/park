package com.kfwy.park.bi.analysis.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.bi.analysis.business.IMaturityManager;
import com.kfwy.park.bi.analysis.dao.IMaturityDbDao;
import com.kfwy.park.bi.analysis.entity.MaturityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
@Service
public class MaturityManagerImpl extends AbstractManager<MaturityEntity> implements IMaturityManager {

    @Autowired
    private IMaturityDbDao maturityDbDao;


    @Override
    protected IMyBatisBaseDao<MaturityEntity, Long> getMyBatisDao() {
        return this.maturityDbDao;
    }

    /**
     * @Description 查询某年每个月的合同到期数
     * @author wangchuang
     * @Date 2019/5/21 18:54
     * @Version 1.0
     * @Param [selectYear]
     * @return java.util.List<com.kfwy.park.bi.analysis.entity.MaturityEntity>
     **/
    @Override
    public List<MaturityEntity> selectMaturityInfo(String selectYear) {

        int year = Integer.parseInt(selectYear);

        //返回结果集
        List<MaturityEntity> result = new ArrayList<>();


        //查询出来一年的统计数量集合
        List<MaturityEntity> maturityEntityList = null;

        //实体
        MaturityEntity maturityEntity = null;

        //用于判断是否是查询出来的月份
        String equalsMonth = "";

        //请求map
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        for (int i=0; i<3; i++) {

            String yearStr = String.valueOf(year);

            //查询出该年按月统计的到期合同数量集
            map.put("selectYear",yearStr);
            maturityEntityList = maturityDbDao.selectMaturityInfo(map);

            //查询结果集合下标
            int n = 0;

            //检查查询出来的结果是否是12个月份的数据，某月没数据就补0（数据库只查询出来有数据的月）
            for (int j=0;j<12;j++) {
                //数据库取出来的月份是01、02、03...类型，个位数字符比对时也应该补0
                equalsMonth = String.format("%02d",j+1);

                if(n < maturityEntityList.size() && maturityEntityList.get(n) != null && maturityEntityList.get(n).getMaturityMonth().equals(equalsMonth)) {//本月有数据，添加到返回集合中
                    result.add(maturityEntityList.get(n));
                    n++;
                } else {
                    maturityEntity = new MaturityEntity();
                    maturityEntity.setMaturityYear(yearStr);
                    maturityEntity.setMaturityCount(0);

                    maturityEntity.setMaturityMonth(equalsMonth);
                    result.add(maturityEntity);
                }
            }

            year++;
        }

        /**
         * 这里加个校验，校验规则：
         * 正常应该返回3年即36条数据，若不是36条数据，则说明返回数据不对，直接抛出异常
         */
        if(result.size() != 36) {
            throw new BusinessException("合同到期监控查询数据不准确");
        }
        return result;
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
        return maturityDbDao.selectMaturityContractByTimeAndPark(map);
    }
}
