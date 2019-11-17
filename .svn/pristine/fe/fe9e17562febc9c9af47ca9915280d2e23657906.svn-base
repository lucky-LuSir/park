package com.kfwy.park.controller.sys.sms;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import com.gniuu.framework.common.context.CurrentContext;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.dataaccess.PageResult;
import com.gniuu.framework.exception.BusinessException;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.gniuu.framework.service.IServiceResponse;
import com.gniuu.framework.service.SpringRestService;
import com.kfwy.park.common.utils.ParamUtil;
import com.kfwy.park.common.utils.SMSUtil;
import com.kfwy.park.controller.sys.sms.vo.SMSTemplateServiceRequest;
import com.kfwy.park.controller.sys.sms.vo.SMSTemplateServiceResponse;
import com.kfwy.park.sys.sms.business.ISMSTemplateManager;
import com.kfwy.park.sys.sms.business.ISmsSendHistoryManager;
import com.kfwy.park.sys.sms.entity.SMSTemplateEntity;
import com.kfwy.park.sys.sms.entity.SmsSendHistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 短信模板
 * @Auth luming
 * @Date 2019/7/11 10:33
 * @Version 1.0
 * @Param
 * @return
 */
@RequestMapping(path = "/sms")
@Controller
public class SMSTemplateService extends SpringRestService {

    @Autowired
    private ISMSTemplateManager smsTemplateManager;

    @Autowired
    private ISmsSendHistoryManager smsSendHistoryManager;

    private  static  final Logger logger = LoggerFactory.getLogger(SMSTemplateService.class);

