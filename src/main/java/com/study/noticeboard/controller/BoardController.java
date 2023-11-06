package com.study.noticeboard.controller;

import com.study.noticeboard.dto.BoardRequestDto;
import com.study.noticeboard.dto.BoardResponseDto;
import com.study.noticeboard.service.BoardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @PostMapping("/boards")
    public BoardResponseDto createBoard(@RequestBody BoardRequestDto requestDto) {
        return boardService.createBoard(requestDto);
    }

    @GetMapping("/boards")
    public List<BoardResponseDto> getBoards() {
        return boardService.getBoards();
    }

    @GetMapping("/boards/{id}")
    public BoardResponseDto getBoard(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @PutMapping("/boards/{id}")
    public Long updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto) {
        return boardService.updateBoard(id, requestDto);
    }

    @DeleteMapping("/boards/{id}/{password}")
    public Long deleteBoard(@PathVariable Long id, @PathVariable String password) {
        return boardService.deleteBoard(id, password);
    }
}