package com.fisher.cache.springbootcacheredis.service;

import com.fisher.cache.springbootcacheredis.bean.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class CacheService {


    @Cacheable(cacheNames = "users", key = "#type")
    public List<User> findAllUser(Integer type, boolean isFetch) {
        System.out.println("执行方法");
        return IntStream.range(0, 9).mapToObj(index -> {
            User user = new User();
            user.setId(index);
            user.setUsername("username" + index);
            user.setPassword("password" + index);
            return user;
        }).collect(Collectors.toList());
    }


    @Cacheable(cacheNames = "dept")
    public User findById(Integer id) {
        User user = new User();
        user.setId(id);
        user.setUsername("username");
        user.setPassword("password");
        return user;
    }

}
