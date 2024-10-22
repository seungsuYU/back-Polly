package com.sparta.spring_prepare.Service;

import com.sparta.spring_prepare.Entity.User;
import lombok.Getter;

@Getter
public class UserDto {

    private Long id;
    private String username;

    public UserDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
    }
}
