package edu.neu.cs6510.config;

import edu.neu.cs6510.util.BaseException;
import edu.neu.cs6510.util.http.ResponseMessage;
import edu.neu.cs6510.util.http.ResponseMessageCodeEnum;
import edu.neu.cs6510.util.http.Result;
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