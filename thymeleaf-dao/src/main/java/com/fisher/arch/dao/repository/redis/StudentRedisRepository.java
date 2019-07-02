package com.fisher.arch.dao.repository.redis;

import com.fisher.arch.dao.po.Student;
import org.springframework.data.keyvalue.repository.KeyValueRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRedisRepository extends KeyValueRepository<Student, String> {

}

