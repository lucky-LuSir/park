package com.kfwy.park.common.handler;

import com.gniuu.framework.common.enums.ResponseMessage;
import com.gniuu.framework.exception.BaseException;
import com.gniuu.framework.logging.Logger;
import com.gniuu.framework.logging.LoggerFactory;
import com.gniuu.framework.service.AbstractServiceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * Created by davidcun on 2018/5/17.
 */
@ControllerAdvice
public class RestExceptionHandler{


    private static Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * 针对REST风格接口，统一返回HTTP OK状态，消息内容是失败的
     * @param ex
     * @param request
     * @return
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({BaseException.class})
    public ResponseEntity<AbstractServiceResponse> handlerRestException(BaseException ex, WebRequest request){

        AbstractServiceResponse response = new AbstractServiceResponse();
        response.setIsSuccess(false);
        response.setIsException(true);

        logger.error("BusinessExceptionHandler happen",ex);

        if (ex instanceof BaseException){

            BaseException be = (BaseException) ex;

            response.setCode(be.getCode());
            response.setMessage(be.getErrorMsg());
        }else{
            response.setMessage(ResponseMessage.unKnownErrorCode_message);
            response.setCode(ResponseMessage.unKnownErrorCode);
        }

        return new ResponseEntity<AbstractServiceResponse>(response,HttpStatus.OK);
    }


    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler({Exception.class})
    public ResponseEntity<AbstractServiceResponse> handlerDefaultException(Exception ex, WebRequest request){

        AbstractServiceResponse response = new AbstractServiceResponse();
        response.setIsSuccess(false);
        response.setIsException(true);
        response.setMessage("");
        response.setCode("500");

        logger.error("exception happen",ex);

        return new ResponseEntity<AbstractServiceResponse>(response,HttpStatus.OK);
    }


}
