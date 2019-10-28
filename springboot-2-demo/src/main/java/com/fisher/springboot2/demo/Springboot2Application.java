package com.fisher.springboot2.demo;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;

@SpringBootApplication
public class Springboot2Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Springboot2Application.class, args);
        System.out.println(context.getBeanDefinitionCount());

        String[] beanNames = context.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        Arrays.asList(beanNames).forEach(System.out::println);
    }


    /**
     * 这个方法在应用程序启动完成以后执行
     * @param helloWorld
     * @return
     */
    @Bean
    public ApplicationRunner applicationRunner(HelloWorld helloWorld) {
        return args -> {
            helloWorld.sayHello();
        };
    }
}
