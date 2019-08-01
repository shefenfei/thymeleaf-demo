package com.fisher.arch.service.impl;

import com.fisher.arch.dao.po.Student;
import com.fisher.arch.dao.repository.redis.StudentRedisRepository;
import com.fisher.arch.service.StudentAdvancedService;
import org.springframework.data.domain.Example;
import org.springframework.data.redis.core.RedisKeyValueTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentAdvancedServiceImpl implements StudentAdvancedService {

    @Resource
    private StudentRedisRepository studentRedisRepository;

    @Resource
    private RedisKeyValueTemplate redisKeyValueTemplate;

    @Override
    public List<Student> findByExample(String name) {
        Student student = new Student();
        student.setId("1");
        Student student1 = studentRedisRepository.findOne(Example.of(student)).orElse(null);
        System.out.println(student1);
        return null;
    }

    @Override
    public Student findOneByExample(String name) {
        Student student = new Student();
        student.setId("1");
        student.setName(name);
        Example<Student> studentExample = Example.of(student);
        return studentRedisRepository.findOne(studentExample).orElse(null);
    }

    @Override
    public Student updateByPartial(Student student) {
        student.setName("aasdadad");
//        PartialUpdate<Student> studentPartialUpdate = PartialUpdate.newPartialUpdate(student.getId(), Student.class);
//        studentPartialUpdate.set("name", student.getName() + ":changed");
//        redisKeyValueTemplate.update(student);
        return null;
    }



}
