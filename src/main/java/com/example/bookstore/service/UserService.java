package com.example.bookstore.service;

import com.example.bookstore.dto.user.UserRegistrationRequestDto;
import com.example.bookstore.dto.user.UserResponseDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserResponseDto register(UserRegistrationRequestDto requestDto);
}
