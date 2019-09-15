package com.bill.dto;

import com.bill.common.enumeration.StatusEnum;

/**
 * @author wangjf
 * @date 2019/9/15 0015.
 */
public class Response<T> {
    private int status;
    private String msg;
    private String err;
    private T data;

    public Response(StatusEnum statusEnum, T data) {
        this.status = statusEnum.getStatus();
        this.msg = statusEnum.getMsg();
        this.data = data;
    }

    public Response(StatusEnum statusEnum, String err) {
        this.status = statusEnum.getStatus();
        this.msg = statusEnum.getMsg();
        this.err = err;
    }

    public Response(int status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getErr() {
        return err;
    }

    public void setErr(String err) {
        this.err = err;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
