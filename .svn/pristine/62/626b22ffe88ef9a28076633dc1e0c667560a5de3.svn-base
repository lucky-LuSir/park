package com.kfwy.park.controller.report;

import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.bi.report.business.IFirstReceivableBiManager;
import com.kfwy.park.bi.report.entity.FirstReceivableBiEntity;
import com.kfwy.park.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.park.common.easyexcel.export.FileExportor;
import com.kfwy.park.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.park.common.easyexcel.export.exception.FileExportException;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ExportFileUtil;
import com.kfwy.park.controller.report.vo.FirstReceivableBiServiceRequest;
import com.kfwy.park.controller.report.vo.FirstReceivableBiServiceResponse;
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
import java.util.List;

/**
 * @Description 首笔款应收报表
 * @Auth luming
 * @Date 2019/1/8 19:23
 * @Version 1.0
 * @Param
 * @return
 */
@Component
@RequestMapping("/firstReceivable")
public class FirstReceivableBiService extends SpringRestService {

    @Autowired
    private IFirstReceivableBiManager firstReceivableBiManager;


    /**
     * @Description 查询
     * @Auth luming
     * @Date 2019/1/8 19:27
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/selectFirstReceivableInfo"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody FirstReceivableBiServiceRequest request) {

        FirstReceivableBiServiceResponse response = new FirstReceivableBiServiceResponse();
        FirstReceivableBiEntity firstReceivableBiEntity = request.getEntity();

        List<FirstReceivableBiEntity> list = null;

        if (firstReceivableBiEntity.getStartDate() != null && firstReceivableBiEntity.getEndDate() != null) {
            list = firstReceivableBiManager.selectFirstReceivableList(firstReceivableBiEntity);
        } else {
            throw new BusinessException("查询日期不能为空！");
        }

        response.setResult(list);
        return this.success(response);
    }

    /**
     * @Description 导出报表
     * @Auth luming
     * @Date 2019/1/10 19:55
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/exportFirstBiExcel",
                    method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws IOException, FileExportException {
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

        String path = "/exportTemplate/" + "firstReceivableBiReport" + ".xml";
        StringBuffer name = new StringBuffer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startDateStr = sdf.format(dayBegin);
        String endDateStr = sdf.format(dayEnd);

        name.append(startDateStr).append("-").append(endDateStr);

        //导出内容查询参数
        FirstReceivableBiEntity firstReceivableBiEntity = new FirstReceivableBiEntity();
        firstReceivableBiEntity.setStartDate(dayBegin);
        firstReceivableBiEntity.setEndDate(dayEnd);
        if(StringUtils.isNotBlank(contractorDeptCode)){
            firstReceivableBiEntity.setContractorDeptCode(contractorDeptCode);
        }
        if(StringUtils.isNotBlank(pkCode)){
            firstReceivableBiEntity.setPkCode(pkCode);
        }

        List<FirstReceivableBiEntity> data = firstReceivableBiManager.selectFirstReceivableList(firstReceivableBiEntity);

        if(data.size() <= 0) {
            throw new BusinessException("无数据导出");
        }

        ExportFileUtil exportFileUtil = new ExportFileUtil();
        exportFileUtil.exportExcel(httpServletResponse, FirstReceivableBiService.class, path, data, name.toString() + "首付款报表.xlsx");
    }
}
