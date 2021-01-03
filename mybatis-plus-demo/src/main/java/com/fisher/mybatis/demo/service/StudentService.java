package com.fisher.mybatis.demo.service;

import com.fisher.mybatis.demo.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author shefenfei
 * @since 2020-04-27
 */
public interface StudentService extends IService<Student> {


    void addNewStudent(Student student);

}
