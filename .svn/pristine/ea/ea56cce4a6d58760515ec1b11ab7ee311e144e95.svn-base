package com.kfwy.park.common.utils;

import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;

/**
 * @Description 阿里云短信工具类
 * @Auth luming
 * @Date 2019/7/11 14:15
// * @Version 1.0
 * @Param
 * @return
 */
public class SMSUtil {

    /**
     * 开发者自己的AK（在阿里云访问控制台寻找）
     * AccessKeyId用于标识用户
     * AccessKeySecret是用来验证用户的密钥。AccessKeySecret必须保密。
     */
    static final String ACCESS_KEY_ID = "LTAI5kaIbEfuGXNQ";
    static final String ACCESS_KEY_SECRET = "nyC4K0DMPy8yhXumLj5Z0Gh6yde4IS";

    /**
     * @Description 发送短信
     * @Auth luming
     * @Date 2019/7/11 14:31
     * @Version 1.0
     * @Param
     * @return
     */
    public static CommonResponse sendSms(String phoneNumbers, String signName, String templateId, String TemplateParamJson) throws ClientException {
        // 可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        // 接收短信的手机号，单次最多支持1000个号码的发送（英文逗号分隔）
        request.putQueryParameter("PhoneNumbers", phoneNumbers);
        // 模板签名
        request.putQueryParameter("SignName", signName);
        // 模板ID
        request.putQueryParameter("TemplateCode", templateId);
        // 参数，JSON字符串格式
        request.putQueryParameter("TemplateParam", TemplateParamJson);

        CommonResponse response = null;

        try {
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * @Description 批量发送短信
     * @Auth luming
     * @Date 2019/7/11 16:19
     * @Version 1.0
     * @Param
     * @return
     */
    public static CommonResponse SendBatchSms(String phones, String templateId, String templateSignName) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendBatchSms");
        request.putQueryParameter("PhoneNumberJson", "17630801896");
        request.putQueryParameter("SignNameJson", "签名json");
        request.putQueryParameter("TemplateCode", "模板code");

        CommonResponse response = null;

        try {
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * @Description 查询发送的信息
     * @Auth luming
     * @Date 2019/7/11 16:22
     * @Version 1.0
     * @Param
     * @return
     */
    public static CommonResponse QuerySendDetails(String phoneNumber, String sendDate, String pageSize, String currentPage, String bizId) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("QuerySendDetails");
        request.putQueryParameter("PhoneNumber", phoneNumber);// 接收短信的手机号
        request.putQueryParameter("SendDate", sendDate);// 发送时间，格式为yyyyMMdd
        request.putQueryParameter("PageSize", pageSize);// 分页查看发送记录，指定每页显示的短信记录数量。取值范围为1~50。
        request.putQueryParameter("CurrentPage", currentPage);// 分页查看发送记录，指定发送记录的的当前页码
        request.putQueryParameter("BizId", bizId);// 发送回执ID，即发送流水号。调用发送接口SendSms或SendBatchSms发送短信时，返回值中的BizId字段。

        CommonResponse response = null;

        try {
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return response;
    }

    /**
     * @Description 查询发送的信息
     * @Auth luming
     * @Date 2019/7/11 16:22
     * @Version 1.0
     * @Param
     * @return
     */
    public static CommonResponse QuerySendDetails(String phoneNumber, String sendDate, String pageSize, String currentPage) {
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("QuerySendDetails");
        request.putQueryParameter("PhoneNumber", phoneNumber);// 接收短信的手机号
        request.putQueryParameter("SendDate", sendDate);// 发送时间，格式为yyyyMMdd
        request.putQueryParameter("PageSize", pageSize);// 分页查看发送记录，指定每页显示的短信记录数量。取值范围为1~50。
        request.putQueryParameter("CurrentPage", currentPage);// 分页查看发送记录，指定发送记录的的当前页码

        CommonResponse response = null;

        try {
            response = client.getCommonResponse(request);
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }

        return response;
    }
}
