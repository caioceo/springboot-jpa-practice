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

    @NotBlank(message = "New password is required")
    @Size(min = 6, max = 255, message = "New password must be between 6 and 255 characters")
    private String newPassword;

    @NotBlank(message = "Current password is required")
    @Size(min = 6, max = 255, message = "Current password must be between 6 and 255 characters")
    private String password;
}
