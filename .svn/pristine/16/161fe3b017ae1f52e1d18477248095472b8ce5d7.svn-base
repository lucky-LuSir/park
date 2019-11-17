package com.kfwy.park.controller.sys.pay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.alipay.AlipayConfig;
import com.kfwy.park.controller.sys.pay.vo.AliPayReFundServiceRequest;
import com.kfwy.park.controller.sys.pay.vo.AliPayRefundServiceResponse;
import com.kfwy.park.controller.sys.pay.vo.AliPayServiceRequest;
import com.kfwy.park.controller.sys.pay.vo.AliPayServiceResponse;
import com.kfwy.park.sys.pay.entity.AliPayRefundEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author gengtao
 * @Date 2019/7/25 9:08
 **/
@Controller
@RequestMapping("alipay")
public class AliPayService extends SpringRestService {
    /**
     *@author gtaotao
     *@Date 2019-07-25 14:39:01
     *@Description 支付
     *@param
     *@return 返回支付宝支付页面
     **/
    @RequestMapping(value = "pay"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<IServiceResponse> pay(@RequestBody AliPayServiceRequest request) throws AlipayApiException {
        AliPayServiceResponse response = new AliPayServiceResponse();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        //设置请求参数
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(AlipayConfig.return_url);
        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);

        //商户订单号，商户网站订单系统中唯一订单号，必填
        String out_trade_no = request.getEntity().getWidOutTradeNo();
        //付款金额，必填
        String total_amount = request.getEntity().getWidTotalAmount();
        //订单名称，必填
        String subject = request.getEntity().getWidSubject();
        //商品描述，可空
        String body = request.getEntity().getWidBody();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
                + "\"total_amount\":\""+ total_amount +"\","
                + "\"subject\":\""+ subject +"\","
                + "\"body\":\""+ body +"\","
                + "\"timeout_express\":\"5m\","
                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");

        //请求
        AlipayTradePagePayResponse pagePayResponse = alipayClient.pageExecute(alipayRequest);
        if (pagePayResponse.isSuccess()){
            String result = pagePayResponse.getBody();
            response.setResult(result);
            return this.success(response);
        }else{
            return this.error(response, "请求失败!");
        }

        /*System.out.println(result);
        response.resetBuffer();
        response.setContentType("text/html;charset=UTF-8");
        response.getOutputStream().write(result.toString().getBytes("utf-8"));
        response.getOutputStream().flush();*/
    }
    private  static  final Logger logger = LoggerFactory.getLogger(AliPayService.class);

    /**
     *@author gtaotao
     *@Date 2019-07-25 14:38:40
     *@Description 支付回调方法
     *@param
     *@return
     **/
    @RequestMapping(path = "notifyUrl", method = RequestMethod.POST)
    public void notifyUrl(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {

        //获取支付宝POST过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
           // valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
            params.put(name, valueStr);
        }

        //调用SDK验证签名
        boolean signVerified = false;
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //——请在这里编写您的程序（以下代码仅作参考）——

	/* 实际验证过程建议商户务必添加以下校验：
	1、需要验证该通知数据中的out_trade_no是否为商户系统中创建的订单号，
	2、判断total_amount是否确实为该订单的实际金额（即商户订单创建时的金额），
	3、校验通知中的seller_id（或者seller_email) 是否为out_trade_no这笔单据的对应的操作方（有的时候，一个商户可能有多个seller_id/seller_email）
	4、验证app_id是否为该商户本身。
	*/
        if(signVerified) {//验证成功
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //交易状态
            String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

            if(trade_status.equals("TRADE_FINISHED")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
            }else if (trade_status.equals("TRADE_SUCCESS")){
                //判断该笔订单是否在商户网站中已经做过处理
                //如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
                //如果有做过处理，不执行商户的业务程序

                //注意：
                //付款完成后，支付宝系统发送该交易状态通知
            }

            System.out.println("success");

        }else {//验证失败
            System.out.println("fail");

            //调试用，写文本函数记录程序运行情况是否正常
            //String sWord = AlipaySignature.getSignCheckContentV1(params);
            //AlipayConfig.logResult(sWord);
        }

        //——请在这里编写您的程序（以上代码仅作参考）——

    }

