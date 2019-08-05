package com.fisher.arch.dao.repository.redis;

import com.fisher.arch.dao.po.Person;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class PersonRedisTemplateRepository {

    private static final String KEY_PERSON = "persons";
    private RedisTemplate<String, Object> redisTemplate;
    private HashOperations hashOperations;

    public PersonRedisTemplateRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void savePerson(Person person) {
        hashOperations.put(KEY_PERSON, person.getId(), person);
    }

    public long deletePerson(Person person) {
        return hashOperations.delete(KEY_PERSON, person);
    }

    public void updatePerson(Person person) {
        hashOperations.put(KEY_PERSON, person.getId(), person);
    }

    public Person findById(String id) {
        return (Person) hashOperations.get(KEY_PERSON, id);
    }

    public List<Person> findAllPerson() {
        ArrayList<Person> list = new ArrayList<>();
        Map<String, Person> entries = hashOperations.entries(KEY_PERSON);
        entries.forEach((key, value) -> {
            list.add(value);
        });
        return list;
    }

    public long deleteByIds(Integer... ids) {
        return hashOperations.delete(KEY_PERSON, ids);
    }
}
