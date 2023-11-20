package com.study.todocard.controller;

import com.study.todocard.dto.UserInfoDto;
import com.study.todocard.entity.UserRoleEnum;
import com.study.todocard.security.dto.SignupRequestDto;
import com.study.todocard.security.UserDetailsImpl;
import com.study.todocard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    @PostMapping("/user/signup")
    public ResponseEntity<Object> signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
        // Validation 예외처리
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        if(fieldErrors.size() > 0) {
            List<String> details = new ArrayList<>();
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                String error = fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage();
                log.error(error);
                details.add(error);
            }
            return new ResponseEntity(details.toString(), HttpStatus.BAD_REQUEST);
        }

        try {
            userService.signup(requestDto);
        } catch (IllegalArgumentException ie) {
            return new ResponseEntity(ie.getMessage(), HttpStatus.CONFLICT);
        }

        return new ResponseEntity("계정이 생성되었습니다.", HttpStatus.CREATED);
    }

    // 회원 관련 정보 받기
    @GetMapping("/user-info")
    @ResponseBody
    public UserInfoDto getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        String username = userDetails.getUser().getUsername();
        UserRoleEnum role = userDetails.getUser().getRole();
        boolean isAdmin = (role == UserRoleEnum.ADMIN);

        return new UserInfoDto(username, isAdmin);
    }
}