package com.learning.swd.models.Dto;

import lombok.Data;

@Data
public class UserDto {

    private String name;

    private String username;

    private String password;

    private  String email;

    private Boolean is_Admin;

    private boolean status;
}
