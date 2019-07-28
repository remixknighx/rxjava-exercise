package com.bill.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author wangjf
 * @date 2019/6/30 0030.
 */
@Data
public class StudentEntity {

    private Long id;

    private String name;

    private Integer gender;

    private Integer status;

    private Date createTime;

}
