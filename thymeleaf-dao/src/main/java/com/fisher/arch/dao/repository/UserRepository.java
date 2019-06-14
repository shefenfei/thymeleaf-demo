package com.fisher.arch.dao.repository;

import com.fisher.arch.dao.UserMapper;
import com.fisher.arch.dao.po.UserPO;
import com.fisher.arch.model.exception.UserNotFoundException;
import com.fisher.arch.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;

//@Component
@Slf4j
public class UserRepository {

//    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private UserMapper userMapper;

    public UserPO getUserById(Integer id) throws UserNotFoundException {
        //首先从Reids中获取，如果没有的话，从数据库读取
        UserVO userVO = (UserVO) redisTemplate.opsForHash().get("users" , id+"");
        if (userVO != null) {
            throw new UserNotFoundException("会员不存在");
//            UserPO userPO = new UserPO();
//            BeanUtils.copyProperties(userVO , userPO);
//            return userPO;
        } else {
            UserPO user = userMapper.getUserById(id);
            if (user != null) {
                redisTemplate.opsForHash().put("users" ,user.getId()+"" ,userVO);
                return user;
            } else {
                log.info("会员为 ：{} ，数据不存在!" , user);
                return null;
            }
        }
    }

}
