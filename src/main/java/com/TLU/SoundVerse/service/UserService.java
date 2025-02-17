package com.TLU.SoundVerse.service;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.TLU.SoundVerse.dto.request.CreateUserDto;
import com.TLU.SoundVerse.entity.User;
import com.TLU.SoundVerse.mapper.UserMapper;
import com.TLU.SoundVerse.repository.UserRepository;

import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {
    UserRepository userRepository;
    UserMapper userMapper;

    public User create(CreateUserDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email has been already used, please use another email!");
        }

        User user = userMapper.toUser(request);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    // public User updateUser(String userId, UpdateUserDto updateUserDto) {
    //     User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User Not Found"));

    //     usermapper.updateUser(user, updateUserDto);

    //     return usermapper.toUserResponseDto(userRepository.save(user));
    // }

    @Transactional
    public void deleteUser(String userId) {
        Integer id = Integer.parseInt(userId);
        userRepository.deleteById(id);
    }
}
