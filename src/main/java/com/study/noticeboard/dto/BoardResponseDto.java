package com.study.noticeboard.dto;

import com.study.noticeboard.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardResponseDto {
    private Long id;
    private String username;
    private String contents;
    private LocalDateTime create_date;

    public BoardResponseDto(Board board) {
        this.id = board.getId();
        this.username = board.getUsername();
        this.contents = board.getContents();
        this.create_date = board.getCreate_date();
    }
}