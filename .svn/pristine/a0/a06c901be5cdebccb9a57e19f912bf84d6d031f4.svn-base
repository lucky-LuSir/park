package com.kfwy.park.ati.contract.business;

import com.gniuu.framework.common.business.IManager;
import com.gniuu.framework.dataaccess.PageResult;
import com.kfwy.park.ati.contract.entity.ContractEntity;
import com.kfwy.park.pro.house.building.entity.BuildingEntity;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import org.omg.CORBA.OBJ_ADAPTER;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Created by davidcun on 2018/5/22.
 */
public interface IContractManager extends IManager<ContractEntity> {

    //看板:租金费用异常查询
    public static final String RENT_EXC_QUERY = "rentExcQuery";
    //看板:电费费用异常查询
    public static final String ELE_EXC_QUERY = "eleExcQuery";
    //看板:水费费用异常查询
    public static final String WATER_EXC_QUERY = "waterExcQuery";
    //看板:停车费费用异常查询
    public static final String PARKING_EXC_QUERY = "parkingExcQuery";
    //毛利润统计园区出租面积和金额
    public static final String SELECT_GROSS = "selectGross";

    /**
     * @return int
     * @Description 用于合同变更
     * @author wangchuang
     * @Date 2019/4/15 15:53
     * @Version 1.0
     * @Param [contractEntity]
     **/
    int updateContract(ContractEntity contractEntity);

    /**
     * @Description 修改合同基本信息专用接口
     * @author wangchuang
     * @Date 2019/6/13 10:05
     * @Version 1.0
     * @Param [contractEntity]
     * @return int
     **/
    int basicInfoUpdate(ContractEntity contractEntity);

    /**
     * @return int
     * @Description 删除合同
     * @author wangchuang
     * @Date 2019/4/15 16:34
     * @Version 1.0
     * @Param [contractEntity]
     **/
    int deleteContract(ContractEntity contractEntity);

    /**
     * @return int
     * @Description 2个月内合同到期数
     * @author wangchuang
     * @Date 2018/7/19 11:31
     * @Version 1.0
     * @Param [map]
     **/
    int contractMaturityTime(Map<String, Object> map);

    /**
     * @return
     * @Description 根据pkCode查询合同
     * @Auth luming
     * @Date 2018/7/28 8:43
     * @Version 1.0
     * @Param
     */
    int queryConByPkCode(String pkCode);

    /**
     * @return java.util.List<com.kfwy.park.ati.contract.entity.ContractEntity>
     * @Description 看板:租金费用异常查询
     * @author wangchuang
     * @Date 2018/8/7 16:34
     * @Version 1.0
     * @Param [param]
     **/
    PageResult<ContractEntity> queryRentCostException(Map<String, Object> param, int start, int pageSize);

    /**
     * @return com.gniuu.framework.dataaccess.PageResult<com.kfwy.park.ati.contract.entity.ContractEntity>
     * @Description 看板:电费费用异常查询
     * @author wangchuang
     * @Date 2018/8/8 16:21
     * @Version 1.0
     * @Param [pkCode, start, pageSize]
     **/
    PageResult<ContractEntity> queryEleCostException(Map<String, Object> param, int start, int pageSize);

    /**
     * @return com.gniuu.framework.dataaccess.PageResult<com.kfwy.park.ati.contract.entity.ContractEntity>
     * @Description 看板:水费费用异常查询
     * @author wangchuang
     * @Date 2018/8/8 18:22
     * @Version 1.0
     * @Param [pkCode, start, pageSize]
     **/
    PageResult<ContractEntity> queryWaterCostException(Map<String, Object> param, int start, int pageSize);

    /**
     * @return com.gniuu.framework.dataaccess.PageResult<com.kfwy.park.ati.contract.entity.ContractEntity>
     * @Description 看板:停车费费用异常查询
     * @author wangchuang
     * @Date 2018/8/8 18:59
     * @Version 1.0
     * @Param [pkCode, start, pageSize]
     **/
    PageResult<ContractEntity> queryParkingCostException(Map<String, Object> param, int start, int pageSize);

    /**
     * @return
     * @Description 看板：我的看板，计算本月招商总面积
     * @Auth luming
     * @Date 2018/8/13 15:09
     * @Version 1.0
     * @Param
     */
    int queryThisMonthAreaSum(Map<String, Object> map) throws Exception;

