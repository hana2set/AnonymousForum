package com.study.todocard.dto;

import lombok.Getter;

@Getter
public class CardRequestDto {
    private String title;
    private String contents;
    private Boolean isComplate;
}