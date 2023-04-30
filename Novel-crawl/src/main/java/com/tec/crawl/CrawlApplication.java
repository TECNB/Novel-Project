package com.tec.crawl;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@SpringBootApplication
@MapperScan("com.tec.crawl.dao.mapper")
@ServletComponentScan
@EnableTransactionManagement
public class CrawlApplication {

    public static void main(String[] args) {

        SpringApplication.run(CrawlApplication.class, args);
        log.info("项目启动成功！！！");
    }



}
