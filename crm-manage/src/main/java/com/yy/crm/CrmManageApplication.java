package com.yy.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 鲁源源 on 2017/10/17.
 */
@SpringBootApplication
@RestController
@EnableTransactionManagement
//@MapperScan(basePackages = "com.yy.crm.service.mapper")
@EnableAsync
@ServletComponentScan("com.yy.crm.manage.config.druid")
public class CrmManageApplication {
    public static void main(String[] args) {
        SpringApplication.run(CrmManageApplication.class,args);
    }

    @GetMapping("/hello")
    public String hello(){
        return "hello spring security";
    }

}
