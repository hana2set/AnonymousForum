package com.study.noticeboard.service;

import com.study.noticeboard.dto.BoardRequestDto;
import com.study.noticeboard.dto.BoardResponseDto;
import com.study.noticeboard.entity.Board;
import com.study.noticeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public BoardResponseDto createBoard(BoardRequestDto requestDto) {
        // RequestDto -> Entity
        Board board = new Board(requestDto);

        // DB 저장
        Board saveBoard = boardRepository.save(board);

        // Entity -> ResponseDto
        BoardResponseDto boardResponseDto = new BoardResponseDto(saveBoard);

        return boardResponseDto;
    }

    public List<BoardResponseDto> getBoards() {
        // DB 조회
        return boardRepository.findAll();
    }

    public BoardResponseDto getBoard(Long id) {
        Board board = boardRepository.findById(id);
        if (board != null) {
            return new BoardResponseDto(boardRepository.findById(id));
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    public Long updateBoard(Long id, BoardRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board = boardRepository.findById(id);
        if (board != null) {
            // board 내용 수정
            if (isValidPassword(board.getPassword(), requestDto.getPassword())) {
                boardRepository.update(id, requestDto);
            } else {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }

            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    public Long deleteBoard(Long id, String password) {
        // 해당 메모가 DB에 존재하는지 확인
        Board board = boardRepository.findById(id);
        if (board != null) {
            // board 삭제
            if (isValidPassword(board.getPassword(), password)) {
                boardRepository.delete(id);
            } else {
                throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
            }

            return id;
        } else {
            throw new IllegalArgumentException("선택한 메모는 존재하지 않습니다.");
        }
    }

    private boolean isValidPassword(String db_password, String request_password) {
        if (db_password == null)
            return true;

        if (db_password != null && request_password != null)
            return db_password.equals(request_password);

        return false;
    }

}