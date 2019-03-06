package com.fisher.arch.model.vo;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserVO {

    private Integer id;
    private String username;
    private String birthday;
    private String address;
    private String phone;

}
