package com.study.todocard.entity;

import com.study.todocard.dto.CardRequestDto;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "card")
public class Card extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String contents;

    @Column(columnDefinition = "boolean default false")
    private boolean isComplete = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "card")
    private List<Comment> commentList = new ArrayList<>();


    @Builder
    public Card(String title, String contents, boolean isComplete, User user) {
        this.title = title;
        this.contents = contents;
        this.isComplete = isComplete;
        this.user = user;
    }

    public void update(CardRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.isComplete = requestDto.getIsComplete();
    }

    public void updateComplete(boolean isComplete) {
        this.isComplete = isComplete;
    }
}