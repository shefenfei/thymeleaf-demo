package com.fisher.arch.dao.custom;

import com.fisher.arch.dao.po.Student;

import java.util.Optional;

/**
 * 自定义Repo的实现
 */
public interface CustomizedStudentRepository {

    Student fromOtherSource(String id);

    Optional<Student> findStudentFromRedis(String id);
}
