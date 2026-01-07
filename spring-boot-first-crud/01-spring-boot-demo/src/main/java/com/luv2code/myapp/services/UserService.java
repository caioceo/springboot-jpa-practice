package com.luv2code.myapp.services;

import com.luv2code.myapp.dto.request.CreateUserRequest;
import com.luv2code.myapp.dto.request.UpdateUserPasswordRequest;
import com.luv2code.myapp.dto.request.UpdateUserRequest;
import com.luv2code.myapp.dto.response.UserResponse;
import com.luv2code.myapp.exceptions.IdNotFoundException;
import com.luv2code.myapp.mapper.UserMapper;
import com.luv2code.myapp.models.User;
import com.luv2code.myapp.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> listarUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserResponse findById(Integer id){
            User user = userRepository.findById(id).orElseThrow(() -> new IdNotFoundException(id));
            return userMapper.toDto(user);
    }

    public UserResponse createUser(CreateUserRequest dto){

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("email is already registered");
        }

        User user = userMapper.toEntity(dto);

        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserResponse patchPassword(Integer id, UpdateUserPasswordRequest dto){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new IdNotFoundException(id));
        if(dto.getPassword().equals(user.getPassword())){
            user.setPassword(dto.getNewPassword());
            userRepository.save(user);
            return userMapper.toDto(user);
        }
        throw new RuntimeException("error when updating the password");
    }

        public UserResponse patchById(Integer id, UpdateUserRequest dto){
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new IdNotFoundException(id));

            if(dto.getPassword().equals(user.getPassword())){
                if(userRepository.existsByEmail(dto.getEmail()) && !dto.getEmail().equals(user.getEmail())) {
                    throw new RuntimeException("email is invalid");
                }

                if(dto.getName().equals(user.getName()) && dto.getEmail().equals(user.getEmail())){
                    throw new RuntimeException("no changes detected");
                }
                user.setName(dto.getName());
                user.setEmail(dto.getEmail());
                userRepository.save(user);
                return userMapper.toDto(user);
            }
            throw new RuntimeException("error when patching user info");
        }

    public void deleteUser(Integer id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return;
        }
        throw new IdNotFoundException(id);
    }
}
