package com.fisher.arch.dao.custom.impl;

import com.fisher.arch.dao.custom.CustomizedStudentRepository;
import com.fisher.arch.dao.po.Student;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

/*
*
*  对应于片段接口的类名最重要的部分是Impl后缀。
*  自定义实现的优先级高于基本实现和存储库方面
 */
@Repository
public class CustomizedStudentRepositoryImpl implements CustomizedStudentRepository {

    @Override
    public Student fromOtherSource(String id) {
        if (id.length() > 2) {
            Student student = new Student();
            student.setBirthday(new Date());
            student.setGender(Student.Gender.MALE);
            student.setName("newuser");
            return student;
        }
        return null;
    }

    @Override
    public Optional<Student> findStudentFromRedis(String id) {
        return Optional.empty();
    }
}
