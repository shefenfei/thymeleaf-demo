package com.fisher.arch.service.impl;

import com.fisher.arch.dao.po.UserPO;
import com.fisher.arch.dao.repository.UserRepository;
import com.fisher.arch.model.exception.UserNotFoundException;
import com.fisher.arch.model.vo.UserVO;
import com.fisher.arch.service.IndexService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class IndexServiceImpl implements IndexService {

//    @Autowired
    private UserRepository userRepository;

    @Override
    public String testIndex() {
        return "hello";
    }

    @Override
    public UserVO getUser() {
        UserPO userPO = null;
        try {
            userPO = userRepository.getUserById(100);
        } catch (UserNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found" ,e);
        }
        if (userPO == null) {
            return UserVO.builder().id(1).address("sha").phone("phone").build();
        } else {
            return UserVO.builder().id(userPO.getId()).username(userPO.getUsername()).build();
        }
    }

    @Override
    public UserVO getUserById(Integer id) throws UserNotFoundException {
        UserPO userPO = userRepository.getUserById(id);
        if (userPO == null) {
            throw new UserNotFoundException("会员不存在");
        } else {
            return UserVO.builder().id(userPO.getId()).username(userPO.getUsername()).build();
        }
    }
}
