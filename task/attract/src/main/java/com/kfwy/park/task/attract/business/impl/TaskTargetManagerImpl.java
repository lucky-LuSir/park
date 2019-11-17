package com.kfwy.park.task.attract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.dao.ICustomerDbDao;
import com.kfwy.park.bi.report.dao.IMerchantsBiDbDao;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.pro.house.building.business.IParkButtJointManager;
import com.kfwy.park.pro.house.building.entity.ParkButtJointEntity;
import com.kfwy.park.task.attract.business.ITaskTargetManager;
import com.kfwy.park.task.attract.business.ITodayTaskManager;
import com.kfwy.park.task.attract.dao.ITaskTargetDbDao;
import com.kfwy.park.task.attract.entity.TaskEntity;
import com.kfwy.park.task.attract.entity.TaskTargetEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 招商任务指标
 * @Auth luming
 * @Date 2019/1/21 13:58
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class TaskTargetManagerImpl extends AbstractManager<TaskTargetEntity> implements ITaskTargetManager {

    @Autowired
    private ICustomerDbDao customerDbDao;

    @Autowired
    private ITaskTargetDbDao taskTargetDbDao;

    @Autowired
    private IParkButtJointManager parkButtJointManager;

    @Autowired
    private IMerchantsBiDbDao merchantsBiDbDao;

    @Autowired
    private ITodayTaskManager taskManager;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.taskTargetDbDao;
    }

    /**
     * @Description 物理删除指标
     * @Auth luming
     * @Date 2019/1/21 14:47
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int delete(Long id) {
        return taskTargetDbDao.deleteById(id);
    }

    /**
     * @Description 查询指标报表
     * @Auth luming
     * @Date 2019/1/21 15:23
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<TaskTargetEntity> selectTargetReport(TaskTargetEntity taskTargetEntity) {

        /**
         * 招商指标统计：
         * 现在有小广告、横幅、朋友圈、电话跟进、客户带看、去划面积、管委会/中介拜访、获客数几个维度统计
         * 但是需求不断更改，有时只要统计其中某些字段，所以这里计算还都保留，只是在前端注释掉不需要的字段
         * 导出的Excel文件注释掉不需要的字段即可
         */

        List<TaskTargetEntity> taskTargetList = new ArrayList<>();

        // 前端传入时间参数，获取起始时间的 00:00:00 和 23:59:59
        Date startDateParam = null;
        Date endDateParam = null;
        if (taskTargetEntity.getStartDate() != null && taskTargetEntity.getEndDate() != null) {
            startDateParam = DateFormatUtil.dayBegin(taskTargetEntity.getStartDate());
            endDateParam = DateFormatUtil.dayEnd(taskTargetEntity.getEndDate());
        }


        // 查询园区对接表
        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);
        params.put("cpyCode", CurrentContext.getCpyCode());

        if (!StringUtils.isEmpty(taskTargetEntity.getPkCode())) {
            params.put("pkCode", taskTargetEntity.getPkCode());
        }
        List<ParkButtJointEntity> parkButtJointList = parkButtJointManager.findByMap(params);


        for (ParkButtJointEntity parkButtJoinEntityList : parkButtJointList) {
            String empCode = parkButtJoinEntityList.getEmpCode();
            String pkCode = parkButtJoinEntityList.getPkCode();

            Map<String, Object> param = new HashMap<>();
            if (!StringUtils.isEmpty(pkCode)) {
                param.put("pkCode", pkCode);
            }
            if (!StringUtils.isEmpty(empCode)) {
                param.put("empCode", empCode);
            }
            if (taskTargetEntity.getStartDate() != null) {
                Date monthBeginDay = DateFormatUtil.monthBeginDay(taskTargetEntity.getStartDate());// 本月第一天的 00:00:00
                param.put("targetDate", monthBeginDay);
            }
            param.put("isDeleted", false);
            param.put("cpyCode",CurrentContext.getCpyCode());
            List<TaskTargetEntity> targetEntityList = taskTargetDbDao.selectByMap(param);


            for (TaskTargetEntity targetList : targetEntityList) {
                // 当天的00:00:00 到 23:59:59
                Date date = new Date();
                Date dayBegin = DateFormatUtil.dayBegin(date);
                Date dayEnd = DateFormatUtil.dayEnd(date);

                TaskTargetEntity targetEntity = new TaskTargetEntity();

                targetEntity.setEmpName(targetList.getEmpName());
                targetEntity.setPkName(targetList.getPkName());
                targetEntity.setLeafletsTarget(targetList.getLeafletsTarget());
                targetEntity.setBannerTarget(targetList.getBannerTarget());
                targetEntity.setFriendsShareTarget(targetList.getFriendsShareTarget());
                targetEntity.setOtherChannelTarget(targetList.getOtherChannelTarget());
                targetEntity.setCustomerCountTarget(targetList.getCustomerCountTarget());
                targetEntity.setCustomerPhoneFollowupTarget(targetList.getCustomerPhoneFollowupTarget());
                targetEntity.setCustomerVisityFollowupTarget(targetList.getCustomerVisityFollowupTarget());
                targetEntity.setSignAreaTarget(targetList.getSignAreaTarget());


                /*-------------------------------------- 查询招商人员，本月日常任务（横幅、宣传单、朋友圈） --------------------------------------*/
                TaskEntity taskEntity = taskManager.selectTask(targetList.getEmpCode(), startDateParam, endDateParam);

                targetEntity.setBannerSum(taskEntity.getBanner());
                targetEntity.setLeafletsSum(taskEntity.getLeaflets());
                targetEntity.setFriendsShareSum(taskEntity.getFriendsShare());

                // 当天的任务数
                TaskEntity taskEntityToday = taskManager.selectTask(empCode, dayBegin, dayEnd);

                targetEntity.setBannerToday(taskEntityToday.getBanner());
                targetEntity.setLeafletsToday(taskEntityToday.getLeaflets());
                targetEntity.setFriendsShareToday(taskEntityToday.getFriendsShare());

                /**
                 * 横幅、宣传单、朋友圈完成进度
                 */

                // 宣传单
                if (targetEntity.getLeafletsTarget() != null && targetEntity.getLeafletsTarget() != 0) {
                    Double leafletsFinishDouble = (targetEntity.getLeafletsSum().doubleValue()) * 100 / targetEntity.getLeafletsTarget().doubleValue();
                    Double leafletsFinish = new BigDecimal(leafletsFinishDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    targetEntity.setLeafletsFinish(leafletsFinish);
                } else {
                    targetEntity.setLeafletsFinish(0.0);
                }

                // 横幅
                if (targetEntity.getBannerTarget() != null && targetEntity.getBannerTarget() != 0) {
                    Double bannerFinishDouble = (targetEntity.getBannerSum().doubleValue()) * 100 / targetEntity.getBannerTarget().doubleValue();
                    Double bannerFinish = new BigDecimal(bannerFinishDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    targetEntity.setBannerFinish(bannerFinish);
                } else {
                    targetEntity.setBannerFinish(0.0);
                }

                // 朋友圈
                if (targetEntity.getFriendsShareTarget() != null && targetEntity.getFriendsShareTarget() != 0) {
                    Double FriendsShareFinishDouble = (targetEntity.getFriendsShareSum().doubleValue()) * 100 / targetEntity.getFriendsShareTarget().doubleValue();
                    Double FriendsShareFinish = new BigDecimal(FriendsShareFinishDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    targetEntity.setFriendsShareFinish(FriendsShareFinish);
                } else {
                    targetEntity.setFriendsShareFinish(0.0);
                }


                /*-------------------------------------- 查询获客数、电话跟进客户、带看数 --------------------------------------*/
                //获客总数
                Map<String, Object> cusParam = new HashMap<String, Object>();
                cusParam.put("createCode", empCode);
                cusParam.put("createTimeStart", startDateParam);
                cusParam.put("createTimeEnd", endDateParam);
                cusParam.put("isDeleted", false);

                Integer cusCount = customerDbDao.countByMap(cusParam);
                targetEntity.setCustomerCountSum(cusCount);

                // 查询电话跟进数据
                Integer phoneFollowup = merchantsBiDbDao.selectPhoneFollowup(empCode, startDateParam, endDateParam);
                targetEntity.setCustomerPhoneFollowupSum(phoneFollowup);
                // 查询客户来访
                Integer visityFollowup = merchantsBiDbDao.selectVisityFollowup(empCode, startDateParam, endDateParam);
                targetEntity.setCustomerVisityFollowupSum(visityFollowup);

                //查询当天的获客数
                cusParam.put("createTimeStart", dayBegin);
                cusParam.put("createTimeEnd", dayEnd);
                Integer cusCountToday = customerDbDao.countByMap(cusParam);
                targetEntity.setCustomerCountToday(cusCountToday);

                // 查询当天的跟进数据
                Integer phoneFollowuToday = merchantsBiDbDao.selectPhoneFollowup(empCode, dayBegin, dayEnd);
                targetEntity.setCustomerPhoneFollowupToday(phoneFollowuToday);
                // 当天的客户来访
                Integer visityFollowupToday = merchantsBiDbDao.selectVisityFollowup(empCode, dayBegin, dayEnd);
                targetEntity.setCustomerVisityFollowupToday(visityFollowupToday);

                //当天获客完成进度
                if(targetEntity.getCustomerCountTarget() != null && targetEntity.getCustomerCountTarget() != 0) {
                    Double cusCountDouble = (targetEntity.getCustomerCountSum().doubleValue()) * 100 / targetEntity.getCustomerCountTarget().doubleValue();
                    Double cusCountFinish = new BigDecimal(cusCountDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    targetEntity.setCustomerCountFinish(cusCountFinish);
                } else {
                    targetEntity.setCustomerCountFinish(0.0);
                }

                // 客户跟进、带看完成进度
                if (targetEntity.getCustomerPhoneFollowupTarget() != null && targetEntity.getCustomerPhoneFollowupTarget() !=0) {
                    Double customerPhoneFollowupDouble = (targetEntity.getCustomerPhoneFollowupSum().doubleValue()) * 100 / targetEntity.getCustomerPhoneFollowupTarget().doubleValue();
                    Double customerPhoneFollowup = new BigDecimal(customerPhoneFollowupDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    targetEntity.setCustomerPhoneFollowupFinish(customerPhoneFollowup);
                } else {
                    targetEntity.setCustomerPhoneFollowupFinish(0.0);
                }

                if (targetEntity.getCustomerVisityFollowupTarget() != null && targetEntity.getCustomerVisityFollowupTarget() != 0) {
                    Double customerVisityFollowupDouble = (targetEntity.getCustomerVisityFollowupSum().doubleValue()) * 100 / targetEntity.getCustomerVisityFollowupTarget().doubleValue();
                    Double customerVisityFollowup = new BigDecimal(customerVisityFollowupDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    targetEntity.setCustomerVisityFollowupFinish(customerVisityFollowup);
                } else {
                    targetEntity.setCustomerVisityFollowupFinish(0.0);
                }


                /*-------------------------------------- 去化面积查询 --------------------------------------*/
                // 获取一个招商人员，本月去化面积
                BigDecimal signAreaMonth = merchantsBiDbDao.selectSignAreaMonth(empCode, startDateParam, endDateParam);
                targetEntity.setSignAreaMonthSum(signAreaMonth);

                // 当天的去化面积
                BigDecimal signAreaMonthToday = merchantsBiDbDao.selectSignAreaMonth(empCode, dayBegin, dayEnd);
                targetEntity.setSignAreaToday(signAreaMonthToday);

                // 去化面积完成进度
                if (targetEntity.getSignAreaTarget() != null && targetEntity.getSignAreaTarget().intValue() != 0) {
                    BigDecimal signAreaMonthBig = targetEntity.getSignAreaMonthSum().multiply(new BigDecimal(100)).divide(targetEntity.getSignAreaTarget(), 2, BigDecimal.ROUND_HALF_UP);
                    targetEntity.setSignAreaFinish(signAreaMonthBig);
                } else {
                    targetEntity.setSignAreaFinish(new BigDecimal(0));
                }


                /*-------------------------------------- 查询管委会中介拜访 --------------------------------------*/
                Integer otherChannelFollowupSum = taskTargetDbDao.selectChannelFollowupCount(empCode, startDateParam, endDateParam);
                targetEntity.setOtherChannelSum(otherChannelFollowupSum);

                // 当天的管委会中介拜访
                Integer otherChannelFollowupTodaySum = taskTargetDbDao.selectChannelFollowupCount(empCode, dayBegin, dayEnd);
                targetEntity.setOtherChannelToday(otherChannelFollowupTodaySum);

                // 管委会拜访完成进度
                if (targetEntity.getOtherChannelTarget() != null && targetEntity.getOtherChannelTarget() != 0) {
                    Double otherChannelDouble = (targetEntity.getOtherChannelTarget().doubleValue()) * 100 / targetEntity.getOtherChannelTarget().doubleValue();
                    Double otherChannel = new BigDecimal(otherChannelDouble).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
                    targetEntity.setOtherChannelFinish(otherChannel);
                } else {
                    targetEntity.setOtherChannelFinish(0.0);
                }


                taskTargetList.add(targetEntity);
            }
        }

        return taskTargetList;
    }
}
