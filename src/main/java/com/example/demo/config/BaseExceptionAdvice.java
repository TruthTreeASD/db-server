package com.example.demo.config;

import com.example.demo.util.BaseException;
import com.example.demo.util.http.ResponseMessage;
import com.example.demo.util.http.ResponseMessageCodeEnum;
import com.example.demo.util.http.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Order(value=4)
public class BaseExceptionAdvice {

    private static final Logger logger = LoggerFactory.getLogger(BaseExceptionAdvice.class);

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(BaseException.class)
    @ResponseBody
    public ResponseMessage handlerAdcDaBaseException(BaseException exception) {
        logger.warn(exception.getMessage(), exception);
        return Result.error(exception.getErrorCode(), exception.getMessage());
    }

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseMessage handlerAdcDaBaseException(Exception exception) {
        logger.error(exception.getMessage(), exception);
        return Result.error(ResponseMessageCodeEnum.ERROR.getCode(), "System error, please contact admin");
    }

}