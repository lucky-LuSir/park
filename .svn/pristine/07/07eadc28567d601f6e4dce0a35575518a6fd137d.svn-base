package com.kfwy.park.controller.sys.sms.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.gniuu.framework.common.context.CurrentContext;
import com.kfwy.park.sys.sms.business.ISmsSendHistoryManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * @Description 短信发送成功后，新增发送历史记录，切面
 * @Auth luming
 * @Date 2019/7/27 14:13
 * @Version 1.0
 * @Param
 * @return
 */
@Component
@Aspect
public class SmsSendAspect {
    @Autowired
    private ISmsSendHistoryManager smsSendHistoryManager;

//    @Pointcut("execution(* com.kfwy.park.controller.sys.sms.SMSTemplateService.sendSMS(..))")
//    public void sendSMS() {
//
//    }

//    @Pointcut("execution(* com.kfwy.park.sys.sms.business.impl.SmsSendHistoryManagerImpl.sendSms(..))")
//    public void sendSMS() {
//
//    }


//    @AfterReturning(value="sendSMS()" ,returning="ret")
//    public void afterReturning(JoinPoint joinPoint, Object ret) throws Exception {
//        String str = ret.toString();
//        Map<String, Object> map = (Map)JSON.parse(str);
//
//
//        smsSendHistoryManager.insertSmsSentHistory(map);
//    }

//    @After("sendSMS()")
//    public void after(JoinPoint joinPoint) throws Exception {
//        Object[] args = joinPoint.getArgs();
//        JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(args[0]));
//        String phoneNumbers = jsonObject.getString("phoneNumbers");
//        String data = jsonObject.getString("data");
//
//        JSONObject entity = jsonObject.getJSONObject("entity");
//
//        String smsCode = entity.getString("smsCode");
//        String templateName = entity.getString("templateName");
//        String templateType = entity.getString("templateType");
//        String templateSignName = entity.getString("templateSignName");
//        String templateId = entity.getString("templateId");
//
//        Map<String, Object> map = new HashMap<>();
//        map.put("smsCode", smsCode);
//        map.put("templateName", templateName);
//        map.put("templateType", templateType);
//        map.put("phoneNumbers", phoneNumbers);
//        map.put("templateSignName", templateSignName);
//        map.put("templateId", templateId);
//        map.put("data", data);
//        map.put("userCode", CurrentContext.getUserInfo().getUserCode());
//        map.put("userName", CurrentContext.getUserInfo().getUserName());
//        map.put("ownerDeptCode", CurrentContext.getUserInfo().getOwnerDeptCode());
//        map.put("ownerDeptName", CurrentContext.getUserInfo().getOwnerDeptName());
//        map.put("cpyCode", CurrentContext.getUserInfo().getCpyCode());
//        map.put("cpyName", CurrentContext.getUserInfo().getCpyName());
//
//        Timer nTimer = new Timer();
//        nTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                try {
//                    smsSendHistoryManager.insertSmsSentHistory(map);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        },10000);
//    }
}
