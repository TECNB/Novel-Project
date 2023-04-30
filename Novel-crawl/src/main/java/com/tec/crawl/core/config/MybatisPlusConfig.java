package com.tec.crawl.core.config;

import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MybatisPlusConfig {

    private DataSource dataSource;

    @Primary
    @Bean("db1SqlSessionFactory")
    public SqlSessionFactory db1SqlSessionFactory(DataSource dataSource) throws Exception {
        this.dataSource = dataSource;
        /**
         * 使用 mybatis plus 配置
         */
        MybatisSqlSessionFactoryBean b1 = new MybatisSqlSessionFactoryBean();
        System.out.println("dataSourceLyz"+dataSource.toString());
        b1.setDataSource(dataSource);
        b1.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return b1.getObject();
    }
}
