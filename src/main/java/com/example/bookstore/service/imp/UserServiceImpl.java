package com.example.bookstore.service.imp;

import com.example.bookstore.dto.user.UserRegistrationRequestDto;
import com.example.bookstore.dto.user.UserResponseDto;
import com.example.bookstore.exception.RegistrationException;
import com.example.bookstore.mapper.UserMapper;
import com.example.bookstore.model.Role;
import com.example.bookstore.model.User;
import com.example.bookstore.repository.RoleRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponseDto register(UserRegistrationRequestDto request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RegistrationException("Can't register user");
        }
        User user = userMapper.toModel(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.getRoles().add(roleRepository.findByName(Role.RoleName.ROLE_USER));
        return userMapper.toUserResponseDto(userRepository.save(user));
    }
}
