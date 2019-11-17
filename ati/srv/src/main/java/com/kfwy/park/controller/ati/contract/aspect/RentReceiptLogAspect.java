package com.kfwy.park.controller.ati.contract.aspect;

import com.gniuu.framework.common.utils.OperateFillUtils;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.kfwy.park.ati.contract.business.IRentReceiptLogManager;
import com.kfwy.park.ati.contract.entity.RentReceiptLogEntity;
import com.kfwy.park.controller.ati.contract.vo.RentReceiptRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by liuzhengyang on 2018/8/3.
 * 本该@Component注入即可,但是spring全局配置原因,xml注入
 */
@Aspect
public class RentReceiptLogAspect {

    @Autowired
    private IRentReceiptLogManager rentReceiptLogManager;

    private  static  final Logger logger = LoggerFactory.getLogger(RentReceiptLogAspect.class);

    //Controller层切点
    @Pointcut("execution (* com.kfwy.park.controller.ati.contract..RentReceiptService.update(..))")
    public  void update() {
    }
    @Pointcut("execution (* com.kfwy.park.controller.ati.contract..RentReceiptService.delete(..))")
    public  void delete() {
    }
    @Pointcut("execution (* com.kfwy.park.controller.ati.contract..RentReceiptService.pay(..))")
    public  void pay() {
    }

    /**
     * 后置通知 用于拦截Controller层记录用户的操作
     *
     * @param joinPoint 切点
     */
    @After("update()||delete()||pay()")
    public  void after(JoinPoint joinPoint) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //请求的IP
        String ip = request.getRemoteAddr();
        System.out.println("=====================后置通知=======================");
        try {
            Object[] arguments = joinPoint.getArgs();
            RentReceiptRequest req = (RentReceiptRequest)arguments[0];

            System.out.println("租金编号: " + req.getEntity().getRentCode());
            RentReceiptLogEntity log = new RentReceiptLogEntity();
            OperateFillUtils.fill(log);
            log.setRentCode(req.getEntity().getRentCode());
            log.setIp(ip);
            log.setMethod(joinPoint.getSignature().getName());
            //保存数据库
            rentReceiptLogManager.create(log);
            System.out.println("=====controller后置通知结束=====");
        }  catch (Exception e) {
            //记录本地异常日志
            logger.error("==后置通知异常==");
            logger.error("异常信息:{}", e.getMessage());
        }
    }

}
