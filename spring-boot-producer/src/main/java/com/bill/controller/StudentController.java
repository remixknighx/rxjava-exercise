package com.bill.controller;

import com.bill.entity.StudentEntity;
import com.bill.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author wangjf
 * @date 2019/6/30 0030.
 */
@RestController
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/queryAllStudents")
    public List<StudentEntity> queryAllStudents() {
        return studentService.findAllStudent();
    }

}
