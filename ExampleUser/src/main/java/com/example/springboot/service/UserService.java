package com.example.springboot.service;

import com.example.springboot.dto.UserDto;
import com.example.springboot.entity.User;

import java.util.List;

public interface UserService {

    User createUser(UserDto userDto);

    List<UserDto> getAllUsers();

    boolean updateUser(Long id, UserDto userDto);

    UserDto getUserById(Long userId);

    void deleteUser(Long userId);

    List<User> searchUser(String search);

}
