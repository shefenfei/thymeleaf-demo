package com.fisher.arch.service.impl;

import com.fisher.arch.dao.po.Student;
import com.fisher.arch.dao.repository.db.StudentRepository;
import com.fisher.arch.dao.repository.es.StudentElasticRepostity;
import com.fisher.arch.dao.repository.redis.StudentRedisRepository;
import com.fisher.arch.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentDBRepository;
    private StudentRedisRepository studentRedisRepository;
    private StudentElasticRepostity studentElasticRepostity;

    public StudentServiceImpl(StudentRedisRepository studentRedisRepository,
                              StudentRepository studentDBRepository,
                              StudentElasticRepostity studentElasticRepostity) {
        this.studentRedisRepository = studentRedisRepository;
        this.studentElasticRepostity = studentElasticRepostity;
        this.studentDBRepository = studentDBRepository;
    }

    @Override
    public boolean saveStudent(Student student) {
        studentDBRepository.saveStudent(student);
        studentRedisRepository.save(student);
        studentElasticRepostity.save(student);
        return true;
    }

    @Override
    public void deleteStudentById(String uid) {
        studentRedisRepository.deleteById(uid);
        studentElasticRepostity.deleteById(uid);
    }

    @Override
    public List<Student> findAllStudent() {
        ArrayList<Student> arrayList = new ArrayList<>();
        Iterable<Student> students = studentRedisRepository.findAll();
        for (Student student : students) {
            arrayList.add(student);
        }
        return arrayList.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public void updateStudent(Student student) {
        studentRedisRepository.save(student);
    }


    @Override
    public Student findById(String id) {
        Optional<Student> optionalStudent = studentRedisRepository.findById(id);
        return optionalStudent.orElseGet(() -> {
            log.info("redis不存在，从es加载");
            Optional<Student> studentESOptional = studentElasticRepostity.findById(id);
            if (studentESOptional.isPresent()) {
                Student studentFromEs = studentESOptional.get();
                studentRedisRepository.save(studentFromEs);
                return studentFromEs;
            } else {
                log.info("Es 中不存在，从db中加载");
                Student studentFromDb = studentDBRepository.findStudentById(Integer.valueOf(id));
                studentRedisRepository.save(studentFromDb);
                studentElasticRepostity.save(studentFromDb);
                return studentFromDb;
            }
        });
    }
}
