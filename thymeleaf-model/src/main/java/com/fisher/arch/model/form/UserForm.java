package com.fisher.arch.model.form;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 会员模块提交表单
 */
@Data
public class UserForm implements Serializable {

    @NotNull(message = "用户名不能为空!")
    private String username;

    @NotNull(message = "密码不能为空")
    @Range(min = 6 , max = 12 , message = "密码长度不正确 , 长度应该在6-12位!")
    private String pass;

}
