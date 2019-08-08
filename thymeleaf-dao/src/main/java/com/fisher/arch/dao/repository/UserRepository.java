package com.fisher.arch.dao.repository;

import com.fisher.arch.dao.UserMapper;
import com.fisher.arch.dao.po.UserPO;
import com.fisher.arch.model.dto.UserDto;
import com.fisher.arch.model.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

@Component
@Slf4j
public class UserRepository {

    private RedisTemplate redisTemplate;

    private HashOperations hashOperations;

    @Resource
    private UserMapper userMapper;

    public UserRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }

    public UserPO getUserById(Integer id) throws UserNotFoundException {
        UserPO user = userMapper.getUserById(id);
        return user;
    }

    public boolean saveUser(UserDto userDto) {
        UserPO userPO = new UserPO();
        userPO.setId(new Random().nextInt(1000));
        userPO.setUsername(userDto.getUser_name());
        userPO.setPass(userDto.getPass());
        hashOperations.put("user", userPO.getId(), userPO);
        return true;
    }

    public List<UserDto> findAllUser() {
        Map<Integer, UserPO> user = hashOperations.entries("user");
        user.forEach((k, v) -> {
            System.out.println(k +"..." +v.getUsername());
        });

        List<UserPO> users = ((List<UserPO>) hashOperations.values("user")
                .stream()
                .collect(Collectors.toList()));

        return users.stream().map(userPO -> {
            UserDto userDto = new UserDto();
            userDto.setUser_name(userPO.getUsername());
            userDto.setPass(userPO.getPass());
            return userDto;
        }).collect(Collectors.toList());
    }

    public void deleteById(Integer userId) {

    }

}
