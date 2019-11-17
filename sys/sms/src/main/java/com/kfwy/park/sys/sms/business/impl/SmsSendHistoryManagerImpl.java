package com.kfwy.park.sys.sms.business.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonResponse;
import com.gniuu.framework.common.business.AbstractManager;
import com.gniuu.framework.common.utils.CodeGenUtils;
import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.dataaccess.mybatis.IMyBatisBaseDao;
import com.gniuu.framework.exception.BusinessException;
import com.kfwy.park.common.utils.SMSUtil;
import com.kfwy.park.sys.sms.business.ISmsSendHistoryManager;
import com.kfwy.park.sys.sms.dao.ISmsSendHistoryDbDao;
import com.kfwy.park.sys.sms.entity.SmsSendHistoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Description 短信发送记录
 * @Auth luming
 * @Date 2019/7/23 17:35
 * @Version 1.0
 * @Param
 * @return
 */
@Service
public class SmsSendHistoryManagerImpl extends AbstractManager<SmsSendHistoryEntity> implements ISmsSendHistoryManager {
    @Autowired
    private ISmsSendHistoryDbDao smsSendHistoryDbDao;

    @Override
    protected IMyBatisBaseDao<SmsSendHistoryEntity, Long> getMyBatisDao() {
        return this.smsSendHistoryDbDao;
    }


