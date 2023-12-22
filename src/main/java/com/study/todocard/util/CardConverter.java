package com.study.todocard.util;

import com.study.todocard.dto.CardRequestDto;
import com.study.todocard.dto.CardResponseDto;
import com.study.todocard.dto.CommentRequestDto;
import com.study.todocard.dto.CommentResponseDto;
import com.study.todocard.entity.Card;
import com.study.todocard.entity.Comment;
import com.study.todocard.entity.User;
import org.springframework.stereotype.Component;

public class CardConverter {
    public static Card toEntity(CardRequestDto requestDto, User user) {
        return Card.builder()
                .title(requestDto.getTitle())
                .contents(requestDto.getContents())
                .isComplete(requestDto.getIsComplete())
                .user(user)
                .build();
    }

    public static CardResponseDto toResponse(Card card) {
        return CardResponseDto.builder()
                .id(card.getId())
                .isComplete(card.isComplete())
                .username(card.getUser().getUsername())
                .title(card.getTitle())
                .contents(card.getContents())
                .createdAt(card.getCreatedAt())
                .comments(card.getCommentList()
                        .stream()
                        .map(CommentResponseDto::new)
                        .toList())
                .build();
    }
}
