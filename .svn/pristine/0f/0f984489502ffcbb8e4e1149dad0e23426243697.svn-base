package com.kfwy.park.controller.analysis;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.bi.analysis.business.INeedAreaAnalysisManager;
import com.kfwy.park.bi.analysis.entity.NeedAreaAnalysisEntity;
import com.kfwy.park.controller.analysis.vo.NeedAreaAnalysisResponse;
import com.kfwy.park.controller.hrm.vo.EmpBiServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Currency;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by luming on 2019/5/20.
 */
@Controller
@RequestMapping("/needAreaAnalysis")
public class NeedAreaAnalysisService extends SpringRestService {
    @Autowired
    private INeedAreaAnalysisManager needAreaAnalysisManager;


    @RequestMapping(path = "/selectNeedAreaAnalysis"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectNeedAreaAnalysis(@RequestBody EmpBiServiceRequest request) {
        NeedAreaAnalysisResponse response = new NeedAreaAnalysisResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("cpyCode", CurrentContext.getCpyCode());
        NeedAreaAnalysisEntity needAreaAnalysisEntity = needAreaAnalysisManager.selectNeedAreaAnalysis(map);
        response.setResult(needAreaAnalysisEntity);
        return this.success(response);
    }
}
