package com.study.todocard.dto;

import com.study.todocard.entity.Card;
import com.study.todocard.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CardResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private boolean isComplate;
    private LocalDateTime createAt;

    public CardResponseDto(Card card) {
        this.id = card.getId();
        this.isComplate = card.isComplate();
        this.username = card.getUser().getUsername(); //TODO null처리
        this.title = card.getTitle();
        this.contents = card.getContents();
        this.createAt = card.getCreatedAt();
    }
}