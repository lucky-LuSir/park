package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IRentReceiptPayManager;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import com.kfwy.park.controller.ati.contract.vo.RentReceiptPayServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.RentReceiptPayServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.UUID;

/**
 * Created by gengtao on 2019/4/12.
 */
@RequestMapping("rentReceiptPay")
@Controller
public class RentReceiptPayService extends SpringRestService {
    @Autowired
    private IRentReceiptPayManager rentReceiptPayManager;

    @RequestMapping(path = "/create",
            method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody RentReceiptPayServiceRequest request) {

        RentReceiptPayServiceResponse response = new RentReceiptPayServiceResponse();
        RentReceiptPayEntity entity = request.getEntity();
        entity.setPayCode(CodeGenUtils.generate());

        rentReceiptPayManager.create(entity);

        return this.success(response);
    }
}
