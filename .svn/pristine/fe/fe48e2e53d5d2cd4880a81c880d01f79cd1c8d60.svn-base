package com.kfwy.park.controller.report;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.customer.business.ICustomerFollowupManager;
import com.kfwy.park.bi.report.entity.ElectricityReceivableBiEntity;
import com.kfwy.park.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.park.common.easyexcel.export.FileExportor;
import com.kfwy.park.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.park.common.easyexcel.export.exception.FileExportException;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ExportFileUtil;
import com.kfwy.park.controller.report.vo.ParkFollowCountServiceRequest;
import com.kfwy.park.controller.report.vo.ParkFollowCountServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by kfwy_it_004 on 2019/3/14.
 */
@Component
@RequestMapping("parkFollowCount")
public class ParkFollowCountService extends SpringRestService{
    @Autowired
    private ICustomerFollowupManager customerFollowupManager;

    @RequestMapping(path = "/listCountInfo"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> listCountInfo(@RequestBody ParkFollowCountServiceRequest request){
        Map<String,Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        Date dayBegin = null;
        Date dayEnd = null;
        if(request.getStartDate() != null && request.getEndDate() != null){
            dayBegin = DateFormatUtil.dayBegin(request.getStartDate());
            dayEnd = DateFormatUtil.dayEnd(request.getEndDate());
        }

        List<Map<String,Object>> result = customerFollowupManager.selectPkByCusFolCount(map,dayBegin,dayEnd);
        ParkFollowCountServiceResponse response = new ParkFollowCountServiceResponse();
        response.setResult(result);
        return this.success(response);
    }

    @RequestMapping(path = "/exportParkFollowCountExcel",
            method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws FileExportException, IOException {
        String startDate = httpServletRequest.getParameter("startDate");
        String endDate = httpServletRequest.getParameter("endDate");

        Long sDate = new Long(startDate);
        Long eDate = new Long(endDate);
        Date startTime = new Date(sDate);
        Date endTime = new Date(eDate);

        Date dayBegin = DateFormatUtil.dayBegin(startTime);
        Date dayEnd = DateFormatUtil.dayEnd(endTime);

        String path = "/exportTemplate/" + "parkFollowCountReport" + ".xml";
        StringBuffer name = new StringBuffer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startDateStr = sdf.format(dayBegin);
        String endDateStr = sdf.format(dayEnd);

        name.append(startDateStr).append("-").append(endDateStr);
        //导出内容查询参数
        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<Map<String,Object>> data = customerFollowupManager.selectPkByCusFolCount(map,dayBegin,dayEnd);

        if(data.size() <= 0) {
            throw new BusinessException("无数据导出");
        }

        ExportFileUtil exportFileUtil = new ExportFileUtil();
        exportFileUtil.exportExcel(httpServletResponse, ParkFollowCountService.class, path, data, name.toString() + "多次带看统计报表.xlsx");
    }
}
