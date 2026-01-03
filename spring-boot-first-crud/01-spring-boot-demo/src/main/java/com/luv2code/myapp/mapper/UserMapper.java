package com.luv2code.myapp.mapper;

import com.luv2code.myapp.dto.request.CreateUserRequest;
import com.luv2code.myapp.dto.response.UserResponse;
import com.luv2code.myapp.models.User;
import org.springframework.stereotype.Component;

@Component

public class UserMapper {

    public User toEntity(CreateUserRequest dto){
        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        return user;
    }

    public UserResponse toDto(User user){
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setName(user.getName());
        return dto;
    }


}
