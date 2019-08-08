package com.fisher.arch.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO implements Serializable {

    private Integer id;
    private String username;
    private String birthday;
    private String address;
    private String phone;

    private String userXXx;
}
