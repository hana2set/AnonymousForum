package com.study.noticeboard.repository;

import com.study.noticeboard.dto.BoardRequestDto;
import com.study.noticeboard.dto.BoardResponseDto;
import com.study.noticeboard.entity.Board;
import com.study.noticeboard.entity.Board;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Board save(Board board) {
        // DB 저장
        KeyHolder keyHolder = new GeneratedKeyHolder(); // 기본 키를 반환받기 위한 객체

        String sql = "INSERT INTO board (username, contents, password) VALUES (?, ?, ?)";
        jdbcTemplate.update(con -> {
                    PreparedStatement preparedStatement = con.prepareStatement(sql,
                            Statement.RETURN_GENERATED_KEYS);

                    preparedStatement.setString(1, board.getUsername());
                    preparedStatement.setString(2, board.getContents());
                    preparedStatement.setString(3, board.getPassword());
                    return preparedStatement;
                },
                keyHolder);

        // DB Insert 후 받아온 기본키 확인
        Long id = keyHolder.getKey().longValue();
        board.setId(id);

        return board;
    }

    public List<BoardResponseDto> findAll() {
        // DB 조회
        String sql = "SELECT * FROM board";

        return jdbcTemplate.query(sql, new RowMapper<BoardResponseDto>() {
            @Override
            public BoardResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd HH:mm:ss");

                // SQL 의 결과로 받아온 Board 데이터들을 BoardResponseDto 타입으로 변환해줄 메서드
                Long id = rs.getLong("id");
                String username = rs.getString("username");
                String contents = rs.getString("contents");
                LocalDateTime create_date = LocalDateTime.parse(rs.getString("create_date"), formatter);
                return new BoardResponseDto(id, username, contents, create_date);
            }
        });
    }

    public void update(Long id, BoardRequestDto requestDto) {
        String sql = "UPDATE board SET username = ?, contents = ? WHERE id = ?";
        jdbcTemplate.update(sql, requestDto.getUsername(), requestDto.getContents(), id);
    }

    public void delete(Long id) {
        String sql = "DELETE FROM board WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Board findById(Long id) {
        // DB 조회
        String sql = "SELECT * FROM board WHERE id = ?";

        return jdbcTemplate.query(sql, resultSet -> {
            if (resultSet.next()) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-LL-dd HH:mm:ss");

                Board board = new Board();
                board.setUsername(resultSet.getString("username"));
                board.setContents(resultSet.getString("contents"));
                board.setPassword(resultSet.getString("password"));
                LocalDateTime create_date = LocalDateTime.parse(resultSet.getString("create_date"), formatter);
                return board;
            } else {
                return null;
            }
        }, id);
    }
}