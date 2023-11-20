package com.study.todocard.repository;

import com.study.todocard.entity.Card;
import com.study.todocard.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;


public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findAllByUserOrderByCreatedAtDesc(User user);
}