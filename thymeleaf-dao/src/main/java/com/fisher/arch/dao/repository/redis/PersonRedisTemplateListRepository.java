package com.fisher.arch.dao.repository.redis;

import com.fisher.arch.dao.po.Person;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class PersonRedisTemplateListRepository {

    private static final String KEY_PERSON = "personlist";
    private RedisTemplate<String, Object> redisTemplate;
    //适合做热点数据用，不用的时候清除， 数据结构是数组一样的，从下标0开始 [0,1,2,3,4,5,......],新添加的数据在最左边，角标为0
    private ListOperations listOperations;

    public PersonRedisTemplateListRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.listOperations = redisTemplate.opsForList();
    }

    public void addPerson(Person person) {
        listOperations.leftPush(KEY_PERSON, person);
    }

    public long getNumberOfPerson() {
        return listOperations.size(KEY_PERSON);
    }

    public Person getFriendAtIndex(Integer index) {
        return (Person) listOperations.index(KEY_PERSON, index);
    }

    public void removePerson(Integer index, Person person) {
        listOperations.remove(KEY_PERSON, index, person);
    }

}
