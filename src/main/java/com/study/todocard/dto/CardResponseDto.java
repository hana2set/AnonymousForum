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
    private String title;
    private String contents;
    private boolean isComplete;
    private LocalDateTime createdAt;

    public CardResponseDto(Card card) {
        this.id = card.getId();
        this.isComplete = card.isComplete();
        this.username = card.getUser().getUsername(); //TODO null처리
        this.title = card.getTitle();
        this.contents = card.getContents();
        this.createdAt = card.getCreatedAt();
    }
}