package com.study.todocard.dto;

import com.study.todocard.entity.Card;
import com.study.todocard.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class CardResponseDto {
    private Long id;
    private String username;
    private String title;
    private String contents;
    private boolean isComplete;
    private LocalDateTime createdAt;

    private List<CommentResponseDto> comments;
}