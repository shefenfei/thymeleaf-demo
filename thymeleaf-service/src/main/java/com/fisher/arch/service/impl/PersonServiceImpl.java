package com.fisher.arch.service.impl;

import com.fisher.arch.dao.po.Person;
import com.fisher.arch.dao.repository.redis.PersonRedisTemplateRepository;
import com.fisher.arch.service.PersonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Resource
    private PersonRedisTemplateRepository template;


    @Override
    public void savePerson(Person person) {
        template.savePerson(person);
    }

    @Override
    public Person findById(String id) {
        return template.findById(id);
    }

    @Override
    public List<Person> findAll() {
        return template.findAllPerson();
    }

    @Override
    public long deleteByIds(Integer... ids) {
        return template.deleteByIds(ids);
    }


}