    /**
     *@author gtaotao
     *@Date 2019-07-25 14:38:07
     *@Description 支付后页面返回地址
     *@param
     *@return
     **/
    @RequestMapping(path = "returnUrl", method = RequestMethod.GET)
    public void returnUrl(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        //获取支付宝GET过来反馈信息
        Map<String,String> params = new HashMap<String,String>();
        Map<String,String[]> requestParams = request.getParameterMap();
        for (Iterator<String> iter = requestParams.keySet().iterator(); iter.hasNext();) {
            String name = (String) iter.next();
            String[] values = (String[]) requestParams.get(name);
            String valueStr = "";
            for (int i = 0; i < values.length; i++) {
                valueStr = (i == values.length - 1) ? valueStr + values[i]
                        : valueStr + values[i] + ",";
            }
            //乱码解决，这段代码在出现乱码时使用
            //valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");

            params.put(name, valueStr);
        }

        boolean signVerified = false; //调用SDK验证签名
        try {
            signVerified = AlipaySignature.rsaCheckV1(params, AlipayConfig.alipay_public_key, AlipayConfig.charset, AlipayConfig.sign_type);
        } catch (AlipayApiException e) {
            e.printStackTrace();
        }

        //——请在这里编写您的程序（以下代码仅作参考）——
        if(signVerified) {
            //商户订单号
            String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //支付宝交易号
            String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

            //付款金额
            String total_amount = new String(request.getParameter("total_amount").getBytes("ISO-8859-1"),"UTF-8");

            System.out.println("trade_no:"+trade_no+"<br/>out_trade_no:"+out_trade_no+"<br/>total_amount:"+total_amount);
        }else {
            System.out.println("验签失败");
        }
        //——请在这里编写您的程序（以上代码仅作参考）——
    }

    /**
     *@author gtaotao
     *@Date 2019-07-25 15:29:17
     *@Description 查询支付宝订单状态
     *@param
     *@return
     **/
    @RequestMapping(value = "query"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<IServiceResponse> query(@RequestBody AliPayServiceRequest request) throws AlipayApiException {
        AliPayServiceResponse response = new AliPayServiceResponse();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradeQueryRequest alipayRequest = new AlipayTradeQueryRequest();
        String out_trade_no = request.getEntity().getWidOutTradeNo();
        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\"}");
        AlipayTradeQueryResponse queryResponse = alipayClient.execute(alipayRequest);
        if (queryResponse.isSuccess()){
            String result  = queryResponse.getBody();
            response.setResult(result);
            return this.success(response);
        }else {
            return this.error(response, "请求失败!");
        }
    }

