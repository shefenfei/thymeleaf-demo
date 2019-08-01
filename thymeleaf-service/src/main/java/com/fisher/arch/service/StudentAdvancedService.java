package com.fisher.arch.service;

import com.fisher.arch.dao.po.Student;

import java.util.List;

public interface StudentAdvancedService {

    List<Student> findByExample(String name);

    Student findOneByExample(String name);

    Student updateByPartial(Student student);




}
