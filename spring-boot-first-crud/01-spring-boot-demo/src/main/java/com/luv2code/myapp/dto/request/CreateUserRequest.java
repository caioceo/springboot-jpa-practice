package com.luv2code.myapp.dto.request;

public class CreateUserRequest {

    private String name;
    private String email;
    private String password;

    public String getPassword() {
        return password;
    }
    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
