package com.study.todocard.dto;

import com.study.todocard.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CardResponseDto {
    private Long id;
    private String username;
    private String contents;

    public CardResponseDto(Card card) {
        this.id = card.getId();
        this.username = card.getUsername();
        this.contents = card.getContents();
    }
}