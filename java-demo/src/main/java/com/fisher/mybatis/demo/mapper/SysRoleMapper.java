package com.fisher.mybatis.demo.mapper;

import com.fisher.mybatis.demo.model.SysRole;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface SysRoleMapper {

    List<SysRole> selectUserRoleById(Long id);

    List<SysRole> selectUserRoleById1(Long id);

    List<SysRole> selectUserRoleById2(Long id);

    List<SysRole> selectUserRoleById3(Long id);



    @Results({
            @Result(id = true, column = "id", property = "id"),
            @Result(column = "role_name", property = "roleName")
    })
    @Select({"SELECT * FROM sys_role WHERE id = #{id}"})
    SysRole selectSysRole(Long id);

}
