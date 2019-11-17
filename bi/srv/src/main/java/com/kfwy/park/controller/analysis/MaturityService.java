package com.kfwy.park.controller.analysis;

import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.bi.analysis.business.IMaturityManager;
import com.kfwy.park.bi.analysis.entity.MaturityEntity;
import com.kfwy.park.controller.analysis.vo.MaturityServiceRequest;
import com.kfwy.park.controller.analysis.vo.MaturityServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangchuang
 * @Description TODO
 * @Date $date$ $time$
 * @Version 1.0
 * @Param $param$
 * @return $return$
 **/
@RestController
@RequestMapping("/maturity")
public class MaturityService extends SpringRestService {

    @Autowired
    private IMaturityManager maturityManager;


    @RequestMapping(path = "/selectMaturityCon"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> selectMaturityCon(@RequestBody MaturityServiceRequest request) {

        MaturityServiceResponse response = new MaturityServiceResponse();

        MaturityEntity entity = request.getEntity();

        if(entity == null || entity.getMaturityYear() == null) {
            throw new BusinessException("传入开始年份为空");
        }
        List<MaturityEntity> maturityEntityList = maturityManager.selectMaturityInfo(entity.getMaturityYear());

        response.setResult(maturityEntityList);

        return this.success(response);

    }

}
