package com.kfwy.park.controller.ati;


import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.park.common.easyexcel.export.FileExportor;
import com.kfwy.park.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.park.common.easyexcel.export.exception.FileExportException;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.vo.TaskServiceResponse;
import com.kfwy.park.controller.ati.vo.TaskTargetServiceRequest;
import com.kfwy.park.controller.ati.vo.TaskTargetServiceResponse;
import com.kfwy.park.task.attract.business.ITaskTargetManager;
import com.kfwy.park.task.attract.entity.TaskTargetEntity;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 招商任务指标
 * @Auth luming
 * @Date 2019/1/21 14:00
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping(path = "/taskTarget")
public class TaskTargetService extends SpringRestService {

    @Autowired
    private ITaskTargetManager taskTargetManager;


    /**
     * @Description 新增
     * @Auth luming
     * @Date 2019/1/21 14:06
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody TaskTargetServiceRequest request) throws IllegalAccessException {

        TaskServiceResponse response = new TaskServiceResponse();
        TaskTargetEntity taskTargetEntity = request.getEntity();

        List<String> list = new ArrayList<>();
        list.add("pkCode");
        list.add("pkName");
        list.add("empCode");
        list.add("empName");
        //统计指标更改（注释不要删，以后可能还要统计这些维度） update by wangchuang 2019-6-4
//        list.add("leafletsTarget");
//        list.add("bannerTarget");
//        list.add("friendsShareTarget");
//        list.add("otherChannelTarget");
//        list.add("customerPhoneFollowupTarget");
        list.add("customerCountTarget");
        list.add("customerVisityFollowupTarget");
        list.add("signAreaTarget");
        list.add("targetDate");
        ParamUtil<TaskTargetEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(taskTargetEntity, list);

        taskTargetEntity.setTargetCode(CodeGenUtils.generate());

        Date targetDate = taskTargetEntity.getTargetDate();
        Date theMonthBegin = DateFormatUtil.monthBeginDay(targetDate);// 本月第一天的0时0分0秒
        taskTargetEntity.setTargetDate(theMonthBegin);

        taskTargetManager.create(taskTargetEntity);

        response.setResult(taskTargetEntity.getTargetCode());
        return this.success(response);
    }

    /**
     * @Description 非物理删除
     * @Auth luming
     * @Date 2019/1/21 14:07
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody TaskTargetServiceRequest request) {

        TaskTargetServiceResponse response = new TaskTargetServiceResponse();
        TaskTargetEntity taskTargetEntity = request.getEntity();

        if (StringUtils.isEmpty(taskTargetEntity.getId())) {
            return this.error(response, "删除失败，传入信息有误!");
        }

        taskTargetManager.delete(taskTargetEntity.getId());

        return this.success(response);
    }

    /**
     * @Description 修改信息
     * @Auth luming
     * @Date 2019/1/21 14:08
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody TaskTargetServiceRequest request) throws IllegalAccessException {

        TaskTargetServiceResponse response = new TaskTargetServiceResponse();
        TaskTargetEntity taskTargetEntity = request.getEntity();

        List<String> list = new ArrayList<>();
        list.add("targetCode");
        list.add("pkCode");
        list.add("pkName");
        list.add("empCode");
        list.add("empName");
        //统计指标更改（注释不要删，以后可能还要统计这些维度） update by wangchuang 2019-6-4
//        list.add("leafletsTarget");
//        list.add("bannerTarget");
//        list.add("friendsShareTarget");
//        list.add("otherChannelTarget");
//        list.add("customerPhoneFollowupTarget");
        list.add("customerCountTarget");
        list.add("customerVisityFollowupTarget");
        list.add("signAreaTarget");
        list.add("targetDate");
        ParamUtil<TaskTargetEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(taskTargetEntity, list);

        Date targetDate = taskTargetEntity.getTargetDate();
        Date theMonthBegin = DateFormatUtil.monthBegin(targetDate);// 本月第一天
        Date dayBegin = DateFormatUtil.dayBegin(theMonthBegin);// 本月第一天的0时0分0秒
        taskTargetEntity.setTargetDate(dayBegin);

        taskTargetManager.update(taskTargetEntity);

        return this.success(response);
    }

    /**
     * @Description 查询招商指标集合
     * @Auth luming
     * @Date 2019/1/21 14:37
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody TaskTargetServiceRequest request) throws IllegalAccessException {

        TaskTargetServiceResponse response = new TaskTargetServiceResponse();
        TaskTargetEntity taskTargetEntity = request.getEntity();

        Map<String, Object> param = new HashMap<>();
        param.put("isDeleted", false);
        param.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<TaskTargetEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(taskTargetEntity, param);

        Date targetDate = taskTargetEntity.getTargetDate();

        if (targetDate != null) {
            Date theMonthBegin = DateFormatUtil.monthBegin(targetDate);// 本月第一天
            Date dayBegin = DateFormatUtil.dayBegin(theMonthBegin);// 本月第一天的0时0分0秒

            param.put("targetDate", dayBegin);
        }

        List<TaskTargetEntity> result = taskTargetManager.findByMap(param, "id", true);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询指标数据
     * @Auth luming
     * @Date 2019/1/21 15:16
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/targetReport"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> targetReport(@RequestBody TaskTargetServiceRequest request) throws IllegalAccessException {

        TaskTargetServiceResponse response = new TaskTargetServiceResponse();
        TaskTargetEntity taskTargetEntity = request.getEntity();

        List<TaskTargetEntity> taskTargetEntityList = taskTargetManager.selectTargetReport(taskTargetEntity);

        response.setResult(taskTargetEntityList);
        return this.success(response);
    }

    /**
     * @Description 数据导出
     * @Auth luming
     * @Date 2019/1/22 16:53
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/exportTargetExcel",
            method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws FileExportException, IOException {

        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;
        String startDate = httpServletRequest.getParameter("startDate");
        String endDate = httpServletRequest.getParameter("endDate");
        String pkCode = httpServletRequest.getParameter("pkCode");

        Long sDate = new Long(startDate);
        Long eDate = new Long(endDate);
        Date startTime = new Date(sDate);
        Date endTime = new Date(eDate);

        Date dayBegin = DateFormatUtil.dayBegin(startTime);
        Date dayEnd = DateFormatUtil.dayEnd(endTime);

        String path = "/exportTemplate/" + "taskTargetReport" + ".xml";
        ExportConfig exportConfig = ExportConfigFactory.getExportConfig(TaskTargetService.class.getResourceAsStream(path));
        StringBuffer name = new StringBuffer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startDateStr = sdf.format(dayBegin);
        String endDateStr = sdf.format(dayEnd);

        name.append(startDateStr).append("-").append(endDateStr);

        // 导出内容查询参数
        TaskTargetEntity taskTargetEntity = new TaskTargetEntity();
        taskTargetEntity.setStartDate(dayBegin);
        taskTargetEntity.setEndDate(dayEnd);
        taskTargetEntity.setPkCode(pkCode);

        List<TaskTargetEntity> data = taskTargetManager.selectTargetReport(taskTargetEntity);

        if(data.size() <= 0) {
            throw new BusinessException("无数据导出");
        }

        try {
            workbook =  FileExportor.getExportWorkbook(exportConfig, data);
            String excelName = name.toString() + "指标数据报表.xlsx";
            String filename = new String(excelName.getBytes("UTF-8"), "ISO8859-1");

            httpServletResponse.setHeader("Content-Type", "application/vnd.ms-excel");
            httpServletResponse.setHeader("Content-Disposition", "attachment;filename=" + filename);
            httpServletResponse.setContentType("application/vnd.ms-excel");
            httpServletResponse.setCharacterEncoding("UTF-8");
            out = httpServletResponse.getOutputStream();
            if (workbook != null) {
                workbook.write(out);
            } else {
                workbook.write(out);
            }
            out.flush();
        }catch (Exception e){

        }finally {
            if (printWriter != null) {
                try {
                    printWriter.close();
                } catch (Exception e) {
                    printWriter = null;
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (Exception e) {
                    out = null;
                }
            }

            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    workbook = null;
                }
            }
        }
    }
}
