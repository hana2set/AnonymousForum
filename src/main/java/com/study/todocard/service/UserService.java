package com.study.todocard.service;

import com.study.todocard.exception.BusinessException;
import com.study.todocard.exception.ExceptionType;
import com.study.todocard.security.dto.SignupRequestDto;
import com.study.todocard.entity.User;
import com.study.todocard.entity.UserRole;
import com.study.todocard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${admin.token}") // Base64 Encode 한 SecretKey
    private String ADMIN_TOKEN;

    public void signup(SignupRequestDto requestDto){
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());

        // 회원 중복 확인
        if (userRepository.findByUsername(username).isPresent()) {
            throw new BusinessException(ExceptionType.DUPLICATE_USER);
        }

        // email 중복확인
        String email = requestDto.getEmail();
        if (userRepository.findByEmail(email).isPresent()) {
            throw new BusinessException(ExceptionType.DUPLICATE_EMAIL);
        }

        // 사용자 ROLE 확인
        UserRole role = UserRole.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new BusinessException(ExceptionType.WRONG_ADMIN_PASSWORD);
            }
            role = UserRole.ADMIN;
        }

        // 사용자 등록
        User user = new User(username, password, email, role);
        userRepository.save(user);
    }
}