package com.study.todocard.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardRequestDto {
    private String title;
    private String contents;
    private Boolean isComplete = false;
}