package com.kfwy.park.controller.report;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.bi.report.business.IAccountReceivableBiManager;
import com.kfwy.park.bi.report.entity.AccountReceivableBiEntity;
import com.kfwy.park.common.easyexcel.export.ExportConfigFactory;
import com.kfwy.park.common.easyexcel.export.FileExportor;
import com.kfwy.park.common.easyexcel.export.domain.common.ExportConfig;
import com.kfwy.park.common.easyexcel.export.exception.FileExportException;
import com.kfwy.park.common.utils.DateFormatUtil;
import com.kfwy.park.common.utils.ExportFileUtil;
import com.kfwy.park.controller.report.vo.AccountReceivableBiServiceRequest;
import com.kfwy.park.controller.report.vo.AccountReceivableBiServiceResponse;
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
import java.util.*;

/**
 * @Description 应收费用报表
 * @Auth luming
 * @Date 2018/12/14 11:16
 * @Version 1.0
 * @Param
 * @return
 */
@Component
@RequestMapping("/accountReceivable")
public class AccountReceivableBiService extends SpringRestService {

    @Autowired
    private IAccountReceivableBiManager accountReceivableBiManager;


    /**
     * @Description 查询应收账款报表
     * @Auth luming
     * @Date 2018/12/3 16:08
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/selectRentInfo"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody AccountReceivableBiServiceRequest request) {

        AccountReceivableBiServiceResponse response = new AccountReceivableBiServiceResponse();
        AccountReceivableBiEntity accountReceivableBiEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();

        if (accountReceivableBiEntity.getStartDate() != null && accountReceivableBiEntity.getEndDate() != null) {
            Date dayBegin = DateFormatUtil.dayBegin(accountReceivableBiEntity.getStartDate());
            Date dayEnd = DateFormatUtil.dayEnd(accountReceivableBiEntity.getEndDate());
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

        List<AccountReceivableBiEntity> accountReceivableList = accountReceivableBiManager.selectRentInfo(map);

     //   map.put("ctStatus","3"); //一审通过
    //    List<AccountReceivableBiEntity> accountReceivableList2 = accountReceivableBiManager.selectRentInfo(map);

    //    accountReceivableList.addAll(accountReceivableList2);

        response.setResult(accountReceivableList);
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
    @RequestMapping(path = "/exportAccountBiExcel",
            method = RequestMethod.GET)
    public void export(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws FileExportException, IOException {
        String startDate = httpServletRequest.getParameter("startDate");
        String endDate = httpServletRequest.getParameter("endDate");
        String contractorDeptCode = httpServletRequest.getParameter("contractorDeptCode");
        String pkCode = httpServletRequest.getParameter("pkCode");

        if(startDate == null || endDate==null){
            throw new BusinessException("请选择日期！");
        }

        Long sDate = new Long(startDate);
        Long eDate = new Long(endDate);
        Date startTime = new Date(sDate);
        Date endTime = new Date(eDate);

        Date dayBegin = DateFormatUtil.dayBegin(startTime);
        Date dayEnd = DateFormatUtil.dayEnd(endTime);

        String path = "/exportTemplate/" + "accountBiReport" + ".xml";
        StringBuffer name = new StringBuffer();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String startDateStr = sdf.format(dayBegin);
        String endDateStr = sdf.format(dayEnd);

        name.append(startDateStr).append("-").append(endDateStr);
        //导出内容查询参数
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", dayBegin);
        map.put("endDate", dayEnd);
        map.put("ctStatus", "4");
        map.put("isDeleted",false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        if(StringUtils.isNotBlank(contractorDeptCode)){
            map.put("contractorDeptCode",contractorDeptCode);
        }
        if(StringUtils.isNotBlank(pkCode)){
            map.put("pkCode",pkCode);
        }

        List<AccountReceivableBiEntity> data = accountReceivableBiManager.selectRentInfo(map);

//        map.put("ctStatus", "3");
//        List<AccountReceivableBiEntity> data2 = accountReceivableBiManager.selectRentInfo(map);
//        data.addAll(data2);


        if(data.size() <= 0) {
            throw new BusinessException("无数据导出");
        }

        ExportFileUtil exportFileUtil = new ExportFileUtil();
        exportFileUtil.exportExcel(httpServletResponse, AccountReceivableBiService.class, path, data, name.toString() + "的应收租金报表.xlsx");
    }

}
