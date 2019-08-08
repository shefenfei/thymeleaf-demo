package com.fisher.arch.dao.repository.redis;

import com.fisher.arch.dao.po.Student;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRedisRepository extends KeyValueRepository<Student, String>, QueryByExampleExecutor<Student> {

    Student findByName(String name);

    Optional<Student> findByNameAndCode(String name, String code);

}

