package com.study.todocard.util;

import com.study.todocard.dto.CommentRequestDto;
import com.study.todocard.entity.Card;
import com.study.todocard.entity.Comment;
import com.study.todocard.entity.User;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    public Comment getComment(CommentRequestDto requestDto, Card card, User user) {
        return Comment.builder()
                .contents(requestDto.getContents())
                .card(card)
                .user(user)
                .build();
    }
}
