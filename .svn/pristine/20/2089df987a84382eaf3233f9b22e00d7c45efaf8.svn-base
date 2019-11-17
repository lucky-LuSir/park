package com.kfwy.park.task.attract.business.impl;

import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.kfwy.park.ati.customer.business.ICustomerFollowupManager;
import com.kfwy.park.ati.customer.business.ICustomerManager;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.hrm.org.employee.business.IEmployeeManager;
import com.kfwy.park.hrm.org.employee.entity.EmployeeEntity;
import com.kfwy.park.task.attract.business.ITodayTaskManager;
import com.kfwy.park.task.attract.dao.ITodayTaskDbDao;
import com.kfwy.park.task.attract.entity.TaskEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @version 1.0
 * @description 每日任务管理Manager
 * @auth xiongzhan
 * @date 2018/8/3 10:01
 */
@Service
public class TodayTaskManagerImpl extends AbstractManager<TaskEntity> implements ITodayTaskManager {

    @Autowired
    private ITodayTaskDbDao taskDbDao;
    @Autowired
    private ICustomerFollowupManager customerFollowupManager;
    @Autowired
    private ICustomerManager customerManager;
    @Autowired
    private IEmployeeManager employeeManager;

    @Override
    protected IMyBatisBaseDao getMyBatisDao() {
        return this.taskDbDao;
    }


    @Override
    public void insert(TaskEntity taskEntity) {
        //今天00点时间
        Date dayBegin = DateFormatUtil.dayBegin(new Date());
        //查询今天的任务是否提交
        Map<String, Object> param = new HashMap<String, Object>();
        param.put("empCode", taskEntity.getEmpCode());
        param.put("taskTime", dayBegin);
        TaskEntity beforeTask = taskDbDao.selectUniqueByMap(param);

        //不存在,则没提交,生成今日任务
        if (beforeTask == null) {
            taskEntity.setTaskCode(CodeGenUtils.generate());
            taskEntity.setTaskTime(dayBegin);
            OperateFillUtils.fill(taskEntity);
            taskDbDao.insert(taskEntity);
        }

        //存在,则已经提交,修改当前任务
        if (beforeTask != null) {
            taskEntity.setId(beforeTask.getId());
            OperateFillUtils.fill(taskEntity);
            taskDbDao.update(taskEntity);
        }
    }


    /**
     * @Description 招商人员任务看板
     * @Auth liuzhengyang
     * @Date 2018/10/18 16:35
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public List<TaskEntity> select(TaskEntity entity) throws IllegalAccessException {

        Date startDate = null;// 开始日期
        Date endDate = null;// 结束日期

        Date date = new Date();
        Date theMonthFirstDay = DateFormatUtil.monthBegin(date);// 当月第一天
        Date theMonthLastDay = DateFormatUtil.monthEnd(date);// 当月最后一天

        List<Date> taskTimeList = entity.getTaskTimeList();
        
        if (taskTimeList.isEmpty()) {
            startDate = DateFormatUtil.dayBegin(theMonthFirstDay);// 当月第一天的00:00:00
            endDate = DateFormatUtil.dayEnd(theMonthLastDay);// 当月最后一天的23:59:59
        } else {
            startDate = DateFormatUtil.dayBegin(entity.getTaskTimeList().get(0));
            endDate = DateFormatUtil.dayEnd(entity.getTaskTimeList().get(1));
        }

        Map<String, Object> params = new HashMap<>();
        params.put("isDeleted", false);
        params.put("deptCode", entity.getCreateDeptCode());
        params.put("cpyCode", CurrentContext.getCpyCode());

        List<EmployeeEntity> empList = employeeManager.findByMap(params);// 根据部门编号查询招商人员

        List<TaskEntity> taskList = new ArrayList<>();

        for (EmployeeEntity empEntity : empList) {
            String empCode = empEntity.getEmpCode();

            TaskEntity taskEntity = new TaskEntity();

            taskEntity.setEmpCode(empEntity.getEmpCode());
            taskEntity.setEmpName(empEntity.getEmpName());

            // 查询招商人员日常任务数据：横幅、宣传单、朋友圈
            TaskEntity taskDate = taskDbDao.selectTask(empCode, startDate, endDate);
            taskEntity.setBanner(0);
            taskEntity.setFriendsShare(0);
            taskEntity.setLeaflets(0);
            if (taskDate != null) {
                taskEntity.setBanner(taskDate.getBanner());
                taskEntity.setFriendsShare(taskDate.getFriendsShare());
                taskEntity.setLeaflets(taskDate.getLeaflets());
            }

            // 招商人员跟进数量
            Integer customerFollowup = customerFollowupManager.selectFollowup(empCode, startDate, endDate);
            taskEntity.setCustomerFollowup(customerFollowup);

            // 主动获客数
            Integer cusCount = customerManager.selectCusCount(empCode, startDate, endDate);
            taskEntity.setInitiative(cusCount);

            // 中介获客数
            Integer agent = customerManager.selectAgent(empCode, startDate, endDate);
            taskEntity.setAgent(agent);

            // 客房无忧获客数
            Integer kfwy = customerManager.selectKfwy(empCode, startDate, endDate);
            taskEntity.setKuFangWuYou(kfwy);

            taskList.add(taskEntity);
        }
        return taskList;
    }

    /**
     * @Description 查询招商人员，本月日常任务
     * @Auth luming
     * @Date 2018/12/4 8:48
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public TaskEntity selectTask(String empCode, Date startDate, Date endDate) {
        TaskEntity taskEntity = taskDbDao.selectTask(empCode, startDate, endDate);
        return taskEntity;
    }
}
