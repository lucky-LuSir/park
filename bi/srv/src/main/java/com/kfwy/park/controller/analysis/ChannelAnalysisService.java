package com.kfwy.park.controller.analysis;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.bi.analysis.business.IChannelAnalysisManager;
import com.kfwy.park.bi.analysis.entity.ChannelAnalysisEntity;
import com.kfwy.park.controller.analysis.vo.NeedAreaAnalysisResponse;
import com.kfwy.park.controller.hrm.vo.EmpBiServiceRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by luming on 2019/5/20.
 */
@Controller
@RequestMapping("/channelAnalysis")
public class ChannelAnalysisService extends SpringRestService {
    @Autowired
    private IChannelAnalysisManager channelAnalysisManager;


    /**
     * @Description 查询获客渠道分析数据
     * @author luming
     * @Date 2019/5/20 19:32
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     */
    @RequestMapping(path = "/selectChannelAnalysis"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectChannelAnalysis(@RequestBody EmpBiServiceRequest request) {
        NeedAreaAnalysisResponse response = new NeedAreaAnalysisResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("cpyCode", CurrentContext.getCpyCode());
        ChannelAnalysisEntity channelAnalysisEntity = channelAnalysisManager.selectChannelAnalysis(map);

        response.setResult(channelAnalysisEntity);
        return this.success(response);
    }
}
