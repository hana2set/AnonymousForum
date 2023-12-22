package com.study.todocard.controller;

import com.study.todocard.security.dto.SignupRequestDto;
import com.study.todocard.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity signup(@Valid SignupRequestDto requestDto, BindingResult bindingResult) {
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

        userService.signup(requestDto);

        return new ResponseEntity(HttpStatus.CREATED);
    }

}