package com.bill.sensestudio;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SenseStudioVerifyServiceTest {

    @Autowired
    private SenseStudioVerifyService senseStudioVerifyService;

    @Test
    public void verifyIdNumber() {
        String name = "";
        String idNumber = "";
        String filePath = "C:\\Users\\wbwangjianfeng\\Desktop\\商汤\\image1.jpg";
        System.out.println(senseStudioVerifyService.verifyIdNumber(name, idNumber, filePath));
    }
}