package com.study.noticeboard.entity;

import com.study.noticeboard.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Board {
    private Long id;
    private String username;
    private String contents;
    private String password;
    private LocalDateTime create_date;

    public Board(BoardRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }
}