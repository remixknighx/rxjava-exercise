package com.bill.common;

import com.bill.common.enumeration.StatusEnum;
import com.bill.dto.Response;

/**
 * @author wangjf
 * @date 2019/9/15 0015.
 */
public class ResponseUtil {

    public static Response ok(Object data) {
        return new Response<>(StatusEnum.success, data);
    }

    public static Response error(String msg, String err) {
        return new Response<>(StatusEnum.success, err);
    }

}
