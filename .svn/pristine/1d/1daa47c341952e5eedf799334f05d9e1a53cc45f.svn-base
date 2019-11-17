package com.kfwy.park.controller.ati.contract;

import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.ati.contract.business.IPayManager;
import com.kfwy.park.ati.contract.entity.RentReceiptPayEntity;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.controller.ati.contract.vo.PayServiceRequest;
import com.kfwy.park.controller.ati.contract.vo.PayServiceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 租金、电费、水费、停车费，支付表
 * @Auth luming
 * @Date 2018/9/19 18:44
 * @Version 1.0
 * @Param
 * @return
 */
@RestController
@RequestMapping(path = "/pay")
public class PayService extends SpringRestService {

    @Autowired
    private IPayManager payManager;

    /**
     * @Description 修改缴费金额
     * @author wangchuang
     * @Date 2019/1/15 13:56
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody PayServiceRequest request) throws IllegalAccessException {

        PayServiceResponse response = new PayServiceResponse();
        RentReceiptPayEntity entity = request.getEntity();

        if (StringUtils.isEmpty(entity.getId()) && StringUtils.isEmpty(entity.getCtCode())) {
            return this.error(response, "更新失败，传入ID为空!");
        }

        //将必填字段放入容器中
        List<String> param = new ArrayList<>();
        param.add("payCode");
        param.add("code");
        param.add("costType");
        param.add("payDate");
        param.add("receivableRent");
        param.add("realRent");
        param.add("hasTax");
        if(entity.getHasTax()) {
            param.add("taxPoint");
        }
        if("1".equals(entity.getCostType())) {
            param.add("proHasTax");
            if(entity.getProHasTax()) {
                param.add("proTaxPoint");
            }
        }

        ParamUtil paramUtil = new ParamUtil();
        paramUtil.check(entity, param);

        payManager.update(entity);

        return this.success(response);
    }

    /**
     * @Description 删除一条缴费记录
     * @author wangchuang
     * @Date 2019/1/16 11:47
     * @Version 1.0
     * @Param [request]
     * @return org.springframework.http.ResponseEntity<com.gniuu.framework.service.IServiceResponse>
     **/
    @RequestMapping(path = "/delete"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> delete(@RequestBody PayServiceRequest request) throws IllegalAccessException {

        PayServiceResponse response = new PayServiceResponse();
        RentReceiptPayEntity entity = request.getEntity();

        if (StringUtils.isEmpty(entity.getId()) && StringUtils.isEmpty(entity.getCtCode())) {
            return this.error(response, "更新失败，传入ID为空!");
        }

        payManager.deleteById(entity.getId());

        return this.success(response);
    }

    /**
     * @Description 缴费详情页查询支付历史记录，按照租金、水、电、停车费、保险费查询
     * @Auth luming
     * @Date 2018/9/19 18:52
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody PayServiceRequest request) throws Exception {

        PayServiceResponse response = new PayServiceResponse();
        RentReceiptPayEntity payEntity = request.getEntity();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<RentReceiptPayEntity> paramUtil = new ParamUtil<RentReceiptPayEntity>();
        paramUtil.objectToMap(payEntity, map);

        List<RentReceiptPayEntity> payList = payManager.findByMap(map, "id", true);

        response.setResult(payList);

        return this.success(response);
    }

    /**
     * @Description 合同列表查询缴费记录，为综合数据（包含租金、水、电、停车费、保险费）
     * @Auth luming
     * @Date 2018/12/5 10:22
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/contractPayList"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> contractPayList(@RequestBody PayServiceRequest request) throws Exception {

        PayServiceResponse response = new PayServiceResponse();
        RentReceiptPayEntity payEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<RentReceiptPayEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(payEntity, map);

        List<RentReceiptPayEntity> payList = payManager.findByMap(map);

        response.setResult(payList);

        return this.success(response);
    }
}