    @RequestMapping(path = "/create"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> create(@RequestBody SMSTemplateServiceRequest request) throws Exception {
        SMSTemplateServiceResponse response = new SMSTemplateServiceResponse();

        SMSTemplateEntity smsTemplateEntity = request.getEntity();
        smsTemplateEntity.setSmsCode(CodeGenUtils.generate());

        List<String> list = new ArrayList();
        list.add("templateName");
        list.add("templateType");
        list.add("templateId");
        list.add("templateSignName");
        list.add("templateContent");
        ParamUtil<SMSTemplateEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(smsTemplateEntity, list);

        smsTemplateManager.create(smsTemplateEntity);
        return this.success(response);
    }

    /**
     * @Description 修改
     * @Auth luming
     * @Date 2019/7/17 9:26
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/update"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> update(@RequestBody SMSTemplateServiceRequest request) throws Exception {
        SMSTemplateServiceResponse response = new SMSTemplateServiceResponse();

        SMSTemplateEntity smsTemplateEntity = request.getEntity();

        List<String> list = new ArrayList();
        list.add("templateName");
        list.add("templateType");
        list.add("templateId");
        list.add("templateSignName");
        list.add("templateContent");
        ParamUtil<SMSTemplateEntity> paramUtil = new ParamUtil<>();
        paramUtil.check(smsTemplateEntity, list);

        smsTemplateManager.update(smsTemplateEntity);
        return this.success(response);
    }

    /**
     * @Description 分页查询
     * @Auth luming
     * @Date 2019/7/11 13:49
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/query"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> query(@RequestBody SMSTemplateServiceRequest request) throws Exception {
        SMSTemplateServiceResponse response = new SMSTemplateServiceResponse();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());
        PageResult<SMSTemplateEntity> result = smsTemplateManager.findByMap(map, request.getStart(), request.getPageSize(), "id", false);

        response.setResult(result);
        return this.success(response);
    }

    /**
     * @Description 查询集合
     * @Auth luming
     * @Date 2019/7/11 14:01
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/list"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> list(@RequestBody SMSTemplateServiceRequest request) throws Exception {
        SMSTemplateServiceResponse response = new SMSTemplateServiceResponse();

        SMSTemplateEntity smsTemplateEntity = request.getEntity();

        Map<String, Object> map = new HashMap<>();
        map.put("isDeleted", false);
        map.put("cpyCode", CurrentContext.getCpyCode());

        ParamUtil<SMSTemplateEntity> paramUtil = new ParamUtil<>();
        paramUtil.objectToMap(smsTemplateEntity, map);

        List<SMSTemplateEntity> list = smsTemplateManager.findByMap(map);

        response.setResult(list);
        return this.success(response);
    }

    private static Map<String, Object> params = new HashMap<>();

    /**
     * @Description 发送短信
     * @Auth luming
     * @Date 2019/7/11 16:07
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "/sendSMS"
            , method = RequestMethod.POST
            , consumes = MediaType.APPLICATION_JSON_UTF8_VALUE
            , produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<IServiceResponse> sendSMS(@RequestBody SMSTemplateServiceRequest request) throws Exception {
        SMSTemplateServiceResponse response = new SMSTemplateServiceResponse();
        SMSTemplateEntity smsTemplateEntity = request.getEntity();

        String smsCode = smsTemplateEntity.getSmsCode();// 模板code
        String templateName = smsTemplateEntity.getTemplateName();// 模板名称
        String templateType = smsTemplateEntity.getTemplateType();// 模板类型

        String phoneNumbers = request.getPhoneNumbers();// 手机号
        String templateSignName = smsTemplateEntity.getTemplateSignName();// 模板签名
        String templateId = smsTemplateEntity.getTemplateId();// 模板ID
        String templateParam = request.getTemplateParam();// 短信模板参数

        // 发送短信
        CommonResponse sendResponse = SMSUtil.sendSms(phoneNumbers, templateSignName, templateId, templateParam);
        String data = sendResponse.getData();

        // 发送短信成功后响应数据
        request.setData(data);

        /**
         * 短信发送后，返回的参数
         */
        JSONObject sendResObj = JSONObject.parseObject(data);

        // 请求状态码不等于“OK”的情况下表示请求失败，不进行查询操作
        String sc = sendResObj.getString("Code");
        if (!sc.equals("OK")) {
            throw new BusinessException(sendResObj.getString("Message"));
        }

        params.put("smsCode", smsCode);
        params.put("templateName", templateName);
        params.put("templateType", templateType);
        params.put("templateSignName", templateSignName);
        params.put("templateId", templateId);
        params.put("data", data);
        params.put("userCode", CurrentContext.getUserInfo().getUserCode());
        params.put("userName", CurrentContext.getUserInfo().getUserName());
        params.put("ownerDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
        params.put("ownerDeptName", CurrentContext.getUserInfo().getOwnerDeptName());
        params.put("cpyCode", CurrentContext.getUserInfo().getCpyCode());
        params.put("cpyName", CurrentContext.getUserInfo().getCpyName());

        return this.success(response);
    }

    /**
     * @Description 短信发送结果推送
     * @Auth luming
     * @Date 2019/7/29 16:45
     * @Version 1.0
     * @Param
     * @return
     */
    @RequestMapping(path = "smsSendCallback")
    @ResponseBody
    public JSONObject aliyunCallBack(HttpServletRequest request) {
        // 公共参数
        String smsCode = (String) params.get("smsCode");
        String templateName = (String) params.get("templateName");
        String templateType = (String) params.get("templateType");
        String templateSignName = (String) params.get("templateSignName");
        String templateId = (String) params.get("templateId");
        String data = (String) params.get("data");

        // 基础字段
        String userCode = (String) params.get("userCode");
        String userName = (String) params.get("userName");
        String ownerDeptCode = (String) params.get("ownerDeptCode");
        String ownerDeptName = (String) params.get("ownerDeptName");
        String cpyCode = (String) params.get("cpyCode");
        String cpyName = (String) params.get("cpyName");


        try {
            BufferedReader streamReader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            StringBuilder responseStrBuilder = new StringBuilder();

            String inputStr;
            while ((inputStr = streamReader.readLine()) != null) {
                responseStrBuilder.append(inputStr);
            }

            JSONArray jsonArray = JSONArray.parseArray(responseStrBuilder.toString());
            for (int i = 0; i < jsonArray.size(); i++) {
                SmsSendHistoryEntity ssh = new SmsSendHistoryEntity();
                ssh.setSendCode(CodeGenUtils.generate());
                ssh.setSmsCode(smsCode);
                ssh.setTemplateName(templateName);
                ssh.setTemplateType(templateType);
                ssh.setSignName(templateSignName);
                ssh.setTemplateCode(templateId);

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                ssh.setPhoneNum(jsonObject.getString("phone_number"));
                ssh.setSendDate(jsonObject.getString("send_time"));
                ssh.setReceiveDate(jsonObject.getString("report_time"));
                ssh.setSuccess(jsonObject.getBoolean("success"));
                ssh.setErrCode(jsonObject.getString("err_code"));
                ssh.setErrMsg(jsonObject.getString("err_msg"));
                ssh.setSmsSize(jsonObject.getString("sms_size"));
                ssh.setBizId(jsonObject.getString("biz_id"));

                // 填充基础字段
                ssh.setCreateCode(userCode);
                ssh.setCreateName(userName);
                ssh.setCreateDeptCode(ownerDeptCode);
                ssh.setCreateDeptName(ownerDeptName);
                ssh.setCpyCode(cpyCode);
                ssh.setCpyName(cpyName);
                ssh.setOwnerCode(userCode);
                ssh.setOwnerName(userName);
                ssh.setOwnerDeptCode(ownerDeptCode);
                ssh.setOwnerDeptName(ownerDeptName);
                ssh.setLastUpdateCode(userCode);
                ssh.setLastUpdateName(userName);

                /**
                 * 短信发送后，返回的参数
                 */
                JSONObject sendResObj = JSONObject.parseObject(data);

                // 请求状态码不等于“OK”的情况下表示请求失败，不进行查询操作
                String sc = sendResObj.getString("Code");
                if (!sc.equals("OK")) {
                    throw new BusinessException(sendResObj.getString("Message"));
                }

                // 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。
                String bizId = sendResObj.getString("BizId");
                // 当前日期
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                String date = sdf.format(new Date());

                CommonResponse queryResponse = SMSUtil.QuerySendDetails(jsonObject.getString("phone_number"), date, "50", "1", bizId);
                String d = queryResponse.getData();
                JSONObject jsonData = JSONObject.parseObject(d);
                JSONArray array = JSONArray.parseArray(jsonData.getJSONObject("SmsSendDetailDTOs").getString("SmsSendDetailDTO"));

                for (int j = 0; j < array.size(); j++) {
                    JSONObject jsonObject1 = array.getJSONObject(i);
                    ssh.setContent(jsonObject1.getString("Content"));// 短信内容
                }

                int ins = smsSendHistoryManager.create(ssh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("msg", "接收成功");
        return jsonObject;
    }
}
