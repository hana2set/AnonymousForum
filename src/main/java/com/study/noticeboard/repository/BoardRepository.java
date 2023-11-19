package com.study.noticeboard.repository;

import com.study.noticeboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findAllByOrderByModifiedAtDesc();

}