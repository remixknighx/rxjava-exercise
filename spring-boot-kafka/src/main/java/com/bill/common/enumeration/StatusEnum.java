package com.bill.common.enumeration;

/**
 * @author wangjf
 * @date 2019/9/15 0015.
 */
public enum StatusEnum {
    /***/
    success(0, "ok"),
    error(-1, "error");

    private int status;
    private String msg;
    private StatusEnum(int status, String msg) {
        this.status = status;
        this.msg = msg;
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
}