    /**
     *@author gtaotao
     *@Date 2019-07-25 16:23:48
     *@Description 退款
     *@param
     *@return
     **/
    @RequestMapping(value = "refund"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<IServiceResponse> refund(@RequestBody AliPayReFundServiceRequest request) throws AlipayApiException {
        AliPayRefundServiceResponse response = new AliPayRefundServiceResponse();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
        AliPayRefundEntity refundEntity = request.getEntity();
        //商家单号
        String out_trade_no = refundEntity.getWidOutTradeNo();
        //支付宝单号,与商家单号同时存在时,优先使用支付宝单号
       // String trade_no = refundEntity.getTradeNo();
        //先查询该订单的付款金额,退款金额不得大于付款金额
        String refund_amount = refundEntity.getRefundAmount();
        //退款的原因说明
        String refund_reason = refundEntity.getRefundReason();
        //标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传,建议必填
        String out_request_no = refundEntity.getOutRequestNo();
        //操作员编号
        String operator_id = CurrentContext.getUserCode();
        //商户门店编号
        String store_id = CurrentContext.getCpyCode();

        alipayRequest.setBizContent("{\"out_trade_no\":\""+ out_trade_no +"\","
              //  + "\"trade_no\":\""+ trade_no +"\","
                + "\"refund_amount\":\""+ refund_amount +"\","
                + "\"refund_reason\":\""+ refund_reason +"\","
                + "\"operator_id\":\""+ operator_id +"\","
                + "\"store_id\":\""+ store_id +"\","
                + "\"out_request_no\":\""+ out_request_no +"\"}"
        );

        //请求
        AlipayTradeRefundResponse refundResponse = alipayClient.execute(alipayRequest);
        //退款总金额
        if (refundResponse.isSuccess()){
            String refundFee = refundResponse.getRefundFee();
            response.setResult(refundFee);
            return this.success(response);
        }else {
            return this.error(response, "请求失败!");
        }

    }

    /**
     *@author gtaotao
     *@Date 2019-07-25 16:24:02
     *@Description 下载支付宝账单
     *@param
     *@return
     **/
    @RequestMapping(value = "bill"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<IServiceResponse> bill(@RequestBody AliPayServiceRequest request) throws AlipayApiException {
        AliPayServiceResponse response = new AliPayServiceResponse();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);

        String bill_type = request.getBillType();
        String bill_date = request.getBillDate();
        AlipayDataDataserviceBillDownloadurlQueryRequest alipayRequest = new AlipayDataDataserviceBillDownloadurlQueryRequest();
        alipayRequest.setBizContent("{" +
                "\"bill_type\":\""+bill_type+"\"," +
                "\"bill_date\":\""+bill_date+"\"" +
                "}");
        AlipayDataDataserviceBillDownloadurlQueryResponse queryResponse = alipayClient.execute(alipayRequest);
        if (queryResponse.isSuccess()){
            //获取下载地址
            String urlStr = queryResponse.getBillDownloadUrl();
            response.setResult(urlStr);
            return this.success(response);
        }else {
            return this.error(response, "请求失败");
        }

    }

    /**
     *@author gtaotao
     *@Date 2019-07-26 09:19:24
     *@Description 关闭订单,只有创建的订单未被支付,才可以取消,通过page.pay方法生成的订单不算创建的订单
     *@param
     *@return
     **/
    @RequestMapping(value = "close"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<IServiceResponse> close(@RequestBody AliPayServiceRequest request) throws AlipayApiException {
        AliPayServiceResponse response = new AliPayServiceResponse();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradeCloseRequest alipayRequest = new AlipayTradeCloseRequest();
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = request.getEntity().getWidOutTradeNo();
        //操作员编号
        String operator_id = CurrentContext.getUserCode();
        //支付宝交易号 请二选一设置
        //String trade_no = request.getEntity().getTrade_no();

        alipayRequest.setBizContent("{" +
                "\"out_trade_no\":\""+ out_trade_no +"\"," +
                "\"operator_id\":\""+ operator_id +"\"}");

        //请求
        AlipayTradeCloseResponse closeResponse = alipayClient.execute(alipayRequest);
        if(closeResponse.isSuccess()){
            response.setResult(closeResponse.getTradeNo());
            return this.success(response);
        }else {
            return this.error(response, "请求失败!");
        }
    }

    @RequestMapping(value = "refund/query"
            ,method = RequestMethod.POST
            ,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            ,produces = MediaType.APPLICATION_JSON_UTF8_VALUE
    )
    public ResponseEntity<IServiceResponse> refundQuery(@RequestBody AliPayReFundServiceRequest request) throws AlipayApiException {
        AliPayRefundServiceResponse response = new AliPayRefundServiceResponse();
        //获得初始化的AlipayClient
        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id, AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key, AlipayConfig.sign_type);
        //设置请求参数
        AlipayTradeFastpayRefundQueryRequest alipayRequest = new AlipayTradeFastpayRefundQueryRequest();
        //商户订单号，商户网站订单系统中唯一订单号
        String out_trade_no = request.getEntity().getWidOutTradeNo();
        //支付宝交易号 请二选一设置,同时存在时,支付宝默认使用trade_no
        String trade_no = request.getEntity().getTradeNo();
        //退款单号
        String out_request_no = request.getEntity().getOutRequestNo();

        alipayRequest.setBizContent("{" +
                "\"out_request_no\":\""+ out_request_no +"\"," +
              //  "\"trade_no\":\""+ trade_no +"\"," +
                "\"out_trade_no\":\""+ out_trade_no +"\"}");

        //请求
        AlipayTradeFastpayRefundQueryResponse res = alipayClient.execute(alipayRequest);
        if (res.isSuccess()){
            String result = res.getRefundStatus();
            // res.getRefundStatus()  REFUND_PROCESSING 退款处理中；REFUND_SUCCESS或者null 退款处理成功；REFUND_FAIL 退款失败
            response.setResult(result);
            return this.success(response);
        }else {
            return this.error(response,"请求失败!");
        }
    }
}