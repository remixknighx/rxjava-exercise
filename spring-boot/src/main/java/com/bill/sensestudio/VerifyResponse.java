package com.bill.sensestudio;

/**
 * @author wangjianfeng
 */
public class VerifyResponse {

      private String request_id;
      private int code;
      private float verification_score;

    public String getRequest_id() {
        return request_id;
    }

    public VerifyResponse setRequest_id(String request_id) {
        this.request_id = request_id;
        return this;
    }

    public int getCode() {
        return code;
    }

    public VerifyResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public float getVerification_score() {
        return verification_score;
    }

    public VerifyResponse setVerification_score(float verification_score) {
        this.verification_score = verification_score;
        return this;
    }

    @Override
    public String toString() {
        return "VerifyResponse{" +
                "request_id='" + request_id + '\'' +
                ", code=" + code +
                ", verification_score=" + verification_score +
                '}';
    }
}
