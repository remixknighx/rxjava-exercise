package com.bill.common.exception;

/**
 * @author wangjf
 * @date 2019/10/4 0004.
 */
public class ZkException extends RuntimeException {

    public ZkException(String message) {
        super(message);
    }

    public ZkException(String message, Throwable cause) {
        super(message, cause);
    }

    public ZkException(Throwable cause) {
        super(cause);
    }
}
