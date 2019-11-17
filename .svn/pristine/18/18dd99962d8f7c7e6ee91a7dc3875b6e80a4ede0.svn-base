package com.kfwy.park.controller.report;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.bi.report.business.IMerchantsBiManager;
import com.kfwy.park.bi.report.entity.MerchantsBiEntity;
import com.kfwy.park.common.easyexcel.export.exception.FileExportException;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ExportFileUtil;
import com.kfwy.park.controller.report.vo.MerchantsBiServiceRequest;
import com.kfwy.park.controller.report.vo.MerchantsBiServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 招商人员任务、去化数据
 * @Auth luming
 * @Date 2018/12/3 9:36
 * @Version 1.0
 * @Param
 * @return
 */
@Component
@RequestMapping("/merchantsBi")
public class MerchantsBiService extends SpringRestService {

    @Autowired
    private IMerchantsBiManager merchantsBiManager;


    /**
     * @Description 查询
     * @Auth luming
     * @Date 2018/12/3 16:08
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/selectMerchants"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody MerchantsBiServiceRequest request) throws IllegalAccessException, ParseException {

        MerchantsBiServiceResponse response = new MerchantsBiServiceResponse();
        MerchantsBiEntity merchantsBiEntity = request.getEntity();

        Map<String, Object> params = new HashMap<>();
        params.put("deptCode", merchantsBiEntity.getDeptCode());
        params.put("isDeleted", false);
        params.put("cpyCode", CurrentContext.getCpyCode());

        List<MerchantsBiEntity> result = merchantsBiManager.selectMerchants(merchantsBiEntity, params);

        response.setResult(result);
        return this.success(response);
    }


    /**
     * @Description 导出报表
     * @Auth luming
     * @Date 2019/1/10 17:11
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/exportMerchantsBiExcel",
            method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws FileExportException, IOException, ParseException {
        String startDate = httpServletRequest.getParameter("startDate");
        String endDate = httpServletRequest.getParameter("endDate");
        String deptCode = httpServletRequest.getParameter("deptCode");
        String pkCode = httpServletRequest.getParameter("pkCode");

        Long sDate = new Long(startDate);
        Long eDate = new Long(endDate);
        Date startTime = new Date(sDate);
        Date endTime = new Date(eDate);

        Date dayBegin = DateFormatUtil.dayBegin(startTime);
        Date dayEnd = DateFormatUtil.dayEnd(endTime);

        String path = "/exportTemplate/" + "merchantsBiReport" + ".xml";
        StringBuffer name = new StringBuffer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startDateStr = sdf.format(dayBegin);
        String endDateStr = sdf.format(dayEnd);

        name.append(startDateStr).append("-").append(endDateStr);

        //导出内容查询参数
        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("deptCode", deptCode);
        map.put("cpyCode", CurrentContext.getCpyCode());

        MerchantsBiEntity merchantsBiEntity = new MerchantsBiEntity();
        merchantsBiEntity.setStartDate(dayBegin);
        merchantsBiEntity.setEndDate(dayEnd);
        merchantsBiEntity.setPkCode(pkCode);
        merchantsBiEntity.setDeptCode(deptCode);

        List<MerchantsBiEntity> data = merchantsBiManager.selectMerchants(merchantsBiEntity, map);

        if(data.size() <= 0) {
            throw new BusinessException("无数据导出");
        }

        ExportFileUtil exportFileUtil = new ExportFileUtil();
        exportFileUtil.exportExcel(httpServletResponse, MerchantsBiService.class, path, data, name.toString() + "招商数据报表.xlsx");
    }

}
