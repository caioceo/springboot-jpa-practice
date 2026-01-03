package com.luv2code.myapp.dto.response;

public class UserResponse {
    private Integer Id;
    private String name;
    private String email;


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Integer getId() {
        return Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
