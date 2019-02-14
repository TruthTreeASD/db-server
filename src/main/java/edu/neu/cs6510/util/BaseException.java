package edu.neu.cs6510.util;

/**
 * Created by Administrator on 2018/3/2.
 */
public class BaseException extends RuntimeException {
    private String errorCode;

    public BaseException() {
    }

    public BaseException(String message) {
        this("-1", message);
    }

    public BaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
