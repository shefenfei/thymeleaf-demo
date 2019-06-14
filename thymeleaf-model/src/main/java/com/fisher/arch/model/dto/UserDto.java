package com.fisher.arch.model.dto;

public class UserDto {
    private String user_name;
    private String pass;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "user_name='" + user_name + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
