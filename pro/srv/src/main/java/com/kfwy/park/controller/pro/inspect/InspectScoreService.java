package com.kfwy.park.controller.pro.inspect;

import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.controller.pro.inspect.vo.InspectScoreServiceRequest;
import com.kfwy.park.pro.inspect.business.IInspectScoreManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("inspectScore")
public class InspectScoreService extends SpringRestService {
    @Autowired
    private IInspectScoreManager inspectScoreManager;

    @RequestMapping(path = "treeList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse>treeList(@RequestBody InspectScoreServiceRequest request){
        return null;
    }
}
