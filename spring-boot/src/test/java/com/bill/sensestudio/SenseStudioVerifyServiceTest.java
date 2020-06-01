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
        String name = "王剑峰";
        String idNumber = "310229199204010612";
        String filePath = "C:\\Users\\wbwangjianfeng\\Desktop\\商汤\\wuyanzu.jpg";
        System.out.println(senseStudioVerifyService.verifyIdNumber(name, idNumber, filePath));
    }
}