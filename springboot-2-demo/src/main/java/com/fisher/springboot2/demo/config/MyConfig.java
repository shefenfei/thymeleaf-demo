package com.fisher.springboot2.demo.config;

import com.fisher.springboot2.demo.bean.MyDemo;
import com.fisher.springboot2.demo.bean.Student;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnJava;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.system.JavaVersion;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//当存在这个类的时候，这个配置才生效
// 当Conditional配置的条件生效的时候才有用
@ConditionalOnJava(value = JavaVersion.EIGHT)
@ConditionalOnMissingBean
@ConditionalOnProperty
@ConditionalOnClass(value = {MyDemo.class})
public class MyConfig {

    //将方法的返回值加载到容器中
    @Bean(name = "student")
    public Student myStudent() {
        Student student = new Student();
        student.setAddress("sha");
        student.setUsername("stu name");
        return student;
    }



}
