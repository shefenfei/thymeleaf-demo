package com.fisher.mybatis.demo.service.impl;

import com.fisher.mybatis.demo.entity.Student;
import com.fisher.mybatis.demo.mapper.StudentMapper;
import com.fisher.mybatis.demo.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author shefenfei
 * @since 2020-04-27
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

    @Override
    public void addNewStudent(Student student) {
        save(student);
    }
}
