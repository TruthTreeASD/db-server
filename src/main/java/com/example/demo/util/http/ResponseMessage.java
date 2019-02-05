package com.example.demo.util.http;

/**
 * Created by Administrator on 2018/3/9.
 */
public class ResponseMessage<T> {

    private String respCode;
    private String respMsg;
    private T data;
    private boolean ok;

    public ResponseMessage() {
    }

    public ResponseMessage(String respCode, String message) {
        this.respCode = respCode;
        this.respMsg = message;
    }

    public ResponseMessage(String respCode, String message, boolean ok) {
        this.respCode = respCode;
        this.respMsg = message;
        this.ok = ok;
    }

    public ResponseMessage(String respCode, String message, boolean ok, T data) {
        this.respCode = respCode;
        this.respMsg = message;
        this.ok = ok;
        this.data = data;
    }

    public String getRespCode() {
        return this.respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getMessage() {
        return this.respMsg;
    }

    public void setMessage(String message) {
        this.respMsg = message;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isOk() {
        return this.ok;
    }
}

