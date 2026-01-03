package com.luv2code.myapp.dto.request;

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

public class UpdateUserPasswordRequest {

    @NotBlank
    @Size(min = 6, max = 255)
    private String newPassword;

    @NotBlank
    @Size(min = 6, max = 255)
    private String password;
}
