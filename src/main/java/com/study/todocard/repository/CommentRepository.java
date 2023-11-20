package com.study.todocard.repository;

import com.study.todocard.entity.Card;
import com.study.todocard.entity.Comment;
import com.study.todocard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByCardOrderByCreatedAtDesc(Card card);
}