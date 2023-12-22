package com.study.todocard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.todocard.dto.CardRequestDto;
import com.study.todocard.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Table(name = "comment")
@NoArgsConstructor
public class Comment extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String contents;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @Builder
    public Comment(String contents, User user, Card card) {
        this.contents = contents;
        this.user = user;
        this.card = card;
    }

    public void update(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }

}