package com.fisher.arch.service;

import com.fisher.arch.model.exception.UserNotFoundException;
import com.fisher.arch.model.vo.UserVO;

public interface IndexService {

    String testIndex();

    UserVO getUser();


    UserVO getUserById(Integer id) throws UserNotFoundException;
}
