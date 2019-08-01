package com.fisher.arch.web.controller;

import com.fisher.arch.dao.po.Student;
import com.fisher.arch.service.StudentAdvancedService;
import com.fisher.arch.service.StudentService;
import com.voyager.beyonds.rsik.manager.annoation.EnableRsik;
import com.voyager.beyonds.rsik.manager.client.RsikClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/other")
public class OtherController {

    @Autowired
    private StudentService studentService;
    @Autowired
    private StudentAdvancedService studentAdvancedService;
    @Autowired
    RsikClient client;

    @GetMapping("/test")
    @EnableRsik(eventId = "gift", uri = "/v2/event")
    public ResponseEntity foo(@RequestHeader("tokenId") String tokenId, @RequestHeader("ip") String localhost) {
        client.sayHello();
        return new ResponseEntity<>("{\n" +
                "  \"success\":true,\n" +
                "  \"code\":200\n" +
                "}", HttpStatus.OK);
    }

    @GetMapping("/test2")
    @EnableRsik(eventId = "register", uri = "/v2/event")
    public ResponseEntity foo1() {
        return new ResponseEntity<>("{\n" +
                "  \"success\":true,\n" +
                "  \"code\":200\n" +
                "}", HttpStatus.OK);
    }


    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Student student) {
        studentService.saveStudent(student);
        return new ResponseEntity<>("{\n" +
                "  \"success\":\"ok\"\n" +
                "}", HttpStatus.OK);
    }


    @GetMapping("/deleteById/{uid}")
    public ResponseEntity deleteById(@PathVariable("uid") String uid) {
        studentService.deleteStudentById(uid);
        return new ResponseEntity<>("{\n" +
                "  \"success\":\"ok\"\n" +
                "}", HttpStatus.OK);
    }


    @GetMapping("/findAll")
    public ResponseEntity findAllStudent() {
        List<Student> allStudent = studentService.findAllStudent();
        return new ResponseEntity<>(allStudent, HttpStatus.OK);
    }

    @GetMapping("/findByCondition")
    public ResponseEntity findByCondition() {
        Student shefenfei = studentService.findByName("shefenfei");
        System.out.println(shefenfei);
        List<Student> allStudent = studentService.findAllStudent();
        return new ResponseEntity<>(allStudent, HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity findByName(@PathVariable("name") String name) {
        Student shefenfei = studentService.findByName(name);
        System.out.println(shefenfei);
        List<Student> allStudent = studentService.findAllStudent();
        return new ResponseEntity<>(allStudent, HttpStatus.OK);
    }

//    @Trace
    @GetMapping("/findByNameAndCode")
    public ResponseEntity findByNameAndCode(String name, String code) {

//        ActiveSpan.tag("name&code", "from Thymeleaf-demo#findByNameAndCode(..) ");
        Student byNameAndCode = studentService.findByNameAndCode(name, code);
        log.info("返回 log :{}", byNameAndCode);
//        ActiveSpan.tag("name&code", "from Thymeleaf-demo#findByNameAndCode(..) result : " + byNameAndCode);
//        ActiveSpan.tag("name&code", "from Thymeleaf-demo#findByNameAndCode(..) result : " + num);
        return new ResponseEntity<>(byNameAndCode, HttpStatus.OK);
    }


    @PostMapping("/update")
    public ResponseEntity updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
        return new ResponseEntity<>("{\n" +
                "  \"success\":\"ok\"\n" +
                "}", HttpStatus.OK);
    }

}
