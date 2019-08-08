package com.fisher.arch.web.controller;

import com.fisher.arch.dao.po.Person;
import com.fisher.arch.dao.repository.redis.PersonRedisSetRepository;
import com.fisher.arch.dao.repository.redis.PersonRedisTemplateListRepository;
import com.fisher.arch.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/persons")
@Slf4j
public class PersonController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);

    @Resource
    private PersonService personService;
    @Resource
    private PersonRedisTemplateListRepository listRepository;
    @Resource
    private PersonRedisSetRepository redisSetRepository;

    @PostMapping("/save")
    public ResponseEntity savePerson(@RequestBody Person person) {
        LOGGER.info("数据 ：{}",  person);
        personService.savePerson(person);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity findPersonById(@PathVariable("id") String id) {
        Person person = personService.findById(id);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll() {
        List<Person> people = personService.findAll();
        return new ResponseEntity(people, HttpStatus.OK);
    }

    @PostMapping("/deleteByIds")
    public ResponseEntity deleteByIds(@RequestBody Integer[] ids) {
        LOGGER.info("ids : {}", ids);
        personService.deleteByIds(ids);
        return new ResponseEntity<>("", HttpStatus.OK);
    }


    @PostMapping("/saveByList")
    public ResponseEntity saveByList(@RequestBody Person person) {
        listRepository.addPerson(person);
        long numberOfPerson = listRepository.getNumberOfPerson();
        LOGGER.info("number of person : {}", numberOfPerson);

        Person friendAtIndex = listRepository.getFriendAtIndex(1);
        LOGGER.info("friendArIndex : {}", friendAtIndex);

        listRepository.removePerson(1, person);

        return new ResponseEntity<>("", HttpStatus.OK);
    }


    @GetMapping("/remove/{index}")
    public ResponseEntity removePerson(@PathVariable("index") Integer index, @RequestBody Person person) {
        listRepository.removePerson(index, person);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    //----------------------------set操作-------------------------------------

    @PostMapping("/saveBySet")
    public ResponseEntity saveBySet(@RequestBody Person[] people) {
        redisSetRepository.addFamilyMembers(people);
        LOGGER.info("request body : {}", people);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/findAllBySet")
    public ResponseEntity findAllPerson() {
        Set<Person> members = redisSetRepository.getMembers();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }

}
