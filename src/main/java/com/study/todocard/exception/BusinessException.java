package com.study.todocard.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class BusinessException extends RuntimeException{

    public BusinessException(String message) {
        super(message);
    }

}
