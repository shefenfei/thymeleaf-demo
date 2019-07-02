package com.fisher.arch.dao.repository.db;

import com.fisher.arch.dao.StudentMapper;
import com.fisher.arch.dao.po.Student;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
@Slf4j
public class StudentRepository {

    @Resource
    private StudentMapper studentMapper;

    public int saveStudent(Student student) {
        return studentMapper.saveStudent(student);
    }


    public Student findStudentById(Integer id) {
        return studentMapper.findStudentById(id);
    }

}
