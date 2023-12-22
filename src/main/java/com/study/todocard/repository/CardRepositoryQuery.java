package com.study.todocard.repository;

import com.study.todocard.entity.Card;
import com.study.todocard.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CardRepositoryQuery {
    Page<Card> findAllRelationCardByUser(Pageable pageable, User user);
}
