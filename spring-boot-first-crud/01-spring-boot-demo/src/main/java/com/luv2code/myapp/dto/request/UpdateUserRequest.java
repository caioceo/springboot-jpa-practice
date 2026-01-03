package com.luv2code.myapp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class UpdateUserRequest {
    private String email;
    private String name;
    private final String password;
}
