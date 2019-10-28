package com.fisher.arch.service.impl;

import com.fisher.arch.dao.po.UserPO;
import com.fisher.arch.dao.repository.UserRepository;
import com.fisher.arch.model.dto.UserDto;
import com.fisher.arch.model.exception.UserNotFoundException;
import com.fisher.arch.model.form.UserForm;
import com.fisher.arch.model.vo.UserVO;
import com.fisher.arch.service.IndexService;
import io.sentry.SentryClient;
import io.sentry.context.Context;
import io.sentry.event.BreadcrumbBuilder;
import io.sentry.event.UserBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexServiceImpl.class);

    @Autowired
    private UserRepository userRepository;

    @Resource
    private SentryClient sentryClient;

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

    @Override
    public boolean saveUser(UserForm userForm) {
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userForm, userDto);
        userDto.setUser_name(userForm.getUsername());
        return userRepository.saveUser(userDto);
    }

    @Override
    public List<UserVO> findAllUser() {
        return userRepository.findAllUser().stream().map((userDto) -> {
            UserVO userVO = new UserVO();
            userVO.setUsername(userDto.getUser_name());
            return userVO;
        }).collect(Collectors.toList());
    }

    @Override
    public void reportException() {
        Context context = sentryClient.getContext();
        context.recordBreadcrumb(new BreadcrumbBuilder().setMessage("report exception error").build());
        context.setUser(new UserBuilder().setEmail("she1990111@sina.com").build());
        try {
            int result = 1 / 0;
            LOGGER.info("结果: {}", result);
        } catch (Exception e) {
            LOGGER.error("出错了: {}", e.getMessage());
            sentryClient.sendException(e);
            sentryClient.sendMessage("出错了");
        }
    }


}
