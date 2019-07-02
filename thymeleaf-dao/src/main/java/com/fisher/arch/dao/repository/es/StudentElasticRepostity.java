package com.fisher.arch.dao.repository.es;

import com.fisher.arch.dao.custom.CustomizedStudentRepository;
import com.fisher.arch.dao.po.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.Future;

@Repository
public interface StudentElasticRepostity extends ElasticsearchRepository<Student, String>, CustomizedStudentRepository {

    @Query("{\n" +
            "  \"bool\": {\n" +
            "    \"must\": {\n" +
            "      \"term\": {\n" +
            "        \"name\": \"?0\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}")
    Page<Student> findByName(String name, Pageable pageable);


    @Query("{\n" +
            "  \"bool\": {\n" +
            "    \"must\": {\n" +
            "      \"term\": {\n" +
            "        \"gender\": \"?\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}")
    List<Student> findStudentsByGender(Student.Gender gender);

    List<Student> findTop2ByName(String name);

    Page<Student> findStudentsByBirthday(String birthday, Pageable pageable);

    @Async
    Future<Student> findStudentByName(String name);

    @Async
    Future<List<Student>> findStudentsByNameLike(String name);

}
