package com.fisher.arch.service;

import com.fisher.arch.model.exception.UserNotFoundException;
import com.fisher.arch.model.form.UserForm;
import com.fisher.arch.model.vo.UserVO;

import java.util.List;

public interface IndexService {

    String testIndex();

    UserVO getUser();

    UserVO getUserById(Integer id) throws UserNotFoundException;

    boolean saveUser(UserForm userForm);

    List findAllUser();
}
