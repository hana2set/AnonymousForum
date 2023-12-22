package com.study.todocard.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.study.todocard.dto.CardRequestDto;
import com.study.todocard.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@Table(name = "comment")
@NoArgsConstructor
@AllArgsConstructor
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


    public void update(CommentRequestDto requestDto) {
        this.contents = requestDto.getContents();
    }

}