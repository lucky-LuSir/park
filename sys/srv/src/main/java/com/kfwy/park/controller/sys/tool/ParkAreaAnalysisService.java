package com.kfwy.park.controller.sys.tool;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.park.common.easyexcel.export.FileExportor;
import com.kfwy.park.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.park.common.easyexcel.export.exception.FileExportException;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ExportFileUtil;
import com.kfwy.park.controller.sys.tool.vo.ParkAreaAnalysisServiceRequest;
import com.kfwy.park.controller.sys.tool.vo.ParkAreaAnalysisServiceResponse;
import com.kfwy.park.pro.house.building.entity.ParkEntity;
import com.kfwy.park.sys.tool.business.IParkAreaAnalysisManager;
import com.kfwy.park.sys.tool.business.entity.ParkAreaAnalysisEntity;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
 * Created by gengtao on 2019/5/31.
 */
@Controller
@RequestMapping("/parkAreaAnalysis")
public class ParkAreaAnalysisService extends SpringRestService{
    @Autowired
    private IParkAreaAnalysisManager parkAreaAnalysisManager;

    @RequestMapping(path = "/list"
    ,method = RequestMethod.POST
    ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody ParkAreaAnalysisServiceRequest request) throws Exception {
        ParkAreaAnalysisServiceResponse response = new ParkAreaAnalysisServiceResponse();
        ParkEntity entity = request.getEntity();
        List<ParkAreaAnalysisEntity> list = parkAreaAnalysisManager.parkDatials(entity);
        response.setResult(list);
        return this.success(response);
    }

    /**
     * @Description 导出报表操作
     * @author wangchuang
     * @Date 2019/1/9 17:12
     * @Version 1.0
     * @Param [httpServletRequest, httpServletResponse]
     * @return void
     **/
    @RequestMapping(path = "/exportParkAreaAnalysisExcel",
            method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {

        Workbook workbook = null;
        ServletOutputStream out = httpServletResponse.getOutputStream();
        PrintWriter printWriter = null;
        String territoryType = httpServletRequest.getParameter("territoryType");
        String pkCode = httpServletRequest.getParameter("pkCode");


        String path = "/exportTemplate/" + "parkAreaAnalysis" + ".xml";
        ExportConfig exportConfig = ExportConfigFactory.getExportConfig(ParkAreaAnalysisService.class.getResourceAsStream(path));
        StringBuffer name = new StringBuffer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String DateStr = sdf.format(new Date());

        name.append(DateStr).append("-");
        //导出内容查询参数
        ParkEntity entity = new ParkEntity();
        if(StringUtils.isNotBlank(territoryType)){
            entity.setTerritoryType(territoryType);
            name.append(territoryType).append("-");
        }
        if(StringUtils.isNotBlank(pkCode)){
           entity.setPkCode(pkCode);
           name.append(pkCode).append("-");
        }

        List<ParkAreaAnalysisEntity> data = parkAreaAnalysisManager.parkDatials(entity);


        if(data.size() <= 0) {
            throw new BusinessException("无数据导出");
        }

        ExportFileUtil exportFileUtil = new ExportFileUtil();
        exportFileUtil.exportExcel(httpServletResponse, ParkAreaAnalysisService.class, path, data, name.toString() + "园区数据异常报表.xlsx");
    }
}
