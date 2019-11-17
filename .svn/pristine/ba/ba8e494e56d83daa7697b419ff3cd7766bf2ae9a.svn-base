package com.kfwy.park.ati.contract.dao;

import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.ati.contract.entity.ContractHouseEntity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 */
public interface IContractDbDao extends IMyBatisBaseDao<ContractEntity, Long> {

    //根据pkCode查询合同
    public static final String SELECT_CON_BY_PK_CODE = "selectConByPkCode";

    //看板：我的看板，计算本月招商总面积
    public static final String THIS_MONTH_AREA_SUM = "thisMonthAreaSum";

    //看板：我的看板，本月新签合同
    public static final String THIS_MONTH_CONTRACT = "thisMonthContract";

    //查询某月存在的合同
    public static final String SELECT_CON_BY_MONTH = "selectConByMonth";

    // 合同审批状态
    public static final String CONTRACT_VERIFY = "contractVerify";

    //统计应收
    public static final String SUM_RECEIVABLE_PRICE = "sumReceivablePrice";

    public static final String  SELECT_INFO_BY_EMPCODE= "selectInfoByEmpCode";

    public static final String SELECT_INFO_BY_EMPCODE_COUNT ="selectInfoByEmpCodeCount";

    public static final String SELECT_BY_MM_AND_YY = "selectByMMAndYY";

    public static final String SELECT_BY_MM_AND_YY_COUNT = "selectByMMAndYYCount";

    public static final String SELECT_CUS = "selectCus";

    public static final String SELECT_RECEIVABLE_BY_PARK = "selectReceivableByPark";

    public static final String SELECT_THREE_MONTH_EXPIRED_CT_CODE ="selectThreeMonthExpiredCtCode";

    public static final String SELECT_MATURITY_AREA = "selectMaturityArea";

    public static final String SELECT_SUM_RENT_TOTAL = "selectSumRentTotal";

    public static final String SELECT_BY_CONTRACT_MONTH = "selectByContractMonth";

    public static final String SELECT_BY_TIME = "selectByTime";


    /**
     * @return
     * @Description 根据pkCode查询合同
     * @Auth luming
     * @Date 2018/7/27 19:54
     * @Version 1.0
     * @Param
     */
    int queryConByPkCode(String pkCode);

    /**
     * @return
     * @Description 看板：我的看板，计算本月招商总面积
     * @Auth luming
     * @Date 2018/8/13 15:09
     * @Version 1.0
     * @Param
     */
    int queryThisMonthAreaSum(Map<String, Object> map);

    /**
     * @Description 看板：我的看板，本月新签合同
     * @Auth luming
     * @Date 2018/8/14 11:22
     * @Version 1.0
     * @Param
     * @return
     */
    int queryThisMonthContract(Map<String, Object> map);


    /**
     * @Description 毛利润统计园区出租面积和金额
     * @Auth liuzhengyang
     * @Date 2018/10/17 9:33
     * @Version 1.0
     * @Param
     * @return
     */
    Map<String,BigDecimal> selectGross(Map<String, Object> map);

    /**
     * @Description 查询某月存在的合同
     * @author wangchuang
     * @Date 2018/11/1 11:48
     * @Version 1.0
     * @Param [map]
     * @return java.util.List<com.kfwy.park.ati.contract.entity.ContractEntity>
     **/
    List<ContractEntity> selectConByMonth(Map<String, Object> map);

    /**
     * @Description 合同审批状态
     * @Auth luming
     * @Date 2018/12/11 9:04
     * @Version 1.0
     * @Param
     * @return
     */
    int contractVerify(Map<String, Object> map);

    /**
     * @Description 计算房租，物业，水，电，保险的应收费用
     * @Auth gengtao
     * @Date 2019/3/15 8:44
     * @Version 1.0
     * @Param
     * @return
     */
    List<Map<String,Object>> sumReceivablePrice(Map<String,Object> map);

    /**
     *@Author gengtao
     *@Date 2019-04-09 09:03:37
     *@Description 根据管理员编号，获取合同信息
     *@Return
     **/
    List<ContractEntity> selectInfoByEmpCode(Map<String,Object> map);

    /**
     *@Author gengtao
     *@Date 2019-04-09 09:03:37
     *@Description 根据管理员编号，获取合同数量信息
     *@Return
     **/
    int selectInfoByEmpCodeCount(Map<String,Object> map);

    /**
     *@Author gengtao
     *@Date 2019-06-03 10:41:54
     *@Description 根据年份和月份获取信息
     *@Return
     **/
    List<ContractEntity> selectByMMAndYY(Map<String,Object> map);

    int selectByMMAndYYCount(Map<String, Object> map);

    List<ContractEntity> selectCus(Map<String, Object> map);

    /**
     *@author gtaotao
     *@Date 2019-07-31 15:22:14
     *@Description 获取近期到期合同编号
     *@param
     *@return
     **/
    List<String> selectThreeMonthExpiredCtCode(Map<String, Object>map);

    /**
     *@author gtaotao
     *@Date 2019-08-01 16:40:35
     *@Description 获取近期到期面积
     *@param
     *@return
     **/
    List<Map<String, Object>> selectMaturityArea(Map<String, Object> map);

    /**
     *@author gtaotao
     *@Date 2019-08-15 16:43:14
     *@Description 获取6个月营收信息
     *@param
     *@return
     **/
    BigDecimal selectSumRentTotal(Map<String, Object> map);
    /**
     *@author gtaotao
     *@Date 2019-08-15 16:43:14
     *@Description 获取6个月营收信息,查询合同结束日期在本月的合同
     *@param
     *@return
     **/
    List<ContractEntity> selectByContractMonth(Map<String, Object>map);

    /**
     *@author gtaotao
     *@Date 2019-08-15 16:43:14
     *@Description 获取6个月营收信息,获取每个月的合同
     *@param
     *@return
     **/
    List<ContractEntity> selectByTime(Map<String, Object> map);
}
