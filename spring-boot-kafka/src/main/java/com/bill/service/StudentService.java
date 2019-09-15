package com.bill.service;

import com.bill.entity.StudentEntity;

import java.util.List;

/**
 * @author wangjf
 * @date 2019/6/30 0030.
 */
public interface StudentService {

    List<StudentEntity> findAllStudent();

    boolean send2Kafka(String data);

}
