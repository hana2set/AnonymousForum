package com.study.noticeboard.service;

import com.study.noticeboard.dto.BoardRequestDto;
import com.study.noticeboard.dto.BoardResponseDto;
import com.study.noticeboard.entity.Board;
import com.study.noticeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        Board saveBoard = boardRepository.save(new Board(requestDto));
        return new BoardResponseDto(saveBoard);
    }

    public List<BoardResponseDto> getBoards() {
        // DB 조회
        return boardRepository.findAllByOrderByModifiedAtDesc().stream().map(BoardResponseDto::new).toList();
    }

    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 메모는 존재하지 않습니다."));
        return new BoardResponseDto(board);
    }

    @Transactional
    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        // DB에 존재하는지 확인
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 메모는 존재하지 않습니다."));

        board.update(requestDto);
        return id;
    }

    public Long deleteBoard(Long id) {
        // DB에 존재하는지 확인
        Board board = boardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 메모는 존재하지 않습니다."));

        boardRepository.delete(board);
        return id;
    }


}