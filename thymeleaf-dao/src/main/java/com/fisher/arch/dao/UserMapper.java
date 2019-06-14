package com.fisher.arch.dao;

import com.fisher.arch.dao.po.UserPO;
import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

@MapperScan
public interface UserMapper {

    UserPO getUserById(@Param("id") Integer id);

    int save(UserPO userPO);
}
