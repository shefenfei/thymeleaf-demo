package com.fisher.arch.web.controller;

import com.fisher.arch.dao.po.Student;
import com.fisher.arch.dao.repository.es.StudentElasticRepostity;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("/elastic")
@Slf4j
public class ElasticController {

    @Autowired
    private StudentElasticRepostity repostity;

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @PostMapping("/save")
    public ResponseEntity saveStudent(@RequestBody Student student) {
        repostity.save(student);
        return new ResponseEntity<>("{\n" +
                "  \"success\":\"ok\"\n" +
                "}", HttpStatus.OK);
    }


    @GetMapping("/findAll")
    public ResponseEntity findAllStudent() {
        ArrayList<Student> objects = new ArrayList<>();
        Iterable<Student> students = repostity.findAll();
        for (Student student : students) {
            objects.add(student);
        }
        return new ResponseEntity<>(objects, HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity deleteStudentById(@PathVariable("id") String id) {
        repostity.deleteById(id);
        return findAllStudent();
    }

    @GetMapping("/byElastic")
    public ResponseEntity byElasticTemplate() {
        SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(new MatchAllQueryBuilder())
                .withIndices("thymleaf")
                .withTypes("student")
                .withFields("name")
                .withPageable(PageRequest.of(0, 10))
                .build();

        Page<Student> students = elasticsearchTemplate.startScroll(1000, searchQuery, Student.class);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    @GetMapping("/findByName/{name}")
    public ResponseEntity findByName(@PathVariable("name") String name) {
//        Student byName = repostity.findByName(name);
        Page<Student> students = repostity.findByName(name, PageRequest.of(0, 10));
        List<Student> content = students.getContent();
        return new ResponseEntity<>(content, HttpStatus.OK);
    }


    @GetMapping("/findByGender")
    public ResponseEntity findByGender() {
        List<Student> students = repostity.findStudentsByGender(Student.Gender.FEMALE);
        return new ResponseEntity<>(students, HttpStatus.OK);
    }


    @GetMapping("/findByGender2Slice")
    public ResponseEntity findByGender2Slice() {
//        List<Student> studentSlice = repostity.findByGender(Student.Gender.FEMALE, PageRequest.of(0, 10));
        List<Student> studentList = repostity.findTop2ByName("zhangwentao");
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }


    @GetMapping("/findByBirthday/{birthday}")
    public ResponseEntity findByBirthday(@PathVariable("birthday") String birthday) {
        Slice<Student> students = repostity.findStudentsByBirthday(birthday, PageRequest.of(0, 10));
        log.info("hasNext: {}", students.hasNext());
        log.info("hasPrevious : {}", students.hasPrevious());
        log.info("getSize : {}", students.getSize());
        log.info("totalPage : {}", ((Page<Student>) students).getTotalPages());
        log.info("content : {}", students.getContent());
        return new ResponseEntity<>(students.hasNext(), HttpStatus.OK);
    }


    @GetMapping("/findByAsynName/{name}")
    public ResponseEntity findByAsynName(@PathVariable("name") String name) {
        Future<Student> future = repostity.findStudentByName(name);
        if (future.isDone()) {
            try {
                Student student = future.get();
                return new ResponseEntity<>(student, HttpStatus.OK);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @GetMapping("/findByAsynNameLike/{name}")
    public ResponseEntity findByAsynNameLike(@PathVariable("name") String name) {
        Future<List<Student>> future = repostity.findStudentsByNameLike(name);
        if (future.isDone()) {
            try {
                List<Student> list = future.get();
                return new ResponseEntity<>(list, HttpStatus.OK);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    @GetMapping("/findByCustom/{id}")
    public ResponseEntity findByCustom(@PathVariable("id") String id) {
        Student student = repostity.fromOtherSource(id);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
