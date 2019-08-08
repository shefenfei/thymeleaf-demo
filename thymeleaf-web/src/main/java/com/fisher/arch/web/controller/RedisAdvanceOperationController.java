package com.fisher.arch.web.controller;

import com.fisher.arch.dao.po.Student;
import com.fisher.arch.service.StudentAdvancedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * redis 一些高级操作
 */
@RestController
@RequestMapping("/redis")
public class RedisAdvanceOperationController {

    @Resource
    private StudentAdvancedService studentAdvancedService;

    @GetMapping("/findOneByExample/{name}")
    public ResponseEntity findOneByExample(@PathVariable("name") String name) {
       studentAdvancedService.findByExample(name);
        return new ResponseEntity<>("", HttpStatus.OK);
    }



    @PostMapping("/updateByPartial/")
    public ResponseEntity updateByPartial(@RequestBody Student student) {
        studentAdvancedService.updateByPartial(student);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

}
