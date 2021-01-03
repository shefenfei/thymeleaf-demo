package com.fisher.mybatis;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

public class CodeGenerator {
    public static void main(String[] args) {

        AutoGenerator autoGenerator = new AutoGenerator();

        //1, 全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setAuthor("shefenfei");
        globalConfig.setActiveRecord(true);
        globalConfig.setBaseColumnList(true);
        globalConfig.setBaseResultMap(true);
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false);
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setIdType(IdType.AUTO);
        globalConfig.setSwagger2(true);
        globalConfig.setOutputDir("/Users/shefenfei/Documents/web_workspace/thymeleaf-demo/mybatis-plus-demo/src/main/java");

        autoGenerator.setGlobalConfig(globalConfig);


        //2, 数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/thymeleaf?useSSL=true&useUnicode=true&characterEncoding=utf8&autoReconnect=true&failOverReadOnly=false&zeroDateTimeBehavior=convertToNull");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");

        autoGenerator.setDataSource(dataSourceConfig);


        //3，包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setController("controller");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setParent("com.fisher.mybatis.demo");
        packageConfig.setService("service");

        autoGenerator.setPackageInfo(packageConfig);

        //4, 策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityTableFieldAnnotationEnable(true);

        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true);

        strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);
        autoGenerator.setStrategy(strategyConfig);

        autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());

        //5. 执行
        autoGenerator.execute();
    }
}
