package com.bill.sensestudio.dict;

public enum SenseTimeDict {
    /***/
    VALID_RESPONSE(1000, "valid response", "有效响应"),
    INVALID_CREDENTIAL(1100, "invalid credential", "账号密码不匹配或签名认证生成有误"),
    INVALID_ARGUMENT(1200, "invalid argument", "输入参数无效"),
    EXPIRED_CREDENTIAL(1101, "expired credential",	"账号过期"),
    URL_NO_PERMISSION(1103, "url no permission", "该接口没有权限"),
    RATE_LIMIT_EXCEEDED(1002, "rate limit exceeded", "调用频率超过限制"),
    INVALID_IMAGE_SIZE(2003, "invalid image size", "图片大小不符合要求"),
    INVALID_CONTENT_LENGTH(2004, "invalid content length", "输入内容长度不符合要求"),
    INVALID_IMAGE_TYPE(2005, "invalid image type", "图片类型不符合要求"),
	CORRUPTED_IMAGE_ERROR(2006, "corrupted image error", "图片损坏"),
    EXTERNAL_SERVICE_UNAVAILABLE(3000, "external service unavailable", "外部服务不可用"),
    EXTERNAL_SERVICE_TIMEOUT(3001, "external service timeout", "外部服务超时"),
    EXTERNAL_SERVICE_ERROR(3002, "external service error", "外部服务出错"),
    NAME_ID_MISMATCHED(3003, "name id mismatched", "姓名与身份证号不匹配"),
    INVALID_ID_NUMBER(3004, "invalid id number", "身份证号无效"),
    PHOTO_NOT_EXIST(3005, "photo not exist", "第三方底图不存在"),
    DETECTION_FAILED(4000, "detection failed", "提取特征失败,没有检测到图片中的人脸");


    private int code;
    private String value;
    private String description;

    private SenseTimeDict(int code, String value, String description) {
        this.code = code;
        this.value = value;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public String getDescription() {
        return description;
    }
}
