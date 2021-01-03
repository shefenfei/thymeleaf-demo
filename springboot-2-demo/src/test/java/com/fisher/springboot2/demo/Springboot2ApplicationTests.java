package com.fisher.springboot2.demo;

import com.fisher.springboot2.demo.bean.Person;
import com.fisher.springboot2.demo.bean.Student;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot2ApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void contextLoads() {
        System.out.println(person);
        Student student1 = ((Student) applicationContext.getBean("student"));
        Student student2 = ((Student) applicationContext.getBean("student"));
        System.out.println(student1.toString());

        System.out.println(student1.equals(student2));
//        Assert.assertNotEquals(student1, student2);
    }

}
