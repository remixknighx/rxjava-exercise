package com.bill.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.bill.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author wangjf
 * @date 2019/6/30 0030.
 */
@Mapper
public interface StudentMapper extends BaseMapper<StudentEntity> {

    List<StudentEntity> getStudentList();

}
