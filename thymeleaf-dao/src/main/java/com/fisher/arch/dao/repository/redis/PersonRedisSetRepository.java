package com.fisher.arch.dao.repository.redis;

import com.fisher.arch.dao.po.Person;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class PersonRedisSetRepository {

    private static final String KEY_PERSON = "personset";
    private RedisTemplate<String, Object> redisTemplate;
    private SetOperations setOperations;

    public PersonRedisSetRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.setOperations = redisTemplate.opsForSet();
    }

    public void addFamilyMembers(Person... people) {
        setOperations.add(KEY_PERSON, people);
    }

    public Set<Person> getMembers() {
        return setOperations.members(KEY_PERSON);
    }

    public long sizeOfMembers() {
        return setOperations.size(KEY_PERSON);
    }

    public long removeMemebers(Person... people) {
        return setOperations.remove(KEY_PERSON, people);
    }
}
