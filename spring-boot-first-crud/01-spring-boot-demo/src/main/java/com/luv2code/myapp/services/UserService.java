package com.luv2code.myapp.services;

import com.luv2code.myapp.dto.request.CreateUserRequest;
import com.luv2code.myapp.dto.request.UpdateUserPasswordRequest;
import com.luv2code.myapp.dto.request.UpdateUserRequest;
import com.luv2code.myapp.dto.response.UserResponse;
import com.luv2code.myapp.exceptions.AlreadyExistsException;
import com.luv2code.myapp.exceptions.BusinessException;
import com.luv2code.myapp.exceptions.InternalServerErrorException;
import com.luv2code.myapp.exceptions.NotFoundException;
import com.luv2code.myapp.mapper.UserMapper;
import com.luv2code.myapp.models.User;
import com.luv2code.myapp.repositories.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponse> listarUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();
    }

    public UserResponse findById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User", id));
        return userMapper.toDto(user);
    }

    public UserResponse createUser(CreateUserRequest dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new AlreadyExistsException("User", "email", dto.getEmail());
        }

        User user = userMapper.toEntity(dto);

        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public UserResponse patchPassword(Integer id, UpdateUserPasswordRequest dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User", id));
        if (dto.getPassword().equals(user.getPassword())) {
            user.setPassword(dto.getNewPassword());
            userRepository.save(user);
            return userMapper.toDto(user);
        }
        throw new BusinessException("Current password is incorrect");
    }

    public UserResponse patchById(Integer id, UpdateUserRequest dto) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User", id));

        if (!dto.getPassword().equals(user.getPassword())) {
            throw new BusinessException("Current password is incorrect");
        }
        if (userRepository.existsByEmail(dto.getEmail()) && !dto.getEmail().equals(user.getEmail())) {
            throw new AlreadyExistsException("User", "email", dto.getEmail());
        }

        if (dto.getName().equals(user.getName()) && dto.getEmail().equals(user.getEmail())) {
            throw new BusinessException("No changes detected");
        }
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    public void deleteUser(Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return;
        }
        throw new NotFoundException("User", id);
    }
}
