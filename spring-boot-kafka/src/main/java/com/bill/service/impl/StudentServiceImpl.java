package com.bill.service.impl;

import com.bill.entity.StudentEntity;
import com.bill.mapper.StudentMapper;
import com.bill.producer.SimpleKafkaProducer;
import com.bill.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangjf
 * @date 2019/6/30 0030.
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    @Qualifier("simpleProducer")
    private SimpleKafkaProducer producer;

    @Override
    public List<StudentEntity> findAllStudent() {
        return studentMapper.getStudentList();
    }

    @Override
    public String send2Kafka(String data) {
        producer.sendMessage(data);
        return null;
    }
}
