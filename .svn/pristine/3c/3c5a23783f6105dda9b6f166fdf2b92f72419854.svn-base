package com.kfwy.park.controller.report;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.bi.report.business.PremiumReceivableBiManager;
import com.kfwy.park.bi.report.entity.AccountReceivableBiEntity;
import com.kfwy.park.bi.report.entity.PremiumReceivableBiEntity;
import com.kfwy.park.bi.report.entity.WaterReceivableBiEntity;
import com.kfwy.park.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.park.common.easyexcel.export.FileExportor;
import com.kfwy.park.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.park.common.easyexcel.export.exception.FileExportException;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ExportFileUtil;
import com.kfwy.park.controller.report.vo.AccountReceivableBiServiceRequest;
import com.kfwy.park.controller.report.vo.AccountReceivableBiServiceResponse;
import com.kfwy.park.controller.report.vo.PremiumReceivableBiServiceRequest;
import com.kfwy.park.controller.report.vo.PremiumReceivableBiServiceResponse;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
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
 * Created by gengtao on 2019/4/17.
 */
@Component
@RequestMapping("/premiumReceivable")
public class PremiumReceivableBiService extends SpringRestService {
    @Autowired
    private PremiumReceivableBiManager premiumReceivableBiManager;

    @RequestMapping(path = "/selectRentInfo"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody PremiumReceivableBiServiceRequest request) {

        PremiumReceivableBiServiceResponse response = new PremiumReceivableBiServiceResponse();
        Map<String, Object> map = new HashMap<>();

        if (request.getStartDate() != null && request.getEndDate() != null) {
            Date dayBegin = DateFormatUtil.dayBegin(request.getStartDate());
            Date dayEnd = DateFormatUtil.dayEnd(request.getEndDate());
            map.put("startDate", dayBegin);
            map.put("endDate", dayEnd);
        } else {
            throw new BusinessException("请选择日期！");
        }
        if(!StringUtils.isEmpty(request.getContractorDeptCode())){
            map.put("contractorDeptCode",request.getContractorDeptCode());
        }
        if(!StringUtils.isEmpty(request.getPkCode())){
            map.put("pkCode",request.getPkCode());
        }
        map.put("ctStatus", "4");
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        List<PremiumReceivableBiEntity> list = premiumReceivableBiManager.selectReceivable(map);

        response.setResult(list);
        return this.success(response);
    }

    /**
     * @Description 水费应收导出
     * @author wangchuang
     * @Date 2019/1/10 18:56
     * @Version 1.0
     * @Param [httpServletRequest, httpServletResponse]
     * @return void
     **/
    @RequestMapping(path = "/exportPremiumReceivableBiExcel",
            method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws FileExportException, IOException {
        String startDate = httpServletRequest.getParameter("startDate");
        String endDate = httpServletRequest.getParameter("endDate");
        String contractorDeptCode = httpServletRequest.getParameter("contractorDeptCode");
        String pkCode = httpServletRequest.getParameter("pkCode");

        Long sDate = new Long(startDate);
        Long eDate = new Long(endDate);
        Date startTime = new Date(sDate);
        Date endTime = new Date(eDate);

        Date dayBegin = DateFormatUtil.dayBegin(startTime);
        Date dayEnd = DateFormatUtil.dayEnd(endTime);

        String path = "/exportTemplate/" + "premiumReceivableReport" + ".xml";
        StringBuffer name = new StringBuffer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startDateStr = sdf.format(dayBegin);
        String endDateStr = sdf.format(dayEnd);

        name.append(startDateStr).append("-").append(endDateStr);
        //导出内容查询参数
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", dayBegin);
        map.put("endDate", dayEnd);
        map.put("isDeleted",false);
        map.put("ctStatus", "4");
        map.put("cpyCode", CurrentContext.getCpyCode());
        if(StringUtils.isNotBlank(contractorDeptCode)){
            map.put("contractorDeptCode",contractorDeptCode);
        }
        if(StringUtils.isNotBlank(pkCode)){
            map.put("pkCode",pkCode);
        }

        List<PremiumReceivableBiEntity> data = premiumReceivableBiManager.selectReceivable(map);
        if(data.size() <= 0) {
            throw new BusinessException("无数据导出");
        }

        ExportFileUtil exportFileUtil = new ExportFileUtil();
        exportFileUtil.exportExcel(httpServletResponse, PremiumReceivableBiService.class, path, data, name.toString() + "的应收保险费报表.xlsx");
    }
}
