package com.luv2code.myapp.controller;

import com.luv2code.myapp.dto.request.CreateUserRequest;
import com.luv2code.myapp.dto.request.UpdateUserPasswordRequest;
import com.luv2code.myapp.dto.request.UpdateUserRequest;
import com.luv2code.myapp.dto.response.UserResponse;
import com.luv2code.myapp.models.User;
import com.luv2code.myapp.services.UserService;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    
    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserResponse> getAllUsers(){
        return userService.listarUsers();
    }


    @PostMapping("/")
    public UserResponse createUser(@RequestBody CreateUserRequest dto){
        return userService.createUser(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(@PathVariable Integer id){
        return ResponseEntity.ok(userService.findById(id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponse> patchById(@PathVariable Integer id, @RequestBody UpdateUserRequest dto){
        return ResponseEntity.ok(userService.patchById(id, dto));
    }

    @PatchMapping("/{id}/password")
    public ResponseEntity<UserResponse> patchPassword(@PathVariable Integer id, @RequestBody UpdateUserPasswordRequest dto){
        return ResponseEntity.ok(userService.patchPassword(id, dto));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
    }
}
