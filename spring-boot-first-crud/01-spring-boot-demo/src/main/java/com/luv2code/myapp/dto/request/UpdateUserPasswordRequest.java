package com.luv2code.myapp.dto.request;

public class UpdateUserPasswordRequest {
    private String newPassword;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
