package com.tec.vuepractice;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Slf4j
@SpringBootApplication
@MapperScan("com.tec.vuepractice.dao.mapper")
@ServletComponentScan
@EnableTransactionManagement
public class VuePracticeApplication {
    public static void main(String[] args) {
        SpringApplication.run(VuePracticeApplication.class, args);
        log.info("项目启动成功！！！");
    }
}