    /**
     * @return
     * @Description 看板：我的看板，本月新签合同
     * @Auth luming
     * @Date 2018/8/14 11:21
     * @Version 1.0
     * @Param
     */
    int queryThisMonthContract(Map<String, Object> map) throws Exception;

    /**
     * @return
     * @Description 毛利润统计园区出租面积和金额
     * @Auth liuzhengyang
     * @Date 2018/10/17 9:34
     * @Version 1.0
     * @Param
     */
    Map<String, BigDecimal> selectGross(Map<String, Object> map);

    /**
     * @return void
     * @Description 一键生成租金详情
     * @author wangchuang
     * @Date 2018/10/19 16:53
     * @Version 1.0
     * @Param [contractEntity]
     **/
    void initRentReceipt(ContractEntity contractEntity);

    /**
     * @return java.util.List<com.kfwy.park.ati.contract.entity.ContractEntity>
     * @Description 查询某月存在的合同
     * @author wangchuang
     * @Date 2018/11/1 11:48
     * @Version 1.0
     * @Param [map]
     **/
    List<ContractEntity> selectConByMonth(Map<String, Object> map);

    /**
     * @return
     * @Description 合同审批状态
     * @Auth luming
     * @Date 2018/12/11 9:04
     * @Version 1.0
     * @Param
     */
    void contractVerify(ContractEntity contractEntity);

    /**
     * @return void
     * @Description 退租、修改删除合同还原面积
     * @author luming
     * @Date 2019/3/26 9:37
     * @Version 1.0
     * @Param [contractEntity]
     */
    void recoverContract(ContractEntity contractEntity, String flag);

    /**
     * 应收情况统计
     *
     * @param map
     * @return
     * @Auth gengtao
     * @Date 2019/3/15 10:18
     */
    Map<String, Object> receivableChart(Map<String, Object> map);


    /**
     * @Author gengtao
     * @Date 2019-04-09 09:10:41
     * @Description 根据管理员编号，获取合同信息
     * @Return
     **/
    PageResult<ContractEntity> selectInfoByEmpCode(Map<String, Object> map, int start, int pageSize);

    /**
     * @Author gengtao
     * @Date 2019-04-09 09:10:41
     * @Description 根据管理员编号，获取合同数量信息
     * @Return
     **/
    int selectInfoByEmpCodeCount(Map<String, Object> map);

    /**
     * @return com.kfwy.park.pro.house.building.entity.ParkEntity
     * @Description 查询园区详情并关联合同和合同房源信息
     * @author luming
     * @Date 2019/5/16 9:51
     * @Version 1.0
     * @Param [map]
     */
    ParkEntity parkDetails(Map<String, Object> map) throws Exception;

    /**
     * @return com.kfwy.park.pro.house.building.entity.BuildingEntity
     * @Description 查询楼栋详情并关联合同和合同房源信息
     * @author luming
     * @Date 2019/5/16 16:55
     * @Version 1.0
     * @Param [map]
     */
    BuildingEntity buildingDetails(Map<String, Object> map) throws Exception;

    /**
     *@Author gengtao
     *@Date 2019-06-03 10:45:05
     *@Description 根据年份和月份查询数据
     *@Return
     **/
    PageResult<ContractEntity> selectByMMAndYY(Map<String,Object> map,int start, int pageSize);

    int selectByMMAndYYCount(Map<String, Object> map);

    /**
     *@Author gengtao
     *@Date 2019-06-14 11:31:31
     *@Description 获取客户
     *@Return
     **/
    List<ContractEntity> findCus(Map<String, Object> map);

    /**
     *@author gtaotao
     *@Date 2019-07-31 15:23:59
     *@Description 获取三个月到期合同编号
     *@param
     *@return
     **/
    List<String> selectThreeMonthExpiredCtCode(Map<String,Object>map);

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
     *@Description 获取6个月营收信息,查询合同结束日期在本月的合同
     *@param
     *@return
     **/
    List<ContractEntity> selectByTime(Map<String, Object> map);

    /**
     * @Description 合同关联水电表，此接口用于物业中的水电抄表展示
     * @Auth luming
     * @Date 2019/8/20 16:22
     * @Version 1.0
     * @Param
     * @return
     */
    PageResult<ContractEntity> queryMeter(Map<String, Object> map, int start, int pageSize);
}
