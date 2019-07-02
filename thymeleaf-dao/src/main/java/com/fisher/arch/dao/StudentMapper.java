package com.fisher.arch.dao;

import com.fisher.arch.dao.po.Student;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface StudentMapper {

    int saveStudent(Student student);

    Student findStudentById(Integer id);

    int deleteStudentById(Integer id);

    int updateStudent(Student student);
}
