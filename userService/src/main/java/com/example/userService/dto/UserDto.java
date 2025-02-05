package com.example.userService.dto;

import com.example.userService.vo.ResponseOrder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class UserDto {
    private String email;
    private String pwd;
    private String name;
    private String userId;
    private Date createdAt;

    private String encryptedPwd;

    private List<ResponseOrder> orders;
}
