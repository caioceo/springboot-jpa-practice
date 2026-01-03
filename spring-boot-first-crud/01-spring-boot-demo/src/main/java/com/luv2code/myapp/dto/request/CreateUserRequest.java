package com.luv2code.myapp.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CreateUserRequest {

    @Size(min = 3, max = 99)
    @NotBlank
    private String name;

    @Email
    @NotBlank
    private String email;
    
    @Size(min = 6, max = 255)
    @NotBlank
    private String password;
}
