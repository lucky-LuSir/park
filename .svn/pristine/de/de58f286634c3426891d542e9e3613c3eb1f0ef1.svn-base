package com.kfwy.park.ati.contract.dao.impl;

import com.gniuu.framework.dataaccess.mybatis.AbstractMyBatisDao;
import com.kfwy.park.ati.contract.dao.IContractDbDao;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static com.kfwy.park.ati.contract.business.IContractManager.SELECT_GROSS;

/**
 * Created by davidcun on 2018/5/22.
 */
@Repository
public class ContractDbDaoImpl extends AbstractMyBatisDao<ContractEntity,Long> implements IContractDbDao {

    /**
     * @Description 根据pkCode查询合同
     * @Auth luming
     * @Date 2018/7/27 19:54
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int queryConByPkCode(String pkCode) {
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_CON_BY_PK_CODE,pkCode);
    }

    /**
     * @Description 看板：我的看板，计算本月招商总面积
     * @Auth luming
     * @Date 2018/8/13 15:09
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int queryThisMonthAreaSum(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + THIS_MONTH_AREA_SUM, map);
    }

    /**
     * @Description 看板：我的看板，本月新签合同
     * @Auth luming
     * @Date 2018/8/14 11:23
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int queryThisMonthContract(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + THIS_MONTH_CONTRACT,map);
    }

    /**
     * @Description 毛利润统计园区出租面积和金额
     * @Auth liuzhengyang
     * @Date 2018/10/17 9:33
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public Map<String,BigDecimal> selectGross(Map<String, Object> map) {
        Map param = this.getSqlSession().selectOne(this.mapperNamespace + SELECT_GROSS, map);
        return param;
    }

    /**
     * @Description 查询某月存在的合同
     * @author wangchuang
     * @Date 2018/11/1 11:48
     * @Version 1.0
     * @Param [map]
     * @return java.util.List<com.kfwy.park.ati.contract.entity.ContractEntity>
     **/
    @Override
    public List<ContractEntity> selectConByMonth(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_CON_BY_MONTH, map);
    }

    /**
     * @Description 合同审批状态
     * @Auth luming
     * @Date 2018/12/11 9:08
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int contractVerify(Map<String, Object> map) {
        return this.getSqlSession().update(this.mapperNamespace + CONTRACT_VERIFY, map);
    }

    @Override
    public List<Map<String,Object>> sumReceivablePrice(Map<String,Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace+SUM_RECEIVABLE_PRICE,map);
    }

    @Override
    public List<ContractEntity> selectInfoByEmpCode(Map<String,Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace+SELECT_INFO_BY_EMPCODE,map);
    }

    @Override
    public int selectInfoByEmpCodeCount(Map<String,Object> map){
        return this.getSqlSession().selectOne(this.mapperNamespace+SELECT_INFO_BY_EMPCODE_COUNT,map);
    }

    @Override
    public List<ContractEntity> selectByMMAndYY(Map<String,Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace+SELECT_BY_MM_AND_YY, map);
    }

    @Override
    public int selectByMMAndYYCount(Map<String, Object> map){
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_BY_MM_AND_YY_COUNT, map);
    }

    @Override
    public List<ContractEntity> selectCus(Map<String, Object> map){
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_CUS, map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-01 16:42:33
     *@Description 获取近期到期的合同编号
     *@param
     *@return
     **/
    @Override
    public List<String> selectThreeMonthExpiredCtCode(Map<String, Object>map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_THREE_MONTH_EXPIRED_CT_CODE, map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-01 16:40:35
     *@Description 获取近期到期面积
     *@param
     *@return
     **/
    @Override
    public List<Map<String, Object>> selectMaturityArea(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_MATURITY_AREA, map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-15 16:43:14
     *@Description 获取6个月营收信息
     *@param
     *@return
     **/
    @Override
    public BigDecimal selectSumRentTotal(Map<String, Object> map) {
        return this.getSqlSession().selectOne(this.mapperNamespace + SELECT_SUM_RENT_TOTAL, map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-15 16:43:14
     *@Description 获取6个月营收信息,查询合同结束日期在本月的合同
     *@param
     *@return
     **/
    @Override
    public List<ContractEntity> selectByContractMonth(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_BY_CONTRACT_MONTH, map);
    }

    /**
     *@author gtaotao
     *@Date 2019-08-15 16:43:14
     *@Description 获取6个月营收信息,获取每个月的合同
     *@param
     *@return
     **/
    @Override
    public List<ContractEntity> selectByTime(Map<String, Object> map) {
        return this.getSqlSession().selectList(this.mapperNamespace + SELECT_BY_TIME, map);
    }

}
