package com.fisher.demo.tracing.opentracedemo.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserRequestForm {
    @NotNull(message = "不能为空")
    private String name;
    private Integer age;
    private String email;
    private Date birthday;
}
