package com.fisher.logback.demo.dao;

import com.fisher.logback.demo.model.Person;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FriendDao {

    private static final String KEY = "friendKey";

    private RedisTemplate<String, Object> redisTemplate;
    private ListOperations<String, Object> opsForList;

    public FriendDao(RedisTemplate<String, Object> redisTemplate) {
        this.opsForList = redisTemplate.opsForList();
    }

    public void addFriend(Person person) {
        opsForList.leftPush(KEY, person);
    }

    public long countFriendSize() {
        return opsForList.size(KEY);
    }

    public Person getFriendAtIndex(Integer index) {
        return (Person) opsForList.index(KEY, index);
    }

    public void removeFriend(Person person) {
        opsForList.remove(KEY, 1, person);
    }
}
