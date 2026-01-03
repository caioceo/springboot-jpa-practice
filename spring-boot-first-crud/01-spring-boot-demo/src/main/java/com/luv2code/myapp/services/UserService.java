package com.luv2code.myapp.services;

import com.luv2code.myapp.dto.request.CreateUserRequest;
import com.luv2code.myapp.dto.request.UpdateUserPasswordRequest;
import com.luv2code.myapp.dto.request.UpdateUserRequest;
import com.luv2code.myapp.dto.response.UserResponse;
import com.luv2code.myapp.mapper.UserMapper;
import com.luv2code.myapp.models.User;
import com.luv2code.myapp.repositories.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public List<UserResponse> listarUsers(){
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserResponse findById(Integer id){
            User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("ID not found"));
            return userMapper.toDto(user);
    }

    public UserResponse createUser(CreateUserRequest dto){

        if(dto.getName() == null || dto.getName().length()<3){
            throw new RuntimeException("username is invalid");
        }

        if(dto.getPassword() == null || dto.getPassword().length()<6){
            throw new RuntimeException("password is invalid");
        }

        if(dto.getEmail() == null || !dto.getEmail().contains("@")){
            throw new RuntimeException("email is invalid");
        }

        if(userRepository.existsByEmail(dto.getEmail())){
            throw new RuntimeException("email is already registered");
        }

        User user = userMapper.toEntity(dto);

        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserResponse patchPassword(Integer id, UpdateUserPasswordRequest dto){
        User user = userRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("id not found"));
        if(dto.getPassword() == null){
            throw new RuntimeException("password is invalid");
        }
        if(dto.getPassword().equals(user.getPassword())){
            user.setPassword(dto.getNewPassword());
            return userMapper.toDto(user);
        }
        throw new RuntimeException("error to update password");
    }

        public UserResponse patchById(Integer id, UpdateUserRequest dto){
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Id not found"));

            if(dto.getPassword().equals(user.getPassword())){
                if(dto.getEmail() == null || !dto.getEmail().contains("@") || (userRepository.existsByEmail(dto.getEmail()) && !dto.getEmail().equals(user.getEmail()))) {

                    throw new RuntimeException("email is invalid");
                }
                if(dto.getName() == null || dto.getName().length()<3){
                    throw new RuntimeException("username is invalid");
                }

                if(dto.getName().equals(user.getName()) && dto.getEmail().equals(user.getEmail())){
                    throw new RuntimeException("no changes detected");
                }
                user.setName(dto.getName());
                user.setEmail(dto.getEmail());
                userRepository.save(user);
                return userMapper.toDto(user);
            }
            throw new RuntimeException("error to patch user data");
        }

    public void deleteUser(Integer id){
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
        }
        throw new EntityNotFoundException("ID not found");
    }
}
