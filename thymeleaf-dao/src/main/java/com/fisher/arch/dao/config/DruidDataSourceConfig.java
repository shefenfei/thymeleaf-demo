package com.fisher.arch.dao.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class DruidDataSourceConfig {

    @Value("${thymeleaf-web.database.url}")
    private String databaseUrl;

    @Value("${thymeleaf-web.database.driver}")
    private String driverClass;

    @Value("${thymeleaf-web.database.username}")
    private String username;

    @Value("${thymeleaf-web.database.password}")
    private String password;

    @Value("classpath:mappers/*.xml")
    private Resource[] mapperLocations;

    @Value("com.fisher.arch.dao.po")
    private String typeAliasePackage;

    @Bean
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(databaseUrl);
        dataSource.setDriverClassName(driverClass);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        return dataSource;
    }


    @Bean
    public SqlSessionFactoryBean sessionFactory(DruidDataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setMapperLocations(mapperLocations);
        sqlSessionFactoryBean.setTypeAliasesPackage(typeAliasePackage);
        return sqlSessionFactoryBean;
    }

}
