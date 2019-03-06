package com.fisher.arch.dao.repository;

import com.fisher.arch.dao.UserMapper;
import com.fisher.arch.dao.po.UserPO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserRepository {

    @Autowired
    private UserMapper userMapper;

    public UserPO getUserById(Integer id) {
        return userMapper.getUserById(id);
    }

}
