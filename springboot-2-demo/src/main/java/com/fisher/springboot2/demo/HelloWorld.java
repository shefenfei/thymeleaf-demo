package com.fisher.springboot2.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class HelloWorld {

    /**
     * @postConstruct() 注解的方法会在spring框架启动， 1，构造函数 2，所有的依赖注入完成，3，执行目标方法
     */
    @PostConstruct
    public void sayHello() {
        System.out.println("Hello world, from Spring boot 2.0!");
    }


    @Bean
    public String getString() {
        System.out.println("hello  world! from @bean 方法");
        return "hello world";
    }
}
