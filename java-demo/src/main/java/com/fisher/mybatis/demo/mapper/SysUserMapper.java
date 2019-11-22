package com.fisher.mybatis.demo.mapper;

import com.fisher.mybatis.demo.model.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SysUserMapper {

    SysUser selectById(Long id);

    List<SysUser> selectAll();

    int insertSysUser(SysUser sysUser);

    int deleteSysUser(Long id);


    List<SysUser> selectUserByCondition(@Param("username")String username, @Param("email")String email);

    List<SysUser> selectUserByCondition1(@Param("username")String username, @Param("email")String email);

    int updateSysUserInfo(SysUser sysUser);


    SysUser selectSysUserByIdOrName(@Param("id") Long id, @Param("username") String username);


    List<SysUser> selectUserInIds(List<Long> ids);
}