    /**
     * @Description 新增短信发送历史记录
     * @Auth luming
     * @Date 2019/7/25 17:26
     * @Version 1.0
     * @Param
     * @return
     */
    @Override
    public int insertSmsSendHistory(Map<String, Object> map) throws Exception {
        String smsCode = (String) map.get("smsCode");
        String templateName = (String) map.get("templateName");
        String templateType = (String) map.get("templateType");
        String phoneNumbers = (String) map.get("phoneNumbers");
        String templateSignName = (String) map.get("templateSignName");
        String templateId = (String) map.get("templateId");
        String data = (String) map.get("data");

        String userCode = (String) map.get("userCode");
        String userName = (String) map.get("userName");
        String ownerDeptCode = (String) map.get("ownerDeptCode");
        String ownerDeptName = (String) map.get("ownerDeptName");
        String cpyCode = (String) map.get("cpyCode");
        String cpyName = (String) map.get("cpyName");


        JSONObject jsonObject = JSONObject.parseObject(data);

        // 发送回执ID，可根据该ID在接口QuerySendDetails中查询具体的发送状态。
        String bizId = jsonObject.getString("BizId");

        // 请求状态码不等于“OK”的情况下表示请求失败，不进行查询操作
        String sc = jsonObject.getString("Code");
        if (!sc.equals("OK")) {
            throw new BusinessException(jsonObject.getString("Message"));
        }

        // 当前日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String date = sdf.format(new Date());

        // 拆分手机号
        String[] phoneList = phoneNumbers.split(",");

        List<SmsSendHistoryEntity> list = new ArrayList<>();

        for (String phone : phoneList) {
            CommonResponse queryResponse = SMSUtil.QuerySendDetails(phone, date, "50", "1", bizId);

            String d = queryResponse.getData();
            JSONObject jsonData = JSONObject.parseObject(d);

            int totalCount = jsonData.getIntValue("TotalCount");// 当前手机号当天总共发了多少条短信
            String code = jsonData.getString("Code");// 请求状态码
            String message = jsonData.getString("Message");// 状态码的描述
            String requestId = jsonData.getString("RequestId");// 请求ID

            // 当前手机号发送的总条数小于50，表示只有一页
            if (totalCount < 50) {
                JSONArray array = JSONArray.parseArray(jsonData.getJSONObject("SmsSendDetailDTOs").getString("SmsSendDetailDTO"));

                for (int i = 0; i < array.size(); i++) {
                    JSONObject jsonObject1 = array.getJSONObject(i);
                    String sendDate = jsonObject1.getString("SendDate");
                    String sendStatus = jsonObject1.getString("SendStatus");
                    String receiveDate = jsonObject1.getString("ReceiveDate");
                    String templateCode = jsonObject1.getString("TemplateCode");
                    String content = jsonObject1.getString("Content");
                    String phoneNum = jsonObject1.getString("PhoneNum");

                    // 封装数据
                    SmsSendHistoryEntity smsSendHistoryEntity = new SmsSendHistoryEntity();
                    smsSendHistoryEntity.setSendCode(CodeGenUtils.generate());// 短信发送code
                    smsSendHistoryEntity.setSmsCode(smsCode);// 模板code，关联模板表
                    smsSendHistoryEntity.setTemplateName(templateName);// 模板名称
                    smsSendHistoryEntity.setTemplateType(templateType);// 模板类型

//                    smsSendHistoryEntity.setCode(code);// 请求状态码
//                    smsSendHistoryEntity.setMessage(message);// 状态码的描述
//                    smsSendHistoryEntity.setRequestId(requestId);// 请求ID
                    smsSendHistoryEntity.setContent(content);// 短信内容
                    smsSendHistoryEntity.setPhoneNum(phoneNum);// 接收短信的手机号码
                    smsSendHistoryEntity.setReceiveDate(receiveDate);// 短信接收日期和时间
                    smsSendHistoryEntity.setSendDate(sendDate);// 发送时间
//                    smsSendHistoryEntity.setSendStatus(sendStatus);// 发送状态
                    smsSendHistoryEntity.setTemplateCode(templateCode);// 短信模板ID（阿里云生成）
                    smsSendHistoryEntity.setSignName(templateSignName);// 短信模板签名

                    list.add(smsSendHistoryEntity);
                }
            } else {
                int ps = totalCount % 50;
                int totalPage = 1;// 共多少页

                if (ps == 0) {
                    totalPage = totalCount / 50;
                } else {
                    totalPage = totalCount / 50 + 1;
                }

                for (int i = 1; i < (totalPage + 1); i++) {
                    String startPage = String.valueOf(i);// 开始页
                    CommonResponse qr = SMSUtil.QuerySendDetails(phone, date, "50", startPage, bizId);

                    String smsSendDetailDTO = JSONObject.parseObject(qr.getData()).getJSONObject("SmsSendDetailDTOs").getString("SmsSendDetailDTO");
                    JSONArray array = JSONArray.parseArray(smsSendDetailDTO);

                    JSONObject jsonObject1 = array.getJSONObject(i);
                    String sendDate = jsonObject1.getString("SendDate");
                    String sendStatus = jsonObject1.getString("SendStatus");
                    String receiveDate = jsonObject1.getString("ReceiveDate");
                    String templateCode = jsonObject1.getString("TemplateCode");
                    String content = jsonObject1.getString("Content");
                    String phoneNum = jsonObject1.getString("PhoneNum");

                    // 封装数据
                    SmsSendHistoryEntity smsSendHistoryEntity = new SmsSendHistoryEntity();
                    smsSendHistoryEntity.setSendCode(CodeGenUtils.generate());// 短信发送code
                    smsSendHistoryEntity.setSmsCode(smsCode);// 模板code，关联模板表
                    smsSendHistoryEntity.setTemplateName(templateName);// 模板名称
                    smsSendHistoryEntity.setTemplateType(templateType);// 模板类型

//                    smsSendHistoryEntity.setCode(code);// 请求状态码
//                    smsSendHistoryEntity.setMessage(message);// 状态码的描述
//                    smsSendHistoryEntity.setRequestId(requestId);// 请求ID
                    smsSendHistoryEntity.setContent(content);// 短信内容
                    smsSendHistoryEntity.setPhoneNum(phoneNum);// 接收短信的手机号码
                    smsSendHistoryEntity.setReceiveDate(receiveDate);// 短信接收日期和时间
                    smsSendHistoryEntity.setSendDate(sendDate);// 发送时间
//                    smsSendHistoryEntity.setSendStatus(sendStatus);// 发送状态
                    smsSendHistoryEntity.setTemplateCode(templateCode);// 短信模板ID（阿里云生成）
                    smsSendHistoryEntity.setSignName(templateSignName);// 短信模板签名

                    list.add(smsSendHistoryEntity);
                }
            }
        }

        // 填充基础字段
        for (SmsSendHistoryEntity smsSendHistoryEntity : list) {
            OperateFillUtils.fill(smsSendHistoryEntity);
            smsSendHistoryEntity.setCreateCode(userCode);
            smsSendHistoryEntity.setCreateName(userName);
            smsSendHistoryEntity.setCreateDeptCode(ownerDeptCode);
            smsSendHistoryEntity.setCreateDeptName(ownerDeptName);
            smsSendHistoryEntity.setCpyCode(cpyCode);
            smsSendHistoryEntity.setCpyName(cpyName);
            smsSendHistoryEntity.setOwnerCode(userCode);
            smsSendHistoryEntity.setOwnerName(userName);
            smsSendHistoryEntity.setOwnerDeptCode(ownerDeptCode);
            smsSendHistoryEntity.setOwnerDeptName(ownerDeptName);
            smsSendHistoryEntity.setLastUpdateCode(userCode);
            smsSendHistoryEntity.setLastUpdateName(userName);
        }
        int i = smsSendHistoryDbDao.batchInsert(list);

        return i;
    }
}
