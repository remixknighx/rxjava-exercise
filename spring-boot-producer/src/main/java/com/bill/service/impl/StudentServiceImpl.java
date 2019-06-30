package com.bill.service.impl;

import com.bill.entity.StudentEntity;
import com.bill.mapper.StudentMapper;
import com.bill.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public List<StudentEntity> findAllStudent() {
        return studentMapper.getStudentList();
    }
}
