package com.study.todocard.service;

import com.study.todocard.constant.CommonTest;
import com.study.todocard.entity.Card;
import com.study.todocard.entity.Comment;
import com.study.todocard.entity.User;
import com.study.todocard.entity.UserRole;
import com.study.todocard.repository.CardRepository;
import com.study.todocard.repository.CommentRepository;
import com.study.todocard.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class CardServiceImplTest implements CommonTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    CardService cardService;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    CommentRepository commentRepository;

    @Test
    @DisplayName("댓글단 게시글 조회")
    @Transactional
    void listRelationCards() {
        //given
        User user1 = getTestUser("test_user_1", "1234567890", "a@a.a");
        User user2 = getTestUser("test_user_2", "1234567891", "a@a.b");
        userRepository.save(user1);
        userRepository.save(user2);

        Card card = Card.builder().user(user2).build();
        cardRepository.save(card);

        Pageable pageable1 = PageRequest.of(0,1);
        Pageable pageable2 = PageRequest.of(0,2);

        Comment comment1 = Comment.builder().card(card).user(user1).build();
        Comment comment2 = Comment.builder().card(card).user(user1).build();
        commentRepository.save(comment1);
        commentRepository.save(comment2);

        //when - then
        assertFalse(cardService.listRelationCards(pageable1, user1).isEmpty());
        assertTrue(cardService.listRelationCards(pageable1, user2).isEmpty());
        assertTrue(cardService.listRelationCards(pageable1, user1).getSize() == 1);
        assertTrue(cardService.listRelationCards(pageable2, user1).getSize() == 2);
    }

    private User getTestUser(String username, String password, String email) {
        return User.builder().username(username).password(password).email(email).role(UserRole.USER).build();
    }
}
