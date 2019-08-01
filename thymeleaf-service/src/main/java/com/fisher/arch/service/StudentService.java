package com.fisher.arch.service;

import com.fisher.arch.dao.po.Student;

import java.util.List;

public interface StudentService {

    boolean saveStudent(Student student);

    void deleteStudentById(String uid);

    List<Student> findAllStudent();

    void updateStudent(Student student);

    Student findById(String id);

    Student findByName(String name);

    Student findByNameAndCode(String name, String code);
}
