package com.fisher.arch.service;

import com.fisher.arch.dao.po.Person;

import java.util.List;

public interface PersonService {

    void savePerson(Person person);

    Person findById(String id);

    List<Person> findAll();

    long deleteByIds(Integer... ids);

}
