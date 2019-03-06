package com.fisher.arch.service.impl;

import com.fisher.arch.dao.po.UserPO;
import com.fisher.arch.dao.repository.UserRepository;
import com.fisher.arch.model.vo.UserVO;
import com.fisher.arch.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexServiceImpl implements IndexService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public String testIndex() {
        return "hello";
    }

    @Override
    public UserVO getUser() {
        UserPO userPO = userRepository.getUserById(1);
        if (userPO == null) {
            return UserVO.builder().id(1).address("sha").phone("phone").build();
        } else {
            return UserVO.builder().id(userPO.getId()).username(userPO.getUsername()).build();
        }
    }
}
