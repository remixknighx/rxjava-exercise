package com.bill.sensestudio;

import com.bill.sensestudio.dict.SenseTimeDict;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

/**
 * @author wangjianfeng
 */
@Service
public class SenseStudioVerifyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SenseStudioVerifyService.class);

    @Autowired
    private OkHttpClient okHttpClient;
    @Autowired
    private AuthGenerator authGenerator;

    float SUCCESS_SCORE = 0.6F;

    public boolean verifyIdNumber(String name, String idNumber, String filePath) {
        String token = authGenerator.generateToken();
        File file = new File(filePath);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image_file", file.getName(),  RequestBody.create(MediaType.parse("image/png"), file))
                .addFormDataPart("name", name)
                .addFormDataPart("idnumber", idNumber)
                .addFormDataPart("auto_rotate", String.valueOf(true))
                .build();

        Request request = new Request.Builder()
                .header("Authorization", token)
                .url("https://v2-auth-api.visioncloudapi.com/identity/idnumber_verification/stateless")
                .post(requestBody)
                .build();

        try {
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == HttpStatus.OK.value() && response.body() != null) {
                ObjectMapper objectMapper = new ObjectMapper();
                VerifyResponse verifyResponse = objectMapper.readValue(response.body().string(), VerifyResponse.class);
                if (SenseTimeDict.VALID_RESPONSE.getCode() == verifyResponse.getCode() && verifyResponse.getVerification_score() >= SUCCESS_SCORE) {
                    LOGGER.info("user [{}] 第三方人脸验证通过 score [{}]", name, verifyResponse.getVerification_score());
                    return true;
                } else {
                    LOGGER.warn("人脸验证不通过! name => [{}], verifyResponse => [{}]", name, verifyResponse.toString());
                }
            } else {
                LOGGER.warn("调用商汤接口状态异常! name => [{}], code => [{}]", name, response.code());
            }

            return false;
        } catch (IOException e) {
            LOGGER.error("连接商汤接口网络异常! name => [{}]", name, e);
            return false;
        }
    }

}
