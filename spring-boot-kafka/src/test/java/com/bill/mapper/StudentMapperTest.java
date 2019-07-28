package com.bill.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wangjf
 * @date 2019/6/30 0030.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentMapperTest {

    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void testGetStudentList() throws Exception {
        System.out.println(studentMapper.getStudentList());
    }

}