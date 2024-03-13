package com.example.springboot.service;

import com.example.springboot.dto.UserDto;
import com.example.springboot.entity.User;
import com.example.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User createUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setSalary(userDto.getSalary());
        User createdUser = userRepository.save(user);
        return createdUser;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(User::getUserDto).collect(Collectors.toList());
    }

    public List<User> searchUser(String search){
        return userRepository.findAllByNameContaining(search);
    }

    @Override
    public boolean updateUser(Long id, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()){
            User existingUser = optionalUser.get();
            existingUser.setName(userDto.getName());
            existingUser.setAge(userDto.getAge());
            existingUser.setSalary(userDto.getSalary());
            userRepository.save(existingUser);
            return true;
        }
        return false;
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> optionalCar = userRepository.findById(id);
        return optionalCar.map(User::getUserDto).orElse(null);
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}
