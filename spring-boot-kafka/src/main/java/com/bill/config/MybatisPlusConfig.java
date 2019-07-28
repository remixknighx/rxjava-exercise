package com.bill.config;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Configuration;

/**
 * @author wangjf
 * @date 2019/6/30 0030.
 */
@Configuration
public class MybatisPlusConfig {

    public PaginationInterceptor paginationInterceptor(){
        PaginationInterceptor page = new PaginationInterceptor();
        //设置方言类型
        page.setDialectType("mysql");
        return page;
    }

}
