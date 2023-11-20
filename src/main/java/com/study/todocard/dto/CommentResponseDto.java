package com.study.todocard.dto;

import com.study.todocard.entity.Card;
import com.study.todocard.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class CommentResponseDto {
    private Long id;
    private String username;
    private String contents;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.username = comment.getUser().getUsername(); //TODO null처리
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
    }
}