package com.kfwy.park.bi.report.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.bi.report.business.IMerchantsBiManager;
import com.kfwy.park.bi.report.dao.IMerchantsBiDbDao;
import com.kfwy.park.bi.report.entity.MerchantsBiEntity;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.hrm.org.employee.business.IEmployeeManager;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;
import com.kfwy.park.pro.house.building.business.IParkButtJointManager;
import com.kfwy.park.pro.house.building.entity.ParkButtJointEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;

/**
 * @Description 招商人员任务、去化数据
 * @Auth luming
 * @Date 2018/12/3 15:39
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class MerchantsBiManagerImpl extends AbstractManager<MerchantsBiEntity> implements IMerchantsBiManager {

    @Autowired
    private IMerchantsBiDbDao merchantsBiDbDao;
    
    @Autowired
    private IEmployeeManager employeeManager;

    @Autowired
    private IParkButtJointManager parkButtJointManager;
    
    @Override
    protected IMyBatisBaseDao<MerchantsBiEntity, Long> getMyBatisDao() {
        return this.merchantsBiDbDao;
    }


    /**
     * @Description 查询招商人员去化数据、日常任务
     * @Auth luming
     * @Date 2018/12/3 18:51
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<MerchantsBiEntity> selectMerchants(MerchantsBiEntity merchantsBiEntity, Map<String, Object> params) throws ParseException {
        // 根据部门编号查询该部门下的所有人员
        List<EmployeeEntity> empList = employeeManager.findByMap(params);

        List<EmployeeEntity> list = null;


        // 查询园区对接表，获取该园区的招商人员
        List<EmployeeEntity> unionList = new ArrayList<>();


        if (!StringUtils.isEmpty(merchantsBiEntity.getPkCode())) {
            Map<String, Object> map = new HashMap<>();
            map.put("pkCode", merchantsBiEntity.getPkCode());
            map.put("isDeleted", false);
            List<ParkButtJointEntity> parkButtJointEntityList = parkButtJointManager.findByMap(map);


            // 如果筛选框有pkCode，则取并集
            if (parkButtJointEntityList.size() != 0) {
                for (EmployeeEntity emp : empList) {
                    for (ParkButtJointEntity pbj : parkButtJointEntityList) {
                        if (pbj.getEmpCode().equals(emp.getEmpCode())) {
                            unionList.add(emp);
                        }
                    }
                }
            }

            list = unionList;
        } else {
            list = empList;
        }

        Date startDate = null;
        Date endDate = null;

        Date date = new Date();
        Date theMonthFirstDay = DateFormatUtil.monthBegin(date);// 本月第一天
        Date theMonthLastDay = DateFormatUtil.monthEnd(date);// 本月最后一天

        if (merchantsBiEntity.getStartDate() != null && merchantsBiEntity.getEndDate() != null) {
            startDate = DateFormatUtil.dayBegin(merchantsBiEntity.getStartDate());// 获取开始日期
            endDate = DateFormatUtil.dayEnd(merchantsBiEntity.getEndDate());// 获取结束日期
        } else {
            startDate = DateFormatUtil.dayBegin(theMonthFirstDay);
            endDate = DateFormatUtil.dayBegin(theMonthLastDay);
        }


        /**
         * 根据部门Code查询该部门下员工的相关数据
         */
        List<MerchantsBiEntity> merchantsBiEntityList = new ArrayList<>();

        for (EmployeeEntity empEntity : list) {
            String empCode = empEntity.getEmpCode();

            MerchantsBiEntity merchantsEntity = new MerchantsBiEntity();

            // 获取一个招商人员名字
            merchantsEntity.setEmpCode(empEntity.getEmpCode());
            merchantsEntity.setEmpName(empEntity.getEmpName());

            // 查询招商人员，本月日常任务（横幅、宣传单、朋友圈）
            Integer banner = merchantsBiDbDao.selectTaskBanner(empCode, startDate, endDate);
            Integer leaflets = merchantsBiDbDao.selectTaskLeaflets(empCode, startDate, endDate);
            Integer friendShare = merchantsBiDbDao.selectTaskFriendShare(empCode, startDate, endDate);

            merchantsEntity.setBanner(banner);
            merchantsEntity.setLeaflets(leaflets);
            merchantsEntity.setFriendsShare(friendShare);

            // 获取一个招商人员，谈判中的客户
            int cusNegotiateCount = merchantsBiDbDao.selectCusNegotiateCount(empCode, startDate, endDate);
            merchantsEntity.setCusNegotiateCount(cusNegotiateCount);

            // 获取一个招商人员，本月入驻客户
            int cusEntryCount = merchantsBiDbDao.selectCusEntryCount(empCode, startDate, endDate);
            merchantsEntity.setCusEntryCount(cusEntryCount);

            // 获取一个招商人员，本月去化面积
            BigDecimal signAreaMonth = merchantsBiDbDao.selectSignAreaMonth(empCode, startDate, endDate);
            merchantsEntity.setSignAreaMonth(signAreaMonth);

            // 查询主动获客数据
            Integer cusCount = merchantsBiDbDao.selectCusCount(empCode, startDate, endDate);
            merchantsEntity.setInitiative(cusCount);

            // 查询中介数据
            Integer agent = merchantsBiDbDao.selectAgent(empCode, startDate, endDate);
            merchantsEntity.setAgent(agent);

            // 查询库房无忧数据
            Integer kfwy = merchantsBiDbDao.selectKfwy(empCode, startDate, endDate);
            merchantsEntity.setKuFangWuYou(kfwy);

            // 查询本月获客数
            int custCount = merchantsBiDbDao.getCustCount(empCode, startDate, endDate);
            merchantsEntity.setCusCount(custCount);

            // 查询跟进数据
            Integer phoneFollowup = merchantsBiDbDao.selectPhoneFollowup(empCode, startDate, endDate);
            merchantsEntity.setCustomerPhoneFollowup(phoneFollowup);

            // 查询客户来访
            Integer visityFollowup = merchantsBiDbDao.selectVisityFollowup(empCode, startDate, endDate);
            merchantsEntity.setCustomerVisityFollowup(visityFollowup);

            merchantsBiEntityList.add(merchantsEntity);
        }

        return merchantsBiEntityList;
    }
}
